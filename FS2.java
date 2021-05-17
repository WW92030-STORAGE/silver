import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FS2 { // http://usaco.org/index.php?page=viewproblem2&cpid=919
	// haha 2d prefix sum go brrr
	// SUBMITTED
	static int[][] psum;
	static int[][] grid;
	static int n, k;
	static int maxx, maxy;
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("paintbarn.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out"))); // Thing paintbarn
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		psum = new int[1010][1010];
		grid = new int[1010][1010];
		
		int x1, y1, x2, y2;
		maxx = -1;
		maxy = -1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			maxx = Math.max(maxx, x2);
			maxy = Math.max(maxy, y2);
			for (int ii = x1; ii <= x2; ii++) {
				grid[ii][y1]++;
				grid[ii][y2 + 1]--;
			}
		}
		
		for (int x = 0; x <= maxx; x++) { // dont worry i wrote this i just used x here to keep track of where im going
			psum[x][0] = grid[x][0];
			for (int y = 1; y <= maxy; y++) psum[x][y] = psum[x][y - 1] + grid[x][y];
		}
		
		int count = 0;
		for (int i = 0; i <= maxx; i++) {
			for (int j = 0; j <= maxy; j++) {
				if (psum[i][j] == k) count++;
			}
		}
		System.out.println(count);
		pp.println(count);
		/*
		for (int i = 0; i <= maxx; i++) {
			for (int j = 0; j <= maxy; j++) System.out.print(psum[i][j] + " ");
			System.out.println();
		}
		*/
		
		r.close();
		pp.close();
	}
}

// http://bit.ly/3mnSY6o
