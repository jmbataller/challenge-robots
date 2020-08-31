package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.*;
import org.test.robots.domain.input.RobotInput;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.test.robots.domain.Instruction.F;
import static org.test.robots.domain.Instruction.R;

public class FileInputParserTest {

    private final FileInputStringParser mockFileInputStringParser = mock(FileInputStringParser.class);
    private final GridSizeParser mockGridSizeParser = mock(GridSizeParser.class);
    private final RobotInputParser mockRobotInputParser = mock(RobotInputParser.class);

    private final FileInputParser underTest = new FileInputParser(mockFileInputStringParser, mockGridSizeParser, mockRobotInputParser);

    @Test
    @DisplayName("parse a valid file with 1 robot")
    void testParseAValidFile() throws IOException {
        // GIVEN
        var strGridSize = "5 3";
        var strPosition = "1 1 E";
        var strInstructions = "RFRFRFRF";
        var expectedGrid = Grid.size(5, 3);
        var expectedRobot = RobotInput.with(
                Position.with(Point.of(1, 1), Orientation.E),
                List.of(R, F, R, F, R, F, R, F)
        );

        when(mockFileInputStringParser.parse(anyString())).thenReturn(List.of(strGridSize, strPosition, strInstructions, ""));
        when(mockGridSizeParser.parse(strGridSize)).thenReturn(expectedGrid);
        when(mockRobotInputParser.parse(Pair.of(strPosition, strInstructions))).thenReturn(expectedRobot);

        // WHEN
        var input = underTest.parse("dummy file");

        // THEN
        assertThat(input.getGrid()).isEqualTo(expectedGrid);
        assertThat(input.getRobots().get(0)).isEqualTo(expectedRobot);

        verify(mockFileInputStringParser, times(1)).parse(anyString());
        verify(mockGridSizeParser, times(1)).parse(anyString());
        verify(mockRobotInputParser, times(1)).parse(any(Pair.class));
    }

    @Test
    @DisplayName("parse a valid file with multiple robots")
    void testParseAValidFileWithMultipleRobotEntries() throws IOException {
        // GIVEN
        var strGridSize = "5 3";
        var strPosition = "1 1 E";
        var strInstructions = "RFRFRFRF";
        var expectedGrid = Grid.size(5, 3);
        var expectedRobot = RobotInput.with(
                Position.with(Point.of(1, 1), Orientation.E),
                List.of(R, F, R, F, R, F, R, F)
        );

        when(mockFileInputStringParser.parse(anyString())).thenReturn(
                List.of(
                        strGridSize,
                        strPosition, strInstructions, "",
                        strPosition, strInstructions, "",
                        strPosition, strInstructions, ""
                ));
        when(mockGridSizeParser.parse(strGridSize)).thenReturn(expectedGrid);
        when(mockRobotInputParser.parse(Pair.of(strPosition, strInstructions))).thenReturn(expectedRobot);

        // WHEN
        var input = underTest.parse("dummy file");

        // THEN
        assertThat(input.getGrid()).isEqualTo(expectedGrid);
        assertThat(input.getRobots().get(0)).isEqualTo(expectedRobot);
        assertThat(input.getRobots().get(1)).isEqualTo(expectedRobot);
        assertThat(input.getRobots().get(2)).isEqualTo(expectedRobot);

        verify(mockFileInputStringParser, times(1)).parse(anyString());
        verify(mockGridSizeParser, times(1)).parse(anyString());
        verify(mockRobotInputParser, times(3)).parse(any(Pair.class));
    }

}
