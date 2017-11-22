import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BinHeap<E> implements PrioQueue<E> {

	ArrayList<E> heap;
	Comparator<? super E> comp;

	// Constructor
	public BinHeap(Comparator<? super E> comp) {
		heap = new ArrayList<E>();
		this.comp = comp;
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
	public void add(E e) {
		heap.add(e);
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
			heap.remove(heap.size() - 1);
			bubbleDown(0);
			return temp;
		}
		// If no elements in heap
		return null;
	}


	public void remove(E e) {
		for (int index = 0; index < heap.size(); index++) {
			if (comp.compare(heap.get(index), e) == 0) {
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

	public ArrayList<E> getList() {
		return heap;
	}




	public static void main(String[] args) {
		Comparator<String> comp = new NaturalOrderComparator<String>();
		BinHeap<String> test = new BinHeap<String>(comp);

		test.add("Anders");
		test.add("Andrea");
		test.add("Bert");
		test.add("Ina");
		test.add("Olof");
		test.poll();
		test.remove("Bert");
		test.add("Ulrik");
		System.out.println(test.getList() + " ");




//		System.out.println(pq.getList());

		//System.out.println( " " + test.getList());

		/*
		test.add(5);
		test.add(-4);
		test.add(6);
		test.add(1);
		test.add(3);
		test.add(0);
		System.out.println(test.getList() + " ");

		//Integer i = test.poll();
		test.remove(5);
		System.out.println(test.getList() + " ");

		test.remove(0);
		System.out.println(test.getList() + " ");

		test.remove(5);
		System.out.println(test.getList() + " ");

		test.remove(-4);
		System.out.println(test.getList() + " ");


		test.remove(-4);
		System.out.println(test.getList() + " ");
		 */

	}


}







