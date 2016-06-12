/*
 * Copyright (c) 2016 OnniFaces.org. All Rights Reserved.
 */
package org.omnifaces.template;

import javax.enterprise.util.AnnotationLiteral;

/**
 * The annotation literal for @TemplateProcessor.
 */
@SuppressWarnings("all")
public class TemplateProcessorLiteral extends AnnotationLiteral<TemplateProcessor> implements TemplateProcessor {

    /**
     * Stores the serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Stores the local name.
     */
    private String localName;

    /**
     * Stores the uri.
     */
    private String uri;

    /**
     * Constructor.
     *
     * @param uri the URI.
     * @param localName the local name.
     */
    public TemplateProcessorLiteral(String uri, String localName) {
        this.uri = uri;
        this.localName = localName;
    }

    /**
     * Get the local name.
     *
     * @return the local name.
     */
    public String localName() {
        return localName;
    }

    /**
     * Get the URI.
     *
     * @return the URI.
     */
    public String uri() {
        return uri;
    }
}
