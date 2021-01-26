package programming.set6.plotter;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * @author Bennet Blessmann
 *         Created on 04.12.2016.
 */
public class GraphPlotter extends GraphicsProgram {

	/**
	 * Width of the drawing.
	 */
	private static final int WIDTH = 600;
	/**
	 * Height of the drawing.
	 */
	private static final int HEIGHT = 400;
	/**
	 * Min x coordinate.
	 */
	private static final double MIN_X = -1.5;
	/**
	 * Max x coordinate.
	 */
	private static final double MAX_X = 5.5;
	/**
	 * X tick interval.
	 */
	private static final double X_TICK_INTERVAL = 1;
	/**
	 * Min y coordinate.
	 */
	private static final double MIN_Y = -2.5;
	/**
	 * Max y coordinate.
	 */
	private static final double MAX_Y = 2.5;
	/**
	 * Y tick interval.
	 */
	private static final double Y_TICK_INTERVAL = 1;

	/**
	 * The Color for the graphs axis and marks
	 */
	private static final Color AXIS_COLOR = Color.GRAY;

	public void run() {
		drawXAxis();
		drawYAxis();
		plotGraph();
		drawRects(MIN_X, MAX_X, 0.2, false);
		drawRects(MIN_X, MAX_X, 0.2, true);
	}

	/**
	 * The function whose area to calculate.
	 * *
	 *
	 * @param x the x coordinate.
	 * @return the value of f at the x coordinate.
	 */
	public double f(double x) {
		// This is the function whose area we want to calculate. Hardcoding
		// it here is a bit unfortunate; we can improve the design once we
		// know more about classes and interfaces
		return Math.sin(x) + Math.cos(0.5 * x);
	}

	/**
	 * Calculates the height of a rectangle at the given x coordinate and with
	 * the given width. The height of the rectangle is the function value at its
	 * left and right side that is nearer to the x axis. If the function value
	 * is negative, the height is negative.
	 * *
	 *
	 * @param x     the rectangle's x coordinate (left boundary).
	 * @param width the rectangle's width.
	 * @return the rectangle's height, which may actually be negative.
	 */
	public double minRectHeight(double x, double width) {
		double f1 = f(x), f2 = f(x + width);
		//check which is closer to the x-Axis and return that
		return Math.abs(f1) < Math.abs(f2) ? f1 : f2;
	}

	/**
	 * Calculates the height of a rectangle at the given x coordinate and with
	 * the given width. The height of the rectangle is the function value at its
	 * left and right side that is further from the x axis. If the function value
	 * is negative, the height is negative.
	 * *
	 *
	 * @param x     the rectangle's x coordinate (left boundary).
	 * @param width the rectangle's width.
	 * @return the rectangle's height, which may actually be negative.
	 */
	public double maxRectHeight(double x, double width) {
		double f1 = f(x), f2 = f(x + width);
		//check which is further from the x-Axis and return that
		return Math.abs(f1) > Math.abs(f2) ? f1 : f2;
	}

	/**
	 * Calculates the effective x screen coordinate for the given x coordinate
	 * in the graph's coordinate system.
	 *
	 * @param x the x coordinate.
	 * @return the screen coordinate.
	 */
	public double xToScreen(double x) {
		return (x - MIN_X) * WIDTH / (MAX_X - MIN_X);
	}

	/**
	 * Calculates the coordinate in the coordinate system for the given x
	 * coordinate on the screen.
	 *
	 * @param screenX screen x coordinate.
	 * @return the coordinate system coordinate.
	 */
	public double screenToX(double screenX) {
		return (screenX * (MAX_X - MIN_X) / WIDTH) + MIN_X;
	}

	/**
	 * Calculates the effective y screen coordinate for the given y coordinate
	 * in the graph's coordinate system.
	 *
	 * @param y the y coordinate.
	 * @return the screen coordinate.
	 */
	public double yToScreen(double y) {
		return (MAX_Y - y) * HEIGHT / (MAX_Y - MIN_Y);
	}

