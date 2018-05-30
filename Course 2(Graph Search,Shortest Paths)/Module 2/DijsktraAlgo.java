import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Graph{
    int v;
    int weight;
}

public class DijsktraAlgo {
    public static void dijkstra(Graph g[][],int src)
    {
        int dist[],sptset[];
        dist=new int[201];
        sptset=new int[201];
        for (int i = 1; i < 201; i++) {
            dist[i]=1000000;
            sptset[i]=0;
        }
        dist[src]=0;    //dist of src vertex
        for (int i = 0; i < 200 - 1; i++) {
            int u=minDistance(dist,sptset);
            sptset[u]=1;        //processed vertex
            for (Graph p:g[u]) {
                if(sptset[p.v]==0 && (dist[u]+p.weight< dist[p.v]))
                    dist[p.v]=dist[u]+p.weight;
            }
        }
        
        int list[]={7,37,59,82,99,115,133,165,188,197};
        for (int u : list) {
            System.out.print(dist[u]+",");
        }
    }
   
   public static int minDistance(int dist[],int sptset[]){
       int min=1000000,minindex=1;
       for (int i = 1; i < 201; i++) {
           if(sptset[i]==0 && dist[i]<=min){
               minindex=i;
               min=dist[i];
           }
       }
       return minindex;
   } 
    
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Graph g[][];   
     g = new Graph[201][];
     File f=new File("E://dijkstraData.txt");
     BufferedReader br=new BufferedReader(new FileReader(f));
     String line=br.readLine();
       for (int i = 1; i < 201; i++) {
          String lines[]=line.split("\t");
          g[i]=new Graph[lines.length - 1];
          for (int j = 0; j < lines.length -1 ; j++) {
               String p[]=lines[j+1].split(",");
               g[i][j]=new Graph();
               g[i][j].v     =Integer.parseInt(p[0]);
               g[i][j].weight=Integer.parseInt(p[1]);
           }
          line=br.readLine();
       }
      dijkstra(g, 1);
   }
}
