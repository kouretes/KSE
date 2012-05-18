/**
 * 
 */
package statechart.diagram.part;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;

import statechart.Model;
import statechart.StatechartFactory;
import statechart.diagram.edit.commands.StateChartLabelingCommand;

/**
 * @author angelica
 *
 */

/**
 * @generated NOT
 * */
public class LabelStatesAndTransitions extends WorkbenchWindowActionDelegate {

	/**
	 * 
	 */
	/**
	 * @generated NOT
	 * */
	public static final String HANDLED = "StateChartDesign.diagram.actions.LabelStatesAndTransitions";
	public static final String ID = "statechart.diagram.LabelStatesAndTransitions";
	private IWorkbenchWindow window;
	/**
	 * @generated NOT
	 * */
	public LabelStatesAndTransitions() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	/**
	 * @generated NOT
	 * */
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		//window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		//LabelModel c = new LabelModel();
		
		//c.execute(action, this.getSelection(),PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		 
		EditPart ep = ((EditPart)((StructuredSelection)this.getSelection()).getFirstElement());
		TransactionalEditingDomain domain =  (TransactionalEditingDomain)
				AdapterFactoryEditingDomain.getEditingDomainFor(ep);
		Model m =  StatechartFactory.eINSTANCE.createModel();
		EObject obj =(EObject) ep.getAdapter(EObject.class);
		while(!obj.getClass().equals(m.getClass())){
			obj = obj.eContainer();
		}
		m = (Model) obj;
 
        StateChartLabelingCommand semanticCommand = new StateChartLabelingCommand("labeling", m);
         
        try {
            OperationHistoryFactory.getOperationHistory().execute(
                    semanticCommand, null, null);
        } catch (ExecutionException e) {
            // smth Happend here
        }
	}
	
	/**
	 * @generated NOT
	 * */	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		window = null;
	}	

}
