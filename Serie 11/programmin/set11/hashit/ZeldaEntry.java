package programming.set11.hashit;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */
public class ZeldaEntry<K, V> {

	private final K KEY;

	private final V VALUE;

	/**
	 * @param key   the key for this entry
	 * @param value the value for this entry
	 */
	public ZeldaEntry(K key, V value) {
		KEY = key;
		VALUE = value;
	}

	/**
	 * gets the key
	 * */
	public K getKey() {
		return KEY;
	}

	/**
	 * get the value
	 * */
	public V getValue() {
		return VALUE;
	}
}
