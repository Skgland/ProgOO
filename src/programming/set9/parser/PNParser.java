package programming.set9.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Bennet Blessmann
 * Created on 08. Jan. 2017.
 */
public class PNParser {


	private final SimpleTree st;

	/**
	 * The constructor expecting the expression in polish notation.
	 */
	public PNParser(String pNExpression) {
		List<String> elements =new ArrayList<>(Arrays.asList(pNExpression.split(" ")));
		st = parse(elements);
		if(elements.size()!=0){
			throw new IllegalArgumentException("Too many Tokens!");
		}
	}

	private SimpleTree parse(List<String> elements) {
		if(elements.isEmpty()){
			throw new IllegalArgumentException("Not enough Tokens!");
		}
		String content = elements.get(0);
		elements.remove(0);
		if("+".equals(content) || "*".equals(content)) {
			return new SimpleTree(content, parse(elements), parse(elements));
		} else {
			return new SimpleTree(content);
		}
	}

	/**
	 * @return the tree in infix notation as string.
	 */
	@Override
	public String toString() {
		return simpleTreeToString(st, false);
	}

	static String simpleTreeToString(SimpleTree st, boolean factor) {
		if(st == null) { return ""; }
		boolean       nextFactor = !st.getContent().equals("+");
		boolean       parents    = factor && !nextFactor;
		StringBuilder sb         = new StringBuilder();

		sb.append(parents ? "(" : "");
		if(st.getLeft() != null) {
			sb.append(simpleTreeToString(st.getLeft(), nextFactor));
			sb.append(' ');
		} sb.append(st.getContent());
		if(st.getRight() != null) {
			sb.append(' ');
			sb.append(simpleTreeToString(st.getRight(), nextFactor));
		}
		sb.append(parents ? ")" : "");
		return sb.toString();
	}
}
