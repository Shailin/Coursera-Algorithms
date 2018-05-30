import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Graph {
	private Map<Integer, Vertex> vertices;
	private Map<Integer, Vertex> reversedVertices;
	List<Integer> sccSizes;
	int components;
	Stack<Vertex> finishingTimes;
	public Graph(Map<Integer,Vertex> vertices, Map<Integer,Vertex> reversedVertices) {
		this.vertices = vertices;
		this.reversedVertices = reversedVertices;
		sccSizes = new ArrayList<Integer>();
		finishingTimes = new Stack<Vertex>();
	}
	
	public Graph(String filePath) throws IOException {
		// Both normal and reversed graphs will be constructed
		Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
		Map<Integer, Vertex> reversedVertices = new HashMap<Integer, Vertex>();
		
		// Open necessary objects to read file contents
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line = bufferedReader.readLine();
		
		while(line != null) {
			String[] edge = line.split(" ");
			int head = Integer.parseInt(edge[0]);
			int tail = Integer.parseInt(edge[1]);
			int headReversed = Integer.parseInt(edge[1]);
			int tailReversed = Integer.parseInt(edge[0]);
			if(!vertices.containsKey(head)) {
				Vertex v = new Vertex(head);
				vertices.put(head, v);
			}
			if(!reversedVertices.containsKey(headReversed)) {
				Vertex vR = new Vertex(headReversed);
				reversedVertices.put(headReversed, vR);
			}
			if(!vertices.containsKey(tail)) {
				Vertex v2 = new Vertex(tail);
				vertices.put(tail, v2);
			}
			if(!reversedVertices.containsKey(tailReversed)) {
				Vertex v2R = new Vertex(tailReversed);
				reversedVertices.put(tailReversed, v2R);
			}
			
			//  Add the tail to the list of adjacent nodes for the head (construct the digraph connections)
			vertices.get(head).addAdjacentNode(vertices.get(tail));
			reversedVertices.get(headReversed).addAdjacentNode(reversedVertices.get(tailReversed));
			
			line = bufferedReader.readLine(); // get the next line
		} 
		bufferedReader.close(); // clean up
		
		// Finally, set the graph
		this.vertices = vertices;
		this.reversedVertices = reversedVertices;
		sccSizes = new ArrayList<Integer>();
		finishingTimes = new Stack<Vertex>();
	}
	
	public Map<Integer, Vertex> getVertices() {
		return vertices;
	}
	
	public void setAllVisited(boolean visited) {
		for(Vertex n : this.getVertices().values()) {
			n.setVisited(false);
		}
	}
	
	public void setReversedGraph(Map<Integer, Vertex> nodesReversed) {
		this.reversedVertices = nodesReversed;
	}
	
	public Map<Integer, Vertex> getReversedVertices() {
		return this.reversedVertices;
	}
	
	public Stack<Vertex> depthFirstSearchLoop() {
		for(Vertex v : this.getVertices().values()) {
			if(!v.isVisited()) {
				depthFirstSearch(v);
			}
		}
		return this.finishingTimes;
	}
	
	public void depthFirstSearchLoop(Stack<Vertex> finishingTimes) {
		Vertex v;
		while(!finishingTimes.isEmpty()) {
			v = finishingTimes.pop();
			v = this.getVertices().get(v.getValue());
			if(!v.isVisited()) {
				components = 1;
				this.depthFirstSearch(v);
				sccSizes.add(components);
			}
		}	
	}
	
	public void depthFirstSearch(Vertex v) {
		v.setVisited(true);
		for(Vertex adjacentVertex : v.getAdjacentVertices()) {
			if(!adjacentVertex.isVisited()) {
				components++;
				this.depthFirstSearch(adjacentVertex);
			}
		}
		finishingTimes.push(v);
	}
	
	public List<Integer> stronglyConnectedComponents() {		
		Graph gReversed = new Graph(this.reversedVertices, this.vertices);
		Stack<Vertex> finishingTimes = gReversed.depthFirstSearchLoop();
		gReversed.setAllVisited(false);
		this.depthFirstSearchLoop(finishingTimes);
		Collections.sort(sccSizes);
		return sccSizes;
	}
}