import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OS2 { // http://usaco.org/index.php?page=viewproblem2&cpid=643
	static int n, k;
	// SUBMITTED
	
	static int[] list;
	static int[] upper;
	
	static int get(int[] list, int x) {
		if (x >= list.length) return Integer.MAX_VALUE;
		return list[x];
	}
	
	static int interval(int x) {
		return upper[x] - x + 1;
	}
	
	public static void main (String[] args) throws IOException {

	//	System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader rr = new BufferedReader(new FileReader("diamond.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out"))); // Thing
		
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		list = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		upper = new int[n];
		int low = 0;
		int high = 0;
		while (high < n) { // 2 pointers method
			int nextdiff = get(list, high + 1) - get(list, low);
			if (nextdiff > k) {
				upper[low] = high;
				low++;
			}
			else high++;
		}
		
		System.out.println(n + " " + k);
	//	for (int i : list) System.out.print(i + " ");
		System.out.println();
	//	for (int i = 0; i < n; i++) System.out.println(i + " " + upper[i]);
		
		int max, cur;
		max = Integer.MIN_VALUE;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				while (j <= upper[i]) j++;
				if (j >= n) break;
				cur = interval(i) + interval(j);
				max = Math.max(cur, max);
			}
		}
		
		System.out.println(max);
		pp.println(max);
		
		rr.close();
		pp.close();
	}
}
