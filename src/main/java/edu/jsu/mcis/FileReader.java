import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import de.vogella.xml.stax.model.Item;

public class FileReader{
    private static final String ARGUMENT = "argument";
    private static final String OPTIONAL = "optional";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TYPE = "type";
    private static final String DEFAULT = "default";
    private static final String FLAG = "flag";
    
    public void readArguments(Sting fileName, List<String> argumentNames, 
        List<String> optionalArgumentNames, 
        Map<String, ArgumentInformation> argumentMap,
        Map<String, OptionalArgumentInformation> optionalArgumentMap){
            try{
                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(XMLStreamException e){
                e.printStackTrace();
            }
            
        }
}