package statechart.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (StateChartVisualIDRegistry.getVisualID(view)) {

			case ModelEditPart.VISUAL_ID:
				return new ModelEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeNameEditPart.VISUAL_ID:
				return new NodeNameEditPart(view);

			case Node2EditPart.VISUAL_ID:
				return new Node2EditPart(view);

			case NodeName2EditPart.VISUAL_ID:
				return new NodeName2EditPart(view);

			case VariableEditPart.VISUAL_ID:
				return new VariableEditPart(view);

			case VariableNameEditPart.VISUAL_ID:
				return new VariableNameEditPart(view);

			case VariableTypeEditPart.VISUAL_ID:
				return new VariableTypeEditPart(view);

			case Node3EditPart.VISUAL_ID:
				return new Node3EditPart(view);

			case Node4EditPart.VISUAL_ID:
				return new Node4EditPart(view);

			case Node5EditPart.VISUAL_ID:
				return new Node5EditPart(view);

			case NodeName3EditPart.VISUAL_ID:
				return new NodeName3EditPart(view);

			case Node6EditPart.VISUAL_ID:
				return new Node6EditPart(view);

			case WrappingLabelEditPart.VISUAL_ID:
				return new WrappingLabelEditPart(view);

			case Node7EditPart.VISUAL_ID:
				return new Node7EditPart(view);

			case WrappingLabel2EditPart.VISUAL_ID:
				return new WrappingLabel2EditPart(view);

			case Node8EditPart.VISUAL_ID:
				return new Node8EditPart(view);

			case NodeName4EditPart.VISUAL_ID:
				return new NodeName4EditPart(view);

			case Node9EditPart.VISUAL_ID:
				return new Node9EditPart(view);

			case NodeName5EditPart.VISUAL_ID:
				return new NodeName5EditPart(view);

			case Node10EditPart.VISUAL_ID:
				return new Node10EditPart(view);

			case Node11EditPart.VISUAL_ID:
				return new Node11EditPart(view);

			case NodeName6EditPart.VISUAL_ID:
				return new NodeName6EditPart(view);

			case Node12EditPart.VISUAL_ID:
				return new Node12EditPart(view);

			case NodeNodeOrCompEditPart.VISUAL_ID:
				return new NodeNodeOrCompEditPart(view);

			case NodeNodeOrComp2EditPart.VISUAL_ID:
				return new NodeNodeOrComp2EditPart(view);

			case NodeNodeAndCompEditPart.VISUAL_ID:
				return new NodeNodeAndCompEditPart(view);

			case NodeNodeAndComp2EditPart.VISUAL_ID:
				return new NodeNodeAndComp2EditPart(view);

			case TransitionEditPart.VISUAL_ID:
				return new TransitionEditPart(view);

			case TransitionTEEditPart.VISUAL_ID:
				return new TransitionTEEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn()
						&& getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width,
							SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont())
							.getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
							SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
