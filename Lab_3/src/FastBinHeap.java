
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FastBinHeap<E> implements PrioQueue<E> {

	ArrayList<E> heap;
	Comparator<? super E> comp;
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
			E temp;
			if (index != 0) {
				parent = (index - 1)/2;
				if (comp.compare(heap.get(index), heap.get(parent)) < 0) {
					// Save child locally
					temp = heap.get(index);
					// Switch places
					heap.set(index, heap.get(parent));
					// Update mapping from element to index
					map.put(heap.get(parent), index);
					
					heap.set(parent, temp);
					map.put(temp, parent);
					
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
			E temp;
			// If not at bottom
			if (child1  < heap.size()) {
				// Have only left child
				if (child2 > heap.size() - 1) {
					// If parent larger than child
					if (comp.compare(heap.get(index), heap.get(child1)) > 0) {
						// Save parent locally
						temp = heap.get(index);
						// Switch places
						heap.set(index, heap.get(child1));
						map.put(heap.get(child1), index);

						heap.set(child1, temp);
						map.put(temp, child1);

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
						// Save parent locally
						temp = heap.get(index);
						// Switch places
						heap.set(index, heap.get(winnerChild));
						heap.set(winnerChild, temp);
						// Recursive call to bubbleDown
						bubbleDown(winnerChild);

					}
				}
			}
		}

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

	public ArrayList<E> getList() {
		return heap;
	}



}








