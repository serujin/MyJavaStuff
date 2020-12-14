package com.helper.seruji.handler.file;

import java.io.*;
import java.util.List;

public class OOSFileHandler<T> {
    private File file;

    public OOSFileHandler(File file) {
        this.file = file;
    }

    public List<T> getFileContent() {
        List<T> toRead = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            toRead = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return toRead;
    }

    public void addObjectToFile(List<T> toWrite) throws FileNotFoundException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(toWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
