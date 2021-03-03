package programming.set9.zelda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * @author Bennet Blessmann
 *         Created on 09.01.2017.
 */
class ZeldaListTest {

	final ZeldaList<String> zl = new ZeldaList<>();
	final LinkedList<String> ll = new LinkedList<>();

	@BeforeEach
	public void init() {
		zl.add("Eins");
		zl.add("Drei");
		zl.add(1, "Zwei");
		zl.add(3, "Vier");

		ll.add("Eins");
		ll.add("Zwei");
		ll.add("Drei");
		ll.add("Vier");
	}

	@Test
	public void TestAddIndex() {
		for (int i = 0; i < zl.size(); i++) {
			Assertions.assertEquals(ll.get(i), zl.get(i));
		}
	}

	@Test
	public void TestSize() {
		Assertions.assertEquals(4, zl.size());
	}

}