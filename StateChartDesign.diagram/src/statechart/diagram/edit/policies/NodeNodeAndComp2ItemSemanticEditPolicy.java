package statechart.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import statechart.diagram.edit.commands.Node11CreateCommand;
import statechart.diagram.edit.commands.Node9CreateCommand;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class NodeNodeAndComp2ItemSemanticEditPolicy extends
		StateChartBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeNodeAndComp2ItemSemanticEditPolicy() {
		super(StateChartElementTypes.Node_2002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (StateChartElementTypes.Node_3003 == req.getElementType()) {
			return getGEFWrapper(new Node9CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
