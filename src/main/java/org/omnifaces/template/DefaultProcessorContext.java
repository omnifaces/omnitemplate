/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.HashMap;
import java.util.Map;

/**
 * A default implementation of a processor context.
 */
public class DefaultProcessorContext implements ProcessorContext {

    /**
     * Stores the attributes.
     */
    protected Map<String, Object> attributes;

    /**
     * Constructor.
     */
    public DefaultProcessorContext() {
        attributes = new HashMap<>(2);
    }

    /**
     * Get the attribute.
     *
     * @param name the name.
     * @return the attribute or null if not found.
     */
    @Override
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * Set the attribute.
     *
     * @param name the name.
     * @param value the value.
     */
    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }
}
