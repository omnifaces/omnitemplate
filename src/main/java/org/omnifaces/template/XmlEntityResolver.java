/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The SAX Entity resolver.
 */
public class XmlEntityResolver implements EntityResolver {

    /**
     * Resolve the entity.
     *
     * @param publicId the public id.
     * @param systemId the system id.
     * @return the input source.
     * @throws SAXException when a SAX error occurs.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        InputSource result = null;

        if (publicId != null) {
            switch (publicId) {
                case "-//W3C//DTD XHTML 1.0 Transitional//EN":
                    result = new InputSource(getClass().getResourceAsStream(
                            "/org/omnifaces/template/xhtml1-transitional.dtd"));
                    break;
                case "-//W3C//ENTITIES Latin 1 for XHTML//EN":
                    result = new InputSource(getClass().getResourceAsStream(
                            "/org/omnifaces/template/xhtml-lat1.ent"));
                    break;
                case "-//W3C//ENTITIES Symbols for XHTML//EN":
                    result = new InputSource(getClass().getResourceAsStream(
                            "/org/omnifaces/template/xhtml-symbol.ent"));
                    break;
                case "-//W3C//ENTITIES Special for XHTML//EN":
                    result = new InputSource(getClass().getResourceAsStream(
                            "/org/omnifaces/template/xhtml-special.ent"));
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
