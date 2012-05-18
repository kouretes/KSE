package statechart.diagram.part;

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;

import statechart.diagram.windows.ConfigurationWindow;


public class Configure extends WorkbenchWindowActionDelegate {

	public Configure() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		ConfigurationWindow conf = new ConfigurationWindow(action, this.getWindow());
		conf.createConfigurationWindow();
	}

}
