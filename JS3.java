import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JS3 { // http://www.usaco.org/index.php?page=viewproblem2&cpid=692
	// abc => abc cab babcca aabccabbabcc
	// calculate which segment it is in
	// n -> (n - 1) % [half length]
	// this one was so confusing i had to write it all down 
	
	// SUBMITTED
	
	static long n;
	static String s;
	static char derive(long index, long halfLength) {
		System.out.println(index + " " + halfLength + " " + (index % halfLength));
		if (index < s.length()) return s.charAt((int)index);
		
		
		if (index >= halfLength) index--;
		index %= halfLength;
		while (index < 0) index += 2 * halfLength;
		index %= halfLength;
		return derive(index, halfLength / 2);
	}
	
	public static void main (String[] args) throws FileNotFoundException, IOException {
		BufferedReader rr = new BufferedReader(new FileReader("cowcode.in")); // this must be in the general project folder not src
		PrintWriter pp = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out"))); // Thing cowcode
        
		StringTokenizer st = new StringTokenizer(rr.readLine());
		s = st.nextToken();
		n = Long.parseLong(st.nextToken());
		
		long reality = s.length();
		while (reality * 2 <= n) reality *= 2;
		System.out.println(reality);
		char c = derive(n - 1, reality);
		System.out.println(c);
		pp.println(c);
		
		rr.close();
		pp.close();
	}
	
}
