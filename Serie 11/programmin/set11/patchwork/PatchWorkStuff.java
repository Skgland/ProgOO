package programming.set11.patchwork;

import acm.program.GraphicsProgram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by BB20101997 on 20. Jan. 2017.
 */
public class PatchWorkStuff extends GraphicsProgram implements KeyListener{

	private int PATCH_SIDE_LENGTH = 250;
	private int rows = 4,columns = 7;

	public void run() {
		askDimensions();
		generatePachtWork();
		getGCanvas().addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()=='r'|| e.getKeyChar() == 'R'){
			//generate a new set of patches with the same row and column count
			generatePachtWork();
		}
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
			//ask the user for a new row and column count and generate that
			askDimensions();
			generatePachtWork();
		}
	}

	@Override
	public void keyPressed(KeyEvent e){
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	/**
	 * ask the user in the console for a row and column count
	 * and saves the answer to rows and columns
	 * */
	private void askDimensions(){
		rows = readInt("Please provide the amount of rows: ");
		columns = readInt("Please provide the amount of columns: ");
	}

	/**
	 *  generates rows * columns Patches and adds them to the Canvas
	 * */
	private void generatePachtWork() {
		//clear canvas first
		removeAll();
		for(int x = 0; x < columns; x++) {
			for(int y = 0; y < rows; y++) {
				add(new Patch(PATCH_SIDE_LENGTH), x * PATCH_SIDE_LENGTH, y * PATCH_SIDE_LENGTH);
			}
		}
		//set size to match the space the patches need
		setSize(PATCH_SIDE_LENGTH * columns, PATCH_SIDE_LENGTH * rows);
	}
}
