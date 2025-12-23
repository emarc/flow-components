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
import java.util.Objects;

/**
 * Immutable value object representing a position within a {@link GridLayout}.
 * Use the {@link Builder} to create instances.
 * <p>
 * Positions use 1-based indexing to match CSS Grid conventions.
 * <p>
 * Example usage:
 *
 * <pre>
 * // Position at column 2, row 1
 * GridPosition pos = GridPosition.at(2, 1);
 *
 * // Position spanning multiple cells
 * GridPosition spanning = GridPosition.spanning(1, 1, 2, 3);
 *
 * // Using the builder for more control
 * GridPosition custom = GridPosition.builder().column(1).row(2).columnSpan(3)
 *         .build();
 * </pre>
 *
 * @see GridLayout#setPosition(com.vaadin.flow.component.HasElement,
 *      GridPosition)
 */
public final class GridPosition implements Serializable {

    private final Integer column;
    private final Integer row;
    private final Integer columnSpan;
    private final Integer rowSpan;
    private final Integer columnEnd;
    private final Integer rowEnd;

    private GridPosition(Builder builder) {
        this.column = builder.column;
        this.row = builder.row;
        this.columnSpan = builder.columnSpan;
        this.rowSpan = builder.rowSpan;
        this.columnEnd = builder.columnEnd;
        this.rowEnd = builder.rowEnd;
    }

    /**
     * Gets the starting column (1-based).
     *
     * @return the column index or null if not set
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Gets the starting row (1-based).
     *
     * @return the row index or null if not set
     */
    public Integer getRow() {
        return row;
    }

    /**
     * Gets the column span.
     *
     * @return the number of columns to span or null if not set
     */
    public Integer getColumnSpan() {
        return columnSpan;
    }

    /**
     * Gets the row span.
     *
     * @return the number of rows to span or null if not set
     */
    public Integer getRowSpan() {
        return rowSpan;
    }

    /**
     * Gets the ending column (exclusive, 1-based).
     *
     * @return the ending column index or null if not set
     */
    public Integer getColumnEnd() {
        return columnEnd;
    }

    /**
     * Gets the ending row (exclusive, 1-based).
     *
     * @return the ending row index or null if not set
     */
    public Integer getRowEnd() {
        return rowEnd;
    }

