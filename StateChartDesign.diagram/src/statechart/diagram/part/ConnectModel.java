/**
 * 
 */
package statechart.diagram.part;


import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;

import statechart.Model;
import statechart.StatechartFactory;
import statechart.diagram.edit.commands.ConnectModelCommand;

/**
 * @author angelica
 *
 */
public class ConnectModel extends WorkbenchWindowActionDelegate {

	/**
	 * 
	 */
	public ConnectModel() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		if(this.getSelection()!=null){
			EditPart ep = (EditPart) ((StructuredSelection)this.getSelection()).getFirstElement();
			if(ep!=null){
				
				EObject obj =  (EObject) ep.getAdapter(EObject.class);
				Model m = StatechartFactory.eINSTANCE.createModel();
				while(!obj.getClass().equals(m.getClass()))
					obj = obj.eContainer();
				m = (Model) obj;
			//	if(m.eResource()!=null)
				//	System.out.println(m.eResource().getURI().path());
				try{
				File modelfile = new File(new java.net.URI(m.eResource().getURI().toString()));
				JOptionPane.showMessageDialog(null, "File "+ modelfile.toURI().toString()+", model "+ m.getName());
				ConnectModelCommand semantiCommand =  new ConnectModelCommand("Connect",modelfile.toURI().toString() , m);
				 try {
		         OperationHistoryFactory.getOperationHistory().execute(
		            		semantiCommand, null, null);
				 } catch (ExecutionException e) {
			        	e.printStackTrace();
			        	JOptionPane.showMessageDialog(null, "Execution failed. ");
			       } 
			 } catch (URISyntaxException e) {
					// TODO Auto-generated catch block
			    	   JOptionPane.showMessageDialog(null, "URI failed ");
				}
			}else
				JOptionPane.showMessageDialog(null, "Please Open One Diagram To Connect To Source Folder!");
		}else{
			JOptionPane.showMessageDialog(null, "Please Select One Diagram Element");
		}
	      
	}

}
