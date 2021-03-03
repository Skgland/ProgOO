package programming.set9.morse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bennet Blessmann
 * Created on 08. Jan. 2017.
 */
public class MorseCoder {

	//a map storing the conversion from the alphabet to international morse code
	private static final Map<Character, String> charToMorse = new HashMap<>();

	static {
		/*
		fill in the conversion map
		* */
		charToMorse.put('A', ".-");
		charToMorse.put('B', "-...");
		charToMorse.put('C', "-.-.");
		charToMorse.put('D', "-..");
		charToMorse.put('E', ".");
		charToMorse.put('F', "..-.");
		charToMorse.put('G', "--.");
		charToMorse.put('H', "....");
		charToMorse.put('I', "..");
		charToMorse.put('J', ".---");
		charToMorse.put('K', "-.-");
		charToMorse.put('L', ".-..");
		charToMorse.put('M', "--");
		charToMorse.put('N', "-.");
		charToMorse.put('O', "---");
		charToMorse.put('P', ".--.");
		charToMorse.put('Q', "--.-");
		charToMorse.put('R', ".-.");
		charToMorse.put('S', "...");
		charToMorse.put('T', "-");
		charToMorse.put('U', "..-");
		charToMorse.put('V', "...-");
		charToMorse.put('W', ".--");
		charToMorse.put('X', "-..-");
		charToMorse.put('Y', "-.--");
		charToMorse.put('Z', "--..");

		charToMorse.put('1', ".----");
		charToMorse.put('2', "..---");
		charToMorse.put('3', "...--");
		charToMorse.put('4', "....-");
		charToMorse.put('5', ".....");
		charToMorse.put('6', "-....");
		charToMorse.put('7', "--...");
		charToMorse.put('8', "---..");
		charToMorse.put('9', "----.");
		charToMorse.put('0', "-----");

	}

	/**
	 * Returns a new string which is the morse code version of the input string.
	 * Each word is on a separate line. The morse representation of each
	 * character of the input string is separated from the next by a space
	 * character in the output string.
	 *
	 * @param input the input string.
	 * @return the morse code version of the input string, ignoring all characters in the input string that cannot be
	 * represented in international morse code.
	 */
	public static String encode(String input) {
		StringBuilder stringBuilder = new StringBuilder(); //to store the result
		// remove ß because it would be converted to SS
		// than to uppercase and split at " "
		// iterate over the resulting array of words
		for (String s : input.replace("ß", "").toUpperCase().split(" ")) {
			//for each char in a word
			for (char c : s.toCharArray()) {
				//if the char can be converted to morse
				if (charToMorse.containsKey(c)) {
					//append the morse representation
					stringBuilder.append(charToMorse.get(c));
					//append a space
					stringBuilder.append(' ');
				}
			}
			//end of word append new line
			stringBuilder.append('\n');
		}
		//return the resulting string
		return stringBuilder.toString();
	}

	/**
	 * Returns a new string which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encode method.
	 *
	 * @param input morse code input string.
	 * @return natural language version or {@code null} if the input string could not be properly parsed.
	 */
	public static String decode(String input) {
		StringBuilder stringBuilder = new StringBuilder();//to store the result
		for (String s : input.split("\n")) { //split into lines and iterate over them
			for (String c : s.split(" ")) { //split into morse codes and iterate over them
				if (charToMorse.containsValue(c)) { //can be converted back
					//find the entry in the map
					for (Map.Entry<Character, String> entry : charToMorse.entrySet()) {
						if (c.equals(entry.getValue())) {
							//append the letter
							stringBuilder.append(entry.getKey());
							break;
						}
					}
				} else { //else fail and return null
					return null;
				}
			}
			//end of line append " "
			stringBuilder.append(' ');
		}
		//return the result
		return stringBuilder.toString();
	}
}
