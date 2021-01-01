import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class DS1 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=570
	// SUBMITTED
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
	
	
	static TreeSet<pair> visited = new TreeSet<pair>();
	static boolean[][] grid;
	static boolean updated;
	
	static TreeMap<pair, TreeSet<pair>> lights = new TreeMap<pair, TreeSet<pair>>();
	
	static pair push(pair p1, pair p2) {
		if (!lights.containsKey(p1)) lights.put(p1, new TreeSet<pair>());
		lights.get(p1).add(p2);
		return p2;
	}
	
	static int n, m;
	
	static boolean inBounds(int x, int y) {
		if (x < 0 || x >= n) return false;
		if (y < 0 || y >= n) return false;
		return true;
	}
	
	static boolean isGood(int x, int y) {
		return inBounds(x, y) && !visited.contains(new pair(x, y)) && grid[x][y];
	}
	
	static void dfs(int sx, int sy) {
		ArrayList<pair> q = new ArrayList<pair>();
		q.add(new pair(sx, sy));
		
		int[] dx = {01, 00, -1, 00};
		int[] dy = {00, 01, 00, -1};
		while (!q.isEmpty()) {
			pair p = q.get(q.size() - 1);
		//	System.out.println(p + " " + "--");
			q.remove(q.size() - 1);
			visited.add(p); 
			if (lights.containsKey(p)) {
				for (pair target : lights.get(p)) {
				//	System.out.println(target + "++");
					if (grid[target.x][target.y] != true) {
						grid[target.x][target.y] = true; 
						updated = false;
					}
				}
			}
				
			
			for (int i = 0; i < 4; i++) {
				int xp = p.x + dx[i];
				int yp = p.y + dy[i];
				if (isGood(xp, yp)) q.add(new pair(xp, yp));
			}
		}
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("lightson.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		grid = new boolean[n][n];
		
		int x1, y1, x2, y2;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(r.readLine()); 
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			
			push(new pair(x1, y1), new pair(x2, y2));
		}
		
		long start = System.nanoTime();
		
		grid[0][0] = true;
		
		updated = true;
		while (updated) {
			visited.clear();
			dfs(0, 0);
		}
		
		for (int i = 0; i < 200; i++) {
			visited.clear();
			dfs(0, 0);
		}
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j]) count++;
			}
		}
		
		System.out.println(count);
		p.println(count);
		
		System.out.println((System.nanoTime() - start) / 1000000);
		
		r.close();
		p.close();
	}
}
