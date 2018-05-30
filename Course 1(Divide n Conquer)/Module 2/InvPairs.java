import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class InvPairs {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int arr[],n,temp[];
        File f=new File("E://array.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        LineNumberReader reader=new LineNumberReader(new FileReader(f));
        while ((reader.readLine()) != null);
        n=reader.getLineNumber();
        arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }
        
        temp=new int[n];
        long count=merge_sort(arr,temp,0,n-1);        // an overflow with int
        System.out.println("Inversion pairs:"+count);
        
  }
    public static long merge_sort(int arr[],int temp[],int i,int j){
        int mid;
        long inv_count=0;
        if(j>i){
           mid=(i+j)/2;
           inv_count=merge_sort(arr,temp,i,mid);
           inv_count+=merge_sort(arr,temp,mid+1,j);
           
           inv_count+=merge(arr,temp,i,mid+1,j);
        }
        return inv_count;
    }
    
    public static long merge(int arr[],int temp[],int left,int mid,int right){
        long inv_count=0;
        int i,j,k;
        i=left;
        k=left;
        j=mid;
        
        while((i < mid)&&(j <= right)){
            if(arr[i]<=arr[j])
                temp[k++]=arr[i++];
            else{
                temp[k++]=arr[j++];
                inv_count+=(mid-i);
            }
        }
        while (i <= mid - 1)
          temp[k++] = arr[i++];
        while (j <= right)
           temp[k++] = arr[j++];

        for (i=left; i <= right; i++)
           arr[i] = temp[i];
        return inv_count;
    }
}
