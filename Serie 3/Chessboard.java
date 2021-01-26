import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by stu201758 on 11/13/16.
 */
public class Chessboard extends GraphicsProgram {

	public static final int SCALE = 800;

	public void run(){
		printBoard(); //draw the board tiles
		printLabels(); //draw the column and row designators
	}

	public void printBoard(){

		//it looks better with a boarder around the outside
		GRect outerBorder = new GRect(SCALE/10-1,SCALE/10-1,8*SCALE/10+2,8*SCALE/10+2);
		add(outerBorder);

		GRect grey;
		/*
		* loop through all rows and columns
		* make the odd tiles gray
		* */
		for(int x = 0;x<8;x++){
			for(int y = 0;y<8;y++){
				if((x+y)%2!=0){//
					grey = new GRect((x+1)*SCALE/10,(y+1)*SCALE/10,SCALE/10,SCALE/10);
					grey.setFilled(true);
					grey.setFillColor(Color.LIGHT_GRAY);
					//it looks better without inner borders
					grey.setColor(Color.LIGHT_GRAY);
					add(grey);
				}
			}
		}
	}

	public void printLabels(){
		GLabel top,bottom,left,right;
		/*
		* add the row and column designators to the top, bottom, left and right
		* of the chessboard
		* */
		for(int i = 0;i<8;i++){
			top = new GLabel((char)('A'+i)+"",SCALE/20+(i+1)*SCALE/10,SCALE/20);
			bottom = new GLabel((char)('A'+i)+"",SCALE/20+(i+1)*SCALE/10,SCALE/20+SCALE*9/10);
			left = new GLabel((char)('8'-i)+"",SCALE/20,SCALE/20+(i+1)*SCALE/10);
			right = new GLabel((char)('8'-i)+"",SCALE/20+SCALE*9/10,SCALE/20+(i+1)*SCALE/10);
			add(top);
			add(bottom);
			add(left);
			add(right);
		}
	}

}