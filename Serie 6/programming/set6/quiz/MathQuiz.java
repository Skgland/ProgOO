package programming.set6.quiz;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

/**
 * @author Bennet Blessmann
 *         Created on 03.12.2016.
 */
public class MathQuiz extends ConsoleProgram {

	private static final String ADD = "+", SUB = "-";
	private static final RandomGenerator RG = new RandomGenerator();

	/**
	 * The array of possible messages on success
	 */
	private static final String[] SM = {
			"Congratulation you are right!",
			"Yes that is correct!",
			"You are absolutely right!",
			"Perfect you succeeded!",
			"That was right brilliant!"
	};

	/**
	 * The array of possible failure messages
	 */
	private static final String[] FM = {
			"Sorry, but that was wrong. %s would have been right.",
			"%s would have been right.",
			"The right answer would have been %s.",
			"%s was right, may be next time."
	};

	@Override
	public void run() {
		/*
		 * Welcome the player
		 * */
		println("Welcome to the MathQuiz Program!");
		/*
		 * ask five questions
		 * */
		for (int i = 0; i < 5; i++) {
			askProblem();
		}
	}

	/**
	 * This method asks a question and checks the answer
	 * depending on the correctness a message will indicate success on a right answer or
	 * on a false answer will give the right answer and inform of the the failure to answer correctly
	 */
	private void askProblem() {
		boolean operator = RG.nextBoolean();
		String op = operator ? ADD : SUB;
		int op1 = RG.nextInt(0, 20);
		int op2 = RG.nextInt(0, operator ? 20 - op1 : op1);
		int res = readInt("What is " + op1 + ' ' + op + ' ' + op2 + '?');
		boolean cor = isCorrect(op1, op, op2, res);
		if (cor) {
			println(generateSuccessMessage());
		} else {
			println(generateFailMessage(operator ? op1 + op2 : op1 - op2));
		}

	}

	/**
	 * Given the two operands
	 * @param op1 and
	 * @param op2 and the operator
	 *
	 * @param op which is either "+" or "-"
	 *          will check if the
	 * @res is the answer to test for correctness
	 * @return true if op1 op op2 = res
	 * e.g.
	 * op1 = 1;
	 * op = "+";
	 * op2 = 2;
	 * res = 3;
	 * returns true
	 */
	public boolean isCorrect(int op1, String op, int op2, int res) {
		switch (op) {
			case ADD: {
				return op1 + op2 == res;
			}
			case SUB: {
				return op1 - op2 == res;
			}
			default:
				return false;
		}
	}

	/**
	 * return a random success message
	 * */
	private String generateSuccessMessage() {
		return SM[RG.nextInt(0, SM.length - 1)];
	}

	/**
	 * return a random failure Message with inserted correct result.
	 * */
	private String generateFailMessage(int correctResult) {
		return String.format(FM[RG.nextInt(0, FM.length - 1)], correctResult);
	}

}