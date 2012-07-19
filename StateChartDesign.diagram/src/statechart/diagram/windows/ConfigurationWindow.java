package statechart.diagram.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;

import statechart.diagram.application.StateChartApplication;

public class ConfigurationWindow implements ActionListener{

	IAction action;
	IWorkbenchWindow window;
	String chosenEditor;
	String chosenGenerator;
	JTextField otherText;
	JTextField genText ;
	JFrame MainWindow;
	JFrame command;
	JTextField comm;
	public ConfigurationWindow(IAction action,IWorkbenchWindow window){
		
		this.action = action;
		this.window = window;
		this.chosenEditor = new String();
		this.chosenGenerator =  new String();
	}
	

	public void createConfigurationWindow(){
		System.out.println("CreateConfigurationWindow");
		chosenEditor = StateChartApplication.cppeditor;
		chosenGenerator = StateChartApplication.generator;
		MainWindow = new JFrame("Configure KSE...");
		
		JPanel editor = new JPanel();
		JPanel generator =  new JPanel();
		JPanel buttons = new JPanel();
		
		JLabel edit = new JLabel("Choose the editor for the activities");
		JLabel gen = new JLabel("Choose the code generator");
		JButton cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		otherText = new JTextField(20);
		genText = new JTextField(20);
		
		JRadioButton geany;
		if(StateChartApplication.cppeditor.equals("geany"))
			geany = new JRadioButton("geany", true);
		else
			geany = new JRadioButton("geany", false);
		geany.setMnemonic(KeyEvent.VK_B);
		geany.setActionCommand("geany");
		
		JRadioButton eclipse;
		if(StateChartApplication.cppeditor.equals("eclipse")||StateChartApplication.cppeditor.equals("eclipse.exe"))
			eclipse = new JRadioButton("eclipse", true);
		else
			eclipse = new JRadioButton("eclipse", false);
		eclipse.setMnemonic(KeyEvent.VK_C);
		eclipse.setActionCommand("eclipse");

		JRadioButton gedit;
		if(StateChartApplication.cppeditor.equals("gedit"))
			gedit = new JRadioButton("gedit", true);
		else
			gedit = new JRadioButton("gedit", false);
		gedit.setMnemonic(KeyEvent.VK_D);
		gedit.setActionCommand("gedit");

		JRadioButton kdevelop ;
		if(StateChartApplication.cppeditor.equals("kdevelop"))
			kdevelop = new JRadioButton("kdevelop", true);
		else
			kdevelop = new JRadioButton("kdevelop", false);
		kdevelop.setMnemonic(KeyEvent.VK_R);
		kdevelop.setActionCommand("kdevelop");
		
		JRadioButton other ;
		if(System.getProperty("os.name").equals("Linux")){
			if(!StateChartApplication.cppeditor.equals("kdevelop") && !StateChartApplication.cppeditor.equals("gedit")
					&& !StateChartApplication.cppeditor.equals("geany") && !StateChartApplication.cppeditor.equals("eclipse")){
				other = new JRadioButton("other: ", true);
				otherText.setText(StateChartApplication.cppeditor);
			}else{
				other = new JRadioButton("other: ", false);
				otherText.setText("");
			}
		}else{
			if(!StateChartApplication.cppeditor.equals("notepad.exe") && !StateChartApplication.cppeditor.equals("visual")
					&& !StateChartApplication.cppeditor.endsWith("wordpad.exe")&& !StateChartApplication.cppeditor.equals("eclipse.exe")){ 
				other = new JRadioButton("other: ", true);
			otherText.setText(StateChartApplication.cppeditor);
			}else{
				other = new JRadioButton("other: ", false);
				otherText.setText("");
				}
		}
		other.setMnemonic(KeyEvent.VK_R);
		other.setActionCommand("other");

		JRadioButton notepad;
		if(StateChartApplication.cppeditor.equals("notepad.exe"))
			notepad = new JRadioButton("Notepad", true);
		else
			notepad = new JRadioButton("Notepad", false);
		notepad.setMnemonic(KeyEvent.VK_R);
		notepad.setActionCommand("notepad");

		JRadioButton wordpad;
		if(StateChartApplication.cppeditor.endsWith("wordpad.exe")) 
			wordpad = new JRadioButton("Wordpad", true);
		else
			wordpad = new JRadioButton("Wordpad", false);
		wordpad.setMnemonic(KeyEvent.VK_R);
		wordpad.setActionCommand("wordpad");
		
		JRadioButton visual;
		if(StateChartApplication.cppeditor.equals("visual"))
			visual = new JRadioButton("Visual Studio", true);
		else
			visual = new JRadioButton("Visual Studio", false);
		visual.setMnemonic(KeyEvent.VK_R);
		visual.setActionCommand("visual");
		 //Group the radio buttons.
		
	    ButtonGroup group = new ButtonGroup();
	    if(System.getProperty("os.name").equals("Linux")){
		    group.add(gedit);
		    group.add(geany);
		    group.add(eclipse);	    
		    group.add(kdevelop);
		    group.add(other);
		    

		    geany.addActionListener(this);
		    eclipse.addActionListener(this);
		    gedit.addActionListener(this);
		    kdevelop.addActionListener(this);
		    other.addActionListener(this);
		    
		    editor.setLayout(new FlowLayout(FlowLayout.LEFT));
			editor.add(edit);
			editor.add(gedit);
			editor.add(geany);
			editor.add(eclipse);
			editor.add(kdevelop);
			editor.add(other);
			editor.add(otherText);
		  	
	    }else{ 		//An exoume windows.. pros to paron
	    	group.add(wordpad);
	    	group.add(notepad);
	    	group.add(notepad);
	    	group.add(other);
	    	

	    	wordpad.addActionListener(this);
	    	notepad.addActionListener(this);
	    	visual.addActionListener(this);
		    other.addActionListener(this);
		  	
		    editor.setLayout(new FlowLayout(FlowLayout.LEFT));
			editor.add(edit);
			editor.add(wordpad);
			editor.add(notepad);
			editor.add(visual);
			editor.add(other);
			editor.add(otherText);
	    }
	    	

	    //Register a listener for the radio buttons.
	    
		cancel.setActionCommand("CANCEL");
		OK.setActionCommand("OK");
		
		JRadioButton iac2monas;
		
	//	JRadioButton genOther = new JRadioButton("Other: ");
		
		JRadioButton genOther; 
		
		if(StateChartApplication.generator.endsWith("generator.jar")){ 
			iac2monas= new JRadioButton("Generator for Monas", true);
			genOther = new JRadioButton("Other", false);
		}else{
			iac2monas= new JRadioButton("Generator for Monas", false);
			genOther = new JRadioButton("Other", true);
		}
		iac2monas.setMnemonic(KeyEvent.VK_R);
		iac2monas.setActionCommand("iac2monas");
		genOther.setMnemonic(KeyEvent.VK_R);
		genOther.setActionCommand("genOther");
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(iac2monas);
		group2.add(genOther);
		iac2monas.addActionListener(this);
		genOther.addActionListener(this);
		generator.setLayout(new FlowLayout(FlowLayout.LEFT));
		generator.add(gen);
		generator.add(iac2monas);
		generator.add(genOther);
	//	generator.add(genText);

		/*GroupLayout g = new GroupLayout(generator);
		generator.setLayout(g);
		g.setAutoCreateGaps(true);
		g.setAutoCreateContainerGaps(true);
		
		g.setVerticalGroup(g.createSequentialGroup().addComponent(iac2monas)
				.addGroup(g.createParallelGroup().addComponent(genOther)).addComponent(genText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE));
				*/
		
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttons.add(cancel);
		buttons.add(OK);
		
		editor.setBackground(new Color(198, 198, 198));
		generator.setBackground(new Color(198, 198, 198));
		buttons.setBackground(new Color(198, 198, 198));
		MainWindow.setLayout(new BorderLayout());
		MainWindow.add(editor, BorderLayout.NORTH);
		MainWindow.add(generator, BorderLayout.CENTER);
		MainWindow.add(buttons, BorderLayout.PAGE_END);
		//MainWindow.setLocation(200, 200);
		
		cancel.addActionListener(this);
		OK.addActionListener(this);		
		
		MainWindow.pack();
		MainWindow.setVisible(true);
	}

