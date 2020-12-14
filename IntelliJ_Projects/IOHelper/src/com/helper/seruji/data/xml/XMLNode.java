package com.helper.seruji.data.xml;

public class XMLNode {
    private String name;
    private String value;
    private XMLAttributes attributes;

    public XMLNode(String name) {
        this.name = name;
        this.value = null;
        this.attributes = null;
    }

    public XMLNode(String name, XMLAttributes attributes) {
        this.name = name;
        this.value = null;
        this.attributes = attributes;
    }

    public XMLNode(String name, String value, XMLAttributes attributes) {
        this.name = name;
        this.value = value;
        this.attributes = attributes;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public XMLAttributes getAttributes() {
        return this.attributes;
    }
}
