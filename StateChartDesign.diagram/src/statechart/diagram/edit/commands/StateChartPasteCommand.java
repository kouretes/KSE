package statechart.diagram.edit.commands;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;


public class StateChartPasteCommand extends AbstractCommand {
	
	public EditPart targetEditPart;
	private EObject targetElement;
	private PasteTransactionalCommand pasteCommand;
	private List<EObject> newObjects;
	private List<EditPart> newEditParts;
	
	public StateChartPasteCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public StateChartPasteCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	
	public StateChartPasteCommand(String label, IGraphicalEditPart target) {
		super(label);
		this.targetEditPart = (IGraphicalEditPart) (target);
		this.targetElement = target.resolveSemanticElement();
		newObjects = new LinkedList<EObject>(EcoreUtil.copyAll(StateChartCopyCommand.getObjectsToPaste()));
		newEditParts = new LinkedList<EditPart>((StateChartCopyCommand.getEditPartsToPaste()));
	}
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		TransactionalEditingDomain domain = (TransactionalEditingDomain)
			AdapterFactoryEditingDomain.getEditingDomainFor(targetElement);

		pasteCommand = new PasteTransactionalCommand(domain, newObjects, targetElement, this, newEditParts);
		pasteCommand.execute(progressMonitor, info);
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
		pasteCommand.doUndoWithResult(progressMonitor, info);
		return null;
	}
	
	
	
	
	public void printChildrensClass(EditPart part){
		System.out.println("Child " + part.getClass().toString());
		if(part.getParent()!=null){
			System.out.println("Parent " + part.getParent().getClass().toString());
			printChildrensClass(part.getParent());
		}
	}
	
}
