import acm.program.ConsoleProgram;

/**
 * Created by stu201758 on 11/11/16.
 */
public class DigitalRoot extends ConsoleProgram {

	@Override
	public void run() {
		int input = readInt("Will compute DigitalRoot when given a positve number!: ");
		if(input < 0) { //handle negative numbers as described in the exercise
			println("Error");
			return;
		}
		int temp; //this will temporary store the current sum of the digits
		while(input>9){ //stop when we have reached a single digit
			temp = 0;  //reset temp prior to adding the digits
			while(input!=0){ //if input is 0 we have added all digits
				temp += input%10; //get the LSD (least significant digit) and add it to temp
				input /= 10; //remove the LSD, so we can get the next digit in the next iteration
			}
			input = temp; //override input sum of its digits (technically input is 0 by now therefore it's not really the sum of it's current digits)
		}
		println(input);//output the output to meet the requirements
	}
}