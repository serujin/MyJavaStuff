package com.helper.seruji.handler.file;

import com.helper.seruji.model.AFileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTFileHandler extends AFileHandler {
    public TXTFileHandler(File file) throws IOException {
        super(file);
    }

    @Override
    public void showFileContent() {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> getFileContent() {
        List<Object> toReturn = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                toReturn.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void addTextToFile(boolean append, String text) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))) {
            bw.write(text);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
