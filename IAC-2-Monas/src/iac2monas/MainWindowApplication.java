package iac2monas;
/**
 * 
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

/**
 * @author angelica
 *
 */
public class MainWindowApplication {

	/**
	 * @param args
	 */
	String file;
	String modelName;
	JFrame mainFrame;
	
	public MainWindowApplication(){
		modelName = new String();
		file = new String();
	}
	
	public MainWindowApplication(String file){
		modelName = new String();
		this.file = file;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**args[0] --> filename args[1]--> src-gen args[2] --> flag for activities only
		*/	
	//	args = new String[2];
	//	args[0] = new String("/home/angelica/AngelicasWorkspace/testw/testw.stct");
	//	args[1] = new String("/home/angelica/Desktop/Monas/src");
	//	args[2] = new String("activity");
	//	args[0] = new String("/home/angelica/AngelicasWorkspace/TestEdit/TestEdit.stct" );
	//	args[1] = new String("/home/angelica/Desktop/Monas/src/activities" );
	//	args[2] = new String("testActivity" );

		ModelConvertor modelconvertor = new ModelConvertor(); 
		
		File f=null;
		if(args[0].endsWith("stct")){
			
			MainWindowApplication ap = new MainWindowApplication();
			
			if(args ==null || args.length<2){
				f =  new File(args[0]);
				
			//	ap.file = modelconvertor.IACToStateChart(f.getAbsolutePath(), f.getParent());
				ap.mainFrame.setVisible(false);
				ap.createDialog("Two attributes needed, the file of the .stct Model and the target folder for code generation");
			}else if(args.length== 3){
				f =  new File(args[0]);
				ap.file = modelconvertor.StateChartToIAC(f.getAbsolutePath(), f.getParent());
				ap.runWorkflow(args[1], "activityGen.mwe");
				File newf = new File(ap.file);
				newf.delete();
			}
			else{
				int op =JOptionPane.showConfirmDialog(null, "Would you like to add logger in transition?");
				f =  new File(args[0]);
				ap.modelName = new String (args[0]);
				ap.modelName = ap.modelName.substring(0, ap.modelName.indexOf(".stct"));
				StringTokenizer tok = new StringTokenizer(ap.modelName, File.separator);
				String model =  new String();
				while(tok.hasMoreTokens()){
					model = tok.nextToken();
				}
				ap.modelName = model;
				try {
					ap.file = new File( modelconvertor.StateChartToIAC(f.getAbsolutePath(), f.getParent())).toURL().getPath();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ap.mainFrame.setVisible(false);
					ap.createDialog("URL malformed for the stct file you chose");
					System.exit(0);
				}
				if(op == 0){//yes
					
					ap.mainFrame = new JFrame("Code Generator Message!!!");
					JPanel p =  new JPanel();
					p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					p.add(new JLabel("Please Wait While Code is Generated!!!"));
					ap.mainFrame.add(p);			
					ap.mainFrame.pack();
					ap.mainFrame.setLocation(300, 300);
					ap.mainFrame.setVisible(true);
					ap.runWorkflow(args[1], "logger.mwe");
				}
				else if (op==1){//no
					ap.mainFrame = new JFrame("Code Generator Message!!!");
					JPanel p =  new JPanel();
					p.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
					p.add(new JLabel("Please Wait While Code is Generated!!!"));
					ap.mainFrame.add(p);			
					ap.mainFrame.pack();
					ap.mainFrame.setLocation(300, 300);
					ap.mainFrame.setVisible(true);
					ap.runWorkflow(args[1], "workflow.mwe");
				}else{
					JOptionPane.showMessageDialog(null, "The code generation is canceled!");
					return;
				}
				File newf = new File(ap.file);
				newf.delete();
			}
		}else if(args[0].endsWith("iac")){
			//Converting from IAC model to Statechart Model
			 f =  new File(args[0]);
			
			String modelFile =modelconvertor.IACToStateChart(f.getAbsolutePath(), args[1]);
			JOptionPane.showMessageDialog(null, "Your statechart model is :" + modelFile);
		}
	}
	
	public void runWorkflow(String srcGenPath, String workflowName){
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("model", file);
		properties.put("src-gen",srcGenPath);
		
		if(workflowName.equals("workflow.mwe") || workflowName.equals("logger.mwe")){
			File src = new File(srcGenPath);
			
			File f = new File(src+ File.separator + "statecharts"+File.separator + modelName);
			removeDirectory(f);
			f.mkdir();
			properties.put("statechart",f.getAbsolutePath());
			f = new File(srcGenPath+File.separator+"activities");
			properties.put("activities", f.getAbsolutePath());
			f = new File(src+ File.separator + "statecharts"+File.separator + modelName+File.separator+"transitions");
			properties.put("transitions", f.getAbsolutePath());
		}
		//properties.put("out", System.getProperty("user.home"));
		ProgressMonitor themonitor;
		themonitor = new ProgressMonitor() {
			
			@Override
			public void worked(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void subTask(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void started(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setTaskName(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setCanceled(boolean arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void preTask(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postTask(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isCanceled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void internalWorked(double arg0) {
				//resource/ TODloggerO Auto-generated method stub
				
			}
			
			@Override
			public void finished(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void done() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beginTask(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		};
	
		WorkflowRunner wr = new WorkflowRunner();
		@SuppressWarnings("deprecation")
		boolean g = wr.run(workflowName, themonitor, properties, null);
		if(g){
			if(mainFrame!=null){
				mainFrame.setVisible(false);
				createDialog("Code is successfully generated for model in folder: "+ srcGenPath);
			}else
				return;
		}
		else{
			mainFrame.setVisible(false);
			createDialog("An error occured in the workflowRunner!!!");
		}
				
	}
	
	private void createDialog(String message){
		final JFrame frame = new JFrame("Message");
		JButton ok = new JButton("OK");
		JPanel p1, p2;
		frame.setLayout(new BorderLayout(10, 10));
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(new JLabel( message));
		p1.setMinimumSize(new Dimension(message.length()*5, 30));
		p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(ok);
		ok.setMinimumSize(new Dimension(50, 50));
	
		frame.add(p1, BorderLayout.PAGE_START);
		frame.add(p2, BorderLayout.PAGE_END);
		ok.setActionCommand("OK");
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if("OK".equals(arg0.getActionCommand())) {
					  frame.setVisible(false);
					  System.exit(0);
				    }
			}
		});
	
		frame.pack();
		frame.setLocation(200, 200);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
	

public static boolean removeDirectory(File directory) {

  // System.out.println("removeDirectory " + directory);

  if (directory == null)
    return false;
  if (!directory.exists())
    return true;
  if (!directory.isDirectory())
    return false;

  String[] list = directory.list();

  // Some JVMs return null for File.list() when the
  // directory is empty.
  if (list != null) {
    for (int i = 0; i < list.length; i++) {
      File entry = new File(directory, list[i]);

      //        System.out.println("\tremoving entry " + entry);

      if (entry.isDirectory())
      {
        if (!removeDirectory(entry))
          return false;
      }
      else
      {
        if (!entry.delete())
          return false;
      }
    }
  } 

  return directory.delete();
}

}
