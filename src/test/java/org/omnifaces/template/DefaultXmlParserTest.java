/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.io.StringBufferInputStream;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * The JUnit test for the DefaultXmlParser class.
 */
public class DefaultXmlParserTest {

    /**
     * Test parse method.
     */
    @Test
    public void testParse() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class);
        Processor<ProcessorContext, Object> result = parser.parse("<xml></xml>");
        assertNotNull(result);
    }

    /**
     * Test parse method.
     */
    @Test
    public void testParse2() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class, XmlDefaultHandler2.class);
        Processor<ProcessorContext, Object> result = parser.parse("<xml></xml>");
        assertNotNull(result);
    }

    /**
     * Test parse method.
     */
    @Test
    public void testParse3() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class, XmlDefaultHandler2.class);
        Processor<ProcessorContext, Object> result = parser.parse(new StringBufferInputStream("<xml></xml>"));
        assertNotNull(result);
    }

    /**
     * Test parse method.
     */
    @Test(expected = RuntimeException.class)
    public void testParse4() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class, XmlDefaultHandler2.class);
        Processor<ProcessorContext, Object> result = parser.parse(new StringBufferInputStream("<xml>/xml>"));
    }

    /**
     * Test parse method.
     */
    @Test(expected = RuntimeException.class)
    public void testParse5() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class, XmlDefaultHandler2.class);
        Processor<ProcessorContext, Object> result = parser.parse("<xml>/xml>");
    }

    /**
     * Test parse method.
     */
    @Test(expected = RuntimeException.class)
    public void testParse6() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, TestTextProcessor.class, TestXmlDefaultHandler2.class);
        parser.parse("<xml>/xml>");
    }

    /**
     * Test parse method.
     */
    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void testParse7() {
        TestTagProcessorFactory processorFactory = new TestTagProcessorFactory();
        XmlDefaultHandler2 xmlDefaultHandler2 = new XmlDefaultHandler2();
        xmlDefaultHandler2.textProcessorClass = TestTextProcessor.class;
        DefaultXmlParser parser = new DefaultXmlParser(TestRootProcessor.class, processorFactory, xmlDefaultHandler2);
        Processor<ProcessorContext, Object> result = parser.parse("<xml>/xml>");
    }

    /**
     * Test root processor.
     */
    public static class TestRootProcessor extends TagProcessor<String, String> {
    }

    /**
     * Test text processor.
     */
    public static class TestTextProcessor extends StaticTextProcessor<ProcessorContext, Object> {

        @Override
        public void process(ProcessorContext context, Object node, int index) {
        }
    }

    /**
     * Test tag processor factory.
     */
    public static class TestTagProcessorFactory implements TagProcessorFactory<ProcessorContext, Object> {

        @Override
        public TagProcessor<ProcessorContext, Object> getTagProcessor(String uri, String localName) {
            return null;
        }

        @Override
        public boolean hasTagProcessor(String uri, String localName) {
            return false;
        }
    }

    /**
     * Test XmlDefaultHandler2.
     */
    public static class TestXmlDefaultHandler2 extends XmlDefaultHandler2<Object, Object> {

        public TestXmlDefaultHandler2() throws InstantiationException {
            throw new InstantiationException();
        }
    }
}
