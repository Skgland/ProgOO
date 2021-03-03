package programming.set9.zelda;

/**
 * A linked list element that stores a value and a link to the next element, if any.
 *
 * @param <T>
 *            data type of the value that can be stored in this element.
 */
class ZeldaElement<T> {

	/** The value this element holds. */
	private T value = null;
	/** Link to the next element. */
	private ZeldaElement<T> nextElement = null;
	
	
	/**
	 * Returns the value this element holds.
	 * 
	 * @return the value.
	 */
	public T getValue() {
		return value;
	}
	
	/**
	 * Sets the value this element holds.
	 * 
	 * @param value
	 *            the new value.
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * Returns the link to the next element, if any.
	 * 
	 * @return link to the next element, or {@code null} if there is no next element.
	 */
	public ZeldaElement<T> getNextElement() {
		return nextElement;
	}
	
	/**
	 * Sets the next element.
	 * 
	 * @param nextElement the new next element.
	 */
	public void setNextElement(ZeldaElement<T> nextElement) {
		this.nextElement = nextElement;
	}
	
}
