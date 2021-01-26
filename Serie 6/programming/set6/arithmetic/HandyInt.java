package programming.set6.arithmetic;

/**
 * An integer that provides arithmetic operations for great glory.
 */
public class HandyInt {
	/** The integer represented by an instance of this class. */
	/**
	 * should never be changed after initialisation therefor changed to final
	 */
	private final int theInt;

	/**
	 * Constructs a new handy integer representing the given int.
	 *
	 * @param theInt the integer to be represented by this instance.
	 */
	public HandyInt(int theInt) {
		this.theInt = theInt;
	}

	/**
	 * Constructs a new handy integer representing the integer represented by
	 * the given handy integer.
	 *
	 * @param handyInt the handy integer to intialize the new object with.
	 */
	public HandyInt(HandyInt handyInt) {
		this.theInt = handyInt.theInt;
	}

	/**
	 * Returns the integer represented by this instance.
	 *
	 * @return the represented integer.
	 */
	public int getInt() {
		return theInt;
	}

	/**
	 * Returns a handy integer that represents the sum of this and the other
	 * handy integer.
	 *
	 * @param other the handy integer to add.
	 * @return sum of the two handy integers.
	 */
	public HandyInt add(HandyInt other) {
		//should always return a new instance rather than changing the existing one
		return new HandyInt(theInt + other.theInt);
	}

	/**
	 * Returns a handy integer that represents the result of subtracting the
	 * other integer from this one.
	 *
	 * @param other the handy integer to subtract from this one.
	 * @return difference of the two handy integers.
	 */
	public HandyInt sub(HandyInt other) {
		//should always return a new instance rather than changing the existing one
		return new HandyInt(theInt - other.theInt);
	}

	@Override
	public String toString() {
		return Integer.toString(theInt);
	}
}
