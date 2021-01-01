import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DS2_2 { // 10 / 11
	static class triple implements Comparable<triple>{
		private int x;
		private int y;
		private int z;
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
	
	static ArrayList<triple> list = new ArrayList<triple>();
	
	static TreeMap<Integer, Long> prod = new TreeMap<Integer, Long>();
	
	static ArrayList<Long> maxs = new ArrayList<Long>();
	static TreeMap<Long, Long> multiprod = new TreeMap<Long, Long>();
	
	static void increment(long x) {
		if (!multiprod.containsKey(x)) multiprod.put(x, 0L);
		multiprod.put(x, multiprod.get(x) + 1);
	}
	
	static void decrement(long x) {
		if (!multiprod.containsKey(x)) return;
		multiprod.put(x, multiprod.get(x) - 1);
		if (multiprod.get(x) <= 0) multiprod.remove(x);
	}
	
	static void update(int c, long d) {
		if (!prod.containsKey(c)) prod.put(c, 0L);
		prod.put(c, prod.get(c) + d);
	}
	
	static int doTheThing(String s) {
		int magnitude = Integer.parseInt(s.substring(1));
		if (s.charAt(0) == '+') return magnitude;
		return -1 * magnitude;
	}
	
	
	static ArrayList<HashSet<Integer>> state = new ArrayList<HashSet<Integer>>();
	
	static int n, g;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("measurement.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out"))); // measurement Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		
		long start = System.nanoTime();
		
		int t, x, d;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = doTheThing(st.nextToken());
			list.add(new triple(t, x, d));
		}
		
		Collections.sort(list);
		
		
		long prevmax = -1;
		long prevmaxprod = -1;
		long curmaxprod = -1;
		long prevval = -1;
		long curval = -1;
		long curmax = -1;
		long count = 0;
		int index = 0;
		int delta = 0;
		
		boolean unusedincrement = false;
		for (triple T : list) {
			unusedincrement = false;
			index = T.y;
			delta = T.z;
			if (prod.containsKey(index)) prevval = prod.get(index);
			else prevval = 0;
			
			prevmax = curmax;
			prevmaxprod = curmaxprod;
			
			update(index, delta);
			decrement(prevval);
			increment(prevval + delta);
			curval = prod.get(index);
			
		//	System.out.println("VALUES " + index + " " + prevval + " " + delta + " " + (prevval + delta));
		//	System.out.println(prod);	
		//	System.out.println(multiprod);
			
			curmax = multiprod.get(multiprod.lastKey());
			curmaxprod = multiprod.lastKey();
			
		//	System.out.println("MAXIMUMS " + prevmax + " " + curmax + " " + prevmaxprod + " " + curmaxprod);
			if (prevmax != curmax) {
				count++;
				unusedincrement = true;
			}
			else {
				if (curval > prevmaxprod && prevval < prevmaxprod) {
					count++;
					unusedincrement = true;
				}
				else if (curval < curmaxprod && prevval > curmaxprod) {
					count++;
					unusedincrement = true;
				}
			}
			
		//	System.out.println("COUNTER " + unusedincrement + " " + count);
		//	System.out.println("----------------------");
		}
		
		
		System.out.println(count);
		pp.println(count);
		rr.close();
		pp.close();
		
	//	System.out.println((System.nanoTime() - start) / 1000000);
	}
}
