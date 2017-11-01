import java.util.Arrays;
import java.lang.Math;

public class BinSearchIntSet implements IntSet {
	//instance variables
	private int[] set;
	
	//constructor
	public BinSearchIntSet() {
		//default is length 1 and 0 as element
		set = new int[1];
		
	}
	//add element to set
	public void add(int element) {
		//if set does not already contain element
		if (!contains(element)) {
			System.out.println("Adding element " + element);
			//increase array size by 1
			int[] newSet = new int[set.length + 1];
			//check each element in set and copy old set
			for (int i = 0; i < set.length; i++) {
				System.out.println(i);
				if (set[i] < element) {
					if (i == set.length) {
						newSet[i+1] = element;
					}
					newSet[i] = set[i];
				}
				else if (set[i] > element) {
					newSet[i] = element;
					newSet[i + 1] = set[i];
				}
			}
			this.set = newSet;
		}
	}
	
	//binary search to find if array set contains element
	public boolean contains(int element){
		//last index of set is set.length-1
		int index = binarySearch(element, 0, set.length-1);
		if(index > -1) {
			return true;
		}
		return false;
	}
	
	
	//binary search which returns index of element in set
	//if element is not in array or array has length 0, return -1
	public int binarySearch(int element, int start, int end) {
		
		//if array has length 0 or something else is wrong with input
		if (start > end) {
			return -1;
		}
		
		int m = (int) Math.floor((start + end)/2);
		
		//index of element is found
		if (set[m] == element) {
			return m;
		}
		else if (set[m] > element ) {
			//redo search from start to middle
			return binarySearch(element, start, m - 1);
		}
		else if (set[m] < element) {
			//redo search from middle to end
			return binarySearch(element, m + 1, end);
		}
		else 
			//element not found in array
			return -1;
	}
	
	
	//remove element from set
	public void remove(int element) {
	}
	
	//get current set (for debugging only)
	public int[] get() {
		return set;
	}
	
	public static void main(String[] args) {
		BinSearchIntSet mySet = new BinSearchIntSet();
		mySet.add(5);
		mySet.add(76);
		mySet.add(4);
		
		
		
		int test = 4;
		boolean cont = mySet.contains(test); 
		
		System.out.println("Current set: " + Arrays.toString(mySet.get()));
		
		System.out.println("Testing for " + test  +" gave ans " + cont);

		
		int[] someSet = new int[1];
		System.out.println(someSet[0] > 1);
		System.out.println(someSet[0] < 1);
		
		
		
		
	}
	
	
}
