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
package com.vaadin.flow.component.orderedlayout.it;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.GridLayout;
import com.vaadin.flow.component.orderedlayout.GridPosition;
import com.vaadin.flow.router.Route;

/**
 * Integration test view for {@link GridLayout} component.
 */
@Route("vaadin-ordered-layout/grid-layout-tests")
public class GridLayoutITView extends Div {

    public GridLayoutITView() {
        createColumnsTest();
        createGapTest();
        createAlignmentTest();
        createPositionTest();
        createAreaTest();
        createThemeTest();
    }

    private void createColumnsTest() {
        GridLayout grid = new GridLayout();
        grid.setId("columns-grid");
        grid.setColumns("100px 200px 100px");
        grid.add(createDiv("#78909C"), createDiv("#546E7A"),
                createDiv("#37474F"));

        NativeButton setColumnsBtn = new NativeButton("Set 3 equal columns",
                e -> grid.setColumns(3));
        setColumnsBtn.setId("set-columns-btn");

        Span columnsDisplay = new Span();
        columnsDisplay.setId("columns-display");
        NativeButton getColumnsBtn = new NativeButton("Get columns",
                e -> columnsDisplay.setText(String.valueOf(grid.getColumns())));
        getColumnsBtn.setId("get-columns-btn");

        add(grid, setColumnsBtn, getColumnsBtn, columnsDisplay);
    }

    private void createGapTest() {
        GridLayout grid = new GridLayout();
        grid.setId("gap-grid");
        grid.setColumns(2);
        for (int i = 0; i < 4; i++) {
            Div div = createDiv("#78909C");
            div.setWidth("50px");
            div.setHeight("50px");
            grid.add(div);
        }

        NativeButton setGapBtn = new NativeButton("Set gap 20px",
                e -> grid.setGap("20px"));
        setGapBtn.setId("set-gap-btn");

        NativeButton setColumnGapBtn = new NativeButton("Set column gap 30px",
                e -> grid.setColumnGap("30px"));
        setColumnGapBtn.setId("set-column-gap-btn");

        NativeButton setRowGapBtn = new NativeButton("Set row gap 15px",
                e -> grid.setRowGap("15px"));
        setRowGapBtn.setId("set-row-gap-btn");

        add(grid, setGapBtn, setColumnGapBtn, setRowGapBtn);
    }

    private void createAlignmentTest() {
        Div section = new Div();
        section.getStyle().set("marginTop", "20px").set("marginBottom", "20px");
        section.add(new Span("Alignment Test:"));

        GridLayout grid = new GridLayout();
        grid.setId("align-grid");
        grid.setColumns("1fr 1fr"); // Use fractional units so cells have size
        grid.setRows("100px 100px"); // Fixed row height for visual clarity
        grid.setWidth("400px");
        grid.getStyle().set("border", "2px solid #1976D2");
        grid.getStyle().set("backgroundColor", "#E3F2FD");

        // Items WITHOUT fixed size so stretch is visible
        for (int i = 0; i < 4; i++) {
            Div div = createDiv(i % 2 == 0 ? "#1976D2" : "#42A5F5");
            div.setText("Item " + (i + 1));
            div.getStyle().set("padding", "8px");
            // No width/height set - will be affected by alignment
            grid.add(div);
        }

        NativeButton centerBtn = new NativeButton("Center items", e -> {
            grid.setJustifyItems(GridLayout.ItemAlignment.CENTER);
            grid.setAlignItems(GridLayout.ItemAlignment.CENTER);
        });
        centerBtn.setId("center-btn");

        NativeButton stretchBtn = new NativeButton("Stretch items", e -> {
            grid.setJustifyItems(GridLayout.ItemAlignment.STRETCH);
            grid.setAlignItems(GridLayout.ItemAlignment.STRETCH);
        });
        stretchBtn.setId("stretch-btn");

        NativeButton startBtn = new NativeButton("Start items", e -> {
            grid.setJustifyItems(GridLayout.ItemAlignment.START);
            grid.setAlignItems(GridLayout.ItemAlignment.START);
        });
        startBtn.setId("start-btn");

        NativeButton endBtn = new NativeButton("End items", e -> {
            grid.setJustifyItems(GridLayout.ItemAlignment.END);
            grid.setAlignItems(GridLayout.ItemAlignment.END);
        });
        endBtn.setId("end-btn");

        NativeButton setJustifyContentBtn = new NativeButton(
                "Set justify-content space-between",
                e -> grid.setJustifyContent(
                        GridLayout.ContentAlignment.SPACE_BETWEEN));
        setJustifyContentBtn.setId("set-justify-content-btn");

        section.add(grid, centerBtn, stretchBtn, startBtn, endBtn,
                setJustifyContentBtn);
        add(section);
    }

