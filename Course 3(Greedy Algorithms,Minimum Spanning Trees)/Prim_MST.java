import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Prim_MST {
	private Map<Integer,List<Node>> graph;
	private Queue<Node> queue;
	private int MAX = 1000000;
	/*cost of building MST*/
	private static int cost = 0;
	public void buildGraph() throws Exception{
		String path = "E:\\edges.txt";
		File f = new File(path);
		Scanner sc = new Scanner(f);
		String regex = "\\s";
		String line;
		String[] strArr;
		int label1, label2;
		graph = new HashMap<Integer,List<Node>>();
		strArr = sc.nextLine().split(regex);
		int numNodes = Integer.valueOf(strArr[0]);
		for(int i = 0; i < numNodes; i++) {
			List<Node> tempList = new ArrayList<Node>();
			graph.put(i+1, tempList);
		}
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			strArr = line.split(regex);
			label1 = Integer.valueOf(strArr[0]);
			label2 = Integer.valueOf(strArr[1]);
			int n1 = Integer.valueOf(strArr[1]), d1 = Integer.valueOf(strArr[2]);
			int n2 = Integer.valueOf(strArr[0]), d2 = d1;
			Node node1 = new Node(n1, d1), node2 = new Node(n2, d2);
			graph.get(label1).add(node1);
			graph.get(label2).add(node2);
		}
		sc.close();
	}
	
	public void buildQueue() {
		queue = new PriorityQueue<Node>(10, new Comparator<Node>(){
			@Override
			public int compare(Node n1, Node n2) {
				if(n1.getDist() > n2.getDist()) return 1;
				else if(n1.getDist() < n2.getDist()) return -1;
				else return 0;
			}
		});
		for(int x : graph.keySet()) {
			Node node;
			if(x == 1) node = new Node(x,0);
			else node = new Node(x,MAX);
			queue.add(node);
		}
	}

              public void prim_MST() throws Exception{
		buildGraph();
//		for(int i = 1; i < 10; i++)
//			System.out.println(graph.get(1).toString());
		buildQueue();
		Node node;
		while(!queue.isEmpty()) {
			double minDist = MAX;
			node = queue.peek();
			for(Node nod : queue) {
				if(nod.getDist() < minDist) {
					minDist = nod.getDist();
					node = nod;
				}
			}
			queue.remove(node);
			Set<Integer> set = new HashSet<Integer>();
			for(Node nd : graph.get(node.getLabel())) {
				set.add(nd.getLabel());
			}
			for(Node nd : queue) {
				if(set.contains(nd.getLabel())) {
					double dist = 0;		
					for(Node n : graph.get(node.getLabel())) {
						if(n.getLabel() == nd.getLabel())
							dist = n.getDist();
					}
					if(nd.getDist() > dist)
						nd.setDist(dist);
				}
			}
			cost += node.getDist();
		}
	}

	public static void main(String[] args) throws Exception {
		Prim_MST mst = new Prim_MST();
		mst.prim_MST();
		System.out.println("cost = " + Prim_MST.cost);
	}
}