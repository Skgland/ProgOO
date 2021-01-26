/**
 * Created by Bennet on 28.11.2016.
 * The class for all man powered boats
 */
public class Boat extends WaterVehicle {

	/**
	 * how many are rowing
	 */
	public final int ROWER;
	/**
	 * how deep is the draught and how wide is the boat
	 */
	public final double DRAUGHT, WIDTH;

	/**
	 * Create a boat withe the name
	 *
	 * @param name    and the rower amount of
	 * @param rowers  and the draught
	 * @param draught and the width
	 * @param width
	 */
	public Boat(String name, int rowers, double draught, double width) {
		super(name);
		ROWER = rowers;
		DRAUGHT = draught;
		WIDTH = width;
	}


	/**
	 * The power of all rowers in W
	 * */
	@Override
	double getPower() {
		return ROWER * 100;
	}

	/**
	 * the Surface area used to calculate the max velocity
	 * */
	@Override
	double getSurface() {
		return DRAUGHT * WIDTH / 2;
	}

	@Override
	public String toString() {
		return NAME + " (" + ROWER + " rowers, b = " + DRAUGHT + "m, h = " + WIDTH + "m ): " + (int) getMaxVelocity() + "km/h (" + (int) KMHToKnots(getMaxVelocity()) + "kts)";
	}
}
