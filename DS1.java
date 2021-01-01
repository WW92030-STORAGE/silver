import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;
// SUBMITTED
public class DS1 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=666
	static TreeSet<Integer> set = new TreeSet<Integer>();
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int n, q;
	
	static int upper_bound(ArrayList<Integer> list, int x) {
		int res = Collections.binarySearch(list, x);
		if (res >= 0) return res + 1;
		else return (-1 * (res + 1));
	}
	
	static int lower_bound(ArrayList<Integer> list, int x) {
		int res = Collections.binarySearch(list, x);
		if (res >= 0) return res;
		else return (-1 * (res + 1)); 
	}
	
	public static void main (String[] args) throws IOException {

		System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader r = new BufferedReader(new FileReader("haybales.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out"))); // ditto
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(r.readLine()); 
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			list.add(x);
			set.add(x);
		}
		
		Collections.sort(list);
		
		int a, b;
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(r.readLine()); 
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int res = upper_bound(list, b) - lower_bound(list, a);
		//	System.out.println(res);
			
			p.println(res);
		}
		
		r.close();
		p.close();
	}
}
