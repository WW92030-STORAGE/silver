import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FS2 { // http://usaco.org/index.php?page=viewproblem2&cpid=530 
/*
Man 1: Got a question for you.  What's heavier?  A kilogram of steel, or a kilogram of feathers?  
[Timer appears on screen and counts down about 4 seconds]
Answer is: a kilogram of steel, because steel is heavier than feathers.

[Timeskip. ENTER another man]

Man 1: What do you mean?

Man 2: They're both a kilogram

Man 1: But steel's heavier than feathers.

Man 2: [chuckles] I know. But they're both a kilogram

Man 1: What?
*/
	
	// SUBMITTED
	
	static int r, c, k;
	
	static long[][] grid;
	static long[][] dp;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("hopscotch.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out"))); // hopscotch Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new long[r][c];
		grid = new long[r][c];
		for (int i = 0; i < r; i++) dp[i][0] = 0;
		for (int i = 0; i < c; i++) dp[0][i] = 0;
		dp[0][0] = 1;
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(rr.readLine()); 
			for (int j = 0; j < c; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < r; i++) {
			for (int j = 1; j < c; j++) {
				long sum = 0;
				for (int a = 0; a < i; a++) {
					for (int b = 0; b < j; b++) {
						if (grid[a][b] != grid[i][j]) sum += dp[a][b];
						sum %= 1000000007;
					}
				}
				dp[i][j] = sum % 1000000007;
			}
		}
		
		System.out.println(dp[r - 1][c - 1] % 1000000007);
		pp.println(dp[r - 1][c - 1] % 1000000007);
		
		rr.close();
		pp.close();
	}
}
