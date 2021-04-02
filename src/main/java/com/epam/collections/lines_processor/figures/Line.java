package com.epam.collections.lines_processor.figures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * Class that stores lines data, represents by equation y = kx + b
 */
public class Line {
    private static final int CALCULATION_PRECISION = 2;
    private Set<Point> linePoints = new HashSet<>();
    private double slope;
    private double yIntercept;
    private Integer verticalLineX;

    public Line(Point point1, Point point2) {
        this.linePoints.add(point1);
        this.linePoints.add(point2);
        setK();
        setB();
    }

    public Set<Point> getPoints() {
        return new HashSet<>(linePoints);
    }

    public double getK() {
        double k = slope;
        if (Double.isFinite(k)) {
            k = BigDecimal.valueOf(k).setScale(CALCULATION_PRECISION, RoundingMode.HALF_UP).doubleValue();
        }
        return k;
    }

    public double getB() {
        double b = yIntercept;
        if (Double.isFinite(b)) {
            b = BigDecimal.valueOf(b).setScale(CALCULATION_PRECISION, RoundingMode.HALF_UP).doubleValue();
        }
        return b;
    }

    public Integer getVerticalLineX() {
        return verticalLineX;
    }

    public void setPoints(Set<Point> points) {
        this.linePoints = new HashSet<>(points);
    }

    private void setK() {
        LinkedList<Point> pointList = new LinkedList<>(linePoints);
        Iterator<Point> pointIterator = pointList.descendingIterator();
        Point currentPoint = pointIterator.next();
        int yCoordinateChange = currentPoint.getY();
        int xCoordinateChange = currentPoint.getX();
        while (pointIterator.hasNext()) {
            currentPoint = pointIterator.next();
            yCoordinateChange -= currentPoint.getY();
            xCoordinateChange -= currentPoint.getX();
        }
        this.slope = yCoordinateChange / (double) xCoordinateChange;
    }

    private void setB() {
        Point point = linePoints.iterator().next();
        this.yIntercept = point.getY() - slope * point.getX();
        if (Double.isInfinite(yIntercept)) {
            this.verticalLineX = point.getX();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Line line = (Line) o;
        return Double.compare(line.slope, slope) == 0 && Double.compare(line.yIntercept, yIntercept) == 0 &&
            Objects.equals(linePoints, line.linePoints) && Objects.equals(verticalLineX, line.verticalLineX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linePoints, slope, yIntercept, verticalLineX);
    }
}
