package statechart.diagram.edit.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import statechart.Model;
import statechart.diagram.application.StateChartApplication;


public class GenerationCommand extends AbstractCommand implements ICommand {
	
	private String fileName;
	private Model model;

	public GenerationCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}
	public GenerationCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public GenerationCommand(String label, String fileName, Model model) {
		super(label);
		this.fileName = fileName;
		this.model = model;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		
		      try {
		    		if(!StateChartApplication.modelURL.contains(this.fileName)){
		    			
		    			JOptionPane.showMessageDialog(null, "This model is not connected with a source folder please connect the model to a folder "+this.fileName);
						return CommandResult.newOKCommandResult();
					}
					
					File activityFile;
					int indx =-1;
					indx = StateChartApplication.modelURL.indexOf(fileName);
					if(indx>-1 && indx<StateChartApplication.generationURL.size()){
						activityFile = new File(new java.net.URI(StateChartApplication.generationURL.get(indx)));
					}
					else{
						JOptionPane.showMessageDialog(null, "Please Reconnect The Model To A Source Folder");
						return CommandResult.newOKCommandResult();
					}
						
					File fileForGeneration = new File(new java.net.URI(fileName));
					
					Runtime rt = Runtime.getRuntime();
	            	 
					File dir = new File(activityFile.getParent());
					Process pr;
					if(System.getProperty("os.name").equals("Linux")){
						JOptionPane.showMessageDialog(null, StateChartApplication.generator+" "+ fileForGeneration.getAbsolutePath()+ " "+ dir.getAbsolutePath());
						pr = rt.exec(StateChartApplication.generator+" "+ fileForGeneration.getAbsolutePath()+ " "+ dir.getAbsolutePath());
					}
					else{
						JOptionPane.showMessageDialog(null, StateChartApplication.generator+" "+ fileForGeneration.getAbsolutePath()+ " "+ dir.getAbsolutePath());
						String[] command=  {StateChartApplication.generator+" \""+ fileForGeneration.getAbsolutePath()+ "\" \""+ dir.getAbsolutePath()+"\""};
						pr = rt.exec(command);
					}
					//Process pr = rt.exec("java -jar /home/angelica/Desktop/generator.jar "+ fileForGeneration.getAbsolutePath()+ " "+ dir.getAbsolutePath());
					BufferedReader input = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		            	  
	                String line=null;
	   
	                while((line=input.readLine()) != null) {
	                   System.out.println(line);
	                }
	   
	              } catch (Exception e) {
	            	  // TODO Auto-generated catch block
	            	  e.printStackTrace();
	            	  JOptionPane.showMessageDialog(null, "Error in Generation");
	              }
		      TransactionalEditingDomain domain = (TransactionalEditingDomain)
						AdapterFactoryEditingDomain.getEditingDomainFor(model);

					GenerationTransanctionalCommand cmd = new GenerationTransanctionalCommand(domain, model, fileName, this);
					cmd.execute(progressMonitor, info);
					this.setResult(CommandResult.newOKCommandResult());
		return CommandResult.newOKCommandResult();
	}

	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
