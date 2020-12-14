package com.helper.seruji.handler.file;

import com.helper.seruji.data.raf.RafDataType;
import com.helper.seruji.data.raf.RafRegister;
import com.helper.seruji.exception.InvalidRAFRegisterValuesException;
import com.helper.seruji.model.AFileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RAFFileHandler extends AFileHandler {
    private String[] descriptions;
    private RafDataType[] types;

    public RAFFileHandler(File file, RafRegister register) {
        super(file);
        this.descriptions = register.getDescriptions();
        this.types = register.getTotalBytesFromRegister();
    }

    @Override
    public void showFileContent() {
        try(RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                for(int i = 0; i < types.length; i++) {
                    readFromRafDataType(i, raf);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> getFileContent() {
        List<Object> toReturn = new ArrayList<>();
        try(RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                for(int i = 0; i < types.length; i++) {
                    toReturn.add(getFromRafDataType(i, raf));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    public void addRegisterToFile(Object[] objects) throws InvalidRAFRegisterValuesException {
        if(objects == null || objects.length != this.types.length) {
            throw new InvalidRAFRegisterValuesException();
        }
        try(RandomAccessFile raf = new RandomAccessFile(file, "w")) {
            for(int i = 0; i < this.types.length; i++) {
                addRafDataTypeToFile(this.types[i], raf, objects[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromRafDataType(int currentIndex, RandomAccessFile raf) throws IOException {
        switch (this.types[currentIndex]) {
            case BYTE:
                System.out.println(this.descriptions[currentIndex] + raf.readByte());
                break;
            case BOOLEAN:
                System.out.println(this.descriptions[currentIndex] + raf.readBoolean());
                break;
            case SHORT:
                System.out.println(this.descriptions[currentIndex] + raf.readShort());
                break;
            case CHAR:
                System.out.println(this.descriptions[currentIndex] + raf.readChar());
                break;
            case INT:
                System.out.println(this.descriptions[currentIndex] + raf.readInt());
                break;
            case FLOAT:
                System.out.println(this.descriptions[currentIndex] + raf.readFloat());
                break;
            case LONG:
                System.out.println(this.descriptions[currentIndex] + raf.readLong());
                break;
            case DOUBLE:
                System.out.println(this.descriptions[currentIndex] + raf.readDouble());
                break;
            case UTF_8:
                System.out.println(this.descriptions[currentIndex] + raf.readUTF());
        }
    }

    private Object getFromRafDataType(int currentIndex, RandomAccessFile raf) throws IOException {
        switch (this.types[currentIndex]) {
            case BYTE:
                return raf.readByte();
            case BOOLEAN:
                return raf.readBoolean();
            case SHORT:
                return raf.readShort();
            case CHAR:
                return raf.readChar();
            case INT:
                return raf.readInt();
            case FLOAT:
                return raf.readFloat();
            case LONG:
                return raf.readLong();
            case DOUBLE:
                return raf.readDouble();
            case UTF_8:
                return raf.readUTF();
        }
        return null;
    }

    private void addRafDataTypeToFile(RafDataType dataType, RandomAccessFile raf, Object toAdd) throws IOException {
        switch (dataType) {
            case BYTE:
                raf.writeByte((int) toAdd);
                break;
            case BOOLEAN:
                raf.writeBoolean((boolean) toAdd);
                break;
            case SHORT:
                raf.writeShort((int) toAdd);
                break;
            case CHAR:
                raf.writeChar((int) toAdd);
                break;
            case INT:
                raf.writeInt((int) toAdd);
                break;
            case FLOAT:
                raf.writeFloat((float) toAdd);
                break;
            case LONG:
                raf.writeLong((long) toAdd);
                break;
            case DOUBLE:
                raf.writeDouble((double) toAdd);
        }
    }
}
