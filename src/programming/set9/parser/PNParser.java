package programming.set9.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BB20101997 on 08. Jan. 2017.
 */
public class PNParser {


	SimpleTree st;

	/**
	 * The constructor expecting the expression in polish notation.
	 */
	public PNParser(String pNExpression) {
		List<String> elemetns =new ArrayList<>(Arrays.asList(pNExpression.split(" ")));
		st = parse(elemetns);
		if(elemetns.size()!=0){
			throw new IllegalArgumentException("Too many Tokens!");
		}
	}

	private SimpleTree parse(List<String> elemets) {
		if(elemets.isEmpty()){
			throw new IllegalArgumentException("Not enough Tokens!");
		}
		String content = elemets.get(0);
		elemets.remove(0);
		if("+".equals(content) || "*".equals(content)) {
			return new SimpleTree(content, parse(elemets), parse(elemets));
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
		boolean       nextfactor = !st.getContent().equals("+");
		boolean       parents    = factor && !nextfactor;
		StringBuilder sb         = new StringBuilder();

		sb.append(parents ? "(" : "");
		if(st.getLeft() != null) {
			sb.append(simpleTreeToString(st.getLeft(), nextfactor));
			sb.append(' ');
		} sb.append(st.getContent());
		if(st.getRight() != null) {
			sb.append(' ');
			sb.append(simpleTreeToString(st.getRight(), nextfactor));
		}
		sb.append(parents ? ")" : "");
		return sb.toString();
	}
}
