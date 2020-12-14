package com.helper.seruji.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AFileHandler {
    protected File file;

    protected AFileHandler(File file) throws IOException {
        this.file = file;
        if(!this.file.exists()) {
            this.file.createNewFile();
        }
    }

    public abstract void showFileContent();
    public abstract List<Object> getFileContent();
}
