package org.test.robots.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Orientation {

    N("N", "W", "E"),
    E("E", "N", "S"),
    S("S", "E", "W"),
    W("W", "S", "N");

    @Getter
    private String value;
    private String left;
    private String right;
}
