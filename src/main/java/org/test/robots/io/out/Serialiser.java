package org.test.robots.io.out;

public interface Serialiser<F, O> {

    void serialise(String filename, O output);
}
