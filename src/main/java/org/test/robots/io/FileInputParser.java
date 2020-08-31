package org.test.robots.io;

import org.test.robots.domain.input.RobotsListInput;

import java.io.IOException;
import java.util.List;

public interface FileInputParser extends Parser<String, RobotsListInput> {

    /**
     * Expects absolute path filename and parses the input file into a list of String lines
     *
     * @param filename
     * @return
     */
    @Override
    default RobotsListInput parse(String filename) throws IOException {
        List<String> lines = FileInputStringParser.parse(filename);
        var grid = GridSizeParser.parse(lines.get(0));
        return null;
    }
}
