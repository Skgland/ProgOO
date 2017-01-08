package programming.set9.morse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BB20101997 on 08. Jan. 2017.
 */
public class MorseCoder {

	private static final Map<Character, String> charToMorse = new HashMap<>();
	private static final Map<String, Character> morseToChar = new HashMap<>();

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

		for(Map.Entry<Character,String> set:charToMorse.entrySet()){
			morseToChar.put(set.getValue(),set.getKey());
		}
	}

	/**
	 * Returns a new string which is the morse code version of the input string.
	 * Each word is on a separate line. The morse representation of each
	 * character of the input string is separated from the next by a space
	 * character in the output string.
	 *
	 * @param input the input string.
	 *
	 * @return the morse code version of the input string, ignoring all characters in the input string that cannot be
	 * represented in international morse code.
	 */
	public static String encode(String input) {
		System.out.println(input);
		StringBuilder stringBuilder = new StringBuilder();
		for(String s : input.replace("ß","").toUpperCase().split(" ")) {
			for(char c:s.toCharArray()){
				if(charToMorse.containsKey(c)){
					stringBuilder.append(charToMorse.get(c));
					stringBuilder.append(' ');
				}
			}
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}

	/**
	 * Returns a new string which is the natural-language version of the input
	 * string, which is assumed to be in morse code format as produced by the
	 * encode method.
	 *
	 * @param input morse code input string.
	 *
	 * @return natural language version or {@code null} if the input string could not be properly parsed.
	 */
	public static String decode(String input) {
		StringBuilder stringBuilder = new StringBuilder();
		for(String s : input.split("\n")) {
			for(String c : s.split(" ")) {
				if(charToMorse.containsValue(c)) {
					charToMorse.entrySet().forEach(e->{if(c.equals(e.getValue())){stringBuilder.append(e.getKey());}});
				}else{
					return  null;
				}
			}
			stringBuilder.append(' ');
		}
		return stringBuilder.toString();
	}
}
