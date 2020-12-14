package main;

import com.helper.seruji.data.db.DBDataTypes;
import com.helper.seruji.data.db.DBProvider;
import com.helper.seruji.data.db.DBRegister;
import com.helper.seruji.data.raf.RafDataType;
import com.helper.seruji.data.raf.RafRegister;
import com.helper.seruji.data.xml.XMLAttributes;
import com.helper.seruji.data.xml.XMLNode;
import com.helper.seruji.data.xml.XMLRegister;
import com.helper.seruji.exception.InvalidRAFRegisterConstructionException;
import com.helper.seruji.exception.InvalidRAFRegisterValuesException;
import com.helper.seruji.exception.InvalidXMLAttributesConstructionException;
import com.helper.seruji.handler.database.DBManager;
import com.helper.seruji.handler.file.OOSFileHandler;
import com.helper.seruji.handler.file.RAFFileHandler;
import com.helper.seruji.handler.file.TXTFileHandler;
import com.helper.seruji.handler.file.XMLFileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class Test {
    public static void main(String[] args) {
        testRAF(new File("raf.dat"));
        testOOS(new File("oos.dat"));
        testTXT(new File("txt.txt"));
        testXML(new File("xml.xml"));
        testMySQL();
    }
    
    private static void testRAF(File file) {
        RAFFileHandler raf = null;
        try {
            raf = new RAFFileHandler(file, new RafRegister(new String[] {
                "Entero = ",
                "Boolean = "
            }, new RafDataType[] {
                RafDataType.INT,
                RafDataType.BOOLEAN
            }));
            raf.addRegisterToFile(true, new Object[] {
                9,
                true
            });
        } catch (InvalidRAFRegisterConstructionException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidRAFRegisterValuesException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        raf.showFileContent();
    }
    
    private static void testOOS(File file) {
        List<Client> clients = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            clients.add(new Client(i));
        }
        OOSFileHandler<Client> oos = new OOSFileHandler<>(file);
        try {
            oos.addObjectToFile(clients);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Client client : oos.getFileContent()) {
            System.out.println("client is " + client.getAge() + " years old");
        }
    }
    
    private static void testTXT(File file) {
        TXTFileHandler txt = null;
        try {
            txt = new TXTFileHandler(file);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i < 10; i++) {
            txt.addTextToFile(true, "\nLine " + i);
        }
        txt.showFileContent();
        System.out.println(txt.getFileContent());
    }
    
    private static void testXML(File file) {
        XMLFileHandler xml = null;
        try {
            xml = new XMLFileHandler(file, "coches");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        XMLAttributes parentAttributes = null;
        try {
            parentAttributes = new XMLAttributes(new String[] {"id"}, new String[] {"1"});
        } catch (InvalidXMLAttributesConstructionException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        XMLNode parent = new XMLNode("coche", parentAttributes);
        XMLNode[] children = new XMLNode[] {
            new XMLNode("matrícula", "1234CJH", parentAttributes),
            new XMLNode("espejos", "3"),
            new XMLNode("puertas", "5")
        };
        XMLRegister register = new XMLRegister(parent, children);
        try {
            xml.addRegisterToXML(register);
        } catch (TransformerException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        xml.showFileContent();
    }
    
    private static void testMySQL() {
        DBManager manager = null;
        try {
            manager = new DBManager(DBProvider.MYSQL, "validacion", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(manager.select("select * from usuarios", new DBRegister(new DBDataTypes[] {
                DBDataTypes.INT,
                DBDataTypes.STRING
            })));
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
