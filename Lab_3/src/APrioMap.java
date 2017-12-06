import java.util.HashMap;
import java.util.Map;


public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {

	public PrioQueue<Pair<K, V>> heap;
	// Mapping from key value to Pair with exactly same key value and value associated with key
	Map<K, Pair<K,V>> map;

	//Constructor
	public APrioMap() {
		heap = new FastBinHeap<Pair<K, V>>(new PairComparator<K, V>());
		map = new HashMap<K, Pair<K,V>>();
	}

	public void put(K k, V v) {
		if (map.containsKey(k)) {
			// Do not have to remove old (k,Pair<k,v>) from map since it is overwritten
			heap.remove(map.get(k));
		}
		Pair<K, V> newPair = new Pair<K, V>(k,v);
		// If key k already is contained in map, old value is replaced
		map.put(k, newPair);
		// Place new Pair in heap
		heap.add(newPair);	
	}

	
	// Returns value of k
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
}
