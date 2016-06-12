/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit tests for StaticTextProcessor.
 */
public class StaticTextProcessorTest {

    /**
     * Test setParent method.
     */
    @Test
    public void testSetParent() {
        StaticTextProcessor processor = new TestStaticTextProcessor();
        assertNull(processor.getParent());
        processor.setParent(new TestStaticTextProcessor());
        assertNotNull(processor.getParent());
    }

    /**
     * Test implementation of a processor context.
     */
    public static class TestProcessorContext implements ProcessorContext {

        /**
         * Get the attribute.
         *
         * @param name the name.
         * @return the value.
         */
        @Override
        public Object getAttribute(String name) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * Set the attribute.
         *
         * @param name the name.
         * @param value the value.
         */
        @Override
        public void setAttribute(String name, Object value) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * Test implementation of a static text processor.
     */
    public static class TestStaticTextProcessor extends StaticTextProcessor<TestProcessorContext, Object> {

        /**
         * Process.
         *
         * @param context the context.
         * @param node the node.
         * @param index the index.
         */
        @Override
        public void process(TestProcessorContext context, Object node, int index) {
        }
    }
}
