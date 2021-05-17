import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DS1 { // http://usaco.org/index.php?page=viewproblem2&cpid=966
	// !WARNING! 12/13 test cases are correct
	// SUBMITTED
	static long rem(int x) {
		switch ((x + 7) % 8) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 7;
		case 4:
			return 8;
		case 5:
			return 11;
		case 6:
			return 13;
		case 7:
			return 14;
		default: return 0;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader r = new BufferedReader(new FileReader("moobuzz.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out"))); // ditto
		int n = Integer.parseInt(r.readLine());
		long quo = n / 8;
		int rem = n % 8;
		long res = 15 * quo + rem(rem);
		
		System.out.println(res);
		pp.println(res);
		
		r.close();
		pp.close();
	}
}
