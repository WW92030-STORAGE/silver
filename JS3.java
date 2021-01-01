import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JS3 {
	static int[][] grid = new int[4010][4010];
	
	static int cx;
	static int cy;
	static int minx, maxx, miny, maxy;
	
	static int n;
	
	static ArrayList<Integer> moves = new ArrayList<Integer>();
	
	static void print(int x) {
		String s = Integer.toString(x);
		int maxlength = 3;
		while (s.length() < maxlength) s = s + " ";
		System.out.print(s);
	}
	
	static boolean inBounds(int x, int y, int a, int b, int c, int d) {
		if (x < Math.max(0, a) || x >= Math.min(grid.length, b)) return false;
		if (y < Math.max(0, c) || y >= Math.min(grid.length, d)) return false;
		return true;
	}
	
	static void bfs(int sx, int sy, int n, int a, int b, int c, int d) {
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		qx.add(sx);
		qy.add(sy);
		
		int x, y;
		int[] dx = {01, 00, -1, 00};
		int[] dy = {00, 01, 00, -1};
		while (!qx.isEmpty()) {
			x = qx.poll();
			y = qy.poll();
			grid[x][y] = n;
			for (int i = 0; i < 4; i++) {
				int xp = x + dx[i];
				int yp = y + dy[i];
				if (inBounds(xp, yp, a, b, c, d) && grid[xp][yp] == 0) {
					grid[xp][yp] = n;
					qx.add(xp);
					qy.add(yp);
				}
			}
		}
	}
	
	public static void main (String[] args) throws IOException {
	//	System.out.println(); // DELETE LINE WHEN SUBMITTING
		BufferedReader rr = new BufferedReader(new FileReader("gates.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("gates.out"))); // Thing
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		String sss = rr.readLine();
		for (int i = 0; i < n; i++) {
			char c = sss.charAt(i);
			if (c == 'E') moves.add(0);
			else if (c == 'N') moves.add(1);
			else if (c == 'W') moves.add(2);
			else if (c == 'S') moves.add(3);
		}
		
		long start = System.nanoTime();
		
	//	System.out.println(moves);
		
		cx = cy = grid.length / 2;
		
		int[] dx = {01, 00, -1, 00};
		int[] dy = {00, 01, 00, -1};
		
		minx = miny = Integer.MAX_VALUE;
		maxx = maxy = Integer.MIN_VALUE;
		for (int i : moves) {
			for (int twice = 0; twice < 2; twice++) {
				grid[cx][cy] = -1;
				cx += dx[i];
				cy += dy[i];
				minx = Math.min(cx, minx);
				maxx = Math.max(cx, maxx);
				miny = Math.min(cy, miny);
				maxy = Math.max(cy, maxy);
			}
			minx = Math.min(cx, minx);
			maxx = Math.max(cx, maxx);
			miny = Math.min(cy, miny);
			maxy = Math.max(cy, maxy);
		}
		
		// expand bounds
		minx -= 3;
		maxx += 3;
		miny -= 3;
		maxy += 3;
		
		
		
		int count = 1;
		for (int i = minx; i < maxx; i++) {
			for (int j = miny; j < maxy; j++) {
				if (grid[i][j] == 0) {
					
					bfs(i, j, count, minx, maxx, miny, maxy);
					count++;
				}
			}
		}
		
	//	for (int a = minx; a < maxx; a++) {
	//		for (int b = miny; b < maxy; b++) {
	//			print(grid[a][b]);
	//		}
	//		System.out.println();
	//	}
		
		pp.println(count - 2);

		System.out.println((System.nanoTime() - start) / 1000000);
		rr.close();
		pp.close();
	}
}
