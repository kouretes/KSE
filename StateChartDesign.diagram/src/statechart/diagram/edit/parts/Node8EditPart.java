package statechart.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import statechart.diagram.edit.policies.Node8ItemSemanticEditPolicy;
import statechart.diagram.part.StateChartVisualIDRegistry;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class Node8EditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3002;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public Node8EditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new Node8ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new NodeBasicFigure();
	}

	/**
	 * @generated
	 */
	public NodeBasicFigure getPrimaryShape() {
		return (NodeBasicFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeName4EditPart) {
			((NodeName4EditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureNodeBasicName());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof NodeName4EditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(StateChartVisualIDRegistry
				.getType(NodeName4EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StateChartElementTypes.Transition_4001);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof NodeEditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node2EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node3EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node4EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node5EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node6EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node7EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof statechart.diagram.edit.parts.Node8EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node9EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node10EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node11EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		if (targetEditPart instanceof Node12EditPart) {
			types.add(StateChartElementTypes.Transition_4001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == StateChartElementTypes.Transition_4001) {
			types.add(StateChartElementTypes.Node_2001);
			types.add(StateChartElementTypes.Node_2002);
			types.add(StateChartElementTypes.Node_2004);
			types.add(StateChartElementTypes.Node_2005);
			types.add(StateChartElementTypes.Node_2006);
			types.add(StateChartElementTypes.Node_2007);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(StateChartElementTypes.Transition_4001);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == StateChartElementTypes.Transition_4001) {
			types.add(StateChartElementTypes.Node_2001);
			types.add(StateChartElementTypes.Node_2002);
			types.add(StateChartElementTypes.Node_2004);
			types.add(StateChartElementTypes.Node_2005);
			types.add(StateChartElementTypes.Node_2006);
			types.add(StateChartElementTypes.Node_2007);
			types.add(StateChartElementTypes.Node_3001);
			types.add(StateChartElementTypes.Node_3002);
			types.add(StateChartElementTypes.Node_3003);
			types.add(StateChartElementTypes.Node_3004);
			types.add(StateChartElementTypes.Node_3005);
			types.add(StateChartElementTypes.Node_3006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class NodeBasicFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureNodeBasicName;

		/**
		 * @generated
		 */
		public NodeBasicFigure() {
			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(18),
					getMapMode().DPtoLP(18)));
			this.setLineWidth(2);
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureNodeBasicName = new WrappingLabel();
			fFigureNodeBasicName.setText("name");

			fFigureNodeBasicName.setFont(FFIGURENODEBASICNAME_FONT);

			fFigureNodeBasicName.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(10), getMapMode().DPtoLP(10), getMapMode().DPtoLP(
					10), getMapMode().DPtoLP(10)));

			this.add(fFigureNodeBasicName);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureNodeBasicName() {
			return fFigureNodeBasicName;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 161, 255, 70);

	/**
	 * @generated
	 */
	static final Font FFIGURENODEBASICNAME_FONT = new Font(
			Display.getCurrent(), "TitleFont", 14, SWT.BOLD);

}
