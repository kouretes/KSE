package statechart.diagram.edit.commands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;

import statechart.*;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.parts.TransitionEditPart;

public class StateChartCopyCommand extends AbstractCommand {

	private List<EObject> elements;
	private List<EditPart> editparts;
	private static List<EObject> objectsToPaste;
	private static List<EditPart> ediPartsToPaste;
	private Model model;
	
	public StateChartCopyCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public StateChartCopyCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public StateChartCopyCommand(String label, List<EObject> elements, List<EditPart> editparts) {
		super(label);
		this.elements = elements;
		this.editparts = editparts;
		if(!elements.isEmpty()){
			EObject ob=  elements.get(0).eContainer();
			Model m = StatechartFactory.eINSTANCE.createModel();
			if(ob!=null){
			while(!ob.getClass().equals(m.getClass()))
				ob = ob.eContainer();
			}
			this.model = (Model)ob;
			//StateChartLabelingCommand lcmd = new StateChartLabelingCommand("labeling", model);
			//System.out.println("Model : " + model.toString());
		}
	
		RenderedDiagramRootEditPart rootpart = (RenderedDiagramRootEditPart) editparts.get(0).getRoot();
	
	
		//rootpart.refreshVisuals();
//		for(int i=0; i<rootpart.getChildren().size(); i++)
//			printChildren((EditPart) rootpart.getChildren().get(i));
//		System.out.println("-------------------------------------");
//	
//		for(int i=0; i<editparts.size(); i++){
//			printChildren(editparts.get(i));
//		}
//	System.out.println("***************************************");
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info)
		throws ExecutionException {
		
		List<EObject> myObjectsToCopy = new LinkedList<EObject> (EcoreUtil.copyAll(elements));
		//check if it is consistent
		List<Node> trNodes = new LinkedList<Node>();
		List<Node> nodes = new LinkedList<Node>();
		List<Transition> transitions = new LinkedList<Transition>();
		List<Variable> variables = new LinkedList<Variable>();
		
		for (Iterator<EObject> it = myObjectsToCopy.iterator(); it.hasNext();){
			EObject cur = it.next();
			Node node =  StatechartFactory.eINSTANCE.createNode();
			Transition tr = StatechartFactory.eINSTANCE.createTransition();
			Variable var = StatechartFactory.eINSTANCE.createVariable();
			//if your object is Node add in nodes list
			if(cur.getClass().equals( node.getClass())){
				node = (Node)cur;
			//	System.out.println("Node Name: "+node.getName());
					nodes.add(node);
			//if your object is transition add in transitions list
			}else if(cur.getClass().equals( tr.getClass())){
				tr = (Transition)cur;
				//System.out.println("Transition Name: "+tr.getName());
				transitions.add(tr);
			//if your object is variable addtransitions = deleteTransitionFromList(node, transitions); in variables list
			}else if(cur.getClass().equals( var.getClass())){
				var = (Variable)cur;
				//System.out.println("Variable Name: "+var.getName());
				variables.add(var);
			}
		}

		//create list of nodes that are source or target of a transition
		for(int i=0; i<transitions.size(); i++){
			if(((!trNodes.isEmpty() && !trNodes.contains(transitions.get(i).getSource())) || trNodes.isEmpty()))
				trNodes.add(transitions.get(i).getSource());
			if(((!trNodes.isEmpty() && !trNodes.contains(transitions.get(i).getTarget())) || trNodes.isEmpty()))
				trNodes.add(transitions.get(i).getTarget());
		}
		
		//remove dependencies
		
		for(Iterator<Node> it = trNodes.iterator(); it.hasNext();){
			Node node = it.next();
			if(!nodes.isEmpty() && !nodes.contains(node)){
				transitions = deleteTransitionFromList(node, transitions);
			}
		}

		//add transitions of children nodes!
		List<Node>allNodes = new LinkedList<Node>();
		for(int i=0; i<nodes.size(); i++){
			allNodes.add(nodes.get(i));
			allNodes = addChildren(nodes.get(i), allNodes);
		}
		EditPart parent = editparts.get(0);
		while(!parent.getParent().getClass().equals(ModelEditPart.class))
			parent = parent.getParent();
		List<EditPart> Children = parent.getChildren();
		List<TransitionEditPart> trEditParts = new LinkedList<TransitionEditPart>();
		for(int i=0; i<Children.size(); i++){
			if(Children.get(i).getClass().equals(TransitionEditPart.class))
				trEditParts.add((TransitionEditPart) Children.get(i));
		}
		for(int i=0; i<model.getTransitions().size(); i++){
			String source = model.getTransitions().get(i).getSource().getLabel();
			String target = model.getTransitions().get(i).getTarget().getLabel();
			for(int j=0; j<allNodes.size(); j++){
				if(allNodes.get(j).getLabel()==null){
					JOptionPane.showMessageDialog(null, "You can't copy nodes with no label or name.\nYou also can't" +
							"copy transitions with no name.\n" +
							"Please go to Edit->Labeling Diagram and then copy the Diagram elements!");
					return CommandResult.newErrorCommandResult("You can't copy nodes with no label or name.\nYou also can't" +
							"copy transitions with no name.\n" +
							"Please go to Edit->Labeling Diagram and then copy the Diagram elements!");
				}
				if(source.equals(allNodes.get(j).getLabel())){
					for(int q=0; q<allNodes.size(); q++){
						if(target.equals(allNodes.get(q).getLabel())){
							//System.out.println("Transitions added: " +model.getTransitions().get(i).toString());	
							transitions.add(EcoreUtil.copy(model.getTransitions().get(i)));
							break;
						}
					}
					
				}
			}
		}
		editparts.addAll(trEditParts);
		ediPartsToPaste = new LinkedList<EditPart>();
		if(!editparts.isEmpty())
			editparts.addAll(editparts);
		objectsToPaste = new LinkedList<EObject>();
		if(!nodes.isEmpty())
			objectsToPaste.addAll(nodes);
		if(!transitions.isEmpty())
			objectsToPaste.addAll(transitions);
		if(!variables.isEmpty())
			objectsToPaste.addAll(variables);
	//	for(int i=0; i<objectsToPaste.size(); i++){
	//		System.out.println(objectsToPaste.get(i).toString());
	//	}
		this.setResult(CommandResult.newOKCommandResult());
		return this.getCommandResult();
	}
	
