/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A composite processor.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public class CompositeProcessor<C, N> implements Processor<C, N> {

    /**
     * Stores the list of child processors.
     */
    protected List<Processor<C, N>> children;

    /**
     * Stores the parent processor.
     */
    protected Processor<C, N> parent;

    /**
     * Constructor.
     */
    public CompositeProcessor() {
        this.children = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param parent the parent processor.
     */
    public CompositeProcessor(Processor<C, N> parent) {
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    /**
     * Get the children.
     *
     * @return the children.
     */
    public List<Processor<C, N>> getChildren() {
        return children;
    }

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
     * @param context the context.
     * @param node the node.
     * @param index the index.
     */
    @Override
    public void process(C context, N node, int index) {
        Iterator<Processor<C, N>> iterator = children.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Processor<C, N> processor = iterator.next();
            processor.process(context, node, i);
            i++;
        }
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
