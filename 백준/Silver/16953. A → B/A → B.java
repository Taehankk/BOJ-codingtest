import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		
		Queue<long[]> queue = new LinkedList<>();
		queue.offer(new long[] {A, 0});
		
		long result = Integer.MAX_VALUE;
		long[] check;
		while(!queue.isEmpty()) {
			check = queue.poll();

			if(check[0] * 10 + 1 == B || check[0] * 2 == B) {
				if(result > check[1] + 2) {
					result = check[1] + 2;					
				}
				continue;
			}
			
			if(check[0] * 10 + 1 < B) {
				queue.offer(new long[] {check[0] * 10 + 1, check[1] + 1});
			}
			
			if(check[0] * 2 < B) {
				queue.offer(new long[] {check[0] * 2, check[1] + 1});
			}
		}
		
		if(result == Integer.MAX_VALUE) result = -1;
		
		System.out.println(result);
	}
}
