package programming.set9.parser;

/**
 * A simple tree, where nodes contain strings.
 *
 */
public class SimpleTree {
	
	// Instance Variables
	private SimpleTree left, right;
	private String content;
	
	/**
	 * Constructor with no branches
	 * @param content
	 */
	public SimpleTree(String content) {
		this.content = content;
		left = null;
		right = null;
	}
	
	/**
	 * Constructor with branches
	 * 
	 * @param content
	 * @param left
	 * @param right
	 */
	public SimpleTree(String content, SimpleTree left, SimpleTree right) {
		this.content = content;
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public SimpleTree getLeft() {
		return left;
	}


	/**
	 * @return the right
	 */
	public SimpleTree getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(SimpleTree right) {
		this.right = right;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
