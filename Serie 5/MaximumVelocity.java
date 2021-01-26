import acm.program.ConsoleProgram;

/**
 * Created by Bennet on 28.11.2016.
 */
public class MaximumVelocity extends ConsoleProgram {

	public void run() {
		/*
		 * crate an object of each type and
		 * each instance necessary and print it to the console
		*/
		println(new Car("VW Polo", 45));
		println(new Car("Porsche 911", 218));
		println(new Car("Lamborghini Countach", 454));
		println(new Ship("HMS Titanic", 51_000, 45_000, 269));
		println(new Ship("USS Nimitz", 280_000, 80_000, 332));
		println(new Boat("Greek Trireme", 170, 6.1, 0.9));
		println(new Bicycle(BikeType.TOPS));//kreutzotter kommt aufs selbe ergebnis
		println(new Bicycle(BikeType.DROPS));//kreutzotter kommt aufs selbe ergebnis
		println(new Bicycle(BikeType.ROADSTER));
	}

	/**
	 * Class Hierarchy Explanation
	 *
	 * MaximumVelocity:
	 *      The class just there to actually print the results
	 *
	 * Vehicle:
	 *      An abstract Superclass for all of my vehicles
	 *      defining the core functions and functionality
	 *
	 * WaterVehicle:
	 *      An abstract Subclass of Vehicle specialised for Swimming Vehicles
	 *      Adds a function for converting form KM/H to KNTS
	 *
	 * Boat:
	 *      A Subclass of WaterVehicle for Boat like Vehicles
	 *      that are operated by manpower
	 *
	 * Ship:
	 *      A Subclass of WaterVehicle that is powered by
	 *      engines.
	 *
	 * Car:
	 *      A Subclass of Vehicle that is powered by engines
	 *
	 * Bicycle:
	 *      A Subclass of Vehicle that is powered by manpower
	 *
	 * BikeType:
	 *      An enum class that has three Instances that correspond to
	 *      the three types of Bikes used in this exercise.
	 *
	 * This structure was chosen to minimize repetition.
	 * E.g.
	 *
	 * The maxVelocity function is only defined once and overridden once.
	 * It's defined in the Vehicle class because most vehicles use the same formula.
	 * It's Overridden in Bicycle because this use a different formula to calculate the
	 * maxVelocity.
	 *
	 * The four abstract functions are defined in Vehicle so they can already be used in
	 * maxVelocity even though the implementation follows later in a subclass
	 *
	 * Likewise the getDrag and getDensity functions are implemented in WaterVehicles,
	 * because all implemented WaterVehicles use the same values.
	 *
	 * I hope this much explanation will suffice
	 * */
}
