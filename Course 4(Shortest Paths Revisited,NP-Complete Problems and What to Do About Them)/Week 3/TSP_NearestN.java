import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class TSP_NearestN 
{
    static int n;
    static double dist[][];
    static Stack<Integer> stack;
    
    public static void tspNN()
    {
        stack=new Stack<>();
        int[] visited = new int[n+1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        double min,x_dist;
        double total=0.0;
        boolean minFlag = false;
        System.out.println(1);
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Double.MAX_VALUE;
            //System.out.println("Element"+element);
            while (i <= n)
            {
                x_dist=Math.sqrt(Math.pow((dist[i][0]-dist[element][0]),2)+Math.pow((dist[i][1]-dist[element][1]),2));
                if (x_dist > 1 && visited[i] == 0)
                {
                    if (min > x_dist)
                    {
                        min = x_dist;
                        dst = i;
                        minFlag = true;
                        //System.out.println( i+"=Min dist is:"+dst);
                    }
                    if((i+1)<n && (Math.abs(dist[i+1][0]-dist[element][0])>min))
                        break;
                }
                i++;
            }
            
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst+"\n");
                minFlag = false;
                total+=min;
                continue;
            }
            stack.pop();
        }
        x_dist=Math.sqrt(Math.pow((dist[1][0]-dist[dst][0]),2)+Math.pow((dist[1][1]-dist[dst][1]),2));
        System.out.println("The Shortest tour cost is:"+(total+x_dist));   //for every city in tour find the total of each tour
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File f=new File("E://nn.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line=br.readLine();
        n=Integer.parseInt(line);
        dist=new double[n+1][2];
        
        for (int i = 1; i <=n ; i++) {
            line=br.readLine();
            String p[]=line.split(" ");
            dist[i][0]=Double.parseDouble(p[1]);
            dist[i][1]=Double.parseDouble(p[2]);
        }
        tspNN();
   }
}