/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit test for PlaceholderProcessor class.
 */
public class PlaceholderProcessorTest {

    /**
     * Test getParent method.
     */
    @Test
    @SuppressWarnings({"unchecked"})
    public void testGetParent() {
        PlaceholderProcessor processor = new PlaceholderProcessor();
        assertNull(processor.getParent());
        processor.setParent(new PlaceholderProcessor());
        assertNotNull(processor.getParent());
    }

    /**
     * Test process method.
     */
    @Test(expected = UnsupportedOperationException.class)
    @SuppressWarnings({"unchecked"})
    public void testProcess() {
        PlaceholderProcessor processor = new PlaceholderProcessor();
        processor.process(null, null, 0);
    }
}
