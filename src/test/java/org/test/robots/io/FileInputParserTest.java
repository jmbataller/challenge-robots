package org.test.robots.io;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileInputParserTest {

    private static final String RELATIVE_PATH = "src/test/resources/samples/input/";
    private static final String SAMPLE_FILE_ONE_ROBOT = RELATIVE_PATH.concat("sample-1-robot.txt");

    private final FileInputStringParser mockFileInputStringParser = Mockito.mock(FileInputStringParser.class);
    private final GridSizeParser mockGridSizeParser = Mockito.mock(GridSizeParser.class);
    private final RobotInputParser mockRobotInputParser = Mockito.mock(RobotInputParser.class);

    private final FileInputParser underTest = new FileInputParser(mockFileInputStringParser, mockGridSizeParser, mockRobotInputParser);

    @Test
    @DisplayName("read valid file with 1 robot")
    void testReadValidFile() throws IOException {
        assertThat(underTest.parse(SAMPLE_FILE_ONE_ROBOT)).isNull();
    }

    @Test
    @DisplayName("attempt to read a file that doesn't exist")
    void testReadNonExistingFile() {
        assertThatThrownBy(() -> underTest.parse("invalid/file/path"))
                .isInstanceOf(IOException.class);
    }
}
