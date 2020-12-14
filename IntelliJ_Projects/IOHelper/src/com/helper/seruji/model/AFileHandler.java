package com.helper.seruji.model;

import java.io.File;
import java.util.List;

public abstract class AFileHandler {
    protected File file;

    protected AFileHandler(File file) {
        this.file = file;
    }

    public abstract void showFileContent();
    public abstract List<Object> getFileContent();
}
