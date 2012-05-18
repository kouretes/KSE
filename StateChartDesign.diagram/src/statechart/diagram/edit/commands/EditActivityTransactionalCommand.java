/**
 * 
 */
package statechart.diagram.edit.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;
import statechart.diagram.application.StateChartApplication;

/**
 * @author angelica
 *
 */
public class EditActivityTransactionalCommand extends
		AbstractTransactionalCommand {

	/**
	 * @param domain
	 * @param label
	 * @param affectedFiles
	 */
	private String ModelFile;
	private String State;

	
	public EditActivityTransactionalCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	public EditActivityTransactionalCommand(TransactionalEditingDomain domain,
			 String ModelFile, String State,  EditActivityCommand cmd) {
		super(domain, cmd.getLabel(), cmd.getAffectedFiles());
		this.ModelFile = ModelFile;
		this.State = State;

	}
	/**
	 * @param domain
	 * @param label
	 * @param options
	 * @param affectedFiles
	 */
	public EditActivityTransactionalCommand(TransactionalEditingDomain domain,
			String label, Map options, List affectedFiles) {
		super(domain, label, options, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		try{
			
			if(!StateChartApplication.modelURL.contains(this.ModelFile)){
				JOptionPane.showMessageDialog(null, "This Model is not Connected with a source Folder please Connect the model to a folder");
				return CommandResult.newErrorCommandResult("This Model is not Connected with a source Folder please Connect the model to a folder");
			}
			
			File activityFile;
			int indx =-1;
			indx = StateChartApplication.modelURL.indexOf(ModelFile);
			if(indx>-1 && indx<StateChartApplication.generationURL.size()){
				activityFile = new File(new java.net.URI( StateChartApplication.generationURL.get(indx)));
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Reconnect The Model To A Source Folder");
				return CommandResult.newErrorCommandResult("Please Reconnect The Model To A Source Folder");
			}
			
			String directory = new String(activityFile.getAbsolutePath()+File.separator+ State);
			File dir = new File(directory);
			
			if(!dir.exists()){
				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
						.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
									new XMIResourceFactoryImpl());
				resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
						StatechartPackage.eINSTANCE);
		
				Resource resource = resourceSet.createResource(URI
						.createURI("http://statechart/1.0"));
							
				File source = new File(new java.net.URI(ModelFile));
				
				resource.load(new FileInputStream(source), new HashMap<Object,Object>());
				resource.load(null);
				
				Model model = (statechart.Model) resource.getContents().get(0);

				model = searchName(model.getNodes(), State, "GENERATE", model);
				
				resource.getContents().clear();
				resource.getContents().add(model);
				FileOutputStream out;
				
				out = new FileOutputStream(source);
				resource.save(out, null);
				out.close();
			
				Runtime rt = Runtime.getRuntime();
				
				Process pr;
				File modelForGeneration = new File(new java.net.URI(ModelFile));
				if(System.getProperty("os.name").equals("Linux"))
					pr= rt.exec(StateChartApplication.generator+" "+ modelForGeneration.getAbsolutePath() + " "+ dir.getParent() + " "+State );
				else{
					String[] command=  {StateChartApplication.generator+" "+ modelForGeneration.getAbsolutePath()+ " "+ dir.getAbsolutePath()};
					pr = rt.exec(command);
					}
				//System.out.println("java -jar /home/angelica/Desktop/generator.jar "+ ModelFile+ " "+ activityFile.getAbsolutePath() +" "+ State);
				//Process pr = rt.exec("java -jar /home/angelica/Desktop/generator.jar "+ ModelFile+ " "+ activityFile.getAbsolutePath() +" "+ State );
				BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
          	  
				String line=null;

				while((line=input.readLine()) != null) {
					System.out.println(line);
				}
				resource.load(new FileInputStream(source), new HashMap<Object,Object>());
				resource.load(null);
				model = (statechart.Model) resource.getContents().get(0);
					
				model = searchName(model.getNodes(), State, dir.toURI().toString(), model);
				
				resource.getContents().clear();
				resource.getContents().add(model);
				 
				out = new FileOutputStream(source);
				resource.save(out, null);
				out.close();
				openCppEditor(dir);
			}else{//if the directory exists!!!
				ResourceSet resourceSet = new ResourceSetImpl();
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
						.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
									new XMIResourceFactoryImpl());
				resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
						StatechartPackage.eINSTANCE);
		
				Resource resource = resourceSet.createResource(URI
						.createURI("http://statechart/1.0"));
							
				File source = new File(new java.net.URI(ModelFile));
				
				resource.load(new FileInputStream(source), new HashMap<Object,Object>());
				resource.load(null);
				
				Model model = (statechart.Model) resource.getContents().get(0);
				Model copied = StatechartFactory.eINSTANCE.createModel();
				copied = model;
				
				model = searchName(model.getNodes(), State, dir.toURI().toString(), model);
				if(!model.equals(copied)){
					resource.getContents().clear();
					resource.getContents().add(model);
				 
					FileOutputStream out = new FileOutputStream(source);
					resource.save(out, null);
					out.close();
				}
				openCppEditor(dir);

			}
						
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}catch(Exception x){
			System.err.println("Exception occurd while invoking Editor!");
			
      	  // TODO Auto-generated catch block
      	 x.printStackTrace();
        }
		return CommandResult.newOKCommandResult();
	}

	private Model searchName(List<Node> nodes, String nodeName, String activityAttribute, Model model){
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).getType().equals("BASIC") && nodes.get(i).getName().equals(nodeName)){
				Node n  = nodes.get(i);
				if(n.getActivity()==null)
					n.setActivity(activityAttribute);
				else if(n.getActivity()=="")
					n.setActivity(activityAttribute);
				else if(n.getActivity().equals("GENERATE"))
					n.setActivity(activityAttribute);
				nodes.set(i, n);
			}
			model = searchName(nodes.get(i).getChildren(), nodeName, activityAttribute, model);
		}
		return model;
	}
	
	private void openCppEditor(File dir){
		
		String[] editors;
		String prog = new File(new File(System.getProperty("java.home")).getParent()).getParent();
		if(System.getProperty("os.name").equals("Linux")){
			 editors = new String[]{StateChartApplication.cppeditor, "geany", "eclipse", "gedit" };
		}else{
			editors = new String[]{StateChartApplication.cppeditor , "notepad.exe", prog+File.separator+"\\Windows NT"+File.separator+"Accessories"+File.separator+"wordpad.exe" };
		}
		String editor = null;
		Runtime runtime=Runtime.getRuntime();
		if(System.getProperty("os.name").equals("Linux")){
		try {			
			for (int count = 0; count < editors.length && editor == null; count++)
			
				if (runtime.exec(new String[] {"which", editors[count]}).waitFor() == 0)
					editor = editors[count];
			
			if (editor == null)
				throw new Exception("Could not find cpp editor");
			else{	
			
				String urlH = new String(dir.getAbsolutePath()+File.separator+ State+".h");
				String urlCPP = new String(dir.getAbsolutePath()+File.separator+ State+".cpp");
		
				runtime.exec(new String[] { editor, urlH, urlCPP });
				
			
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else{
		String urlH = new String(dir.getAbsolutePath()+File.separator+ State+".h");
		String urlCPP = new String(dir.getAbsolutePath()+File.separator+ State+".cpp");
		for(int u=0; u<editors.length; u++){
			editor = editors[u];
			try {
				runtime.exec( editor +" \""+urlH+"\"");
				runtime.exec( editor+" \""+urlCPP+"\"" );
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

}
