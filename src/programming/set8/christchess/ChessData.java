package programming.set8.christchess;

import acm.graphics.GPoint;

/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
public class ChessData implements Cloneable {


	private ChessPiece[][] CHESS_PIECE_ARRAY = new ChessPiece[8][8];

	private int activePlayer =  References.PLAYER1;

	public void initNewGame() {
		for (int i = 0; i < 8; i++) {
			addNewPiece(References.PAWN,  References.PLAYER1, i, 1);
			addNewPiece(References.PAWN,  References.PLAYER2, i, 6);
		}

		addNewPiece(References.KING,  References.PLAYER1, 3, 0);
		addNewPiece(References.KING,  References.PLAYER2, 3, 7);

		addNewPiece(References.QUEEN,  References.PLAYER1, 4, 0);
		addNewPiece(References.QUEEN,  References.PLAYER2, 4, 7);

		addNewPiece(References.ROOK,  References.PLAYER1, 0, 0);
		addNewPiece(References.ROOK,  References.PLAYER1, 7, 0);
		addNewPiece(References.ROOK,  References.PLAYER2, 0, 7);
		addNewPiece(References.ROOK,  References.PLAYER2, 7, 7);

		addNewPiece(References.KNIGHT,  References.PLAYER1, 1, 0);
		addNewPiece(References.KNIGHT,  References.PLAYER1, 6, 0);
		addNewPiece(References.KNIGHT,  References.PLAYER2, 1, 7);
		addNewPiece(References.KNIGHT,  References.PLAYER2, 6, 7);

		addNewPiece(References.BISHOP,  References.PLAYER1, 2, 0);
		addNewPiece(References.BISHOP,  References.PLAYER1, 5, 0);
		addNewPiece(References.BISHOP,  References.PLAYER2, 2, 7);
		addNewPiece(References.BISHOP,  References.PLAYER2, 5, 7);
	}

	public void addNewPiece(int type, int player, int x, int y) {
		CHESS_PIECE_ARRAY[x][y] = new ChessPiece(type, player, x, y);
	}

	public void removeAllPieces() {
		CHESS_PIECE_ARRAY = new ChessPiece[8][8];
	}

	public ChessPiece getPieceAt(int x, int y) {
		if (x >= 0 && 7 >= x && y >= 0 && 7 >= y) {
			return CHESS_PIECE_ARRAY[x][y];
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
		if (CHESS_PIECE_ARRAY[cp.getX()][cp.getY()] == cp) {
			if (activePlayer == cp.getPlayer()) {
				if (cp.getValidTargetSquares(this).size() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isValidMove(ChessPiece piece, int x, int y) {
		return piece.getValidTargetSquares(this).contains(new GPoint(x, y));
	}

	public ChessPiece movePieceTo(ChessPiece piece, int x, int y) {
		ChessPiece captured = CHESS_PIECE_ARRAY[x][y];
		CHESS_PIECE_ARRAY[x][y] = new ChessPiece(piece.getType(), piece.getPlayer(), x, y);
		CHESS_PIECE_ARRAY[piece.getX()][piece.getY()] = null;
		return captured;
	}

	public void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}

	public void togglePlayer() {
		activePlayer = activePlayer ==  References.PLAYER1 ?  References.PLAYER2 :  References.PLAYER1;
	}

	public int isCheckmate() {
		ChessPiece cur;
		outer: for (int i = 1; i < 3; i++) {
			if (isInCheck(i)) {
				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						cur = getPieceAt(x, y);
						if (cur != null && (cur.getPlayer() == i) && !cur.getValidTargetSquares(this).isEmpty()) {
						 break outer;
						}
					}
				}
				return i;
			}
		}
		return References.NO_PLAYER;
	}

	public boolean isInCheck(int player) {
		ChessPiece king = null;
		ChessPiece current;
		//find the king
		outer:
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				current = CHESS_PIECE_ARRAY[i][ii];
				if (current != null) {
					if (current.getType() == References.KING) {
						if (current.getPlayer() == player) {
							king = current;
							break outer;
						}
					}
				}
			}
		}

		if (king == null) {
			return true;
		}

		//check if the king can be attacked
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				current = CHESS_PIECE_ARRAY[i][ii];
				if (current != null) {
					if (current.getPlayer() != player) {
						if (FigureLogic.values()[current.getType()].isValidMove(current, this, king.getX(), king.getY())) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public int getActivePlayer() {
		return activePlayer;
	}


	@Override
	public Object clone() throws CloneNotSupportedException {
		ChessData cd = (ChessData) super.clone();
		cd.CHESS_PIECE_ARRAY = new ChessPiece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				cd.CHESS_PIECE_ARRAY[i][ii] = CHESS_PIECE_ARRAY[i][ii];
			}
		}
		return cd;
	}
}
