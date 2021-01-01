import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class OS1REAL { // http://www.usaco.org/index.php?page=viewproblem2&cpid=738
	static TreeMap<Integer, Integer> vals = new TreeMap<Integer, Integer>();
	
	static void push(int key, int val) {
		if (!vals.containsKey(key)) vals.put(key, 0);
		vals.put(key, vals.get(key) + val);
	}
	
	static void pop(int key, int val) {
		vals.put(key, vals.get(key) - val);
		if (vals.get(key) <= 0) vals.remove(key);
	}
	
	static int n;
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("pairup.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		int a, b;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			push(b, a);
		}
		
		
		int max = -1;
		while (!vals.isEmpty()) {
			if (vals.size() >= 2) {
				int low = vals.firstKey();
				int high = vals.lastKey();
				max = Math.max(max, low + high);
				
				int mindiff = Math.min(vals.get(low), vals.get(high));
				pop(low, mindiff);
				pop(high, mindiff);
			}
			else if (!vals.isEmpty()) {
				int now = vals.get(vals.firstKey());
				max = Math.max(max, 2 * now);
				pop(vals.firstKey(), vals.get(vals.firstKey()));
			}
		}
		
		System.out.println(max);
		pp.println(max);
		
		r.close();
		pp.close();
	}
}
