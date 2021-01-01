import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// SUBMITTED
public class DS1{ // manipulating suffix arrays http://www.usaco.org/index.php?page=viewproblem2&cpid=762

	static int n;
	static int[] arr;
	static int[] smin;
	static int[] ssum; // suffix this time
	static int[] sdif;
	static double[] saverages; // sic
	
	static boolean equals(double a, double b) {
		double epsilon = 0.0000001;
		if (b - a < epsilon && a - b < epsilon) return true;
		return false;
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("homework.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("homework.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		smin = new int[n];
		ssum = new int[n];
		sdif = new int[n];
		
		st = new StringTokenizer(r.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		smin[n - 1] = arr[n - 1];
		ssum[n - 1] = arr[n - 1];
		sdif[n - 1] = ssum[n - 1] - smin[n - 1];
		
		for (int i = n - 2; i >= 0; i--) {
			smin[i] = Math.min(smin[i + 1], arr[i]);
			ssum[i] = ssum[i + 1] + arr[i];
			sdif[i] = ssum[i] - smin[i];
		}
		
	//	for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
	//	System.out.println();
	//	for (int i = 0; i < n; i++) System.out.print(ssum[i] + " ");
	//	System.out.println();
	//	for (int i = 0; i < n; i++) System.out.print(smin[i] + " ");
	//	System.out.println();
	//	for (int i = 0; i < n; i++) System.out.print(sdif[i] + " ");
	//	System.out.println();
		
		double min = Double.MIN_VALUE;
		
		saverages = new double[n];
		saverages[0] = saverages[n - 1] = Double.MAX_VALUE;
		
		for (int k = 1; k < n - 1; k++) {
			saverages[k] = (double)sdif[k] / (n - k - 1);
			min = Math.max(saverages[k], min);
		}
		
	//	for (int i = 0; i < n; i++) System.out.print(saverages[i] + " ");
	//	System.out.println();
		
		for (int i = 0; i < n; i++) {
			if (equals(saverages[i], min)) {
				System.out.println(i);
				p.println(i);
			}
		}
		
		r.close();
		p.close();
	}
}