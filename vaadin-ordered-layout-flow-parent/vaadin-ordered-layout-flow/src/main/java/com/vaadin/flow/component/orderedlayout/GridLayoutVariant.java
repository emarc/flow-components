/*
 * Copyright 2000-2025 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.orderedlayout;

import com.vaadin.flow.component.shared.ThemeVariant;

/**
 * Set of theme variants applicable for {@link GridLayout} component.
 */
public enum GridLayoutVariant implements ThemeVariant {
    /**
     * Adds margin around the layout (Lumo theme).
     */
    LUMO_MARGIN("margin"),
    /**
     * Adds padding inside the layout (Lumo theme).
     */
    LUMO_PADDING("padding"),
    /**
     * Adds extra-small spacing between grid items (Lumo theme).
     */
    LUMO_SPACING_XS("spacing-xs"),
    /**
     * Adds small spacing between grid items (Lumo theme).
     */
    LUMO_SPACING_S("spacing-s"),
    /**
     * Adds medium (default) spacing between grid items (Lumo theme).
     */
    LUMO_SPACING("spacing"),
    /**
     * Adds large spacing between grid items (Lumo theme).
     */
    LUMO_SPACING_L("spacing-l"),
    /**
     * Adds extra-large spacing between grid items (Lumo theme).
     */
    LUMO_SPACING_XL("spacing-xl"),
    /**
     * Adds margin around the layout (Aura theme).
     */
    AURA_MARGIN("margin"),
    /**
     * Adds padding inside the layout (Aura theme).
     */
    AURA_PADDING("padding"),
    /**
     * Adds spacing between grid items (Aura theme).
     */
    AURA_SPACING("spacing");

    private final String variant;

    GridLayoutVariant(String variant) {
        this.variant = variant;
    }

    /**
     * Gets the variant name.
     *
     * @return variant name
     */
    @Override
    public String getVariantName() {
        return variant;
    }
}
