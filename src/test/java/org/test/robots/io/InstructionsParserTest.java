package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.test.robots.domain.Instruction;
import org.test.robots.exceptions.ParseException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.test.robots.domain.Instruction.*;

public class InstructionsParserTest {

    private final InstructionsParser underTest = new InstructionsParser();

    @Test
    @DisplayName("instructions have to be smaller than 100")
    void testInstructionsLimitAreLessThan100() {
        assertThatThrownBy(() -> underTest.parse("RLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRL" +
                "FRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLF" +
                "RLFRLFRLFRLFRLFRLF"))
                .isInstanceOf(ParseException.class)
                .hasMessage("instructions have to be less than 100");
    }

    @ParameterizedTest
    @ValueSource(strings = {"X", "RLFX", "RLFRLFRLFRLFRLFRLFX"})
    @DisplayName("invalid instructions sequence")
    void testInstructionsWithInvalidFormat(final String instructions) {
        assertThatThrownBy(() -> underTest.parse(instructions))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("valid instructions sequence")
    void testValidInstructionSequence() {
        assertThat(underTest.parse("RLFFLR"))
                .isEqualTo(List.of(R, L, F, F, L, R));
    }
}
