import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MaxSpaceCluster{
	private List<Edge> edges;
	private int[] nodeIndexes;	
	public void readFile(String path) throws Exception{
		Scanner scanner = new Scanner(new File(path));
		int numNodes = scanner.nextInt();
		int left, right;
		double cost;
		
		edges = new ArrayList<Edge>();
		nodeIndexes = new int[numNodes];
		
		for(int i = 0; i < numNodes; i++)
			nodeIndexes[i] = i + 1;
		
		while(scanner.hasNext()) {
			left = scanner.nextInt();
			right = scanner.nextInt();
			cost = (double)scanner.nextInt();
			Edge edge = new Edge(cost, left, right);
			edges.add(edge);
		}
		scanner.close();
	}
	
	//k means number of clusters that you want
	//return the spacing for the given number of clusters k 
	public double cluster(int k) {
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				if(e1.getCost() > e2.getCost())
					return 1;
				else if(e2.getCost() > e1.getCost())
					return -1;
				else 
					return 0;
			}
		});
		UF uf = new UF();
		uf.init(nodeIndexes);
		int left, right;
		double spacing = -1;
		for(int i = 0; i < edges.size(); i++) {
			left = edges.get(i).getLeft();
			right = edges.get(i).getRight();
			if(uf.getCount() == k) {
				if(!uf.connected(left, right)) {
					spacing = edges.get(i).getCost();
					return spacing;
				}
			}
			if(!uf.connected(left, right)) {
				uf.union(left, right);
			}
		}
		
		//return 0 means something have been wrong!
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		MaxSpaceClustermsc = new MaxSpaceCluster();
		String path = "E:\\clustering1.txt";
		msc.readFile(path);
		double spacing = -1;
		int k = 4;
		spacing = msc.cluster(k);
		System.out.println("spacing is " + spacing);
	}
}