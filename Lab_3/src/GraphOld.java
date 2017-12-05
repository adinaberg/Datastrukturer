import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphOld {
	// Mapping from node to List of neighbours to that node, represented by the name of the neighbour and the distance from the node to the neighbor
	// Change specific type APrioMap to PrioMap, debugging purposes
	private Map<String, APrioMap<String, Integer>> map;
	//private int nrOfNodes;
	private List<String> nodeList;
	
    public GraphOld() { 
    	map = new HashMap<>();
    	//nrOfNodes = 0;
    	nodeList = new ArrayList<>();
    }
    
    public void addVertice(String label) {
    	// Create new empty PrioMap of neighbours
    	// Change from specific type APrioMap to PrioMap; debugging
    	APrioMap<String, Integer> neighbours = new APrioMap<>();
    	map.put(label, neighbours);
    	//nrOfNodes++;
    	nodeList.add(label);
    	
    }
    // Adds node2 to list of neighbours of node1 and vice versa
    public void addEdge(String node1, String node2, int dist) {
    	// Add new neighbour to PrioMap for both nodes
    	map.get(node1).put(node2, dist);
    	map.get(node2).put(node1, dist);

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
    	int nrOfNodes = nodeList.size();
    	int[] d = new int[nrOfNodes];
    	String[] p = new String[nrOfNodes];
    	Boolean[] k = new Boolean[nrOfNodes];
    	// Don't need to initialize p and k; default to correct values
    	for (int i = 0; i < nrOfNodes; i++) {
    		d[i] = Integer.MAX_VALUE;
    	}
    	
    	
    	// indexOf is O(n)
    	int indexStartNode = nodeList.indexOf(start);
    	// Modular to get index of unchecked nodes
    	int indexCheckNode = (indexStartNode + 1) % nrOfNodes;
    	d[indexStartNode] = 0;
    	k[indexStartNode] = true;
    	// do not want to check start node, hence -1
    	for (int i = 0; i < nrOfNodes - 1; i++) {
    		// Only check if d[current] == infty
    		if (d[indexCheckNode] == Integer.MAX_VALUE) {
    			// Get closest neighbour of current node
    			Pair<String, Integer> bestNeighbour = map.get(nodeList.get(indexCheckNode)).peek();    			
    			
        		indexCheckNode = (indexCheckNode + 1) % nrOfNodes;
    		}	
    	}
    	
    	return null;
    }
    
    
    
    
    // REMOVE LATER, for debugging only
    // Returns a list of the neighbours of node with name "node", sorted by shortest distance
    public ArrayList<Pair<String, Integer>> getNeighbours(String node) {
    	return map.get(node).getPrioList();
    }
    
}

