package statechart.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.RectangleFigure;
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
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy;
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
import statechart.diagram.edit.policies.VariableItemSemanticEditPolicy;
import statechart.diagram.part.StateChartVisualIDRegistry;
import statechart.diagram.providers.StateChartElementTypes;

/**
 * @generated
 */
public class VariableEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

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
	public VariableEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new VariableItemSemanticEditPolicy());
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
		return primaryShape = new VariableFigure();
	}

	/**
	 * @generated
	 */
	public VariableFigure getPrimaryShape() {
		return (VariableFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof VariableNameEditPart) {
			((VariableNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureVariableName());
			return true;
		}
		if (childEditPart instanceof VariableTypeEditPart) {
			((VariableTypeEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureVariableType());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof VariableNameEditPart) {
			return true;
		}
		if (childEditPart instanceof VariableTypeEditPart) {
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
				.getType(VariableNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class VariableFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private RoundedRectangle fFigureVartype;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureVariableName;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureVariableType;

		/**
		 * @generated
		 */
		public VariableFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(15),
					getMapMode().DPtoLP(15)));
			this.setLineWidth(2);
			this.setBackgroundColor(THIS_BACK);
			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(80),
					getMapMode().DPtoLP(60)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureVariableName = new WrappingLabel();
			fFigureVariableName.setText("Name");

			fFigureVariableName.setFont(FFIGUREVARIABLENAME_FONT);

			fFigureVariableName.setBorder(new MarginBorder(getMapMode().DPtoLP(
					5), getMapMode().DPtoLP(10), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(10)));

			this.add(fFigureVariableName, BorderLayout.TOP);

			fFigureVartype = new RoundedRectangle();
			fFigureVartype.setCornerDimensions(new Dimension(getMapMode()
					.DPtoLP(8), getMapMode().DPtoLP(8)));
			fFigureVartype.setBackgroundColor(FFIGUREVARTYPE_BACK);

			this.add(fFigureVartype, BorderLayout.CENTER);

			BorderLayout layoutFFigureVartype = new BorderLayout();
			fFigureVartype.setLayoutManager(layoutFFigureVartype);

			fFigureVariableType = new WrappingLabel();
			fFigureVariableType.setText("Type");

			fFigureVariableType.setFont(FFIGUREVARIABLETYPE_FONT);

			fFigureVariableType.setBorder(new MarginBorder(getMapMode().DPtoLP(
					5), getMapMode().DPtoLP(10), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(10)));

			fFigureVartype.add(fFigureVariableType, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		public RoundedRectangle getFigureVartype() {
			return fFigureVartype;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureVariableName() {
			return fFigureVariableName;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureVariableType() {
			return fFigureVariableType;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 72, 234, 126);

	/**
	 * @generated
	 */
	static final Font FFIGUREVARIABLENAME_FONT = new Font(Display.getCurrent(),
			"varName", 12, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Color FFIGUREVARTYPE_BACK = new Color(null, 72, 234, 126);

	/**
	 * @generated
	 */
	static final Font FFIGUREVARIABLETYPE_FONT = new Font(Display.getCurrent(),
			"VarType", 12, SWT.ITALIC);

}
