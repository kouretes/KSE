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
import statechart.Node;
import statechart.StatechartFactory;
import statechart.diagram.edit.commands.EditActivityCommand;

/**
 * @author angelica
 *
 */
public class EditActivityAction extends WorkbenchWindowActionDelegate {

	/**
	 * 
	 */
	public EditActivityAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		EditPart ep = ((EditPart)((StructuredSelection)this.getSelection()).getFirstElement());
		if(ep==null){
			JOptionPane.showMessageDialog(null, "You Have To Select A BASIC State For Edititing!");
			return;
		}
			
		EObject o = (EObject) ep.getAdapter(EObject.class);
		
		Node n = StatechartFactory.eINSTANCE.createNode();
		if(!o.getClass().equals(n.getClass())){
			JOptionPane.showMessageDialog(null, "For this action you need to select a Node of type BASIC");
			return;
		}
		if(!((Node)o).getType().equals("BASIC")){
			JOptionPane.showMessageDialog(null, "For this action you need to select a Node of type BASIC");
			return;
		}
		n = (Node)o;

		Model m = StatechartFactory.eINSTANCE.createModel();
		while(!o.getClass().equals(m.getClass()))
			o = o.eContainer();
		m = (Model) o;
		try{
			File modelfile = new File(new java.net.URI (m.eResource().getURI().toString()));
			EditActivityCommand semanticCommand = new EditActivityCommand("EditActivity", n.getName(), modelfile.toURI().toString(), m);
			try {
	            OperationHistoryFactory.getOperationHistory().execute(
	                    semanticCommand, null, null);
	        } catch (ExecutionException e) {
	        	JOptionPane.showMessageDialog(null, e.getCause());
	        } 
		}
		catch (URISyntaxException e) {
			// TODO Auto-generated catch block
        	JOptionPane.showMessageDialog(null, e.getCause());
		}
	}
}
