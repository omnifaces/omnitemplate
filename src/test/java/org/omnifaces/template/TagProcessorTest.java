/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit tests for TagProcessor.
 */
public class TagProcessorTest {

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor() {
        Map<String, TagAttribute> attributes = new HashMap<>();
        TagProcessor processor = new TagProcessor(attributes);
        assertEquals(attributes, processor.getAttributes());
    }

    /**
     * Test getAttributes method.
     */
    @Test
    public void testGetAttributes() {
        TagProcessor processor = new TagProcessor();
        assertNotNull(processor.getAttributes());
    }

    /**
     * Test setAttributes method.
     */
    @Test
    public void testSetAttributes() {
        Map<String, TagAttribute> attributes = new HashMap<>();
        TagProcessor processor = new TagProcessor();
        processor.setAttributes(attributes);
        assertEquals(attributes, processor.getAttributes());
    }
}
