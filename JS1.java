import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JS1 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=594
	// !WARNING! 9/10 TEST CASES CORRECT
	// SUBMITTED
	static int[] arr;
	
	static int n, k;
	static int r;
	static int low, high, mid;
	
	static boolean check(int r) { // 1 3 8 10 18 20 25
		// 					r = 5:   -------- --------
		int len = 2 * r;
		int ind = 0;
		int cur = 0;
		int count = 0;
		while (count <= k && ind < n) {
			cur = arr[ind];
			count++;
			while (ind < n && arr[ind] <= cur + len) ind++;
			if (ind >= n) break;
		}
	//	System.out.print(count + " ");
		if (count <= k) return true;
		return false;
	}
	
	public static void main (String[] args) throws IOException {

	//	System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader r = new BufferedReader(new FileReader("angry.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("angry.out"))); // Thing
		
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[n]; 
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		low = 0;
		high = arr[n - 1] + 1 << 16;
		while (low <= high) {
			mid = (low + high) / 2;
			System.out.println(low + " " + mid + " " + high + " ");
			boolean works = check(mid);
		//	System.out.println();
			if (works) {
				high = mid - 1;
				if (!check(mid - 1)) break;
			}
			else {
				low = mid + 1;
			}
		}
		pp.println(mid);
		System.out.println(mid);
		
		pp.close();
		
	}
}
