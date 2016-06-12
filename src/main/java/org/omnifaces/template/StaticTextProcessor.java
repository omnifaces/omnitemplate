/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * A processor for static text.
 *
 * @param <C> the context type.
 * @param <N> the node type.
 */
public abstract class StaticTextProcessor<C extends ProcessorContext, N> implements Processor<C, N> {

    /**
     * Stores the parent processor.
     */
    protected Processor<C, N> parent;

    /**
     * Stores the string builder.
     */
    protected StringBuilder stringBuilder;

    /**
     * Constructor.
     */
    public StaticTextProcessor() {
    }

    /**
     * Get the parent processor.
     *
     * @return the parent processor.
     */
    @Override
    public Processor<C, N> getParent() {
        return parent;
    }

    /**
     * Get the string builder.
     *
     * @return the string builder.
     */
    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    /**
     * Get the text.
     *
     * @return the text.
     */
    public String getText() {
        return stringBuilder.toString();
    }

    /**
     * Execute the processor.
     *
     * @param context the context.
     * @param node the node.
     * @param index the index.
     */
    @Override
    public abstract void process(C context, N node, int index);

    /**
     * Set the parent.
     *
     * @param parent the parent.
     */
    @Override
    public void setParent(Processor<C, N> parent) {
        this.parent = parent;
    }

    /**
     * Set the text.
     *
     * @param text the text.
     */
    public final void setText(String text) {
        stringBuilder = new StringBuilder();
        stringBuilder.append(text);
    }
}
