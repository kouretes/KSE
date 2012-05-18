package statechart.diagram.application;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @generated
 */
public class StateChartApplication implements IApplication {

	/**
	 * @generated NOT
	 */
	public static File workspace;
	/**
	 * @generated NOT
	 */
	public static String appDirectory;
	/**
	 * @generated NOT
	 */
	public static String generator;
	/**
	 * @generated NOT
	 */
	public static String cppeditor;
	/**
	 * @generated NOT
	 */
	public static List<String> generationURL;
	/**
	 * @generated NOT
	 */
	public static List<String> modelURL;

	/**
	 * @generated NOT
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
			generationURL = new LinkedList<String>();
			modelURL = new LinkedList<String>();
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Select Workspace");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			File ffff = new File("");
			appDirectory = new String(ffff.getAbsolutePath());
			File str = new File(ffff.getAbsolutePath() + File.separator + "KSE");
			chooser.setCurrentDirectory(new File(str.getParent())
					.getCanonicalFile());
			int retval = chooser.showOpenDialog(null);
			if (retval == JFileChooser.APPROVE_OPTION) {
				workspace = chooser.getSelectedFile();
				workspace.mkdir();
				String prefRep = "configuration" + File.separator
						+ "StateChartDesign.diagram" + File.separator
						+ "repositories.xml";
				File f = new File(prefRep);
				String pref = "configuration" + File.separator
						+ "StateChartDesign.diagram" + File.separator
						+ "preferences.xml";
				File prf = new File(pref);
				try {
					if (!f.exists()) {
						f.createNewFile();
					} else {
						if (readRepositories() != 1)
							JOptionPane.showMessageDialog(null,
									"Error in loading existing repositories");
					}
					if (!prf.exists()) {
						JOptionPane
								.showMessageDialog(
										null,
										"Error in loading existing repositories users configuration. Please configure your application!");
					} else {
						if (readConfiguration() != 1)
							JOptionPane
									.showMessageDialog(
											null,
											"Error in loading existing repositories users configuration. Please configure your application!");
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (retval == JFileChooser.CANCEL_OPTION) {
				//workspace = new File(System.getProperty("user.home"));
				return IApplication.EXIT_OK;
			} else if (retval == JFileChooser.ERROR_OPTION) {
				JOptionPane.showMessageDialog(chooser, "A error Occured!!!");
			}

			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new DiagramEditorWorkbenchAdvisor());

			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	/**
	 * @generated
	 */
	public void stop() {
	}