	public static List<EObject> getObjectsToPaste(){
		return objectsToPaste;
	}
	public static List<EditPart> getEditPartsToPaste(){
		return ediPartsToPaste; 
	}
	private List<Transition> deleteTransitionFromList(Node node, List<Transition> list){
		Transition t = StatechartFactory.eINSTANCE.createTransition();
		List<Transition> localList = list;
		for (int i=0; i<list.size(); i++){
			t = (Transition)list.get(i);
			if(t.getSource().equals(node) || t.getTarget().equals(node)){
				localList.remove(t);
				//System.out.println("Delete: " +t.toString());
			}

		}
		return localList;
	}
	
	private List<Node> addChildren(Node n, List<Node> all){
	//	System.out.println("ADD NODES");
		if(!n.getChildren().isEmpty()){
			all.addAll(n.getChildren());
			for(int i=0; i<n.getChildren().size(); i++){
				all = addChildren(n.getChildren().get(i), all);
			}
		}
		return all;
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
	
	 public void printFather(EditPart part){
		 System.out.println(part.toString());
		 if(part.getParent()!=null)
			 printFather(part.getParent());
	 }
	 public void printChildren(EditPart part){
		 TransitionEditPart tpart = new TransitionEditPart(null);
		 if(part.getClass().equals(tpart.getClass())){
			 tpart = (TransitionEditPart) part;
			 System.out.println("Source "+tpart.getSource().toString());
			 System.out.println("Target "+tpart.getTarget().toString());
			 
		 }
		 for(int i =0; i<part.getChildren().size(); i++){
			 System.out.println("Parent " + part.toString() + " Child " + part.getChildren().get(i).toString());
			 if(part.getChildren().get(i).getClass().equals(tpart.getClass())){
				 tpart = (TransitionEditPart) part.getChildren().get(i);
				 System.out.println("Source "+tpart.getSource().toString());
				 System.out.println("Target "+tpart.getTarget().toString());
				 
			 }
			 if(!((EditPart)part.getChildren().get(i)).getChildren().isEmpty())
				 printChildren((EditPart)part.getChildren().get(i));
		 }
	 }
}
