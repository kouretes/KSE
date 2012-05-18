package statechart.diagram.providers;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.IClientSelector;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import statechart.Model;
import statechart.Node;
import statechart.StatechartPackage;
import statechart.Transition;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.part.StateChartDiagramEditorPlugin;
import statechart.diagram.part.StateChartVisualIDRegistry;

/**
 * @generated
 */
public class StateChartValidationProvider {

	/**
	 * @generated
	 */
	private static boolean constraintsActive = false;

	/**
	 * @generated
	 */
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	 * @generated
	 */
	public static void runWithConstraints(
			TransactionalEditingDomain editingDomain, Runnable operation) {
		final Runnable op = operation;
		Runnable task = new Runnable() {
			public void run() {
				try {
					constraintsActive = true;
					op.run();
				} finally {
					constraintsActive = false;
				}
			}
		};
		if (editingDomain != null) {
			try {
				editingDomain.runExclusive(task);
			} catch (Exception e) {
				StateChartDiagramEditorPlugin.getInstance().logError(
						"Validation failed", e); //$NON-NLS-1$
			}
		} else {
			task.run();
		}
	}

	/**
	 * @generated
	 */
	static boolean isInDefaultEditorContext(Object object) {
		if (shouldConstraintsBePrivate() && !constraintsActive) {
			return false;
		}
		if (object instanceof View) {
			return constraintsActive
					&& ModelEditPart.MODEL_ID.equals(StateChartVisualIDRegistry
							.getModelID((View) object));
		}
		return true;
	}

	/**
	 * @generated
	 */
	public static class DefaultCtx implements IClientSelector {

		/**
		 * @generated
		 */
		public boolean selects(Object object) {
			return isInDefaultEditorContext(object);
		}
	}

	/**
	 * @generated NOT
	 */
	// More than one root
	public static class Adapter1 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Model context = (Model) ctx.getTarget();

