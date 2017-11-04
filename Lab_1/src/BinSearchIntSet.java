import java.util.Arrays;



public class BinSearchIntSet implements IntSet {
	
	int[] set;
	int size = 4;
	
	public BinSearchIntSet () {
		set = new int[5];	
		set[0] = -1;
		set[1] = -4;
		set[2] = 3;
		set[3] = 5;
		set[4] = 10;
	}
	
	
    public void add(int element) {
    	
    	
    	int min = 0;
    	int max = size - 1;
    	
    	while (max - min > 0) {
    		System.out.println("min: " + min + " max: " + max);
    		int middle = (max + min)/2;
    		int candidate = set[middle];
    		if (candidate == element) {
    			//element already in set, break while
    			System.out.println("Element already in set");
    			break;
    		}
    		//element is in higher half
    		else if (candidate < element) {
        		System.out.println("Updating min");
    			min = middle + 1;
    		}
    		//element is in lower half
    		else {
        		System.out.println("Updating max");
    			max = middle;
    		}   		

    	}
		System.out.println("After while  min: " + min + " max: " + max);
    	
    	//add element
    	if (max - min == 0) {
    		//if there is space left
    		if (set.length - size > 0) {
    			
    			//shift elements 1 step
    			for (int i = 0; i < size - max; i++) {
    				set[size - i] = set[size - 1 - i];
    			}
    			set[max] = element;
    		}
    	}
    	
    	System.out.println(Arrays.toString(set));
    }
    	
    
    
    public boolean contains(int element) {
    	int min = 0;
    	int max = size - 1;
    	while (max - min >= 0) {
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
    	System.out.println("set to false");
    	return false;	
    }
    public void remove(int element) {
    	
    }
    
    public int[] get() {
    	return set;
    }
    
	
	
	public static void main(String args[]) {
		
		
		BinSearchIntSet test = new BinSearchIntSet();
		System.out.println(Arrays.toString(test.get()));
		boolean t = test.contains(10);
			System.out.println(t);
		
		//test.add(3);
	
		

	}
	
}