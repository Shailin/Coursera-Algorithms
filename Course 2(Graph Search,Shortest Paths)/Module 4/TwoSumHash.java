import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TwoSumHash {
  public static void main(String[] args) throws FileNotFoundException, IOException {
      long i,y;int count=0;
      File f=new File("E://2sum.txt");
      BufferedReader br=new BufferedReader(new FileReader(f));
      HashMap<Long,Integer> hm=new HashMap<>();
      String line=br.readLine();
      while(line!=null){
         i=Long.parseLong(line);
         hm.put(i,0);
         line=br.readLine();
      }
      System.out.println(hm.size());
      
      for (int j =0; j <=10000; j++) {
            for (Long x:hm.keySet()) {
              y=j-x;
              if(x==y)
                  continue;
              if(hm.containsKey(y)){
                  count++;
                  break;
              }
            }
      }
      System.out.println("The no. of 2 sum is:"+count);
  } 
}