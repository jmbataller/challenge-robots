package org.test.robots.io;

import java.io.IOException;

public interface Parser<I, O> {

    O parse(I input) throws IOException;
}
