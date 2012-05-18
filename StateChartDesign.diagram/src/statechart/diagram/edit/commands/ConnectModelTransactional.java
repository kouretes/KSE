/**
 * 
 */
package statechart.diagram.edit.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
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
import statechart.StatechartPackage;
import statechart.diagram.application.StateChartApplication;

/**
 * @author angelica
 *
 */
public class ConnectModelTransactional extends AbstractTransactionalCommand {

	/**
	 * @param domain
	 * @param label
	 * @param affectedFiles
	 */
	private String modelUrl;
	private Model model;
	
	public ConnectModelTransactional(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param domain
	 * @param label
	 * @param options
	 * @param affectedFiles
	 */
	public ConnectModelTransactional ( TransactionalEditingDomain domain , Model model , String modelUrl, ConnectModelCommand cmd){
		super(domain, cmd.getLabel(), cmd.getAffectedFiles());
		this.model = model;
		this.modelUrl =  modelUrl;
		
	}
	public ConnectModelTransactional(TransactionalEditingDomain domain,
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
		// TODO Auto-generated method stub
		JFileChooser dialog = new JFileChooser();
		
		dialog.setDialogTitle("Choose src folder to connect the new model(activities folder for Monas architecture)");
		dialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int retval = dialog.showOpenDialog(null);
		String src = new String();
		if (retval == JFileChooser.APPROVE_OPTION) {
			src = dialog.getSelectedFile().toURI().toString();
			if(StateChartApplication.modelURL.contains(modelUrl))
				StateChartApplication.generationURL.set(StateChartApplication.modelURL.indexOf(modelUrl), src);
			else{
				StateChartApplication.generationURL.add(src);
				StateChartApplication.modelURL.add(modelUrl);
			}
						
			/* Update repositories */
			StateChartApplication.writeRepositories();
			model = searchName(model.getNodes().get(0).getChildren(),  src, model);
			ResourceSet resourceSet = new ResourceSetImpl();
			
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
								new XMIResourceFactoryImpl());

			resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
						StatechartPackage.eINSTANCE);

			Resource resource = resourceSet.createResource(URI
					.createURI("http://statechart/1.0"));
			resource.getContents().add(model);
				// create output	
			try {
			File newFile = new File(new java.net.URI(modelUrl)); 
			FileOutputStream out;			
			
				out = new FileOutputStream(newFile);
				resource.save(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Configuration File not found!");
				return CommandResult.newOKCommandResult();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Input/Output Exception!");
				return CommandResult.newOKCommandResult();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "URI Exception!");
			}
		}

		return CommandResult.newOKCommandResult();
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
