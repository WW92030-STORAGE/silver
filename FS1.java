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
import java.util.TreeSet;

public class FS1 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=810
	// SUBMITTED
	static TreeMap<Long, Long> stops = new TreeMap<Long, Long>();
	static ArrayList<Long> points = new ArrayList<Long>();
	
	static int l, n, f, b;
	
	static TreeSet<Long> marks = new TreeSet<Long>();
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("reststops.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		int diff = Math.abs(f - b);
		
		long x, t;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			x = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			stops.put(x, t);
			points.add(x);
		}
		
		Collections.sort(points);
		long curmax = -1;
		for (int i = points.size() - 1; i >= 0; i--) {
			long now = points.get(i);
			long cur = stops.get(now);
			if (cur > curmax) {
				marks.add(now);
				curmax = cur;
			}
		}
		
		long sum = 0;
		sum += (long)marks.first() * diff * stops.get(marks.first());
		
		long prev = marks.first();
		long cur = prev;
		marks.remove(marks.first());
		
		while (!marks.isEmpty()) {
			System.out.println(marks.first());
			prev = cur;
			cur = marks.first();
			marks.remove(marks.first());
			sum += (long)(cur - prev) * (long)stops.get(cur) * (long)diff;
		}
		
		System.out.println(sum);
		pp.println(sum);
		
		r.close();
		pp.close();
	}
}
