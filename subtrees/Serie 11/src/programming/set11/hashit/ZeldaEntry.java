package programming.set11.hashit;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */
public class ZeldaEntry<K,V> {

	private final K KEY;

	private final V VALUE;

	public ZeldaEntry(K key,V value){
		KEY = key;
	    VALUE = value;
	}

	public K getKey(){
		return KEY;
	}

	public V getValue(){
		return VALUE;
	}
}
