package iac2monas;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import statechart.Node;
import statechart.StatechartFactory;
import statechart.StatechartPackage;

import IAC.IACFactory;
import IAC.IACPackage;

public class ModelConvertor {
	
	private IAC.Node IACroot;
	private statechart.Node root;
	private IAC.Model IACModel;
	private statechart.Model model;
	
	public ModelConvertor(){
		IACroot = IACFactory.eINSTANCE.createNode();
		IACModel = IACFactory.eINSTANCE.createModel();
		root = StatechartFactory.eINSTANCE.createNode();
		model = StatechartFactory.eINSTANCE.createModel();
	}
	
	public String IACToStateChart(String filename, String Path){
		String file= new String();
		IAC.Model old = (IAC.Model) IACFactory.eINSTANCE.createModel();
		ResourceSet resourceSet1 = new ResourceSetImpl();
		resourceSet1.getResourceFactoryRegistry().getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());
		
		resourceSet1.getPackageRegistry().put(IACPackage.eNS_URI,
				IACPackage.eINSTANCE);
		Resource resource = resourceSet1.createResource(URI
				.createURI(IACPackage.eNS_URI));
		try {
			File source = new File(filename);
			resource.load(new FileInputStream(source), new HashMap<Object,Object>());
			resource.load(null);
			old = (IAC.Model) resource.getContents().get(0);
			root = StatechartFactory.eINSTANCE.createNode();
			statechart.Node node = StatechartFactory.eINSTANCE.createNode();

			model  = StatechartFactory.eINSTANCE.createModel();
			
			for(Iterator<IAC.Node>  it = old.getNodes().iterator(); it.hasNext();){
				IAC.Node cur = it.next();
				node = IACNodeToStateChartNode(cur);
				if(cur.getFather_of().size()==0){
					root = node;
					model.getNodes().add(root);
				}else{
					addNodeStatechart(node, cur.getFather_of().get(0).getLabel());
				}
					
			}
			statechart.Transition item = StatechartFactory.eINSTANCE.createTransition();
			for(Iterator<IAC.Transition> it = old.getTransitions().iterator(); it.hasNext();){
				IAC.Transition cur = it.next();
				item = StatechartFactory.eINSTANCE.createTransition();
				item.setName(cur.getName());
				if(cur.getTE()!=null)
					item.setTE(cur.getTE());
				item.setSource(getNodeStateChart(cur.getSource().getLabel()));
				item.setTarget(getNodeStateChart(cur.getTarget().getLabel()));
				model.getTransitions().add(item);
			}
		
			statechart.Variable var;
			IAC.Variable vIAC = IACFactory.eINSTANCE.createVariable();
			for(Iterator<IAC.Variable> it = old.getVariables().iterator(); it.hasNext();){
				vIAC = it.next();
				var = StatechartFactory.eINSTANCE.createVariable();
				var.setName(vIAC.getName());
				var.setType(vIAC.getType());	
				model.getVariables().add(var);
			}
			
			model.setName(old.getName());
			ResourceSet resourceSet = new ResourceSetImpl();

			// Register the appropriate resource factory to handle all file
			// extensions.
			//
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			//
			resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
					StatechartPackage.eINSTANCE);

			Resource resource1 = resourceSet.createResource(URI
					.createURI(StatechartPackage.eNS_URI));
			resource1.getContents().add(model);
			
