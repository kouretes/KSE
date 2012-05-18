package JavaHelpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import IAC.Variable;

public class Variables {

	public static String InitVariables(List<Variable> variables){
		
		String res = "";
		Set<String> topics = new HashSet<String>();
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type = variables.get(i).getType();
			
			// topic type msg_type
			
			Matcher varMatcher = variablePattern.matcher(type);
						
			if(varMatcher.find()){
				StringTokenizer strtok = new StringTokenizer(type, ".");
				String topic = strtok.nextToken();
				if ( ! topics.contains( topic ) ){
					topics.add(topic);
					res += "\t_blk->updateSubscription(\""+topic+"\",msgentry::SUBSCRIBE_ON_TOPIC);\n";
				}
			}
		}	
		//System.out.println(res);
		return res;
	}
	
	
	public static String ReadVariables(List<Variable> variables){
		
		String res = "";
		
		// topic type msg_type
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type  = variables.get(i).getType();
			String name = variables.get(i).getName();
			Matcher varMatcher = variablePattern.matcher(type);
			
			if ( varMatcher.find() ) {
				
				String[] toks = type.split("\\.");
				
				if ( toks.length < 3 )
					continue;
								
				res += "\t"
						+name
						+ " = _blk->read" + toks[1] + "<" + toks[2] + ">"
						+ " (\"" + toks[0] + "\" );\n" ;			
			}
		}
	//	System.out.println(res);
		return res;
	}

	public static String DeclareVariables(List<Variable> variables){
		
		String res = "";
		
		// topic type msg_type
		Pattern variablePattern = Pattern.compile("\\w+\\.\\w+\\.\\w+");
		for(int i=0; i<variables.size(); i++){
			String type  = variables.get(i).getType();
			String name = variables.get(i).getName();
			Matcher varMatcher = variablePattern.matcher(type);
			
			if ( varMatcher.find() ) {
				
				String[] toks = type.split("\\.");
				
				if ( toks.length < 3 )
					continue;
								
				res += 	"\tboost::shared_ptr<const "+toks[2]+ "> " 
						+ name + ";\n";	
			}
		}
		//System.out.println(res);
		return res;
	}
}
