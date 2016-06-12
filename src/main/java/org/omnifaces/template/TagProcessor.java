/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.HashMap;
import java.util.Map;

/**
 * A tag processor.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public class TagProcessor<C, N> extends CompositeProcessor<C, N> {

    /**
     * Stores the tag attributes.
     */
    protected Map<String, TagAttribute> attributes;

    /**
     * Constructor.
     */
    public TagProcessor() {
        this.attributes = new HashMap<>(2);
    }

    /**
     * Constructor.
     *
     * @param attributes the tag attributes
     */
    public TagProcessor(Map<String, TagAttribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Get the tag attributes.
     *
     * @return the tag attributes.
     */
    public Map<String, TagAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Set the tag attributes.
     *
     * @param attributes the tag attributes.
     */
    public void setAttributes(Map<String, TagAttribute> attributes) {
        this.attributes = attributes;
    }
}
