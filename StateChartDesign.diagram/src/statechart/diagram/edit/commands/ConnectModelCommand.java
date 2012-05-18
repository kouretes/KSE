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


/**
 * @author angelica
 *
 */

public class ConnectModelCommand extends AbstractCommand implements ICommand {

	/**
	 * @param label
	 */
	private String modelUrl;
	private Model model;
	
	public ConnectModelCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public ConnectModelCommand(String label, String modelUrl, Model model) {
		super(label);
		this.modelUrl = modelUrl;
		this.model = model;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param label
	 * @param affectedFiles
	 */
	public ConnectModelCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		TransactionalEditingDomain domain = (TransactionalEditingDomain)
				AdapterFactoryEditingDomain.getEditingDomainFor(model);

			ConnectModelTransactional cmd = new ConnectModelTransactional(domain, model, modelUrl, this);
			cmd.execute(progressMonitor, info);
			this.setResult(CommandResult.newOKCommandResult());
			return this.getCommandResult();
						
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doRedoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doUndoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
