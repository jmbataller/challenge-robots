package org.test.robots.services;

import org.springframework.stereotype.Service;
import org.test.robots.domain.Grid;
import org.test.robots.domain.Instruction;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotInput;
import org.test.robots.domain.output.RobotOutput;

import java.util.List;

@Service
public class RobotProcessor {

    public RobotOutput process(final Grid grid, final RobotInput input, final List<Position> scents) {
        return processInstructions(grid, input.getInstructions(), RobotOutput.with(input.getPosition(), false), scents);
    }

    /**
     * Process recursively the list of pending instructions
     * @param grid
     * @param instructions
     * @param currentPosition
     * @param scents
     * @return
     */
    private RobotOutput processInstructions(final Grid grid, final List<Instruction> instructions,
                                            final RobotOutput currentPosition, final List<Position> scents) {
        if (instructions.isEmpty() || currentPosition.isLost()) {
            return currentPosition;
        } else {
            var newPosition = processInstruction(grid, instructions.get(0), currentPosition, scents);
            return processInstructions(grid, instructions.subList(1, instructions.size()), newPosition, scents);
        }
    }

    /**
     * Process one instruction
     * @param grid
     * @param instruction
     * @param currentPosition
     * @param scents
     * @return
     */
    private RobotOutput processInstruction(final Grid grid, final Instruction instruction,
                                           final RobotOutput currentPosition, final List<Position> scents) {
        var orientedInstruction = currentPosition.getPosition().getOrientation().getInstruction();
        var position = currentPosition.getPosition();
        var newPosition = position;
        switch (instruction) {
            case F:
                newPosition = scents.contains(position) ? position : orientedInstruction.forward(position);
                break;
            case L:
                newPosition = orientedInstruction.left(position);
                break;
            case R:
                newPosition = orientedInstruction.right(position);
                break;
        }
        return grid.isLost(newPosition.getPoint())
                ? RobotOutput.with(currentPosition.getPosition(), true)
                : RobotOutput.with(newPosition, false);
    }
}
