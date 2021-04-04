package com.epam.collections.lines_processor;

import com.epam.collections.lines_processor.figures.Line;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

public class View {
    private static final String NOT_VERTICAL_LINE = "Line: y = ";
    private static final String FLOAT_FORMAT = "%.2f";
    private static final String FLOAT_FORMAT_PLUS = " + %.2f";
    private static final String POINTS = ", points: ";
    private static final String DO_NOT_PRINT = "";
    private static final String X_SYMBOL = "x";
    private static final Logger LOG = LogManager.getLogger(View.class);

    public void printLines(Iterable<Line> lines) {
        for (Line line : lines) {
            double yIntercept = line.getB();
            if (Double.isInfinite(yIntercept)) {
                LOG.info(String.format("Line: x = %d points: = %s %n", line.getVerticalLineX(), line.getPoints()));
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                Locale.setDefault(Locale.US);
                stringBuilder.append(String.format("%s", NOT_VERTICAL_LINE));
                double slope = line.getK();
                if (Double.compare(slope, 0) != 0) {
                    stringBuilder.append(String.format(FLOAT_FORMAT + X_SYMBOL, slope));
                }
                stringBuilder.append(appendYIntercept(yIntercept));
                stringBuilder.append(String.format("%s%s%n", POINTS, line.getPoints()));
                LOG.info(stringBuilder.toString());
            }
        }
    }

    private String appendYIntercept(double yIntercept) {
        String yInterceptString;
        if (Double.compare(yIntercept, 0) == 0) {
            yInterceptString = DO_NOT_PRINT;
        } else {
            String yInterceptFormat;
            if (yIntercept < 0) {
                yInterceptFormat = FLOAT_FORMAT;
            } else {
                yInterceptFormat = FLOAT_FORMAT_PLUS;
            }
            yInterceptString = String.format(yInterceptFormat, yIntercept);
        }
        return yInterceptString;
    }
}
