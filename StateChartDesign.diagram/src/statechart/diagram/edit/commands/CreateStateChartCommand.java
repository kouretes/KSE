/**
 * 
 */
package statechart.diagram.edit.commands;


import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import org.eclipse.jface.action.IAction;

import org.eclipse.ui.IWorkbenchWindow;

import statechart.diagram.windows.FormulaEditorWindow;


public class CreateStateChartCommand extends AbstractCommand implements
		ICommand {

	public IAction action;
	public IWorkbenchWindow window;
	/**
	 * @param label
	 */
	public CreateStateChartCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param affectedFiles
	 */
	public CreateStateChartCommand(String label, List<Object> affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	public CreateStateChartCommand(String label, IAction action, IWorkbenchWindow w){
		super(label);
		this.action = action;
		this.window = w;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		FormulaEditorWindow f = new FormulaEditorWindow(this.action, this.window);
		f.createFormulaWindow(null, null, "\n");
	
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
