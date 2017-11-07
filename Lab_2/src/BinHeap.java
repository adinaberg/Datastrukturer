
public class BinHeap<E> implements PrioQueue<E> {


	@Override
	public Iterator<E> iterator() {
		Iterator<E> it = new Iterator<E>() {

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < currentSize && arrayList[currentIndex] != null;
			}

			@Override
			public E next() {
				return arrayList[currentIndex++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		return it;
	}


	public void add(E e) {

	}
	public E peek() {

	}
	public E poll() {

	}
	public void remove(E e) {

	}
}
