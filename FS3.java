import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class FS3 { // http://usaco.org/index.php?page=viewproblem2&cpid=716
	// SUBMITTED
	static class quad implements Comparable<quad>{
		private int x;
		private int y;
		private int z;
		private int w;
		public quad(int a, int b, int c, int d) {
			x = a;
			y = b;
			z = c;
			w = d;
		}
		
		public void set(int a, int b, int c, int d) {
			x = a;
			y = b;
			z = c;
			w = d;
		}
		
		public void set(quad p) {
			x = p.x;
			y = p.y;
			z = p.z;
			w = p.w;
		}
		
		public int compareTo(quad other) {
			Integer px = this.x;
			Integer py = this.y;
			Integer pz = this.z;
			Integer pw = this.w;
			Integer ox = other.x;
			Integer oy = other.y;
			Integer oz = other.z;
			Integer ow = other.w;
			if (px != ox) return px.compareTo(ox);
			if (py != oy) return py.compareTo(oy);
			if (pz != oz) return pz.compareTo(oz);
			if (pw != ow) return pw.compareTo(ow);
			return 0;
		}
		
		public boolean equals(quad other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y + " " + z + " " + w;
		}
	}
	static class pair implements Comparable<pair>{
		private int x;
		private int y;
		public pair(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(int a, int b) {
			x = a;
			y = b;
		}
		
		public void set(pair p) {
			x = p.x;
			y = p.y;
		}
		
		public int compareTo(pair other) {
			int px = this.x;
			int py = this.y;
			int ox = other.x;
			int oy = other.y;
			if (px != ox) return px - ox;
			if (py != oy) return py - oy;
			return 0;
		}
		
		public boolean equals(pair other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y;
		}
	}
	
	static HashMap<Integer, HashSet<pair>> connected = new HashMap<Integer, HashSet<pair>>();
	
	static pair[] cows;
	
	static HashSet<pair> distant = new HashSet<pair>();
	
	static int n, k, r;
	
	static boolean inBounds(int x, int y) {
		if (x < 0 || x >= n) return false;
		if (y < 0 || y >= n) return false;
		return true;
	}
	
	static int[] dx = {00, -1, 00, 01};
	static int[] dy = {01, 00, -1, 00};
	
	static int[][] grid;
	static quad[][] reachable;
	
	static boolean dunndunndunn(quad q, int x) {
		if (x == 0) return q.x == 1;
		if (x == 1) return q.y == 1;
		if (x == 2) return q.z == 1;
		if (x == 3) return q.w == 1;
		return (int)(Math.random() * 2) == 0;
	}
	
	static void dfs(int sx, int sy, int id) {
		ArrayList<pair> stack = new ArrayList<pair>();
		stack.add(new pair(sx, sy));
		int x, y;
		while (!stack.isEmpty()) {
			pair p = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			x = p.x;
			y = p.y;
		//	System.out.println(x + " " + y + " " + id);
			grid[x][y] = id;
			for (int i = 0; i < 4; i++) {
				if (dunndunndunn(reachable[x][y], i)) {
					if (inBounds(x + dx[i], y + dy[i]) && grid[x + dx[i]][y + dy[i]] == 0) stack.add(new pair(x + dx[i], y + dy[i]));
				}
			}
		}
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("countcross.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		cows = new pair[k];
		grid = new int[n][n];
		reachable = new quad[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				reachable[i][j] = new quad(1, 1, 1, 1); // R U L D
			}
		}
		
		for (int i = 0; i < n; i++) Arrays.fill(grid[i], 0);
		
		
		int x1, y1, x2, y2;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(rr.readLine()); 
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			if (x1 == x2) {
				if (y1 < y2) { // 1 is left 2
					reachable[x1][y1].x = 0;
					reachable[x2][y2].z = 0;
				}
				else { // 1 is right 2
					reachable[x2][y2].x = 0;
					reachable[x1][y1].z = 0;
				}
			}
			else if (x1 < x2) { // 1 is above 2
				reachable[x1][y1].w = 0;
				reachable[x2][y2].y = 0;
			}
			else { // 1 is below 2
				reachable[x2][y2].w = 0;
				reachable[x1][y1].y = 0;
			}
		}
		/*
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println(i + " " + j + " = " + reachable[i][j]);
			}
			System.out.println();
		}
		*/
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] <= 0) {
					count++;
					dfs(i, j, count);
				}
			}
		}
		System.out.println();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		
		int x, y;
		
		for (int i = 1; i <= count; i++) connected.put(i, new HashSet<pair>());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(rr.readLine()); 
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;
			int id = grid[x][y];
			connected.get(id).add(new pair(x, y));
		}
		
		int res = 0;
		for (int i = 1; i < count; i++) {
			for (int j = i + 1; j <= count; j++) {
				res += (connected.get(i).size() * connected.get(j).size());
			}
		}
		
		System.out.println(res);
		p.println(res);
		
		rr.close();
		p.close();
	}
}