    private void createPositionTest() {
        GridLayout grid = new GridLayout();
        grid.setId("pos-grid");
        grid.setColumns(3);
        grid.setRows("repeat(2, 50px)");
        grid.getStyle().set("border", "1px solid #ccc");

        Div posItem = createDiv("#37474F");
        posItem.setId("pos-item");
        grid.add(posItem);

        NativeButton setPosBtn = new NativeButton("Set position 2,1 span 2",
                e -> grid.setPosition(posItem, GridPosition.builder().column(2)
                        .row(1).columnSpan(2).build()));
        setPosBtn.setId("set-pos-btn");

        NativeButton clearPosBtn = new NativeButton("Clear position",
                e -> grid.clearPosition(posItem));
        clearPosBtn.setId("clear-pos-btn");

        add(grid, setPosBtn, clearPosBtn);
    }

    private void createAreaTest() {
        GridLayout grid = new GridLayout();
        grid.setId("area-grid");
        grid.setTemplateAreas("a a", "b c");
        grid.setColumns("1fr 1fr");
        grid.setRows("50px 50px");
        grid.getStyle().set("border", "1px solid #ccc");

        Div areaItem = createDiv("#263238");
        areaItem.setId("area-item");
        grid.add(areaItem);

        NativeButton setAreaBtn = new NativeButton("Set area 'a'",
                e -> grid.setArea(areaItem, "a"));
        setAreaBtn.setId("set-area-btn");

        NativeButton setAreaBBtn = new NativeButton("Set area 'b'",
                e -> grid.setArea(areaItem, "b"));
        setAreaBBtn.setId("set-area-b-btn");

        Span areaDisplay = new Span();
        areaDisplay.setId("area-display");
        NativeButton getAreaBtn = new NativeButton("Get area", e -> areaDisplay
                .setText(String.valueOf(grid.getArea(areaItem))));
        getAreaBtn.setId("get-area-btn");

        add(grid, setAreaBtn, setAreaBBtn, getAreaBtn, areaDisplay);
    }

    private void createThemeTest() {
        Div section = new Div();
        section.getStyle().set("marginTop", "30px").set("padding", "10px")
                .set("backgroundColor", "#FFF3E0");
        section.add(new Span("Theme Variants Test (Lumo):"));

        GridLayout grid = new GridLayout();
        grid.setId("theme-grid");
        grid.setColumns(3);
        grid.getStyle().set("border", "2px dashed #FF9800");
        grid.getStyle().set("backgroundColor", "#FFE0B2");
        for (int i = 0; i < 6; i++) {
            Div div = createDiv("#FF9800");
            div.setText("Cell " + (i + 1));
            div.getStyle().set("padding", "10px");
            grid.add(div);
        }

        NativeButton addSpacingBtn = new NativeButton("Add spacing",
                e -> grid.getThemeList().add("spacing"));
        addSpacingBtn.setId("add-spacing-btn");

        NativeButton addSpacingXlBtn = new NativeButton("Add spacing-xl",
                e -> grid.getThemeList().add("spacing-xl"));
        addSpacingXlBtn.setId("add-spacing-xl-btn");

        NativeButton removeSpacingBtn = new NativeButton("Remove spacing",
                e -> {
                    grid.getThemeList().remove("spacing");
                    grid.getThemeList().remove("spacing-xl");
                });
        removeSpacingBtn.setId("remove-spacing-btn");

        NativeButton addPaddingBtn = new NativeButton("Add padding",
                e -> grid.getThemeList().add("padding"));
        addPaddingBtn.setId("add-padding-btn");

        NativeButton addMarginBtn = new NativeButton("Add margin",
                e -> grid.getThemeList().add("margin"));
        addMarginBtn.setId("add-margin-btn");

        NativeButton clearThemesBtn = new NativeButton("Clear all themes",
                e -> grid.getThemeList().clear());
        clearThemesBtn.setId("clear-themes-btn");

        Span themeDisplay = new Span();
        themeDisplay.setId("theme-display");
        NativeButton showThemesBtn = new NativeButton("Show themes",
                e -> themeDisplay.setText(grid.getThemeList().toString()));

        section.add(grid, addSpacingBtn, addSpacingXlBtn, removeSpacingBtn,
                addPaddingBtn, addMarginBtn, clearThemesBtn, showThemesBtn,
                themeDisplay);
        add(section);
    }

    private Div createDiv(String backgroundColor) {
        Div div = new Div();
        div.getStyle().set("backgroundColor", backgroundColor).set("minHeight",
                "30px");
        return div;
    }
}
