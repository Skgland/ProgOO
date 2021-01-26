package programming.set9.parser;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Bennet Blessmann
 *         Created on 08. Jan. 2017.
 */
public class PNParser {


	private final SimpleTree st; //to store the structure of the equation

	/**
	 * The constructor expecting the expression in polish notation.
	 */
	public PNParser(String pNExpression) {
		//turn the equation into its parts and save it in a list
		//linked list because we always get and remove the first element
		LinkedList<String> elements = new LinkedList<>(Arrays.asList(pNExpression.split(" ")));
		st = parse(elements); //parse the list a SimpleTree
		if (elements.size() != 0) { // not all tokens have been used by the parser
			throw new IllegalArgumentException("Too many Tokens!");
		}
	}

	private SimpleTree parse(LinkedList<String> elements) {
		if (elements.isEmpty()) {//can't parse an equation with no tokens left
			throw new IllegalArgumentException("Not enough Tokens!");
		}
		String content = elements.pop(); // get and remove the first element
		if ("+".equals(content) || "*".equals(content)) { // if it is an operation add branch
			return new SimpleTree(content, parse(elements), parse(elements));
		} else { //else add a leaf
			return new SimpleTree(content);
		}
	}

	/**
	 * @return the tree in infix notation as string.
	 */
	@Override
	public String toString() {
		//bootstrap the simpleTreeToString recursion and return the result as String
		return simpleTreeToString(st, false).toString();
	}

	static StringBuilder simpleTreeToString(SimpleTree st, boolean factor) {

		//to store the result
		StringBuilder sb = new StringBuilder();

		//not a tree is empty
		if (st == null) {
			return sb;
		}
		//is the current content not an addition necessary for deciding if parenthesis are necessary
		boolean nextFactor = !st.getContent().equals("+");
		//should parenthesis be added
		boolean parents = factor && !nextFactor;

		//the current tree
		sb.append(parents ? "(" : ""); //if necessary add parenthesis
		//if existent add left branch
		if (st.getLeft() != null) {
			sb.append(simpleTreeToString(st.getLeft(), nextFactor));
			sb.append(' ');
		}
		//add content
		sb.append(st.getContent());
		//if existent add right branch
		if (st.getRight() != null) {
			sb.append(' ');
			sb.append(simpleTreeToString(st.getRight(), nextFactor));
		}
		sb.append(parents ? ")" : "");//if necessary add parenthesis
		return sb;
	}
}