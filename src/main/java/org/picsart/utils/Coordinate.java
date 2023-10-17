package org.picsart.utils;

public record Coordinate(double x, double y) implements Location {

    public int distanceTo(Coordinate other) {
        return (int) Math.round(Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y)));
    }
}
