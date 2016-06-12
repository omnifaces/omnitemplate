/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import java.util.List;

/**
 * A tree node.
 *
 * @param <N> the node type.
 */
public interface TreeNode<N> {

    /**
     * Get the children.
     *
     * @return the children
     */
    List<N> getChildren();

    /**
     * Set the parent.
     *
     * @param parent the parent.
     */
    void setParent(N parent);
}
