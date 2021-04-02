package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.figures.Point;
import com.epam.collections.lines_processor.utils.PointsReader;

import java.io.File;
import java.util.Set;

public class Controller {
    private final LinesProcessor linesProcessor;
    private final View view;
    private final String pointsFilePath;
    private Set<Point> points;

    public Controller(
        LinesProcessor linesProcessor, View view, String pointsFilePath) {
        this.linesProcessor = linesProcessor;
        this.view = view;
        this.pointsFilePath = pointsFilePath;
    }

    public void getPointsFromFile() {
        this.points = new PointsReader().getPointsFromFile(new File(pointsFilePath));
    }

    public void findLines() {
        view.printLines(linesProcessor.reduceLines(linesProcessor.mapPointsToLines(points)));
    }
}
