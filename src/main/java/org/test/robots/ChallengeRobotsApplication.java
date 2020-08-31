package org.test.robots;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.test.robots.io.FileInputParser;

import java.util.Objects;

@SpringBootApplication
@Slf4j
public class ChallengeRobotsApplication implements CommandLineRunner, FileInputParser {

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
        parse(filename);

    }
}
