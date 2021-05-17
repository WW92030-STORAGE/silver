import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DS2 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=859
	// WEEK 8
	
	
	// lower id = higher seniority
	
	static class triple implements Comparable<triple>{
		private int x; // seniority / id
		private int y; // arrival time
		private int z; // duration
		public triple(int a, int b, int c) {
			x = a;
			y = b;
			z = c;
		}
		
		public void set(int a, int b, int c) {
			x = a;
			y = b;
			z = c;
		}
		
		public int compareTo(triple other) {
			int px = this.x;
			int py = this.y;
			int pz = this.z;
			int ox = other.x;
			int oy = other.y;
			int oz = other.z;
			if (px != ox) return px - ox;
			if (py != oy) return py - oy;
			if (pz != oz) return pz - oz;
			return 0;
		}
		
		public boolean equals(triple other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y + " " + z;
		}
	}
	
	static int n;
	
	static ArrayList<triple> arrivals = new ArrayList<triple>(); // arrival id duration
	static TreeSet<triple> pq = new TreeSet<triple>();
	
	static TreeSet<Integer> ate = new TreeSet<Integer>();
	
	static TreeSet<triple> events = new TreeSet<triple>(); // keeps track of arrivals and departures. Time State Id
	
	static int[] duration;
	static int[] wait;
	
	static String toString(int[] arr) {
		String res = "";
		for (int i : arr) res = res + i + " ";
		return res;
	}
	
	static void print(int[] arr) {
		System.out.println(toString(arr));
	}
	
	public static void main (String[] args) throws IOException {
	//	System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader rr = new BufferedReader(new FileReader("convention2.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out"))); // Thing
		
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		duration = new int[n];
		wait = new int[n];
		
		long start = System.nanoTime();
		
		int arr, dur;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			arr = Integer.parseInt(st.nextToken());
			dur = Integer.parseInt(st.nextToken());
			arrivals.add(new triple(arr, i, dur));
			events.add(new triple(arr, 1, i));
			duration[i] = dur;
		}
		
		Collections.sort(arrivals);
	//	System.out.println(arrivals);
		
		triple cur;
		int curTime = 0;
		int id = 0;
		int state = 0;
		boolean active = false;
		while (ate.size() != n) {
			cur = events.pollFirst();
		//	System.out.println(cur);
		//	System.out.println(pq);
		//	System.out.println(events);
			
			curTime = cur.x;
			id = cur.z;
			state = cur.y;
			
			if (state == 1 && !active) { // arrived and is empty
				wait[id] = 0;
				active = true;
				events.add(new triple(curTime + duration[id], -1, id)); // time state id;
			}
			else if (state == 1 && active) {
				pq.add(new triple(id, curTime, duration[id])); // id arrival duration
			}
			else if (state == -1) {
				ate.add(id);
				active = false;
			}
			if (active == false && !pq.isEmpty()) { // set new cow to eat
				triple front = pq.pollFirst(); // id arrival duration
				active = true;
				events.add(new triple(curTime + duration[front.x], -1, front.x));
				wait[front.x] = curTime - front.y;
			}
			
		}
		
	//	print(wait);
		Arrays.sort(wait);
		
		System.out.println(wait[wait.length - 1]);
		pp.println(wait[wait.length - 1]);

		System.out.println((System.nanoTime() - start) / 1000000);
		rr.close();
		pp.close();
	}
}
