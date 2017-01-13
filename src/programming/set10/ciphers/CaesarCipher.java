package programming.set10.ciphers;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class CaesarCipher {

	private final int KEY;

	CaesarCipher(CryptMode cm, int key){
		key%=26;
		key = key>=0?key : 26+key;
		KEY = cm==CryptMode.ENCRYPT?key:26-key;
	}

	/**
	 * Encrypts or decrypts the given text, depending on the mode of operation this
	 * cypher was created for.
	 *
	 * @param text the text to encode.
	 *
	 * @return encryted or decrypted version of the text.
	 */
	public String encode(String text) {
		StringBuilder ret = new StringBuilder(text.length());
		for(char c:text.toCharArray()){
			if(c>='A'&&c<='Z'){
				ret.append((c-'A'+KEY)%26+'A');
			}
			else if(c >= 'a' && c <= 'z'){
				ret.append((c - 'a' + KEY) % 26+'a');
			}else{
				ret.append(c);
			}
		}
		return ret.toString();
	}

}
