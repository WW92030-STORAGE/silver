import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// SUBMITTED
public class DS3{ // http://www.usaco.org/index.php?page=viewproblem2&cpid=668

	static int n;
	
	static int[][] graph;
	
	static int[][] system; // coords and radius
	static HashSet<Integer> visited = new HashSet<Integer>();
	
	static boolean close(int x1, int y1, int x2, int y2, int r1) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		
		int dsq = dx * dx + dy * dy;
		return dsq <= (r1 * r1);
	}
	
	static int dfs(int src) {
		int count = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(src);
		
		while (!q.isEmpty()) {
			int now = q.poll();
			visited.add(now);
			for (int i = 0; i < n; i++) {
				if (graph[now][i] == 1 && !visited.contains(i)) {
					q.add(i);
				}
			}
		}
		return visited.size();
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("moocast.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		system = new int[n][3];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(r.readLine()); 
			system[i][0] = Integer.parseInt(st.nextToken());
			system[i][1] = Integer.parseInt(st.nextToken());
			system[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int x1 = system[i][0];
				int y1 = system[i][1];
				int x2 = system[j][0];
				int y2 = system[j][1];
				int r1 = system[i][2];
				int r2 = system[j][2];
				if (close(x1, y1, x2, y2, r1)) graph[i][j] = 1;
				if (close(x2, y2, x1, y1, r2)) graph[j][i] = 1;
			}
		}
		
		int max = -69;
		for (int i = 0; i < n; i++) {
			visited.clear();
			int count = dfs(i);
			max = Math.max(max, count);
		}
		
		System.out.println(max); // yourself
		p.println(max);
		
		r.close();
		p.close();
	}
}