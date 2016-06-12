/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit tests for TemplateProcessorLiteral.
 */
public class TemplateProcessorLiteralTest {

    /**
     * Test localName method.
     */
    @Test
    public void testLocalName() {
        TemplateProcessorLiteral literal = new TemplateProcessorLiteral(null, "localName");
        assertEquals("localName", literal.localName());
    }

    /**
     * Test uri method.
     */
    @Test
    public void testUri() {
        TemplateProcessorLiteral literal = new TemplateProcessorLiteral("uri", null);
        assertEquals("uri", literal.uri());
    }
}
