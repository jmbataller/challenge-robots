package org.test.robots.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor(staticName = "size")
public class Grid {

    private final int sizeX;
    private final int sizeY;

}
