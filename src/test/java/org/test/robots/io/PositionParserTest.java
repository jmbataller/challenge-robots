package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.exceptions.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class PositionParserTest {

    private CoordinateParser mockCoordinateParser = mock(CoordinateParser.class);
    private PositionParser underTest = new PositionParser(mockCoordinateParser);

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
                .hasMessage("Invalid position");
    }

    @Test
    @DisplayName("input is invalid (missing orientation)")
    void testInvalidPosition() {
        when(mockCoordinateParser.parse("5")).thenReturn(5);
        when(mockCoordinateParser.parse("3")).thenReturn(3);

        assertThatThrownBy(() -> underTest.parse("5 3 "))
                .isInstanceOf(ParseException.class)
                .hasMessage("Invalid position");
    }

    @Test
    @DisplayName("input contains invalid orientation")
    void testPositionContainsInvalidOrientation() {
        when(mockCoordinateParser.parse("5")).thenReturn(5);
        when(mockCoordinateParser.parse("3")).thenReturn(3);

        assertThatThrownBy(() -> underTest.parse("5 3 X"))
                .isInstanceOf(IllegalArgumentException.class);

        verify(mockCoordinateParser, times(1)).parse("5");
        verify(mockCoordinateParser, times(1)).parse("3");
    }

    @Test
    @DisplayName("input is valid")
    void testValidGridSize() {
        when(mockCoordinateParser.parse("5")).thenReturn(5);
        when(mockCoordinateParser.parse("3")).thenReturn(3);

        assertThat(underTest.parse("5 3 N"))
                .isEqualTo(Position.with(Point.of(5, 3), Orientation.N));

        verify(mockCoordinateParser, times(1)).parse("5");
        verify(mockCoordinateParser, times(1)).parse("3");
    }

}
