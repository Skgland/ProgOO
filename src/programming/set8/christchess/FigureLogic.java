package programming.set8.christchess;

/**
 * @author Bennet Blessmann
 *         Created on 19.12.2016.
 */
public enum FigureLogic {

	PAWN('\u2659', '\u265F') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());
			if (dx != 0) {
				if (dx == 1 && dy == 1) {
					if (cd.getPieceAt(x, y) != null) {
						return true;
					}
				}
			} else {
				if (cd.getPieceAt(x, y) == null) {
					if (dy == 1) {
						if (y > cp.getY()) {
							if (cp.getPlayer() == References.PLAYER1) {
								return true;
							}
						} else {
							if (cp.getPlayer() ==  References.PLAYER2) {
								return true;
							}
						}
					} else if (dy == 2) {
						if (y > cp.getY()) {
							if (cp.getPlayer() == References.PLAYER1 && cp.getY() == 1) {
								return true;
							}
						} else {
							if (cp.getPlayer() ==  References.PLAYER2 && cp.getY() == 6) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}
	},
	KNIGHT('\u2658', '\u265E') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());

			return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
		}
	},
	BISHOP('\u2657', '\u265D') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());
			int xDir = (int) Math.signum(x - cp.getX());
			int yDir = (int) Math.signum(y - cp.getY());
			if (dx == dy) {
				for (int i = 1; i < dx; i++) {
					if (cd.getPieceAt(i * xDir + cp.getX(), i * yDir + cp.getY()) != null) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	},
	ROCK('\u2656', '\u265C') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());
			int xDir = (int) Math.signum(x - cp.getX());
			int yDir = (int) Math.signum(y - cp.getY());
			if ((dx == 0) ^ (dy == 0)) {
				for (int i = 1; (i < dx) || (i < dy); i++) {
					if (cd.getPieceAt(i * xDir + cp.getX(), i * yDir + cp.getY()) != null) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	},
	QUEEN('\u2655', '\u265B') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());
			int xDir = (int) Math.signum(x - cp.getX());
			int yDir = (int) Math.signum(y - cp.getY());

			if (dx == dy || dy == 0 || dx == 0) {
				for (int i = 1; i < dx || i < dy; i++) {
					if (cd.getPieceAt(i * xDir + cp.getX(), i * yDir + cp.getY()) != null) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
	},
	KING('\u2654', '\u265A') {
		@Override
		public boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y) {
			int dx = Math.abs(x - cp.getX());
			int dy = Math.abs(y - cp.getY());

			if (dx <= 1 && dy <= 1) {
				return true;
			}

			return false;
		}
	};

	public final char unicode_w;
	public final char unicode_b;


	FigureLogic(char cw, char cb) {
		unicode_w = cw;
		unicode_b = cb;
	}

	public abstract boolean isValidMove(ChessPiece cp, ChessData cd, int x, int y);

	@Override
	public String toString() {
		return name().charAt(0) + name().toLowerCase().substring(1);
	}
}
