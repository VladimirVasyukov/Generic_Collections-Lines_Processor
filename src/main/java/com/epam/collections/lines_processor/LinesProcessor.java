package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.figures.Line;
import com.epam.collections.lines_processor.figures.Point;

import java.util.*;

/**
 * Class for working with inventory
 */
public class LinesProcessor {

    public static void main(String[] args) {
        throw new UnsupportedOperationException("You need to implement this method");
    }

    /**
     * Generate all available lines by list of points (with duplicates). Result size should be equals to (n ^ 2) - n
     * @param points Points to generate lines
     * @return List of all available lines
     */
    public List<Line> mapPointsToLines(List<Point> points) {
        throw new UnsupportedOperationException("You need to implement this method");
    }

    /**
     * Reduce amount of lines - unite same lines build by different points in one line
     * @param lines List of lines to be reduced
     * @return List of lines without duplicates
     */
    public List<Line> reduceLines(List<Line> lines) {
        throw new UnsupportedOperationException("You need to implement this method");
    }

}
