package org.test.robots.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.test.robots.domain.Grid;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.domain.output.RobotOutput;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.test.robots.domain.Instruction.*;
import static org.test.robots.domain.Orientation.*;

public class RobotProcessorTest {

    private RobotProcessor underTest = new RobotProcessor();

    private Grid gridZero = Grid.size(0, 0);
    private Point pointZero = Point.of(0, 0);

    @Test
    @DisplayName("process Left instruction")
    void testProcessLeftInstruction() {
        // given
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(L));

        // when
        var robotOutput = underTest.process(gridZero, robotInput, List.of());

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(pointZero, W), false));
    }

    @Test
    @DisplayName("process Right instruction")
    void testProcessRightInstruction() {
        // given
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(R));

        // when
        var robotOutput = underTest.process(gridZero, robotInput, List.of());

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(pointZero, E), false));
    }

    @Test
    @DisplayName("process Forward instruction")
    void testProcessForwardInstruction() {
        // given
        var grid = Grid.size(0, 1);
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(F));

        // when
        var robotOutput = underTest.process(grid, robotInput, List.of());

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(Point.of(0, 1), N), false));
    }

    @Test
    @DisplayName("process Forward instruction and robot gets lost")
    void testProcessForwardAndRobotGetsLost() {
        // given
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(F));

        // when
        var robotOutput = underTest.process(gridZero, robotInput, List.of());

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(pointZero, N), true));
    }

    @Test
    @DisplayName("ignore Forward instruction when exist scent")
    void testIgnoreForwardWhenExistsScent() {
        // given
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(F));

        // when
        var robotOutput = underTest.process(gridZero, robotInput, List.of(Position.with(pointZero, N)));

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(pointZero, N), false));
    }

    @Test
    @DisplayName("process multiple instructions")
    void testProcessMultipleInstructions() {
        // given
        var grid = Grid.size(0, 3);
        var robotInput = RobotInput.with(Position.with(pointZero, N), List.of(R, R, R, R, L, L, L, L, F, F, F));

        // when
        var robotOutput = underTest.process(grid, robotInput, List.of());

        // then
        assertThat(robotOutput).isEqualTo(RobotOutput.with(Position.with(Point.of(0, 3), N), false));
    }
}
