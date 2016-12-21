package programming.set8.christchess;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GPoint;
import acm.graphics.GRect;

import java.awt.*;
import java.util.List;

/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
@SuppressWarnings("WeakerAccess")
public class ChessView {

	GCanvas GC;
	GRect[][] fields = new GRect[8][8];
	GLabel[][] label = new GLabel[4][8];

	public ChessView(GCanvas gc) {
		GC = gc;
		GC.setSize(new Dimension(  References.FIELD_SIZE * 11 + References.TEXT_SPACE,   References.FIELD_SIZE * 11));
	}

	public void init(ChessData data) {
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				fields[i][ii] = new GRect((i + 1) *   References.FIELD_SIZE, (ii + 1) *   References.FIELD_SIZE,   References.FIELD_SIZE,   References.FIELD_SIZE);
				fields[i][ii].setFillColor((i + ii) % 2 == 0 ? Color.WHITE : Color.GRAY);
				fields[i][ii].setFilled(true);
			}
		}
		for (int i = 0; i < 8; i++) {
			//left
			label[0][i] = new GLabel("" + (char) ('8' - i),   References.FIELD_SIZE / 2, (i + 1) *   References.FIELD_SIZE +   References.FIELD_SIZE / 2);
			//rights
			label[1][i] = new GLabel("" + (char) ('8' - i),   References.FIELD_SIZE * 9 +   References.FIELD_SIZE / 2, (i + 1) *   References.FIELD_SIZE +   References.FIELD_SIZE / 2);
			//bottom
			label[2][i] = new GLabel("" + (char) ('A' + i), (i + 1) *   References.FIELD_SIZE +   References.FIELD_SIZE / 2,   References.FIELD_SIZE / 2);
			//top
			label[3][i] = new GLabel("" + (char) ('A' + i), (i + 1) *   References.FIELD_SIZE +   References.FIELD_SIZE / 2,   References.FIELD_SIZE / 2 +   References.FIELD_SIZE * 9);
		}

		update(data);
	}

	public void update(ChessData data) {
		GC.removeAll(); //clear
		for (GRect[] gra : fields) //add all Fields
			for (GRect gr : gra) {
				GC.add(gr);
			}
		for (GLabel[] gra : label) //add all Labels
			for (GLabel gr : gra) {
				GC.add(gr);
			}

		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				ChessPiece cp = data.getPieceAt(i, ii);
				if (cp != null) {
					FigureLogic fl = FigureLogic.values()[cp.getType()];
					GLabel gl = new GLabel("" + (cp.getPlayer() == References.PLAYER1 ? fl.unicode_w : fl.unicode_b));
					gl.setFont(Font.SANS_SERIF);
					gl.setFont("*-" +   References.FIELD_SIZE + "-*");

					GC.add(gl, (i + 1) *   References.FIELD_SIZE + (  References.FIELD_SIZE - gl.getWidth()) / 2, (ii + 2) *   References.FIELD_SIZE - (  References.FIELD_SIZE - (gl.getDescent() + gl.getAscent())) / 2 - gl.getDescent());
				}
			}
		}
		GC.repaint();
	}

	public void updateValidMoves(ChessData cd, ChessPiece cp) {
		if (cp != null) {
			//show selection in yellow
			//fields[cp.getX()][cp.getY()].setFillColor(Color.YELLOW);
			List<GPoint> vm = cp.getValidTargetSquares(cd);
			for (GPoint gp : vm) {
				int x = (int) gp.getX(),
						y = (int) gp.getY();
				fields[x][y].setFillColor(cd.getPieceAt(x, y) == null ? Color.GREEN : Color.RED);
			}
		}else{
			for(int i = 0;i<8;i++)
			for (int ii = 0; ii < 8; ii++) {
				fields[i][ii].setFillColor((i + ii) % 2 == 0 ? Color.WHITE : Color.GRAY);
			}
		}
	}

}
