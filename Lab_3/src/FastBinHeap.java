
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FastBinHeap<E> implements PrioQueue<E> {
	
	// List of elements element in increasing order
	List<E> heap;
	Comparator<? super E> comp;
	// Mapping from element to its position in the 
	Map<E, Integer> map;

	// Constructor
	public FastBinHeap(Comparator<? super E> comp) {
		heap = new ArrayList<E>();
		this.comp = comp;
		map = new HashMap<E, Integer>();
	}

	// Iterator
	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < heap.size() && heap.get(currentIndex) != null;
			}

			@Override
			public E next() {
				return heap.get(currentIndex++);
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}


	private void bubbleUp(int index) {
		if (heap.size() > 1 && index < heap.size()) {
			int parent;
			if (index != 0) {
				parent = (index - 1)/2;
				if (comp.compare(heap.get(index), heap.get(parent)) < 0) {					
					swap(index, parent);
					// Recursive call to bubbleUp
					bubbleUp(parent);
				}
			}
		}

	}


	public void bubbleDown(int index) {
		if (heap.size() > 1 && index < heap.size()) {
			int child1 = index*2 + 1;
			int child2 = index*2 + 2;
			int winnerChild;
			// If not at bottom
			if (child1  < heap.size()) {
				// Have only left child
				if (child2 > heap.size() - 1) {
					// If parent larger than child
					if (comp.compare(heap.get(index), heap.get(child1)) > 0) {
						swap(index, child1);

					}
				}
				// Have both children
				else {
					// If left child smaller
					if (comp.compare(heap.get(child1), heap.get(child2)) < 0) {
						winnerChild = child1;
					}
					else {
						winnerChild = child2;
					}
					// Compare parent to winnerChild
					if (comp.compare(heap.get(index), heap.get(winnerChild)) > 0) {
						swap(index, winnerChild);
						// Recursive call to bubbleDown
						bubbleDown(winnerChild);

					}
				}
			}
		}

	}

	// Swap places and update mapping
	public void swap(int higher, int lower) {
		// Save higher element locally
		E temp = heap.get(higher);
		// Switch places
		heap.set(higher, heap.get(lower));
		map.put(heap.get(lower), higher);
		
		heap.set(lower, temp);
		map.put(temp, lower);
	}
	
	

	// Adds element in right position of the queue
	public void add(E e) {
		heap.add(e);
		map.put(e, heap.size() - 1);

		bubbleUp(heap.size() - 1);
	}


	// Returns element of highest priority
	public E peek() {
		if (heap.size() > 0) {
			return heap.get(0);
		}
		return null;
	}


	// Returns element of highest priority and removes it
	public E poll() {

		if (heap.size() > 0) {
			E temp = heap.get(0);
			// Replace element with last element
			heap.set(0, heap.get(heap.size() - 1));
			map.put(heap.get(heap.size() - 1), 0);
			
			heap.remove(heap.size() - 1);
			map.remove(temp);
			
			bubbleDown(0);
			return temp;
		}
		// If no elements in heap
		return null;
	}


	public void remove(E e) {
		Integer index = map.get(e);
		if (index != null) {
			if (heap.size() > 1) {
				// Replace element with last element
				heap.set(index, heap.get(heap.size() - 1));
				// Update mapping from element to index
				map.put(heap.get(heap.size() - 1), index);
			}
			
			
			heap.remove(heap.size() - 1);
			map.remove(e);
			bubbleDown(index);
			bubbleUp(index);

		}
	}

	public List<E> getList() {
		return heap;
	}
	
	public Map<E, Integer> getMap() {
		return map;
	}
}








