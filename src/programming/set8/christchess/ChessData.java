package programming.set8.christchess;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ChessData implements Cloneable {

	private List<ChessPiece> pieces = new ArrayList<>();
	private int activePlayer = References.PLAYER1;
	private double turnCount = 1;

	public void initNewGame() {

		removeAllPieces();
		turnCount = 1;

		for (int i = 0; i < 8; i++) {
			addNewPiece(References.PAWN, References.PLAYER1, i, 6);
			addNewPiece(References.PAWN, References.PLAYER2, i, 1);
		}

		addNewPiece(References.KING, References.PLAYER1, 3, 7);
		addNewPiece(References.KING, References.PLAYER2, 3, 0);

		addNewPiece(References.QUEEN, References.PLAYER1, 4, 7);
		addNewPiece(References.QUEEN, References.PLAYER2, 4, 0);

		addNewPiece(References.ROOK, References.PLAYER1, 0, 7);
		addNewPiece(References.ROOK, References.PLAYER1, 7, 7);
		addNewPiece(References.ROOK, References.PLAYER2, 0, 0);
		addNewPiece(References.ROOK, References.PLAYER2, 7, 0);

		addNewPiece(References.KNIGHT, References.PLAYER1, 1, 7);
		addNewPiece(References.KNIGHT, References.PLAYER1, 6, 7);
		addNewPiece(References.KNIGHT, References.PLAYER2, 1, 0);
		addNewPiece(References.KNIGHT, References.PLAYER2, 6, 0);

		addNewPiece(References.BISHOP, References.PLAYER1, 2, 7);
		addNewPiece(References.BISHOP, References.PLAYER1, 5, 7);
		addNewPiece(References.BISHOP, References.PLAYER2, 2, 0);
		addNewPiece(References.BISHOP, References.PLAYER2, 5, 0);
	}

	public ChessPiece addNewPiece(int type, int player, int x, int y) {
		ChessPiece cp = new ChessPiece(type, player, x, y);
		pieces.add(cp);
		return cp;
	}

	public void removeAllPieces() {
		pieces.clear();
	}

	public ChessPiece getPieceAt(int x, int y) {
		if (x >= 0 && 7 >= x && y >= 0 && 7 >= y) {
			for (ChessPiece cp : pieces) {
				if ((cp.getX() == x) && (cp.getY() == y)) {
					return cp;
				}
			}
		}
		return null;
	}

	public ChessPiece getPieceAt(String s) {
		return getPieceAt(stringToX(s), stringToY(s));
	}

	public static int stringToX(String s) {
		if (s.length() < 2)
			return -1;
		return (s.charAt(0) - 'a');
	}


	public static int stringToY(String s) {
		if (s.length() < 2)
			return -1;
		return ('8' - s.charAt(1));
	}

	public boolean isValidSelection(ChessPiece cp) {
		if (cp == null) {
			return false;
		}

		if (pieces.contains(cp)) {
			if (activePlayer == cp.getPlayer()) {
				if (!cp.getValidTargetSquares(this).isEmpty()) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isValidMove(ChessPiece piece, int x, int y) {
		return piece.getValidTargetSquares(this).contains(new Point(x, y));
	}

	public ChessPiece movePieceTo(ChessPiece piece, int x, int y) {
		ChessPiece captured;
		captured = getPieceAt(x, y);
		pieces.remove(captured);
		piece.moveTo(x, y);
		return captured;
	}

	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void togglePlayer() {
		turnCount+=0.5;
		activePlayer = activePlayer == References.PLAYER1 ? References.PLAYER2 : References.PLAYER1;
	}

	public int isCheckmate() {
		outer:
		for (int i = 1; i < 3; i++) {
			if (isInCheck(i)) {
				for(ChessPiece cur:pieces){
					if (cur != null && (cur.getPlayer() == i) && !cur.getValidTargetSquares(this).isEmpty()) {
						continue outer;
					}
				}
				return i;
			}
		}
		return References.NO_PLAYER;
	}

	public boolean isInCheck(int player) {
		ChessPiece king = null;
		//find the king
		for (ChessPiece current : pieces) {
			if (current.getType() == References.KING) {
				if (current.getPlayer() == player) {
					king = current;
					break;
				}
			}
		}

		if (king == null) {
			return false;
		}

		//check if the king can be attacked
		for (ChessPiece current : pieces) {
			if (current.getPlayer() != player) {
				if (FigureLogic.values()[current.getType()].isValidMove(current, this, king.getX(), king.getY())) {
					return true;
				}
			}
		}

		return false;
	}

	public int getActivePlayer() {
		return activePlayer;
	}

	public List<ChessPiece> getPieces(){
		List<ChessPiece> cpl = new ArrayList<>();
		for(ChessPiece cp:pieces){
			cpl.add(new ChessPiece(cp.getType(),cp.getPlayer(),cp.getX(),cp.getY()));
		}
		return cpl;
	}

	public int getTurn() {
		return (int)turnCount;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ChessData cd = (ChessData) super.clone();
		cd.pieces = new ArrayList<>();

		for (ChessPiece cp : pieces) {
			cd.pieces.add(new ChessPiece(cp.getType(), cp.getPlayer(), cp.getX(), cp.getY()));
		}
		return cd;
	}
}
