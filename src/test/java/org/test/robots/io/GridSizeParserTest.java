package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.test.robots.domain.Grid;
import org.test.robots.exceptions.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class GridSizeParserTest {

    private CoordinateParser mockCoordinateParser = mock(CoordinateParser.class);
    private GridSizeParser underTest = new GridSizeParser(mockCoordinateParser);

    @Test
    @DisplayName("input is null")
    void testNullInput() {
        assertThatThrownBy(() -> underTest.parse(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("input is empty")
    void testEmptyInput() {
        assertThatThrownBy(() -> underTest.parse(""))
                .isInstanceOf(ParseException.class)
                .hasMessage("Invalid grid size");
    }

    @Test
    @DisplayName("input is invalid")
    void testInvalidGridSize() {
        when(mockCoordinateParser.parse("5")).thenReturn(5);

        assertThatThrownBy(() -> underTest.parse(""))
                .isInstanceOf(ParseException.class)
                .hasMessage("Invalid grid size");
    }

    @Test
    @DisplayName("input is valid")
    void testValidGridSize() {
        when(mockCoordinateParser.parse("5")).thenReturn(5);
        when(mockCoordinateParser.parse("3")).thenReturn(3);

        assertThat(underTest.parse("5 3"))
                .isEqualTo(Grid.size(5, 3));
        verify(mockCoordinateParser, times(1)).parse("5");
        verify(mockCoordinateParser, times(1)).parse("3");
    }

}
