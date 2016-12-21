package programming.set8.links;

import org.junit.Test;

/**
 * @author Bennet Blessmann
 *         Created on 21.12.2016.
 */
public class LinkedElementTest {
	@Test
	public void get() throws Exception {
		LinkedElement<String> le = new LinkedElement<>();
		le.set(0,"1");
		for(int i = 2;i<=10;i++){
			le.add(""+i);
		}

		for(int i = 0;i<10;i++){
			assert le.get(i).equals(""+(i+1));
		}
	}

}