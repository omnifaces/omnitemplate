/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The JUnit tests for XmlDefaultHandler2.
 */
public class XmlDefaultHandler2Test {

    /**
     * Test characters method.
     *
     * @throws Exception when an error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testCharacters() throws Exception {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.stack.push(new CompositeProcessor());
        handler.textProcessorClass = TestStaticTextProcessor.class;
        handler.characters(" ".toCharArray(), 0, 1);
        assertTrue(handler.stack.peek() instanceof CompositeProcessor);
        CompositeProcessor compositeProcessor = (CompositeProcessor) handler.stack.peek();
        assertTrue(compositeProcessor.getChildren().size() == 1);
        StaticTextProcessor staticTextProcessor = (StaticTextProcessor) compositeProcessor.getChildren().get(0);
        assertEquals(" ", staticTextProcessor.getText());
    }

    /**
     * Test characters method.
     *
     * @throws Exception when an error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testCharacters2() throws Exception {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        CompositeProcessor parent = new CompositeProcessor();
        handler.stack.push(parent);
        StaticTextProcessor textProcessor = new TestStaticTextProcessor();
        textProcessor.setText("");
        parent.getChildren().add(textProcessor);
        handler.textProcessorClass = TestStaticTextProcessor.class;
        handler.characters(" ".toCharArray(), 0, 1);
        assertTrue(handler.stack.peek() instanceof CompositeProcessor);
        CompositeProcessor compositeProcessor = (CompositeProcessor) handler.stack.peek();
        assertTrue(compositeProcessor.getChildren().size() == 1);
        assertTrue(compositeProcessor.getChildren().get(0) instanceof TestStaticTextProcessor);
        StaticTextProcessor staticTextProcessor = (StaticTextProcessor) compositeProcessor.getChildren().get(0);
        assertEquals(" ", staticTextProcessor.getText());
    }

    /**
     * Test characters method.
     *
     * @throws Exception when an error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testCharacters3() throws Exception {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        PlaceholderProcessor placeHolder = new PlaceholderProcessor();
        CompositeProcessor parent = new CompositeProcessor();
        parent.getChildren().add(placeHolder);
        placeHolder.setParent(parent);
        handler.stack.push(placeHolder);
        handler.textProcessorClass = TestStaticTextProcessor.class;
        handler.characters(" ".toCharArray(), 0, 1);
        assertTrue(handler.stack.peek() instanceof PlaceholderProcessor);
        parent = (CompositeProcessor) placeHolder.getParent();
        assertEquals(2, parent.getChildren().size());
    }

    /**
     * Test createTextProcessor method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test(expected = SAXException.class)
    @SuppressWarnings({"unchecked"})
    public void testCreateTextProcessor() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2(TestExceptionStaticTextProcessor.class);
        handler.stack.push(new CompositeProcessor());
        handler.characters(" ".toCharArray(), 0, 1);
    }

    /**
     * Test endElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testEndElement() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestNotFoundTagProcessorFactory();
        handler.textProcessorClass = TestStaticTextProcessor.class;
        CompositeProcessor parent = new CompositeProcessor();
        handler.stack.push(parent);
        handler.endElement(null, "name", null);
        assertTrue(parent.getChildren().size() == 1);
        StaticTextProcessor staticTextProcessor = (StaticTextProcessor) parent.getChildren().get(0);
        assertEquals("</name>", staticTextProcessor.getText());
        assertTrue(handler.stack.empty());
    }

    /**
     * Test endElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testEndElement2() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestAlwaysFoundTagProcessorFactory();
        handler.textProcessorClass = TestStaticTextProcessor.class;
        CompositeProcessor parent = new CompositeProcessor();
        handler.stack.push(parent);
        handler.endElement(null, "name", null);
        assertTrue(handler.stack.empty());
    }

    /**
     * Test endElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testEndElement3() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestNotFoundTagProcessorFactory();
        handler.textProcessorClass = TestStaticTextProcessor.class;
        TestStaticTextProcessor text = new TestStaticTextProcessor();
        text.setText("");
        handler.stack.push(text);
        handler.endElement(null, "name", null);
        assertTrue(handler.stack.empty());
        assertEquals(text.getText(), "</name>");
    }

    /**
     * Test endElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testEndElement4() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestNotFoundTagProcessorFactory();
        handler.textProcessorClass = TestStaticTextProcessor.class;
        TestCompositeProcessor root = new TestCompositeProcessor();
        PlaceholderProcessor placeholder = new PlaceholderProcessor();
        placeholder.setParent(root);
        root.getChildren().add(placeholder);
        handler.stack.push(root);
        handler.stack.push(placeholder);
        handler.endElement(null, "name", null);
        assertEquals(2, root.getChildren().size());
        assertTrue(root.getChildren().get(1) instanceof TestStaticTextProcessor);
    }

    /**
     * Test getHtmlAttributes method.
     */
    @Test
    public void testGetHtmlAttributes() {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("uri", "localName", "qName", "type", "value");
        assertEquals(" localName=\"value\"", handler.getHtmlAttributes(attributes));
    }

