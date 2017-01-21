package programming.set11.ebooks;

import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by BB20101997 on 20. Jan. 2017.
 */
public class EBookReader extends GraphicsProgram implements KeyListener{

	private String font            = "Serif-12";
	private int    boarder         = 10;
	private int    lineWidth       = 300;
	private int    firstLineIndent = 20;
	private double lineSpacing     = 1.2;
	private String content         = "";

	@Override
	public void init() {
		super.init();
		//addKeyListeners();//you didn't implement acm right
	}

	public void run() {
		//setTextFont("Garamond-bold-18");
		//setBorder(10);
		//setLineWidth(350);
		//setFirstLineIndent(20);
		//setLineSpacingFactor(1.2);
		/*
		displayText("The alley was unusually dark and alley-like. A thick mist covered the ground, penetrating the air like a heavy mattress, but without the solid parts. He felt reminded of an ingeniously staged theatre play. But this time, he might not make it out in one piece, the Don would make sure of that if given half a chance.\n"
				            + "'You're late!', said a voice in the dark.\n" +
				            "It was then that he noticed that he might not have been on time.");
		*/
		displayText("Alice waited till the eyes appeared, and then nodded. 'It's no use speaking to it,' she thought, 'till its ears have come, or at least one of them.' In another minute the whole head appeared, and then Alice put down her flamingo, and began an account of the game, feeling very glad she had someone to listen to her. The Cat seemed to think that there was enough of it now in sight, and no more of it appeared.");
	}

	/**
	 * This function takes a String and sets the font based on it
	 */
	public void setTextFont(String s) {
		font = s;
		updateDisplay();
	}

	/**
	 * This function takes an int and sets the boarde thicknes in pixels to that value
	 */
	public void setBorder(int n) {
		boarder = n;
		updateDisplay();
	}

	/**
	 * Sets the width of a Line
	 */
	public void setLineWidth(int n) {
		lineWidth = n;
		updateDisplay();
	}

	/**
	 * The offset at the beginning of a new Paragraph
	 */
	public void setFirstLineIndent(int n) {
		firstLineIndent = n;
		updateDisplay();
	}

	/**
	 * The space between two baselines as a multiple of the fonts height
	 */
	public void setLineSpacingFactor(double d) {
		lineSpacing = d;
		updateDisplay();
	}

	public void displayText(String s) {
		content = s;
		updateDisplay();
	}

	/**
	 * //updates the displayed text to match changed settings/text
	 */
	private void updateDisplay() {
		removeAll(); //first remove old Labels
		GLabel metricsLable = new GLabel("This is just there to get the metrics");
		metricsLable.setFont(font);//set the Font
		FontMetrics fontMetrics = metricsLable.getFontMetrics(); //get the Metrics
		String[]    strings     = content.split("\n"); //split into the paragraphs
		for(int i = 0, l = 0; i < strings.length; i++) { //generate and add the GLabels for each paragraph
			l = addParagraph(strings[i], l, fontMetrics);
		}
		setSize(getGCanvas().getSize());//set the window size to match the canvas size
	}

	/**
	 * @param string      the content of the Paragraph
	 * @param l           the next empty line
	 * @param fontMetrics the fontMetrics to use
	 *
	 * @return the next empty line
	 */
	private int addParagraph(String string, int l, FontMetrics fontMetrics) {
		int                x   = boarder + firstLineIndent;
		double             y;
		LinkedList<String> lls = new LinkedList<>(Arrays.asList(string.split(" ")));
		String             cur;
		GLabel             gLabel;
		while(!lls.isEmpty()) {
			y = getLineY(l++, fontMetrics);
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
			add(gLabel, x, y);
			x = boarder;
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

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
			case 'b': {
				if(boarder > 0) { EBookReader.this.setBorder(boarder - 1); }
				break;
			}
			case 'B': {
				if(boarder < lineWidth / 2) { EBookReader.this.setBorder(boarder + 1); }
				break;
			}
			case 'w': {
				if(lineWidth > 0) { EBookReader.this.setLineWidth(lineWidth - 1); }
				break;
			}
			case 'W': {
				EBookReader.this.setLineWidth(lineWidth + 1);
				break;
			}
			case 'i': {
				if(firstLineIndent > 0) { EBookReader.this.setFirstLineIndent(firstLineIndent - 1); }
				break;
			}
			case 'I': {
				if(firstLineIndent<lineWidth)
				EBookReader.this.setFirstLineIndent(firstLineIndent + 1);
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
