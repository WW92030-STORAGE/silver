import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DS2STRING { // using a string instead of a pair
	// http://www.usaco.org/index.php?page=viewproblem2&cpid=667
	// SUBMITTED
	static String swap(String s) {
		return s.substring(2) + s.substring(0, 2);
	}
	
	static TreeMap<String, Long> count = new TreeMap<String, Long>();
	
	static void add(String s) {
		if (!count.containsKey(s)) count.put(s, 0L);
		count.put(s, count.get(s) + 1);
	}
	
	static int n;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("citystate.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		String c, s;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			c = st.nextToken();
			s = st.nextToken();
			c = c.substring(0, 2);
			
			String res = "" + c + s;
			add(res);
		}
		
		long sum = 0;
		for (String x : count.keySet()) {
			if (x.substring(0, 2).equals(x.substring(2))) continue;
			long c1 = count.get(x);
			String y = swap(x);
			long c2 = 0;
			if (count.containsKey(y)) c2 = count.get(y);
			sum += c1 * c2;
		}
		
		System.out.println(sum / 2);
		p.println(sum / 2);
		
		r.close();
		p.close();
	}
}
