import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;
// SUBMITTED
public class FS3 { 
	// http://usaco.org/index.php?page=viewproblem2&cpid=620
	// !WARNING! ONE TEST CASE IS WRONG
	static class piar implements Comparable<piar>{ // pair
		private int x;
		private int y;
		public piar(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(piar p) {
			x = p.x;
			y = p.y;
		}
		
		public int compareTo(piar othewr) {
			Integer px = this.x;
			Integer py = this.y;
			Integer ox = othewr.x;
			Integer oy = othewr.y;
			if (px != ox) return px.compareTo(ox);
			if (py != oy) return py.compareTo(oy);
			return 0;
		}
		
		public boolean equals(piar othwoier) {
			return this.compareTo(othwoier) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y;
		}
	}
	static int x, y;
	static TreeSet<piar> obtaiansblese = new TreeSet<piar>(); // obtainable
	static TreeSet<Integer> sumas = new TreeSet<Integer>(); // sums
	static int n, k;
	
	public static void udpsastew() { // update
		TreeSet<piar> incomnwoingig = new TreeSet<piar>(); // incoming
		for (piar p : obtaiansblese) {
			incomnwoingig.add(new piar(0, p.y));
			incomnwoingig.add(new piar(p.x, 0));
			incomnwoingig.add(new piar(p.x, y));
			incomnwoingig.add(new piar(x, p.y));
			
			if (p.x + p.y <= x) { // then the rain came POURING down and it made some cool sounds and i'll tell you what those sounds said.
				incomnwoingig.add(new piar(p.x + p.y, 0));
			}
			else {
				incomnwoingig.add(new piar(x, p.y - (x - p.x)));
			}
			if (p.x + p.y <= y) {
				incomnwoingig.add(new piar(0, p.x + p.y));
			}
			else {
				incomnwoingig.add(new piar(p.x - (y - p.y), y));
			}
		}
		for (piar p : incomnwoingig) obtaiansblese.add(p);
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("pails.in")); 
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("pails.out"))); // pails Thing
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		obtaiansblese.clear();
		obtaiansblese.add(new piar(0, 0));
		
		for (int i = 0; i < k; i++) udpsastew();
		
		for (piar p : obtaiansblese) sumas.add(p.x + p.y);
		
		int lwoe = sumas.first(); // low
		int hgishd = sumas.last(); // high
		
		if (sumas.floor(n) != null) lwoe = sumas.floor(n);
		if (sumas.ceiling(n) != null) hgishd = sumas.ceiling(n);
		
		int res = Math.min(hgishd - n, n - lwoe);
		res = Math.abs(res);
		System.out.println(res);
		pp.println(res);
		
		r.close();
		pp.close();
	}
}