			if (context.getNodes().size() > 1) {
				JOptionPane.showMessageDialog(null,
						"Failure Only one root node per model");
				System.out.println("Failure Only one root node per model");
				return ctx.createFailureStatus(0);

			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// AND node's children different than OR 
	public static class Adapter2 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			if (context.getType().equals("AND")) {
				for (int i = 0; i < context.getChildren().size(); i++) {
					if (!context.getChildren().get(i).getType().equals("OR")) {
						System.out
								.println("Failure And Can Have Only Or children");
						JOptionPane.showMessageDialog(null,
								"Failure And Can Have Only Or children");
						return ctx.createFailureStatus(0);
					}
				}
			}
			return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// Transition can't have START as a target
	public static class Adapter3 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getTarget() != null
					&& context.getTarget().getType().equals("START")) {
				System.out.println("Failure start can't be target");

				//JOptionPane
				//			.showMessageDialog(
				//				null,
				//			context.getTarget().getName()
				//				+ " is a START Node so it shouldn't be target node for a transition");
				return ctx.createFailureStatus(1);
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT
	 */
	// Transition can't have END as a source
	public static class Adapter4 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getSource() != null
					&& context.getSource().getType().equals("END")) {
				System.out.println("Failure End can't be source");
				//	JOptionPane
				//		.showMessageDialog(
				//			null,
				//		context.getTarget().getName()
				//			+ " is a END Node so it shouldn't be source node for a transition");
				return ctx.createFailureStatus(1);
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT 
	 */
	// Transition can't have source and target from different father WARNING
	public static class Adapter5 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */

		public IStatus validate(IValidationContext ctx) {
			Transition context = (Transition) ctx.getTarget();
			if (context.getSource() != null && context.getTarget() != null) {
				if ((context.getSource().getFather_of() != null
						&& context.getTarget().getFather_of() != null && !context
						.getSource().getFather_of()
						.equals(context.getTarget().getFather_of()))) {

					System.out.println("Failure Different Father");
					System.out.println("Target: "
							+ context.getTarget().getName() + " Father_of: "
							+ context.getTarget().getFather_of().getName());
					System.out.println("Source: "
							+ context.getSource().getName() + " Father_of: "
							+ context.getSource().getFather_of().getName());
					//	JOptionPane
					//		.showMessageDialog(
					//			null,
					//		context.getName()
					//			+ " is a transition between two nodes of a different parent.\nIt is recommended the source and target node of a transition to have the same parent node.");
					return ctx.createFailureStatus(0);
				} else if (context.getSource().getFather_of() == null
						|| context.getTarget().getFather_of() == null) {
					System.out.println("Failure No Transition Father");
					//	JOptionPane
					//		.showMessageDialog(
					//			null,
					//		context.getName()
					//			+ " is a transition between two nodes of a different parent.\nIt is recommended the source and target node of a transition to have the same parent node.");
					return ctx.createFailureStatus(0);
				}
				return ctx.createSuccessStatus();
			} else
				return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT
	 */
	// An OR node can have only one START node
	public static class Adapter6 extends AbstractModelConstraint {

		/**
		 * @generated NOT 
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			int count = 0;
			for (int i = 0; i < context.getChildren().size(); i++) {
				if (context.getType().equals("OR")
						&& ((Node) context.getChildren().get(i)).getType()
								.equals("START")) {
					count++;
				}
			}
			if (count > 1) {
				{
					JOptionPane
							.showMessageDialog(null,
									"Failure OR Node Can Have Only one START node as a child");
				}
				return ctx.createFailureStatus();

			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// An OR node can have only one END node
	public static class Adapter7 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			Node context = (Node) ctx.getTarget();
			int count = 0;
			for (int i = 0; i < context.getChildren().size(); i++) {
				if (context.getType().equals("OR")
						&& ((Node) context.getChildren().get(i)).getType()
								.equals("END")) {
					count++;
				}
			}
			if (count > 1) {
				JOptionPane
						.showMessageDialog(null,
								"Failure OR Node Can Have Only one END node as a child");
				return ctx.createFailureStatus();
			} else
				return ctx.createSuccessStatus();
			/**
			 * LIVE VALIDATION
			 * */
		}
	}

	/**
	 * @generated NOT
	 */
	// Error in Transition's condition
	public static class Adapter8 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			final Object context = ctx.getTarget().eGet(
					StatechartPackage.eINSTANCE.getTransition_TE());
			String te = (String) context;

			if (context == null) {
				return ctx.createSuccessStatus();
			}

			if (te != null) {
				if (CheckSyntaxForCondition(te)) {
					return ctx.createSuccessStatus();
				} else {
					JOptionPane.showMessageDialog(
							null,
							ctx.getTarget().eGet(
									StatechartPackage.eINSTANCE
											.getTransition_Name())
									+ " has a syntax error in condition.");
					return ctx.createFailureStatus(0);
				}
			}
			return ctx.createSuccessStatus();

		}
	}

	/**
	 * @generated NOT
	 */
	// Error in Transition's action
	public static class Adapter9 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			final Object context = ctx.getTarget().eGet(
					StatechartPackage.eINSTANCE.getTransition_TE());
			if (context == null) {
				return Status.OK_STATUS;
			}

			String te = (String) context;
			if (te != null) {
				if (CheckSyntaxForAction(te))
					return ctx.createSuccessStatus();
				else {
					JOptionPane.showMessageDialog(
							null,
							ctx.getTarget().eGet(
									StatechartPackage.eINSTANCE
											.getTransition_Name())
									+ " has a syntax error in action.");
					return ctx.createFailureStatus(0);
				}
			}
			return ctx.createSuccessStatus();
		}
	}

	/**
	 * @generated NOT
	 */
	// Error in variable type
	public static class Adapter10 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			final Object context = ctx.getTarget().eGet(
					StatechartPackage.eINSTANCE.getVariable_Type());
			if (context == null) {
				return ctx.createFailureStatus(new Object[] { formatElement(ctx
						.getTarget()) });
			}
			String type = (String) context;
			Pattern varPattern = Pattern.compile("(\\w+?(\\.\\w+?){2})");
			Matcher varMatcher = varPattern.matcher(type);
			if (varMatcher.matches()) {
				//System.out.println("Success VarType");
				return ctx.createSuccessStatus();
			} else {
				//System.out.println("Failure VarType");
				JOptionPane.showMessageDialog(
						null,
						ctx.getTarget().eGet(
								StatechartPackage.eINSTANCE.getVariable_Name())
								+ " has a syntax error in variable type.");
				return ctx.createFailureStatus(new Object[] { formatElement(ctx
						.getTarget()) });
			}
		}
	}

	/**
	 * @generated NOT
	 */
	// Error in variable name
	public static class Adapter11 extends AbstractModelConstraint {

		/**
		 * @generated NOT
		 */
		public IStatus validate(IValidationContext ctx) {
			final Object context = ctx.getTarget().eGet(
					StatechartPackage.eINSTANCE.getVariable_Name());
			if (context == null) {
				return ctx.createFailureStatus(new Object[] { formatElement(ctx
						.getTarget()) });
			}
			String name = (String) context;
			Pattern varPattern = Pattern.compile("([A-Za-z][\\w]*?)");
			Matcher varMatcher = varPattern.matcher(name);
			if (varMatcher.matches()) {
				//System.out.println("Success VarName");
				return ctx.createSuccessStatus();
			} else {
				//System.out.println("Failure VarName");
				JOptionPane.showMessageDialog(
						null,
						ctx.getTarget().eGet(
								StatechartPackage.eINSTANCE.getVariable_Name())
								+ " has unacceptable name.");
				return ctx.createFailureStatus(new Object[] { formatElement(ctx
						.getTarget()) });
			}
		}
	}

	/**
	 * @generated
	 */
	static String formatElement(EObject object) {
		return EMFCoreUtil.getQualifiedName(object, true);
	}

	/**
	 * @generated NOT
	 */

	public static String getEventOfExpression(String expression) {
		// pattern for events
		Pattern eventPattern = Pattern
				.compile("^[\\w\\W&&[^/\\[\\]]]+(\\[[\\w\\W&&[^\\[\\]]]+\\])?(/[\\w\\W]+)?$");
		Matcher eventMatcher = eventPattern.matcher(expression);
		if (eventMatcher.find()
				&& (eventMatcher.group().length() == expression.length())) {
			StringTokenizer st = new StringTokenizer(expression, "[]/");
			return st.nextToken();
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	public static String getConditionOfExpression(String expression) {
		// pattern for conditions
		Pattern conditionPattern = Pattern
				.compile("^([\\w\\W&&[^/\\[\\]]]+)?(\\[[\\w\\W&&[^\\[\\]]]+\\])(/[\\w\\W]+)?$");
		Matcher conditionMatcher = conditionPattern.matcher(expression);
		if (conditionMatcher.find()
				&& (conditionMatcher.group().length() == expression.length())) {
			StringTokenizer st = new StringTokenizer(expression, "]");
			String condition = st.nextToken();
			condition = condition.substring(condition.indexOf("[") + 1);
			return condition;
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	public static String getActionOfExpression(String expression) {
		// pattern for actions
		Pattern actionPattern = Pattern
				.compile("^([\\w\\W&&[^/\\[\\]]]+)?(\\[[\\w\\W&&[^\\[\\]]]+\\])?(/[\\w\\W]+)$");
		Matcher actionMatcher = actionPattern.matcher(expression);
		if (actionMatcher.find()
				&& (actionMatcher.group().length() == expression.length())) {
			String action = expression
					.substring(expression.lastIndexOf("/") + 1);
			return action;
		}
		return null;
	}

	/**
	 * @generated NOT
	 */
	public static boolean CheckSyntaxForCondition(String xpr) {

		String condExp = getConditionOfExpression(xpr);

		if (condExp == null)
			return true;
		int open = 0, close = 0;
		String parenth = new String(condExp);
		while (parenth.contains("(")) {
			open++;
			if (parenth.indexOf("(") < parenth.length() - 1)
				parenth = parenth.substring(parenth.indexOf("(") + 1);
		}
		parenth = new String(condExp);
		while (parenth.contains(")")) {
			close++;
			if (parenth.indexOf(")") < parenth.length() - 1)
				parenth = parenth.substring(parenth.indexOf(")") + 1);
		}
		if (open != close)
			return false;
		String Comparator = "(([<>]=??)|([!=]=))";
		String LogicOperator = "((\\&\\&)|(\\|\\|))";
		String NotOperator = "!";
		String s = "(\\w+?)";
		String var = "((\\w+?(\\.\\w+?){2})(\\.\\w+?\\(\\w*?\\))*?)";
		String val = "(" + s + "|(\"" + s + "\"))";
		String varVal = "(" + var + "|" + val + ")";
		String args = "(((\\s*?)" + varVal + "(\\s*?))(\\,\\s*?" + varVal
				+ "\\s*?)*?)";
		String function = "((\\w+?(\\:{2})??)+?(\\(+?\\s*?)(" + args
				+ ")??(\\s*?)\\)+?)";
		String expr = "(" + function + "|" + var + ")";
		String extXpr = "(\\(*?(\\s*?)" + NotOperator + "??" + expr
				+ "\\)*?)(((\\s*?)" + Comparator + "\\s*?)((\\(*?(\\s*?)"
				+ expr + "\\)*?(\\s*?))|(\\(*?(\\s*?)" + val
				+ "\\)*?(\\s*?))))??";
		String condition = "^(" + extXpr + "){1}(((\\s*?)" + LogicOperator
				+ "(\\s*?))(" + extXpr + "))*?$";
		Pattern variablePattern = Pattern.compile(condition);

		Matcher m = variablePattern.matcher(condExp);
		//	System.out.println(condExp);
		boolean mm = m.matches();
		//	System.out.println("True or False??? " + mm);
		return mm;

	}

	/**
	 * @generated NOT
	 */

	public static boolean CheckSyntaxForAction(String xpr) {

		String ActionExp = getActionOfExpression(xpr);
		if (ActionExp == null)
			return true;
		int open = 0, close = 0;
		String parenth = new String(ActionExp);
		while (parenth.contains("(")) {
			open++;
			if (parenth.indexOf("(") < parenth.length() - 1)
				parenth = parenth.substring(parenth.indexOf("(") + 1);
		}
		parenth = new String(ActionExp);
		while (parenth.contains(")")) {
			close++;
			if (parenth.indexOf(")") < parenth.length() - 1)
				parenth = parenth.substring(parenth.indexOf(")") + 1);
		}
		if (open != close)
			return false;
		String connectiveOp = "\\;";
		String timeout = "(TimeoutAction\\.\\w+\\.\\d+)";
		String action = "((process_messages)|(publish_all)|((publish)((\\.\\w+?){3})(\\.\\w+?\\(\\w*?\\))+?))";
		String oneAction = "(" + action + "|" + timeout + ")";
		String actions = "((\\s*?)" + oneAction + "(\\s*?)(" + connectiveOp
				+ "(\\s*?)" + action + "(\\s*?))*?)";

		Pattern variablePattern = Pattern.compile(actions);

		Matcher m = variablePattern.matcher(ActionExp);
		//	System.out.println(ActionExp);
		boolean mm = m.matches();
		//System.out.println("True or False??? " + mm);
		return mm;

	}
}
