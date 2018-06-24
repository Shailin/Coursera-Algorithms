import java.io.File;
import java.util.Scanner;

public class JobSchedule {
	
	private int[] weights;
	private int[] lengths;
	private double[] ratios;
	private int size;
	public void readFile(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
			size = sc.nextInt();
			weights = new int[size];
			lengths = new int[size];
			ratios = new double[size];

			for (int index = 0; index < size; index++) {
				weights[index] = sc.nextInt();
				lengths[index] = sc.nextInt();
				ratios[index] = (double) weights[index] / lengths[index];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	
	public void helpFunction(int l, int r) {
		if (r <= l)
			return;
		double pivot = ratios[l];
		int i = l + 1, j = l + 1;
		double temp;
		for (int k = l + 1; k <= r; k++) {
			if (ratios[k] < pivot)
				j++;
			else {
				temp = ratios[i];
				ratios[i] = ratios[j];
				ratios[j] = temp;
				temp = weights[i];
				weights[i] = weights[j];
				weights[j] = (int) temp;
				i++;
				j++;
			}
		}
		
		temp = ratios[l];
		ratios[l] = ratios[i - 1];
		ratios[i - 1] = temp;
		temp = weights[l];
		weights[l] = weights[i - 1];
		weights[i - 1] = (int) temp;

		helpFunction(l, i - 2);
		helpFunction(i, r);
	}
	public double solveJobSchedule() {
		double cost = 0;
		int curCompletion = 0;		
		
		helpFunction(0, size - 1);
		
		for (int i = 0; i < size; i++) {
			curCompletion += (weights[i] / ratios[i]); 
			cost += weights[i] * curCompletion;
		}
		
		return cost;
	}

	public static void main(String[] args) throws Exception {
		JobSchedule js = new JobSchedule();
		String path = "E:\\jobs.txt";
		js.readFile(path);
		double cost = js.solveJobSchedule();
		System.out.println("Cost is： " + cost);
	}
}