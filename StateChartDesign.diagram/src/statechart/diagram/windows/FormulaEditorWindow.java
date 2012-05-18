package statechart.diagram.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.jface.action.IAction;

import org.eclipse.ui.IWorkbenchWindow;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;

import statechart.diagram.application.StateChartApplication;

import statechart.diagram.part.Liveness2StateChart;


public class FormulaEditorWindow {

	/**
	 * @generated NOT
	 */
	String formulaIAC;

	/**
	 * @generated NOT
	 */
	String fName;
	IAction action;
	IWorkbenchWindow window;
	
	public FormulaEditorWindow(IAction action,IWorkbenchWindow window){
		
		formulaIAC =  new String();
		fName = new String();
		this.action = action;
		this.window = window;
		
	}
	
	public void createFormulaWindow(final String filename, String fname, String formulatext){
		
		final JFrame MainWindow = new JFrame("Formula Liveness");
		final JTextArea formulaName;
	
		if(fname==null)
			formulaName =  new JTextArea("",1, 38);
		else
			formulaName =  new JTextArea(fname,1, 38);
		final JTextArea formulaField = new JTextArea(formulatext, 25, 40);
		
		JButton cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		Action tabAction = new Action() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("TAB RE");
				JOptionPane.showMessageDialog(null, "TAB RE");
				formulaField.setCursor( formulaName.getCursor());
				formulaName.setCursor(null);
			}
			
			@Override
			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		
		
		cancel.setActionCommand("cancel");
		OK.setActionCommand("OK");
		
		JLabel nlabel = new JLabel("Enter Formula's Name   :");	
		JLabel flabel = new JLabel("Enter Liveness Formula :");
		
		JPanel nPanel = new JPanel();
		JPanel fPanel = new JPanel();
		
		nlabel.setLabelFor(formulaName);
		flabel.setLabelFor(formulaField);
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;
        c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
        c.fill = GridBagConstraints.NONE;      //reset to default
        c.weightx = 0.0;
        nlabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        nPanel.add(nlabel, c);

        c.gridwidth = GridBagConstraints.REMAINDER;     //end row
        c.fill = GridBagConstraints.NONE;
        c.weightx = 1.0;
      
        nPanel.add(formulaName, c);
       
		nPanel.setLayout(gridbag);
		nPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
		nPanel.setVisible(true);
		
		GridBagLayout newgrid = new GridBagLayout();
		GridBagConstraints c2 = new GridBagConstraints();
		c2.anchor = GridBagConstraints.EAST;
		c2.gridwidth = GridBagConstraints.RELATIVE;
		c2.fill = GridBagConstraints.NONE;
		c2.weightx = 0.0;
		c2.weighty = 0.0;
		
		flabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 350, 10));
		fPanel.add(flabel, c2);
		
		c2.gridwidth = GridBagConstraints.REMAINDER;     //end row
	    c2.fill = GridBagConstraints.HORIZONTAL;
	    c2.weightx = 1.0;
	    
	    fPanel.add(formulaField, c2);
		
		fPanel.setLayout(newgrid);
		fPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		fPanel.setVisible(true);
		JPanel buttons = new JPanel();
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.add(cancel);
		buttons.add(OK);
		
		nPanel.setBackground(new Color(198, 198, 198));
		fPanel.setBackground(new Color(198, 198, 198));
		buttons.setBackground(new Color(198, 198, 198));
		MainWindow.setLayout(new BorderLayout());
		MainWindow.add(nPanel, BorderLayout.NORTH);
		MainWindow.add(fPanel, BorderLayout.CENTER);
		MainWindow.add(buttons, BorderLayout.PAGE_END);
		MainWindow.setLocation(200, 200);
		
		
		formulaName.getInputMap().put(KeyStroke.getKeyStroke("VK_TAB"), "changeWindow");
		formulaName.getActionMap().put("changeWindow", tabAction );
			
		cancel.addActionListener(new ActionListener() {
			/**
			 * @generated NOT
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if("cancel".equals(arg0.getActionCommand()))
					MainWindow.setVisible(false);
			}
		});
		
		OK.addActionListener(new ActionListener() {
			/**
			 * @generated NOT
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame f= new JFrame();
				
				if("OK".equals(arg0.getActionCommand())){
					
					try {
						fName = formulaName.getText();
						formulaIAC = formulaField.getText();
						
						File fil;
						File dir;
					    if(formulaIAC!=null){
					    					    	
							if(filename!=null){
								MainWindow.setVisible(false);
								
								int selection = -1;
								selection = JOptionPane.showConfirmDialog(null, "Do you want to overwrite the existing model?");
							
								if(selection==0){//yes
									fil = new File(filename);
									fil.delete();
									fil = new File(filename);
									dir =  new File(fil.getParent());
								}else if(selection == 1){//no
									int i=1;
									fil = new File(filename);
									dir = new File(fil.getParent());
									int indexx = dir.getAbsolutePath().length();
									String modelname = filename.substring(indexx);
									System.out.println("ONOMA "+ modelname);
								
									while(dir.exists()){
										dir = new File(StateChartApplication.workspace.getAbsolutePath()+ File.separator + fName+Integer.toString(i));
										i++;
									}
									dir.mkdir();
									fil = new File(dir.getAbsolutePath()+File.separator+modelname);
								}else//cancel
									return;
							}
							else{
								
								if(fName==null)
									fName = "defaultName";
								dir = new File(StateChartApplication.workspace.getAbsolutePath()+ File.separator + fName);
							
								if(dir.exists()){
									JOptionPane.showMessageDialog(null, "A model with the same name already exists in your workspace please change the formula's name!");
								//	System.out.println("Exists re");
																	
									return;
								}
								else{
									MainWindow.setVisible(false);
									dir.mkdir();
						
									fil= new File(dir.getAbsolutePath()+ File.separator+fName+".txt");
								}
							}
											
							FileWriter fw;
							fw = new FileWriter(fil);
							fw.write(fName+"\n"+formulaIAC);
							
							fw.close();
							Liveness2StateChart live  =  new Liveness2StateChart( formulaIAC, fName);
							Model model =  StatechartFactory.eINSTANCE.createModel();
					    	model = live.formula(formulaIAC, fName);
					    	// Create a resource set to hold the resources.
							//
					    	//JOptionPane.showMessageDialog(f, model.getName());
							ResourceSet resourceSet = new ResourceSetImpl();
						//	TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(resourceSet);
							// Register the appropriate resource factory to handle all file
							// extensions.
							//
							resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
									.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
											new XMIResourceFactoryImpl());
	
							// Register the package to ensure it is available during loading.
							//
							resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
									StatechartPackage.eINSTANCE);
	
							Resource resource = resourceSet.createResource(URI
									.createURI("http://statechart/1.0"));
							
							// create output							
							File newFile = new File(dir.getAbsolutePath()+  File.separator+model.getName() +".stct");
						
							JFileChooser dialog = new JFileChooser();
							
							dialog.setDialogTitle("Choose src folder to connect the new model(activities folder for Monas architecture)");
							dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							int retval = dialog.showOpenDialog(null);
							String src = new String();
							if (retval == JFileChooser.APPROVE_OPTION) {
								
								src = dialog.getSelectedFile().toURI().toString();
				
								StateChartApplication.generationURL.add(src);
								StateChartApplication.modelURL.add(newFile.toURI().toString());
								/* Update preferences  */
								StateChartApplication.writeRepositories();
								model = searchName(model.getNodes().get(0).getChildren(), src, model);
							}
							resource.getContents().add(model);
							FileOutputStream out = new FileOutputStream(newFile);	
							resource.save(out, null);
							out.close();
							
							
							JOptionPane.showMessageDialog(f, "Model is generated :" + newFile.getAbsolutePath());
					    }
					    
				     } catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							if(e.getClass().equals(NullPointerException.class))
								JOptionPane.showMessageDialog(f, "There is an error in your formulas.Please refer to the EBNF grammar.");
							else
								JOptionPane.showMessageDialog(f, "An exception occurred! "+e.getClass().toString());
						}
				}
			}
		});		
		
		MainWindow.pack();
		MainWindow.setVisible(true);
	}

	private Model searchName(List<Node> nodes, String src, Model model){
		try {
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).getType().equals("BASIC")){
				Node n  = nodes.get(i);
				
				File f = new File(new java.net.URI(src));
				
				if(new File(f.getAbsolutePath()+File.separator+nodes.get(i).getName()).exists())
					n.setActivity(new File(f.getAbsolutePath()+File.separator+nodes.get(i).getName()).toURI().toString());
				else
					n.setActivity(null);
				nodes.set(i, n);
			}
			model = searchName(nodes.get(i).getChildren(), src, model);
		}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
}
