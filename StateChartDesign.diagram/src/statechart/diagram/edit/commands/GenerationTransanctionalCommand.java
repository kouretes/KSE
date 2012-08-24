/**
 * 
 */
package statechart.diagram.edit.commands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

import statechart.Model;
import statechart.Node;
import statechart.StatechartPackage;
import statechart.diagram.application.StateChartApplication;

/**
 * @author angelica
 *
 */
public class GenerationTransanctionalCommand extends AbstractTransactionalCommand {

	
	private String modelUrl;
	private Model model;
	private String destinationFolder;
	
	public GenerationTransanctionalCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}
	
	public GenerationTransanctionalCommand(TransactionalEditingDomain domain,
			Model model, String modelUrl,String destination, GenerationCommand cmd) {
		super(domain, cmd.getLabel(), cmd.getAffectedFiles());
		// TODO Auto-generated constructor stub
		this.model = model;
		this.modelUrl = modelUrl;
		this.destinationFolder = destination;
		if(StateChartApplication.generator.contains("plugins"))
			destinationFolder = destination+File.separator+"statecharts"+File.separator+"models"+File.separator;
		else
			destinationFolder="";
	}

	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		int i=0;
		i = StateChartApplication.modelURL.indexOf(modelUrl);
		String src = StateChartApplication.generationURL.get(i);
		try {
			java.net.URI myUri = new java.net.URI(src);
			src = myUri.getPath();
			model = searchName(model.getNodes().get(0).getChildren(), src, model);
			ResourceSet resourceSet = new ResourceSetImpl();
		
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());

			resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
					StatechartPackage.eINSTANCE);

			Resource resource = resourceSet.createResource(URI
				.createURI("http://statechart/1.0"));
			resource.getContents().add(model);
			// create output							
			File newFile = new File(new java.net.URI(modelUrl)); 
			FileOutputStream out;			
		
			out = new FileOutputStream(newFile);
			resource.save(out, null);
			out.close();
			
			File FolderDestModel = new File(destinationFolder+File.separator+model.getName()+File.separator);
			File modelFolder = new File(new File(new java.net.URI(modelUrl)).getParent());
			//System.out.println(modelFolder.getAbsolutePath());
			//System.out.println(FolderDestModel.isDirectory());
			//System.out.println(modelFolder.isDirectory());
			if(destinationFolder!=""){
				//System.out.println("Folder "+modelFolder);
				String[] FilesToCopy = modelFolder.list();
				
				if(FolderDestModel.exists() && !modelFolder.equals(FolderDestModel)){
					removeDirectory(FolderDestModel);
				}
				FolderDestModel = new File(destinationFolder+File.separator+model.getName());
				if(!FolderDestModel.exists()){
					FolderDestModel.mkdir();
					for(int q=0; q<FilesToCopy.length; q++){
						nioBufferCopy(new File(modelFolder.getAbsolutePath()+File.separator+FilesToCopy[q]), new File(FolderDestModel+File.separator+FilesToCopy[q]));
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					
	return CommandResult.newOKCommandResult();
	
	}

	
	private Model searchName(List<Node> nodes, String src, Model model){
		
		for(int i=0; i<nodes.size(); i++){
			if(nodes.get(i).getType().equals("BASIC")){
				Node n  = nodes.get(i);
				if(new File(src+File.separator+nodes.get(i).getName()).exists())
					n.setActivity(new File(src+File.separator+nodes.get(i).getName()).toURI().toString());
				else
					n.setActivity(null);
				nodes.set(i, n);
			}
			model = searchName(nodes.get(i).getChildren(), src, model);
		}
		return model;
	}
	
	public static boolean removeDirectory(File directory) {

	  // System.out.println("removeDirectory " + directory);

	  if (directory == null)
	    return false;
	  if (!directory.exists())
	    return true;
	  if (!directory.isDirectory())
	    return false;

	  String[] list = directory.list();

	  // Some JVMs return null for File.list() when the
	  // directory is empty.
	  if (list != null) {
	    for (int i = 0; i < list.length; i++) {
	      File entry = new File(directory, list[i]);

	      //        System.out.println("\tremoving entry " + entry);

	      if (entry.isDirectory())
	      {
	        if (!removeDirectory(entry))
	          return false;
	      }
	      else
	      {
	        if (!entry.delete())
	          return false;
	      }
	    }
	  } 

	  return directory.delete();
	}
	
	
	private static void nioBufferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;

        FileInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);

            in = inStream.getChannel();
            out = outStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inStream);
            close(in);
            close(outStream);
            close(out);
        }
    }
	   private static void customBufferBufferedStreamCopy(File source, File target) {
	        InputStream fis = null;
	        OutputStream fos = null;
	        try {
	            fis = new BufferedInputStream(new FileInputStream(source));
	            fos = new BufferedOutputStream(new FileOutputStream(target));

	            byte[] buf = new byte[4096];

	            int i;
	            while ((i = fis.read(buf)) != -1) {
	                fos.write(buf, 0, i);
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            close(fis);
	            close(fos);
	        }
	    }
	 private static void close(Closeable closable) {
	        if (closable != null) {
	            try {
	                closable.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