    /**
     * Test getTagAttributes method.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetTagAttributes() {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        AttributesImpl attributes = new AttributesImpl();
        attributes.addAttribute("uri", "localName", "qName", "type", "value");
        Map<String, TagAttribute> map = handler.getTagAttributes(attributes);
        assertFalse(map.isEmpty());
        assertTrue(map.containsKey("localName"));
        TagAttribute attribute = map.get("localName");
        assertEquals("localName", attribute.getLocalName());
        assertEquals("uri", attribute.getUri());
        assertEquals("value", attribute.getValue());
    }

    /**
     * Test startElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    public void testStartElement() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestNotFoundTagProcessorFactory();
        handler.stack.push(new TestCompositeProcessor());
        handler.textProcessorClass = TestStaticTextProcessor.class;
        handler.startElement("", "localName", "qName", new AttributesImpl());
        TestStaticTextProcessor result = (TestStaticTextProcessor) handler.stack.peek();
        assertEquals("<localName>", result.getText());
    }

    /**
     * Test startElement method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test
    public void testStartElement2() throws SAXException {
        XmlDefaultHandler2 handler = new XmlDefaultHandler2();
        handler.tagProcessorFactory = new TestAlwaysFoundTagProcessorFactory();
        handler.stack.push(new TestCompositeProcessor());
        handler.textProcessorClass = TestStaticTextProcessor.class;
        handler.startElement("", "localName", "qName", new AttributesImpl());
        assertTrue(handler.stack.peek() instanceof TestTagProcessor);
    }

    /**
     * Helper class used during testing.
     */
    public static class TestStaticTextProcessor extends StaticTextProcessor<ProcessorContext, Object> {

        public TestStaticTextProcessor() {
        }

        @Override
        public void process(ProcessorContext context, Object node, int index) {
        }
    }

    /**
     * Helper class used during testing.
     */
    public static class TestExceptionStaticTextProcessor extends StaticTextProcessor<ProcessorContext, Object> {

        public TestExceptionStaticTextProcessor() throws InstantiationException {
            throw new InstantiationException();
        }

        @Override
        public void process(ProcessorContext context, Object node, int index) {
        }
    }

    /**
     * Helper class used during testing.
     */
    public static class TestNotFoundTagProcessorFactory implements TagProcessorFactory<Object, Object> {

        @Override
        public TagProcessor<Object, Object> getTagProcessor(String uri, String localName) {
            return null;
        }

        @Override
        public boolean hasTagProcessor(String uri, String localName) {
            return false;
        }
    }

    /**
     * Helper class used during testing.
     */
    public static class TestAlwaysFoundTagProcessorFactory implements TagProcessorFactory<Object, Object> {

        @Override
        public TagProcessor<Object, Object> getTagProcessor(String uri, String localName) {
            return new TestTagProcessor();
        }

        @Override
        public boolean hasTagProcessor(String uri, String localName) {
            return true;
        }
    }

    /**
     * Helper class during testing.
     */
    private static class TestCompositeProcessor extends CompositeProcessor<ProcessorContext, Object> {

        public TestCompositeProcessor() {
        }
    }

    /**
     * Helper class during testing.
     */
    private static class TestTagProcessor extends TagProcessor<Object, Object> {

        public TestTagProcessor() {
        }
    }
}
