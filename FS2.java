import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FS2 { // sliding window sum http://www.usaco.org/index.php?page=viewproblem2&cpid=715
	// SUBMITTED
	static int[] road;
	static int[] pre;
	
	static int sum(int a, int b) {
		if (a == 0) return pre[b];
		return pre[b] - pre[a - 1];
	}
	
	static int n, k, b;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("maxcross.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		road = new int[n];
		pre = new int[n];
		
		Arrays.fill(road, 0);
		
		int index;
		for (int i = 0; i < b; i++) {
			index = Integer.parseInt(r.readLine()) - 1;
			road[index] = 1;
		}
		
		pre[0] = road[0];
		for (int i = 1; i < n; i++) pre[i] = pre[i - 1] + road[i];
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i + k - 1 < n; i++) {
			int cur = sum(i, i + k - 1);
			min = Math.min(min, cur); // the problem HERE is to find the minimum window sum (of the broken areas)
		}
		
		System.out.println(min);
		p.println(min);
		
		r.close();
		p.close();
	}
}
