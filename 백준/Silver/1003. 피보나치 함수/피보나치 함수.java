import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		int N;
		int[] fibo;
		
		for(int test = 1; test <= T; test++) {
			N = Integer.parseInt(br.readLine());
			
			fibo = new int[N+1];
			
			if(N < 2) {
				fibo[N] = 1;
			} else {
				fibo[N] = 1;
				fibo[N-1] = 1;
				
				for(int i = N-2; i >= 1; i--) {
					fibo[i] = fibo[i+1] + fibo[i+2];
				}
				
				fibo[0] = fibo[2];				
			}
			
			System.out.println(fibo[0] + " " + (N == 0? 0 : fibo[1]));
		}
	}
}
