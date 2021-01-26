import acm.program.ConsoleProgram;

/**
 * Created by stu201758 on 11/18/16.
 */
public class PowerSet extends ConsoleProgram {

	public void run(){
		int input = getInput(); //get the user input
		long now = System.currentTimeMillis();
		String s  = genPowSet(input); //get the powerset
		long dif = System.currentTimeMillis()-now;
		System.out.println("It took "+dif+" milliseconds!");
		println("The powerset of "+getListFromFlag((2<<input)-1)+" is");
		println(s);//print the powerset
	}

	/**
	 * This function generates the powerset of the set
	 * with the natural Numbers form 0 to input,
	 *
	 * @param input the natural number N that is the upper bound of the set for which the powerset is generated
	 * @return the powerset as a string
	 * */
	private String genPowSet(int input) {
		int stopAt = 2<<input; //calculate stop condition so it won't be calculated every loop cycle
		StringBuilder sb = new StringBuilder(); // StringBuilder is more efficient than String appending
		sb.append("{ "); // start with opening bracket an a space
		for(int i = 0; i<stopAt;i++){ // for each subset
			sb.append(getListFromFlag(i)); // append the subset
			if(i!=stopAt-1){ //don't add a comma after the last set
				sb.append(',');
			}
			sb.append(' '); // space after each set
		}
		sb.append('}'); //  end bracket
		return sb.toString(); //nun wird ein String draus
	}

	/**
	 * @param flag the integer representing the set to generate
	 * @return returns the set represented by the flag parameter
	 * */
	private StringBuilder getListFromFlag(int flag) {
		StringBuilder sb = new StringBuilder(); // see genPoweSet
		sb.append("{ "); // open bracket
		for(int number = 0;flag!=0;flag>>=1,number++){ //insert the numbers of the set represented by the flag
			if((flag&1)!=0){//is the current number part of the set
				sb.append(number); // append the current number
				if(flag!=1){ // don't append a comma if last number in set
					sb.append(',');
				}
				sb.append(' ');// space after each number
			}
		}
		sb.append('}');// closing bracket
		return sb;// return the set
	}

	/**
	 * Ask the user for an integer form 0 to 10 inclusive until such integer is entered
	 * @return the first valid input
	 * */
	private int getInput(){
		int result;
		while((result=readInt("Enter N (0 <= N <= 10): "))<0||result>10);// read the input while out of range
		return result;//return the input
	}
}