package statechart.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import statechart.Model;
import statechart.Node;
import statechart.StatechartPackage;
import statechart.Transition;
import statechart.Variable;
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
import statechart.diagram.edit.parts.NodeNodeAndComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeAndCompEditPart;
import statechart.diagram.edit.parts.NodeNodeOrComp2EditPart;
import statechart.diagram.edit.parts.NodeNodeOrCompEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class StateChartDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getSemanticChildren(View view) {
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000SemanticChildren(view);
		case NodeNodeOrCompEditPart.VISUAL_ID:
			return getNodeNodeOrComp_7001SemanticChildren(view);
		case NodeNodeOrComp2EditPart.VISUAL_ID:
			return getNodeNodeOrComp_7002SemanticChildren(view);
		case NodeNodeAndCompEditPart.VISUAL_ID:
			return getNodeNodeAndComp_7003SemanticChildren(view);
		case NodeNodeAndComp2EditPart.VISUAL_ID:
			return getNodeNodeAndComp_7004SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getModel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) view.getElement();
		LinkedList<StateChartNodeDescriptor> result = new LinkedList<StateChartNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNodes().iterator(); it.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NodeEditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node2EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node3EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node4EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node5EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node6EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getVariables().iterator(); it
				.hasNext();) {
			Variable childElement = (Variable) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == VariableEditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getNodeNodeOrComp_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Node modelElement = (Node) containerView.getElement();
		LinkedList<StateChartNodeDescriptor> result = new LinkedList<StateChartNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Node7EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node8EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node9EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node10EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node11EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node12EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getNodeNodeOrComp_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Node modelElement = (Node) containerView.getElement();
		LinkedList<StateChartNodeDescriptor> result = new LinkedList<StateChartNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Node7EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node8EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node9EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node10EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node11EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Node12EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getNodeNodeAndComp_7003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Node modelElement = (Node) containerView.getElement();
		LinkedList<StateChartNodeDescriptor> result = new LinkedList<StateChartNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Node9EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartNodeDescriptor> getNodeNodeAndComp_7004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Node modelElement = (Node) containerView.getElement();
		LinkedList<StateChartNodeDescriptor> result = new LinkedList<StateChartNodeDescriptor>();
		for (Iterator<?> it = modelElement.getChildren().iterator(); it
				.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = StateChartVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Node9EditPart.VISUAL_ID) {
				result.add(new StateChartNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getContainedLinks(View view) {
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000ContainedLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001ContainedLinks(view);
		case Node2EditPart.VISUAL_ID:
			return getNode_2002ContainedLinks(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2003ContainedLinks(view);
		case Node3EditPart.VISUAL_ID:
			return getNode_2004ContainedLinks(view);
		case Node4EditPart.VISUAL_ID:
			return getNode_2005ContainedLinks(view);
		case Node5EditPart.VISUAL_ID:
			return getNode_2006ContainedLinks(view);
		case Node6EditPart.VISUAL_ID:
			return getNode_2007ContainedLinks(view);
		case Node7EditPart.VISUAL_ID:
			return getNode_3001ContainedLinks(view);
		case Node8EditPart.VISUAL_ID:
			return getNode_3002ContainedLinks(view);
		case Node9EditPart.VISUAL_ID:
			return getNode_3003ContainedLinks(view);
		case Node10EditPart.VISUAL_ID:
			return getNode_3004ContainedLinks(view);
		case Node11EditPart.VISUAL_ID:
			return getNode_3005ContainedLinks(view);
		case Node12EditPart.VISUAL_ID:
			return getNode_3006ContainedLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4001ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getIncomingLinks(View view) {
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case NodeEditPart.VISUAL_ID:
			return getNode_2001IncomingLinks(view);
		case Node2EditPart.VISUAL_ID:
			return getNode_2002IncomingLinks(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2003IncomingLinks(view);
		case Node3EditPart.VISUAL_ID:
			return getNode_2004IncomingLinks(view);
		case Node4EditPart.VISUAL_ID:
			return getNode_2005IncomingLinks(view);
		case Node5EditPart.VISUAL_ID:
			return getNode_2006IncomingLinks(view);
		case Node6EditPart.VISUAL_ID:
			return getNode_2007IncomingLinks(view);
		case Node7EditPart.VISUAL_ID:
			return getNode_3001IncomingLinks(view);
		case Node8EditPart.VISUAL_ID:
			return getNode_3002IncomingLinks(view);
		case Node9EditPart.VISUAL_ID:
			return getNode_3003IncomingLinks(view);
		case Node10EditPart.VISUAL_ID:
			return getNode_3004IncomingLinks(view);
		case Node11EditPart.VISUAL_ID:
			return getNode_3005IncomingLinks(view);
		case Node12EditPart.VISUAL_ID:
			return getNode_3006IncomingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4001IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getOutgoingLinks(View view) {
		switch (StateChartVisualIDRegistry.getVisualID(view)) {
		case NodeEditPart.VISUAL_ID:
			return getNode_2001OutgoingLinks(view);
		case Node2EditPart.VISUAL_ID:
			return getNode_2002OutgoingLinks(view);
		case VariableEditPart.VISUAL_ID:
			return getVariable_2003OutgoingLinks(view);
		case Node3EditPart.VISUAL_ID:
			return getNode_2004OutgoingLinks(view);
		case Node4EditPart.VISUAL_ID:
			return getNode_2005OutgoingLinks(view);
		case Node5EditPart.VISUAL_ID:
			return getNode_2006OutgoingLinks(view);
		case Node6EditPart.VISUAL_ID:
			return getNode_2007OutgoingLinks(view);
		case Node7EditPart.VISUAL_ID:
			return getNode_3001OutgoingLinks(view);
		case Node8EditPart.VISUAL_ID:
			return getNode_3002OutgoingLinks(view);
		case Node9EditPart.VISUAL_ID:
			return getNode_3003OutgoingLinks(view);
		case Node10EditPart.VISUAL_ID:
			return getNode_3004OutgoingLinks(view);
		case Node11EditPart.VISUAL_ID:
			return getNode_3005OutgoingLinks(view);
		case Node12EditPart.VISUAL_ID:
			return getNode_3006OutgoingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_4001OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getModel_1000ContainedLinks(
			View view) {
		Model modelElement = (Model) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getVariable_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getTransition_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2001IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2002IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getVariable_2003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2004IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2005IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2006IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2007IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3001IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3002IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3003IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3004IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3005IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3006IncomingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getTransition_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2001OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2002OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getVariable_2003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2004OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2005OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2006OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_2007OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3001OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3002OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3003OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3004OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3005OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getNode_3006OutgoingLinks(
			View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Transition_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<StateChartLinkDescriptor> getTransition_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<StateChartLinkDescriptor> getContainedTypeModelFacetLinks_Transition_4001(
			Model container) {
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		for (Iterator<?> links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != StateChartVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTarget();
			Node src = link.getSource();
			result.add(new StateChartLinkDescriptor(src, dst, link,
					StateChartElementTypes.Transition_4001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StateChartLinkDescriptor> getIncomingTypeModelFacetLinks_Transition_4001(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != StatechartPackage.eINSTANCE
					.getTransition_Target()
					|| false == setting.getEObject() instanceof Transition) {
				continue;
			}
			Transition link = (Transition) setting.getEObject();
			if (TransitionEditPart.VISUAL_ID != StateChartVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getSource();
			result.add(new StateChartLinkDescriptor(src, target, link,
					StateChartElementTypes.Transition_4001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<StateChartLinkDescriptor> getOutgoingTypeModelFacetLinks_Transition_4001(
			Node source) {
		Model container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Model) {
				container = (Model) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<StateChartLinkDescriptor> result = new LinkedList<StateChartLinkDescriptor>();
		for (Iterator<?> links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != StateChartVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getTarget();
			Node src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StateChartLinkDescriptor(src, dst, link,
					StateChartElementTypes.Transition_4001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

}
