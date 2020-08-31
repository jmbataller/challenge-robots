package org.test.robots.io.in;

import java.io.IOException;

public interface Parser<I, O> {

    O parse(I input) throws IOException;
}
