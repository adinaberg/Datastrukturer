import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	// Map from name of vertice to list of neighbours connected to that vertice.
	// Neighbours represented by Pair<String, Integer> with name and distance
	public Map<String, ArrayList<Pair<String, Integer>>> map;
	// List of all nodes in Graph; to enable looping through all nodes
	public List<String> nodeList;
	
	// Constructor
	public Graph() {
		map = new HashMap<>();
		nodeList = new ArrayList<>();
	}

	// Adds vertice to graph
	public void addVertice(String label) {
		map.put(label,new ArrayList<>());
		nodeList.add(label);
	}

	//Adds weighted edge between two nodes
	public void addEdge(String node1, String node2, int dist) {
		if(!map.containsKey(node1) || !map.containsKey(node2)){
			System.out.println("Entered an invalid node!");
		} else {
			Pair<String, Integer> p1 = new Pair<String, Integer>(node1,dist);	// Path to node1 with distance to be added to node2
			map.get(node2).add(p1);	// Add the path from node2 to node1
			
			Pair<String, Integer> p2 = new Pair<String, Integer>(node2,dist);
			map.get(node1).add(p2);	// Add the path from node1 to node2
		}
	}

	// Class representing a path to a node in a graph
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

	// Returns the shortest path from some node start to some node dest using Dijkstra's algorithm
	public Path shortestPath(String start, String dest) {
		// Currently known shortest distances to all nodes in graph
		APrioMap<String, Integer> verticeCost = new APrioMap<>();
		// Mapping from every node to its currently best predecessor
		Map<String, String> parent = new HashMap<>();
		// Final shortest distances to all nodes in graph
		Map<String, Integer> bestCost = new HashMap<>();
		
		// Initialize all currently known shortest distances to infinity
		for (int i = 0; i < nodeList.size(); i ++) {
			verticeCost.put(nodeList.get(i), Integer.MAX_VALUE);
		}
		
		// Update cost of start node
		verticeCost.put(start, 0);
		// While destination node not polled yet (shortest path to dest not yet found)
		while (verticeCost.get(dest) != null) {
			
			Pair<String, Integer> best = verticeCost.poll();
			// Polled verticeCost from non-connected node; no path to dest
			if (best.b == Integer.MAX_VALUE) {
				return null;
			}
			// Distance to node "best.a" is guaranteed to be optimal
			bestCost.put(best.a, best.b);
			// All neighbours to current node
			List<Pair<String, Integer>> neighbours = map.get(best.a);
			for (Pair<String, Integer> currentNeighbour : neighbours) {
				
				// If shortest path to neighbour not found
				if (verticeCost.get(currentNeighbour.a) != null) {
					// Distance to currentNeighbour if path via this node
					int newCost = best.b + currentNeighbour.b;
					// Previous shortest known distance to currentNeighbour
					int oldCost =  verticeCost.get(currentNeighbour.a);
					
					if (newCost < oldCost)  {
						// Update with new lowest cost and change predecessor
						verticeCost.put(currentNeighbour.a, newCost);
						parent.put(currentNeighbour.a, best.a);
					}
				}
			}			
		} // End while
		
		// Collect path to destination node in a List
		List<String> parentList = new ArrayList<>();
		parentList.add(dest);
		String checkParent = parent.get(dest);
		while (checkParent != null) {
			parentList.add(checkParent);
			checkParent = parent.get(checkParent);
		}
		Collections.reverse(parentList);
		return new Path(bestCost.get(dest), parentList);
	}
}
