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

	private String title      = "Pascal!";
	private String titleFont  = "SansSerif-bold-24";
	private String numberFont = "SansSerif-bold-14";
	private int    rows       = 8;
	private int    circleSize = 40;

	public void init() {
		getGCanvas().addKeyListener(this);
		drawPascalsTriangle();
	}

	public void configure(String file) {
		try {
			File confFile = new File(file);
			FileReader fileReader = new FileReader(confFile);
			char[]     config;
			fileReader.read(config = new char[(int) confFile.length()]); //assuming files won't be too long
			StringTokenizer stringTokenizer = new StringTokenizer(new String(config), "=;", false);
			String          key, temp, value;
			while(stringTokenizer.hasMoreElements()) {
				key = stringTokenizer.nextToken().trim();
				if(!stringTokenizer.hasMoreElements()) {
					break;
				}
				value = stringTokenizer.nextToken().trim();
				switch(key) {
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
						}
						catch(NumberFormatException e) {
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
						}
						catch(NumberFormatException e) {
							// If parseInt cannot parse the String as a number, execution will jump
							// here. In that case, ignore the parameter. Execution will then continue
							// with whatever comes below this block.
						}
						break;
					}
				}

			}

		}
		catch(IOException e) {
			//use default config
			System.err.println("Failed to load file!");
		}

	}

	public void drawPascalsTriangle() {
		GLabel gTitle = new GLabel(title);
		gTitle.setFont(titleFont);
		int circleSpace = (int) ((1.5 * rows - 0.5) * circleSize + 20);
		int width = circleSpace>gTitle.getWidth() ? circleSpace : (int) gTitle.getWidth();
		setSize(width ,(int)(circleSpace + 10 + gTitle.getAscent() + gTitle.getDescent()));

		add(gTitle, (width - gTitle.getWidth()) / 2, gTitle.getAscent() + 10);

		double x, y = 20 + gTitle.getAscent() + gTitle.getDescent();

		int[]  line = new int[]{1};
		int[]  nextLine;
		GLabel number;
		GOval  circle;
		for(int r = 0; r < rows; r++) {
			for(int n = 0; n < line.length; n++) {
				number = new GLabel("" + line[n]);
				number.setFont(numberFont);
				x = getXForCircle(width,r, n);
				add(number , x + (circleSize - number.getWidth()) / 2 , y +  number.getAscent() + (circleSize - (number.getAscent() + number.getDescent())) / 2);
				circle = new GOval(circleSize,circleSize);
				add(circle,x,y);
			}

			nextLine = new int[line.length+1];

			for(int i = 0;i<nextLine.length;i++){
				nextLine[i] = 0;
				if(i!=0){
					nextLine[i]+=line[i-1];
				}
				if(i!=nextLine.length-1){
					nextLine[i] += line[i];
				}
			}

			line = nextLine;
			y += 1.5 * circleSize;
		}

	}

	private double getXForCircle(int width,int r, int n) {
		double ret = (width - circleSize) / 2;    //start in first row
		ret += (n * 1.5 - r * 0.75) * circleSize;     //subtract start offset based on current row r
		                                              //add offset based on current circle n
		return ret;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
			removeAll();
			drawPascalsTriangle();
		}
		if(e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
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
