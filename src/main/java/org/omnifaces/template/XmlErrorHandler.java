/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The SAX ErrorHandler.
 */
public class XmlErrorHandler implements ErrorHandler {

    /**
     * Handle the warning.
     *
     * @param saxpe the SAX parse error.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    public void warning(SAXParseException saxpe) throws SAXException {
        throw saxpe;
    }

    /**
     * Handle the error.
     *
     * @param saxpe the SAX parse error.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    public void error(SAXParseException saxpe) throws SAXException {
        throw saxpe;
    }

    /**
     * Handle the fatal error.
     *
     * @param saxpe the SAX parse error.
     * @throws SAXException when a SAX error occurs.
     */
    @Override
    public void fatalError(SAXParseException saxpe) throws SAXException {
        throw saxpe;
    }
}
