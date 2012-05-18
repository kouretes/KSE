package statechart.diagram.providers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramGlobalActionHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import statechart.Model;
import statechart.StatechartFactory;
import statechart.diagram.edit.commands.StateChartCopyCommand;
import statechart.diagram.edit.commands.StateChartCutCommand;
import statechart.diagram.edit.commands.StateChartPasteCommand;

public class StateChartClipboardSupportGlobalActionHandler extends
		DiagramGlobalActionHandler {

	boolean canPaste=false;
	public StateChartClipboardSupportGlobalActionHandler() {
		// TODO Auto-generated constructor stub
	}

	public ICommand getCommand(IGlobalActionContext cntxt) {
		
		IWorkbenchPart part = cntxt.getActivePart();
		
		if (!(part instanceof IDiagramWorkbenchPart)) {
			return null; 
			}
		IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;
		org.eclipse.gmf.runtime.common.core.command.ICommand command = null;
		String actionId = cntxt.getActionId();
		if (actionId.equals(GlobalActionId.DELETE)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.COPY)) {
			command = getCopyCommand(cntxt, diagramPart, false);
		} else if (actionId.equals(GlobalActionId.CUT)) {
			command = getCutCommand(cntxt, diagramPart);
		} else if (actionId.equals(GlobalActionId.OPEN)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.PASTE)) {
			command = getPasteCommand(cntxt, diagramPart);
		} else if (actionId.equals(GlobalActionId.SAVE)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.PROPERTIES)) {
			super.getCommand(cntxt);
		}
		return command;
	}
				 
	protected ICommand getCopyCommand(IGlobalActionContext cntxt,
		IDiagramWorkbenchPart diagramPart, final boolean isUndoable) {
		
		List<EObject> toCopyElements = this.getSelectedElements(cntxt.getSelection());
		List<EditPart> toCopyEditParts = this.getSelectedEditParts(cntxt.getSelection());
		StateChartCopyCommand copyCmd =  new StateChartCopyCommand("Copy",toCopyElements, toCopyEditParts);
		return copyCmd;
	}
		 
	private ICommand getPasteCommand(IGlobalActionContext cntxt,
		IDiagramWorkbenchPart diagramPart) {
		return new StateChartPasteCommand("Paste",(IGraphicalEditPart) ((StructuredSelection)cntxt.getSelection()).getFirstElement());
	}
	
	private ICommand getCutCOmmand(IGlobalActionContext cntxt,
		IDiagramWorkbenchPart diagramPart){
		List<EObject> toCutElements = this.getSelectedElements(cntxt.getSelection());
		List<EditPart> toCutEditParts = this.getSelectedEditParts(cntxt.getSelection());
		
		return new StateChartCutCommand("Cut",toCutElements, toCutEditParts, (Model) ((EditPart)toCutEditParts.get(0)).getModel());
	}
		// These are 2 utilitary methods:
	protected List<EObject> getSelectedElements(ISelection selection){
		List<EObject> results = new LinkedList<EObject>();
		if (selection==null || selection.isEmpty())
			return results;
		Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
		while (iterator.hasNext()){
			Object selectedElement = iterator.next();
			EObject element =  (EObject) ( (EditPart) selectedElement).getAdapter(EObject.class) ;
			results.add(element);
		}
		return results;
	}
		 
	private List<EditPart> getSelectedEditParts(ISelection selection){
		
		List<EditPart> results = new LinkedList<EditPart>();
		Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
		while (iterator.hasNext()){
			Object selectedElement = iterator.next();
			results.add((EditPart)selectedElement);
		}
		return results;
	}
	private Model getSelectedModel(ISelection selection){
		Model m = StatechartFactory.eINSTANCE.createModel();
		EditPart ep = (EditPart) ((StructuredSelection)selection).getFirstElement();
		EObject obj =(EObject) ep.getAdapter(EObject.class);
		while(!obj.getClass().equals(m.getClass())){
			obj = obj.eContainer();
		}
		m = (Model) obj;
		return m;
	}
	@Override
	public boolean canHandle(final IGlobalActionContext cntxt) {
		
		/* Check if the active part is a IDiagramWorkbenchPart */
		IWorkbenchPart part = cntxt.getActivePart();
		if (!(part instanceof IDiagramWorkbenchPart)) {
			return false;
		}
		
		/* Check if the selection is a structured selection */
		if (!(cntxt.getSelection() instanceof IStructuredSelection)) {
			return false;
		}
	
		/* Check the action id */
		String actionId = cntxt.getActionId();
		if (actionId.equals(GlobalActionId.COPY)) {
			return canCopy(cntxt);
		} else if (actionId.equals(GlobalActionId.CUT)) {
			return canCut(cntxt);
		} else if (actionId.equals(GlobalActionId.PASTE)) {
			return canPaste(cntxt);
		}
			return false;
		}
	
		@Override
		protected boolean canPaste(IGlobalActionContext cntxt){
			return canPaste;
		}
		@Override
		protected boolean canCopy(IGlobalActionContext cntxt){
			List<?> elements = getSelectedElements(cntxt.getSelection());
			
			if(elements.isEmpty()){
				canPaste = false;
				return false;
			}
			canPaste=true;
			return true;
		}
		@Override
		protected boolean canCut(IGlobalActionContext cntxt){
			String actionId = cntxt.getActionId();
			if (actionId.equals(GlobalActionId.CUT)) {
				ICommand command = getCommand(cntxt);
			if (command != null && command.canExecute()) {
				return canCopy(cntxt);
				}
			}
			return false;
		}
}
