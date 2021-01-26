import acm.program.ConsoleProgram;

/**
 * Created by Bennet on 19.11.2016.
 */
public class FunctionArea extends ConsoleProgram {

	public void run(){
		do {
			double left  = readDouble("LeftEdge: ");
			double right = readDouble("RightEdge: ");
			double width = readDouble("Width: ");
			println("(" + left + ":" + right + ":" + width + ")" + approxFunctionArea(left, right, width));
		}while(readBoolean("Continue?","Y","N"));
		System.exit(0);
	}

	/**
	 * The function whose area to calculate.
	 **
	 @param x
	  * the x coordinate.
	  * @return the value of f at the x coordinate.
	 */
	public double f(double x) {
		// This is the function whose area we want to calculate. Hardcoding
		// it here is a bit unfortunate; we can improve the design once we
		// know more about classes and interfaces
		return Math.sin(x) + Math.cos(0.5 * x);
		//return x;
	}

	/**
	 * Calculates the height of a rectangle at the given x coordinate and with
	 * the given width. The height of the rectangle is the function value at its
	 * left and right side that is nearer to the x axis. If the function value
	 * is negative, the height is negative.
	 **
	 @param x
	  * the rectangle's x coordinate (left boundary).
	  * @param width
	 * the rectangle's width.
	 * @return the rectangle's height, which may actually be negative.
	 */
	public double minRectHeight(double x, double width) {
		double f1 = f(x),f2 = f(x+width);
		//check which is closer to the x-Axis and return that
		return Math.abs(f1)<Math.abs(f2)?f1:f2;
	}

	/**
	 * Calculates the height of a rectangle at the given x coordinate and with
	 * the given width. The height of the rectangle is the function value at its
	 * left and right side that is further from the x axis. If the function value
	 * is negative, the height is negative.
	 **
	 @param x
	  * the rectangle's x coordinate (left boundary).
	  * @param width
	 * the rectangle's width.
	 * @return the rectangle's height, which may actually be negative.
	 */
	public double maxRectHeight(double x, double width) {
		double f1 = f(x),f2 = f(x+width);
		//check which is further from the x-Axis and return that
		return Math.abs(f1)>Math.abs(f2)?f1:f2;
	}

	/**
	 * Approximates the area enclosed by the x axis, {@link #f(double)}, and two
	 * vertical lines at {@code left} and {@code right}. The approximation
	 * divides the x axis section into different parts of width
	 * {@code rectWidth} (the rightmost part may have to be smaller to keep it
	 * from extending beyond the right boundary). For each part, the function
	 * computes the min and max rectangle and uses the min rectangle's area plus
	 * half the difference of the two rectangle areas as the approximate area
	 * for that part.
	 **
	 @param left
	  * left boundary.
	  * @param right
	 * right boundary.
	 * @param rectWidth
	 * width of the rectangles used to approximate the area.
	 * @return the approximate area. If the left boundary is right of the right
	 * boundary, the result is 0.
	 */
	public double approxFunctionArea(double left, double right, double rectWidth) {
		double all=0,leftBound = left,maxHeight,minHeight,cur;
		//you need to start before you can stop
		if(left>right){
			return 0;
		}

		//go till the end
		for(;leftBound<right;){
			//know your limits
			if(leftBound+rectWidth>right){
				rectWidth = right-leftBound;
			}

			maxHeight = (maxRectHeight(leftBound,rectWidth));
			minHeight = (minRectHeight(leftBound,rectWidth));

			//add the current rectangle to the sum of all previous rectangles
			cur = ((maxHeight+minHeight)*rectWidth/2);
			all+= cur;

			//next bounds
			leftBound +=rectWidth;
		}
		return all;
	}

}