import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

/**
 * Created by Bennet on 25.11.2016.
 */
public class PiApproximator extends GraphicsProgram {

	public static final int SIDE_LENGTH = 800;
	public static final int POINTS = 10_000;
	RandomGenerator rg = new RandomGenerator();

	/**
	 * Randomly generates a new point whose x and y coordinates lie between -1
	 * and 1.
	 *
	 * @return random point.
	 */
	public GPoint randomPoint(){
		return new GPoint(rg.nextDouble(-1,1),rg.nextDouble(-1,1));
	}

	/**
	 * Checks if the point with the given coordinates is inside the circle with
	 * radius 1 centered at the coordinate system's origin.
	 *
	 * @param unitPoint
	 *            the point to check.
	 * @return {@code true} if the point is inside the circle, {@code false} if
	 *         it's not.
	 */
	public boolean isInCircle(GPoint unitPoint) {
		return 1>Math.sqrt(Math.pow(unitPoint.getX(),2)+Math.pow(unitPoint.getY(),2));
	}

	public void run(){
		//boarder Rectangle
		GRect bound = new GRect(5,5,SIDE_LENGTH,SIDE_LENGTH);
		add(bound);

		GOval go;
		GPoint cur;
		boolean inside;
		int insidePoints = 0;
		//generate the required amount of Points and count the points in the circle
		for(int i = 0;i<POINTS;i++){
			cur = randomPoint();
			inside = isInCircle(cur);
			go = new GOval(1,1);
			go.setLocation((cur.getX()+1)*SIDE_LENGTH/2+5,(cur.getY()+1)*SIDE_LENGTH/2+5);
			go.setColor(inside? Color.CYAN:Color.RED);
			add(go);
			if(inside)
				insidePoints++;
		}
		//output PI approximation
		println(insidePoints*4.0/POINTS);
	}
}