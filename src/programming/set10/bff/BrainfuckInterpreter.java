package programming.set10.bff;

import java.util.Arrays;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class BrainfuckInterpreter {

	private char[] programm;
	private byte[] cell;

	private static final int CELL_AMMOUNT = 1024;

	public void setProgram(char[] prog) {
		int tally = 0;

		//make a copy of prog so it won't be altered while or after checking for correctness
		char[] tmp = Arrays.copyOf(prog, prog.length);

		for (char c : tmp) {
			if (c == '[') {
				tally++;
			} else if (c == ']') {
				tally--;
			}

			if (tally < 0) {
				throw new IllegalArgumentException("Closing a Loop before opening one!");
			}
		}

		if (tally != 0) {
			throw new IllegalArgumentException("Unbalanced Loop Brackets,not all Loops where closed!");
		}

		//the program is correct and will be set
		programm = tmp;
	}

	public String interpret() {

		if (programm == null) {
			throw new IllegalStateException("No Program set!");
		}

		//init at cell 0
		int currentCell = 0;
		//reset cells
		cell = new byte[CELL_AMMOUNT];

		//for the output
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < programm.length; i++) {
			switch (programm[i]) {
				case '<': {
					//one cell to the left
					currentCell--;
					continue;
				}
				case '>': {
					//one cell to the right
					currentCell++;
					continue;
				}
				case '+': {
					//increment cell value
					cell[currentCell]++;
					continue;
				}
				case '-': {
					//decrement cell value
					cell[currentCell]--;
					continue;
				}
				case '.': {
					//output cell value as char
					sb.append((char) cell[currentCell]);
					continue;
				}
				case '/': {
					//double cell value
					cell[currentCell] *= 2;
					continue;
				}
				case '[': {
					//loop start
					if (cell[currentCell] == 0) {
						int tally = 0;
						while (programm[i] != ']' || tally != 0) {
							if (programm[i] == '[') {
								tally++;
							}
							i++;
							if (programm[i] == ']') {
								tally--;
							}
						}
					}
					continue;
				}
				case ']': {
					//loop end
					if (cell[currentCell] != 0) {
						int tally = 0;
						while (programm[i] != '[' || tally != 0) {
							if (programm[i] == ']') {
								tally++;
							}
							i--;
							if (programm[i] == '[') {
								tally--;
							}
						}
					}
				}
			}
		}
		return sb.toString();
	}
}