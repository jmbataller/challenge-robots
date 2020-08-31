package org.test.robots.io.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.test.robots.domain.output.RobotsListOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class FileOutputSerialiser implements Serialiser<String, RobotsListOutput> {

    public void serialise(final String filename, final RobotsListOutput output) {
        log.info("\n------------------ OUTPUT ---------------------");
        try (var fileWriter = new FileWriter(filename); var printWriter = new PrintWriter(fileWriter)) {
            output.getRobots()
                    .stream()
                    .peek(r -> log.info(r.toString()))
                    .forEach(r -> printWriter.println(r.toString()));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
