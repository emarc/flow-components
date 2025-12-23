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
package com.vaadin.flow.component.orderedlayout.testbench;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * A TestBench element representing a {@code GridLayout} component.
 * <p>
 * Since GridLayout uses a standard {@code <div>} element with
 * {@code display: grid}, this element matches by the div tag. To locate
 * specific GridLayout instances:
 * <ul>
 * <li>Use an ID selector: {@code $(GridLayoutElement.class).id("my-grid")}</li>
 * <li>Use class selector:
 * {@code $("div").withAttributeContainingWord("class", "vaadin-grid-layout")}</li>
 * </ul>
 * <p>
 * All GridLayout instances have the CSS class {@code vaadin-grid-layout}.
 */
@Element("div")
public class GridLayoutElement extends TestBenchElement {

    /**
     * CSS class name used by all GridLayout components.
     */
    public static final String GRID_LAYOUT_CLASS = "vaadin-grid-layout";

    /**
     * Gets the computed grid-template-columns value.
     *
     * @return the grid-template-columns CSS value
     */
    public String getGridTemplateColumns() {
        return getCssValue("grid-template-columns");
    }

    /**
     * Gets the computed grid-template-rows value.
     *
     * @return the grid-template-rows CSS value
     */
    public String getGridTemplateRows() {
        return getCssValue("grid-template-rows");
    }

    /**
     * Gets the computed gap value.
     *
     * @return the gap CSS value
     */
    public String getGap() {
        return getCssValue("gap");
    }

    /**
     * Checks if this element is using display: grid.
     *
     * @return true if display is grid
     */
    public boolean isGridDisplay() {
        return "grid".equals(getCssValue("display"));
    }
}
