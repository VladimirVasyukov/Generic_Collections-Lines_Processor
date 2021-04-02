package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.figures.Line;
import com.epam.collections.lines_processor.figures.Point;

import java.util.*;

/**
 * 06_Generic_Collections - Vladimir Vasyukov
 * Class for working with inventory
 */
public class LinesProcessor {

    public static void main(String[] args) {
        Controller controller = new Controller(new LinesProcessor(), new View(), args[0]);
        controller.getPointsFromFile();
        controller.findLines();
    }

    /**
     * Generate all available lines by list of points (with duplicates). Result size should be equals to (n ^ 2) - n
     *
     * @param points Points to generate lines
     * @return List of all available lines
     */
    public List<Line> mapPointsToLines(Iterable<Point> points) {
        List<Line> lines = new ArrayList<>();
        for (Point pointOne : points) {
            for (Point pointTwo : points) {
                if (!pointOne.equals(pointTwo)) {
                    lines.add(new Line(pointOne, pointTwo));
                }
            }
        }
        return lines;
    }

    /**
     * Reduce amount of lines - unite same lines build by different points in one line
     *
     * @param lines List of lines to be reduced
     * @return List of lines without duplicates
     */
    public List<Line> reduceLines(Iterable<Line> lines) {
        Set<Line> reducedLines = new HashSet<>();
        for (Line line : lines) {
            if (!reducedLines.contains(line) && !isSamePoints(line, reducedLines)) {
                reducedLines = addPointsToLines(line, reducedLines);
            }
        }
        return new ArrayList<>(reducedLines);
    }

    private Set<Line> addPointsToLines(Line line, Set<Line> reducedLines) {
        Set<Line> lines = new HashSet<>(reducedLines);
        boolean addedPointToLine = false;
        for (Line lineFromReducedLines : lines) {
            if (isSameFormula(line, lineFromReducedLines)) {
                Set<Point> increasedPointSet = new HashSet<>(line.getPoints());
                increasedPointSet.addAll(lineFromReducedLines.getPoints());
                lineFromReducedLines.setPoints(increasedPointSet);
                addedPointToLine = true;
            }
        }
        if (!addedPointToLine) {
            lines.add(line);
        }
        return lines;
    }

    private boolean isSamePoints(Line line, Set<Line> lines) {
        boolean setHasLineWithSamePoints = false;
        for (Line setLine : lines) {
            if (line.getPoints().equals(setLine.getPoints())) {
                setHasLineWithSamePoints = true;
                break;
            }
        }
        return setHasLineWithSamePoints;
    }

    private boolean isSameFormula(Line lineOne, Line lineTwo) {
        boolean areSameLines = false;
        if (Double.isFinite(lineOne.getB()) &&
            Double.compare(lineOne.getK(), lineTwo.getK()) == 0 &&
            Double.compare(lineOne.getB(), lineTwo.getB()) == 0) {
            areSameLines = true;
        }
        if (Double.isInfinite(lineOne.getB()) &&
            Double.compare(lineOne.getK(), lineTwo.getK()) == 0 &&
            Double.compare(lineOne.getB(), lineTwo.getB()) == 0 &&
            lineOne.getVerticalLineX().equals(lineTwo.getVerticalLineX())) {
            areSameLines = true;
        }
        return areSameLines;
    }
}
