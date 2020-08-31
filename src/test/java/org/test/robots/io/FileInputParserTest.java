package org.test.robots.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FileInputParserTest implements FileInputParser {

    private static final String RELATIVE_PATH = "src/test/resources/samples/input/";
    private final String SAMPLE_FILE_ONE_ROBOT = RELATIVE_PATH.concat("sample-1-robot.txt");

    @Test
    @DisplayName("read valid file with 1 robot")
    void testReadValidFile() throws IOException {
        assertThat(parse(SAMPLE_FILE_ONE_ROBOT)).isNull();
    }

    @Test
    @DisplayName("attempt to read a file that doesn't exist")
    void testReadNonExistingFile() {
        Assertions.assertThatThrownBy(() -> parse("invalid/file/path"))
                .isInstanceOf(IOException.class);
    }
}
