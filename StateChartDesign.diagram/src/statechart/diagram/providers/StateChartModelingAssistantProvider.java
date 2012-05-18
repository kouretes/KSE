package statechart.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

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
import statechart.diagram.edit.parts.VariableEditPart;
import statechart.diagram.part.Messages;
import statechart.diagram.part.StateChartDiagramEditorPlugin;

/**
 * @generated
 */
public class StateChartModelingAssistantProvider extends
		ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof ModelEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(7);
			types.add(StateChartElementTypes.Node_2001);
			types.add(StateChartElementTypes.Node_2002);
			types.add(StateChartElementTypes.Variable_2003);
			types.add(StateChartElementTypes.Node_2004);
			types.add(StateChartElementTypes.Node_2005);
			types.add(StateChartElementTypes.Node_2006);
			types.add(StateChartElementTypes.Node_2007);
			return types;
		}
		if (editPart instanceof NodeNodeOrCompEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(6);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
			return types;
		}
		if (editPart instanceof NodeNodeOrComp2EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(6);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
			return types;
		}
		if (editPart instanceof NodeNodeAndCompEditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(StateChartElementTypes.Node_3003);
			return types;
		}
		if (editPart instanceof NodeNodeAndComp2EditPart) {
			ArrayList<IElementType> types = new ArrayList<IElementType>(1);
			types.add(StateChartElementTypes.Node_3003);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node2EditPart) {
			return ((Node2EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node3EditPart) {
			return ((Node3EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node4EditPart) {
			return ((Node4EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node5EditPart) {
			return ((Node5EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node6EditPart) {
			return ((Node6EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node7EditPart) {
			return ((Node7EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node8EditPart) {
			return ((Node8EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node9EditPart) {
			return ((Node9EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node10EditPart) {
			return ((Node10EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node11EditPart) {
			return ((Node11EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof Node12EditPart) {
			return ((Node12EditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node2EditPart) {
			return ((Node2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node3EditPart) {
			return ((Node3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node4EditPart) {
			return ((Node4EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node5EditPart) {
			return ((Node5EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node6EditPart) {
			return ((Node6EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node7EditPart) {
			return ((Node7EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node8EditPart) {
			return ((Node8EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node9EditPart) {
			return ((Node9EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node10EditPart) {
			return ((Node10EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node11EditPart) {
			return ((Node11EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Node12EditPart) {
			return ((Node12EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node2EditPart) {
			return ((Node2EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node3EditPart) {
			return ((Node3EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node4EditPart) {
			return ((Node4EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node5EditPart) {
			return ((Node5EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node6EditPart) {
			return ((Node6EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node7EditPart) {
			return ((Node7EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node8EditPart) {
			return ((Node8EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node9EditPart) {
			return ((Node9EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node10EditPart) {
			return ((Node10EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node11EditPart) {
			return ((Node11EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof Node12EditPart) {
			return ((Node12EditPart) sourceEditPart)
					.getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node2EditPart) {
			return ((Node2EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node3EditPart) {
			return ((Node3EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node4EditPart) {
			return ((Node4EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node5EditPart) {
			return ((Node5EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node6EditPart) {
			return ((Node6EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node7EditPart) {
			return ((Node7EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node8EditPart) {
			return ((Node8EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node9EditPart) {
			return ((Node9EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node10EditPart) {
			return ((Node10EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node11EditPart) {
			return ((Node11EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Node12EditPart) {
			return ((Node12EditPart) targetEditPart)
					.getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node2EditPart) {
			return ((Node2EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node3EditPart) {
			return ((Node3EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node4EditPart) {
			return ((Node4EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node5EditPart) {
			return ((Node5EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node6EditPart) {
			return ((Node6EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node7EditPart) {
			return ((Node7EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node8EditPart) {
			return ((Node8EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node9EditPart) {
			return ((Node9EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node10EditPart) {
			return ((Node10EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node11EditPart) {
			return ((Node11EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof Node12EditPart) {
			return ((Node12EditPart) sourceEditPart)
					.getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target,
			IElementType relationshipType) {
		return selectExistingElement(target,
				getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source,
			IElementType relationshipType) {
		return selectExistingElement(source,
				getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host
				.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		HashSet<EObject> elements = new HashSet<EObject>();
		for (Iterator<EObject> it = diagram.getElement().eAllContents(); it
				.hasNext();) {
			EObject element = it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements
				.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(
				element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				StateChartDiagramEditorPlugin.getInstance()
						.getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				shell, labelProvider);
		dialog.setMessage(Messages.StateChartModelingAssistantProviderMessage);
		dialog.setTitle(Messages.StateChartModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
