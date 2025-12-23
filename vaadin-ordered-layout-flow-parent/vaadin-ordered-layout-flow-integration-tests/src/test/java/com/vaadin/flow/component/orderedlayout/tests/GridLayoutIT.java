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
package com.vaadin.flow.component.orderedlayout.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.testutil.TestPath;
import com.vaadin.tests.AbstractComponentIT;

/**
 * Integration tests for {@link GridLayout}.
 */
@TestPath("vaadin-ordered-layout/grid-layout-tests")
public class GridLayoutIT extends AbstractComponentIT {

    @Before
    public void init() {
        open();
    }

    @Test
    public void gridLayout_hasDisplayGrid() {
        WebElement grid = findElement(By.id("columns-grid"));
        Assert.assertEquals("grid", grid.getCssValue("display"));
    }

    @Test
    public void setColumns_updatesGridTemplateColumns() {
        WebElement grid = findElement(By.id("columns-grid"));

        // Initial state - check it contains the expected values
        String initialColumns = grid.getCssValue("grid-template-columns");
        Assert.assertTrue("Initial columns should contain pixel values",
                initialColumns.contains("px"));

        // Click button to change to equal columns
        findElement(By.id("set-columns-btn")).click();
        waitUntil(d -> {
            String columns = grid.getCssValue("grid-template-columns");
            // After setting 3 equal columns, should have 3 equal fr values
            return !columns.contains("200px");
        });
    }

    @Test
    public void getColumns_returnsCorrectValue() {
        findElement(By.id("get-columns-btn")).click();
        WebElement display = findElement(By.id("columns-display"));
        Assert.assertEquals("100px 200px 100px", display.getText());
    }

    @Test
    public void setGap_updatesGapProperty() {
        WebElement grid = findElement(By.id("gap-grid"));
        findElement(By.id("set-gap-btn")).click();
        waitUntil(d -> "20px".equals(grid.getCssValue("gap"))
                || "20px 20px".equals(grid.getCssValue("gap")));
    }

    @Test
    public void setColumnGap_updatesColumnGapProperty() {
        WebElement grid = findElement(By.id("gap-grid"));
        findElement(By.id("set-column-gap-btn")).click();
        waitUntil(d -> {
            String columnGap = grid.getCssValue("column-gap");
            return "30px".equals(columnGap);
        });
    }

    @Test
    public void setRowGap_updatesRowGapProperty() {
        WebElement grid = findElement(By.id("gap-grid"));
        findElement(By.id("set-row-gap-btn")).click();
        waitUntil(d -> {
            String rowGap = grid.getCssValue("row-gap");
            return "15px".equals(rowGap);
        });
    }

    @Test
    public void setAlignment_updatesAlignmentProperties() {
        WebElement grid = findElement(By.id("align-grid"));
        findElement(By.id("center-btn")).click();
        waitUntil(d -> "center".equals(grid.getCssValue("justify-items")));
        Assert.assertEquals("center", grid.getCssValue("align-items"));
    }

    @Test
    public void setStretchAlignment_updatesAlignmentProperties() {
        WebElement grid = findElement(By.id("align-grid"));
        // First center, then stretch
        findElement(By.id("center-btn")).click();
        waitUntil(d -> "center".equals(grid.getCssValue("justify-items")));

        findElement(By.id("stretch-btn")).click();
        waitUntil(d -> "stretch".equals(grid.getCssValue("justify-items"))
                || "normal".equals(grid.getCssValue("justify-items")));
    }

    @Test
    public void setJustifyContent_updatesProperty() {
        WebElement grid = findElement(By.id("align-grid"));
        findElement(By.id("set-justify-content-btn")).click();
        waitUntil(d -> "space-between"
                .equals(grid.getCssValue("justify-content")));
    }

    @Test
    public void setPosition_updatesItemGridColumn() {
        WebElement item = findElement(By.id("pos-item"));
        findElement(By.id("set-pos-btn")).click();

        waitUntil(d -> {
            String gridColumn = item.getCssValue("grid-column");
            // CSS may normalize the value differently in different browsers
            return gridColumn != null && !gridColumn.isEmpty()
                    && !"auto".equals(gridColumn);
        });
    }

    @Test
    public void clearPosition_removesItemGridColumn() {
        WebElement item = findElement(By.id("pos-item"));

        // First set position
        findElement(By.id("set-pos-btn")).click();
        waitUntil(d -> {
            String gridColumn = item.getCssValue("grid-column");
            return gridColumn != null && !gridColumn.isEmpty()
                    && !"auto".equals(gridColumn);
        });

        // Then clear it
        findElement(By.id("clear-pos-btn")).click();
        waitUntil(d -> {
            String gridColumn = item.getCssValue("grid-column");
            return "auto".equals(gridColumn) || "auto / auto".equals(gridColumn)
                    || gridColumn == null || gridColumn.isEmpty();
        });
    }

    @Test
    public void setArea_updatesItemGridArea() {
        WebElement item = findElement(By.id("area-item"));
        findElement(By.id("set-area-btn")).click();

        waitUntil(d -> {
            String gridArea = item.getCssValue("grid-area");
            return gridArea != null && gridArea.contains("a");
        });
    }

    @Test
    public void setArea_changesArea() {
        WebElement item = findElement(By.id("area-item"));

        // Set to area 'a'
        findElement(By.id("set-area-btn")).click();
        waitUntil(d -> {
            String gridArea = item.getCssValue("grid-area");
            return gridArea != null && gridArea.contains("a");
        });

        // Set to area 'b'
        findElement(By.id("set-area-b-btn")).click();
        waitUntil(d -> {
            String gridArea = item.getCssValue("grid-area");
            return gridArea != null && gridArea.contains("b");
        });
    }

    @Test
    public void getArea_returnsCorrectValue() {
        findElement(By.id("set-area-btn")).click();
        // Wait for area to be set
        waitUntil(d -> {
            WebElement item = findElement(By.id("area-item"));
            String gridArea = item.getCssValue("grid-area");
            return gridArea != null && gridArea.contains("a");
        });

        findElement(By.id("get-area-btn")).click();
        WebElement display = findElement(By.id("area-display"));
        Assert.assertEquals("a", display.getText());
    }

    @Test
    public void themeVariant_addsSpacingTheme() {
        WebElement grid = findElement(By.id("theme-grid"));

        findElement(By.id("add-spacing-btn")).click();
        waitUntil(d -> {
            String theme = grid.getDomAttribute("theme");
            return theme != null && theme.contains("spacing");
        });
    }

    @Test
    public void themeVariant_removesSpacingTheme() {
        WebElement grid = findElement(By.id("theme-grid"));

        // First add
        findElement(By.id("add-spacing-btn")).click();
        waitUntil(d -> {
            String theme = grid.getDomAttribute("theme");
            return theme != null && theme.contains("spacing");
        });

        // Then remove
        findElement(By.id("remove-spacing-btn")).click();
        waitUntil(d -> {
            String theme = grid.getDomAttribute("theme");
            return theme == null || !theme.contains("spacing");
        });
    }

    @Test
    public void themeVariant_addsPaddingTheme() {
        WebElement grid = findElement(By.id("theme-grid"));

        findElement(By.id("add-padding-btn")).click();
        waitUntil(d -> {
            String theme = grid.getDomAttribute("theme");
            return theme != null && theme.contains("padding");
        });
    }
}
