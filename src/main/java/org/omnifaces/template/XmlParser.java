/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.io.InputStream;

/**
 * The xml parser interface.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public interface XmlParser<C, N> {

    /**
     * Parse the XML.
     *
     * @param xml the XML.
     * @return the processor.
     */
    Processor<C, N> parse(String xml);

    /**
     * Parse the input stream.
     *
     * @param inputStream the input stream.
     * @return the processor.
     */
    Processor<C, N> parse(InputStream inputStream);
}
