import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[N+1];
		visited[0] = true;
		visited[1] = true;
		
        // 에라토스테네스의 체
        // 주어진 수의 루트 값까지만 체크하면 된다
		for(int i = 2; i <= Math.sqrt(N); i++) {
			int num = 2;
			while(num*i <= N) {
				visited[(num++)*i] = true;
							}
		}
		
		for(int i = M; i <= N; i++) {
			if(!visited[i]) System.out.println(i);
		}
	}
}
