/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit tests for CompositeProcessor.
 */
public class CompositeProcessorTest {

    /**
     * Test getChildren method.
     */
    @Test
    public void testGetChildren() {
        CompositeProcessor processor = new CompositeProcessor();
        assertEquals(0, processor.getChildren().size());
    }

    /**
     * Test getParent method.
     */
    @Test
    public void testGetParent() {
        CompositeProcessor processor = new CompositeProcessor();
        assertNull(processor.getParent());
    }

    /**
     * Test process method.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testProcess() {
        Object context = null;
        Object node = null;
        CompositeProcessor processor = new CompositeProcessor();
        processor.process(context, node, 0);
    }

    /**
     * Test process method.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testProcess2() {
        Object context = null;
        Object node = null;
        CompositeProcessor parent = new CompositeProcessor();
        CompositeProcessor processor = new CompositeProcessor(parent);
        processor.process(context, node, 0);
    }

    /**
     * Test process method.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testProcess3() {
        Object context = null;
        Object node = null;
        CompositeProcessor child = new CompositeProcessor();
        CompositeProcessor processor = new CompositeProcessor();
        processor.getChildren().add(child);
        processor.process(context, node, 0);
    }

    /**
     * Test of setParent method, of class CompositeProcessor.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testSetParent() {
        CompositeProcessor parent = new CompositeProcessor();
        CompositeProcessor processor = new CompositeProcessor();
        assertNull(processor.getParent());
        processor.setParent(parent);
        assertNotNull(processor.getParent());
        assertEquals(parent, processor.getParent());
    }
}
