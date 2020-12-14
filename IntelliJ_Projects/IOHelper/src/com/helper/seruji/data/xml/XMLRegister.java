package com.helper.seruji.data.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLRegister {
    private XMLNode parent;
    private XMLNode[] children;

    public XMLRegister(XMLNode parent, XMLNode[] children) {
        this.parent = parent;
        this.children = children;
    }

    public Element createRegister(Document dom) {
        Element root = dom.createElement(this.parent.getName());
        addAttributesToElement(root, parent.getAttributes());
        for(XMLNode child : this.children) {
            Element childElement = dom.createElement(child.getName());
            addValueToElement(dom, childElement, child.getValue());
            addAttributesToElement(childElement, child.getAttributes());
        }
        return root;
    }

    private void addValueToElement(Document dom, Element element, String value) {
        if(value == null) {
            return;
        }
        element.appendChild(dom.createTextNode(value));
    }

    private void addAttributesToElement(Element element, XMLAttributes attributes) {
        if(attributes == null) {
            return;
        }
        String[] names = attributes.getNames();
        String[] values = attributes.getValues();
        for(int i = 0; i < names.length; i++) {
            element.setAttribute(names[i], values[i]);
        }
    }
}
