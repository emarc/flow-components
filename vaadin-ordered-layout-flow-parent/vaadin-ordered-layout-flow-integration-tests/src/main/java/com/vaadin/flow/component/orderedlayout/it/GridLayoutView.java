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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.GridLayout;
import com.vaadin.flow.component.orderedlayout.GridPosition;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;

/**
 * View for {@link GridLayout} component demos.
 */
@Route("vaadin-ordered-layout/grid-layout")
public class GridLayoutView extends AbstractLayout {

    private static final String[] COLORS = { "#78909C", "#546E7A", "#37474F",
            "#263238", "#455A64", "#607D8B", "#90A4AE", "#B0BEC5", "#CFD8DC",
            "#ECEFF1", "#78909C", "#546E7A" };

    public GridLayoutView() {
        createBasicGridLayout();
        createGridWithColumnTemplates();
        createGridWithTemplateAreas();
        createGridWithAlignment();
        createGridWithPositioning();
        createGridWithAutoFlow();
        createResponsiveGrid();
    }

    private void createBasicGridLayout() {
        GridLayout grid = new GridLayout();
        grid.setColumns(3);
        grid.setGap("10px");
        grid.setWidth("100%");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        for (int i = 1; i <= 6; i++) {
            grid.add(createComponent(i, getColor(i)));
        }

        grid.setId("basic-grid");
        addCard("Basic GridLayout", "A simple 3-column grid with gap", grid);
    }

    private void createGridWithColumnTemplates() {
        GridLayout grid = new GridLayout();
        grid.setColumns("1fr 2fr 1fr");
        grid.setRows("auto auto");
        grid.setGap("10px");
        grid.setWidth("100%");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        for (int i = 1; i <= 6; i++) {
            grid.add(createComponent(i, getColor(i)));
        }

        grid.setId("grid-with-templates");
        addCard("Grid with Templates",
                "Column template: 1fr 2fr 1fr (middle column is twice as wide)",
                grid);
    }

    private void createGridWithTemplateAreas() {
        GridLayout grid = new GridLayout();
        grid.setTemplateAreas("header header header", "nav    main   aside",
                "footer footer footer");
        grid.setColumns("150px 1fr 150px");
        grid.setRows("auto 1fr auto");
        grid.setGap("10px");
        grid.setWidth("100%");
        grid.setHeight("300px");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        Div header = createAreaComponent("Header", "#1976D2");
        header.setId("area-header");
        grid.add(header);
        grid.setArea(header, "header");

        Div nav = createAreaComponent("Nav", "#388E3C");
        nav.setId("area-nav");
        grid.add(nav);
        grid.setArea(nav, "nav");

        Div main = createAreaComponent("Main Content", "#FFA000");
        main.setId("area-main");
        grid.add(main);
        grid.setArea(main, "main");

        Div aside = createAreaComponent("Aside", "#7B1FA2");
        aside.setId("area-aside");
        grid.add(aside);
        grid.setArea(aside, "aside");

        Div footer = createAreaComponent("Footer", "#C2185B");
        footer.setId("area-footer");
        grid.add(footer);
        grid.setArea(footer, "footer");

        grid.setId("grid-with-areas");
        addCard("Grid with Template Areas",
                "Holy Grail layout using named grid areas", grid);
    }

    private void createGridWithAlignment() {
        GridLayout grid = new GridLayout();
        grid.setColumns(3);
        grid.setGap("10px");
        grid.setWidth("400px");
        grid.setHeight("200px");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        for (int i = 1; i <= 3; i++) {
            Div div = createComponent(i, getColor(i));
            // No fixed width/height so stretch alignment is visible
            div.getStyle().remove("minHeight");
            div.setId("align-item-" + i);
            grid.add(div);
        }

        RadioButtonGroup<GridLayout.ItemAlignment> justifyItems = new RadioButtonGroup<>();
        justifyItems.setItems(GridLayout.ItemAlignment.START,
                GridLayout.ItemAlignment.CENTER, GridLayout.ItemAlignment.END,
                GridLayout.ItemAlignment.STRETCH);
        justifyItems
                .setRenderer(new TextRenderer<>(a -> a.name().toLowerCase()));
        justifyItems.setValue(GridLayout.ItemAlignment.STRETCH);
        justifyItems.addValueChangeListener(
                e -> grid.setJustifyItems(e.getValue()));
        justifyItems.setId("justify-items-select");

        RadioButtonGroup<GridLayout.ItemAlignment> alignItems = new RadioButtonGroup<>();
        alignItems.setItems(GridLayout.ItemAlignment.START,
                GridLayout.ItemAlignment.CENTER, GridLayout.ItemAlignment.END,
                GridLayout.ItemAlignment.STRETCH);
        alignItems.setRenderer(new TextRenderer<>(a -> a.name().toLowerCase()));
        alignItems.setValue(GridLayout.ItemAlignment.STRETCH);
        alignItems
                .addValueChangeListener(e -> grid.setAlignItems(e.getValue()));
        alignItems.setId("align-items-select");

        grid.setId("grid-with-alignment");
        addCard("Grid with Alignment", "Control item alignment within cells",
                grid, new Span("Justify Items (horizontal):"), justifyItems,
                new Span("Align Items (vertical):"), alignItems);
    }

