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

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.GridLayout;
import com.vaadin.flow.component.orderedlayout.GridPosition;

public class GridLayoutTest {

    private GridLayout layout;

    @Before
    public void setup() {
        layout = new GridLayout();
    }

    // Constructor tests
    @Test
    public void constructor_setsDisplayGrid() {
        Assert.assertEquals("grid", layout.getStyle().get("display"));
    }

    @Test
    public void constructorWithChildren_addsChildrenAndSetsDisplayGrid() {
        Div div1 = new Div();
        Div div2 = new Div();
        GridLayout layout = new GridLayout(div1, div2);
        Assert.assertEquals("grid", layout.getStyle().get("display"));
        Assert.assertEquals(2, layout.getComponentCount());
    }

    // Column template tests
    @Test
    public void setColumns_stringTemplate_setsProperty() {
        layout.setColumns("1fr 2fr 1fr");
        Assert.assertEquals("1fr 2fr 1fr", layout.getColumns());
    }

    @Test
    public void setColumns_count_createsRepeatTemplate() {
        layout.setColumns(3);
        Assert.assertEquals("repeat(3, 1fr)", layout.getColumns());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setColumns_invalidCount_throws() {
        layout.setColumns(0);
    }

    @Test
    public void setColumns_null_removesProperty() {
        layout.setColumns("1fr 1fr");
        layout.setColumns(null);
        Assert.assertNull(layout.getColumns());
    }

    // Row template tests
    @Test
    public void setRows_stringTemplate_setsProperty() {
        layout.setRows("auto 1fr auto");
        Assert.assertEquals("auto 1fr auto", layout.getRows());
    }

    @Test
    public void setRows_count_createsRepeatTemplate() {
        layout.setRows(2);
        Assert.assertEquals("repeat(2, 1fr)", layout.getRows());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setRows_invalidCount_throws() {
        layout.setRows(0);
    }

    @Test
    public void setRows_null_removesProperty() {
        layout.setRows("auto auto");
        layout.setRows(null);
        Assert.assertNull(layout.getRows());
    }

    // Template areas tests
    @Test
    public void setTemplateAreas_multipleRows_formatsCorrectly() {
        layout.setTemplateAreas("header header", "main sidebar");
        String expected = "\"header header\" \"main sidebar\"";
        Assert.assertEquals(expected, layout.getTemplateAreas());
    }

    @Test
    public void setTemplateAreas_singleRow_formatsCorrectly() {
        layout.setTemplateAreas("header");
        Assert.assertEquals("\"header\"", layout.getTemplateAreas());
    }

    @Test
    public void setTemplateAreas_null_removesProperty() {
        layout.setTemplateAreas("header header");
        layout.setTemplateAreas((String[]) null);
        Assert.assertNull(layout.getTemplateAreas());
    }

    @Test
    public void setTemplateAreas_emptyArray_removesProperty() {
        layout.setTemplateAreas("header header");
        layout.setTemplateAreas(new String[0]);
        Assert.assertNull(layout.getTemplateAreas());
    }

    // Gap tests
    @Test
    public void setGap_setsProperty() {
        layout.setGap("10px");
        Assert.assertEquals("10px", layout.getGap());
    }

    @Test
    public void setGap_null_removesProperty() {
        layout.setGap("10px");
        layout.setGap(null);
        Assert.assertNull(layout.getGap());
    }

    @Test
    public void setColumnGap_setsProperty() {
        layout.setColumnGap("20px");
        Assert.assertEquals("20px", layout.getColumnGap());
    }

    @Test
    public void setRowGap_setsProperty() {
        layout.setRowGap("15px");
        Assert.assertEquals("15px", layout.getRowGap());
    }

    // Alignment tests
    @Test
    public void setJustifyItems_setsProperty() {
        layout.setJustifyItems(GridLayout.ItemAlignment.CENTER);
        Assert.assertEquals(GridLayout.ItemAlignment.CENTER,
                layout.getJustifyItems());
    }

    @Test
    public void setJustifyItems_null_removesProperty() {
        layout.setJustifyItems(GridLayout.ItemAlignment.CENTER);
        layout.setJustifyItems(null);
        Assert.assertEquals(GridLayout.ItemAlignment.STRETCH,
                layout.getJustifyItems());
    }

    @Test
    public void setAlignItems_setsProperty() {
        layout.setAlignItems(GridLayout.ItemAlignment.END);
        Assert.assertEquals(GridLayout.ItemAlignment.END,
                layout.getAlignItems());
    }

    @Test
    public void setAlignItems_null_removesProperty() {
        layout.setAlignItems(GridLayout.ItemAlignment.CENTER);
        layout.setAlignItems(null);
        Assert.assertEquals(GridLayout.ItemAlignment.STRETCH,
                layout.getAlignItems());
    }

    @Test
    public void setJustifyContent_setsProperty() {
        layout.setJustifyContent(GridLayout.ContentAlignment.SPACE_BETWEEN);
        Assert.assertEquals(GridLayout.ContentAlignment.SPACE_BETWEEN,
                layout.getJustifyContent());
    }

    @Test
    public void setJustifyContent_null_returnsDefaultNormal() {
        layout.setJustifyContent(GridLayout.ContentAlignment.CENTER);
        layout.setJustifyContent(null);
        Assert.assertEquals(GridLayout.ContentAlignment.NORMAL,
                layout.getJustifyContent());
    }

    @Test
    public void setAlignContent_setsProperty() {
        layout.setAlignContent(GridLayout.ContentAlignment.SPACE_AROUND);
        Assert.assertEquals(GridLayout.ContentAlignment.SPACE_AROUND,
                layout.getAlignContent());
    }

    @Test
    public void setAlignContent_null_returnsDefaultNormal() {
        layout.setAlignContent(GridLayout.ContentAlignment.CENTER);
        layout.setAlignContent(null);
        Assert.assertEquals(GridLayout.ContentAlignment.NORMAL,
                layout.getAlignContent());
    }

    // Auto flow tests
    @Test
    public void setAutoFlow_setsProperty() {
        layout.setAutoFlow(GridLayout.AutoFlow.COLUMN_DENSE);
        Assert.assertEquals(GridLayout.AutoFlow.COLUMN_DENSE,
                layout.getAutoFlow());
    }

    @Test
    public void setAutoFlow_null_returnsDefaultRow() {
        layout.setAutoFlow(GridLayout.AutoFlow.COLUMN);
        layout.setAutoFlow(null);
        Assert.assertEquals(GridLayout.AutoFlow.ROW, layout.getAutoFlow());
    }

    @Test
    public void setAutoRows_setsProperty() {
        layout.setAutoRows("minmax(100px, auto)");
        Assert.assertEquals("minmax(100px, auto)", layout.getAutoRows());
    }

    @Test
    public void setAutoRows_null_removesProperty() {
        layout.setAutoRows("100px");
        layout.setAutoRows(null);
        Assert.assertNull(layout.getAutoRows());
    }

    @Test
    public void setAutoColumns_setsProperty() {
        layout.setAutoColumns("1fr");
        Assert.assertEquals("1fr", layout.getAutoColumns());
    }

    // Position tests
    @Test
    public void setPosition_setsGridColumnAndRow() {
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(2).row(1).columnSpan(2)
                .build();
        layout.setPosition(div, pos);

        Assert.assertEquals("2 / span 2",
                div.getElement().getStyle().get("gridColumn"));
        Assert.assertEquals("1", div.getElement().getStyle().get("gridRow"));
    }

    @Test
    public void setPosition_columnOnly_setsOnlyGridColumn() {
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(3).build();
        layout.setPosition(div, pos);

        Assert.assertEquals("3", div.getElement().getStyle().get("gridColumn"));
        Assert.assertNull(div.getElement().getStyle().get("gridRow"));
    }

    @Test
    public void clearPosition_removesAllPositionStyles() {
        Div div = new Div();
        layout.add(div);
        layout.setPosition(div, GridPosition.at(1, 1));
        layout.setArea(div, "header");
        layout.clearPosition(div);

        Assert.assertNull(div.getElement().getStyle().get("gridColumn"));
        Assert.assertNull(div.getElement().getStyle().get("gridRow"));
        Assert.assertNull(div.getElement().getStyle().get("gridArea"));
    }

    // Area tests
    @Test
    public void setArea_setsGridAreaProperty() {
        Div div = new Div();
        layout.add(div);
        layout.setArea(div, "header");
        Assert.assertEquals("header", layout.getArea(div));
    }

    @Test
    public void setArea_null_removesProperty() {
        Div div = new Div();
        layout.add(div);
        layout.setArea(div, "header");
        layout.setArea(div, null);
        Assert.assertNull(layout.getArea(div));
    }

    @Test
    public void setArea_emptyString_removesProperty() {
        Div div = new Div();
        layout.add(div);
        layout.setArea(div, "header");
        layout.setArea(div, "");
        Assert.assertNull(layout.getArea(div));
    }

    // Self-alignment tests
    @Test
    public void setJustifySelf_setsPropertyOnComponent() {
        Div div = new Div();
        layout.add(div);
        layout.setJustifySelf(GridLayout.SelfAlignment.CENTER, div);
        Assert.assertEquals(GridLayout.SelfAlignment.CENTER,
                layout.getJustifySelf(div));
    }

    @Test
    public void setJustifySelf_null_removesProperty() {
        Div div = new Div();
        layout.add(div);
        layout.setJustifySelf(GridLayout.SelfAlignment.CENTER, div);
        layout.setJustifySelf(null, div);
        Assert.assertEquals(GridLayout.SelfAlignment.AUTO,
                layout.getJustifySelf(div));
    }

    @Test
    public void setJustifySelf_multipleComponents_setsPropertyOnAll() {
        Div div1 = new Div();
        Div div2 = new Div();
        layout.add(div1, div2);
        layout.setJustifySelf(GridLayout.SelfAlignment.END, div1, div2);
        Assert.assertEquals(GridLayout.SelfAlignment.END,
                layout.getJustifySelf(div1));
        Assert.assertEquals(GridLayout.SelfAlignment.END,
                layout.getJustifySelf(div2));
    }

    @Test
    public void setAlignSelf_setsPropertyOnComponent() {
        Div div = new Div();
        layout.add(div);
        layout.setAlignSelf(GridLayout.SelfAlignment.END, div);
        Assert.assertEquals(GridLayout.SelfAlignment.END,
                layout.getAlignSelf(div));
    }

    @Test
    public void setAlignSelf_null_removesProperty() {
        Div div = new Div();
        layout.add(div);
        layout.setAlignSelf(GridLayout.SelfAlignment.CENTER, div);
        layout.setAlignSelf(null, div);
        Assert.assertEquals(GridLayout.SelfAlignment.AUTO,
                layout.getAlignSelf(div));
    }

    // Null validation tests
    @Test(expected = NullPointerException.class)
    public void setPosition_nullComponent_throws() {
        layout.setPosition(null, GridPosition.at(1, 1));
    }

    @Test(expected = NullPointerException.class)
    public void setPosition_nullPosition_throws() {
        Div div = new Div();
        layout.setPosition(div, null);
    }

    @Test(expected = NullPointerException.class)
    public void clearPosition_nullComponent_throws() {
        layout.clearPosition(null);
    }

    @Test(expected = NullPointerException.class)
    public void setArea_nullComponent_throws() {
        layout.setArea(null, "header");
    }

    // Wrap tests (not supported for CSS Grid)
    @Test(expected = UnsupportedOperationException.class)
    public void setWrap_throws() {
        layout.setWrap(true);
    }

    @Test
    public void isWrap_returnsFalse() {
        Assert.assertFalse(layout.isWrap());
    }
}
