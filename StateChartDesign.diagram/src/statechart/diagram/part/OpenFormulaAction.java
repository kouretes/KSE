/**
 * 
 */
package statechart.diagram.part;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import statechart.diagram.edit.commands.OpenFormulaCommand;


/**
 * @author angelica
 *
 */
/**
 * @generated NOT
 */
public class OpenFormulaAction extends WorkbenchWindowActionDelegate{


	IWorkbenchWindow window;
	/**
	 * @generated NOT
	 * */	
	public static final String ID = "StateChartDesign.diagram.actions.OpenFormulaAction";
	/**
	 * @generated NOT
	 * */	
	public OpenFormulaAction() {
		// TODO Auto-generated constructor stub
		window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}
	/**
	 * @generated NOT
	 * */	
	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		
		OpenFormulaCommand semanticCommand = new OpenFormulaCommand("createstatechart",action, window);

		try {
		    OperationHistoryFactory.getOperationHistory().execute(
		            semanticCommand, null, null);
		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(null, "An error occured!!!");
		}
	}
	/**
	 * @generated NOT
	 * */	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}/**
	 * @generated NOT
	 * */	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		window = null;
	}/**
	 * @generated NOT
	 * */	
	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

}
