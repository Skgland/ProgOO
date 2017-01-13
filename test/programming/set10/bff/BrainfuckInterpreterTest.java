package programming.set10.bff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class BrainfuckInterpreterTest {

	static BrainfuckInterpreter bfi;

	@BeforeAll
	static void befor() {
		bfi = new BrainfuckInterpreter();
	}

	@Test
	void yo() {
		Assertions.assertEquals("Yo!\n", testProgramFile("yo.b"));
	}

	@Test
	void hello_world1() {
		Assertions.assertEquals("Hello World!\n", testProgramFile("hello1.b"));
	}

	@Test
	void hello_world2() {
		Assertions.assertEquals("Hello World!\n", testProgramFile("hello2.b"));
	}

	@Test
	void hanoi() {
		System.out.println(testProgramFile("hanoi.b"));
	}

	String testProgramFile(String fileName){
		try {
			File           file           = new File(fileName);
			char[]         prog;
			//noinspection ResultOfMethodCallIgnored
			new FileReader(file).read(prog = new char[(int) file.length()]);
			bfi.setProgram(prog);
			return bfi.interpret();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "ERROR!";
	}
}