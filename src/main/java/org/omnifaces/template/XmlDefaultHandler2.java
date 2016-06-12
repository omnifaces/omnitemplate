/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.Attributes2Impl;
import org.xml.sax.ext.DefaultHandler2;

/**
 * The SAX DefaultHandler2.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public class XmlDefaultHandler2<C, N> extends DefaultHandler2 {

    /**
     * Stores the tag processor factory.
     */
    protected TagProcessorFactory<C, N> tagProcessorFactory;

    /**
     * Stores the processor stack.
     */
    protected final Stack<Processor<C, N>> stack;

    /**
     * Stores the text processor.
     */
    protected Class<?> textProcessorClass;

    /**
     * Constructor.
     */
    public XmlDefaultHandler2() {
        this.stack = new Stack<>();
    }

    /**
     * Constructor.
     *
     * @param textProcessorClass the text processor.
     */
    public XmlDefaultHandler2(Class<?> textProcessorClass) {
        this.stack = new Stack<>();
        this.textProcessorClass = textProcessorClass;
    }

    /**
     * Characters.
     *
     * @param characters the characters.
     * @param start the start index.
     * @param length the length.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public void characters(char[] characters, int start, int length) throws SAXException {
        CompositeProcessor<ProcessorContext, Object> parent;
        String string = new String(characters, start, length);

        if (stack.peek() instanceof CompositeProcessor) {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek();
        } else {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek().getParent();
        }

        if (!parent.getChildren().isEmpty()
                && parent.getChildren().get(parent.getChildren().size() - 1) instanceof StaticTextProcessor) {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor
                    = (StaticTextProcessor<ProcessorContext, Object>) parent.
                    getChildren().get(parent.getChildren().size() - 1);
            staticTextProcessor.getStringBuilder().append(string);
        } else {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor = createTextProcessor();
            staticTextProcessor.parent = parent;
            staticTextProcessor.stringBuilder = new StringBuilder(string);
            parent.getChildren().add(staticTextProcessor);
        }
    }

    /**
     * Create the text processor.
     *
     * @return the static text processor
     * @throws SAXException when unable to create text processor.
     */
    @SuppressWarnings({"unchecked"})
    private StaticTextProcessor<ProcessorContext, Object> createTextProcessor() throws SAXException {
        try {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor
                    = (StaticTextProcessor<ProcessorContext, Object>) textProcessorClass.newInstance();
            return staticTextProcessor;
        } catch (InstantiationException | IllegalAccessException ie) {
            throw new SAXException(ie);
        }
    }

    /**
     * End the document.
     *
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        stack.pop();
    }

    /**
     * End the element.
     *
     * @param uri the URI.
     * @param localName the local name.
     * @param qName the QName.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public void endElement(String uri, String localName, String qName) throws SAXException {
        boolean hasHandler = tagProcessorFactory.hasTagProcessor(uri, localName);

        CompositeProcessor<ProcessorContext, Object> parent;

        if (stack.peek() instanceof CompositeProcessor) {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek();
        } else {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek().getParent();
        }

        if (hasHandler) {
            stack.pop();
        } else if (stack.peek() instanceof StaticTextProcessor) {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor
                    = (StaticTextProcessor<ProcessorContext, Object>) stack.peek();
            StringBuilder stringBuilder = staticTextProcessor.getStringBuilder();
            stringBuilder.append("</");
            stringBuilder.append(localName);
            stringBuilder.append(">");
            stack.pop();
        } else if (stack.peek() instanceof PlaceholderProcessor) {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor;
            if (parent.getChildren().get(parent.getChildren().size() - 1) instanceof StaticTextProcessor) {
                staticTextProcessor = (StaticTextProcessor<ProcessorContext, Object>) parent.
                        getChildren().get(parent.getChildren().size() - 1);
                StringBuilder stringBuilder = staticTextProcessor.getStringBuilder();
                stringBuilder.append("</");
                stringBuilder.append(localName);
                stringBuilder.append(">");
            } else {
                staticTextProcessor = createTextProcessor();
                staticTextProcessor.setText("</" + localName + ">");
                parent.getChildren().add(staticTextProcessor);
            }
            PlaceholderProcessor placeHolder = (PlaceholderProcessor) stack.pop();
            placeHolder.setParent(null);
        } else {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor = createTextProcessor();
            staticTextProcessor.parent = parent;
            staticTextProcessor.stringBuilder = new StringBuilder("</" + localName + ">");
            parent.getChildren().add(staticTextProcessor);
            stack.pop();
        }
    }

    /**
     * Get the attributes.
     *
     * @param attributes the attributes.
     * @return the attributes.
     */
    protected String getHtmlAttributes(Attributes attributes) {
        Attributes2Impl attributes2 = new Attributes2Impl(attributes);
        StringBuilder result = new StringBuilder();
        if (attributes2.getLength() > 0) {
            for (int i = 0; i < attributes2.getLength(); i++) {
                if (attributes2.isSpecified(i)) {
                    result.append(" ");
                    result.append(attributes2.getLocalName(i));
                    result.append("=\"");
                    result.append(attributes2.getValue(i));
                    result.append("\"");
                }
            }
        }
        return result.toString();
    }

    /**
     * Get the map of tag attributes for the given XML attributes.
     *
     * @param attributes the XML attributes.
     * @return the map of tag attributes.
     */
    protected Map<String, TagAttribute> getTagAttributes(Attributes attributes) {
        Map<String, TagAttribute> result = new HashMap<>();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            result.put(attributes.getLocalName(i),
                    new TagAttribute(
                            attributes.getURI(i),
                            attributes.getLocalName(i),
                            attributes.getValue(i)));
        }
        return result;
    }

    /**
     * Set the tag processor factory.
     *
     * @param tagProcessorFactory the tag processor factory.
     */
    public void setTagProcessorFactory(TagProcessorFactory<C, N> tagProcessorFactory) {
        this.tagProcessorFactory = tagProcessorFactory;
    }

    /**
     * Set the processor.
     *
     * @param processor the processor.
     */
    public void setProcessor(Processor<C, N> processor) {
        this.stack.push(processor);
    }

    /**
     * Start an XML element.
     *
     * @param uri the URI.
     * @param localName the local name.
     * @param qName the QName.
     * @param attributes the attributes.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        TagProcessor<ProcessorContext, Object> tagProcessor
                = (TagProcessor<ProcessorContext, Object>) tagProcessorFactory.getTagProcessor(uri, localName);
        CompositeProcessor<ProcessorContext, Object> parent;

        if (stack.peek() instanceof CompositeProcessor) {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek();
        } else {
            parent = (CompositeProcessor<ProcessorContext, Object>) stack.peek().getParent();
        }

        if (tagProcessor != null) {
            stack.push((Processor<C, N>) tagProcessor);
            tagProcessor.attributes = getTagAttributes(attributes);
            tagProcessor.parent = parent;
            parent.getChildren().add(tagProcessor);
        } else if (!parent.getChildren().isEmpty()
                && parent.getChildren().get(parent.getChildren().size() - 1) instanceof StaticTextProcessor) {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor
                    = (StaticTextProcessor<ProcessorContext, Object>) parent.
                    getChildren().get(parent.getChildren().size() - 1);
            StringBuilder stringBuilder = staticTextProcessor.getStringBuilder();
            stringBuilder.append("<");
            stringBuilder.append(localName);
            stringBuilder.append(getHtmlAttributes(attributes));
            stringBuilder.append(">");
            PlaceholderProcessor<ProcessorContext, Object> placeHolder = new PlaceholderProcessor<>();
            placeHolder.setParent(parent);
            stack.push((Processor<C, N>) placeHolder);
        } else {
            StaticTextProcessor<ProcessorContext, Object> staticTextProcessor = createTextProcessor();
            staticTextProcessor.parent = parent;
            staticTextProcessor.stringBuilder = new StringBuilder(
                    "<" + localName + getHtmlAttributes(attributes) + ">");
            stack.push((Processor<C, N>) staticTextProcessor);
            parent.getChildren().add(staticTextProcessor);
        }
    }

    /**
     * Start the document.
     *
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public void startDocument() throws SAXException {
        super.startDocument();
        CompositeProcessor<ProcessorContext, Object> top = (CompositeProcessor<ProcessorContext, Object>) stack.peek();
        StaticTextProcessor<ProcessorContext, Object> staticTextProcessor = createTextProcessor();
        staticTextProcessor.parent = top;
        staticTextProcessor.stringBuilder = new StringBuilder();
        stack.push((Processor<C, N>) staticTextProcessor);
        top.getChildren().add(staticTextProcessor);
    }

    /**
     * Set the text processor class.
     *
     * @param textProcessorClass the text processor class.
     */
    public void setTextProcessorClass(Class<?> textProcessorClass) {
        this.textProcessorClass = textProcessorClass;
    }
}
