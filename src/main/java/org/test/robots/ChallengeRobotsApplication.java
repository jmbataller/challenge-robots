package org.test.robots;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.robots.domain.Orientation;
import org.test.robots.domain.Point;
import org.test.robots.domain.Position;
import org.test.robots.domain.input.RobotsListInput;
import org.test.robots.domain.output.RobotOutput;
import org.test.robots.domain.output.RobotsListOutput;
import org.test.robots.io.in.FileInputParser;
import org.test.robots.io.out.FileOutputSerialiser;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class ChallengeRobotsApplication implements CommandLineRunner {

    private final FileInputParser fileInputParser;
    private final FileOutputSerialiser fileOutputSerialiser;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeRobotsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (Objects.isNull(args) || args.length == 0) {
            System.out.println("Usage:");
            System.out.println("java -jar challenge-robots.jar <file_path>");
            System.exit(-1);
        }

        var filename = args[0];
        log.info("Filename is {}", filename);
        RobotsListInput input = fileInputParser.parse(filename);


        // TODO: processor



        RobotsListOutput output = RobotsListOutput.with(List.of(
                RobotOutput.with(Position.with(Point.of(1, 1), Orientation.E), false),
                RobotOutput.with(Position.with(Point.of(1, 1), Orientation.E), true)
        ));

        // save output file in the same folder as input file with `.out` extension
        fileOutputSerialiser.serialise(filename.concat(".out"), output);
    }
}
