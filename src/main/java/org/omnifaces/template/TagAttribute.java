/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

/**
 * A tag attribute.
 */
public class TagAttribute {

    /**
     * Stores the local name.
     */
    private final String localName;

    /**
     * Stores the URI.
     */
    private final String uri;

    /**
     * Stores the value.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param uri the URI.
     * @param localName the local name.
     * @param value the value.
     */
    public TagAttribute(String uri, String localName, String value) {
        this.uri = uri;
        this.localName = localName;
        this.value = value;
    }

    /**
     * Get the local name.
     *
     * @return the local name.
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * Get the URI.
     *
     * @return the URI.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Get the value.
     *
     * @return the value.
     */
    public String getValue() {
        return value;
    }
}
