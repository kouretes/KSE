package JavaHelpers;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class TransExpr {
	
	public boolean parenthes;
	
	public static boolean HasEvent ( String trExp ) {
		if ( trExp == null)
			return false;
		String ret = getEventOfExpression(trExp);
		if ( ret == null )
			return false;
		return ret.compareTo("null")!=0;
	}
	
	public static boolean HasCondition ( String trExp ) {
		if ( trExp == null)
			return false;
		return getConditionOfExpression(trExp)!=null;
	}
	
	public static boolean HasAction ( String trExp ) {
		if ( trExp == null)
			return false;
		return getActionOfExpression(trExp)!=null;
	}
	
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
	
	
	
	//Condition............................................................
	
	
	public static String InitVarCondExpr(String expression){
		expression = expression.replace("\n", " ");
		String condExp = getConditionOfExpression(expression);
		// topic type msg_type
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+.");
		Matcher varMatcher = variablePattern.matcher(condExp);
		
		String res = "";
		Set<String> topics = new HashSet<String>();
		while ( varMatcher.find() ) {
			
			String var = varMatcher.group();

			StringTokenizer strtok = new StringTokenizer(var, ".");
			String topic = strtok.nextToken();
		
			if ( ! topics.add( topic ) )
				continue;
			res += "\t\t_blk->updateSubscription(\""+topic+"\",msgentry::SUBSCRIBE_ON_TOPIC);\n";	
		}		
		variablePattern = Pattern.compile("TimeoutCheck\\(\\s*?\\w+?\\s*?\\)");
		varMatcher = variablePattern.matcher(condExp);
		if(varMatcher.find()){
			String check = varMatcher.group();
			int indx = check.indexOf("(");
			String top = check.substring(indx+1, check.length()-1);
			if(!topics.contains(top))
				res += "\t\t_blk->updateSubscription(\""+top+"\",msgentry::SUBSCRIBE_ON_TOPIC);\n";	
		}
		
		return res;
	}
	
	
	
	public static String GetVarName(String name) {
		return "var_"+ Math.abs( name.hashCode() );
	}
	
	public static String ReadVarCondExpr(String expression ){
		expression = expression.replace("\n", " ");
		String condExp = getConditionOfExpression(expression);
		// topic type msg_type
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		Matcher varMatcher = variablePattern.matcher(condExp);
		
		String res = "";
		Set<String> variables = new HashSet<String>();
		while ( varMatcher.find() ) {
			String var = varMatcher.group();			
			String[] toks = var.split("\\.");
			
			if ( toks.length < 3 )
				continue;
			
			if ( ! variables.add( GetVarName(var) ) )
				continue;
						
			res += "\t\tboost::shared_ptr<const "+toks[2]+ "> " 
					+ GetVarName(var)
					+ " = _blk->read" + toks[1] + "<" + toks[2] + ">"
					+ " (\"" + toks[0] + "\" );\n" ;			
		}
		
		variablePattern = Pattern.compile("TimeoutCheck\\(\\s*?\\w+?\\s*?\\)");
		varMatcher = variablePattern.matcher(condExp);
		if(varMatcher.find()){
			String check = varMatcher.group();
			int indx = check.indexOf("(");
			String top = check.substring(indx+1, check.length()-1);
			
			res += "\t\tboost::shared_ptr<const TimeoutMsg > msg = _blk->readState< TimeoutMsg > (\""+top+"\");\n";
			res +="\t\t_blk->process_messages();\n";
		}
		return res;
	}
		
	public static String EvalCondExpr(String expression){
		expression = expression.replace("\n", " ");
		expression = getConditionOfExpression(expression);
		String temp = expression;

		expression = replaceMessages(expression);
		expression = replaceFunction(expression);
		expression = replaceConditionExpression(expression);
		
		if ( !temp.equals(expression) ){
			return "\t\treturn ( " + expression + " );";
		}
		return "\t\treturn false;\n";
	}
	
	
	public static String EvalCondExprLogger(String expression, String name){
		expression = expression.replace("\n", " ");
		expression = getConditionOfExpression(expression);
		String temp = new String(expression);
		expression = replaceMessages(expression);
		expression = replaceFunction(expression);
		expression = replaceConditionExpression(expression);
		
		if ( !temp.equals(expression) ){
			return "Logger::Instance().WriteMsg(\""+name+", "+ temp+"\" "+",_toString("+expression+"),  Logger::Info);\n"+
			"\t\treturn ( " + expression + " );";			
		}
		return "Logger::Instance().WriteMsg(\""+name+"\", \"false\",  Logger::Info);\n"+ "\t\treturn false;\n";
	}
	
	
	public static String replaceFunction(String expression){
		expression = expression.replace("\n", " ");
		String s = "((\\w+)|(\\d+\\.?\\d+))";
		String var = "((\\w+(\\.\\w+){2})(\\.\\w+\\(\\w*\\))*)";
		String val = "(" + s + "|(\"" + s + "\"))";
		String varVal = "(" + var + "|" + val + ")";
		String args = "(((\\s*)" + varVal + "(\\s*))(\\,\\s*" + varVal
				+ "\\s*)*)";
		String function = "((\\w+(\\:{2})?)+?(\\(+\\s*)(" + args
				+ ")(\\s*)\\)+)";
		String extXpr =  "!?"+function ;
		
		
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		Matcher varMatcher ;
		Pattern exprPattern = Pattern.compile(extXpr);
		
		Matcher expMatcher = exprPattern.matcher(expression);
		while ( expMatcher.find() ) {	
			String x = expMatcher.group();
			//JOptionPane.showMessageDialog(null, x);
		
				
				String newX=new String(x);
				varMatcher = variablePattern.matcher(x);
				while(varMatcher.find()){
					String varr = varMatcher.group();
					if(newX.contains(varr+"."))
						newX= newX.replace(varr+".",GetVarName(varr)+"->" );
					else if(newX.contains(varr))
						newX= newX.replace(varr,GetVarName(varr) );
					//JOptionPane.showMessageDialog(null, newX);
				}
				expression=expression.replace(x, newX);
		}
		
		return expression;
	}
	public static String replaceConditionExpression(String expression){
		expression = expression.replace("\n", " ");
		String Comparator = "(([<>]=?)|([!=]=))";	
		String s = "((\\w+)|(\\d+\\.?\\d+))";
		String var = "((\\w+(\\.\\w+){2})(\\.\\w+\\(\\w*\\))*)";
		String val = "(" + s + "|(\"" + s + "\"))";
		String extXpr =  "!?"+var +"(\\s*)"+"("+ Comparator +"(\\s*)"+ "("+var+"|" +val+"))?" ;
	
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		Matcher varMatcher ;
		Pattern exprPattern = Pattern.compile(extXpr);
		
		Matcher expMatcher = exprPattern.matcher(expression);
		boolean par = false;
		while ( expMatcher.find() ) {	
			String x = expMatcher.group();
			//JOptionPane.showMessageDialog(null, x);	
				String newX=new String(x);
				varMatcher = variablePattern.matcher(x);
				boolean first = true;
				while(varMatcher.find()){
					String varr = varMatcher.group();
					if(first){
						newX = "("+GetVarName(varr)+".get()!=0 && "+ newX;
						first = false;
					//	JOptionPane.showMessageDialog(null, varr);
						par = true;
					}
					newX= newX.replace(varr+".",GetVarName(varr)+"->" );
					//JOptionPane.showMessageDialog(null, newX);
				}
				if(par){
					newX = newX+")";
					par = false;
				}
				expression=expression.replace(x, newX);
		}
		
		variablePattern = Pattern.compile("TimeoutCheck\\(\\s*?\\w+?\\s*?\\)");
		varMatcher = variablePattern.matcher(expression);
		if(varMatcher.find()){
			String check = varMatcher.group();
			expression = expression.replace(check, "(msg.get()!=0 && msg->wakeup()!=\"\" && boost::posix_time::from_iso_string(msg->wakeup())<boost::posix_time::microsec_clock::local_time())" );
		}
		
		return expression;
	}
	public static String replaceMessages(String expression){
		expression = expression.replace("\n", " ");
		Pattern messagePattern =  Pattern.compile("(\\w+\\.\\w+\\.\\w+\\s*==\\s*NULL\\s*\\|\\|)|(\\|\\|\\s*\\w+\\.\\w+\\.\\w+\\s*==\\s*NULL)");
		Matcher msgMatcher = messagePattern.matcher(expression);
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		Matcher varMatcher ;

		while ( msgMatcher.find() ) {				
			String msg = msgMatcher.group();
			varMatcher = variablePattern.matcher(msg);
			if(varMatcher.find()){
				if(msg.indexOf("||")>msg.length()/2){
					expression = expression.replace(msg, GetVarName(varMatcher.group())+".get()==0 || ");
				}else
					expression = expression.replace(msg, "|| " +GetVarName(varMatcher.group())+".get()==0 ");
			}
		}
		return expression;
	}

	
	//Actions.............................................................

	public static String CreateVarActionExpr(String expression){
		expression = expression.replace("\n", " ");
		String actionExp = getActionOfExpression(expression);
		
		String res = "";
		Set<String> variables = new HashSet<String>();		
		
		StringTokenizer strtok = new StringTokenizer(actionExp, ";");
		
		while ( strtok.hasMoreTokens() ) {
			String curToken = strtok.nextToken();
				
			String[] toks = curToken.split("\\.");
			
			//TODO: add support for further dotting...
			if ( toks.length < 4 )
				continue;
			
			int i = 0;
			if ( toks[0].equalsIgnoreCase("publish"))
				i = 1;
			
			String topic = toks[i];
			String type = toks[i+1];
			String msg_type = toks[i+2];
			
			String curVar = topic + "." + type + "." + msg_type;
			
			if ( variables.add( curVar ) ) 
				res += "\t\tboost::shared_ptr<"+msg_type+ "> " 
						+ GetVarName(curVar) + "(new " + msg_type +" );\n";
		}
		
		return res;
	}
	
public static String CreateVarTimeoutActionExpr(String expression){
	expression = expression.replace("\n", " ");
		String actionExp = getActionOfExpression(expression);
		
		String res = "";
		StringTokenizer strtok = new StringTokenizer(actionExp, ";");
		boolean command = false;
		while ( strtok.hasMoreTokens() ) {
			String curToken = strtok.nextToken();
			if(curToken.startsWith("TimeoutAction")){
				String[] toks = curToken.split("\\.");	
				res += ": statechart_engine::TimeoutAction( \""+ toks[1]+ "\", " + toks[2]+ " ) { \n" ;
			}else{
				command= true;
				res+= AddCommand(curToken);
			}
		}
		if(!command)
			res+= "\t\t;\n\t }";
		else
			res+="\t}";
		return res;		
	}
	
	public static String ExecuteActionExpr(String expression){
		expression = expression.replace("\n", " ");
		String actionExp = getActionOfExpression(expression);
		
		String res = "";
		StringTokenizer strtok = new StringTokenizer(actionExp, ";");
		
		while ( strtok.hasMoreTokens() ) {
			String curToken = strtok.nextToken();
			res+= AddCommand(curToken);
		}		

		return res;
	}

	public static String AddCommand(String curToken){
		String res = "";
		if ( curToken.equals( "process_messages")) 
			res += "\n\t\t_blk->process_messages();\n";
		else if ( curToken.equals("publish_all")) 
			res += "\n\t\t_blk->publish_all();\n";
		else if(curToken.contains(".")) {
			String[] toks = curToken.split("\\.");
			if(toks.length<4)
				return res;
			int i = 0;
			if ( toks[0].equalsIgnoreCase("publish"))
				i = 1;
			
			String topic = toks[i];
			String type = toks[i+1];
			String msg_type = toks[i+2];
			
			String var = GetVarName( topic + "." + type + "." + msg_type );
				
			res+= "\n\t\t"+msg_type +"* "+var + " = new "+ msg_type+"();"; 
			
			
			String	field = toks[4];
			for (int j = 5; j < toks.length; j++)
				field += "." + toks[j];					
			res += "\n\t\t"+ var + "->" + field + ";";					
								
			res += "\n\t\t_blk->publish" + type + " ( *" +  
						var + ", \"" + topic + "\" );";	
			
		}
		return res;
	}

	public static boolean addParenthesis(String expr){
	
		Set<String> msgs = new HashSet<String>();
		
		Pattern messagePattern =  Pattern.compile("\\w+\\.\\w+\\.\\w+\\s*?==\\s*?NULL");
		Matcher msgMatcher = messagePattern.matcher(expr);
		Pattern varPattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		Matcher varMatcher =null;
		while ( msgMatcher.find() ) {				
			String msg = msgMatcher.group();
			varMatcher = varPattern.matcher(msg);
			if(varMatcher.find()){
				String var = varMatcher.group();
				msgs.add( GetVarName(var) ) ;
			}
		}
		
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		varMatcher = variablePattern.matcher(expr);
		
		while ( varMatcher.find() ) {				
			String var = varMatcher.group();
			if(!msgs.contains(GetVarName(var))){
				return true;
			}
			
		}
		
		return false;
	}
}
