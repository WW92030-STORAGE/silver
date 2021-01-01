import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// SUBMITTED
public class JS2 { // http://usaco.org/index.php?page=viewproblem2&cpid=595
	static int modd = 7;
	static int[] pmod;
	static int[] arr;
	
	static int n;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("div7.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("div7.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		pmod = new int[n + 1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i] %= modd;
		}
		
		pmod[0] = 0;
		
		int[] first = new int[modd];
		int[] last = new int[modd];
		Arrays.fill(first, -1);
		Arrays.fill(last, -1);
		first[0] = 0;
		last[0] = 0;
		
		for (int i = 1; i <= n; i++) {
			pmod[i] = pmod[i - 1] + arr[i - 1];
			pmod[i] %= modd;
			int now = pmod[i];
			
			if (first[now] == -1) first[now] = i;
			last[now] = i;
		}
		
		int max = -69;
		for (int i = 0; i < 7; i++) {
			if (first[i] != -1 && last[i] != -1) max = Math.max(last[i] - first[i], max);
		}
		
		System.out.println(max);
		pp.println(max);
		
		r.close();
		pp.close();
	}
}
