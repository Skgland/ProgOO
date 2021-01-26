import acm.program.ConsoleProgram;

/**
 * Created by stu201758 on 11/11/16.
 */
public class TemperatureConverter extends ConsoleProgram{

	@Override
	public void run() {
		println("What do you want me to do?");
		println("(1) Convert Celsius to Fahrenheit");
		println("(2) Convert Fahrenheit to Celsius");
		int modus = readInt(1,2); //read the mode
		println("Mode: "+modus); //tell the user his choice
		double input;
		while((input=readDouble("Temperature: "))!=-1000){ //continue reading in till given the sentinel
			println(modus==1?celsiusFahrenheit(input):fahrenheitCelsius(input)); //output the correct conversion
		}
		println("Goodbye!"); //at the end we tell goodbye
	}

	public double fahrenheitCelsius(double fahrenheit){
		return (fahrenheit-32)*5/9; // multiplication from left to right with the brackets being evaluated first
	}

	public double celsiusFahrenheit(double celsius){
		return celsius/5*9+32;//from left to right first division and multiplication than addition
	}
}