package org.test.robots.io;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileInputStringParserTest {

    private static final String RELATIVE_PATH = "src/test/resources/samples/input/";
    private static final String SAMPLE_FILE_ONE_ROBOT = RELATIVE_PATH.concat("sample-1-robot.txt");
    private static final String SAMPLE_EMPTY = RELATIVE_PATH.concat("sample-empty.txt");

    private final FileInputStringParser underTest = new FileInputStringParser();

    @Test
    @DisplayName("read empty file")
    void testReadEmptyFile() throws IOException {
        assertThat(underTest.parse(SAMPLE_EMPTY).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("read valid file with 1 robot")
    void testRead1RobotFile() throws IOException {
        assertThat(underTest.parse(SAMPLE_FILE_ONE_ROBOT).size()).isEqualTo(3);
    }
}
