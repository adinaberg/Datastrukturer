import java.util.Arrays;

public class BinSearchIntSet implements IntSet {

	int[] set;
	int size;

	public BinSearchIntSet () {
		set = new int[1];
		size = 0;
	}

	public void add(int element) {
		int min = 0;
		int max = size - 1;
		if (size == 0) max = 0;

		
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
		if (size > 0) {
			int min = 0;
			int max = size - 1;

			int middle = (max + min)/2;


			while (max - min > 0) {
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
				middle = (max + min)/2;
			}

			//catch special case when element is in last place
			if (max == size - 1 && set[max] == element) {
				//to make sure we enter next if
				max++;
			}

			//if element found, remove
			if(!(max - min == 0)) {
				for (int i = 0; i < size - middle - 1; i++) {
					set[middle + i] = set[middle + i + 1];
				}
				//shrink size
				size--;
			}

		}


	}





	public boolean contains(int element) {
		if (size > 0) {
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
					max = middle;
				}		
			}
			//if element is last
			if (max == size - 1 && set[max] == element) {
				return true;

			}
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


        BinSearchIntSet set2 = new BinSearchIntSet();
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.add(1);
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.add(2);
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.add(1);
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.remove(3);
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.remove(1);
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());

        System.out.println(set2.contains(1));
        System.out.println(set2.contains(2));
        System.out.println(set2.contains(3));

		//test.remove(4);
		//System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());

		//test.remove(-1);
		//System.out.println("Set: " + Arrays.toString(test.getSet()) + " Size: " + test.getSize());


		//boolean t = test.contains(-2);

	}

}
