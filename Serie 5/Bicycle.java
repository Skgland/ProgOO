/**
 * Created by Bennet on 28.11.2016.
 * This class handles all bike related matters
 */
public class Bicycle extends Vehicle {

	/**
	 * the type of this bike
	*/
	 public final BikeType TYPE;

	/**
	 * Create a bike of the given type
	 * @param type
	 * */
	public Bicycle(BikeType type){
		super("Bicycle");
		TYPE = type;
	}

	/**
	 * The maximum velocity of the bike in km/h
	 * */
	@Override
	double getMaxVelocity() {
		double firstPart = Math.cbrt(TYPE.A+Math.sqrt(Math.pow(TYPE.A,2)+Math.pow(TYPE.B,3)));
		double secondPart = Math.cbrt(TYPE.A-Math.sqrt(Math.pow(TYPE.A,2)+Math.pow(TYPE.B,3)));
		double thirdPart = 2.0*0.1/(getSurface()*getDensity()*3);
		return (firstPart+secondPart-thirdPart)*3.6;
	}

	/**
	 * The assumed Power in W
	 * */
	@Override
	double getPower() {
		return 160;
	}

	/**
	 * The approximated Area of the cyclist already including the drag
	 * */
	@Override
	double getSurface() {
		return TYPE.SURFACE;
	}

	/**
	 * already included in the Surface Area therefor 1 and never used
	 * */
	@Override
	double getDrag() {
		return 1;
	}

	/**
	 * the air density
	 * */
	@Override
	double getDensity() {
		return 1.2;
	}

	@Override
	public String toString() {
		return NAME+" ("+TYPE.DESCRIPTION +"): "+(int)getMaxVelocity()+"km/h";
	}
}
