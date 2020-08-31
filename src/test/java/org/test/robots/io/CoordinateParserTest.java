package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.test.robots.exceptions.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoordinateParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-500", "51", "500", "1291209"})
    @DisplayName("coordinate in input is smaller than 0 or bigger than 50")
    void testCoordinateBiggerThanLimit(final String num) {
        assertThatThrownBy(() -> CoordinateParser.parse(num))
                .isInstanceOf(ParseException.class)
                .hasMessage("coordinate needs to be between 0 and 50");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "x", "y", "51.34", "0.383", "29292929292929292929292929292929292929299292"})
    @DisplayName("coordinate in input is invalid format")
    void testCoordinateWithInvalidFormat(final String num) {
        assertThatThrownBy(() -> CoordinateParser.parse(num))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "25", "50"})
    @DisplayName("coordinate in input is valid")
    void testValidCoordinate(final String num) {
        assertThat(CoordinateParser.parse(num)).isEqualTo(Integer.valueOf(num));
    }
}
