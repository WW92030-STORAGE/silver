import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FS1 {
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
			
			if (py != oy) return py.compareTo(oy);
			if (px != ox) return px.compareTo(ox);
			return 0;
		}
		
		public boolean equals(pair other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y;
		}
	}
	static ArrayList<pair> slots = new ArrayList<pair>();
	static PriorityQueue<Integer> times = new PriorityQueue<Integer>();
	
	static int c, n; // n cows and c chickens
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("helpcross.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out"))); // helpcross Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(rr.readLine()); 
			times.add(Integer.parseInt(st.nextToken())); // CHICKEN ONLY AT TIME SPECIFIED
		}
		
		int a, b;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			slots.add(new pair(a, b)); // COW CAN USE SLOT
		}
		
		Collections.sort(slots);
		System.out.println(times);
		System.out.println(slots);
		
		int count = 0;
		while (!slots.isEmpty() && !times.isEmpty()) {
			
			int now = times.poll();
			
			int index = 0;
			pair p = new pair(0, 0);
			for (index = 0; index < slots.size(); index++) {
				p = slots.get(index);
				if (p.y >= now) {
					if (p.x <= now) {
						slots.remove(index);
						count++;
						break;
					}
				}
			}
		}
		
		System.out.println(count);
		pp.println(count);
		
		rr.close();
		pp.close();
	}
	
	
}
