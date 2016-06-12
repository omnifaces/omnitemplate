/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit tests for DefaultProcessorContext.
 */
public class DefaultProcessorContextTest {

    /**
     * Test getAttribute method.
     */
    @Test
    public void testGetAttribute() {
        DefaultProcessorContext context = new DefaultProcessorContext();
        assertNull(context.getAttribute("name"));
    }

    /**
     * Test of setAttribute method, of class DefaultProcessorContext.
     */
    @Test
    public void testSetAttribute() {
        DefaultProcessorContext context = new DefaultProcessorContext();
        context.setAttribute("name", "value");
        assertEquals("value", context.getAttribute("name"));
    }
}
