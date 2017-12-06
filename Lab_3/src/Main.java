
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
		g.addVertice("V0");
		g.addVertice("V1");
		g.addVertice("V2");
		g.addVertice("V3");
		g.addVertice("V4");
		g.addEdge("V4", "V2", 2);
		g.addEdge("V0", "V4", 1);
		g.addEdge("V3", "V0", 2);
		g.addEdge("V3", "V1", 0);
		g.addEdge("V4", "V1", 0);
		g.addEdge("V4", "V3", 4);
		g.addEdge("V0", "V1", 1);
		
		//System.out.println(g.shortestPath("V3", "V0"));
		//System.out.println(g.shortestPath("V4", "V1"));
		System.out.println(g.shortestPath("V4", "V0"));

	}

}






