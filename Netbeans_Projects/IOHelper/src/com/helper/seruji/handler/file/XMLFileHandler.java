package com.helper.seruji.handler.file;

import com.helper.seruji.data.xml.XMLRegister;
import com.helper.seruji.model.AFileHandler;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.TransformerConfigurationException;

public class XMLFileHandler {
    private static final String DEFAULT_ATTR_TEXT = " tiene el atributo ";
    private static final String DEFAULT_VALUE_TEXT = " con valor ";
    
    private File file;
    private Document dom;
    private String rootName;

    public XMLFileHandler(File file, String rootName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        this.file = file;
        this.rootName = rootName;
        initXMLFile();
    }

    public void showFileContent() {
        System.out.println(getDataFromNode(dom.getDocumentElement(), DEFAULT_ATTR_TEXT, DEFAULT_VALUE_TEXT));
    }

    public List<Object> getFileContent() {
        List<Object> toReturn = new ArrayList<>();
        toReturn.add(getDataFromNode(dom.getDocumentElement(), DEFAULT_ATTR_TEXT, DEFAULT_VALUE_TEXT));
        return toReturn;
    }

    public void addRegisterToXML(XMLRegister register) throws TransformerConfigurationException, TransformerException {
        dom.getElementsByTagName(this.rootName).item(0).appendChild(register.createRegister(dom));  
        updateFile();
    }

    public String getDataFromNode(Node node, String attrSpace, String valueSpace) {
        StringBuilder builder = new StringBuilder();
        NodeList children = node.getChildNodes();
        NamedNodeMap attributes = node.getAttributes();
        if(node.getNodeName().equals(dom.getElementsByTagName(this.rootName).item(0).getChildNodes().item(0).getNodeName())) {
            builder.append("\n");
        }
        if(attributes != null) {
            addAttributes(builder, node, attributes, attrSpace, valueSpace);
        }
        if(isElement(node) && children.getLength() < 2 && node.getNodeName() != this.rootName) {
            addValue(builder, node, valueSpace);
        }
        for(int i = 0; i < children.getLength(); i++) {
            builder.append(getDataFromNode(children.item(i), attrSpace, valueSpace));
        }
        return builder.toString();
    }

    private void initXMLFile() throws TransformerException, ParserConfigurationException, IOException, SAXException {
        if(!file.exists()) {
            createXMLFile(this.rootName);
        } else {
            loadXMLFile();
        }
    }

    private void createXMLFile(String rootName) throws ParserConfigurationException, TransformerException {
        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = dom.createElement(rootName);
        dom.appendChild(root);
        updateFile();
    }
    
    private void updateFile() throws TransformerConfigurationException, TransformerException {
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(dom), new StreamResult(file));
    }
 
    private void loadXMLFile() throws ParserConfigurationException, IOException, SAXException {
        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    private void addAttributes(StringBuilder builder, Node node, NamedNodeMap attributes, String firstSpace, String secondSpace){
        for(int i = 0; i < attributes.getLength(); i++) {
            builder.append("\n");
            builder.append(node.getNodeName() + firstSpace + attributes.item(i).getNodeName() + secondSpace + attributes.item(i).getTextContent());
        }
    }

    private void addValue(StringBuilder builder, Node node, String midSpace) {
        builder.append("\n");
        builder.append(node.getNodeName() + midSpace + node.getTextContent());
    }

    private boolean isElement(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }
}
