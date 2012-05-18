package statechart.diagram.providers;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerProvider;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public class StateChartActionProvider extends AbstractProvider implements
		IGlobalActionHandlerProvider {

	public StateChartActionProvider() {
		// TODO Auto-generated constructor stub
	}
	private Hashtable handlerList = new Hashtable();
	@Override
	public boolean provides(IOperation operation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IGlobalActionHandler getGlobalActionHandler(
			final IGlobalActionHandlerContext context) {
		if (!getHandlerList().containsKey(context.getActivePart())) {
		getHandlerList().put(context.getActivePart(),
		new StateChartClipboardSupportGlobalActionHandler());
			 
			/*
			* Register as a part listener so that the cache can be cleared when
			* the part is disposed
			*/
			context.getActivePart().getSite().getPage().addPartListener(
			new IPartListener() {
			 
			private IWorkbenchPart localPart = context.getActivePart();
			 
			/**
			* @see org.eclipse.ui.IPartListener#partActivated(IWorkbenchPart)
			*/
			public void partActivated(IWorkbenchPart part) {
			// Do nothing
			}
			 
			/**
			* @see org.eclipse.ui.IPartListener#partBroughtToTop(IWorkbenchPart)
			*/
			public void partBroughtToTop(IWorkbenchPart part) {
			// Do nothing
			}
			 
			/**
			* @see org.eclipse.ui.IPartListener#partClosed(IWorkbenchPart)
			*/
			public void partClosed(IWorkbenchPart part) {
			/* Remove the cache associated with the part */
			if (part != null && part == localPart
			&& getHandlerList().containsKey(part)) {
			getHandlerList().remove(part);
			localPart.getSite().getPage().removePartListener(
			this);
			localPart = null;
			}
			}
			 
			/**
			* @see org.eclipse.ui.IPartListener#partDeactivated(IWorkbenchPart)
			*/
			public void partDeactivated(IWorkbenchPart part) {
			// Do nothing
			}
			 
			/**
			* @see org.eclipse.ui.IPartListener#partOpened(IWorkbenchPart)
			*/
			public void partOpened(IWorkbenchPart part) {
			// Do nothing
			}
			});
			}
			 
			return (StateChartClipboardSupportGlobalActionHandler) getHandlerList().get(
			context.getActivePart());
	}
	
	private Hashtable getHandlerList() {
		return handlerList;
	}

}
