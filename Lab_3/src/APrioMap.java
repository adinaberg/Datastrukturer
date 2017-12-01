import java.util.Comparator;


public class APrioMap<K, V extends Comparable<? super V>> implements PrioMap<K, V> {
    
	PrioQueue heap = new BinHeap<Pair>();
	
	public void put(K k, V v) {
    	
    }
    public V get(K k) {
    	return null;
    }
    public Pair<K, V> peek() {
    	return null;
    }
    
    public Pair<K, V> poll() {
    	return null;
    }
	
	private class PairComparator implements Comparator<V>{
		public int compare(V v1, V v2) {
			
			
			
		}
	}

}
