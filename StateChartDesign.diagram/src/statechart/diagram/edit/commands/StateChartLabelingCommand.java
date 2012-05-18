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
import statechart.StatechartFactory;

/**
 * @author angelica
 *
 */
public class StateChartLabelingCommand extends AbstractCommand implements
		ICommand {

	private Model modelForLabeling;
	private LabelingTransactionalCommand labelCommand;
	/**
	 * @param label
	 */
	public StateChartLabelingCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param affectedFiles
	 */
	public StateChartLabelingCommand(String label, Model modelForLabeling){
		super(label);
		this.modelForLabeling  = StatechartFactory.eINSTANCE.createModel();
		this.modelForLabeling = modelForLabeling;
	}
	public StateChartLabelingCommand(String label, List affectedFiles) {
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
		TransactionalEditingDomain domain = (TransactionalEditingDomain)
				AdapterFactoryEditingDomain.getEditingDomainFor(modelForLabeling);

			labelCommand = new LabelingTransactionalCommand(domain, modelForLabeling, this);
			labelCommand.execute(progressMonitor, info);
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