	/**
	 * Sets up the x axis of the coordinate system. The method assumes that the
	 * coordinate system's top left corner is at (0, 0). Its width and height as
	 * well as the tick marks are controlled through the constants defined in
	 * this class.
	 */
	public void drawXAxis() {
		if (MIN_X < 0) {
			GLine xAxis = new GLine(0, yToScreen(0), WIDTH, yToScreen(0));
			xAxis.setColor(AXIS_COLOR);
			add(xAxis);

			GLine mark;
			GLabel marker;
			for (double i = X_TICK_INTERVAL; MAX_X > i || -i > MIN_X; i += X_TICK_INTERVAL) {
				if (MAX_X > i) {
					mark = new GLine(xToScreen(i), yToScreen(0) + 5, xToScreen(i), yToScreen(0) - 5);
					mark.setColor(AXIS_COLOR);
					add(mark);

					marker = new GLabel("" + i, 0, 0);
					marker.setLocation(xToScreen(i) - marker.getWidth() / 2, marker.getAscent() + yToScreen(0) + 5);
					marker.setColor(AXIS_COLOR);
					add(marker);
				}
				if (MIN_X < -i) {
					mark = new GLine(xToScreen(-i), yToScreen(0) + 5, xToScreen(-i), yToScreen(0) - 5);
					mark.setColor(AXIS_COLOR);
					add(mark);

					marker = new GLabel("" + -i, 0, 0);
					marker.setLocation(xToScreen(-i) - marker.getWidth() / 2, marker.getAscent() + yToScreen(0) + 5);
					marker.setColor(AXIS_COLOR);
					add(marker);
				}
			}
		}
	}

	/**
	 * Sets up the y axis of the coordinate system. The method assumes that the
	 * coordinate system's top left corner is at (0, 0). Its width and height as
	 * well as the tick marks are controlled through the constants defined in
	 * this class.
	 */
	public void drawYAxis() {
		if (MIN_Y < 1) {
			GLine yAxis = new GLine(xToScreen(0), 0, xToScreen(0), HEIGHT);
			yAxis.setColor(AXIS_COLOR);
			add(yAxis);

			GLine mark;
			GLabel marker;
			for (double i = Y_TICK_INTERVAL; MAX_Y > i || -i > MIN_Y; i += Y_TICK_INTERVAL) {
				if (MAX_Y > i) {
					mark = new GLine(xToScreen(0) + 5, yToScreen(i), xToScreen(0) - 5, yToScreen(i));
					mark.setColor(AXIS_COLOR);
					add(mark);

					marker = new GLabel("" + i, 0, 0);
					marker.setLocation(xToScreen(0) - marker.getWidth() - 5, yToScreen(i) + (marker.getAscent() - marker.getDescent()) / 2);
					marker.setColor(AXIS_COLOR);
					add(marker);
				}
				if (MIN_Y < -i) {
					mark = new GLine(xToScreen(0) + 5, yToScreen(-i), xToScreen(0) - 5, yToScreen(-i));
					mark.setColor(AXIS_COLOR);
					add(mark);

					marker = new GLabel("" + -i, 0, 0);
					marker.setLocation(xToScreen(0) - marker.getWidth() - 5, yToScreen(-i) + (marker.getAscent() - marker.getDescent()) / 2);
					marker.setColor(AXIS_COLOR);
					add(marker);
				}
			}
		}
	}

	/**
	 * Plots the graph by iterating over all x coordinates and generating
	 * points. A point is only added if it's in the visible area. Points are
	 * visualized through tiny rectangles with a side length of 1.
	 */
	public void plotGraph() {
		double y;
		for (int x = 0; x < WIDTH; x++) {
			y = yToScreen(f(screenToX(x)));
			if (y < HEIGHT) {
				GRect point = new GRect(x, y, 1, 1);
				add(point);
			}
		}
	}

	/**
	 * Draws the rectangles used to approximate the graph's area. All inputs are
	 * specified in the graph's coordinate system.
	 *
	 * @param minX      leftmost rectangle coordinate.
	 * @param maxX      rightmost rectangle coordinate.
	 * @param rectWidth width of the rectangles.
	 * @param minRects  if {@code true}, the min rects are drawn; otherwise, the max
	 *                  rects are drawn.
	 */
	public void drawRects(double minX, double maxX, double rectWidth, boolean minRects) {
		double x0, y0, x1, y1, x, y, width, height;
		GRect cur;
		for (double i = minX; i < maxX; i += rectWidth) {
			x0 = xToScreen(i);
			x1 = xToScreen((i + rectWidth) < maxX ? i + rectWidth : maxX);
			x = x0 > x1 ? x1 : x0;
			width = Math.abs(x0 - x1);

			y0 = yToScreen(0);
			y1 = yToScreen(minRects ? minRectHeight(i, i + rectWidth < maxX ? rectWidth : maxX - i) : maxRectHeight(i, i + rectWidth < maxX ? rectWidth : maxX - i));
			y = y0 > y1 ? y1 : y0;
			height = Math.abs(y1 - y0);

			cur = new GRect(x, y, width, height);
			cur.setFilled(true);
			cur.setColor(Color.BLUE);
			cur.setFillColor(minRects ? new Color(0, 0, 50, 50) : new Color(50, 0, 0, 50));
			add(cur);
		}
	}

}