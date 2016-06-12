/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import static javax.xml.parsers.SAXParserFactory.newInstance;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * The default XML parser.
 */
public class DefaultXmlParser implements XmlParser<ProcessorContext, Object> {

    /**
     * Stores the factory.
     */
    private final SAXParserFactory factory;

    /**
     * Stores the handler class.
     */
    private Class handlerClass;

    /**
     * Stores the handler.
     */
    private XmlDefaultHandler2 handler;

    /**
     * Stores the root processor class.
     */
    private final Class rootProcessorClass;

    /**
     * Stores the parser.
     */
    private SAXParser saxParser;

    /**
     * Stores the tag processor factory.
     */
    private final TagProcessorFactory<ProcessorContext, Object> tagProcessorFactory;

    /**
     * Stores the XML reader.
     */
    private XMLReader xmlReader;

    /**
     * Stores the text class.
     */
    private Class textClass;

    /**
     * Constructor.
     *
     * @param rootProcessorClass the root processor class.
     * @param tagProcessorFactory the tag processor factory.
     * @param textClass the text class.
     */
    public DefaultXmlParser(Class<?> rootProcessorClass,
            TagProcessorFactory<ProcessorContext, Object> tagProcessorFactory, Class<?> textClass) {
        this(rootProcessorClass, tagProcessorFactory, textClass, XmlDefaultHandler2.class);
    }

    /**
     * Constructor.
     *
     * @param rootProcessorClass the root processor class.
     * @param tagProcessorFactory the tag processor factory.
     * @param textClass the text class.
     * @param defaultHandler2Class the DefaultHandler2 class.
     */
    @SuppressWarnings({"unchecked"})
    public DefaultXmlParser(
            Class<?> rootProcessorClass,
            TagProcessorFactory<ProcessorContext, Object> tagProcessorFactory,
            Class<?> textClass,
            Class<?> defaultHandler2Class) {
        this.factory = newInstance();
        this.factory.setNamespaceAware(true);
        this.factory.setValidating(false);
        this.handlerClass = defaultHandler2Class;
        this.textClass = textClass;
        this.rootProcessorClass = rootProcessorClass;
        this.tagProcessorFactory = tagProcessorFactory;
    }

    /**
     * Constructor.
     *
     * @param rootProcessorClass the root processor class.
     * @param tagProcessorFactory the tag processor factory.
     * @param xmlDefaultHandler2 the DefaultHandler2.
     */
    public DefaultXmlParser(
            Class<?> rootProcessorClass,
            TagProcessorFactory<ProcessorContext, Object> tagProcessorFactory,
            XmlDefaultHandler2 xmlDefaultHandler2) {
        this.factory = newInstance();
        this.factory.setNamespaceAware(true);
        this.factory.setValidating(false);
        this.handler = xmlDefaultHandler2;
        this.rootProcessorClass = rootProcessorClass;
        this.tagProcessorFactory = tagProcessorFactory;
    }

    /**
     * Parse the given XML string.
     *
     * @param xml the XML string.
     * @return the processor, or null if an error occurred.
     */
    @Override
    @SuppressWarnings({"unchecked", "checkstyle:operatorwrap"})
    public Processor<ProcessorContext, Object> parse(String xml) {
        try {
            if (handlerClass != null) {
                handler = (XmlDefaultHandler2<Object, Object>) handlerClass.newInstance();
                handler.setTextProcessorClass(textClass);
            }
            saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(new XmlErrorHandler());
            xmlReader.setEntityResolver(new XmlEntityResolver());
            XmlDefaultHandler2<ProcessorContext, Object> handler2
                    = (XmlDefaultHandler2<ProcessorContext, Object>) xmlReader.getContentHandler();
            handler2.setTagProcessorFactory(tagProcessorFactory);
            Processor rootProcessor = (Processor) rootProcessorClass.newInstance();
            handler2.setProcessor(rootProcessor);
            xmlReader.parse(new InputSource(new StringReader(xml)));
            return rootProcessor;
        } catch (InstantiationException | IllegalAccessException | IOException |
                ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Unable to parse", e);
        }
    }

    /**
     * Parse the given input stream.
     *
     * @param inputStream the XML input stream.
     * @return the processor, or null if an error occurred.
     */
    @Override
    @SuppressWarnings({"unchecked", "checkstyle:operatorwrap"})
    public Processor<ProcessorContext, Object> parse(InputStream inputStream) {
        try {
            if (handlerClass != null) {
                handler = (XmlDefaultHandler2<Object, Object>) handlerClass.newInstance();
                handler.setTextProcessorClass(textClass);
            }
            saxParser = factory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(new XmlErrorHandler());
            xmlReader.setEntityResolver(new XmlEntityResolver());
            XmlDefaultHandler2<ProcessorContext, Object> handler2
                    = (XmlDefaultHandler2<ProcessorContext, Object>) xmlReader.getContentHandler();
            handler2.setTagProcessorFactory(tagProcessorFactory);
            Processor rootProcessor = (Processor) rootProcessorClass.newInstance();
            handler2.setProcessor(rootProcessor);
            xmlReader.parse(new InputSource(inputStream));
            return rootProcessor;
        } catch (InstantiationException | IllegalAccessException | IOException |
                ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Unable to parse", e);
        }
    }
}
