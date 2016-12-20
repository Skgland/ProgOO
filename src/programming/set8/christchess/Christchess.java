package programming.set8.christchess;

import acm.program.GraphicsProgram;

/**
 * Created by stu201758 on 11/19/16.
 */
public class Christchess extends GraphicsProgram {

	public void run() {
		ChessView cv = new ChessView(getGCanvas());

		setSize(getGCanvas().getSize());

		ChessData cd = new ChessData();

		cd.initNewGame();
		cv.init(cd);

		ChessPiece cp;
		ChessPiece captured;
		String move;
		int x;
		int y;

		do {
			x = -1;
			y = -1;
			do {
				do {
					move = readLine((cd.getActivePlayer() ==  References.PLAYER1 ? "White" : "Black") + ", select a piece: ");
					cp = cd.getPieceAt(move);
				} while ((cp == null) || !cd.isValidSelection(cp));

				cv.updateValidMoves(cd,cp);

				do {
					move = readLine("Move " + cp + " to: ");
					if (move.equals("c")) {
						break;
					}
					x = ChessData.stringToX(move);
					y = ChessData.stringToY(move);
				} while (!cd.isValidMove(cp, x, y));

				cv.updateValidMoves(cd, null);

			}while(move.equals("c"));
			captured = cd.movePieceTo(cp,x,y);
			if(captured != null){
				println("Capture: "+cp+" captures "+captured);
			}

			cd.togglePlayer();
			cv.update(cd);

		} while (cd.isCheckmate() == References.NO_PLAYER);

		println("Player " + (cd.isCheckmate() ==  References.PLAYER1 ? "White" : "Black") + " is in checkmate.");

	}
}