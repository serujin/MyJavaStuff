package com.helper.seruji.data.raf;

import com.helper.seruji.exception.InvalidRAFRegisterConstructionException;

public class RafRegister {
    private String[] descriptions;
    private RafDataType[] types;

    public RafRegister(String[] descriptions, RafDataType[] types) throws InvalidRAFRegisterConstructionException {
        if(descriptions == null || types == null || descriptions.length != types.length) {
            throw new InvalidRAFRegisterConstructionException();
        }
        this.descriptions = descriptions;
        this.types = types;
    }

    public String[] getDescriptions() {
        return this.descriptions;
    }

    public RafDataType[] getTotalBytesFromRegister() {
        return this.types;
    }
}
