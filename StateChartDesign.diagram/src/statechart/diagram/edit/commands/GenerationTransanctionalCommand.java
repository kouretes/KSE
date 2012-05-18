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
public class GenerationTransanctionalCommand extends AbstractTransactionalCommand {

	
	private String modelUrl;
	private Model model;
	
	public GenerationTransanctionalCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	
	public GenerationTransanctionalCommand(TransactionalEditingDomain domain,
			Model model, String modelUrl, GenerationCommand cmd) {
		super(domain, cmd.getLabel(), cmd.getAffectedFiles());
		// TODO Auto-generated constructor stub
		this.model = model;
		this.modelUrl = modelUrl;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		int i=0;
		i = StateChartApplication.modelURL.indexOf(modelUrl);
		String src = StateChartApplication.generationURL.get(i);
		try {
			java.net.URI myUri = new java.net.URI(src);
			src = myUri.getPath();
			model = searchName(model.getNodes().get(0).getChildren(), src, model);
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
			File newFile = new File(new java.net.URI(modelUrl)); 
			FileOutputStream out;			
		
			out = new FileOutputStream(newFile);
			resource.save(out, null);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	return CommandResult.newOKCommandResult();
	
	}

	
	private Model searchName(List<Node> nodes, String src, Model model){
		
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).getType().equals("BASIC")){
				Node n  = nodes.get(i);
				if(new File(src+File.separator+nodes.get(i).getName()).exists())
					n.setActivity(new File(src+File.separator+nodes.get(i).getName()).toURI().toString());
				else
					n.setActivity(null);
				nodes.set(i, n);
			}
			model = searchName(nodes.get(i).getChildren(), src, model);
		}
		return model;
	}
}
