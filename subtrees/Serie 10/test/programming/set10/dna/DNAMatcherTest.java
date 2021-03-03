package programming.set10.dna;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by BB20101997 on 15. Jan. 2017.
 */
class DNAMatcherTest {
	@Test
	void findFirstBindingPosition() {
		String src = "ATTGC";
		String cmp = "TAACG";

		Assertions.assertEquals(0,DNAMatcher.findFirstBindingPosition(src, cmp));
	}

}