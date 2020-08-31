package org.test.robots.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.robots.domain.Grid;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.domain.input.RobotsListInput;
import org.test.robots.domain.output.RobotOutput;
import org.test.robots.domain.output.RobotsListOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RobotBatchProcessor {

    private final RobotProcessor processor;

    public RobotsListOutput process(final RobotsListInput input) {
        var robots = process(input.getGrid(), input.getRobots(), new ArrayList<>(), new ArrayList<>());
        return RobotsListOutput.with(robots);
    }

    /**
     * Process recursively the batch of robots and keeps the list of scents updated
     * @param grid
     * @param robotInputs
     * @param scents
     * @param robotOutputs
     * @return
     */
    private List<RobotOutput> process(final Grid grid, final List<RobotInput> robotInputs,
                                      final List<Position> scents, final List<RobotOutput> robotOutputs) {
        if (robotInputs.isEmpty()) {
            return robotOutputs;
        } else {
            var robot = processor.process(grid, robotInputs.get(0), scents);
            Optional.of(robot).filter(r -> r.isLost()).ifPresent(r -> scents.add(r.getPosition()));
            robotOutputs.add(robot);
            return process(grid, robotInputs.subList(1, robotInputs.size()), scents, robotOutputs);
        }
    }
}
