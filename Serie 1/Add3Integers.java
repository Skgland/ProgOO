import acm.program.ConsoleProgram;

/**
 * Created by Bennet on 29.10.2016.
 */
public class Add3Integers extends ConsoleProgram{

	/*
	* I use the three variables with the names n1, n2 and n3.
	*
	* The variable with the name n1 has the type int and will be set to the answer the user delivers after being ask "Enter n1:"
	* The variable with the name n2 has the type int and will be set to the answer the user delivers after being ask "Enter n2:"
	* The variable with the name n3 has the type int and will be set to the answer the user delivers after being ask "Enter n3:"
	*
	* This Program mentions the class ConsoleProgram and Add3Integers the later one being itself
	* Add3Integers is a subclass of ConsoleProgram while ConsoleProgram is Add3Integers superclass
	* */

	@Override
	public void run(){
		println("This programm adds three integers.");
		int n1 = readInt("Enter n1:"),n2 = readInt("Enter n2:"),n3 = readInt("Enter n3:");
		print("The total is "+(n1+n2+n3)+".");
	}
}
