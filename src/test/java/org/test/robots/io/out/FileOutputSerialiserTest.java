package org.test.robots.io.out;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.domain.output.RobotOutput;
import org.test.robots.domain.output.RobotsListOutput;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class FileOutputSerialiserTest {

    private static final String RELATIVE_PATH = "src/test/resources/samples/output/";
    private static final String SAMPLE_FILE_ONE_ROBOT = RELATIVE_PATH.concat("sample-1-robot.txt.out");

    private final FileOutputSerialiser underTest = new FileOutputSerialiser();

    @Test
    @DisplayName("serialise a valid output")
    void testSerialiseOutput() throws IOException {
        RobotsListOutput output = RobotsListOutput.with(List.of(
                RobotOutput.with(Position.with(Point.of(1, 1), Orientation.E), false),
                RobotOutput.with(Position.with(Point.of(1, 1), Orientation.E), true)
        ));

        underTest.serialise(SAMPLE_FILE_ONE_ROBOT, output);

        var outputFileLines = loadFile(SAMPLE_FILE_ONE_ROBOT);
        Assertions.assertThat(outputFileLines).contains(
                output.getRobots().get(0).toString(),
                output.getRobots().get(1).toString()
        );
    }

    private List<String> loadFile(final String filename) throws IOException {
        var file = ResourceUtils.getFile(filename);
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
    }
}
