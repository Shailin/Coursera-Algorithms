import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class RandomMinCut {
	public static int minCut(HashMap<Integer, ArrayList<Integer>> adjList,ArrayList<Integer> vertices)
	{
		int label = adjList.size() + 1;
		while (vertices.size() > 2)
		{
			int noOfEdges = getNoOfEdges(adjList, vertices);
			int[][] edges = buildEdges(noOfEdges, adjList, vertices);
			Random rand = new Random();
			int no = rand.nextInt(edges.length);
			int vertex1 = edges[no][0];
			int vertex2 = edges[no][1];
			
			ArrayList<Integer> valuesV1 = adjList.get(vertex1);		
			ArrayList<Integer> valuesV2 = adjList.get(vertex2);
		
			for(int i = 0; i < valuesV2.size(); i++)
				valuesV1.add(valuesV2.get(i));
			vertices.remove(vertices.indexOf(vertex2));
			adjList.remove(vertex2);
			for(int i = 0; i < vertices.size(); i++)
			{
				ArrayList<Integer> values = adjList.get(vertices.get(i));
				for(int j = 0; j < values.size(); j++)
				{
					if(values.get(j) == vertex1)
						values.set(j, label);
					if(values.get(j) == vertex2)
						values.set(j, label);
				}
			}
			ArrayList<Integer> values = adjList.get(vertex1);
			adjList.remove(vertex1);
			adjList.put(label, values);
			
			int index = vertices.indexOf(vertex1);
			vertices.set(index, label);
			int position = 0;
			int length = adjList.get(label).size();
			while (position < length)
			{
				if(adjList.get(label).get(position) == label)
				{
					adjList.get(label).remove(position);
					length--;
				} 
				else		position++;
			} 
			label++;
		} 	
		return adjList.get(vertices.get(0)).size();
	} 
        
	public static void construct(HashMap<Integer, ArrayList<Integer>> adjList, 
			ArrayList<Integer> vertices) throws IOException
	{
		File file = new File("E://kargerMinCut.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		try{
		    String line = input.readLine();
		    while (line != null) 
		    {
		    	String[] vector = line.split("\t");
		    	int key = Integer.parseInt(vector[0]);
		    	vertices.add(key);
		    	ArrayList<Integer> edges = new ArrayList<Integer>();
		    	for(int i=0; i < vector.length - 1; i++)
		    		if(Integer.parseInt(vector[i+1]) != key)
		    			edges.add(Integer.parseInt(vector[i+1]));
		    	adjList.put(key, edges);
		    	line = input.readLine();
		    }
		}
		finally
		{	input.close();}
	}

	public static void main(String[] arg) throws IOException
	{
		HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		construct(adjList, vertices);
		int absoluteMinimum = adjList.size() * adjList.size();
		for(int i = 0; i <= (adjList.size() - 1)/2; i++)  //loop over n(n-1)/2 times,(n-1 also works)  
		{
			int minFound = minCut(adjList, vertices);
			System.out.println("Min cut on " + i + "th is: " + minFound);
			
			if(minFound < absoluteMinimum)
				absoluteMinimum = minFound;
			adjList.clear();
			vertices.clear();
			construct(adjList, vertices);
		} 
		System.out.println("\n MINIMAL CUT FOUND IS \n" + absoluteMinimum);
	} 

	public static int[][] buildEdges(int noOfEdges, HashMap<Integer, 
			ArrayList<Integer>> adjList, ArrayList<Integer> vertices)
	{
		int k = 0;
		int[][] edges = new int[noOfEdges][2];
		for(int i= 0; i < vertices.size(); i++)
		{
			ArrayList<Integer> vector = adjList.get(vertices.get(i));
			for(int j= 0; j< vector.size(); j++)
			{
				edges[k][0] = vertices.get(i);
				edges[k][1] = vector.get(j);
				k++;
			} 
		}
		return edges;
	} 
	
	public static int getNoOfEdges(HashMap<Integer, ArrayList<Integer>> adjList, ArrayList<Integer> vertices)
	{
		int noOfEdges = 0;
		for(int i= 0; i < vertices.size(); i++)
		{
			ArrayList<Integer> vector = adjList.get(vertices.get(i));
			noOfEdges += vector.size();
		} 
		return noOfEdges;
	} 
}