import java.util.List;

public class Graph {
	
	
	
	public Graph() { 

	}

	public void addVertice(String label) { 

	}

	public void addEdge(String node1, String node2, int dist) {

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
