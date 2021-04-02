package com.epam.collections.lines_processor.utils;

import com.epam.collections.lines_processor.figures.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class PointsReader {
    private static final String POINT_FIELD_DELIMITER = ";";
    private static final Logger LOG = LogManager.getLogger(PointsReader.class);

    public Set<Point> getPointsFromFile(File file) {
        Set<Point> pointSet = new HashSet<>();
        try (
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
        ) {
            while (reader.ready()) {
                String line = reader.readLine();
                int xCoordinate = Integer.parseInt(line.substring(0, line.indexOf(POINT_FIELD_DELIMITER)));
                int yCoordinate = Integer.parseInt(line.substring(line.indexOf(POINT_FIELD_DELIMITER) + 1));
                pointSet.add(new Point(xCoordinate, yCoordinate));
            }
        } catch (IOException ioException) {
            LOG.error("Unexpected IOException in the getPointsFromFile method!", ioException);
        }
        return pointSet;
    }
}