			try {
				file =Path+ File.separator+ model.getName() +"_new.stct";
				FileOutputStream out = new FileOutputStream(
						new File(file));
				resource1.save(out, null);
				System.out.println("The new Model is in the file : " +file);
			} catch (Exception e) {
				// TODO: handle exception
			System.out.println(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	createDialog(e.getMessage());
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	createDialog(e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return file;
	}

	public String StateChartToIAC(String filename, String Path){
		String file = new String();
		statechart.Model old = (statechart.Model) StatechartFactory.eINSTANCE.createModel();
		ResourceSet resourceSet1 = new ResourceSetImpl();
		resourceSet1.getResourceFactoryRegistry().getExtensionToFactoryMap()
		.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
				new XMIResourceFactoryImpl());
		
		resourceSet1.getPackageRegistry().put(StatechartPackage.eNS_URI,
				StatechartPackage.eINSTANCE);
		Resource resource = resourceSet1.createResource(URI
				.createURI(StatechartPackage.eNS_URI));
		try {
			File source = new File(filename);
			resource.load(new FileInputStream(source), new HashMap<Object,Object>());
			resource.load(null);
			old = (statechart.Model) resource.getContents().get(0);
			root = old.getNodes().get(0);
			IACroot = StatechartNodeToIACNode(root);
			IACModel.getNodes().add(IACroot);
			
			for(int i=0; i<root.getChildren().size(); i++){
				IACModel.getNodes().add(StatechartNodeToIACNode(root.getChildren().get(i)));
				DFAccess(root.getChildren().get(i));
			}
			
			IAC.Transition item = IACFactory.eINSTANCE.createTransition();
			for(Iterator<statechart.Transition> it = old.getTransitions().iterator(); it.hasNext();){
				statechart.Transition cur = it.next();
				item = IACFactory.eINSTANCE.createTransition();
				item.setName(cur.getName());
				if(cur.getTE()!=null)
					item.setTE(cur.getTE());
				item.setSource(getNodeIAC(cur.getSource().getLabel()));
				item.setTarget(getNodeIAC(cur.getTarget().getLabel()));
				IACModel.getTransitions().add(item);
			}
			IAC.Variable var;
			for(int i=0; i<old.getVariables().size(); i++){
				var= IACFactory.eINSTANCE.createVariable();
				var.setName(old.getVariables().get(i).getName());
				var.setType(old.getVariables().get(i).getType());
				IACModel.getVariables().add(var);
			}
			
			DFAddVariables(root);
			
			IACModel.setName(old.getName());
			ResourceSet resourceSet = new ResourceSetImpl();

			// Register the appropriate resource factory to handle all file
			// extensions.
			//
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
					.put(Resource.Factory.Registry.DEFAULT_EXTENSION,
							new XMIResourceFactoryImpl());

			// Register the package to ensure it is available during loading.
			//
			resourceSet.getPackageRegistry().put(StatechartPackage.eNS_URI,
					IACPackage.eINSTANCE);

			Resource resource1 = resourceSet.createResource(URI
					.createURI(IACPackage.eNS_URI));
			resource1.getContents().add(IACModel);
			try {
				file =Path+ File.separator+ IACModel.getName() +"_new.iac";
				FileOutputStream out = new FileOutputStream(
						new File(file));
				resource1.save(out, null);
				//createDialog("The new Model is in the file : " +file);
			} catch (Exception e) {
				// TODO: handle exception
			//	createDialog(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		//	createDialog(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	createDialog(e.getMessage());
		}
		

		
		return file;
	}
	
	private IAC.Node getNodeIAC(String label){
		for( Iterator<IAC.Node> it = (Iterator<IAC.Node>) IACModel.getNodes().iterator(); it.hasNext();){
			IAC.Node cur = it.next();
			if(cur.getLabel().equals(label))
				return cur;
		}
		return null;
	}
	
	private statechart.Node getNodeStateChart(String label){
		if(root.getLabel().equals(label))
			return root;
		else
			return DFSearch(root, label);
	}
	
	private void DFAccess(statechart.Node father){
		
		for(int i=0; i<father.getChildren().size(); i++){
			IACModel.getNodes().add(StatechartNodeToIACNode(father.getChildren().get(i)));
			DFAccess(father.getChildren().get(i));
		}
	}
	private void DFAddVariables(statechart.Node father){
		
		
		for(int i=0; i<father.getChildren().size(); i++){
			if(!father.getChildren().get(i).getVariables().isEmpty()){
				for(int j=0; j<IACModel.getNodes().size(); j++){
					IAC.Node n = IACModel.getNodes().get(j);
					
					if(IACModel.getNodes().get(j).getLabel().equals(father.getChildren().get(i).getLabel())){
						IAC.Variable var = IACFactory.eINSTANCE.createVariable();
						for(int q=0; q<father.getChildren().get(i).getVariables().size(); q++){
						//	System.out.println("StateChartVAR "+father.getChildren().get(i).getVariables().get(q));
													
							var.setName(father.getChildren().get(i).getVariables().get(q).getName());
							var.setType(father.getChildren().get(i).getVariables().get(q).getType());
							//System.out.println("VAR "+ var);
							for(int p=0; p<IACModel.getVariables().size(); p++){
							//	System.out.println("IACMODEL VAR "+ IACModel.getVariables().get(p));
								if(var.getName().equals(IACModel.getVariables().get(p).getName()) && var.getType().equals(IACModel.getVariables().get(p).getType())){
								//	System.out.println("IACMODEL VAR "+ IACModel.getVariables().get(p));
									n.getVariables().add(IACModel.getVariables().get(p));
									if(!n.getVariables().isEmpty()){
										System.out.println(n.getVariables().get(0));
									}
								}
							}
						}
						IACModel.getNodes().set(j, n);
						break;
					}
				}
			}
				
			DFAddVariables(father.getChildren().get(i));
		}
	}
	private statechart.Node DFSearch(statechart.Node root, String label){
		Iterator<Node> it = root.getChildren().iterator();
		statechart.Node search = null;
		while( it.hasNext()){
			search = it.next();
			if(search.getLabel().equals(label)){
			//	System.out.println("Label : " + search.getLabel());
				return search;
			}
			else if(search.getChildren().size()>0){
				statechart.Node newsearch = DFSearch(search, label);
				if (newsearch!=null)
					return newsearch;
			}
		}
		//if(search!=null)
		//	System.out.println("To teleutaio "+ search.getLabel());
		return null;
	}
	
	private statechart.Node IACNodeToStateChartNode(IAC.Node node){
		if(node==null)
			return null;
		statechart.Node item = StatechartFactory.eINSTANCE.createNode();
		
		item.setActivity(node.getActivity());
		item.setLabel(node.getLabel());
		item.setName(node.getName());
		item.setType(node.getType());
		
		return item;
	}
	private IAC.Node StatechartNodeToIACNode(statechart.Node node){
		if(node==null)
			return null;
		IAC.Node item = IACFactory.eINSTANCE.createNode();
		
		item.setActivity(node.getActivity());
		item.setLabel(node.getLabel());
		item.setName(node.getName());
		item.setType(node.getType());
		if(node.getFather_of()!=null)
			item.getFather_of().add(getNodeIAC(node.getFather_of().getLabel()));
				
		return item;
	}
	
	private void addNodeStatechart( statechart.Node node, String fatherLabel){
		
		if(root.getLabel().equals(fatherLabel))
			root.getChildren().add(node);
		else{
			DFSAdd(root, fatherLabel, node);
		}
		
	}
	
	private statechart.Node DFSAdd(Node root, String label, statechart.Node node){
			Iterator<Node> it = root.getChildren().iterator();
			statechart.Node search = null;
			while( it.hasNext()){
				search = it.next();
				if(search.getLabel().equals(label)){
					search.getChildren().add((Node) node);
					return search;
				}
				else if(search.getChildren().size()>0){
					Node newsearch = DFSAdd(search, label, node);
					if (newsearch!=null){
						return newsearch;
					}
				}
			}
			return null;
	}
}
