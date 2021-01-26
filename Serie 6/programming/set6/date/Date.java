package programming.set6.date;

/**
 * @author Bennet Blessmann
 *         Created on 03.12.2016.
 */
public class Date extends Object {

	private final int iYear, iDay;
	private final Month iMonth;

	/**
	 * Enum containing the months
	 */
	private enum Month {
		JANUAR(31), FEBRUAR(28) {
			@Override
			public int getDays(int year) {
				return ((year % 4 == 0) && ((year % 100 != 0) || year % 400 == 0)) ? 29 : 28;
			}
		},
		MÄRZ(31), APRIL(30), MAI(31), JUNI(30), JULI(31),
		AUGUST(31), SEPTEMBER(30), OKTOBER(31), NOVEMBER(30), DEZEMBER(31);

		private int days;

		Month(int days) {
			this.days = days;
		}

		/**
		 * days of the month based on year
		 * only overridden by FEBRUAR
		 */
		public int getDays(int year) {
			return days;
		}

		/**
		 * Only first letter capital
		 */
		@Override
		public String toString() {
			return name().charAt(0) + name().substring(1).toLowerCase();
		}
	}

	/**
	 * check if
	 *
	 * @param year,
	 * @param month and
	 * @param day   form a valid date
	 */
	public static boolean validate(int year, int month, int day) {
		int days = getDaysInMonth(year, month);
		if (days == 0 || days < day || day < 1) {
			return false;
		}
		return true;
	}

	/**
	 * get the days of
	 *
	 * @param month in the given
	 * @param year
	 */
	public static int getDaysInMonth(int year, int month) {
		//is month valid
		if (month < 1 || month > 12) {
			return 0;
		}
		return Month.values()[month - 1].getDays(year);
	}

	/**
	 * creats a new date instance for the given
	 *
	 * @param year,
	 * @param month and
	 * @param day
	 * @throws IllegalArgumentException if the given date is not vaild
	 */
	public Date(int year, int month, int day) {
		if (!validate(year, month, day)) {
			throw new IllegalArgumentException("This is not a valid date.");
		} else {
			iYear = year;
			iMonth = Month.values()[month - 1];
			iDay = day;
		}
	}

	/**
	 * getter for the day of this instance
	 * */
	public int getDay() {
		return iDay;
	}

	/**
	 * getter for the month of this instance
	 * */
	public int getMonth() {
		return iMonth.ordinal() + 1;
	}

	/**
	 * getter for the year of this instance
	 * */
	public int getYear() {
		return iYear;
	}

	/**
	 * @return what day in the year is represented by this instance
	 *  e.g.
	 *  1 for first of january
	 *  32 for first of february
	 *  etc.
	 * */
	public int dayOfYear() {
		int count = 0;
		for (Month m = Month.JANUAR; m.ordinal() < iMonth.ordinal(); m = Month.values()[m.ordinal() + 1]) {
			count += m.getDays(iYear);
		}
		return count + iDay;
	}

	/**
	 * calculate the difference between two dates if they are in the same year
	 * if they are not in the same year return 0
	 * if other is prior to the current date returns a negative value
	 * if other is after the current date returns a positive value
	 * */
	public int sameYearDiff(Date other) {
		if (iYear != other.iYear) {
			return 0;
		}
		return other.dayOfYear() - dayOfYear();
	}

	/**
	 * @return the string representation of this date consisting on the full name of the month(M) the day(D) and the year(Y)
	 * M D,YYYY
	 * */
	@Override
	public String toString() {
		return iMonth + " " + iDay + "," + iYear;
	}
}