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

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.HasOrderedComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.shared.HasThemeVariant;

/**
 * A layout component that implements CSS Grid Layout.
 * <p>
 * This component uses a simple {@code <div>} element with {@code display: grid}
 * to create grid-based layouts. It provides a Java API for configuring CSS Grid
 * properties including template columns/rows, gaps, and item placement.
 * <p>
 * Example usage:
 *
 * <pre>
 * GridLayout grid = new GridLayout();
 * grid.setColumns("1fr 2fr 1fr");
 * grid.setRows("auto auto");
 * grid.setGap("10px");
 *
 * Div header = new Div();
 * grid.add(header);
 * grid.setPosition(header,
 *         GridPosition.builder().column(1).columnSpan(3).row(1).build());
 * </pre>
 *
 * @see <a href=
 *      "https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Grid_Layout">CSS
 *      Grid Layout on MDN</a>
 */
@Tag(Tag.DIV)
@CssImport("./vaadin-grid-layout/lumo-grid-layout.css")
public class GridLayout extends Component
        implements HasOrderedComponents, HasStyle, HasSize, ThemableLayout,
        ClickNotifier<GridLayout>, HasThemeVariant<GridLayoutVariant> {

    /**
     * Enum for item alignment values used with CSS Grid's justify-items and
     * align-items properties. These control how items are aligned within their
     * grid cells.
     */
    public enum ItemAlignment {

        /**
         * Items are positioned at the start of the cell.
         */
        START("start"),

        /**
         * Items are positioned at the end of the cell.
         */
        END("end"),

        /**
         * Items are positioned at the center of the cell.
         */
        CENTER("center"),

        /**
         * Items are stretched to fill the cell (default behavior).
         */
        STRETCH("stretch"),

        /**
         * Items are aligned such that their baselines align.
         */
        BASELINE("baseline");

        private final String cssValue;

        ItemAlignment(String cssValue) {
            this.cssValue = cssValue;
        }

        String getCssValue() {
            return cssValue;
        }

        static ItemAlignment fromCssValue(String cssValue,
                ItemAlignment defaultValue) {
            return Arrays.stream(values())
                    .filter(a -> a.getCssValue().equals(cssValue)).findFirst()
                    .orElse(defaultValue);
        }
    }

    /**
     * Enum for self-alignment values used with CSS Grid's justify-self and
     * align-self properties. These control how individual items override the
     * container's item alignment.
     */
    public enum SelfAlignment {

        /**
         * The item inherits the container's justify-items/align-items value.
         */
        AUTO("auto"),

        /**
         * Item is positioned at the start of the cell.
         */
        START("start"),

        /**
         * Item is positioned at the end of the cell.
         */
        END("end"),

        /**
         * Item is positioned at the center of the cell.
         */
        CENTER("center"),

        /**
         * Item is stretched to fill the cell.
         */
        STRETCH("stretch"),

        /**
         * Item is aligned such that its baseline aligns with other baselines.
         */
        BASELINE("baseline");

        private final String cssValue;

        SelfAlignment(String cssValue) {
            this.cssValue = cssValue;
        }

        String getCssValue() {
            return cssValue;
        }

        static SelfAlignment fromCssValue(String cssValue,
                SelfAlignment defaultValue) {
            return Arrays.stream(values())
                    .filter(a -> a.getCssValue().equals(cssValue)).findFirst()
                    .orElse(defaultValue);
        }
    }

    /**
     * Enum for content alignment values used with CSS Grid's justify-content
     * and align-content properties. These control how the grid tracks are
     * distributed within the container when there is extra space.
     */
    public enum ContentAlignment {

        /**
         * Default browser behavior. For CSS Grid, this behaves like STRETCH for
         * auto-sized tracks, distributing extra space to expand them.
         */
        NORMAL("normal"),

        /**
         * Tracks are positioned at the start of the container.
         */
        START("start"),

        /**
         * Tracks are positioned at the end of the container.
         */
        END("end"),

        /**
         * Tracks are positioned at the center of the container.
         */
        CENTER("center"),

        /**
         * Tracks are stretched to fill the container.
         */
        STRETCH("stretch"),

        /**
         * Tracks are distributed evenly. The first track is flush with the
         * start, the last is flush with the end.
         */
        SPACE_BETWEEN("space-between"),

        /**
         * Tracks are distributed evenly with half-size space on either end.
         */
        SPACE_AROUND("space-around"),

        /**
         * Tracks are distributed evenly with equal space around them.
         */
        SPACE_EVENLY("space-evenly");

        private final String cssValue;

        ContentAlignment(String cssValue) {
            this.cssValue = cssValue;
        }

        String getCssValue() {
            return cssValue;
        }

        static ContentAlignment fromCssValue(String cssValue,
                ContentAlignment defaultValue) {
            return Arrays.stream(values())
                    .filter(a -> a.getCssValue().equals(cssValue)).findFirst()
                    .orElse(defaultValue);
        }
    }

    /**
     * Enum for grid-auto-flow property values.
     */
    public enum AutoFlow {

        /**
         * Items are placed by filling each row in turn, adding new rows as
         * necessary.
         */
        ROW("row"),

        /**
         * Items are placed by filling each column in turn, adding new columns
         * as necessary.
         */
        COLUMN("column"),

        /**
         * Items are placed by filling each row, attempting to fill in holes
         * earlier in the grid.
         */
        ROW_DENSE("row dense"),

        /**
         * Items are placed by filling each column, attempting to fill in holes
         * earlier in the grid.
         */
        COLUMN_DENSE("column dense");

        private final String cssValue;

        AutoFlow(String cssValue) {
            this.cssValue = cssValue;
        }

        String getCssValue() {
            return cssValue;
        }

        static AutoFlow fromCssValue(String cssValue, AutoFlow defaultValue) {
            return Arrays.stream(values())
                    .filter(af -> af.getCssValue().equals(cssValue)).findFirst()
                    .orElse(defaultValue);
        }
    }

    /**
     * CSS class name used for styling GridLayout elements.
     */
    private static final String GRID_LAYOUT_CLASS = "vaadin-grid-layout";

    /**
     * Default constructor. Creates an empty grid layout.
     */
    public GridLayout() {
        getElement().getClassList().add(GRID_LAYOUT_CLASS);
        getStyle().set("display", "grid");
    }

    /**
     * Convenience constructor to create a layout with the children already
     * inside it.
     *
     * @param children
     *            the items to add to this layout
     * @see #add(Component...)
     */
    public GridLayout(Component... children) {
        this();
        add(children);
    }

    // ==================== Wrap (Not Supported) ====================

    /**
     * CSS Grid does not support wrap in the same way as flexbox layouts. This
     * method is not supported and will throw an exception.
     * <p>
     * For responsive wrapping behavior in CSS Grid, use
     * {@link #setColumns(String)} with {@code auto-fill} or {@code auto-fit}:
     * 
     * <pre>
     * grid.setColumns("repeat(auto-fill, minmax(200px, 1fr))");
     * </pre>
     *
     * @param wrap
     *            ignored
     * @throws UnsupportedOperationException
     *             always
     */
    @Override
    public void setWrap(boolean wrap) {
        throw new UnsupportedOperationException(
                "CSS Grid does not support wrap. Use setColumns(\"repeat(auto-fill, minmax(200px, 1fr))\") for responsive layouts.");
    }

    /**
     * CSS Grid does not support wrap in the same way as flexbox layouts.
     *
     * @return always false
     */
    @Override
    public boolean isWrap() {
        return false;
    }

    // ==================== Column/Row Template Methods ====================

    /**
     * Sets the grid-template-columns CSS property.
     * <p>
     * Examples:
     * <ul>
     * <li>{@code "1fr 2fr 1fr"} - three columns with relative widths</li>
     * <li>{@code "100px auto 100px"} - fixed/auto/fixed widths</li>
     * <li>{@code "repeat(3, 1fr)"} - three equal columns</li>
     * <li>{@code "repeat(auto-fill, minmax(200px, 1fr))"} - responsive
     * columns</li>
     * </ul>
     *
     * @param columns
     *            the column template, or null to remove
     */
    public void setColumns(String columns) {
        if (columns == null) {
            getStyle().remove(
                    GridLayoutConstants.GRID_TEMPLATE_COLUMNS_CSS_PROPERTY);
        } else {
            getStyle().set(
                    GridLayoutConstants.GRID_TEMPLATE_COLUMNS_CSS_PROPERTY,
                    columns);
        }
    }

    /**
     * Convenience method to create equal-width columns.
     *
     * @param count
     *            the number of equal columns
     * @throws IllegalArgumentException
     *             if count is less than 1
     */
    public void setColumns(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Column count must be >= 1");
        }
        setColumns("repeat(" + count + ", 1fr)");
    }

    /**
     * Gets the current grid-template-columns value.
     *
     * @return the columns template or null if not set
     */
    public String getColumns() {
        return getStyle()
                .get(GridLayoutConstants.GRID_TEMPLATE_COLUMNS_CSS_PROPERTY);
    }

    /**
     * Sets the grid-template-rows CSS property.
     * <p>
     * Examples:
     * <ul>
     * <li>{@code "auto 1fr auto"} - header/content/footer pattern</li>
     * <li>{@code "100px 200px"} - fixed heights</li>
     * <li>{@code "repeat(3, minmax(100px, auto))"} - minimum heights</li>
     * </ul>
     *
     * @param rows
     *            the row template, or null to remove
     */
    public void setRows(String rows) {
        if (rows == null) {
            getStyle().remove(
                    GridLayoutConstants.GRID_TEMPLATE_ROWS_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.GRID_TEMPLATE_ROWS_CSS_PROPERTY,
                    rows);
        }
    }

    /**
     * Convenience method to create equal-height rows.
     *
     * @param count
     *            the number of equal rows
     * @throws IllegalArgumentException
     *             if count is less than 1
     */
    public void setRows(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Row count must be >= 1");
        }
        setRows("repeat(" + count + ", 1fr)");
    }

    /**
     * Gets the current grid-template-rows value.
     *
     * @return the rows template or null if not set
     */
    public String getRows() {
        return getStyle()
                .get(GridLayoutConstants.GRID_TEMPLATE_ROWS_CSS_PROPERTY);
    }

    // ==================== Template Areas Methods ====================

    /**
     * Sets the grid-template-areas CSS property.
     * <p>
     * Each string represents a row, with space-separated area names. Use "."
     * for empty cells.
     * <p>
     * Example:
     *
     * <pre>
     * grid.setTemplateAreas("header header header", "nav    main   aside",
     *         "footer footer footer");
     * </pre>
     *
     * @param areas
     *            the area definitions, each string being one row
     */
    public void setTemplateAreas(String... areas) {
        if (areas == null || areas.length == 0) {
            getStyle().remove(
                    GridLayoutConstants.GRID_TEMPLATE_AREAS_CSS_PROPERTY);
        } else {
            String value = Arrays.stream(areas).map(row -> "\"" + row + "\"")
                    .collect(Collectors.joining(" "));
            getStyle().set(GridLayoutConstants.GRID_TEMPLATE_AREAS_CSS_PROPERTY,
                    value);
        }
    }

    /**
     * Gets the current grid-template-areas value.
     *
     * @return the template areas or null if not set
     */
    public String getTemplateAreas() {
        return getStyle()
                .get(GridLayoutConstants.GRID_TEMPLATE_AREAS_CSS_PROPERTY);
    }

    // ==================== Gap Methods ====================

    /**
     * Sets both row and column gap.
     *
     * @param gap
     *            the gap value (e.g., "10px", "1rem"), or null to remove
     */
    public void setGap(String gap) {
        if (gap == null) {
            getStyle().remove(GridLayoutConstants.GAP_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.GAP_CSS_PROPERTY, gap);
        }
    }

    /**
     * Gets the current gap value.
     *
     * @return the gap or null if not set
     */
    public String getGap() {
        return getStyle().get(GridLayoutConstants.GAP_CSS_PROPERTY);
    }

    /**
     * Sets the column-gap CSS property.
     *
     * @param columnGap
     *            the column gap value, or null to remove
     */
    public void setColumnGap(String columnGap) {
        if (columnGap == null) {
            getStyle().remove(GridLayoutConstants.COLUMN_GAP_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.COLUMN_GAP_CSS_PROPERTY,
                    columnGap);
        }
    }

    /**
     * Gets the current column-gap value.
     *
     * @return the column gap or null if not set
     */
    public String getColumnGap() {
        return getStyle().get(GridLayoutConstants.COLUMN_GAP_CSS_PROPERTY);
    }

    /**
     * Sets the row-gap CSS property.
     *
     * @param rowGap
     *            the row gap value, or null to remove
     */
    public void setRowGap(String rowGap) {
        if (rowGap == null) {
            getStyle().remove(GridLayoutConstants.ROW_GAP_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.ROW_GAP_CSS_PROPERTY, rowGap);
        }
    }

    /**
     * Gets the current row-gap value.
     *
     * @return the row gap or null if not set
     */
    public String getRowGap() {
        return getStyle().get(GridLayoutConstants.ROW_GAP_CSS_PROPERTY);
    }

    // ==================== Alignment Methods ====================

    /**
     * Sets the justify-items CSS property (horizontal alignment of items within
     * cells).
     *
     * @param alignment
     *            the alignment, or null to remove
     */
    public void setJustifyItems(ItemAlignment alignment) {
        if (alignment == null) {
            getStyle().remove(GridLayoutConstants.JUSTIFY_ITEMS_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.JUSTIFY_ITEMS_CSS_PROPERTY,
                    alignment.getCssValue());
        }
    }

    /**
     * Gets the current justify-items value.
     *
     * @return the alignment or {@link ItemAlignment#STRETCH} if not set
     */
    public ItemAlignment getJustifyItems() {
        return ItemAlignment.fromCssValue(
                getStyle().get(GridLayoutConstants.JUSTIFY_ITEMS_CSS_PROPERTY),
                ItemAlignment.STRETCH);
    }

    /**
     * Sets the align-items CSS property (vertical alignment of items within
     * cells).
     *
     * @param alignment
     *            the alignment, or null to remove
     */
    public void setAlignItems(ItemAlignment alignment) {
        if (alignment == null) {
            getStyle().remove(GridLayoutConstants.ALIGN_ITEMS_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.ALIGN_ITEMS_CSS_PROPERTY,
                    alignment.getCssValue());
        }
    }

    /**
     * Gets the current align-items value.
     *
     * @return the alignment or {@link ItemAlignment#STRETCH} if not set
     */
    public ItemAlignment getAlignItems() {
        return ItemAlignment.fromCssValue(
                getStyle().get(GridLayoutConstants.ALIGN_ITEMS_CSS_PROPERTY),
                ItemAlignment.STRETCH);
    }

    /**
     * Sets the justify-content CSS property (horizontal alignment of grid
     * within container).
     *
     * @param alignment
     *            the alignment, or null to remove
     */
    public void setJustifyContent(ContentAlignment alignment) {
        if (alignment == null) {
            getStyle().remove(GridLayoutConstants.JUSTIFY_CONTENT_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.JUSTIFY_CONTENT_CSS_PROPERTY,
                    alignment.getCssValue());
        }
    }

    /**
     * Gets the current justify-content value.
     *
     * @return the alignment or {@link ContentAlignment#NORMAL} if not
     *         explicitly set
     */
    public ContentAlignment getJustifyContent() {
        return ContentAlignment.fromCssValue(
                getStyle()
                        .get(GridLayoutConstants.JUSTIFY_CONTENT_CSS_PROPERTY),
                ContentAlignment.NORMAL);
    }

    /**
     * Sets the align-content CSS property (vertical alignment of grid within
     * container).
     *
     * @param alignment
     *            the alignment, or null to remove
     */
    public void setAlignContent(ContentAlignment alignment) {
        if (alignment == null) {
            getStyle().remove(GridLayoutConstants.ALIGN_CONTENT_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.ALIGN_CONTENT_CSS_PROPERTY,
                    alignment.getCssValue());
        }
    }

    /**
     * Gets the current align-content value.
     *
     * @return the alignment or {@link ContentAlignment#NORMAL} if not
     *         explicitly set
     */
    public ContentAlignment getAlignContent() {
        return ContentAlignment.fromCssValue(
                getStyle().get(GridLayoutConstants.ALIGN_CONTENT_CSS_PROPERTY),
                ContentAlignment.NORMAL);
    }

    // ==================== Auto Flow Methods ====================

    /**
     * Sets the grid-auto-flow CSS property.
     *
     * @param autoFlow
     *            the auto flow mode, or null to remove
     */
    public void setAutoFlow(AutoFlow autoFlow) {
        if (autoFlow == null) {
            getStyle().remove(GridLayoutConstants.GRID_AUTO_FLOW_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.GRID_AUTO_FLOW_CSS_PROPERTY,
                    autoFlow.getCssValue());
        }
    }

    /**
     * Gets the current grid-auto-flow value.
     *
     * @return the auto flow mode or {@link AutoFlow#ROW} if not set
     */
    public AutoFlow getAutoFlow() {
        return AutoFlow.fromCssValue(
                getStyle().get(GridLayoutConstants.GRID_AUTO_FLOW_CSS_PROPERTY),
                AutoFlow.ROW);
    }

    /**
     * Sets the grid-auto-rows CSS property.
     *
     * @param autoRows
     *            the auto rows value (e.g., "minmax(100px, auto)"), or null to
     *            remove
     */
    public void setAutoRows(String autoRows) {
        if (autoRows == null) {
            getStyle().remove(GridLayoutConstants.GRID_AUTO_ROWS_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.GRID_AUTO_ROWS_CSS_PROPERTY,
                    autoRows);
        }
    }

    /**
     * Gets the current grid-auto-rows value.
     *
     * @return the auto rows or null if not set
     */
    public String getAutoRows() {
        return getStyle().get(GridLayoutConstants.GRID_AUTO_ROWS_CSS_PROPERTY);
    }

    /**
     * Sets the grid-auto-columns CSS property.
     *
     * @param autoColumns
     *            the auto columns value, or null to remove
     */
    public void setAutoColumns(String autoColumns) {
        if (autoColumns == null) {
            getStyle()
                    .remove(GridLayoutConstants.GRID_AUTO_COLUMNS_CSS_PROPERTY);
        } else {
            getStyle().set(GridLayoutConstants.GRID_AUTO_COLUMNS_CSS_PROPERTY,
                    autoColumns);
        }
    }

    /**
     * Gets the current grid-auto-columns value.
     *
     * @return the auto columns or null if not set
     */
    public String getAutoColumns() {
        return getStyle()
                .get(GridLayoutConstants.GRID_AUTO_COLUMNS_CSS_PROPERTY);
    }

    // ==================== Item Position Methods ====================

    /**
     * Sets the position of a component within the grid.
     * <p>
     * This method clears any grid-area setting on the component to avoid
     * conflicting CSS positioning.
     *
     * @param component
     *            the component to position
     * @param position
     *            the grid position
     * @throws NullPointerException
     *             if component or position is null
     */
    public void setPosition(HasElement component, GridPosition position) {
        Objects.requireNonNull(component, "Component cannot be null");
        Objects.requireNonNull(position, "Position cannot be null");

        // Clear grid-area to avoid conflicting positioning
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_AREA_CSS_PROPERTY);

        String columnValue = position.toGridColumnValue();
        String rowValue = position.toGridRowValue();

        if (columnValue != null) {
            component.getElement().getStyle().set(
                    GridLayoutConstants.GRID_COLUMN_CSS_PROPERTY, columnValue);
        } else {
            component.getElement().getStyle()
                    .remove(GridLayoutConstants.GRID_COLUMN_CSS_PROPERTY);
        }

        if (rowValue != null) {
            component.getElement().getStyle()
                    .set(GridLayoutConstants.GRID_ROW_CSS_PROPERTY, rowValue);
        } else {
            component.getElement().getStyle()
                    .remove(GridLayoutConstants.GRID_ROW_CSS_PROPERTY);
        }
    }

    /**
     * Clears any position settings from a component.
     *
     * @param component
     *            the component to clear positioning from
     * @throws NullPointerException
     *             if component is null
     */
    public void clearPosition(HasElement component) {
        Objects.requireNonNull(component, "Component cannot be null");
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_COLUMN_CSS_PROPERTY);
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_ROW_CSS_PROPERTY);
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_AREA_CSS_PROPERTY);
    }

    /**
     * Sets a component to occupy a named grid area. The area must be defined in
     * {@link #setTemplateAreas(String...)}.
     * <p>
     * This method clears any grid-column and grid-row settings on the component
     * to avoid conflicting CSS positioning.
     *
     * @param component
     *            the component to position
     * @param areaName
     *            the name of the grid area, or null to remove
     * @throws NullPointerException
     *             if component is null
     */
    public void setArea(HasElement component, String areaName) {
        Objects.requireNonNull(component, "Component cannot be null");

        // Clear grid-column and grid-row to avoid conflicting positioning
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_COLUMN_CSS_PROPERTY);
        component.getElement().getStyle()
                .remove(GridLayoutConstants.GRID_ROW_CSS_PROPERTY);

        if (areaName == null || areaName.isEmpty()) {
            component.getElement().getStyle()
                    .remove(GridLayoutConstants.GRID_AREA_CSS_PROPERTY);
        } else {
            component.getElement().getStyle()
                    .set(GridLayoutConstants.GRID_AREA_CSS_PROPERTY, areaName);
        }
    }

    /**
     * Gets the grid area name for a component.
     *
     * @param component
     *            the component
     * @return the area name or null if not set
     */
    public String getArea(HasElement component) {
        return component.getElement().getStyle()
                .get(GridLayoutConstants.GRID_AREA_CSS_PROPERTY);
    }

    // ==================== Item Self-Alignment Methods ====================

    /**
     * Sets the justify-self property for specific components (horizontal
     * alignment override within cells).
     *
     * @param alignment
     *            the alignment, or null to remove
     * @param components
     *            the components to apply alignment to
     */
    public void setJustifySelf(SelfAlignment alignment,
            HasElement... components) {
        for (HasElement component : components) {
            if (alignment == null) {
                component.getElement().getStyle()
                        .remove(GridLayoutConstants.JUSTIFY_SELF_CSS_PROPERTY);
            } else {
                component.getElement().getStyle().set(
                        GridLayoutConstants.JUSTIFY_SELF_CSS_PROPERTY,
                        alignment.getCssValue());
            }
        }
    }

    /**
     * Gets the justify-self value for a component.
     * <p>
     * The default value is {@link SelfAlignment#AUTO}, which means the
     * component inherits the container's justify-items setting.
     *
     * @param component
     *            the component
     * @return the alignment or {@link SelfAlignment#AUTO} if not set
     */
    public SelfAlignment getJustifySelf(HasElement component) {
        return SelfAlignment.fromCssValue(
                component.getElement().getStyle()
                        .get(GridLayoutConstants.JUSTIFY_SELF_CSS_PROPERTY),
                SelfAlignment.AUTO);
    }

    /**
     * Sets the align-self property for specific components (vertical alignment
     * override within cells).
     *
     * @param alignment
     *            the alignment, or null to remove
     * @param components
     *            the components to apply alignment to
     */
    public void setAlignSelf(SelfAlignment alignment,
            HasElement... components) {
        for (HasElement component : components) {
            if (alignment == null) {
                component.getElement().getStyle()
                        .remove(GridLayoutConstants.ALIGN_SELF_CSS_PROPERTY);
            } else {
                component.getElement().getStyle().set(
                        GridLayoutConstants.ALIGN_SELF_CSS_PROPERTY,
                        alignment.getCssValue());
            }
        }
    }

    /**
     * Gets the align-self value for a component.
     * <p>
     * The default value is {@link SelfAlignment#AUTO}, which means the
     * component inherits the container's align-items setting.
     *
     * @param component
     *            the component
     * @return the alignment or {@link SelfAlignment#AUTO} if not set
     */
    public SelfAlignment getAlignSelf(HasElement component) {
        return SelfAlignment.fromCssValue(
                component.getElement().getStyle()
                        .get(GridLayoutConstants.ALIGN_SELF_CSS_PROPERTY),
                SelfAlignment.AUTO);
    }

    // ==================== Theme Variant Methods ====================

    /**
     * Adds theme variants to the component. In addition to setting the theme
     * attribute, this method also applies the corresponding inline styles to
     * ensure the styles work in shadow DOM contexts where the external CSS may
     * not be loaded.
     *
     * @param variants
     *            the theme variants to add
     */
    @Override
    public void addThemeVariants(GridLayoutVariant... variants) {
        HasThemeVariant.super.addThemeVariants(variants);
        applyThemeVariantStyles();
    }

    /**
     * Removes theme variants from the component and updates the inline styles
     * accordingly.
     *
     * @param variants
     *            the theme variants to remove
     */
    @Override
    public void removeThemeVariants(GridLayoutVariant... variants) {
        HasThemeVariant.super.removeThemeVariants(variants);
        applyThemeVariantStyles();
    }

    /**
     * Applies inline styles based on current theme variants. This ensures
     * styles work in shadow DOM contexts.
     */
    private void applyThemeVariantStyles() {
        var themeNames = getThemeNames();

        // Handle spacing variants
        if (themeNames.contains("spacing-xs")) {
            getStyle().set("gap", "var(--lumo-space-xs, 0.25rem)");
        } else if (themeNames.contains("spacing-s")) {
            getStyle().set("gap", "var(--lumo-space-s, 0.5rem)");
        } else if (themeNames.contains("spacing")) {
            getStyle().set("gap", "var(--lumo-space-m, 1rem)");
        } else if (themeNames.contains("spacing-l")) {
            getStyle().set("gap", "var(--lumo-space-l, 1.5rem)");
        } else if (themeNames.contains("spacing-xl")) {
            getStyle().set("gap", "var(--lumo-space-xl, 2.5rem)");
        } else {
            getStyle().remove("gap");
        }

        // Handle padding variant
        if (themeNames.contains("padding")) {
            getStyle().set("padding", "var(--lumo-space-m, 1rem)");
        } else {
            getStyle().remove("padding");
        }

        // Handle margin variant
        if (themeNames.contains("margin")) {
            getStyle().set("margin", "var(--lumo-space-m, 1rem)");
        } else {
            getStyle().remove("margin");
        }
    }
}
