package statechart.diagram.edit.commands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;
import statechart.Variable;

public class StateChartCutCommand extends AbstractCommand implements ICommand {

	private List<EObject>elements;
	private List<EditPart>editparts;
	private static List<EObject> objectsToPaste;
	private Model model;
	public StateChartCutCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public StateChartCutCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	public StateChartCutCommand(String label, List<EObject> elements, List<EditPart> editparts, Model model) {
		super(label);
		this.elements = elements;
		this.editparts = editparts;
		this.model = model;
	}
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info)
	throws ExecutionException {
	
	List<EObject> myObjectsToCopy = new LinkedList<EObject> (EcoreUtil.copyAll(elements));
	
	//check if it is consistent
	List<Node> fathers = new LinkedList<Node>();
	List<Node> trNodes = new LinkedList<Node>();
	List<Node> nodes = new LinkedList<Node>();
	List<Transition> transitions = new LinkedList<Transition>();
	List<Variable> variables = new LinkedList<Variable>();
	
	for (Iterator<EObject> it = myObjectsToCopy.iterator(); it.hasNext();){
		EObject cur = it.next();
		Node node =  StatechartFactory.eINSTANCE.createNode();
		Transition tr = StatechartFactory.eINSTANCE.createTransition();
		Variable var = StatechartFactory.eINSTANCE.createVariable();
		
		if(cur.getClass().equals( node.getClass())){
			node = (Node)cur;
			System.out.println("Node Name: "+node.getName());
			if(!nodes.contains(node.getFather_of()) && !myObjectsToCopy.contains(node.getFather_of()) && node.getFather_of()!=null){
				System.out.println(node.getFather_of().getLabel());
				nodes.add(node.getFather_of());
			}
			
		}else if(cur.getClass().equals( tr.getClass())){
			tr = (Transition)cur;
			System.out.println("Transition Name: "+tr.getName());
			if( !transitions.contains(tr) )
				transitions.add(tr);
			
		}else if(cur.getClass().equals( var.getClass())){
			var = (Variable)cur;
			System.out.println("Variable Name: "+var.getName());
			
			if( !variables.contains(var))
				variables.add(var);
		}
	}

	for(int i=0; i<nodes.size(); i++){
		if(nodes.get(i).getFather_of()!=null)
			if(((!fathers.isEmpty() && !fathers.contains(nodes.get(i).getFather_of())) || fathers.isEmpty() )
					&& !nodes.isEmpty() && !nodes.contains(nodes.get(i).getFather_of()))
				fathers.add(nodes.get(i).getFather_of());
	}
	
	for(int i=0; i<transitions.size(); i++){
		if(((!trNodes.isEmpty() && !trNodes.contains(transitions.get(i).getSource())) || trNodes.isEmpty())
			&& !nodes.isEmpty() && !nodes.contains(transitions.get(i).getSource()))
			trNodes.add(transitions.get(i).getSource());
		if(((!trNodes.isEmpty() && !trNodes.contains(transitions.get(i).getTarget())) || trNodes.isEmpty())
			&& !nodes.isEmpty() && !nodes.contains(transitions.get(i).getTarget()))
			trNodes.add(transitions.get(i).getTarget());
	}
	
	//remove dependencies
	for(Iterator<Node> it = fathers.iterator(); it.hasNext();){
		Node node = it.next();
		if(!nodes.isEmpty() && !nodes.contains(node)){
			nodes = deleteFatherFromNode(node, nodes);
		}
	}
	
	for(Iterator<Node> it = trNodes.iterator(); it.hasNext();){
		Node node = it.next();
		if(!nodes.isEmpty() && !nodes.contains(node)){
			transitions = deleteTransitionFromList(node, transitions);
		}
	}
	objectsToPaste = new LinkedList<EObject>();
	if(!nodes.isEmpty()){
		objectsToPaste.addAll(nodes);
		Node root = model.getNodes().get(0);
		//TODO recursive deletion of nodes' children
	}
	if(!transitions.isEmpty()){
		objectsToPaste.addAll(transitions);
		model.getTransitions().removeAll(transitions);
	}
	if(!variables.isEmpty()){
		objectsToPaste.addAll(variables);
		model.getVariables().removeAll(variables);
	}
	this.setResult(CommandResult.newOKCommandResult());
	
	return this.getCommandResult();
}

private List<Node> deleteFatherFromNode(Node father, List<Node> list){

	for (int i=0; i<list.size(); i++){
		Node n = (Node)list.get(i);
		if(n.getFather_of().equals(father)){
			n.setFather_of(null);
			list.set(i, n);
		}
	}
	return list;
}
public static List<EObject> getObjectsToPaste(){
	return objectsToPaste;
}
private List<Transition> deleteTransitionFromList(Node node, List<Transition> list){
	Transition t = StatechartFactory.eINSTANCE.createTransition();
	List<Transition> localList = list;
	for (int i=0; i<list.size(); i++){
		t = (Transition)list.get(i);
		if(t.getSource().equals(node) || t.getTarget().equals(node)){
			localList.remove(t);
		}

	}
	return localList;
}


	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
