package programming.set7.optimus;

import acm.program.ConsoleProgram;

/**
 * @author Bennet Blessmann
 *         Created on 10.12.2016.
 */
public class extends ConsoleProgram {
	@Override
	public void run() {
		int input = readInt("Please enter a Number greater than 2:");
		if (input < 2) {
			/*
			* the throw keyword expects an Exception or Subclass Instance
			* it will terminate execution at the current point
			*
			* if not handled will cause the Program/JVM to be terminated
			*
			* an example of an exception is the following IllegalStateException
			* that is passed to the throw statement
			*/

			throw new IllegalStateException("Input needs to be at least 2!");
		}

		int[] a = sieve(input);
		for (int i : a) {
			if (i != -1) {
				println(i);
			}
		}

	}

	/**
	 * sieve the numbers 2 to
	 *
	 * @param n (inclusive)
	 * @return an array with 2 to n
	 * non primes replaced with -1
	 * length is n-1
	 * index 0 is 2
	 * index n-2 is n or -1 if n is not a prime
	 */
	private int[] sieve(int n) {
		/*
		 * an array is a collection of variables
		 * the length can only be set on instance creation
		 * the values stored in an array can be accessed and set using their index
		 *
		 * e.g.
		 *
		 * int[] a;// a variable of type int array
		 * a = new int[9]; // store an int array of length 9 in a
		 * a[5] = 3// set's the fifth element in the array stored in a to 3
		 * a = {1,2}// store in a the array with length 2 and  1 at index 0 and 2 at index 1
		 * */

		int[] primes = new int[n - 1];
		for (int i = 0; i < primes.length; i++) {
			primes[i] = i + 2;
		}

		int start = 0;

		while (primes.length > start) {
			int step = 1;
			for (int i = start; i < primes.length; i += step) {
				if (step == 1 && primes[i] != -1) {
					step = primes[i];
				} else {
					primes[i] = -1;
				}
			}
			start++;
		}
		return primes;
	}
}
/*
* List is never used in this program,
* a list is a collection of object with a given type/supertype
* a list does not have a fixed length
*
* because list usually use generics, primitives can't usually be used by Lists,
* because generics do not support those at the moment
*
* object can usually be added, removed, inserted, checked for existence in the list
* the length of the list will usually be returned by the size() method
* */