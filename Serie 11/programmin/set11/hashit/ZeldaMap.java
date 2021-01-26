package programming.set11.hashit;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ZeldaMap implements a generic hash map.
 */
public class ZeldaMap<K, V> {

	/**
	 * nope won't use an array if a list will do the trick without a type safty warning, because it can't determine at
	 * Compile time if it will be type safe, would be so much less of a problem if generic array creation would be a thing :(
	*/
	private ArrayList<LinkedList<ZeldaEntry<K,V>>> buckets;
	private int                                    capacity;
	private int size = 0;

	/**
	 * The capacity sets the length of the bucket array.
	 * Must be greater than 0. Throws an IllegalArgumentException otherwise.
	 *
	 * @param capacity bucket array length
	 */
	public ZeldaMap(int capacity) {
		this.capacity = capacity;
		//Create the Buckets ArrayList
		buckets = new ArrayList<>();
		//init buckets with empty linked lists
		for(int i = 0;i<capacity;i++){
			buckets.add(new LinkedList<>());
		}
	}

	/**
	 * Put an item into the map.
	 * Throw an IllegalArgumentException if the key is null.
	 * Also, as we also don't accept null values, do the same.
	 *
	 * @param key the key of the item
	 * @param value the value of the item
	 */
	public void put(K key, V value) {
		if(key==null||value==null){
			throw new IllegalArgumentException("Neither Key nor Value may be null");
		}
		//put the entry in the corresponding bucket
		buckets.get(key.hashCode()%capacity).addFirst(new ZeldaEntry<>(key,value));
		size++;
	}

	/**
	 * Returns a value of a given key.
	 * Throw an IllegalArgumentException if the key is null.
	 *
	 * @param key the key of the item
	 *
	 * @return the value belonging to that key
	 */
	public V get(K key) {
		if(key == null) {
			throw new IllegalArgumentException("Key can't be null!");
		}
		//get the matching bucket
		LinkedList<ZeldaEntry<K, V>> linkedList = buckets.get(key.hashCode() % capacity);
		//find the right entry
		for(ZeldaEntry<K, V> entry : linkedList) {
			if(entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Retrieves the calculated collision ratio between entries added to the map and detected collisions during adding.
	 * For example, you added 100 items in the map and put created 50 collisions, the ratio would be 0.5,
	 * meaning that every second put operation caused an collision.
	 * Also remember to adjust your numbers after removing items.
	 *
	 * @return the average amount of collisions
	 */
	public double getCollisionRatio() {
		int collisions = 0;
		//sum up the collisions per bucket
		for(LinkedList linkedList:buckets){
			if(!linkedList.isEmpty()){
				collisions+=linkedList.size()-1;
			}
		}
		return collisions/(double)size;
	}

	/**
	 * Clears the entire map.
	 */
	public void clear() {
		size = 0;
		//clear all buckets
		for(LinkedList linkedList:buckets){
			linkedList.clear();
		}
	}

	/**
	 * Removes one key from the map.
	 * Throw an IllegalArgumentException if the key is null.
	 *
	 * @param key the key
	 */
	public void remove(K key) {
		if(key==null){
			throw new IllegalArgumentException("Key can't be null!");
		}
		LinkedList<ZeldaEntry<K,V>> linkedList = buckets.get(key.hashCode() % capacity);

		//find the element and remove it
		for(ZeldaEntry<K,V> entry:linkedList){
			if(entry.getKey().equals(key)){
				linkedList.remove(entry);
				size--;
				return;
			}
		}
	}

	/**
	 * Returns a list of all keys that are stored in the map.
	 *
	 * @return a list of keys
	 */
	public List<K> getKeys() {
		ArrayList<K> keys = new ArrayList<>(size);

		//collect all keys
		for(LinkedList<ZeldaEntry<K,V>> linkedList:buckets){
			for(ZeldaEntry<K,V> entry:linkedList ){
				keys.add(entry.getKey());
			}
		}
		return keys;
	}
}
