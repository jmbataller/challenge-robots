package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.Grid;
import org.test.robots.exceptions.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GridSizeParserTest {

    @Test
    @DisplayName("input is null")
    void testNullInput() {
        assertThatThrownBy(() -> GridSizeParser.parse(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("input is empty")
    void testEmptyInput() {
        assertThatThrownBy(() -> GridSizeParser.parse(""))
                .isInstanceOf(ParseException.class)
                .hasMessage("Invalid grid size");
    }

    @Test
    @DisplayName("input is valid")
    void testValidGridSize() {
        assertThat(GridSizeParser.parse("5 3"))
                .isEqualTo(Grid.size(5, 3));
    }

}
