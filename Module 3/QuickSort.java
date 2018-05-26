import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class QuickSort {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int arr[],n;
        File f=new File("E://QuickSort.txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        LineNumberReader reader=new LineNumberReader(new FileReader(f));
        while ((reader.readLine()) != null);
        n=reader.getLineNumber();
        arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }
        int totcompar=quicksort(arr,0,n-1);
        System.out.println("Total Comparisions:"+ totcompar);
    }
    public static int quicksort(int arr[],int low,int high){
        int totalcomp=0;
        if(low<high){
            compute(arr,low,high,2);
            int p=partition(arr,low,high);
            totalcomp+=high-low;
            totalcomp+=quicksort(arr,low,p-1);
            totalcomp+=quicksort(arr,p+1, high);
        }
        return totalcomp;
    }
    
    public static int partition(int arr[],int low,int high){
        int pivot=arr[low];
        int i=low+1;
        for (int j=low+1;j<=high; j++)
        {
            if (arr[j] <= pivot)
            {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i-1];
        arr[i-1] = arr[low];  
        arr[low] = temp;
        return i-1;
    }
    
    public static void compute(int arr[],int low,int high,int op){
        switch(op){
            case 1:{        //for selecting the last ele as pivot
                int temp=arr[low];
                arr[low]=arr[high];
                arr[high]=temp;
            }break;
                
            case 2:{    // median of 3
                int mid= (high+low)/2;
                int x= arr[low]>arr[mid]?(arr[high]>arr[low]?low:(arr[mid]>arr[high]?mid:high)):(arr[high]>arr[mid]?mid:(arr[low]>arr[high]?low:high));
                int temp=arr[low];
                arr[low]=arr[x];
                arr[x]=temp;
            }
        }
    }
}