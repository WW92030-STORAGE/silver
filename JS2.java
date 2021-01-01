import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
// SUBMITTED
public class JS2 { // prefix sums with changes. http://www.usaco.org/index.php?page=viewproblem2&cpid=691
	static int[] ph;
	static int[] pp;
	static int[] ps;
	
	static int[][] state;
	
	static int sh(int a, int b) {
		if (a != 0) return ph[b] - ph[a - 1];
		return ph[b];
	}
	static int sp(int a, int b) {
		if (a != 0) return pp[b] - pp[a - 1];
		return pp[b];
	}
	static int ss(int a, int b) {
		if (a != 0) return ps[b] - ps[a - 1];
		return ps[b];
	}
	
	static int skeppyisretarded(int a, int b, int c) {
		if (c == 0) return sh(a, b);
		if (c == 1) return sp(a, b);
		if (c == 2) return ss(a, b);
		return 0;
	}
	
	static int n;
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("hps.in")); // this must be in the general project folder not src
		PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("hps.out"))); // ditto
        
		StringTokenizer st = new StringTokenizer(r.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		state = new int[n][3];
		ph = new int[n];
		ps = new int[n];
		pp = new int[n];
		
		char c;
		for (int i = 0; i < n; i++) {
			c = r.readLine().charAt(0);
			switch (c) {
			case 'H': 
				state[i][0] = 1;
				break;
			case 'P':
				state[i][1] = 1;
				break;
			case 'S':
				state[i][2] = 1;
				break;
			}
		}
		
		ph[0] = state[0][0];
		pp[0] = state[0][1];
		ps[0] = state[0][2];
		
		for (int i = 1; i < n; i++) {
			ph[i] = ph[i - 1] + state[i][0];
			pp[i] = pp[i - 1] + state[i][1];
			ps[i] = ps[i - 1] + state[i][2];
		}
		
		int max = -69;
		
		int cur;
		
		int[][] bruteforce = {{1, 2}, {1, 0}, {2, 0}, {2, 1}, {0, 1}, {0, 2}};
		
		for (int i = 0; i < n; i++) {
			for (int s = 0; s < 6; s++) {
				cur = skeppyisretarded(0, i, bruteforce[s][0]) + skeppyisretarded(i, n - 1, bruteforce[s][1]);
				max = Math.max(max, cur);
			}
		}
		
		System.out.println(max);
		p.println(max);
		
		r.close();
		p.close();
	}
}
