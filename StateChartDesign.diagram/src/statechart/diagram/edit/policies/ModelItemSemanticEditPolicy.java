package statechart.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import statechart.diagram.edit.commands.Node2CreateCommand;
import statechart.diagram.edit.commands.Node3CreateCommand;
import statechart.diagram.edit.commands.Node4CreateCommand;
import statechart.diagram.edit.commands.Node5CreateCommand;
import statechart.diagram.edit.commands.Node6CreateCommand;
import statechart.diagram.edit.commands.NodeCreateCommand;
import statechart.diagram.edit.commands.VariableCreateCommand;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class ModelItemSemanticEditPolicy extends
		StateChartBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModelItemSemanticEditPolicy() {
		super(StateChartElementTypes.Model_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StateChartElementTypes.Node_2001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (StateChartElementTypes.Node_2002 == req.getElementType()) {
			return getGEFWrapper(new Node2CreateCommand(req));
		}
		if (StateChartElementTypes.Variable_2003 == req.getElementType()) {
			return getGEFWrapper(new VariableCreateCommand(req));
		}
		if (StateChartElementTypes.Node_2004 == req.getElementType()) {
			return getGEFWrapper(new Node3CreateCommand(req));
		}
		if (StateChartElementTypes.Node_2005 == req.getElementType()) {
			return getGEFWrapper(new Node4CreateCommand(req));
		}
		if (StateChartElementTypes.Node_2006 == req.getElementType()) {
			return getGEFWrapper(new Node5CreateCommand(req));
		}
		if (StateChartElementTypes.Node_2007 == req.getElementType()) {
			return getGEFWrapper(new Node6CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
