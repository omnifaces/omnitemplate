/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * A tag processor factory.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public interface TagProcessorFactory<C, N> {

    /**
     * Get the tag processor.
     *
     * @param uri the URI.
     * @param localName the local name.
     * @return the tag processor.
     */
    TagProcessor<C, N> getTagProcessor(String uri, String localName);

    /**
     * Does the tag processor factory have a tag processor for this?
     *
     * @param uri the URI.
     * @param localName the local name.
     * @return true if we have a tag processor, false otherwise.
     */
    boolean hasTagProcessor(String uri, String localName);
}
