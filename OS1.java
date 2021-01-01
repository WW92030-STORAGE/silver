import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class OS1 { // http://usaco.org/index.php?page=viewproblem2&cpid=549
	// WEEK 6
	// sometimes it works when the numbers are small enough
	// if you had 100 possible numbers for each character ... i'd rather not think about it
	static boolean test(int b, int e, int s, int i, int g, int o, int m) {
		long first = (b + e + s + s + i + e + 777777) % 7;
		long second = (g + o + e + s + 777777) % 7;
		long third = (m + o + o + 777777) % 7;
		return (first * second * third) % 7 == 0;
	}
	
	static int n;
	static HashMap<Character, int[]> map = new HashMap<Character, int[]>();
	
	static void put(char c, int x) {
		if (!map.containsKey(c)) {
			map.put(c, new int[7]);
			Arrays.fill(map.get(c), 0);
		}
		map.get(c)[(x + 777777) % 7]++;
	}
	
	static String print(int[] arr) {
		String s = "" + arr[0];
		for (int i = 1; i < 7; i++) {
			s = s + " " + arr[i];
		}
		return s;
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("bgm.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out"))); // bgm Thing
        
		StringTokenizer st = new StringTokenizer(rr.readLine()); 
		n = Integer.parseInt(st.nextToken());
		
		char cd;
		int x;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(rr.readLine()); 
			cd = st.nextToken().charAt(0);
			x = Integer.parseInt(st.nextToken());
			put(cd, (x + 777777) % 7);
		}
		
		for (char c : map.keySet()) {
			System.out.println(c + " " + print(map.get(c)));
		}
		
		int[] bb = map.get('B');
		int[] ee = map.get('E');
		int[] ss = map.get('S');
		int[] ii = map.get('I');
		int[] gg = map.get('G');
		int[] oo = map.get('O');
		int[] mm = map.get('M');
		
		// uh oh
		long count = 0;
		for (int b = 0; b < 7; b++) {
			for (int e = 0; e < 7; e++) {
				for (int s = 0; s < 7; s++) {
					for (int i = 0; i < 7; i++) {
						for (int g = 0; g < 7; g++) {
							for (int o = 0; o < 7; o++) {
								for (int m = 0; m < 7; m++) {
									if (test(b, e, s, i, g, o, m)) {
										long sum = (long)bb[b] * ee[e] * ss[s] * ii[i] * gg[g] * oo[o] * mm[m];
										count += sum;
									}
								}
							}
						}
					}
				}
			}
		}
		
		System.out.println(count);
		pp.println(count);
		
		rr.close();
		pp.close();
	}
}
