package programming.set11.patchwork;

import acm.program.GraphicsProgram;

/**
 * Created by BB20101997 on 20. Jan. 2017.
 */
public class PatchWorkStuff extends GraphicsProgram {

	private int PATCH_SIDE_LENGTH = 250;

	public void run() {
		int rows    = readInt("Please provide the amount of rows: ");
		int columns = readInt("Please provide the amount of columns: ");
		for(int x = 0; x < columns; x++) {
			for(int y = 0; y < rows; y++) {
				add(new Patch(PATCH_SIDE_LENGTH), x * PATCH_SIDE_LENGTH, y * PATCH_SIDE_LENGTH);
			}
		}
		setSize(PATCH_SIDE_LENGTH * columns, PATCH_SIDE_LENGTH * rows);
	}
}
