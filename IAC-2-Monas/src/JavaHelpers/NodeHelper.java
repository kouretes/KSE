package JavaHelpers;

import java.util.*;

import IAC.Node;
import IAC.Variable;


public class NodeHelper {
	public static String NodeNameInst( String str ) {
		return "Node_"+str.replace('.', '_');
	}

	public static String ParentNodeNameInst( String str ) {
		return "Node_"+ str.substring(0, str.lastIndexOf(".") ).replace('.', '_'); 
	}
	
	public static String ActivityNameInst( String str ) {
		return "NodeActivInst_"+str.replace('.', '_'); 
	}
	
	public static String TransitionName( String str ) {
		return "TrNode_"+str.replace('.', '_');
	}
	
	public static String ActionName( String str ) {
		return "TrAction_"+str.replace('.', '_');
	}
	
	public static String ActionNameInst( String str ) {
		return "TrActionInst_"+str.replace('.', '_');
	}
	
	public static String EventName( String str ) {
		return "TrEvent_"+str.replace('.', '_');
	}
	
	public static String EventNameInst( String str ) {
		return "TrEventInst_"+str.replace('.', '_');
	}
	
	public static String ConditionName( String str ) {
		return "TrCond_"+str.replace('.', '_');
	}
	
	public static String ConditionNameInst( String str ) {
		return "TrCondInst_"+str.replace('.', '_');
	}	
	
	public static String NameFilter( String str ) {
		return str.replaceAll("_open_option_", "_op_")
				 .replaceAll("_close_option_", "")
				 .replaceAll("_open_group_", "_gr_")
				 .replaceAll("_close_group_", "")
				 .replaceAll("_sequence_", "_seq_");
	}
	
	public static String ClassName ( String str ) {
		return str; //quick fix
//		String firstLetter = str.substring(0,1);
//        String remainder   = str.substring(1); 
//        return firstLetter.toUpperCase() + remainder.toLowerCase();
	}
	
	public static Collection<Node> SortNodes( Collection<Node> c){
		LinkedList<Node> myList = new LinkedList<Node>(c);
		Collections.sort(myList, new NodeComparator() );
		return myList;
	}
	
	public static Collection<Node> ActionNodes( Collection<Node> c){ //TODO delete?

		LinkedList<Node> actionNodeList = new LinkedList<Node>(c);

		HashSet<String> actions = new HashSet<String>();

		ListIterator<Node> itr = actionNodeList.listIterator();
		while ( itr.hasNext() ) {
			String curNodeAction = ActionName(itr.next().getActivity());
			if ( curNodeAction.compareTo("null")==0 || actions.contains(curNodeAction)) {
				itr.remove();
				continue;
			}
			actions.add(curNodeAction);
		}
		return actionNodeList;
	}
	public List<Variable> ActivityVariables(Node node){
		
		return node.getVariables();
	}
	
		
}
