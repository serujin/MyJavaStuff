package com.helper.seruji.data.xml;

import com.helper.seruji.exception.InvalidXMLAttributesConstructionException;

public class XMLAttributes {
    private String[] names;
    private String[] values;

    public XMLAttributes(String[] names, String[] values) throws InvalidXMLAttributesConstructionException {
        if(names == null || values == null || names.length != values.length) {
            throw new InvalidXMLAttributesConstructionException();
        }
        this.names = names;
        this.values = values;
    }

    public String[] getNames() {
        return this.names;
    }

    public String[] getValues() {
        return this.values;
    }
}
