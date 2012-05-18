/**
 * 
 */
package statechart.diagram.edit.commands;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import statechart.Model;
import statechart.Node;
import statechart.StatechartFactory;
import statechart.Transition;

/**
 * @author angelica
 *
 */
public class LabelingTransactionalCommand extends AbstractTransactionalCommand {

	private Model model;
	
	public LabelingTransactionalCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	
	public LabelingTransactionalCommand(TransactionalEditingDomain domain, Model modelForLabeling,StateChartLabelingCommand cmd){
		super(domain, cmd.getLabel(), cmd.getAffectedFiles());
		model = StatechartFactory.eINSTANCE.createModel();
		model = modelForLabeling;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		Node node = StatechartFactory.eINSTANCE.createNode();
		
		node = model.getNodes().get(0);
		//draft labels
		node.setLabel("0");
		draftLabels(node);
	
		printChildren(model.getNodes().get(0));
		//then label them
		labelingChildren(node);
		labelingTransitions(model);

		return null;
	}

	private Model labelingTransitions(Model m){
		Transition t = StatechartFactory.eINSTANCE.createTransition();
		for(int i=0; i<m.getTransitions().size(); i++){
			t = m.getTransitions().get(i);
			if(t.getSource().getName()!=null && t.getTarget().getName()!=null)
				t.setName(t.getSource().getName()+ "_TO_" + t.getTarget().getName());
			else
				t.setName("transition_"+i);
			m.getTransitions().set(i, t);
		}
		return m;
	}
	
	private void draftLabels(Node parent){
		for(int i=0; i<parent.getChildren().size(); i++){
			Node node = parent.getChildren().get(i);
			node.setLabel(parent.getLabel() + Integer.toString(i));
			System.out.println("Draft " + node.getLabel());
			draftLabels(node);
		}
	}
	
	private void labelingChildren(Node parent){
		System.out.println("LABELING??? " + parent.toString());
		int order = 1;
		int i=0;
		int start = -1;
		int end = -1;
		for( i=0; i<parent.getChildren().size(); i++){
			if(parent.getChildren().get(i).getType().equals("START")){
				start = i;
				if(end!=-1)
					break;
			}
			if(parent.getChildren().get(i).getType().equals("end")){
				end = i;
				if(start!=-1)
					break;
			}
		}
		if(end!=-1)
				parent.getChildren().get(end).setLabel(parent.getLabel()+"."+parent.getChildren().size());
		//search target nodes of transitions with start source
		List index = new LinkedList<Integer>();
		for(int q=0; q<model.getTransitions().size(); q++){
			if(model.getTransitions().get(q).getSource().getLabel().equals(parent.getChildren().get(start).getLabel()))
				index.add(q);
		}
		
		//set proper label for start
		
		parent.getChildren().get(start).setLabel(parent.getLabel()+"."+Integer.toString(order));
		System.out.println("LABEL " + parent.getChildren().get(start).getLabel());
		order++;
		
		i = start;
		boolean done = true;
		for(int o =0; o<parent.getChildren().size(); o++){
			if(!parent.getChildren().get(o).getLabel().contains(".")){
				done = false;
				System.out.println("false");
				break;
			}
		}
		if(done)
			return;
		List newIndex = new LinkedList<Integer>();
		List nodes = new LinkedList<Integer>();
		while(!done){
			for (int u=0; u<index.size(); u++){
				System.out.println("Index "+u);
				System.out.println("Target"+((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget().getLabel());
				if( parent.getChildren().contains(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget()) &&
						!((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget().getLabel().contains(".")){
					System.out.println("Exw target " +((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget() );
					for(int q=0; q<model.getTransitions().size(); q++){
						if(model.getTransitions().get(q).getSource().getLabel().equals(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget().getLabel())){
							newIndex.add(q);
							System.out.println("to target exei target " + model.getTransitions().get(q).getTarget());
							nodes.add(parent.getChildren().indexOf(model.getTransitions().get(q).getTarget()));
						}
					}
					parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).setLabel(parent.getLabel()+"."+Integer.toString(order));
					if(parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).getName()==null ||
							!parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).getType().equals("BASIC"))
						parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).setName(parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).getLabel());
							
							
				//	nodes.add(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u)).getTarget())));
					System.out.println("LABEL " + parent.getChildren().get(parent.getChildren().indexOf(((Transition)model.getTransitions().get((Integer)index.get(u))).getTarget())).getLabel());
					order++;
				
				}
			}
			done = true;
			for(int o =0; o<parent.getChildren().size(); o++){
				if(!parent.getChildren().get(o).getLabel().contains(".")){
					done = false;
					System.out.println("false");
					break;
				}
			}
			index = newIndex;
			if(!nodes.isEmpty()){
				i = (Integer) nodes.remove(0);
				System.out.println(i);
			}else
				break;
		}
		
		for(int k =0;  k<parent.getChildren().size(); k++){
			System.out.println("Kalw "+parent.getChildren().get(k).toString());
			if(!parent.getChildren().get(k).getChildren().isEmpty())
			labelingChildren(parent.getChildren().get(k));
		}
	}
	
	public void printChildren(Node node){
		for(int i=0; i<node.getChildren().size(); i++){
			System.out.println("Model " + node.getChildren().get(i).getLabel());
			printChildren(node.getChildren().get(i));
		}
	}
}