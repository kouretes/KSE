/**
 * 
 */
package statechart.diagram.edit.commands;

import org.eclipse.swt.widgets.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;

import statechart.diagram.application.StateChartApplication;
import statechart.diagram.windows.FormulaEditorWindow;

/**
 * @author angelica
 *
 */
public class OpenFormulaCommand extends AbstractCommand implements ICommand {

	private String filename;
	private String formulaName;
	private String formula;
	private IAction action;
	private IWorkbenchWindow window;
	/**
	 * @param label
	 */
	public OpenFormulaCommand(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param affectedFiles
	 */
	public OpenFormulaCommand(String label, List affectedFiles) {
		super(label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public OpenFormulaCommand(String label, IAction action, IWorkbenchWindow w) {
		super(label);
		this.action = action;
		this.window = w;
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		// TODO Auto-generated method stub
		boolean ok = openFile();
		if(ok){
			readFile();
			FormulaEditorWindow formulaWindow = new FormulaEditorWindow(action,window);
			formulaWindow.createFormulaWindow(filename, formulaName, formula);
		}
		return CommandResult.newOKCommandResult();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doRedoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.command.AbstractCommand#doUndoWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean openFile(){
//		JFileChooser chooser = new JFileChooser();
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Formula file", "txt");
//	    chooser.setFileFilter(filter);
//	    chooser.setDialogTitle("Select formula's file");
	  //  chooser.setCurrentDirectory(StateChartDiagramEditorPlugin.workspace);
	   // int returnVal = chooser.showOpenDialog(new JFrame());
	    
	    FileDialog dialog=  new FileDialog(window.getShell(),
				SWT.OPEN);
	    dialog.setText("Select formula' s file");
	    FilenameFilter ff = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				name = "txt";
				String fdir = dir.getAbsolutePath();
				
				if(fdir.endsWith(name))
					return true;
				return false;
			}
		};
		String[] ext=  new String[1];
		ext[0] = "*.txt";
		dialog.setFilterPath(StateChartApplication.workspace.getAbsolutePath());
	    dialog.setFilterExtensions(ext);	
	    dialog.open();
	    filename = dialog.getFilterPath()+File.separator+ dialog.getFileName();
//	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//	    	filename = chooser.getSelectedFile().getAbsolutePath();
//	    	return true;
//	    }
	    if(filename.endsWith(".txt"))
	    	return true;
	    
	    return false;
	    
	}
	private void readFile(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			
			formulaName = reader.readLine();
			formula = reader.readLine();
			formula = formula+"\n";
			String temp = new String();
			temp = reader.readLine();
			while(temp!=null){
			formula = formula.concat(temp).concat("\n");
				temp = reader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
