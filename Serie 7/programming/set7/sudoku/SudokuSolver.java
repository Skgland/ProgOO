package programming.set7.sudoku;

import acm.util.RandomGenerator;

/**
 * @author Bennet Blessmann
 *         Created on 10.12.2016.
 */
public class SudokuSolver {

	private Sudoku sudoku = new Sudoku();
	public boolean solved = false;
	public boolean solveable = true;

	//easy solveable sudoku
	private static int[][] test = new int[][]{
			{-1, 7, 9, -1, -1, 1, -1, -1, 3},
			{-1, -1, 8, -1, 7, -1, -1, -1, 1},
			{-1, -1, 2, -1, 3, 4, -1, -1, 5},
			{-1, -1, -1, -1, -1, 6, 9, 7, -1},
			{-1, 9, -1, -1, -1, 5, 3, -1, -1},
			{4, -1, 1, -1, -1, -1, -1, -1, -1},
			{-1, 2, -1, 1, -1, 7, -1, -1, -1},
			{-1, 1, -1, 6, 4, 8, 5, 3, -1},
			{8, 5, -1, 2, -1, -1, -1, 6, -1}
	};

	public SudokuSolver(Sudoku sk) {
		copySudoku(sk, sudoku);
		solveable = sk.isValid();
		if (solveable) {
			solved = checkSolved(sudoku);
		}
	}

	private static Sudoku copySudoku(Sudoku sk, Sudoku tempSK) {
		Sudoku temp = tempSK;
		for (int i = 0; i < 9; i++) {
			for (int ii = 0; ii < 9; ii++) {
				temp.setValueAt(i, ii, sk.getValueAt(i, ii));
			}
		}
		return temp;
	}

	private static boolean solveHard(Sudoku sk) {
		if (checkSolved(sk)) {
			return true;
		}
		//try easy solve first than do what ever it takes
		boolean easy;
		try {
			easy = solveEasy(sk);
		} catch (IllegalStateException ise) {
			//unsolvable
			return false;
		}

		if (!easy) {
			int temp;
			final Sudoku tempSK = new Sudoku();
			boolean[] pos;
			int x, y;
			RandomGenerator rg = new RandomGenerator();
			do {
				x = rg.nextInt(0, 8);
				y = rg.nextInt(0, 8);
			} while (sk.getValueAt(x, y) != -1);

			//find the first empty field
			pos = getPossibleNumbers(sk, x, y);
			for (int n = 0; n < 9; n++) {
				if (pos[n]) {
					copySudoku(sk, tempSK);
					tempSK.setValueAt(x, y, n + 1);
					solveHard(tempSK);
					if (checkSolved(tempSK)) {
						copySudoku(tempSK, sk);
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	private static boolean fillEmpty(Sudoku sk) {
		if (!isEmpty(sk)) {
			return false;
		}
		for (int i = 0; i < 9; i++) {
			for (int ii = 0; ii < 9; ii++) {
				sk.setValueAt(i, ii, test[i][ii]);
			}
		}
		solveEasy(sk);
		return true;
	}


	private static boolean isEmpty(Sudoku sk) {
		for (int i = 0; i < 9; i++) {
			for (int ii = 0; ii < 9; ii++) {
				if (sk.getValueAt(i, ii) != NumberBoard.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	//take it easy

	/**
	 * @return whether it could be solved by this method
	 * @throws IllegalStateException if Sudoku is not solvable
	 */
	private static boolean solveEasy(Sudoku sk) throws IllegalStateException {
		boolean[] pos;

		for (int i = 0; i < 9; i++) {
			inner:
			for (int ii = 0; ii < 9; ii++) {

				if (sk.getValueAt(i, ii) != NumberBoard.EMPTY) {
					//wenn das aktuelle feld schon belegt ist prüfe das nächste
					continue;
				}

				pos = getPossibleNumbers(sk, i, ii);
				int set = NumberBoard.EMPTY;
				//prüfe ob der einzutragende Wert eindeutig ist
				for (int d = 0; d < 9; d++) {
					if (pos[d]) {
						if (set == NumberBoard.EMPTY) {
							set = d + 1;
						} else {
							//wert ist nicht eindeutig versuche nächstes feld
							continue inner;
						}
					}
				}

				//No value fits it's not solveable
				if (set == NumberBoard.EMPTY) {
					throw new IllegalStateException("Sudoku not solveable!");
				}

				sk.setValueAt(i, ii, set);

				if (!checkSolved(sk)) {
					//startOver earlyer fields might be solveable now
					i = 0;
					ii = -1;
				} else {
					return true;
				}
			}
		}

		//return if easy could solve it
		return checkSolved(sk);
	}

	private static boolean[] getPossibleNumbers(Sudoku sk, int i, int ii) {
		boolean[] pos = new boolean[]{true, true, true, true, true, true, true, true, true};

		int temp1, temp2, temp3;
		//welche Werte stehen noch zur verfügung
		for (int d = 0; d < 9; d++) {
			temp1 = sk.getValueAt(i, d);
			temp2 = sk.getValueAt(d, ii);
			temp3 = sk.getValueAt((i / 3) * 3 + d % 3, (ii / 3) * 3 + d / 3);
			if (temp1 != -1) {
				pos[temp1 - 1] = false;
			}
			if (temp2 != -1) {
				pos[temp2 - 1] = false;
			}
			if (temp3 != -1) {
				pos[temp3 - 1] = false;
			}
		}

		return pos;
	}

	private static boolean checkSolved(Sudoku sk) {

		for (int i = 0; i < 9; i++) {
			for (int ii = 0; ii < 9; ii++) {
				if (sk.getValueAt(i, ii) == Sudoku.EMPTY) {
					return false;
				}
			}
		}

		return true;
	}

	public void solve() {
		if (solveable) {
			if (!fillEmpty(sudoku) && !fillOneInsert(sudoku)) {
				solved = solveHard(sudoku);
			} else {
				solved = true;
			}
		}
	}

	private static boolean fillOneInsert(Sudoku sudoku) {
		int c = -1;
		int x = 0, y = 0;
		int temp;

		for (int i = 0; i < 9; i++) {
			for (int ii = 0; ii < 9; ii++) {
				temp = sudoku.getValueAt(i, ii);
				if (temp != -1) {
					if (c == -1) {
						c = temp;
						x = i;
						y = ii;
					} else {
						return false;
					}
				}
			}
		}

		c = c - 1;


		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				sudoku.setValueAt(i, j, (i * 3 + i / 3 + j + c) % 9 + 1);

		return true;
	}

	public Sudoku getSolution() {
		return !solved ? null : sudoku;
	}

	public boolean isSolved() {
		return solved;
	}

}
