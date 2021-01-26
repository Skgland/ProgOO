import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by Bennet on 25.11.2016.
 */
public class Mandelbrot extends GraphicsProgram {

	public void run() {
		int sideLength = readInt("Enter side length: ", 1, Integer.MAX_VALUE);
		int width = readInt("Enter width: ", 1, Integer.MAX_VALUE);
		int height = readInt("Enter height: ", 1, Integer.MAX_VALUE);
		drawMandelbrot(sideLength, width, height);
	}

	/**
	 * draws the mandelbrot set made of squares with the side length
	 *
	 * @param sideLength there will be
	 * @param width      squares horizontal
	 *                   and
	 * @param height     squares vertical
	 */
	private void drawMandelbrot(int sideLength, int width, int height) {

		ComplexNumber cn;
		boolean infi;
		GRect cur;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cn = getComplexCoordinates(x, y, width, height);
				infi = approachesInfinite(cn);
				cur = new GRect(x * sideLength, y * sideLength, sideLength, sideLength);
				cur.setFilled(true);
				cur.setColor(infi ? new Color(255, 255, 255) : new Color(240, 240, 240));
				add(cur);
			}
		}

	}

	/**
	 * check whether the set with
	 *
	 * @param c will approach infinity(in the first 400 calculations)
	 * @return if it does approach infinity
	 */
	private boolean approachesInfinite(ComplexNumber c) {
		ComplexNumber z = new ComplexNumber();
		for (int i = 0; i < 400; i++) {
			z = nextRound(z, c);
			if ((Math.pow(z.getReal(), 2) + Math.pow(z.getImaginary(), 2)) > 4) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Based on the previouse number
	 *
	 * @param z and
	 * @param c
	 * @return the next number in the sequenz
	 */
	private ComplexNumber nextRound(ComplexNumber z, ComplexNumber c) {
		return z.multiply(z).add(c);
	}

	/**
	 * Given
	 *
	 * @param x      the current column
	 *               and
	 * @param y      the current row
	 *               and the total
	 * @param width  and
	 * @param height
	 * @return the corresponding ComplexNumber
	 */
	private ComplexNumber getComplexCoordinates(int x, int y, int width, int height) {
		return new ComplexNumber(3.0 * x / width - 2, 2.0 * y / height - 1);
	}

	//copy past of my complex number class
	/**
	 * Created by Bennet on 25.11.2016.
	 * From here on it will get Complex
	 * Be carefully this class may be loaded with puns
	 */
	public class ComplexNumber {

		/**
		 * re the real part
		 * im the imaginary part
		 * they shall not change after initialisation
		 * therefor they will be final
		 */
		private final double re, im;


		/**
		 * this returns the ComplexNumber of which
		 * the real and the imaginary part are both 0
		 * may you never divide by this
		 */
		public ComplexNumber() {
			this(0, 0);
		}

		/**
		 * This creates a copy of the parameter
		 *
		 * @param cn the ComplexNumber to copy
		 *           <p />
		 *           this constructor is stupid because
		 *           the values of this instances can't be
		 *           changed anyway therefor sharing one
		 *           instance would NEVER be a problem!
		 *           reality is rather complex
		 */
		public ComplexNumber(ComplexNumber cn) {
			this(cn.re, cn.im);
		}

		/**
		 * @param real the real part of the number instantiated
		 * @param imag the imaginary part of the number instantiated
		 *             <p />
		 *             reality may be real and your imagination is part of it
		 */
		public ComplexNumber(double real, double imag) {
			re = real;
			im = imag;
		}

		/**
		 * @return the real part of this instance
		 * pi told i to
		 */
		public double getReal() {
			return re;
		}

		/**
		 * @return the imaginary part of this number
		 * if the problem is tough you need to
		 */
		public double getImaginary() {
			return im;
		}

		/**
		 * @return an instance of the ComplexNumber that is the
		 * complex conjugated of this instance
		 */
		public ComplexNumber conjugate() {
			return new ComplexNumber(re, -im);
		}

		/**
		 * @param cn the ComplexNumber to add to this instance
		 * @return the sum of this instance and cn
		 */
		public ComplexNumber add(ComplexNumber cn) {
			return new ComplexNumber(re + cn.re, im + cn.im);
		}

		/**
		 * @param cn the ComplexNumber to add to this instance
		 * @return the result of subtracting cn from this instance
		 */
		public ComplexNumber subtract(ComplexNumber cn) {
			return new ComplexNumber(re - cn.re, im - cn.im);
		}

		/**
		 * @param cn the ComplexNumber to multiply by this instance
		 * @return a new ComplexNumber instance which value is the result of
		 * multiplying this instance with cn
		 */
		public ComplexNumber multiply(ComplexNumber cn) {
			return new ComplexNumber(re * cn.re - im * cn.im, re * cn.im + cn.re * im);
		}

		/**
		 * @param cn the ComplexNumber to divide this instance by
		 * @return a new ComplexNumber instance which value is the result of
		 * dividing this instance by cn
		 */
		public ComplexNumber divide(ComplexNumber cn) {
			return new ComplexNumber((re * cn.re + im * cn.im) / (cn.re * cn.re + cn.im * cn.im), (im * cn.re - re * cn.im) / (cn.re * cn.re + cn.im * cn.im));
		}

		/**
		 * @return the absolute value of this ComplexNumber
		 */
		public double abs() {
			return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
		}

		/**
		 * @return a String representation of this ComplexNumber
		 */
		@Override
		public String toString() {
			return "" + re + ((im >= 0) ? '+' : '-') + im + "i";
		}
	}


}