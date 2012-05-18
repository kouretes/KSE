package statechart.diagram.providers;

import statechart.Node;
import statechart.StatechartPackage;
import statechart.diagram.expressions.StateChartOCLFactory;
import statechart.diagram.part.StateChartDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public void init_Node_2001(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(1,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_2002(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(9,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_2004(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(7,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_2005(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(11,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_2006(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(5,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_2007(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(3,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3001(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(3,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3002(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(5,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3003(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(1,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3004(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(7,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3005(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(9,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Node_3006(Node instance) {
		try {
			Object value_0 = StateChartOCLFactory.getExpression(11,
					StatechartPackage.eINSTANCE.getNode(), null).evaluate(
					instance);
			instance.setType((String) value_0);
		} catch (RuntimeException e) {
			StateChartDiagramEditorPlugin.getInstance().logError(
					"Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = StateChartDiagramEditorPlugin
				.getInstance().getElementInitializers();
		if (cached == null) {
			StateChartDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
