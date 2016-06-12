/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The JUnit tests for XmlEntityResolver.
 */
public class XmlEntityResolverTest {

    /**
     * Test resolveEntity method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testResolveEntity() throws Exception {
        XmlEntityResolver resolver = new XmlEntityResolver();
        assertNull(resolver.resolveEntity(null, null));
    }

    /**
     * Test resolveEntity method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testResolveEntity2() throws Exception {
        XmlEntityResolver resolver = new XmlEntityResolver();
        assertNotNull(resolver.resolveEntity("-//W3C//DTD XHTML 1.0 Transitional//EN", null));
    }

    /**
     * Test resolveEntity method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testResolveEntity3() throws Exception {
        XmlEntityResolver resolver = new XmlEntityResolver();
        assertNotNull(resolver.resolveEntity("-//W3C//ENTITIES Latin 1 for XHTML//EN", null));
    }

    /**
     * Test resolveEntity method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testResolveEntity4() throws Exception {
        XmlEntityResolver resolver = new XmlEntityResolver();
        assertNotNull(resolver.resolveEntity("-//W3C//ENTITIES Symbols for XHTML//EN", null));
    }

    /**
     * Test resolveEntity method.
     *
     * @throws Exception when a serious error occurs.
     */
    @Test
    public void testResolveEntity5() throws Exception {
        XmlEntityResolver resolver = new XmlEntityResolver();
        assertNotNull(resolver.resolveEntity("-//W3C//ENTITIES Special for XHTML//EN", null));
    }
}
