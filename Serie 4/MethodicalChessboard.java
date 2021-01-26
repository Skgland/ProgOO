import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by stu201758 on 11/19/16.
 */
public class MethodicalChessboard extends GraphicsProgram {

	//this is a variable of type int
	public static final int SCALE = 800;
	//Square width
	public static final int SQW   = SCALE / 10;

	//these are two variables the first one of type GRect[][]
	// and second one of type GLabel[][]
	public final GRect[][]  board   = new GRect[8][8];
	public final GLabel[][] figures = new GLabel[8][8];

	// a scope is the part of the program in which a specific variable is defined/valid
	@Override
	public void run() {//<- this bracket is the beginning of this methods scope
		drawChessboard();
		setSize(SCALE, (int) (SCALE * 1.05));
	}//<- this bracket is the end of this methods scope

	/**
	 * initialize figures on starting position
	 */
	private void initPieces() {
		for(int player = 0; player < 2; player++) {
			int y = player == 0 ? 7 : 0;
			drawPiece(0, y, 3, player);//rock left
			drawPiece(7, y, 3, player);//rock right
			drawPiece(1, y, 1, player);//knight left
			drawPiece(6, y, 1, player);//knight right
			drawPiece(2, y, 2, player);//bishop left
			drawPiece(5, y, 2, player);//bishop right
			drawPiece(3, y, 4, player);//queen
			drawPiece(4, y, 5, player);//king

			//this for statement is a control statement
			// more precisely it is a loop statement
			for(int x = 0; x < 8; x++) {
				/*
				 * here we pass the function drawPiece three arguments
				 * all are of type int they will be evaluated before they
				 * are passed to the new stack frame
				 */
				drawPiece(x, player == 0 ? 6 : 1, 0, player);//pawn
			}
		}
	}

	/**
	 * Draws a specific chess {@code piece} of a {@code player} at the position of a specific square
	 * identified by {@code x} and {@code y}.
	 * Pieces are: 0 pawn, 1 Knight, 2 Bishop, 3 Rook, 4 Queen, 5 King
	 * The player playing white is identified by 0.
	 * The player playing with the black pieces is identified by 1.
	 *
	 * @param x      file index
	 * @param y      rank index
	 * @param piece  the piece
	 * @param player player 1 or player 2
	 *               <p></p>
	 *               this is a method with parameters to be specific it has three parameters all of type int
	 */
	public void drawPiece(int x, int y, int piece, int player) {
		if(player > 1 || player < 0 || piece < 0 || piece > 5 || x < 0 || x > 7 || y < 0 || y > 7) { return; }
		if(figures[x][y] != null) {
			remove(figures[x][y]);
		}

		char c = (char) ('\u2659' - piece + player * 6);
		figures[x][y] = new GLabel(c + "");
		figures[x][y].setFont(Font.SANS_SERIF + "-" + (int) (SCALE * 0.11));
		figures[x][y].setLocation((x + 1) * SQW + (SQW - figures[x][y].getWidth()) / 2, (y + 2) * SQW - (SQW + figures[x][y].getDescent() - figures[x][y].getAscent()) / 2);
		add(figures[x][y]);
		//System.out.println(c);
	}

	/**
	 * Draws the square identified by {@code x} and {@code y}
	 * in the color {@code color}.
	 *
	 * @param x     file index
	 * @param y     rank index
	 * @param color chosen color
	 */
	public void drawSquare(int x, int y, Color color) {
		if(x < 0 || x > 7 || y < 0 || y > 7) { return; }
		board[x][y] = new GRect((x + 1) * SQW, (y + 1) * SQW, SQW, SQW);
		board[x][y].setFilled(true);
		board[x][y].setFillColor(color);
		//it looks better without inner borders
		board[x][y].setColor(color);
		add(board[x][y]);
	}

	/**
	 * this draws the board including the labels and
	 * initializes the figures in the starting position
	 * */
	public void drawChessboard() {
		printBoard(); //draw the board tiles
		printLabels(); //draw the column and row designators
		initPieces();//initialize the board with the default piece layout
	}

	/**
	 * this function draws the tiles
	 * */
	public void printBoard() {
		//it looks better with a boarder around the outside
		GRect outerBorder = new GRect(SQW - 1, SQW - 1, 8 * SQW + 2, 8 * SQW + 2);
		add(outerBorder);

		/*
		* loop through all rows and columns
		* make the odd tiles gray
		* */
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				//the if statement is a control statement as well
				//to be precise it is a conditional statement
				if((x + y) % 2 != 0) {
					drawSquare(x, y, Color.LIGHT_GRAY);
				} else {
					drawSquare(x, y, Color.WHITE);
				}
			}
		}
	}

	/**
	 * Adds the labels around the board
	 * */
	public void printLabels() {

		GLabel top, bottom, left, right;
		/*
		* add the row and column designators to the top, bottom, left and right
		* of the chessboard
		* */
		for(int i = 0; i < 8; i++) {
			top = new GLabel((char) ('A' + i) + "", SQW / 2 + (i + 1) * SQW, SQW / 2);
			bottom = new GLabel((char) ('A' + i) + "", SQW / 2 + (i + 1) * SQW, SQW / 2 + 9 * SQW);
			left = new GLabel((char) ('8' - i) + "", SQW / 2, SQW / 2 + (i + 1) * SQW);
			right = new GLabel((char) ('8' - i) + "", SQW / 2 + 9 * SQW, SQW / 2 + (i + 1) * SQW);
			add(top);
			add(bottom);
			add(left);
			add(right);
		}
	}
}