package org.test.robots.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.Grid;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.domain.input.RobotsListInput;
import org.test.robots.domain.output.RobotOutput;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.test.robots.domain.Instruction.*;

public class RobotBatchProcessorTest {

    private final RobotProcessor mockProcessor = mock(RobotProcessor.class);
    private final RobotBatchProcessor underTest = new RobotBatchProcessor(mockProcessor);

    private Grid gridZero = Grid.size(0, 0);
    private Point pointZero = Point.of(0, 0);

    @Test
    @DisplayName("process a list that contains one robot")
    void testProcessOneRobot() {
        var inputList = RobotsListInput.with(gridZero, List.of(RobotInput.with(
                Position.with(pointZero, Orientation.N),
                List.of(L, R))));
        var expectedRobotOutput = RobotOutput.with(Position.with(pointZero, Orientation.N), false);

        when(mockProcessor.process(any(Grid.class), any(RobotInput.class), anyList()))
            .thenReturn(expectedRobotOutput);

        var outputList = underTest.process(inputList);

        assertThat(outputList.getRobots().get(0)).isEqualTo(expectedRobotOutput);
        verify(mockProcessor, times(1)).process(any(Grid.class), any(RobotInput.class), anyList());
    }

    @Test
    @DisplayName("process a list that contains multiple robots")
    void testProcessMultipleRobots() {
        var expectedNumRobots = 3;
        var robotInput = RobotInput.with(
                Position.with(pointZero, Orientation.N),
                List.of(L, R));
        var inputList = RobotsListInput.with(gridZero, List.of(robotInput, robotInput, robotInput));
        var expectedRobotOutput = RobotOutput.with(Position.with(pointZero, Orientation.N), false);

        when(mockProcessor.process(any(Grid.class), any(RobotInput.class), anyList()))
            .thenReturn(expectedRobotOutput);

        var outputList = underTest.process(inputList);

        assertThat(outputList.getRobots().size()).isEqualTo(expectedNumRobots);
        verify(mockProcessor, times(expectedNumRobots)).process(any(Grid.class), any(RobotInput.class), anyList());
    }

    @Test
    @DisplayName("process a list that contains a lost robot")
    void testProcessMultipleRobotsThatIncludeLost() {
        var robotInput = RobotInput.with(
                Position.with(pointZero, Orientation.N),
                List.of(F));
        var inputList = RobotsListInput.with(gridZero, List.of(robotInput, robotInput, robotInput));
        var lostRobotOutput = RobotOutput.with(Position.with(pointZero, Orientation.N), true);
        var expRobotOutput = RobotOutput.with(Position.with(pointZero, Orientation.N), false);

        when(mockProcessor.process(gridZero, robotInput, List.of())).thenReturn(lostRobotOutput);
        when(mockProcessor.process(gridZero, robotInput, List.of(Position.with(pointZero, Orientation.N)))).thenReturn(expRobotOutput);

        var outputList = underTest.process(inputList);

        assertThat(outputList.getRobots().get(0)).isEqualTo(lostRobotOutput);
        assertThat(outputList.getRobots().get(1)).isEqualTo(expRobotOutput);
        assertThat(outputList.getRobots().get(2)).isEqualTo(expRobotOutput);
        verify(mockProcessor, times(3)).process(any(Grid.class), any(RobotInput.class), anyList());
    }
}
