/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * A placeholder processor.
 *
 * <p>
 * Each instance of this processor should have been replaced by the time the
 * parsing is done.
 * </p>
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public class PlaceholderProcessor<C, N> implements Processor<C, N> {

    /**
     * Stores the parent.
     */
    private Processor<C, N> parent;

    /**
     * Get the parent.
     *
     * @return the parent.
     */
    @Override
    public Processor<C, N> getParent() {
        return parent;
    }

    /**
     * Execute the processor.
     *
     * <p>
     * As this processor is a place holder its process method will signal that
     * using this in an actual processor tree is 'Not supported".
     * </p>
     *
     * @param context the context.
     * @param node the node.
     * @param index the index.
     */
    @Override
    public void process(C context, N node, int index) {
        throw new UnsupportedOperationException("Not supported.");
    }

    /**
     * Set the parent.
     *
     * @param parent the parent.
     */
    @Override
    public void setParent(Processor<C, N> parent) {
        this.parent = parent;
    }
}
