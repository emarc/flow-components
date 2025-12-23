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
import org.junit.Test;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.GridLayout;
import com.vaadin.flow.component.orderedlayout.GridPosition;

public class GridPositionTest {

    @Test
    public void builder_columnAndRow_createsPosition() {
        GridPosition pos = GridPosition.builder().column(2).row(3).build();
        Assert.assertEquals(Integer.valueOf(2), pos.getColumn());
        Assert.assertEquals(Integer.valueOf(3), pos.getRow());
    }

    @Test
    public void builder_withSpans_createsPosition() {
        GridPosition pos = GridPosition.builder().column(1).row(1).columnSpan(2)
                .rowSpan(3).build();
        Assert.assertEquals(Integer.valueOf(2), pos.getColumnSpan());
        Assert.assertEquals(Integer.valueOf(3), pos.getRowSpan());
    }

    @Test
    public void builder_withEnds_createsPosition() {
        GridPosition pos = GridPosition.builder().column(1).row(1).columnEnd(4)
                .rowEnd(3).build();
        Assert.assertEquals(Integer.valueOf(4), pos.getColumnEnd());
        Assert.assertEquals(Integer.valueOf(3), pos.getRowEnd());
    }

    @Test
    public void builder_emptyBuild_createsEmptyPosition() {
        GridPosition pos = GridPosition.builder().build();
        Assert.assertNull(pos.getColumn());
        Assert.assertNull(pos.getRow());
        Assert.assertNull(pos.getColumnSpan());
        Assert.assertNull(pos.getRowSpan());
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_zeroColumn_throws() {
        GridPosition.builder().column(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_negativeColumn_throws() {
        GridPosition.builder().column(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_zeroRow_throws() {
        GridPosition.builder().row(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_negativeRow_throws() {
        GridPosition.builder().row(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_zeroColumnSpan_throws() {
        GridPosition.builder().columnSpan(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void builder_zeroRowSpan_throws() {
        GridPosition.builder().rowSpan(0);
    }

    @Test(expected = IllegalStateException.class)
    public void builder_columnSpanAfterColumnEnd_throws() {
        GridPosition.builder().columnEnd(4).columnSpan(2);
    }

    @Test(expected = IllegalStateException.class)
    public void builder_columnEndAfterColumnSpan_throws() {
        GridPosition.builder().columnSpan(2).columnEnd(4);
    }

    @Test(expected = IllegalStateException.class)
    public void builder_rowSpanAfterRowEnd_throws() {
        GridPosition.builder().rowEnd(4).rowSpan(2);
    }

    @Test(expected = IllegalStateException.class)
    public void builder_rowEndAfterRowSpan_throws() {
        GridPosition.builder().rowSpan(2).rowEnd(4);
    }

    // Factory method tests
    @Test
    public void at_createsSimplePosition() {
        GridPosition pos = GridPosition.at(2, 3);
        Assert.assertEquals(Integer.valueOf(2), pos.getColumn());
        Assert.assertEquals(Integer.valueOf(3), pos.getRow());
        Assert.assertNull(pos.getColumnSpan());
        Assert.assertNull(pos.getRowSpan());
    }

    @Test
    public void spanning_createsPositionWithSpans() {
        GridPosition pos = GridPosition.spanning(1, 2, 3, 4);
        Assert.assertEquals(Integer.valueOf(1), pos.getColumn());
        Assert.assertEquals(Integer.valueOf(2), pos.getRow());
        Assert.assertEquals(Integer.valueOf(3), pos.getColumnSpan());
        Assert.assertEquals(Integer.valueOf(4), pos.getRowSpan());
    }

    // Equals and hashCode tests
    @Test
    public void equals_sameValues_returnsTrue() {
        GridPosition pos1 = GridPosition.at(1, 2);
        GridPosition pos2 = GridPosition.at(1, 2);
        Assert.assertEquals(pos1, pos2);
        Assert.assertEquals(pos1.hashCode(), pos2.hashCode());
    }

    @Test
    public void equals_differentColumn_returnsFalse() {
        GridPosition pos1 = GridPosition.at(1, 2);
        GridPosition pos2 = GridPosition.at(2, 2);
        Assert.assertNotEquals(pos1, pos2);
    }

    @Test
    public void equals_differentRow_returnsFalse() {
        GridPosition pos1 = GridPosition.at(1, 2);
        GridPosition pos2 = GridPosition.at(1, 3);
        Assert.assertNotEquals(pos1, pos2);
    }

    @Test
    public void equals_differentSpan_returnsFalse() {
        GridPosition pos1 = GridPosition.spanning(1, 1, 2, 2);
        GridPosition pos2 = GridPosition.spanning(1, 1, 3, 2);
        Assert.assertNotEquals(pos1, pos2);
    }

    @Test
    public void equals_null_returnsFalse() {
        GridPosition pos = GridPosition.at(1, 1);
        Assert.assertNotEquals(pos, null);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        GridPosition pos = GridPosition.at(1, 1);
        Assert.assertNotEquals(pos, "not a position");
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        GridPosition pos = GridPosition.at(1, 1);
        Assert.assertEquals(pos, pos);
    }

    // toString test
    @Test
    public void toString_containsAllFields() {
        GridPosition pos = GridPosition.spanning(1, 2, 3, 4);
        String str = pos.toString();
        Assert.assertTrue(str.contains("column=1"));
        Assert.assertTrue(str.contains("row=2"));
        Assert.assertTrue(str.contains("columnSpan=3"));
        Assert.assertTrue(str.contains("rowSpan=4"));
    }

    // CSS value tests via GridLayout.setPosition
    @Test
    public void setPosition_columnOnly_setsGridColumnStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(2).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("2",
                div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_columnWithSpan_setsGridColumnStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(1).columnSpan(3)
                .build();
        layout.setPosition(div, pos);
        Assert.assertEquals("1 / span 3",
                div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_spanOnly_setsGridColumnStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().columnSpan(2).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("span 2",
                div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_columnWithEnd_setsGridColumnStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(1).columnEnd(4)
                .build();
        layout.setPosition(div, pos);
        Assert.assertEquals("1 / 4",
                div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_columnEndOnly_setsAutoAndEnd() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().columnEnd(4).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("auto / 4",
                div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_rowOnly_setsGridRowStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().row(3).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("3", div.getElement().getStyle().get("grid-row"));
    }

    @Test
    public void setPosition_rowWithSpan_setsGridRowStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().row(1).rowSpan(2).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("1 / span 2",
                div.getElement().getStyle().get("grid-row"));
    }

    @Test
    public void setPosition_rowSpanOnly_setsGridRowStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().rowSpan(3).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("span 3",
                div.getElement().getStyle().get("grid-row"));
    }

    @Test
    public void setPosition_rowWithEnd_setsGridRowStyle() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().row(1).rowEnd(3).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("1 / 3",
                div.getElement().getStyle().get("grid-row"));
    }

    @Test
    public void setPosition_rowEndOnly_setsAutoAndEnd() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().rowEnd(5).build();
        layout.setPosition(div, pos);
        Assert.assertEquals("auto / 5",
                div.getElement().getStyle().get("grid-row"));
    }

    @Test
    public void setPosition_noColumnSet_doesNotSetGridColumn() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().row(1).build();
        layout.setPosition(div, pos);
        Assert.assertNull(div.getElement().getStyle().get("grid-column"));
    }

    @Test
    public void setPosition_noRowSet_doesNotSetGridRow() {
        GridLayout layout = new GridLayout();
        Div div = new Div();
        layout.add(div);
        GridPosition pos = GridPosition.builder().column(1).build();
        layout.setPosition(div, pos);
        Assert.assertNull(div.getElement().getStyle().get("grid-row"));
    }
}
