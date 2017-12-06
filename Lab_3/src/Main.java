
public class Main {
	
	
	public static void main(String[] args) {
		
		/*APrioMap<String, Integer> apr = new APrioMap<>();
		apr.put("First", 5);
		apr.put("Second", 2);
		apr.put("Thrird", 9);
		apr.put("Fourth", 1);
		apr.put("Fifth", 2);
		System.out.println(apr.getPrioList());
		System.out.println(apr.poll());
		System.out.println(apr.get("Fourth"));*/
		
		Graph g = new Graph();
		g.addVertice("A");
		g.addVertice("B");
		g.addVertice("C");
		g.addVertice("D");
		g.addVertice("E");
		g.addVertice("F");
		g.addEdge("A", "B", 2);
		g.addEdge("B", "E", 3);
		g.addEdge("A", "D", 7);
		g.addEdge("D", "E", 1);
		g.addEdge("B", "C", 1);
	}

}






