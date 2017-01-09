package programming.set9.datalab;
/*
 * Adapted from 
 * CS:APP Data Lab 
 * 
 * Originally, 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *   
 *   
 * READ THE FOLLOWING INSTRUCTIONS CAREFULLY.  
 * 
 * 
 * You will provide your solution to the Data Lab by
 * editing the collection of functions in this source file.
 * 
 * CODING RULES:
 * Replace the "return" statement in each function with one
 * or more lines of C code that implements the function. Your code 
 * must conform to the following style:
 * int Funct(arg1, arg2, ...) {
 *   // brief description of how your implementation works 
 *   int var1 = Expr1;
 *   ...
 *   int varM = ExprM;
 *   varJ = ExprJ;
 *   ...
 *   varN = ExprN;
 *   return ExprR;
 * }
 *
 * Each "Expr" is an expression using ONLY the following:
 * 1. Integer constants 0 through 255 (0xFF), inclusive. You are
 *    not allowed to use big constants such as 0xffffffff.
 * 2. Function arguments and local variables (no global variables).
 * 3. Unary integer operations ! ~
 * 4. Binary integer operations & ^ | + << >>
 *
 * Some of the problems restrict the set of allowed operators even further.
 * Each "Expr" may consist of multiple operators. You are not restricted to
 * one operator per line.
 *
 * You are expressly forbidden to:
 * 1. Use any control constructs such as if, do, while, for, switch, etc.
 * 2. Define or use any macros.
 * 3. Define any additional functions in this file.
 * 4. Call any functions.
 * 5. Use any other operations, such as &&, ||, -, or ?:
 * 6. Use any form of casting.
 *  
 * You may assume that your machine:
 * 1. Uses 2s complement, 32-bit representations of integers.
 * 2. Performs right shifts arithmetically.
 * 3. Has unpredictable behavior when shifting an integer by more
 *    than the word size.
 *
 * EXAMPLES OF ACCEPTABLE CODING STYLE:
 * //
 * // pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
 * //
 * public int pow2plus1(int x) {
 *   // exploit ability of shifts to compute powers of 2 
 *   return (1 << x) + 1;
 * }
 * 
 * //
 * // pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
 * //
 * public int pow2plus4(int x) {
 *   // exploit ability of shifts to compute powers of 2
 *   int result = (1 << x);
 *   result += 4;
 *   return result;
 * }
 *   
 * NOTES:
 *  1. Use the iLearn to
 *     check the correctness of your solutions.
 *  2. Each function has a maximum number of operators (! ~ & ^ | + << >>)
 *     that you are allowed to use for your implementation of the function.
 *     The max operator count is checked by dlc. Note that '=' is not
 *     counted; you may use as many of these as you want without penalty.
 *  3. Use the btest test harness to check your functions for correctness.
 *  4. The maximum number of ops for each function is given in the
 *     header comment for each function. If there are any inconsistencies
 *     between the maximum ops in the writeup and in this file, consider
 *     this file the authoritative source.
 *  
 */

public class Bits {

	/** 
	 * abs - absolute value of x (except returns TMin for TMin)
	 *   Example: abs(-1) = 1.
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 10
	 *   Rating: 4
	 */
	public static int abs(int x) {
		return (x^(x>>31))-(x>>31);
	}

	/** 
	 * addOK - Determine if can compute x+y without overflow
	 *   Example: addOK(0x80000000,0x80000000) = 0,
	 *            addOK(0x80000000,0x70000000) = 1, 
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 20
	 *   Rating: 3
	 */
	public static int addOK(int x, int y) {
		return ((x>>31^y>>31)|((~(x>>31))^(y>>31)&(~(x>>31))^((x+y)>>31)))&1;
	}
	
	/** 
	 * bitAnd - x&y using only ~ and | 
	 *   Example: bitAnd(6, 5) = 4
	 *   Legal ops: ~ |
	 *   Max ops: 8
	 *   Rating: 1
	 */
	public static int bitAnd(int x, int y) {
		return ~(~x|~y);
	}
	
