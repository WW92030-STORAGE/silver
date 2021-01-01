import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;
// SUBMITTED
public class DS2 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=571
	static TreeSet<Integer> bb = new TreeSet<Integer>();
	static ArrayList<Integer> ee = new ArrayList<Integer>();
	
	
	
	static int n;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("highcard.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= 2 * n; i++) bb.add(i);
		
		int cur;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			cur = Integer.parseInt(st.nextToken());
			bb.remove(cur);
			ee.add(cur);
		}
		
		int count = 0;
		
		for (int sk = 0; sk < n; sk++) {
			int i = ee.get(0);
			int ceil;
			if (bb.ceiling(i) != null) {
				ceil = bb.ceiling(i);
				count++;
				bb.remove(ceil);
			}
			ee.remove(0);
		}
		
		System.out.println(count);
		p.println(count);
		
		r.close();
		p.close();
	}
}
