package com.epam.collections.lines_processor.figures;

import java.util.Objects;

/**
 * Class that stores points data
 */
public class Point {
    private final int xCoordinate;
    private final int yCoordinate;

    public Point(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return xCoordinate == point.xCoordinate && yCoordinate == point.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + xCoordinate +
            ", y=" + yCoordinate +
            '}';
    }
}
