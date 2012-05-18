package statechart.diagram.edit.commands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;
import statechart.Variable;
import statechart.diagram.edit.commands.StateChartPasteCommand;
import statechart.diagram.edit.parts.ModelEditPart;
import statechart.diagram.edit.policies.ModelCanonicalEditPolicy;

/**
 * @generated NOT
 */

public class PasteTransactionalCommand extends AbstractTransactionalCommand {

	private EObject targetElement;
	private List<EObject>elementsToPaste;
	private StateChartPasteCommand enclosingPasteCommand;
	private List<Transition> transitions;
	private List<Node> nodes;
	private List<Variable> variables;
	private Model model;
	private EditPart targetEditPart; 
	private List<EditPart> editparts;
	
	public PasteTransactionalCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public PasteTransactionalCommand(TransactionalEditingDomain domain,
			String label, Map options, List affectedFiles) {
		super(domain, label, options, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public PasteTransactionalCommand(TransactionalEditingDomain domain, List elemToPaste,
			EObject targetElement,
			StateChartPasteCommand pasteCommand, List<EditPart> editparts){
		
		super(domain, PasteTransactionalCommand.class.getName(), getWorkspaceFiles(elemToPaste));
		this.targetElement = targetElement;
		this.elementsToPaste = elemToPaste;
		this.enclosingPasteCommand = pasteCommand;
		
		EObject ob=  targetElement.eContainer();
		Model m = StatechartFactory.eINSTANCE.createModel();
		while(!ob.getClass().equals(m.getClass())){
			ob = ob.eContainer();
		}
		this.model = (Model)ob;
		this.editparts = editparts;
		this.targetEditPart = pasteCommand.targetEditPart;
		
	
		System.out.println("Model : " + model.toString());
	}
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		// TODO Relabeling nodes and transitions, add
	
		Node root = StatechartFactory.eINSTANCE.createNode();

		root = (Node)targetElement;
		if (elementsToPaste==null){
			return this.getCommandResult();
		}
		List<EObject> myObjectsToPaste = new LinkedList<EObject> (EcoreUtil.copyAll(elementsToPaste));
		nodes = new LinkedList<Node>();
		Transition t  = StatechartFactory.eINSTANCE.createTransition();
		transitions = new LinkedList<Transition>();
		Variable v = StatechartFactory.eINSTANCE.createVariable();
		variables = new LinkedList<Variable>();
		for(int i=0; i<myObjectsToPaste.size(); i++){
			if(myObjectsToPaste.get(i).getClass().equals(root.getClass())){
				if(((Node)myObjectsToPaste.get(i)).getFather_of()==null){
					System.out.println("Attach Node: " +myObjectsToPaste.get(i).toString());
					nodes.add((Node)myObjectsToPaste.get(i));
				}
			}
			if(myObjectsToPaste.get(i).getClass().equals(t.getClass())){
				System.out.println("Transition To Paste: "+myObjectsToPaste.get(i).toString());
				transitions.add((Transition)myObjectsToPaste.get(i));
			}
			if(myObjectsToPaste.get(i).getClass().equals(v.getClass())){
				System.out.println("Variable To Paste: "+myObjectsToPaste.get(i).toString());
				variables.add((Variable)myObjectsToPaste.get(i));
			}
		}
		relabeling(root, nodes,transitions, true);
		targetEditPart.refresh();
		
		if(!transitions.isEmpty()){
			System.out.println("Source: "+ transitions.get(0).getSource().getLabel()+" Target: "+transitions.get(0).getTarget().getLabel());
			model.getTransitions().addAll(transitions);
		}
		for(int j=0; j<variables.size(); j++){
			if(!model.getVariables().contains(variables.get(j)))
				model.getVariables().add(variables.get(j));
		}
		RenderedDiagramRootEditPart rootPart = (RenderedDiagramRootEditPart) targetEditPart.getRoot();
		rootPart.refresh();
		rootPart.refreshVisuals();
		rootPart.activate();
		rootPart.refresh();
		rootPart.refreshVisuals();
		SemanticEditPolicy sep = (SemanticEditPolicy) ((EditPart) rootPart.getChildren().get(0)).getEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
		if(sep!=null)
			sep.activate();
			
		refreshChildren((EditPart) rootPart);
			
		ModelEditPart mep = new ModelEditPart(null);
		EditPart ep = targetEditPart;
		while(!ep.getClass().equals(mep.getClass()))
			ep = ep.getParent();
			
		mep = (ModelEditPart) ep;
		ModelCanonicalEditPolicy mcep = (ModelCanonicalEditPolicy) mep.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
			
		mcep.activate();
		mcep.refresh();
		mcep.refreshConnections();
		mcep.refreshSemantic();
		mcep.refresh();
		
		JOptionPane.showMessageDialog(null,"Please select one or all figures and arrange all!!!");
		return this.getCommandResult();
	}

	
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		
		if(!transitions.isEmpty()){
			model.getTransitions().removeAll(transitions);
								
		}
		if(!variables.isEmpty()){
			model.getVariables().removeAll(variables);
		}
		if(!nodes.isEmpty()){
		for(int i=0; i<nodes.size(); i++)
			DFSDelete(model.getNodes().get(0), nodes.get(i));
		}
		return null;
	}
	
