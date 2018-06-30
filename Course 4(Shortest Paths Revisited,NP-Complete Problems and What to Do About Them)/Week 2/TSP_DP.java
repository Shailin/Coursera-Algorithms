import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TSP_DP {
    static int ncity;
    static double dist[][];
    static int VISITED_ALL;
    //static double dp[][];
    public static double tsp(int mask,int pos)
    {
        double mindist=Double.MAX_VALUE;
        if(mask==VISITED_ALL)
            return dist[pos][0];
        //if(dp[mask][pos]!=-1)
         //   return dp[mask][pos];
        for(int city=0;city< ncity; city++){
		if((mask &(1<<city))==0){
			double newAns = dist[pos][city] + tsp( mask|(1<<city), city);
			mindist = Math.min(mindist, newAns);
		}
	}
	return mindist;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        double initdist[][];
        File f=new File("E://tsp2.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String line=br.readLine();
        ncity=Integer.parseInt(line);
        initdist=new double[ncity][2];
        VISITED_ALL=(1<<ncity) -1;
        //dp=new double[VISITED_ALL+1][ncity];
        
        for (int i = 0; i < ncity ; i++) {
            line=br.readLine();
            String p[]=line.split(" ");
            initdist[i][0]=Double.parseDouble(p[0]);
            initdist[i][1]=Double.parseDouble(p[1]);
        }
        distmatrix(initdist); 
         
        //for(int i=0;i<(1<<ncity);i++){
          // for(int j=0;j<ncity;j++){
            //dp[i][j] = -1;
          //}
         //}
        
        System.out.println("Travelling Salesman Distance is:"+ tsp(1,0));
    }
    
    public static void distmatrix(double initdist[][])
    {
        dist=new double[ncity][ncity];
        for (int i = 0; i < ncity; i++) {
            for (int j = 0; j <ncity ; j++) {
                dist[i][j]=Math.sqrt(Math.pow((initdist[j][0]-initdist[i][0]),2)+
                        Math.pow((initdist[j][1]-initdist[i][1]),2));
            }
        }
        
        for (int i = 0; i < ncity; i++) {
            for (int j = 0; j <ncity; j++) {
                System.out.print(dist[i][j] +" ");
            }
            System.out.println("");
        }
    }
}