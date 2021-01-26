import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Bennet on 28.11.2016.
 */
public class Ship extends WaterVehicle {

	/**
	 * the volume, length and power of the ship
	 */
	public final double VOLUME, LENGTH, POWER;

	/**
	 * create a ship with the name
	 *
	 * @param name   and the displacement volume of
	 * @param vol    and the length of
	 * @param length
	 */
	public Ship(String name, double power, double vol, double length) {
		super(name);
		POWER = power;
		VOLUME = vol;
		LENGTH = length;
	}

	/**
	 * The power of the ship in PS
	 */
	@Override
	double getPower() {
		return PSToW(POWER);
	}

	/***
	 * the surface area used to calculate the max velocity
	 */
	@Override
	double getSurface() {
		return VOLUME / LENGTH;
	}

	@Override
	public String toString() {
		//Locale English for a Comma as a thousands separator
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
		return NAME + " (" + nf.format((int) POWER) + " PS, " + nf.format((int) VOLUME) + "m³, " + (int) LENGTH + "m): " + (int) getMaxVelocity() + "km/h (" + (int) KMHToKnots(getMaxVelocity()) + "kts)";
	}
}
