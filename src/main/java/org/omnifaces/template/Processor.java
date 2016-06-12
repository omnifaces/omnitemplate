/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * A processor.
 *
 * @param <C> the context.
 * @param <N> the node type.
 */
public interface Processor<C, N> {

    /**
     * Execute the processor.
     *
     * @param context the context.
     * @param node the node.
     * @param index the index.
     */
    void process(C context, N node, int index);

    /**
     * Get the parent processor.
     *
     * @return the parent, or null if not set.
     */
    Processor<C, N> getParent();

    /**
     * Set the parent.
     *
     * @param parent the parent.
     */
    void setParent(Processor<C, N> parent);
}
