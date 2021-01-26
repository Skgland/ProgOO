/**
 * Created by Bennet on 28.11.2016.
 * The class for all cars
 */
public class Car extends Vehicle {

	/**
	 * Power in PS
	 */
	public final double POWER;

	/**
	 * create a car with
	 *
	 * @param name  as the name and
	 * @param power as the power in PS
	 */
	public Car(String name, double power) {
		super(name);
		POWER = power;
	}

	/**
	 * the power in W
	 * */
	@Override
	double getPower() {
		return PSToW(POWER);
	}

	/**
	 * All iLearn Cars have the same surface area
	 * */
	@Override
	double getSurface() {
		return 2.5;
	}

	/**
	 * the drag of cars
	 * */
	@Override
	double getDrag() {
		return 0.35;
	}


	/**
	 * the default density for cars
	 * */
	@Override
	double getDensity() {
		return 1.3;
	}

	@Override
	public String toString() {
		return NAME + " (" + (int) POWER + " PS): " + (int) getMaxVelocity() + "km/h";
	}
}
