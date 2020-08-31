package org.test.robots.io.in;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Component
public class FileInputStringParser implements Parser<String, List<String>> {

    /**
     * Expects absolute path filename and parses the input file into a list of String lines
     *
     * @param filename
     * @return
     */
    public List<String> parse(String filename) throws IOException {
        var file = ResourceUtils.getFile(filename);
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
    }

}
