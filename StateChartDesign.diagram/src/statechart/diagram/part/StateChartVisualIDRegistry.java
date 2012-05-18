package statechart.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import statechart.Model;
import statechart.Node;
import statechart.StatechartPackage;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.Node10EditPart;
import statechart.diagram.edit.parts.Node11EditPart;
import statechart.diagram.edit.parts.Node12EditPart;
import statechart.diagram.edit.parts.Node2EditPart;
import statechart.diagram.edit.parts.Node3EditPart;
import statechart.diagram.edit.parts.Node4EditPart;
import statechart.diagram.edit.parts.Node5EditPart;
import statechart.diagram.edit.parts.Node6EditPart;
import statechart.diagram.edit.parts.Node7EditPart;
import statechart.diagram.edit.parts.Node8EditPart;
import statechart.diagram.edit.parts.Node9EditPart;
import statechart.diagram.edit.parts.NodeEditPart;
import statechart.diagram.edit.parts.NodeName2EditPart;
import statechart.diagram.edit.parts.NodeName3EditPart;
import statechart.diagram.edit.parts.NodeName4EditPart;
import statechart.diagram.edit.parts.NodeName5EditPart;
import statechart.diagram.edit.parts.NodeName6EditPart;
import statechart.diagram.edit.parts.NodeNameEditPart;
import statechart.diagram.edit.parts.NodeNodeAndComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeAndCompEditPart;
import statechart.diagram.edit.parts.NodeNodeOrComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeOrCompEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;
import statechart.diagram.edit.parts.TransitionNameEditPart;
import statechart.diagram.edit.parts.TransitionTEEditPart;
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.edit.parts.VariableNameEditPart;
import statechart.diagram.edit.parts.VariableTypeEditPart;
import statechart.diagram.edit.parts.WrappingLabel2EditPart;
import statechart.diagram.edit.parts.WrappingLabel3EditPart;
import statechart.diagram.edit.parts.WrappingLabel4EditPart;
import statechart.diagram.edit.parts.WrappingLabelEditPart;
import statechart.diagram.expressions.StateChartOCLFactory;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class StateChartVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "StateChartDesign.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ModelEditPart.MODEL_ID.equals(view.getType())) {
				return ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return statechart.diagram.part.StateChartVisualIDRegistry
				.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				StateChartDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StatechartPackage.eINSTANCE.getModel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Model) domainElement)) {
			return ModelEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = statechart.diagram.part.StateChartVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = statechart.diagram.part.StateChartVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case ModelEditPart.VISUAL_ID:
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2001((Node) domainElement)) {
				return NodeEditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2002((Node) domainElement)) {
				return Node2EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getVariable().isSuperTypeOf(
					domainElement.eClass())) {
				return VariableEditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2004((Node) domainElement)) {
				return Node3EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2005((Node) domainElement)) {
				return Node4EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2006((Node) domainElement)) {
				return Node5EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_2007((Node) domainElement)) {
				return Node6EditPart.VISUAL_ID;
			}
			break;
		case NodeNodeOrCompEditPart.VISUAL_ID:
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3001((Node) domainElement)) {
				return Node7EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3002((Node) domainElement)) {
				return Node8EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3003((Node) domainElement)) {
				return Node9EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3004((Node) domainElement)) {
				return Node10EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3005((Node) domainElement)) {
				return Node11EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3006((Node) domainElement)) {
				return Node12EditPart.VISUAL_ID;
			}
			break;
		case NodeNodeOrComp2EditPart.VISUAL_ID:
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3001((Node) domainElement)) {
				return Node7EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3002((Node) domainElement)) {
				return Node8EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3003((Node) domainElement)) {
				return Node9EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3004((Node) domainElement)) {
				return Node10EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3005((Node) domainElement)) {
				return Node11EditPart.VISUAL_ID;
			}
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3006((Node) domainElement)) {
				return Node12EditPart.VISUAL_ID;
			}
			break;
		case NodeNodeAndCompEditPart.VISUAL_ID:
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3003((Node) domainElement)) {
				return Node9EditPart.VISUAL_ID;
			}
			break;
		case NodeNodeAndComp2EditPart.VISUAL_ID:
			if (StatechartPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())
					&& isNode_3003((Node) domainElement)) {
				return Node9EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = statechart.diagram.part.StateChartVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = statechart.diagram.part.StateChartVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ModelEditPart.VISUAL_ID:
			if (NodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (VariableEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeEditPart.VISUAL_ID:
			if (NodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeNodeOrCompEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node2EditPart.VISUAL_ID:
			if (NodeName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeNodeAndComp2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case VariableEditPart.VISUAL_ID:
			if (VariableNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (VariableTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node5EditPart.VISUAL_ID:
			if (NodeName3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node6EditPart.VISUAL_ID:
			if (WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node7EditPart.VISUAL_ID:
			if (WrappingLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node8EditPart.VISUAL_ID:
			if (NodeName4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node9EditPart.VISUAL_ID:
			if (NodeName5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeNodeOrComp2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Node11EditPart.VISUAL_ID:
			if (NodeName6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NodeNodeAndCompEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeNodeOrCompEditPart.VISUAL_ID:
			if (Node7EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node8EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node9EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node10EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node11EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node12EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeNodeOrComp2EditPart.VISUAL_ID:
			if (Node7EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node8EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node9EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node10EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node11EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Node12EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeNodeAndCompEditPart.VISUAL_ID:
			if (Node9EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeNodeAndComp2EditPart.VISUAL_ID:
			if (Node9EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransitionEditPart.VISUAL_ID:
			if (TransitionTEEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (StatechartPackage.eINSTANCE.getTransition().isSuperTypeOf(
				domainElement.eClass())) {
			return TransitionEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Model element) {
		return true;
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2001(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(0,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2002(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(8,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2004(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(6,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2005(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(10,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2006(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(4,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_2007(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(2,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3001(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(2,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3002(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(4,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3003(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(0,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3004(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(6,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3005(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(8,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isNode_3006(Node domainElement) {
		Object result = StateChartOCLFactory.getExpression(10,
				StatechartPackage.eINSTANCE.getNode(), null).evaluate(
				domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

}
