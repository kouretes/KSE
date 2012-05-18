/**
 * 
 */
package statechart.diagram.edit.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import statechart.Model;


public class EditActivityCommand extends AbstractCommand implements ICommand {

	/**
	 * @param label
	 */

	private String ModelFile;
	private String State;
	private Model model;
	private EditActivityTransactionalCommand cmd;
	public EditActivityCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public EditActivityCommand(String label,String State, String ModelFile, Model model) {
		super(label);
		// TODO Auto-generated constructor stub
		this.ModelFile = ModelFile;
		this.State = State;
		this.model = model;
	}
	/**
	 * @param label
	 * @param affectedFiles
	 */
	public EditActivityCommand(String label, List<Object> affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		TransactionalEditingDomain domain = (TransactionalEditingDomain)
				AdapterFactoryEditingDomain.getEditingDomainFor(model);

			cmd = new EditActivityTransactionalCommand(domain, ModelFile, State, this);
			cmd.execute(progressMonitor, info);
			this.setResult(CommandResult.newOKCommandResult());
			return this.getCommandResult();
		
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
