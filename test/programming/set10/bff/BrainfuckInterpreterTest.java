package programming.set10.bff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
class BrainfuckInterpreterTest {

	@Test
	void setProgramm() {

	}

	@Test
	void interpret() {
		BrainfuckInterpreter bfi = new BrainfuckInterpreter();

		bfi.setProgramm("Prints Yo! to the console, followed by a newline\n" + "+//////+++++++++++++++++++++++++.\n" + ">+///////-----------------.\n" + ">+/////+.");
		Assertions.assertEquals("Yo!",bfi.interpret());

		bfi.setProgramm("Prints 'Hello World!' to the console, followed by a newline\n" + "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>\n" + "---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.\n");
		Assertions.assertEquals("Hello World!", bfi.interpret());

	}

}