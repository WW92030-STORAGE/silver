import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JS2 {
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
			Integer px = this.x;
			Integer py = this.y;
			Integer ox = other.x;
			Integer oy = other.y;
			if (px != ox) return px.compareTo(ox);
			if (py != oy) return -1 * py.compareTo(oy);
			return 0;
		}
		
		public boolean equals(pair other) {
			return this.compareTo(other) == 0;
		}
		
		public String toString() {
			return "" + x + " " + y;
		}
	}
	static boolean[][] grid;
	static boolean[][] visited;
	static ArrayList<pair> stuff = new ArrayList<pair>();
	
	static int n;
	
	static void print(boolean[][] grid) {
		for (boolean[] r : grid) {
			for (boolean c : r) {
				if (c) System.out.print("#");
				else System.out.print(".");
			}
			System.out.println();
		}
	}
	
	static int area, perimeter;
	
	static boolean inBounds(int x, int y) {
		if (x < 0 || x >= n) return false;
		if (y < 0 || y >= n) return false;
		return true;
	}
	
	static pair dfs(int sx, int sy) {
		int x = 0, y = 0;
		ArrayList<pair> stack = new ArrayList<pair>();
		stack.add(new pair(sx, sy));
		visited[sx][sy] = true;
		int[] dx = {01, 00, -1, 00};
		int[] dy = {00, 01, 00, -1};
		
		int ca = 1;
		int cp = 0;
		while (!stack.isEmpty()) {
			pair p = stack.get(stack.size() - 1);
		//	System.out.println("--" + p);
			x = p.x;
			y = p.y;
			stack.remove(stack.size() - 1);
			
			visited[x][y] = true;
			
			for (int i = 0; i < 4; i++) {
				int xp = x + dx[i];
				int yp = y + dy[i];
				if (inBounds(xp, yp) && !visited[xp][yp] && grid[xp][yp]) {
					stack.add(new pair(xp, yp));
					visited[xp][yp] = true;
					ca++;
				}
				if (!inBounds(xp, yp) || !grid[xp][yp]) cp++;
			}
		}
		return new pair(ca, cp);
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("perimeter.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out"))); // perimeter Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n][n];
		grid = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String s = rr.readLine();
			for (int j = 0; j < n; j++) {
				if (s.charAt(j) == '#') grid[i][j] = true;
				else grid[i][j] = false;
			}
		}
		
		pair cur;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && grid[i][j]) {
					cur = dfs(i, j);
					stuff.add(cur);
				//	System.out.println(cur);
				}
			}
		}
		
	//	print(grid);
		
		Collections.sort(stuff);
		pair res = stuff.get(stuff.size() - 1);
		System.out.println(res);
		pp.println(res);
		
		rr.close();
		pp.close();
	}
}
