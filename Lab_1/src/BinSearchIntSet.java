import java.util.Arrays;


public class BinSearchIntSet implements IntSet{
	
	int[] set;
	int size;
	
	//constructor
	public BinSearchIntSet() {
		set = new int[1];
		size = 0;
	}
	
	private int[] binSearch(int element) {
		//vals contains return values
		//vals[0] = -1 if element is not in array. Then vals[1] dictates index to put element in
		//vals[0] = 1 if element is in array. vals[1] then tells index at which it was found (not necessary for this lab but still potentially useful) 
		int[] vals = new int[2];
		//if set is empty
		if (size == 0) {
			vals[0] = -1;
			vals[1] = 0;
			return vals;
		}
		int min = 0;
		int max = size - 1;
		while (max - min > 0) {
			int middle = (max + min)/2;
			int candidate = set[middle];
			//element is found
			if (candidate == element) {
				//found element
				vals[0] = 1;
				//in this place
				vals[1] = middle;
				//return values and break out of method
				return vals;
			}
		
			//continue search in higher half
			else if (candidate < element) {
				min = middle + 1;
			}
			//continue search in lower half
			else {
				max = middle;
			}
		} //end while
		
		//when out of while, check last element
		if (set[size - 1] == element) {
			vals[0] = 1;
			vals[1] = size - 1;
			return vals;
		}
		else if (set[size - 1] < element) {
			vals[0] = -1;
			//new element should be placed at the end
			vals[1] = size;
			return vals;
		}
		
		//entire set checked, element not found
		vals[0] = -1;
		//max = min place to put element when while loop has breaked
		vals[1] = max;
		return vals;

	} //end binSearch
	

	
	public void add(int element) {
		int[] vals = binSearch(element);
		//position to put new element in
		int pos = vals[1];
		//vals[0] = 1 means element already in set; in that case do nothing
		if (vals[0] < 0) {
			//if there is space left
			if (set.length - size > 0) {
				//shift elements 1 step
				for (int i = 0; i < size - pos; i++) {
					set[size - i] = set[size - 1 - i];
				}
				set[pos] = element;
			}
			//increase set capacity
			else {
				int[] newSet = new int[2*set.length];
				//copy from old set (below new element)
				for (int i = 0; i < pos; i++) {
					newSet[i] = set[i];
				}
				//add new element
				newSet[pos] = element;
				//copy from old set (above new element)
				for (int i = 0; i < size - pos; i++) {
					newSet[pos + 1 + i] = set[pos + i];
				}
				set = newSet;
			}
			//update size of set
			size++;
		}
		
	}
	
	public void remove(int element) {
		int[] vals = binSearch(element);
		//position of element to remove
		int pos = vals[1];
		//only remove if element is in set
		if (vals[0] > 0) {
			for (int i = 0; i < size - pos - 1; i++) {
				set[pos + i] = set[pos + i + 1];
			}
			//shrink size
			size--;
		}
	}
	
	public boolean contains(int element) {
		int[] vals = binSearch(element);
		if (vals[0] > 0) {
			return true;
		}
		else 
			return false;
	}
	
	public int[] getSet() {
		return set;
	}
	public int getSize() {
		return size;
	}
	
	
	public static void main(String[] args) {
		BinSearchIntSet set2 = new BinSearchIntSet();
		System.out.println("Set: " + Arrays.toString(set2.getSet()) + " Size: " + set2.getSize());
        set2.add(1);
        set2.add(2);
        set2.add(1);
        set2.remove(3);
        set2.remove(1);
        System.out.println(set2.contains(1));
        System.out.println(set2.contains(2));
        System.out.println(set2.contains(3));
		
		
	}
}
