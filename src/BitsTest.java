import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bennet Blessmann
 *         Created on 09.01.2017.
 */
class BitsTest {

	@Test
	void abs() {
		Assertions.assertEquals(Math.abs(-5), Bits.abs(-5));
		Assertions.assertEquals(Math.abs(7), Bits.abs(7));
		Assertions.assertEquals(Math.abs(0), Bits.abs(0));
		Assertions.assertEquals(Math.abs(-1), Bits.abs(-1));
	}

	@Test
	void addOK() {

		int[][] test = {
				{Integer.MAX_VALUE - 5, 7},
				{5, 7},
				{Integer.MIN_VALUE+5,-7},
				{-5,-7},
				{Integer.MIN_VALUE,Integer.MAX_VALUE}
		};

		for (int[] t : test) {
			try {
				Math.addExact(t[0], t[1]);
				Assertions.assertEquals(1, Bits.addOK(t[0], t[1]));
			} catch (ArithmeticException ae) {
				Assertions.assertEquals(0, Bits.addOK(t[0], t[1]));
			}
		}
	}

		@Test
		void bitAnd () {
			Assertions.assertEquals(4 & 8, Bits.bitAnd(4, 8));
			Assertions.assertEquals(24 & 5, Bits.bitAnd(24, 5));
			Assertions.assertEquals(9 & 3, Bits.bitAnd(9, 3));
			Assertions.assertEquals(100_000 & 73, Bits.bitAnd(100_000, 73));
		}

		@Test
		void bitParity () {
			Assertions.assertEquals(0, Bits.bitParity(0x010111), "0x010111");
			Assertions.assertEquals(1, Bits.bitParity(0x010101), "0x010101");
			Assertions.assertEquals(1, Bits.bitParity(0x01011011), "0x01011011");
			Assertions.assertEquals(0, Bits.bitParity(0x01101111), "0x01101111");
		}

		@Test
		void bitXor () {
			Assertions.assertEquals(4 ^ 5, Bits.bitXor(4, 5));
			Assertions.assertEquals(6 ^ 92, Bits.bitXor(6, 92));
			Assertions.assertEquals(42 ^ 37, Bits.bitXor(42, 37));
			Assertions.assertEquals(15 ^ 85, Bits.bitXor(15, 85));
		}

		@Test
		void isLess () {
			Assertions.assertEquals(1, Bits.isLess(-4, 59));
			Assertions.assertEquals(0, Bits.isLess(80, 59));
			Assertions.assertEquals(1, Bits.isLess(102, 201));
			Assertions.assertEquals(0, Bits.isLess(0, -90));
		}

		@Test
		void isNonZero () {
			Assertions.assertEquals(0,Bits.isNonZero(0));
			Assertions.assertEquals(1,Bits.isNonZero(45));
			Assertions.assertEquals(1,Bits.isNonZero(-45));
		}

		@Test
		void isNotEqual () {
			Assertions.assertEquals(1,Bits.isNotEqual(0,15));
			Assertions.assertEquals(0,Bits.isNotEqual(0,0));
			Assertions.assertEquals(1,Bits.isNotEqual(-5,7));
			Assertions.assertEquals(0,Bits.isNotEqual(-4,-4));
		}

		@Test
		void logicalNeg () {
			Assertions.assertEquals(1,Bits.logicalNeg(0));
			Assertions.assertEquals(0,Bits.logicalNeg(45));
			Assertions.assertEquals(0,Bits.logicalNeg(-45));
		}

		@Test
		void minusOne () {
			Assertions.assertEquals(-1, Bits.minusOne());
		}

		@Test
		void multFiveEights () {

		}

		@Test
		void sum3 () {
			Assertions.assertEquals(3+5+6,Bits.sum3(3,5,6));
			Assertions.assertEquals(3+(-50)+6,Bits.sum3(3,-50,6));
			Assertions.assertEquals(3+5+67,Bits.sum3(3,5,67));
		}

	}