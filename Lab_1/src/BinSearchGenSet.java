import java.util.ArrayList;

//implements Genset and requires E to extend Comparable
public class BinSearchGenSet<E extends Comparable<? super E>> implements GenSet<E> {

	ArrayList<E> set;

	//constructor
	public BinSearchGenSet() {
		set = new ArrayList<E>();
	}
	

	public int[] binSearch(E element) {
		//vals contains return values
		//vals[0] = -1 if element is not in array. Then vals[1] dictates index to put element in
		//vals[0] = 1 if element is in array. vals[1] then tells index at which it was found (not necessary for this lab but still potentially useful) 
		int[] vals = new int[2];
		//if set is empty
		if (set.size() == 0) {
			vals[0] = -1;
			vals[1] = 0;
			return vals;
		}
		int min = 0;
		int max = set.size() - 1;
		while (max - min > 0) {
			int middle = (max + min)/2;
			E candidate = set.get(middle);
			//element is found
			if (candidate.compareTo(element) == 0) {
				//found element
				vals[0] = 1;
				//in this place
				vals[1] = middle;
				//return values and break out of method
				return vals;
			}
		
			//continue search in higher half
			else if (candidate.compareTo(element) < 0) {
				min = middle + 1;
			}
			//continue search in lower half
			else {
				max = middle;
			}
		} //end while
		
		//when out of while, check last element
		if (set.get(set.size() - 1) == element) {
			vals[0] = 1;
			vals[1] = set.size() - 1;
			return vals;
		}
		else if (set.get(set.size() - 1).compareTo(element) < 0) {
			vals[0] = -1;
			//new element should be placed at the end
			vals[1] = set.size();
			return vals;
		}
		
		//entire set checked, element not found
		vals[0] = -1;
		//max = min place to put element when while loop has breaked
		vals[1] = max;
		return vals;

	} //end binSearch
	
	

	public void add(E element) {
		int[] vals = binSearch(element);
		//position to put new element in
		int pos = vals[1];
		//vals[0] = 1 means element already in set; in that case do nothing
		if (vals[0] < 0) {
			set.add(pos,element);
		}
	}


	public boolean contains(E element) {
		int[] vals = binSearch(element);
		if (vals[0] > 0) {
			return true;
		}
		else 
			return false;
	}


	public void remove(E element) {
		set.remove(element);

	}
	
	public ArrayList<E> getSet() {
		return set;
	}


	public int getSize() {
		return set.size();
	}

	public static void main(String args[]) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		//array.add(3);
		//array.add(5);
		//array.remove(1);
		int s = array.size();
		//System.out.println(array);
		//System.out.println(s);

		
		BinSearchGenSet<Integer> newSet = new BinSearchGenSet<Integer>();
		newSet.add(1);
		System.out.println(newSet.getSet());
		newSet.add(5);
		System.out.println(newSet.getSet());
		newSet.add(1);
		System.out.println(newSet.getSet());
		newSet.add(2);		
		System.out.println(newSet.getSet());
		
		System.out.println(newSet.contains(3));
		System.out.println(newSet.contains(5));
		System.out.println(newSet.contains(-2));
		
		
        BinSearchGenSet<String> kurser = new BinSearchGenSet<>();
        kurser.add("DAT03767");
        kurser.add("DAT037");
        System.out.println(kurser.contains("DAT037"));
        System.out.println(kurser.getSet());
		

	}

}
