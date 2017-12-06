import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	public Map<String, ArrayList<Pair<String, Integer>>> map;
	
	public Graph() {
		map = new HashMap<>();
	}

	public void addVertice(String label) {
		
		map.put(label,new ArrayList<>());
		System.out.println(map);
	}

	public void addEdge(String node1, String node2, int dist) {
		if(!map.containsKey(node1) || !map.containsKey(node2)){
			System.out.println("Entered an invalid node!");
		}else{
			Pair<String, Integer> p1 = new Pair(node1,dist);	// Path to node1 with distance to be added to node2
			map.get(node2).add(p1);	// Add the path from node2 to node1
			System.out.println(map);
			
			Pair<String, Integer> p2 = new Pair(node2,dist);
			map.get(node1).add(p2);	// Add the path from node1 to node2
			System.out.println(map);
		}
	}

	public static class Path {
		public int totalDist;
		public List<String> vertices;
		public Path(int totalDist, List<String> vertices) {
			this.totalDist = totalDist;
			this.vertices = vertices;
		}

		@Override
		public String toString() {
			return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
		}
	}

	public Path shortestPath(String start, String dest) { 
		return null;
	}
}
