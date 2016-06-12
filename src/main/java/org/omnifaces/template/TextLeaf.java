/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * The interface of a text leaf.
 *
 * @param <N> the node type.
 */
public interface TextLeaf<N> extends TreeNode<N> {

    /**
     * Get the text.
     *
     * @return the text.
     */
    String getText();

    /**
     * Set the text.
     *
     * @param text the text.
     */
    void setText(String text);
}
