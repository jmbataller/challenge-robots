package org.test.robots.io.in;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.test.robots.domain.Instruction;
import org.test.robots.exceptions.ParseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstructionsParser implements Parser<String, List<Instruction>> {

    private final int INSTRUCTIONS_LIMIT = 100;

    public List<Instruction> parse(@NonNull final String line) {
        return Optional.of(line)
                .filter(l -> l.length() < INSTRUCTIONS_LIMIT)
                .map(l -> l.chars()
                        .mapToObj(i -> (char) i)
                        .map(c -> Instruction.valueOf(c.toString()))
                        .collect(Collectors.toList()))
                .orElseThrow(ParseException::newInstructionsLimitException);
    }
}
