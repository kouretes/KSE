package statechart.diagram.part;

import java.io.File;
import java.net.URI;
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
import statechart.diagram.edit.commands.GenerationCommand;


/**
 * @generated NOT
 */
public class GenerateAction extends WorkbenchWindowActionDelegate {
	/**
	 * @generated NOT
	 */

	public static final String HANDLED = "StateChartDesign.diagram.actions.GenerateAction";
	public static final String ID = "statechartdesign.diagram.actions.GenerateAction";
	
	public GenerateAction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @generated NOT
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
			//		System.out.println(m.eResource().getURI().path());
				File modelfile;
				try {
					modelfile = new File(new java.net.URI(m.eResource().getURI().toString()));
				
				
					GenerationCommand semanticCommand = new GenerationCommand("generation", modelfile.toURI().toString(), m);
		         
					try {
						OperationHistoryFactory.getOperationHistory().execute(
			               semanticCommand, null, null);
					
					}catch (ExecutionException e) {
				        	JOptionPane.showMessageDialog(null, e.getCause());
				    }
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getCause());
				} 
			}else
				JOptionPane.showMessageDialog(null, "Please Open One Diagram For Generation");
		}else{
			JOptionPane.showMessageDialog(null, "Please  Select One Diagram Element");
		}
		
	}
	

}
