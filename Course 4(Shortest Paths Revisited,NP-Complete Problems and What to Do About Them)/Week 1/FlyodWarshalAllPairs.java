import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlyodWarshalAllPairs {
    static int n ;            // no. of vertices 
    
    public static void floydWarshall(int graph[][])
    {
        int dist[][] = new int[n][n];
        int i, j, k ,minuv=999999;
        for (i = 1; i < n; i++)
            for (j = 1; j <n; j++)
                dist[i][j] = graph[i][j];

        for (k = 1; k < n; k++)
            for (i = 1; i < n; i++)
                for (j = 1; j < n; j++)
                {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                    if(dist[i][j]<minuv)
                        minuv=dist[i][j];
                }
        
        // If distance of any verex from itself
        // becomes negative, then there is a negative
        // weight cycle.
        for (i = 1; i < n; i++)
            if (dist[i][i] < 0)
                System.out.println("Negative wgt cycle");
        
        System.out.println("The minimum edge is:"+ minuv);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int graph[][];    //  input graph
        
        File f=new File("E://g3.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line=br.readLine();
        String p[]=line.split(" ");
        n=Integer.parseInt(p[0])+1;
                
        graph=new int[n][n];
        for (int i = 1; i < n; i++)
            for (int j = 1; j < n; j++){
               if(i!=j) 
                graph[i][j]=999999;
            }
        
        line=br.readLine();
        while(line !=null){
                p=line.split(" ");
                graph[Integer.parseInt(p[0])][Integer.parseInt(p[1])]=Integer.parseInt(p[2]);
                line=br.readLine();
        }
        
        floydWarshall(graph);
    }       
  }   

//Graph G1 and Graph G2 has -ve wgt cycles
// Minimum Edge is Obtained in Graph G3 with the shortest of shortest path is:
//d(u,v)= -19 .