import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = i;
		}
		
		int a, b, result = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(hasCycle(a, b)) {
				result = i + 1;
				break;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean hasCycle(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		
		if(aParent == bParent) {
			return true;
		}else if(aParent > bParent) {
			parent[bParent] = aParent;
		} else {
			parent[aParent] = bParent;
		}
		
		return false;
	}
	
	public static int findParent(int num) {
		if(num != parent[num]) parent[num] = findParent(parent[num]);
		
		return parent[num];
	}
}
