import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class DS3 {// http://www.usaco.org/index.php?page=viewproblem2&cpid=764
	// WEEK 6
	// oh no its a brute force algorithm that works 
	static HashSet<Integer> fixed = new HashSet<Integer>();
	static HashSet<Integer> removals = new HashSet<Integer>();
	static HashSet<Integer> permutation = new HashSet<Integer>();
	
	static HashSet<HashSet<Integer>> existingStuff = new HashSet<HashSet<Integer>>();
	
	static int[] map;
	static int n;
	static HashSet<Integer> permute(HashSet<Integer> h) {
		HashSet<Integer> res = new HashSet<Integer>();
		for (int i : h) res.add(map[i]);
		return res;
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("shuffle.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out"))); // shuffle Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		map = new int[n];
		
		long start = System.nanoTime();
		
		st = new StringTokenizer(rr.readLine()); 
		for (int i = 0; i < n; i++) {
			fixed.add(i);
			permutation.add(i);
			
			map[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		
	//	for (int i : map) System.out.print(i + " ");
		System.out.println();
		
		
		while (!existingStuff.contains(permutation)) {
			existingStuff.add(permutation);
			permutation = permute(permutation);
			removals.clear();
			for (int ii : fixed) {
				if (!permutation.contains(ii)) removals.add(ii);
			}
			for (int ii : removals) fixed.remove(ii);
		}
		
		System.out.println(fixed.size());
		pp.println(fixed.size());
		
		System.out.println((System.nanoTime() - start) / 1000000);
		
		rr.close();
		pp.close();
	}
}
