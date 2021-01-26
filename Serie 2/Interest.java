import acm.program.ConsoleProgram;

/**
 * Created by Bennet on 04.11.2016.
 */
public class Interest extends ConsoleProgram {

	public void run(){
		double balance = readDouble("Enter Balance:");//read in the balance
		double interest = readDouble("Enter Interest Rate:");//read in the interest rate
		println(balance*(100+interest)/100);//print balance after one year
		println(balance*(100+interest)*(100+interest)/10000);//print balance after two years
	}

}