    /**
     * Creates the CSS value for grid-column property.
     *
     * @return the CSS value or null if no column positioning is set
     */
    String toGridColumnValue() {
        if (column == null && columnEnd == null && columnSpan == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (column != null) {
            sb.append(column);
        }
        if (columnSpan != null) {
            if (sb.length() > 0) {
                sb.append(" / span ").append(columnSpan);
            } else {
                sb.append("span ").append(columnSpan);
            }
        } else if (columnEnd != null) {
            if (sb.length() == 0) {
                sb.append("auto");
            }
            sb.append(" / ").append(columnEnd);
        }
        return sb.length() > 0 ? sb.toString() : null;
    }

    /**
     * Creates the CSS value for grid-row property.
     *
     * @return the CSS value or null if no row positioning is set
     */
    String toGridRowValue() {
        if (row == null && rowEnd == null && rowSpan == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (row != null) {
            sb.append(row);
        }
        if (rowSpan != null) {
            if (sb.length() > 0) {
                sb.append(" / span ").append(rowSpan);
            } else {
                sb.append("span ").append(rowSpan);
            }
        } else if (rowEnd != null) {
            if (sb.length() == 0) {
                sb.append("auto");
            }
            sb.append(" / ").append(rowEnd);
        }
        return sb.length() > 0 ? sb.toString() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GridPosition that = (GridPosition) o;
        return Objects.equals(column, that.column)
                && Objects.equals(row, that.row)
                && Objects.equals(columnSpan, that.columnSpan)
                && Objects.equals(rowSpan, that.rowSpan)
                && Objects.equals(columnEnd, that.columnEnd)
                && Objects.equals(rowEnd, that.rowEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row, columnSpan, rowSpan, columnEnd,
                rowEnd);
    }

    @Override
    public String toString() {
        return "GridPosition{column=" + column + ", row=" + row
                + ", columnSpan=" + columnSpan + ", rowSpan=" + rowSpan
                + ", columnEnd=" + columnEnd + ", rowEnd=" + rowEnd + "}";
    }

    /**
     * Creates a new builder for GridPosition.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Convenience method to create a position at a specific column and row.
     *
     * @param column
     *            the column (1-based)
     * @param row
     *            the row (1-based)
     * @return a new GridPosition
     */
    public static GridPosition at(int column, int row) {
        return builder().column(column).row(row).build();
    }

    /**
     * Convenience method to create a position spanning multiple cells.
     *
     * @param column
     *            the starting column (1-based)
     * @param row
     *            the starting row (1-based)
     * @param columnSpan
     *            number of columns to span
     * @param rowSpan
     *            number of rows to span
     * @return a new GridPosition
     */
    public static GridPosition spanning(int column, int row, int columnSpan,
            int rowSpan) {
        return builder().column(column).row(row).columnSpan(columnSpan)
                .rowSpan(rowSpan).build();
    }

    /**
     * Builder for creating {@link GridPosition} instances.
     */
    public static final class Builder implements Serializable {

        private Integer column;
        private Integer row;
        private Integer columnSpan;
        private Integer rowSpan;
        private Integer columnEnd;
        private Integer rowEnd;

        private Builder() {
        }

        /**
         * Sets the starting column (1-based).
         *
         * @param column
         *            the column index
         * @return this builder
         * @throws IllegalArgumentException
         *             if column is less than 1
         */
        public Builder column(int column) {
            if (column < 1) {
                throw new IllegalArgumentException("Column must be >= 1");
            }
            this.column = column;
            return this;
        }

        /**
         * Sets the starting row (1-based).
         *
         * @param row
         *            the row index
         * @return this builder
         * @throws IllegalArgumentException
         *             if row is less than 1
         */
        public Builder row(int row) {
            if (row < 1) {
                throw new IllegalArgumentException("Row must be >= 1");
            }
            this.row = row;
            return this;
        }

        /**
         * Sets the number of columns to span. Cannot be used together with
         * {@link #columnEnd(int)}.
         *
         * @param columnSpan
         *            the number of columns
         * @return this builder
         * @throws IllegalArgumentException
         *             if columnSpan is less than 1
         * @throws IllegalStateException
         *             if columnEnd was already set
         */
        public Builder columnSpan(int columnSpan) {
            if (columnSpan < 1) {
                throw new IllegalArgumentException("Column span must be >= 1");
            }
            if (this.columnEnd != null) {
                throw new IllegalStateException(
                        "Cannot set both columnSpan and columnEnd");
            }
            this.columnSpan = columnSpan;
            return this;
        }

        /**
         * Sets the number of rows to span. Cannot be used together with
         * {@link #rowEnd(int)}.
         *
         * @param rowSpan
         *            the number of rows
         * @return this builder
         * @throws IllegalArgumentException
         *             if rowSpan is less than 1
         * @throws IllegalStateException
         *             if rowEnd was already set
         */
        public Builder rowSpan(int rowSpan) {
            if (rowSpan < 1) {
                throw new IllegalArgumentException("Row span must be >= 1");
            }
            if (this.rowEnd != null) {
                throw new IllegalStateException(
                        "Cannot set both rowSpan and rowEnd");
            }
            this.rowSpan = rowSpan;
            return this;
        }

        /**
         * Sets the ending column (exclusive, 1-based). Cannot be used together
         * with {@link #columnSpan(int)}.
         *
         * @param columnEnd
         *            the ending column index
         * @return this builder
         * @throws IllegalArgumentException
         *             if columnEnd is less than 1
         * @throws IllegalStateException
         *             if columnSpan was already set
         */
        public Builder columnEnd(int columnEnd) {
            if (columnEnd < 1) {
                throw new IllegalArgumentException("Column end must be >= 1");
            }
            if (this.columnSpan != null) {
                throw new IllegalStateException(
                        "Cannot set both columnEnd and columnSpan");
            }
            this.columnEnd = columnEnd;
            return this;
        }

        /**
         * Sets the ending row (exclusive, 1-based). Cannot be used together
         * with {@link #rowSpan(int)}.
         *
         * @param rowEnd
         *            the ending row index
         * @return this builder
         * @throws IllegalArgumentException
         *             if rowEnd is less than 1
         * @throws IllegalStateException
         *             if rowSpan was already set
         */
        public Builder rowEnd(int rowEnd) {
            if (rowEnd < 1) {
                throw new IllegalArgumentException("Row end must be >= 1");
            }
            if (this.rowSpan != null) {
                throw new IllegalStateException(
                        "Cannot set both rowEnd and rowSpan");
            }
            this.rowEnd = rowEnd;
            return this;
        }

        /**
         * Builds the GridPosition.
         *
         * @return a new GridPosition instance
         */
        public GridPosition build() {
            return new GridPosition(this);
        }
    }
}
