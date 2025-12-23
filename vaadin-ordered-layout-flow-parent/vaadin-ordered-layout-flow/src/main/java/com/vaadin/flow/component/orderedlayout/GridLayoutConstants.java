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

import java.io.Serializable;

/**
 * CSS property constants for GridLayout.
 */
final class GridLayoutConstants implements Serializable {

    private GridLayoutConstants() {
        // Utility class
    }

    // Container properties
    static final String GRID_TEMPLATE_COLUMNS_CSS_PROPERTY = "gridTemplateColumns";
    static final String GRID_TEMPLATE_ROWS_CSS_PROPERTY = "gridTemplateRows";
    static final String GRID_TEMPLATE_AREAS_CSS_PROPERTY = "gridTemplateAreas";
    static final String GAP_CSS_PROPERTY = "gap";
    static final String ROW_GAP_CSS_PROPERTY = "rowGap";
    static final String COLUMN_GAP_CSS_PROPERTY = "columnGap";
    static final String JUSTIFY_ITEMS_CSS_PROPERTY = "justifyItems";
    static final String ALIGN_ITEMS_CSS_PROPERTY = "alignItems";
    static final String JUSTIFY_CONTENT_CSS_PROPERTY = "justifyContent";
    static final String ALIGN_CONTENT_CSS_PROPERTY = "alignContent";
    static final String GRID_AUTO_ROWS_CSS_PROPERTY = "gridAutoRows";
    static final String GRID_AUTO_COLUMNS_CSS_PROPERTY = "gridAutoColumns";
    static final String GRID_AUTO_FLOW_CSS_PROPERTY = "gridAutoFlow";

    // Item properties
    static final String GRID_COLUMN_CSS_PROPERTY = "gridColumn";
    static final String GRID_ROW_CSS_PROPERTY = "gridRow";
    static final String GRID_AREA_CSS_PROPERTY = "gridArea";
    static final String JUSTIFY_SELF_CSS_PROPERTY = "justifySelf";
    static final String ALIGN_SELF_CSS_PROPERTY = "alignSelf";

}
