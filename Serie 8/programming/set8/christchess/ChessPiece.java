package programming.set8.christchess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ChessPiece {

	/**
	 * fuck you test
	 * */
	public static final int PAWN = References.PAWN;
	public static final int KNIGHT = References.KNIGHT;
	public static final int BISHOP = References.BISHOP;
	public static final int ROOK = References.ROOK;
	public static final int QUEEN = References.QUEEN;
	public static final int KING = References.KING;

	private int iType;
	private int iPlayer;
	private int iX, iY;

	/**
	 * used if I would implement Rochade or En Passant
	 * */
	@SuppressWarnings("SpellCheckingInspection")
	private int special;
	private boolean notify;

	public ChessPiece(int type, int player, int x, int y) {
		iType = type;
		iPlayer = player;
		iX = x;
		iY = y;
	}

	public List<Point> getValidTargetSquares(ChessData cd) {
		List<Point> gpl = new ArrayList<>();
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
							testData.movePieceTo(testData.getPieceAt(getX(),getY()),i,ii);
							if(!testData.isInCheck(iPlayer)){
								gpl.add(new Point(i,ii));
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

	public void moveTo(int x,int y){
		iX = x;
		iY = y;
	}

	@Override
	public String toString() {
		if (iType >= 0 && iType <= 5) {
			return FigureLogic.values()[iType].toString();
		}
		return "ERROR";
	}
}
