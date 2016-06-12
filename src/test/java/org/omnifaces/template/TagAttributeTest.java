/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * The JUnit test for the TagAttribute class.
 */
public class TagAttributeTest {

    /**
     * Test getLocaleName method
     */
    @Test
    public void testGetLocalName() {
        TagAttribute tagAttribute = new TagAttribute(null, "localName", null);
        assertEquals("localName", tagAttribute.getLocalName());
    }

    /**
     * Test getLocalName method.
     */
    @Test
    public void testGetUri() {
        TagAttribute tagAttribute = new TagAttribute("uri", null, null);
        assertEquals("uri", tagAttribute.getUri());
    }

    /**
     * Test getValue method.
     */
    @Test
    public void testGetValue() {
        TagAttribute tagAttribute = new TagAttribute(null, null, "value");
        assertEquals("value", tagAttribute.getValue());
    }
}
