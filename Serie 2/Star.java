import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * Created by Bennet on 05.11.2016.
 */
public class Star extends GraphicsProgram {

	public void run() {
		int points = readInt("Please enter the amounts of Points the Start shall have:\n", 0, Integer.MAX_VALUE / 2);
		int outRad = readInt("Please provide the radius of the Points:\n", 0, Integer.MAX_VALUE);
		int inRad = readInt("Please provide the inner radius:\n", 0, outRad);
		GLine gl;
		double degre = Math.PI / points; //is fine radius is 2*Pi and the stare is 2 points per point
		for (int i = 0; i < points * 2; i++) {
			int start = i % 2 == 0 ? outRad : inRad; //alter between drawing from inside to from outside
			int stop = i % 2 != 0 ? outRad : inRad; //alter between drawing to outside to to inside
			//create next GLine
			gl = new GLine(Math.sin(i * degre) * start + outRad, Math.cos(i * degre) * start + outRad, Math.sin((i + 1) * degre) * stop + outRad, Math.cos((i + 1) * degre) * stop + outRad);
			add(gl);//add the GLine
		}
	}

}