	public void addContext(IUndoContext context){
		super.addContext(context);
		this.enclosingPasteCommand.addContext(context);
	}
	
	private void refreshChildren(EditPart part){
		for(int i =0; i<part.getChildren().size(); i++){
			((EditPart) part.getChildren().get(i)).refresh();
			
			SemanticEditPolicy sep=	(SemanticEditPolicy) ((EditPart)	part.getChildren().get(i)).getEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
			if(sep!=null)
				sep.activate();
			ConnectionEditPolicy cep = (ConnectionEditPolicy) ((EditPart)part.getChildren().get(i)).getEditPolicy(EditPolicyRoles.CONNECTION_LABELS_ROLE);
			if(cep!=null)
				cep.activate();
			CanonicalEditPolicy ccep = (CanonicalEditPolicy) ((EditPart)part.getChildren().get(i)).getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
			if(ccep!=null)
				ccep.refresh();
			refreshChildren((EditPart) part.getChildren().get(i));
		}
	}
	
	/**
	 * This function sets the new labels for the pasted objects!!!
	 * @param Node root
	 * @param List<Node> nodes
	 * @param List<Transition> transitions
	 * @param boolean  addAsChildren
	 */
	public void relabeling( Node root, List<Node> nodes,List<Transition>transitions, boolean addAsChildren){
	
		String rootLabel = root.getLabel();
		int rootChildren = root.getChildren().size()+1;
		for(int i=0; i<nodes.size(); i++){
			Node temp = EcoreUtil.copy(nodes.get(i));
			if(temp.getName().equals(temp.getLabel())){
				temp.setName(rootLabel+"." + Integer.toString(rootChildren+i));
			}
			temp.setLabel(rootLabel+"." + Integer.toString(rootChildren+i));
			
			if(addAsChildren)
				root.getChildren().add(temp);
			for(int j =0; j<transitions.size(); j++){
				
				if(transitions.get(j).getSource().getLabel()!=null &&
				nodes.get(i).getLabel().equals(transitions.get(j).getSource().getLabel())){
					System.out.println("Relabeling Source");
					Transition t = transitions.get(j);
					t.setSource(temp);
					if(t.getName().length()>0){
						int index =0;
						index=t.getName().indexOf("TO");
						String newName = t.getName().substring(index, t.getName().length()-1);
						t.setName(t.getSource().getLabel().concat(newName));
					}
					transitions.set(j, t);
				}	
				if(transitions.get(j).getTarget().getLabel()!=null &&
						nodes.get(i).getLabel().equals(transitions.get(j).getTarget().getLabel())){
					System.out.println("Relabeling Target");
					Transition t = transitions.get(j);
					t.setTarget(temp);
					if(t.getName().length()>0){
						int index =0;
						index= t.getName().indexOf("TO");
						String newName = t.getName().substring(0, index+2);
						t.setName(newName.concat(t.getTarget().getLabel()));
					}
					for(int e=0; e<editparts.size(); e++){
						if(editparts.get(e).getModel().equals(transitions.get(j))){
							EditPart ep = editparts.get(e);
							ep.setModel(t);
							editparts.set(e,ep);
						}
					}
					transitions.set(j, t);
					
					System.out.println("New transition: "+ t.toString());
				}	
			}
			nodes.set(i, temp);
			if(temp.getChildren().size()>0){
				List<Node> nd = new LinkedList<Node>();
				nd=(List<Node>) EcoreUtil.copyAll(temp.getChildren());
				temp.getChildren().removeAll(temp.getChildren());
				relabeling(temp, nd, transitions, true);
			}
		}
		
	}
	
	
	public void DFSDelete(Node root, Node node){
		Iterator<Node> it = root.getChildren().iterator();
		Node search = null;
		while( it.hasNext()){
			search = it.next();
			if(search.getName().equals(node.getName()) && !search.getLabel().equals(node.getLabel())){
				root.getChildren().remove(search);
			}
			else if(search.getChildren().size()>0){
				DFSDelete(search, node);
				
			}
		}

	}
	public void printChildrensClass(EditPart part){
		for(int i=0; i<part.getChildren().size(); i++){
			System.out.println("Parent " + part.toString()+" child "+part.getChildren().get(i).toString());
			if(!((EditPart)part.getChildren().get(i)).getChildren().isEmpty())
				printChildrensClass((EditPart)part.getChildren().get(i));
		}
	}
}
