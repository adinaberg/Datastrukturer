import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	public Map<String, ArrayList<Pair<String, Integer>>> map;
	public List<String> nodeList;
	
	public Graph() {
		map = new HashMap<>();
		nodeList = new ArrayList<>();
	}

	public void addVertice(String label) {
		
		map.put(label,new ArrayList<>());
		nodeList.add(label);
	}

	public void addEdge(String node1, String node2, int dist) {
		if(!map.containsKey(node1) || !map.containsKey(node2)){
			System.out.println("Entered an invalid node!");
		}else{
			Pair<String, Integer> p1 = new Pair<String, Integer>(node1,dist);	// Path to node1 with distance to be added to node2
			map.get(node2).add(p1);	// Add the path from node2 to node1
			
			Pair<String, Integer> p2 = new Pair<String, Integer>(node2,dist);
			map.get(node1).add(p2);	// Add the path from node1 to node2
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
		APrioMap<String, Integer> verticeCost = new APrioMap<>();
		Map<String, String> parent = new HashMap<>();
		Map<String, Integer> bestCost = new HashMap<>();
		
		// Set cost of all nodes to inf
		for (int i = 0; i < nodeList.size(); i ++) {
			verticeCost.put(nodeList.get(i), Integer.MAX_VALUE);
		}
		
		// Update cost of start node
		verticeCost.put(start, 0);
		// While destination node not polled yet (shortest path not found)
		while (verticeCost.get(dest) != null) {
			
			//System.out.println("Heap " +  verticeCost.getPrioList());
			
			Pair<String, Integer> best = verticeCost.poll();
			//System.out.println("best " + best);
			bestCost.put(best.a, best.b);
			//System.out.println("bestCost " + bestCost);

			// Polled verticeCost from non-connected node; no path to dest
			if (best.b == Integer.MAX_VALUE) {
				return null;
			}
			
			List<Pair<String, Integer>> neighbours = map.get(best.a);
			for (Pair<String, Integer> currentNeighbour : neighbours) {
				
				// If shortest path to neighbour not found
				//System.out.println("currentneighbour " + currentNeighbour);
				if (verticeCost.get(currentNeighbour.a) != null) {
					
					int newCost = best.b + currentNeighbour.b;
					int oldCost =  verticeCost.get(currentNeighbour.a);
					
					// Update with new lowest cost and change predecessor
					if (newCost < oldCost)  {
						//System.out.println("Updating verticeCost with entry " + currentNeighbour.a + " + newCost " + newCost);
						verticeCost.put(currentNeighbour.a, newCost);
						parent.put(currentNeighbour.a, best.a);
					}
					
					//System.out.println("Heap after stuff " +  verticeCost.getPrioList());

				}
			}			
		} // end while
		
		// Collect path to dest node in List
		List<String> parentList = new ArrayList<>();
		parentList.add(dest);
		String checkParent = parent.get(dest);
		while (checkParent != null) {
			parentList.add(checkParent);
			checkParent = parent.get(checkParent);
		}
		Collections.reverse(parentList);

		//System.out.println(bestCost);
		return new Path(bestCost.get(dest), parentList);
	}
	
	
}
