package programming.set8.christchess;

import acm.graphics.GPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
public class ChessPiece {

	private int iType;
	private int iPlayer;
	private int iX, iY;
	private int special;
	private boolean notify;

	public ChessPiece(int type, int player, int x, int y) {
		iType = type;
		iPlayer = player;
		iX = x;
		iY = y;
	}

	public List<GPoint> getValidTargetSquares(ChessData cd) {
		List<GPoint> gpl = new ArrayList<>();
		ChessPiece cp;
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				cp = cd.getPieceAt(i, ii);
				if(i==getX()&&ii==getY())
					continue;
				if (cp == null || cp.iPlayer != iPlayer) {
					if(FigureLogic.values()[iType].isValidMove(this,cd,i,ii)){
						try {
							ChessData testData = (ChessData) cd.clone();
							testData.movePieceTo(this,i,ii);
							if(!testData.isInCheck(iPlayer)){
								gpl.add(new GPoint(i,ii));
							}
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return gpl;
	}

	public int getX() {
		return iX;
	}


	public int getY() {
		return iY;
	}

	public int getPlayer() {
		return iPlayer;
	}

	public int getType() {
		return iType;
	}

	@Override
	public String toString() {
		if (iType >= 0 && iType <= 5) {
			return FigureLogic.values()[iType].toString();
		}
		return "ERROR";
	}
}
