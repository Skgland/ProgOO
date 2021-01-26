/**
 * @author Bennet Blessmann
 * Created on 28.11.2016.
 */
public abstract class Vehicle {

	public final String NAME;

	public Vehicle(String name){
		NAME = name;
	}

	/**
	 * @return the Power in W
	 *
	*/
	abstract double getPower();
	/**
	 * @return the surface area used in the getMaxVelocity calculation
	 * */
	abstract double getSurface();
	/**
	 * @return the drag on the given vehicle
	 * */
	abstract double getDrag();

	/**
	 * @return the density of the fluid the vehicle moves through
	 * */
	abstract double getDensity();

	/**
	 * @return the max velocity a vehicle can have based on the given vehicles specs in km/h
	 * */
	double getMaxVelocity(){
		return Math.cbrt(2*getPower()/(getDrag()*getSurface()*getDensity()))*3.6;
	}

	/**
	 * @param ps the amount od PS that will be converted
	 *@return the amount of W corresponding to ps
	 * */
	public static double PSToW(double ps){
		return ps*735.49875;
	}

}
