package programming.set11.ebooks;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by BB20101997 on 20. Jan. 2017.
 */
public class WrapFigure{

	String font            = "Serif-12";
	int    boarder         = 10;
	int    lineWidth       = 300;
	int    firstLineIndent = 20;
	int    spacing         = 10;
	double lineSpacing     = 1.2;
	GImage img;
	String fileName;
	double scale;
	String content = "";

	WrapFigure(int width, String wrapFigureFilename, double scale, String text){
		lineWidth = width;
		fileName = wrapFigureFilename;
		this.scale = scale;
		img = new GImage(wrapFigureFilename);
		img.scale(scale);
		content = text;
	}

	/**
	 * This function takes a String and sets the font based on it
	 */
	public void setTextFont(String s) {
		font = s;
	}

	/**
	 * This function takes an int and sets the spacing between image and text
	 */
	public void setSpacing(int i) {
		spacing = i;
	}

	/**
	 * This function takes an int and sets the boarde thicknes in pixels to that value
	 */
	public void setBorder(int n) {
		boarder = n;
	}

	/**
	 * Sets the width of a Line
	 */
	public void setLineWidth(int n) {
		lineWidth = n;
	}

	/**
	 * The offset at the beginning of a new Paragraph
	 */
	public void setFirstLineIndent(int n) {
		firstLineIndent = n;
	}

	/**
	 * The space between two baselines as a multiple of the fonts height
	 */
	public void setLineSpacingFactor(double d) {
		lineSpacing = d;
	}

	public void displayText(String s) {
		content = s;
	}

	public GCompound getCompound(){
		GCompound ret = new GCompound();
		updateDisplay(ret);
		return ret;
	}

	/**
	 * //updates the displayed text to match changed settings/text
	 */
	private void updateDisplay(GCompound gc) {
		gc.add(img);
		GLabel metricsLable = new GLabel("This is just there to get the metrics");
		metricsLable.setFont(font);//set the Font
		FontMetrics fontMetrics = metricsLable.getFontMetrics(); //get the Metrics
		String[]    strings     = content.split("\n"); //split into the paragraphs
		for(int i = 0, l = 0; i < strings.length; i++) { //generate and add the GLabels for each paragraph
			l = addParagraph(strings[i], l, fontMetrics,gc);
		}
	}

	/**
	 * @param string      the content of the Paragraph
	 * @param l           the next empty line
	 * @param fontMetrics the fontMetrics to use
	 *
	 * @return the next empty line
	 */
	private int addParagraph(String string, int l, FontMetrics fontMetrics,GCompound gc) {
		double             x;
		double             y;
		LinkedList<String> lls = new LinkedList<>(Arrays.asList(string.split(" ")));
		String             cur;
		GLabel             gLabel;
		while(!lls.isEmpty()) {
			y = getLineY(l++, fontMetrics);
			x = boarder+(y>=fontMetrics.getAscent()+img.getHeight()+boarder+spacing? 0: img.getWidth() + spacing);
			cur = lls.pop();
			while(!lls.isEmpty()) {
				if(fontMetrics.stringWidth(cur + " " + lls.getFirst()) <= lineWidth - x) {
					cur += " " + lls.pop();
				} else {
					break;
				}
			}
			gLabel = new GLabel(cur);
			gLabel.setFont(fontMetrics.getFont());
			gc.add(gLabel, x, y);
		}
		return l;
	}
	/**
	 * returns the Baseline Y-Coordinat of the current line line index starting at 0
	 */
	private double getLineY(int l, FontMetrics fontMetrics) {
		double lineDiff = (fontMetrics.getAscent() + fontMetrics.getDescent()) * lineSpacing; //the spacing between the baselines
		return boarder + fontMetrics.getAscent() + l * lineDiff;
	}
}
