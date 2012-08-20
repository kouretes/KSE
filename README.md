KSE
===

* ********************************************************************************* *
*  If you are reading this file, you are ready to edit or study the KSE source code.  



You can see and edit the source code via eclipse.


Necessary applications and packages:

  -- Java 6 minimum
  
  -- Eclipse Modeling Components release Indigo or newer, linux users might need to download it
	 from the elcipse sites rather than use the synaptic package manager for installation
  
  -- EGF SDK
  
  -- Extended Editing Framework Development Tools
  
  -- Graphical Modeling Framework Eclipse SDK
  
  -- Xpand Eclipse SDK
  
  -- Xtext Eclipse SDK
  
  -- delta pack (necessary only if you want to export KSE for multiple platforms (OS) )
  

Launch eclipse and either choose as your workspace the KSE repository folder or

import these projects to your current workspace. For adding the KSE projects

to your workspace go to "File" -> "Import" from the dialog select 

"General" -> "Existing Projects into Workspace" choose "Next". Choose 

"Select root directory" and "Browse" select all the KSE folder contents 

and choose "Finish".


Summary of KSE Projects:

  -- IAC-2-Monas, the code generator project
  
  -- StateChartDesign, a project with all KSE models
  
  -- StateChartDesign.diagram, the generated diagram editor and all the additional and implemented classes
  
  -- StateChartDesign.edit 
  
  -- StateChartDesign.editor
  
  -- and StateChartDesign.tests are the generated projects for our EMF model StateChart.ecore.
  



* ********************************************************************************* *
*       Create the KSE Application Instructions!!!      

If you have edited the generator's code you need to:

  -- go to "File" -> "Export..." select "Java" -> "Runnable JAR file" choose "Next"
  
  -- Select in "Lauch Configuration" the "MainWindowApplication - IAC-2-Monas"
  
  -- it is recommended to choose as the "Export destination"  the "../KSEExtraFiles/plugins/generator.jar"
  
     if you choose to enter a different name, but you would like to use the new generator in your KSE you 
     
     will have to edit the StateChartDesign.diagram code and export again the KSE.
     
     An alternative will be to configure your KSE to use the new generator as a custom generator.
     
  -- select "Library Handling" as "Extract required libraries into generated JAR"
  
  -- it is recommended to save an ANT script
  
  -- choose "Finish".
  
  For using the new generator in your KSE application please copy the folder "../KSEExtraFiles/plugins/" and
  
  paste it in your KSE folder.
  
  
If you have edited one of the StateChartDesign's models you need to:


  -- generate again the "StateChart.gfmgen" model, for this please follow the instructions from the GMF tutorials
  
  -- in the newly generated "StateChart.gfmgen" you have to edit some values from the properties view:
  
	   -- in Gen Editor Generator statechart.diagram description
	   
		    -- Copyright text (Recommended)
		    
		    -- set Diagram File Extension as kse
		    
		    -- set Domain File Extension as stct
		    
	   -- in Gen Diagram ModelEditPart description
	   
	        -- set Live Validation UI Feedback as true
	        
	        -- set Validation Decorators as true
	        
	        -- set Validation Enabled as true
	        
	        -- set Shortcuts Decorator Provider Priority as Low
	        
	        -- set Validation Decorator Provider Priority as Low
	        
	   -- in Gen Plugin StateChart Plugin description
	   
	        -- set Printing Enabled as true
	        
  
  Since you have completed these changes you can generate the diagram code and the generated
  
  classes from the StateChartDesign.diagram project that have been changed will be updated, except
  
  the ones that have the flag "generated NOT" as comment.
  
  In order to work the KSE properly you need to edit the MANIFEST.MF file, in the plugin tab
  
  set as GlobalActionHandlerProvider for cut, copy and paste actions the custom class 
  
  "statechart.diagram.providers.StateChartActionProvider"
  
  
  
If you have edited the StateChartDesign.diagram you need to open the windows.configuration file

  -- in the overview tab select "Eclipse Product export wizard"
  
	   -- if you have delta pack installed choose "Next" select the desired platforms and choose "Finish"
	   
	   -- if you do not have delta pack installed choose "Finish"
	   
	   
  When the export is done please copy and paste the KSEExtraFiles contents in the new KSE folders.
  
  Please for any additions you have made, update the help files too.
  
  If you have changed the generator please update the existing archive in "KSEExtraFiles/plugins" folder.
  


