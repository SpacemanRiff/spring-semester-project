package edu.jsu.mcis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import edu.jsu.mcis.ArgumentParser.Types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLManager{
    private static final String ARGUMENT = "argument";
    private static final String OPTIONAL = "optional";
    private static final String NAMED_ARGUMENT = "named";
    private static final String POS_ARGUMENT = "positional";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TYPE = "type";
    private static final String DEFAULT = "default";
    private static final String FLAG = "flag";
    private static PrintWriter writer;
        
    public static void writeArguments(String fileName, ArgumentParser p){
        try{
            writer = new PrintWriter(fileName);
            List<String> argNames = new ArrayList<String>();
            List<String> optionalArgNames = new ArrayList<String>();
            
            argNames = p.getPositionalArgumentNames();
            optionalArgNames = p.getNamedArgumentNames();
            
            writer.write("<?xml version=\"1.0\"?>\n");
            writer.write("<arguments>\n");
            
            for(int i = 0; i < argNames.size(); i++){
                writer.write("\t<" + ARGUMENT + " type = \"positional\"" + ">\n");
                writer.write("\t\t<" + NAME + ">" + argNames.get(i) + "</" + NAME + ">\n");
                writer.write("\t\t<" + DESCRIPTION + ">" + p.getArgumentDescription(argNames.get(i)) 
                                + "</" + DESCRIPTION + ">\n");
                writer.write("\t\t<" + TYPE + ">" + p.getArgumentTypeAsString(argNames.get(i))
                                + "</" + TYPE + ">\n");
                writer.write("\t</" + ARGUMENT + ">\n");
                writer.write("\n");
            }
            
            for(int i = 0; i<optionalArgNames.size(); i++){
                    writer.write("\t<" + ARGUMENT +  " type = \"named\"" + ">\n");
                    writer.write("\t\t<" + NAME + ">" + optionalArgNames.get(i) + "</" + NAME + ">\n");
                    writer.write("\t\t<" + DESCRIPTION + ">" + p.getArgumentDescription(optionalArgNames.get(i))
                                    + "</" + DESCRIPTION + ">\n");
                    writer.write("\t\t<" + TYPE + ">" + p.getArgumentTypeAsString(optionalArgNames.get(i))
                                    + "</" + TYPE + ">\n");
                    writer.write("\t\t<" + DEFAULT + ">" + p.getDefaultValueOf(optionalArgNames.get(i))
                                    + "</" + DEFAULT + ">\n");                    
                    writer.write("\t</" + ARGUMENT + ">\n");
                    writer.write("\n");
            }            
            writer.write("</arguments>");
            writer.close();
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static void loadArguments(String fileName, ArgumentParser p){
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(new File(fileName));
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                
            String name = "";
            String description = "";
            Types type = Types.valueOf("STRING");
            String value = "";
            boolean isFlag = false;
            String argumentType = "";
                
            System.out.println("\n");
            
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                Argument arg;
                NamedArgument optArg;
                
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(NAME)) {
                        event = eventReader.nextEvent();
                        name = event.asCharacters().getData();
                        continue;
                    }
                    if (startElement.getName().getLocalPart().equals(ARGUMENT)){
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while(attributes.hasNext()){
                            Attribute attribute = attributes.next();
                            if(attribute.getName().toString().equals(TYPE)){
                                event = eventReader.nextEvent();
                                argumentType = attribute.getValue();
                            }
                        }
                        continue;
                        
                    }
                    if (startElement.getName().getLocalPart().equals(DESCRIPTION)) {
                        event = eventReader.nextEvent();
                        description = event.asCharacters().getData();
                        continue;
                    }
                    if (startElement.getName().getLocalPart().equals(TYPE)) {
                        event = eventReader.nextEvent();
                        type = Types.valueOf(event.asCharacters().getData());
                        continue;
                    }
                    if (startElement.getName().getLocalPart().equals(DEFAULT)) {
                        event = eventReader.nextEvent();
                        value = event.asCharacters().getData();
                        continue;
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ARGUMENT)) {
                        if(argumentType.equals(POS_ARGUMENT)){
                            p.addPositionalArgument(name, description, type);
                        } else if (argumentType.equals(NAMED_ARGUMENT)){
                            p.addNamedArgument(name, description, type, value);
                        }
                        name = "";
                        description = "";
                        type = Types.valueOf("STRING");
                        value = "";
                        isFlag = false;
                    }
                    
                }
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(XMLStreamException e){
            e.printStackTrace();
        }
        
    }
}