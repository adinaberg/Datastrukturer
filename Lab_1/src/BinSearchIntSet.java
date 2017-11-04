import java.util.Arrays;

public class BinSearchIntSet implements IntSet {

	int[] set;
	int size;

	public BinSearchIntSet () {
		set = new int[5];	
		set[0] = -1;
		set[1] = 2;
		set[2] = 3;
		set[3] = 5;
		set[4] = 10;
		size = 5;
	}

	public void add(int element) {

		int min = 0;
		int max = size - 1;

		while (max - min > 0) {
			int middle = (max + min)/2;
			int candidate = set[middle];
			if (candidate == element) {
				//element already in set, break while
				break;
			}
			//element is in higher half
			else if (candidate < element) {
				min = middle + 1;
			}
			//element is in lower half
			else {
				max = middle;
			}   		

		}

		//catch special case
		if (max == size - 1) {
			//check last element in set
			if(set[max] == element) {
				//don't enter next if
				max++;
			}
			//add element larger than current largest
			else if (set[max] < element) {
				max++;
				min++;
			}
		}

		//found index of new array and last index of set does not contain element
		if (max - min == 0) {
			//if there is space left
			if (set.length - size > 0) {

				//shift elements 1 step
				for (int i = 0; i < size - max; i++) {
					set[size - i] = set[size - 1 - i];
				}
				set[max] = element;
				//update size of set
			}

			//increase set capacity
			else {
				int[] newSet = new int[2*set.length];
				//copy from old set (below new element)
				for (int i = 0; i < max; i++) {
					newSet[i] = set[i];
				}
				//add new element
				newSet[max] = element;
				//copy from old set (above new element)
				for (int i = 0; i < size - max; i++) {
					newSet[max + 1 + i] = set[max + i];
				}
				set = newSet;
			}
			//update size of set
			size++;

		}

	}


	public void remove(int element) {
		int min = 0;
		int max = size - 1;
		int middle;
		
		while (max - min > 0) {
			middle = (max + min)/2;
			int candidate = set[middle];
			if (candidate == element) {
				//element already in set, break while
				break;
			}
			//element is in higher half
			else if (candidate < element) {
				min = middle + 1;
			}
			//element is in lower half
			else {
				max = middle;
			}   		

		}
		
		
		if (!(max - min == 0)) {
			if (!(set[max] == element)) {
				
			}
		}
		
		
	}





	public boolean contains(int element) {
		int min = 0;
		int max = size - 1;
		while (max - min > 0) {
			int middle = (max + min)/2;

			int candidate = set[middle];
			if (candidate == element) {
				//element found
				return true;
			}
			//element is in higher half
			else if (candidate < element) {
				min = middle + 1;
			}
			//element is in lower half
			else {
				max = middle - 1;
			}		
		}
		//if element is last
		if (set[max] == element) {
			return true;

		}
		return false;	
	}


	public int[] getSet() {
		return set;
	}
	public int getSize() {
		return size;
	}


	public static void main(String args[]) {


		BinSearchIntSet test = new BinSearchIntSet();
		System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());


		//boolean t = test.contains(10);
		//System.out.println(t);

		test.add(1);
		System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());

		test.add(4);
		System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());

		test.add(11);
		System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());


		boolean t = test.contains(-1);
		System.out.println(t);

	}

}
