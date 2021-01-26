import acm.program.ConsoleProgram;

/**
 * Created by stu201758 on 11/11/16.
 */
public class Fibonacci extends ConsoleProgram{

	@Override
	public void run() {
		int input = readInt("Will compute the n-th Fibonacci number when given a number n that is at least 1!: ");

		if(input < 1) { //handle numbers out of range based on the requirements
			println("Error");
			return;
		}

		int last = 1; //the latest fibonacci number computed;

		//decrease input each iteration by one and stop when we reach 1
		for(int priorLast = 0;input>1;input--) {

			//priorLast is evaluated before the assignment last to priorLast
			last = priorLast + (priorLast = last);

		}
		println(last);//don't forget to deliver an answer, they may wait for ever, but they will probably /*move on*/ timeout
	}
}