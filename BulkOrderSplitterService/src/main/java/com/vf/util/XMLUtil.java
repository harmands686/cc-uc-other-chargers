package com.vf.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtil {

	public static NodeList xPathProcessor(String xpath, String inputXML)
			throws TransformerException, ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {

		// Create DocumentBuilderFactory for reading xml file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse(new InputSource(new StringReader(inputXML)));

		// Compile the XPath expression for getting all brands
		XPathExpression xPathExpr = XPathFactory.newInstance().newXPath().compile(xpath);

		// XPath text example : executing xpath expression in java
		Object result = xPathExpr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		return nodes;
	}
	
	
	
	public static String xpathProcessor_V(String xpath, String xml) throws XPathExpressionException{
		
		InputSource inputXML = new InputSource( new StringReader( xml ) );
        
        XPath xPath = XPathFactory.newInstance().newXPath();
         
        String result = xPath.evaluate(xpath, inputXML);
        
        return result;
		
	}
	
	
	public static String childOrderXML(String masterxpath, String childxpath, String inputXML)
			throws TransformerException, ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {

		// Create DocumentBuilderFactory for reading xml file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse(new InputSource(new StringReader(inputXML)));

		// Compile the XPath expression for getting all brands
		XPathExpression xPathExpr = XPathFactory.newInstance().newXPath().compile(childxpath);
		// XPathExpression xPathExpr = xpath.compile(xPathStringResult);

		// XPath text example : executing xpath expression in java
		Object result = xPathExpr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		//System.out.println("Nodelist = "+nodeListToString(nodes));

		Document master = getMasterOrderNodes(masterxpath, inputXML);

		return documentToString(addNodesToDocument(master, nodes));
	}

	private static Document addNodesToDocument(Document master, NodeList nodes) {

		Element element = (Element) nodes.item(0);
		Node copiedNode = master.importNode(element, true);
		master.getDocumentElement().appendChild(copiedNode);
		return master;
	}

	public static String documentToString(Document doc) throws TransformerException {
		StringWriter buf = new StringWriter();
		Transformer xform = TransformerFactory.newInstance().newTransformer();
		xform.transform(new DOMSource(doc), new StreamResult(buf));
		xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		return (buf.toString());
	}
	
	public static String nodeListToString(NodeList nodes) throws TransformerException {
	    DOMSource source = new DOMSource();
	    StringWriter writer = new StringWriter();
	    StreamResult result = new StreamResult(writer);
	    Transformer transformer = TransformerFactory.newInstance().newTransformer();
	    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

	    for (int i = 0; i < nodes.getLength(); ++i) {
	        source.setNode(nodes.item(i));
	        transformer.transform(source, result);
	    }

	    return writer.toString();
	}

	public static String extractPostRequestBody(HttpServletRequest request) {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			Scanner s = null;
			try {
				s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}

	public static Document getMasterOrderNodes(String excludeXpath, String inputXML) throws TransformerException,
			SAXException, IOException, ParserConfigurationException, XPathExpressionException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new InputSource(new StringReader(inputXML)));

		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		XPathExpression expression = xpath.compile(excludeXpath);

		Node b13Node = (Node) expression.evaluate(document, XPathConstants.NODE);
		b13Node.getParentNode().removeChild(b13Node);
		return document;
	}

}