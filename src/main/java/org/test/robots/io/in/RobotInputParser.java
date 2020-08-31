package org.test.robots.io.in;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.robots.domain.Pair;
import org.test.robots.domain.input.RobotInput;

@Component
@RequiredArgsConstructor
public class RobotInputParser implements Parser<Pair<String, String>, RobotInput> {

    private final PositionParser positionParser;
    private final InstructionsParser instructionsParser;

    public RobotInput parse(@NonNull final Pair<String, String> robotInput) {
        return RobotInput.with(
                positionParser.parse(robotInput.getFirst()),
                instructionsParser.parse(robotInput.getSecond()));
    }
}
