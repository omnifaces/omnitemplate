/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * The context in which a processor executes.
 */
public interface ProcessorContext {

    /**
     * Get the attribute.
     *
     * @param name the name.
     * @return the value.
     */
    Object getAttribute(String name);

    /**
     * Set the attribute.
     *
     * @param name the attribute name.
     * @param value the attribute value.
     */
    void setAttribute(String name, Object value);
}
