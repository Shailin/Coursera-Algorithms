import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MedianMaintainance {
    static MaxHeap maxh=new MaxHeap();
    static MinHeap minh=new MinHeap();
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
      int i,median,avgmedian=0;
      File f=new File("E://Median.txt");
      BufferedReader br=new BufferedReader(new FileReader(f));
      String line=br.readLine();
      while(line!=null){
         i=Integer.parseInt(line);
         median=findMedian(i);
         avgmedian+=median;
         line=br.readLine();
      }
        System.out.println("Sum of the Median:"+avgmedian%10000);
    }
    
    public static int findMedian(int i){
        if(i < minh.getMin()) {
			maxh.insert(i);
		} else{
			minh.insert(i);
		}
		if(maxh.getSize() - minh.getSize() > 1){
			minh.insert(maxh.extractMax());
		}
		if(minh.getSize() > maxh.getSize()){
			maxh.insert(minh.extractMin());
		}
		return maxh.getMax();        // when k is even(k/2)th smallest
                                             // is the max of the all small values which we store in max
    }
}

class MaxHeap{
	private ArrayList<Integer> A = new ArrayList<Integer>();
	private int heapsize = 0; // actually, heapsize = A.size()-1 all the time in this class

	public MaxHeap(){
		A.add(0); // so the index start at 1
	}
	public int getSize(){return A.size() - 1;}
	private int Parent(int i){return i/2;}
	private int Left(int i){return 2*i;}
	private int Right(int i){return 2*i + 1;}
	
	public void maxHeapify(int i){
		int l = Left(i);
		int r = Right(i);
		int largest;
		if (l <= heapsize && A.get(l) > A.get(i)) {
			largest = l;
		} else {
			largest = i;
		}
		if(r <= heapsize && A.get(r) > A.get(largest)){
			largest = r;
		}
		if(largest != i){
			swap(i, largest);
			maxHeapify(largest);
		}
	}
	
	public int getMax(){
		if(heapsize < 1){
			return Integer.MIN_VALUE; // heap underflow
		}
		return A.get(1);
	}
	
	public int extractMax(){
		if(heapsize < 1){
			System.out.println("heap underflow");
		}
		int max = A.get(1);
		A.set(1, A.get(heapsize)); 
		A.remove(heapsize); 
		heapsize--;
		maxHeapify(1);
		return max;
	}
	
	public void heapIncreaseKey(int i, int key){
		if(key < A.get(i)){
			System.out.println("new key is smaller than current key;");
		}
		A.set(i, key);
		while(i > 1 && A.get(Parent(i)) < A.get(i)){
			swap(i, Parent(i));
			i = Parent(i);
		}
	}
	
	/** maxHeapInsert */
	public void insert(int key){
		heapsize++;
		A.add(Integer.MIN_VALUE);
		heapIncreaseKey(heapsize, key);
	}
	
	private void swap(int i, int j){
		int tmp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, tmp);
	}
}

class MinHeap {
	private ArrayList<Integer> A = new ArrayList<Integer>();
	private int heapsize = 0;  // actually, heapsize = A.size()-1 all the time in this class
	
	public MinHeap(){
		A.add(0); // so the index start at 1
	}
	
	public int getSize(){
		return A.size() - 1;
	}
	private int Parent(int i){return i/2;}
	private int Left(int i){return 2*i;}
	private int Right(int i){return 2*i + 1;}
	public void minHeapify(int i){
		int l = Left(i);
		int r = Right(i);
		int smallest;
		if (l <= heapsize && A.get(l) < A.get(i)) {
			smallest = l;
		} else {
			smallest = i;
		}
		if(r <= heapsize && A.get(r) < A.get(smallest)){
			smallest = r;
		}
		if(smallest != i){
			swap(i, smallest);
			minHeapify(smallest);
		}
	}
	
	public void buildMinHeap(){
		heapsize = A.size() - 1;
		for(int i = (A.size() - 1)/2; i > 0; i--){
			minHeapify(i);
		}
	}
	
	public int getMin(){
		if(heapsize < 1){
			return Integer.MAX_VALUE;
		}
		return A.get(1);
	}
	
	public int extractMin(){
		if(heapsize < 1){
			System.out.println("heap underflow");
		}
		int min = A.get(1);
		A.set(1, A.get(heapsize));
		A.remove(heapsize);
		heapsize--;
		minHeapify(1);
		return min;
	}
	
	public void heapDecreaseKey(int i, int key){
		if(key > A.get(i)){
			System.out.println("new key is larger than current key;");
		}
		A.set(i, key);
		while(i > 1 && A.get(Parent(i)) > A.get(i)){
			swap(i, Parent(i));
			i = Parent(i);
		}
	}
	
	public void insert(int key){
		heapsize++;
		A.add(Integer.MAX_VALUE);
		heapDecreaseKey(heapsize, key);
	}

	private void swap(int i, int j){
		int tmp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, tmp);
	}
        
   }