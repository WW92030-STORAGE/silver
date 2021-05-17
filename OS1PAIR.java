import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;


public class OS1PAIR { // http://usaco.org/index.php?page=viewproblem2&cpid=834
	// !WARNING! 9/10 TEST CASES CORRECT
	static class pair implements Comparable<pair>{
		private int x;
		private int y;
		public pair(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(pair p) {
			x = p.x;
			y = p.y;
		}
		
		public int compareTo(pair other) {
			Integer px = this.x;
			Integer py = this.y;
			Integer ox = other.x;
			Integer oy = other.y;
			if (px != ox) return px.compareTo(ox);
			if (py != oy) return py.compareTo(oy);
			return 0;
		}
		
		public boolean equals(pair other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y;
		}
	}
	
	static int[] arr;
	static ArrayList<pair> originals = new ArrayList<pair>();
	static ArrayList<pair> sorted = new ArrayList<pair>();
	
	static int n;
	
	public static void main (String[] args) throws IOException {

	//	System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader rr = new BufferedReader(new FileReader("sort.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("sort.out"))); // sort Thing
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
			sorted.add(new pair(arr[i], i));
			originals.add(new pair(arr[i], i));
		}
		
		Collections.sort(sorted);
		
	//	for (pair p : sorted) System.out.println(p);
	 	System.out.println();
		
		
		int max = -1;
		
		for (pair p : originals) {
			int newIndex = Collections.binarySearch(sorted, p);
			int diff = -1 * (newIndex - p.y);
			max = Math.max(diff, max);
		}
		
		System.out.println(max + 1);
		pp.println(max + 1);
		
		rr.close();
		pp.close();
	}
}
