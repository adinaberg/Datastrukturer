import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {

	// BinHeap for debugging; change to PrioQueue!
	public BinHeap<Pair<K, V>> heap;
	// Mapping from key value to Pair with exactly same key value and value associated with key
	Map<K, Pair<K,V>> map;

	public APrioMap() {
		heap = new BinHeap<Pair<K, V>>(new PairComparator<K, V>());
		map = new HashMap<K, Pair<K,V>>();
	}

	public void put(K k, V v) {
		if (map.containsKey(k)) {
			// Do not have to remove old (k,Pair<k,v>) from map since it is overwritten
			heap.remove(map.get(k));
			//System.out.println("In APrioMap removing " + map.get(k) + " from heap");
			//System.out.println("Heap after remove " + heap.getList());
		}
		// If key k already is contained in map, old value is replaced
		Pair<K, V> newPair = new Pair<K, V>(k,v);
		map.put(k, newPair);
		//System.out.println("In APrioMap adding " + newPair + " to heap" );
		// Place new Pair in heap
		heap.add(newPair);	
	}

	
	// Returns value of 
	public V get(K k) {
		// Do not want to look up k twice
		Pair<K, V> p = map.get(k);
		if (p != null) {
			return p.b;
		}
		return null;
	}
	
	// Returns highest prio-Pair
	public Pair<K, V> peek() {
		return heap.peek();
	}
	
	// Returns and removes highest prio-Pair
	public Pair<K, V> poll() {
		Pair<K, V> best = heap.peek();
		// Only remove from map and heap if heap not empty
		if (best != null) {
			// Remove that specific entry in map (search by key, guaranteed to find right map entry since one key only maps to one value)
			map.remove(best.a);
			// Remove Pair from heap and return
			return heap.poll();
		}
		return null;
	}


	// For debugging only, remove later
	public ArrayList<Pair<K,V>> getPrioList() {
		return heap.getList();
	}


}
