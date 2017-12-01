import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BinPairHeap<K,V> implements PrioQueue<V> {

}/*
	ArrayList<Pair> heap;
	Comparator<? super V> comp;

	// Constructor
	public BinPairHeap(Comparator<? super Pair> comp) {
		heap = new ArrayList<Pair>();
		this.comp = comp;
	}

	// Iterator
	@Override
	public Iterator<Pair> iterator() {
		Iterator<Pair> it = new Iterator<Pair>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < heap.size() && heap.get(currentIndex) != null;
			}

			@Override
			public Pair next() {
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
			Pair temp;
			if (index != 0) {
				parent = (index - 1)/2;
				if (comp.compare(heap.get(index), heap.get(parent)) < 0) {
					// Save child locally
					temp = heap.get(index);
					// Switch places
					heap.set(index, heap.get(parent));
					heap.set(parent, temp);
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
			Pair temp;
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
						heap.set(child1, temp);
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
	public void add(Pair e) {
		heap.add(e);
		bubbleUp(heap.size() - 1);
	}


	// Returns element of highest priority
	public Pair peek() {
		if (heap.size() > 0) {
			return heap.get(0);
		}
		return null;
	}


	// Returns element of highest priority and removes it
	public Pair poll() {
		if (heap.size() > 0) {
			Pair temp = heap.get(0);
			// Replace element with last element
			
			heap.set(0, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);
			bubbleDown(0);
			return temp;
		}
		// If no elements in heap
		return null;
	}


	public void remove(Pair e) {
		for (int index = 0; index < heap.size(); index++) {
			if (e.equals(heap.get(index))) {
				// Replace element with last element
				heap.set(index, heap.get(heap.size() - 1));
				heap.remove(heap.size() - 1);
				bubbleDown(index);
				bubbleUp(index);
				// Break out of for loop
				break;
			}
		}
	}

	public ArrayList<Pair> getList() {
		return heap;
	}



}
*/






