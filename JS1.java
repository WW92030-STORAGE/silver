import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JS1 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=690
	// WEEK 6
	// using a pRiOrItY qUeUe
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
	
	static PriorityQueue<pair> pq = new PriorityQueue<pair>();
	
	static int[] durations;
	
	static int n;
	
	static int m;
	
	static boolean test(int k) {
		int time = 0;
		for (int i = 0; i < k; i++) {
			pq.add(new pair(time + durations[i], i));
		}
		int i = k;
		while (!pq.isEmpty()) {
			time = pq.peek().x;
			pq.remove();
			
			if (i < n) pq.add(new pair(time + durations[i], i));
			i++;
		}
		return (time <= m);
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("cowdance.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out"))); // Thing cowdance
		
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
	//	pp.println("dsijeweogke");
		
		long startTime = System.nanoTime();
		durations = new int[n];
		for (int i = 0; i < n; i++) {
			durations[i] = Integer.parseInt(rr.readLine());
		}
		
		System.out.println();
		
		int low = 0;
		int high = n;
		int mid = 1;

		while (low + 4 < high) {
			mid = (low + high) / 2; 
			if (test(mid)) {
				high = mid - 1;
			}
			else low = mid + 1;
		}
		
		for (mid = Math.max(1, low - 4); mid < high + 4; mid++) {
			if (test(mid)) break;
		}
		pp.println(mid);
		System.out.println(mid);
		
		
		System.out.println((System.nanoTime() - startTime) / 1000000);
		
		rr.close();
		pp.close();
	}
}
