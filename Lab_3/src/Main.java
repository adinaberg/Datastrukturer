
public class Main {
	
	
	public static void main(String[] args) {
		
		APrioMap<String, Integer> apr = new APrioMap<>();
		apr.put("First", 5);
		apr.put("Second", 2);
		apr.put("Thrird", 9);
		apr.put("Fourth", 1);
		System.out.println(apr.getPrioList());
		System.out.println(apr.poll());
		System.out.println(apr.get("Fourth"));
		
	}

}