    private void createGridWithPositioning() {
        GridLayout grid = new GridLayout();
        grid.setColumns("repeat(4, 1fr)");
        grid.setRows("repeat(3, 80px)");
        grid.setGap("5px");
        grid.setWidth("100%");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        // Single cell item for visual contrast
        Div single = createAreaComponent("1x1", "#9E9E9E");
        single.setId("single-cell");
        grid.add(single);
        grid.setPosition(single, GridPosition.at(1, 1));

        // Item spanning 2 columns
        Div span2Col = createAreaComponent("Span 2 columns", "#1976D2");
        span2Col.setId("span-2-col");
        grid.add(span2Col);
        grid.setPosition(span2Col,
                GridPosition.builder().column(2).row(1).columnSpan(2).build());

        // Item spanning 2 rows
        Div span2Row = createAreaComponent("Span 2 rows", "#388E3C");
        span2Row.setId("span-2-row");
        grid.add(span2Row);
        grid.setPosition(span2Row,
                GridPosition.builder().column(4).row(1).rowSpan(2).build());

        // Item at specific position
        Div positioned = createAreaComponent("Position 3,2", "#FFA000");
        positioned.setId("positioned");
        grid.add(positioned);
        grid.setPosition(positioned, GridPosition.at(3, 2));

        // Item spanning both directions
        Div spanBoth = createAreaComponent("2x2 span", "#C2185B");
        spanBoth.setId("span-both");
        grid.add(spanBoth);
        grid.setPosition(spanBoth, GridPosition.spanning(1, 2, 2, 2));

        grid.setId("grid-with-positioning");
        addCard("Grid with Positioning",
                "Components placed at specific positions with spans", grid);
    }

    private void createGridWithAutoFlow() {
        GridLayout grid = new GridLayout();
        grid.setColumns("repeat(4, 80px)");
        grid.setRows("repeat(3, 60px)");
        grid.setGap("5px");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        // Item 1: spans 2 columns (row 1, cols 1-2)
        Div item1 = createComponent(1, getColor(1));
        item1.setId("auto-item-1");
        grid.add(item1);
        grid.setPosition(item1, GridPosition.builder().columnSpan(2).build());

        // Item 2: spans 3 columns - won't fit in row 1, wraps to row 2
        // This leaves a GAP at row 1, cols 3-4
        Div item2 = createComponent(2, getColor(2));
        item2.setId("auto-item-2");
        grid.add(item2);
        grid.setPosition(item2, GridPosition.builder().columnSpan(3).build());

        // Item 3: spans 2 columns
        // Without dense: wraps to row 3 (can't fit after item 2)
        // With dense: fills the gap at row 1, cols 3-4
        Div item3 = createComponent(3, getColor(3));
        item3.setId("auto-item-3");
        grid.add(item3);
        grid.setPosition(item3, GridPosition.builder().columnSpan(2).build());

        // Items 4-6: single column items
        for (int i = 4; i <= 6; i++) {
            Div div = createComponent(i, getColor(i));
            div.setId("auto-item-" + i);
            grid.add(div);
        }

        RadioButtonGroup<GridLayout.AutoFlow> autoFlow = new RadioButtonGroup<>();
        autoFlow.setItems(GridLayout.AutoFlow.values());
        autoFlow.setRenderer(new TextRenderer<>(
                af -> af.name().toLowerCase().replace("_", " ")));
        autoFlow.setValue(GridLayout.AutoFlow.ROW);
        autoFlow.addValueChangeListener(e -> grid.setAutoFlow(e.getValue()));
        autoFlow.setId("auto-flow-select");

        grid.setId("grid-with-auto-flow");
        addCard("Grid with Auto Flow",
                "ROW vs COLUMN changes flow direction. DENSE fills gaps left by spanning items.",
                grid, new Span("Auto Flow:"), autoFlow);
    }

    private void createResponsiveGrid() {
        GridLayout grid = new GridLayout();
        grid.setColumns("repeat(auto-fill, minmax(150px, 1fr))");
        grid.setGap("10px");
        grid.setWidth("100%");
        grid.getStyle().set("border", "1px solid #9E9E9E");

        for (int i = 1; i <= 12; i++) {
            Div div = createComponent(i, getColor(i));
            div.setId("responsive-item-" + i);
            grid.add(div);
        }

        grid.setId("responsive-grid");
        addCard("Responsive Grid",
                "Using auto-fill with minmax for responsive columns", grid);
    }

    private String getColor(int index) {
        return COLORS[(index - 1) % COLORS.length];
    }

    private Div createAreaComponent(String text, String color) {
        Div component = new Div();
        component.setText(text);
        component.getStyle().set("backgroundColor", color).set("color", "white")
                .set("padding", "10px").set("display", "flex")
                .set("alignItems", "center").set("justifyContent", "center");
        return component;
    }

    private void addCard(String title, String description,
            Component... components) {
        VerticalLayout card = new VerticalLayout();
        card.setMargin(true);
        card.setPadding(true);
        card.getStyle().set("border", "1px solid #E0E0E0")
                .set("borderRadius", "4px").set("marginBottom", "20px");
        card.add(new H2(title));
        if (description != null) {
            Span desc = new Span(description);
            desc.getStyle().set("color", "#666");
            card.add(desc);
        }
        card.add(components);
        add(card);
    }
}
