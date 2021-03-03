package programming.set9.parser;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Bennet Blessmann
 * Created on 08. Jan. 2017.
 */
public class PNParserTest {

	@Test
	public void test1() throws Exception {
		Assertions.assertEquals("3 + 4 + 5",new PNParser("+ + 3 4 5").toString());
	}


	@Test
	public void test2() throws Exception

	{
		Assertions.assertEquals("(3 + 4) * 5", new PNParser("* + 3 4 5").toString());

	}

	@Test
	public void test3() throws Exception

	{
		Assertions.assertEquals("3 + 4 * 5", new PNParser("+ 3 * 4 5").toString());

	}

	@Test
	public void test4() throws Exception

	{
		Assertions.assertEquals("3 * (4 + 5)", new PNParser("* 3 + 4 5").toString());

	}

}