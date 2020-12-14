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

public class XMLFileHandler extends AFileHandler {
    private Document dom;

    protected XMLFileHandler(File file, String rootName) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        super(file);
        initXMLFile(rootName);
    }

    @Override
    public void showFileContent() {
        System.out.println(getDataFromNode(dom.getDocumentElement(), " tiene como atributo ", " contiene el valor "));
    }

    @Override
    public List<Object> getFileContent() {
        List<Object> toReturn = new ArrayList<>();
        toReturn.add(getDataFromNode(dom.getDocumentElement(), " tiene como atributo ", " contiene el valor "));
        return toReturn;
    }

    public void addNodeToXML(XMLRegister register) {
        dom.getDocumentElement().appendChild(register.createRegister(dom));
    }

    public String getDataFromNode(Node node, String attrSpace, String valueSpace) {
        StringBuilder builder = new StringBuilder();
        NodeList children = node.getChildNodes();
        NamedNodeMap attributes = node.getAttributes();
        if(attributes != null) {
            showAttributes(builder, node, attributes, attrSpace);
        }
        if(isElement(node) && children.getLength() < 2) {
            showValue(builder, node, valueSpace);
        }
        for(int i = 0; i < children.getLength(); i++) {
            builder.append(getDataFromNode(children.item(i), attrSpace, valueSpace));
        }
        return builder.toString();
    }

    private void initXMLFile(String rootName) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        if(!file.exists()) {
            createXMLFile(rootName);
        }
        loadXMLFile();
    }

    private void createXMLFile(String rootName) throws ParserConfigurationException, TransformerException {
        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = dom.createElement(rootName);
        dom.appendChild(root);
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(dom), new StreamResult(file));
    }

    private void loadXMLFile() throws ParserConfigurationException, IOException, SAXException {
        dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    private void showAttributes(StringBuilder builder, Node node, NamedNodeMap attributes, String midSpace){
        for(int i = 0; i < attributes.getLength(); i++) {
            builder.append(node.getNodeName() + midSpace + attributes.item(i).getTextContent());
        }
    }

    private void showValue(StringBuilder builder, Node node, String midSpace) {
        builder.append(node.getNodeName() + midSpace + node.getTextContent());
    }

    private boolean isElement(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }
}
