/**
 * Created by Bennet on 28.11.2016.
 * All water vehicle should extend this, this should not be instantiated directly
 */
public abstract class WaterVehicle extends Vehicle {



	public WaterVehicle(String name){
		super(name);
	}

	/**
	 * The default drag of WaterVehicles
	 * */
	@Override
	double getDrag() {
		return 0.3;
	}

	/**
	 * The default density of the fluid WaterVehicles swim in(i guess water)
	 * */
	@Override
	double getDensity() {
		return 1028;
	}

	/**
	 * convert knots to km/h
	 * */
	public static double KMHToKnots(double kmh){
		return kmh/1.85;
	}
}