	/**
	 * bitParity - returns 1 if x contains an odd number of 0's
	 *   Examples: bitParity(5) = 0, bitParity(7) = 1
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 20
	 *   Rating: 4
	 */	
	public static int bitParity(int x) {
		return ((x=(x=(x=(x=x^(x>>16))^(x>>8))^(x>>4))^(x>>2))^(x>>1))&1;
		}
	
	/** 
	 * bitXor - x^y using only ~ and & 
	 *   Example: bitXor(4, 5) = 1
	 *   Legal ops: ~ &
	 *   Max ops: 14
	 *   Rating: 2
	 */
	public static int bitXor(int x, int y) {
		return (~(x&y))&(~(~x&~y));
	}
	
	/** 
	 * isLess - if x < y  then return 1, else return 0 
	 *   Example: isLess(4,5) = 1.
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 24
	 *   Rating: 3
	 */
	public static int isLess(int x, int y) {
		return (x+(~y+1))>>31&1;
	}
	
	/** 
	 * isNonZero - Check whether x is nonzero using
	 *              the legal operators except !
	 *   Examples: isNonZero(3) = 1, isNonZero(0) = 0
	 *   Legal ops: ~ & ^ | + << >>
	 *   Max ops: 10
	 *   Rating: 4 
	 */
	public static int isNonZero(int x) {
		return ((x|~x+1)>>31)&1;
	}
	
	/** 
	 * isNotEqual - return 0 if x == y, and 1 otherwise 
	 *   Examples: isNotEqual(5,5) = 0, isNotEqual(4,5) = 1
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 10
	 *   Rating: 2
	 */
	public static int isNotEqual(int x, int y) {
		x = x ^ y;
		x = ((x | (~x + 1)) >> 31) & 1;
		return x;
	}
	
	/** 
	 * logicalNeg - implement the ! operator, using all of 
	 *              the legal operators except !
	 *   Examples: logicalNeg(3) = 0, logicalNeg(0) = 1
	 *   Legal ops: ~ & ^ | + << >>
	 *   Max ops: 12
	 *   Rating: 4 
	 */
	public static int logicalNeg(int x) {
		return ((((~x+1)>>31)|(x>>31))^1)&1;
	}
	
	/** 
	 * minusOne - return a value of -1 
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 2
	 *   Rating: 1
	 */
	public static int minusOne() {
	    return ~0;
	}
	
	/**
	 * multFiveEights - multiplies by 5/8 rounding toward 0.
	 *   Examples: multFiveEights(77) = 48
	 *             multFiveEights(-22) = -13
	 *   You can assume |x| < (1 << 29)
	 *   Legal ops: ! ~ & ^ | + << >>
	 *   Max ops: 12
	 *   Rating: 3
	 */
	public static int multFiveEights(int x) {
		int sign = (x >> 31);
		x = (x^sign)+(sign&1);
		x = ((x << 2) + x) >> 3;
		sign = sign^(x>>31);
		return (sign&1)+(sign^x);
	}
	
	/** 
	 * sum3 - x+y+z using only a single '+'
	 *   Example: sum3(3, 4, 5) = 12
	 *   Legal ops: ! ~ & ^ | << >>
	 *   Max ops: 16
	 *   Rating: 3
	 */
	/* A helper routine to perform the addition.  Don't change this code */
	private static int sum(int x, int y) {
	  return x+y;
	}

	public static int sum3(int x, int y, int z) {
	  int word1 = 0;
	  int word2 = 0;
	  /**************************************************************
	     Fill in code below that computes values for word1 and word2
	     without using any '+' operations 
	  ***************************************************************/
		word1 = x^y^z;
		word2 = ((x&y)|(x&z)|(y&z))<<1;
	  /**************************************************************
	     Don't change anything below here
	  ***************************************************************/
	  return sum(word1,word2);
	}

}