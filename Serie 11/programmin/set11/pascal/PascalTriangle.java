package programming.set11.pascal;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */
public class PascalTriangle extends GraphicsProgram implements KeyListener {

	//The title displayed above the
	private String title      = "Pascal!";
	//The font used for the title
	private String titleFont  = "SansSerif-bold-24";
	//The font used for the numbers
	private String numberFont = "SansSerif-bold-14";
	//how many rows of Pascals Triangle shall be generated
	private int    rows       = 8;
	//the diameter of the circles
	private int    circleSize = 40;

	public void init() {
		//add keylistener (not necessary for the exercise but useful for testing)
		getGCanvas().addKeyListener(this);
		//draw the triangle
		drawPascalsTriangle();
	}

	public void configure(String file) {
		try {
			//get the file
			File confFile = new File(file);
			//use it to get a filereader
			FileReader fileReader = new FileReader(confFile);
			char[]     config;
			//load the files content into a char array
			fileReader.read(config = new char[(int) confFile.length()]); //assuming files won't be too long
			//tokenize the content of the config file
			StringTokenizer stringTokenizer = new StringTokenizer(new String(config), "=;", false);
			String          key, temp, value;
			//while we are not finished parsing the config file
			while (stringTokenizer.hasMoreElements()) {
				//get the next key
				key = stringTokenizer.nextToken().trim();
				if (!stringTokenizer.hasMoreElements()) {
					break;
				}
				//get the corresponding value
				value = stringTokenizer.nextToken().trim();
				//save the value into the corresponding variable
				switch (key) {
					case "title": {
						title = value;
						break;
					}
					case "titleFont": {
						titleFont = value;
						break;
					}
					case "numberFont": {
						numberFont = value;
						break;
					}
					case "rows": {
						try {
							// Add code here to apply the number to the conigured parameter
							rows = Integer.parseInt(value);
						} catch (NumberFormatException e) {
							// If parseInt cannot parse the String as a number, execution will jump
							// here. In that case, ignore the parameter. Execution will then continue
							// with whatever comes below this block.
						}
						break;
					}
					case "circleSize": {
						try {
							// Add code here to apply the number to the conigured parameter
							circleSize = Integer.parseInt(value);
						} catch (NumberFormatException e) {
							// If parseInt cannot parse the String as a number, execution will jump
							// here. In that case, ignore the parameter. Execution will then continue
							// with whatever comes below this block.
						}
						break;
					}
				}

			}

		} catch (IOException e) {
			//use default config
			System.err.println("Failed to load file!");
		}

	}

	public void drawPascalsTriangle() {
		//get a lable for the title
		GLabel gTitle = new GLabel(title);
		//set the font
		gTitle.setFont(titleFont);
		//calculate how much space the circles need
		int circleSpace = (int) ((1.5 * rows - 0.5) * circleSize + 20);
		//set the width to whatever needs more space
		int width = circleSpace > gTitle.getWidth() ? circleSpace : (int) gTitle.getWidth();
		setSize(width, (int) (circleSpace + 10 + gTitle.getAscent() + gTitle.getDescent()));

		//add the title at the right position
		add(gTitle, (width - gTitle.getWidth()) / 2, gTitle.getAscent() + 10);

		//set starting y for circles
		double x, y = 20 + gTitle.getAscent() + gTitle.getDescent();

		//init with first line of pascals triangle
		int[]  line = new int[]{1};
		int[]  nextLine;
		GLabel number;
		GOval  circle;
		//generate all rows
		for (int r = 0; r < rows; r++) {
			//generate the circles in the line
			for (int n = 0; n < line.length; n++) {
				//get the lable for the current circle
				number = new GLabel("" + line[n]);
				//set the font
				number.setFont(numberFont);
				//get the x coordinates of the circle
				x = getXForCircle(width, r, n);
				//add the lable to the center of the circle
				add(number, x + (circleSize - number.getWidth()) / 2, y + number.getAscent() + (circleSize - (number.getAscent() + number.getDescent())) / 2);
				//create the circle
				circle = new GOval(circleSize, circleSize);
				//add the circle
				add(circle, x, y);
			}

			//create a new array for the next line of numbers
			nextLine = new int[line.length + 1];

			//generate the numbers for the next iteration
			for (int i = 0; i < nextLine.length; i++) {
				nextLine[i] = 0;
				if (i != 0) {
					nextLine[i] += line[i - 1];
				}
				if (i != nextLine.length - 1) {
					nextLine[i] += line[i];
				}
			}
			//replace line with the new line
			line = nextLine;
			//add the offset for the new line of circles to the y coordinate
			y += 1.5 * circleSize;
		}
	}

	private double getXForCircle(int width, int r, int n) {
		double ret = (width - circleSize) / 2;    //start in first row
		ret += (n * 1.5 - r * 0.75) * circleSize;     //subtract start offset based on current row r
		//add offset based on current circle n
		return ret;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//redraw
		if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			removeAll();
			drawPascalsTriangle();
		}
		//load new config
		if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
			removeAll();
			configure(readLine("Please enter the Path to the config:"));
			drawPascalsTriangle();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
