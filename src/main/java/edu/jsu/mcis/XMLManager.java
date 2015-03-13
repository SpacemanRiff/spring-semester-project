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
import java.util.List;

public class XMLManager{
    private static final String ARGUMENT = "argument";
    private static final String OPTIONAL = "optional";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String TYPE = "type";
    private static final String DEFAULT = "default";
    private static final String FLAG = "flag";
    private PrintWriter writer;
    
    public void writeArguments(String fileName, ArgumentParser p){
        try{
            writer = new PrintWriter(fileName);
            List<String> argNames = new ArrayList<String>();
            List<String> optionalArgNames = new ArrayList<String>();
            
            argNames = p.getArgumentNames();
            optionalArgNames = p.getOptionalArgumentNames();
            
            writer.write("<?xml version=\"1.0\"?>");
            writer.write("<config>");
            
            for(int i = 0; i < argNames.size(); i++){
                writer.write("\t<argument>");
                writer.write("\t\t<name>" + argNames.get(i) + "</name>");
                writer.write("\t\t<description>" + p.getArgumentDescription(argNames.get(i)) 
                                + "</description>");
                writer.write("\t\t<type>" + p.getArgumentTypeAsString(argNames.get(i))
                                + "</type>");
                writer.write("\t</argument>");
                writer.write("");
            }
            
            for(int i = 0; i<optionalArgNames.size(); i++){
                writer.write("\t<optional>");
                writer.write("\t\t<name>" + optionalArgNames.get(i) + "</name>");
                writer.write("\t\t<description>" + p.getOptionalArgumentDescription(optionalArgNames.get(i))
                                + "</description>");
                writer.write("\t\t<type>" + p.getOptionalArgumentTypeAsString(optionalArgNames.get(i))
                                + "</type>");
                writer.write("\t\t<default>" + p.getOptionalArgumentValueOf(optionalArgNames.get(i))
                                + "</default>");
                writer.write("\t</optional>");
                writer.write("");
            }
            
            writer.write("</config>");
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public void loadArguments(String fileName, ArgumentParser p){
        loadArguments(new File(fileName), p);
    }
    
    public void loadArguments(File file, ArgumentParser p){
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(file);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                
            String name = "";
            String description = "";
            Types type = Types.valueOf("STRING");
            String value = "";
                
            System.out.println("\n");
            
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                ArgumentInformation arg;
                OptionalArgumentInformation optArg;
                
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals(NAME)) {
                        event = eventReader.nextEvent();
                        name = event.asCharacters().getData();
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
                    //should be removed when we get rid of the flag definition
                    if (startElement.getName().getLocalPart().equals(FLAG)) {}
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ARGUMENT)) {
                        p.addArgument(name, description, type);
                        
                        name = "";
                        description = "";
                        type = Types.valueOf("STRING");
                        value = "";
                    }
                    if (endElement.getName().getLocalPart().equals(OPTIONAL)) {
                        p.addOptionalArgument(name, description, type, value);
                        
                        name = "";
                        description = "";
                        type = Types.valueOf("STRING");
                        value = "";
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