	public void createCommandWindow(){
		command = new JFrame("Please write the neccessary command!");
		JLabel lab = new JLabel("Command : ");
		comm = new JTextField(30);
		JPanel up = new JPanel();
		JPanel down = new JPanel();
		JButton cancel = new JButton("Cancel");
		JButton OK = new JButton("OK");
		up.setLayout(new FlowLayout(FlowLayout.CENTER));
		down.setLayout(new FlowLayout(FlowLayout.CENTER));
		command.setLayout(new BorderLayout());
		up.add(lab);
		up.add(comm);
		down.add(cancel);
		down.add(OK);
	
		command.add(up, BorderLayout.NORTH);
		command.add(down, BorderLayout.SOUTH);
		cancel.setActionCommand("CANCEL_COMMAND");
		OK.setActionCommand("OK_COMMAND");
		cancel.addActionListener(this);
		OK.addActionListener(this);	
		command.pack();
		command.setLocation(250, 250);
		command.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("geany")){ 
			chosenEditor = "geany";
			return;
		}
		if(e.getActionCommand().equals("gedit")){ 
			chosenEditor = "gedit";
			return;
		}
		if(e.getActionCommand().equals("eclipse")){ 
			if(System.getProperty("os.name").equals("Linux"))
				chosenEditor = "eclipse";
			else
				chosenEditor = "eclipse.exe";
			return;
		}
		if(e.getActionCommand().equals("kdevelop")){ 
			chosenEditor = "kdevelop";
			return;
		}
		if(e.getActionCommand().equals("other")){ 
			chosenEditor = "other";
			return;
		}
		if(e.getActionCommand().equals("wordpad")){ 
			String prog = new File(new File(System.getProperty("java.home")).getParent()).getParent();
			chosenEditor = prog+File.separator+"\\Windows NT"+File.separator+"Accessories"+File.separator+"wordpad.exe";
			return;
		}
		if(e.getActionCommand().equals("notepad")){ 
			chosenEditor = "notepad.exe";
			return;
		}
		if(e.getActionCommand().equals("visual")){ 
			chosenEditor = "visual";
			return;
		}
		if(e.getActionCommand().equals("iac2monas")){
			chosenGenerator = "iac2monas";
			return;
		}
		if(e.getActionCommand().equals("genOther")){ 
			chosenGenerator = "other";
			return;
		}
		if(e.getActionCommand().equals("OK")){ 
			if(chosenEditor.equals("other")){
				if(otherText.getText()==null){
					JOptionPane.showMessageDialog(null, "Please fill the text field with the editor's execution file name.\nIf not installed please enter the execution command too.");
					return;
				}
				chosenEditor = otherText.getText();
			}
			if(chosenGenerator.equals("other")){
			//	if(genText.getText()==null){
			//		JOptionPane.showMessageDialog(null, "Please fill the text field with the execution command for your code generator.\nPlease notice that the execution will have two arguments, the model's path and the source code folder's path.");
			//		return;
			//	}
			//	chosenGenerator = genText.getText();
				
				JFileChooser generationChooser = new JFileChooser();
				int retval = generationChooser.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					MainWindow.setVisible(false);
					chosenGenerator = generationChooser.getSelectedFile().getAbsolutePath();
					int option = JOptionPane.showConfirmDialog(null, "Please write any execution command if neccessary \nif jar please add \"java -jar \" with the neccessary spaces");
					if(option==0){
						createCommandWindow();
						return;
					}else if(option==1){
						if(chosenEditor==null){
							if(System.getProperty("os.name").equals("Linux"))
								chosenEditor = "gedit";
							else
								chosenEditor = "notepad.exe";
						}
						System.out.println("Editor "+ chosenEditor + " generator "+chosenGenerator);
						StateChartApplication.writeConfiguration(chosenGenerator, chosenEditor);
						StateChartApplication.readConfiguration();
						MainWindow.setVisible(false);
						return;
					}else{
						MainWindow.setVisible(true);
						return;
					}
				}else{
					JOptionPane.showMessageDialog(null, "You haven't selected a generator!");
					MainWindow.setVisible(true);
					return;
				}
			
			}else{
				if(chosenEditor==null){
					if(System.getProperty("os.name").equals("Linux"))
						chosenEditor = "gedit";
					else
						chosenEditor = "notepad.exe";
				}
					
				if(chosenGenerator==null)
					chosenGenerator = "iac2monas";
				System.out.println("Editor "+ chosenEditor + " generator "+chosenGenerator);
				StateChartApplication.writeConfiguration(chosenGenerator, chosenEditor);
				StateChartApplication.readConfiguration();
				MainWindow.setVisible(false);
				return;
			}
		}
		if(e.getActionCommand().equals("CANCEL")){ 
			MainWindow.setVisible(false);
			return;
		}
		if(e.getActionCommand().equals("CANCEL_COMMAND")){ 
			command.setVisible(false);
			MainWindow.setVisible(true);
			return;
		}
		if(e.getActionCommand().equals("OK_COMMAND")){ 
			command.setVisible(false);
			if(chosenEditor==null){
				if(System.getProperty("os.name").equals("Linux"))
					chosenEditor = "gedit";
				else
					chosenEditor = "notepad.exe";
			}
			chosenGenerator = comm.getText()+chosenGenerator;
			System.out.println("Editor "+ chosenEditor + " generator "+chosenGenerator);
			StateChartApplication.writeConfiguration(chosenGenerator, chosenEditor);
			StateChartApplication.readConfiguration();
			return;
		}
	}
}