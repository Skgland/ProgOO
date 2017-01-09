package programming.set9.zelda;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;

/**
 * @author Bennet Blessmann
 *         Created on 09.01.2017.
 */
public class ZeldaList<T> implements Iterable<ZeldaElement<T>> {

	//the current head of the list
	private ZeldaElement<T> headElement = new ZeldaElement<>();

	//the size of the list updated on add,remove and clear
	private int size = 0;

	/**
	 * Adds the given value to the end of the list.
	 *
	 * @param value the value to add. If {@code value == null}, nothing happens.
	 */
	public void add(T value) {
		if (value == null) {
			return;
		}

		ZeldaElement<T> cur = headElement;
		while (cur.getValue() != null) {
			if (cur.getNextElement() == null) {
				cur.setNextElement(cur = new ZeldaElement<>());
			} else {
				cur = cur.getNextElement();
			}
		}
		size++;
		cur.setValue(value);
	}

	/**
	 * Inserts the given value into the list at the given index. The element that was at that index
	 * previously ends up at {@code index + 1} after this method.
	 *
	 * @param index the index to insert the value at, with {@code 0 <= index <= getSize()}. If this
	 *              condition is not met, nothing happens.
	 * @param value the value to insert. If this is {@code null}, nothing happens.
	 */
	public void add(int index, T value) {
		if (0 > index || index > size()) {
			return; //index out of bounds
		}

		if (index == 0) { //head has to be changed
			ZeldaElement<T> nEl = new ZeldaElement<>();
			nEl.setNextElement(headElement);
			nEl.setValue(value);
			headElement = nEl;
			size++;
			return;
		}


		ZeldaElement<T> pre = headElement;
		while (index != 1) { //find the element that needs to be updated
			pre = pre.getNextElement();
			index--;
		}

		//create inserted link and update previous one
		ZeldaElement<T> nEl = new ZeldaElement<>();
		nEl.setNextElement(pre.getNextElement());
		nEl.setValue(value);
		pre.setNextElement(nEl);
		size++;

	}

	/**
	 * Removes the given value from the list if it's in there somewhere.
	 * only the first occurrence will be removed
	 *
	 * @param value the value to remove. If this is {@code null}, nothing is removed.
	 * @return {@code true} if the value was found and removed, {@code false} otherwise.
	 */
	public boolean remove(T value) {
		ZeldaElement<T> pre = null;
		ZeldaElement<T> cur = headElement;
		if (!contains(value)) {
			return false;
		}

		do {
			if (cur.getValue().equals(value)) {
				if (pre != null) {
					pre.setNextElement(cur.getNextElement());
				} else {
					headElement = cur.getNextElement();
				}
				size--;
				return true;
			}
			pre = cur;
			cur = cur.getNextElement();
		} while (true);
	}

	/**
	 * Removes all elements from the list.
	 */
	public void clear() {
		headElement = new ZeldaElement<>();
		size = 0;
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return number of elements.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the value at the given index in the list.
	 *
	 * @param index the index of the element whose value to return.
	 * @return the value at the given index, or {@code null} if the index is invalid.
	 */
	public T get(int index) {
		if (index < 0) {
			return null;
		}
		ZeldaElement<T> cur = headElement;
		while (index != 0 && cur != null) {
			cur = cur.getNextElement();
			index--;
		}
		if (cur != null) {
			return cur.getValue();
		}
		return null;
	}

	/**
	 * Sets the value at the given index.
	 *
	 * @param index index of the value to reset.
	 * @param value the new value. If this is {@code null}, nothing happens.
	 * @return the old value at the index, or {@code null} if the index was invalid or the new value
	 * is {@code null}.
	 */
	public T set(int index, T value) {
		if (index < 0 || index >= size() || value == null) {
			return null;
		}

		ZeldaElement<T> cur = headElement;
		while (index != 0) {
			cur = cur.getNextElement();
			index--;
		}


		T old = cur.getValue();
		cur.setValue(value);
		return old;
	}

	/**
	 * Returns the smallest index where the given value appears in the list, if it does.
	 *
	 * @param value the value to look for.
	 * @return the value's index or -1 if {@code value == null} or if the value is not in the list.
	 */
	public int indexOf(T value) {
		if (value == null) {
			return -1;
		}
		int count = 0;
		for(ZeldaElement cur:this){
			if(value.equals(cur.getValue())){
				return count;
			}
			count++;
		}
		return -1;
	}

	/**
	 * Checks if the given value appears anywhere in the list.
	 *
	 * @param value the value to search for.
	 * @return {@code true} if the value appears in the list, {@code false} if it doesn't or if it
	 * is {@code null}.
	 */
	public boolean contains(T value) {
		return indexOf(value) != -1;
	}


	/**
	 * Checks if the list contains any elements.
	 *
	 * @return {@code true} if the list is empty, {@code false} if it isn't.
	 */
	public boolean isEmpty() {
		return headElement.getValue() == null;
	}

	@Override
	public Iterator<ZeldaElement<T>> iterator() {
		return new Iterator<ZeldaElement<T>>() {
			ZeldaElement<T> next = headElement;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public ZeldaElement<T> next() {
				ZeldaElement<T> cur = next;
				next = next.getNextElement();
				return cur;
			}
		};
	}

	@Override
	public Spliterator<ZeldaElement<T>> spliterator() {
		return Spliterators.spliterator(iterator(),size,0);
	}
}
