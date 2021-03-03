package programming.set10.ciphers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
class CaesarCipherTest {

	public static final String TEST_TEXT = "This is Sparta!";

	@Test
	void encode() {
		for(int i = -500;i<=500;i++){
			Assertions.assertEquals(TEST_TEXT,new CaesarCipher(CryptMode.DECRYPT,i).encode(new CaesarCipher(CryptMode.ENCRYPT,i).encode(TEST_TEXT)));
		}

		System.out.println(new CaesarCipher(CryptMode.ENCRYPT,1).encode(TEST_TEXT));
	}

}