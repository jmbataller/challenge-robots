package org.test.robots.io;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.robots.domain.Pair;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.domain.input.RobotsListInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileInputParser implements Parser<String, RobotsListInput> {

    private static int GRID_SIZE_LINE = 0;
    private static int LINES_PER_ROBOT = 3;
    private static int POSITION_LINE = 0;
    private static int INSTRUCTIONS_LINE = 1;

    private final FileInputStringParser fileInputStringParser;
    private final GridSizeParser gridSizeParser;
    private final RobotInputParser robotInputParser;

    /**
     * Expects absolute path filename and parses the input file into a list of String lines
     *
     * @param filename
     * @return
     */
    public RobotsListInput parse(String filename) throws IOException {
        var lines = fileInputStringParser.parse(filename);

        var grid = gridSizeParser.parse(lines.get(GRID_SIZE_LINE));
        var robotInputList = groupByPairs(lines.subList(GRID_SIZE_LINE + 1, lines.size()))
                .stream()
                .map(robotInputParser::parse)
                .collect(Collectors.toList());

        return RobotsListInput.with(grid, robotInputList);
    }

    /**
     * Groups robots lines and return a list of Pairs of [position, instruction]
     *
     * @param robotLines
     * @return
     */
    private List<Pair<String, String>> groupByPairs(final List<String> robotLines) {
        var lines = robotLines;
        var pairList = new ArrayList<Pair<String, String>>();

        while(isThereNextPair(lines)) {
            pairList.add(extractNextPair(lines));
            lines = robotLines.subList(LINES_PER_ROBOT, robotLines.size());
        }

        return pairList;
    }

    /**
     * Groups the position and instruction lines of a robot in a Pair
     * @param lines
     * @return
     */
    private Pair<String, String> extractNextPair(final List<String> lines) {
        return Pair.of(lines.get(POSITION_LINE), lines.get(INSTRUCTIONS_LINE));
    }

    private boolean isThereNextPair(final List<String> lines) {
        return lines.size() > LINES_PER_ROBOT;
    }
}