	/**
	 * @generated NOT
	 */
	public static int readConfiguration() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(appDirectory
					+ File.separator + "configuration" + File.separator
					+ "StateChartDesign.diagram" + File.separator
					+ "preferences.xml"));

			Node root = doc.getFirstChild();

			//    for(int q =0; q<root.getChildNodes().getLength(); q++){
			NodeList nlist = ((Element) root).getElementsByTagName("generator")
					.item(0).getChildNodes();

			StateChartApplication.generator = nlist.item(0).getNodeValue()
					.trim();
			System.out.println(StateChartApplication.generator);

			nlist = ((Element) root).getElementsByTagName("cppeditor").item(0)
					.getChildNodes();
			StateChartApplication.cppeditor = nlist.item(0).getNodeValue()
					.trim();
			System.out.println(StateChartApplication.cppeditor);

			if (generator.equals("iac2monas")) {
				if (System.getProperty("os.name").equals("Linux")) {
					StateChartApplication.generator = "java -jar "
							+ StateChartApplication.appDirectory
							+ File.separator + "plugins" + File.separator
							+ "generator.jar";
				} else {
					File kk = new File(StateChartApplication.appDirectory
							+ File.separator + "plugins" 
							+ File.separator 
							+ "generator.jar");
					if (!kk.exists())
						JOptionPane.showMessageDialog(null,
								"Den yparxei " + kk.getAbsolutePath());
					StateChartApplication.generator ="\""+ System.getProperty("java.home")+"\\bin\\javaw.exe\" -jar \""
							+ kk.getAbsolutePath() + "\" ";
				}

			}
			return 1;

		} catch (SAXParseException err) {
			JOptionPane.showMessageDialog(null, "** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			JOptionPane.showMessageDialog(null, " " + err.getMessage());
			return 0;
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
			return 0;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, t.getMessage() + " throwable");

			return 0;
		}

	}


	/**
	 * @generated NOT
	 */
	public static int writeConfiguration(String generatr, String cppeditor) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("configuration");
			doc.appendChild(rootElement);
			// generator elements
			if (generatr != null) {
				Element appGenerator = doc.createElement("generator");
				appGenerator.appendChild(doc.createTextNode(generatr.trim()));
				rootElement.appendChild(appGenerator);
			}
			// cppeditor elements
			if (cppeditor != null) {
				Element appCppEditor = doc.createElement("cppeditor");
				appCppEditor.appendChild(doc.createTextNode(cppeditor.trim()));
				rootElement.appendChild(appCppEditor);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(appDirectory
					+ File.separator + "configuration" + File.separator
					+ "StateChartDesign.diagram" + File.separator
					+ "preferences.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			return 1;
		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, pce.getMessage());
			return 0;
		} catch (TransformerException tfe) {
			JOptionPane.showMessageDialog(null, tfe.getCause());
			return 0;
		}
		//return 1;
	}

	/**
	 * @generated NOT
	 */
	public static int readRepositories() {
		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(appDirectory
					+ File.separator + "configuration" + File.separator
					+ "StateChartDesign.diagram" + File.separator
					+ "repositories.xml"));

			Node root = doc.getFirstChild();

			NodeList repositories = ((Element) root)
					.getElementsByTagName("repository");
			int totalReps = repositories.getLength();
			System.out.println("Total no of reps : " + totalReps);

			for (int s = 0; s < repositories.getLength(); s++) {

				Node rep = repositories.item(s);
				if (rep.getNodeType() == Node.ELEMENT_NODE) {

					Element repEl = (Element) rep;

					//-------
					Element modelEl = (Element) repEl.getElementsByTagName(
							"model").item(0);
					String model = modelEl.getChildNodes().item(0)
							.getNodeValue().trim();

					//-------
					Element generationurlEl = (Element) repEl
							.getElementsByTagName("generationURL").item(0);

					String genURL = generationurlEl.getChildNodes().item(0)
							.getNodeValue().trim();

					System.out.println("Model: " + model + " generation: "
							+ genURL);
					modelURL.add(model);
					generationURL.add(genURL);
				}//end of if clause
			}//end of for loop with s var

			return 1;
		} catch (SAXParseException err) {
			JOptionPane.showMessageDialog(null, "** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			JOptionPane.showMessageDialog(null, " " + err.getMessage());
			return 0;
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();
			return 0;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, t.getMessage() + " throwable");

			return 0;
		}
	}

	/**
	 * @generated NOT
	 */

	public static int writeRepositories() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("configuration");
			doc.appendChild(rootElement);

			for (int i = 0; i < modelURL.size(); i++) {

				Element repository = doc.createElement("repository");
				rootElement.appendChild(repository);
				if (modelURL.get(i) != null) {
					Node ag = doc.createElement("model");
					ag.appendChild(doc.createTextNode(modelURL.get(i).trim()));
					repository.appendChild(ag);
				}
				if (generationURL.get(i) != null) {
					Node ac = doc.createElement("generationURL");
					ac.appendChild(doc.createTextNode(generationURL.get(i)
							.trim()));
					repository.appendChild(ac);
				}

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(appDirectory
					+ File.separator + "configuration" + File.separator
					+ "StateChartDesign.diagram" + File.separator
					+ "repositories.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");
			return 1;
		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "** Parsing error");
			JOptionPane.showMessageDialog(null, " " + pce.getMessage());
			return 0;
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(null, t.getMessage() + " throwable");

			return 0;
		}
	}

}
