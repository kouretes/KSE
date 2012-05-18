/**
 * 
 */
package statechart.diagram.helpsupport;


import java.io.File;
import java.io.IOException;

import org.eclipse.help.IContext;
import org.eclipse.ui.help.AbstractHelpUI;

import statechart.diagram.application.StateChartApplication;

/**
 * @author angelica
 *
 */
public class StateChartHelp extends AbstractHelpUI {

	/**
	 * 
	 */
	public StateChartHelp() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.help.AbstractHelpUI#displayHelp()
	 */
	@Override
	public void displayHelp() {
		// TODO Auto-generated method stub
		try{
			String dir = new File(new File(System.getProperty("java.home")).getParent()).getParent();
			String[] browsersLinux = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
			String[] browsersWindows = {"\""+dir+File.separator+"Internet Explorer\\iexplore.exe\"", "\""+dir+File.separator+"Mozilla Firefox\\mozilla.exe\"", "\""+dir+File.separator+"Mozilla Firefox\\firefox.exe\""};
			String browser = null;
			Runtime runtime=Runtime.getRuntime();
			if(System.getProperty("os.name").equals("Linux")){
				for (int count = 0; count < browsersLinux.length && browser == null; count++)
					if (runtime.exec(new String[] {"which", browsersLinux[count]}).waitFor() == 0)
						browser = browsersLinux[count];
				if (browser == null)
					throw new Exception("Could not find web browser");
				else{
					String url = new String(StateChartApplication.appDirectory+ File.separator+"help_files"+ File.separator+"html"+ File.separator+"toc.html");
					runtime.exec(new String[] {browser, url });
				}
			}else{
				for(int u=0; u<browsersWindows.length; u++){
					browser = browsersWindows[u];
				try{
					String url = new String(StateChartApplication.appDirectory+ File.separator+"help_files"+ File.separator+"html"+ File.separator+"toc.html");
					runtime.exec(new String[] {browser, url });
					return;
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		catch(Exception x){
		System.err.println("Exception occured while invoking Browser!");
		x.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.help.AbstractHelpUI#displayContext(org.eclipse.help.IContext, int, int)
	 */
	@Override
	public void displayContext(IContext context, int x, int y) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.help.AbstractHelpUI#displayHelpResource(java.lang.String)
	 */
	@Override
	public void displayHelpResource(String href) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.help.AbstractHelpUI#isContextHelpDisplayed()
	 */
	@Override
	public boolean isContextHelpDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
