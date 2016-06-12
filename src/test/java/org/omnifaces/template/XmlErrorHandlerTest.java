/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The JUnit tests for XmlErrorHandler.
 */
public class XmlErrorHandlerTest {

    /**
     * Test warning method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test(expected = SAXException.class)
    public void testWarning() throws SAXException {
        XmlErrorHandler handler = new XmlErrorHandler();
        handler.warning(new SAXParseException(null, null));
    }

    /**
     * Test error method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test(expected = SAXException.class)
    public void testError() throws SAXException {
        XmlErrorHandler handler = new XmlErrorHandler();
        handler.error(new SAXParseException(null, null));
    }

    /**
     * Test fatalError method.
     *
     * @throws SAXException when a parse error occurs.
     */
    @Test(expected = SAXException.class)
    public void testFatalError() throws SAXException {
        XmlErrorHandler handler = new XmlErrorHandler();
        handler.fatalError(new SAXParseException(null, null));
    }
}
