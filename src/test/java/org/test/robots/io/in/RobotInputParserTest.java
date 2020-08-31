package org.test.robots.io.in;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.Pair;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.io.in.InstructionsParser;
import org.test.robots.io.in.PositionParser;
import org.test.robots.io.in.RobotInputParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.test.robots.domain.Instruction.*;
import static org.test.robots.domain.Orientation.N;

public class RobotInputParserTest {

    private PositionParser mockPositionParser = mock(PositionParser.class);
    private InstructionsParser mockInstructionParser = mock(InstructionsParser.class);
    private RobotInputParser underTest = new RobotInputParser(mockPositionParser, mockInstructionParser);

    @Test
    @DisplayName("input is valid")
    void testValidGridSize() {
        var expectedPosition = Position.with(Point.of(0, 0), N);
        var expectedInstructions = List.of(R, L, F);
        when(mockPositionParser.parse(anyString())).thenReturn(expectedPosition);
        when(mockInstructionParser.parse(anyString())).thenReturn(expectedInstructions);

        assertThat(underTest.parse(Pair.of("0 0 N", "RLF")))
                .isEqualTo(RobotInput.with(expectedPosition, expectedInstructions));

        verify(mockPositionParser, times(1)).parse("0 0 N");
        verify(mockInstructionParser, times(1)).parse("RLF");
    }

}
