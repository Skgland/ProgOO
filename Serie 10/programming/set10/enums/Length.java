package programming.set10.enums;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public enum Length {

	ARSHIN(0.71),FINGER(0.022225),METRE(1), HORSE_LENGTH(2.4), PARSEC(30_856_776_000_000_000d),PLUTO_RADIUS(1_186_000);

	private final double IN_METERS;

	private Length(double inMeters){
		IN_METERS = inMeters;
	}

	/**
	 * Returns how much one of this unit is in metres.
	 *
	 * @return one unit in metres.
	 */
	public double getUnitLengthInMetres() {
		return IN_METERS;
	}

	/**
	 * Converts the given amount measured in the current length unit to how much it would be
	 * in the target unit.
	 *
	 * @param targetLength the target unit of length.
	 * @param amount       the length to convert to the target length.
	 *
	 * @return the corresponding length in the target unit.
	 */
	public double convertTo(Length targetLength, double amount) {
		return amount*IN_METERS/targetLength.IN_METERS;
	}

}