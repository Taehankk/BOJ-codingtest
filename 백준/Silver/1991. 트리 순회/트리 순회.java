import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, String[]> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		
		String node, child_l, child_r;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			node = st.nextToken();
			child_l = st.nextToken();
			child_r = st.nextToken();
			String[] child = new String[] {child_l, child_r};
			map.put(node, child);
		}
		
		preorder("A");
		System.out.println();
		inorder("A");
		System.out.println();
		postorder("A");
	}
	
	public static void preorder(String node) {
		if(node.equals(".")) {
			return;
		}
		
		System.out.print(node);			
		
		if(map.containsKey(node)) {
			preorder(map.get(node)[0]);			
		}
		
		if(map.containsKey(node)) {
			preorder(map.get(node)[1]);			
		}
	}
	
	public static void inorder(String node) {
		if(node.equals(".")) {
			return;
		}
				
		if(map.containsKey(node)) {
			inorder(map.get(node)[0]);			
		}
		
		System.out.print(node);			
		
		if(map.containsKey(node)) {
			inorder(map.get(node)[1]);			
		}
	}
	
	public static void postorder(String node) {
		if(node.equals(".")) {
			return;
		}		
		
		if(map.containsKey(node)) {
			postorder(map.get(node)[0]);			
		}
		
		if(map.containsKey(node)) {
			postorder(map.get(node)[1]);			
		}
		
		System.out.print(node);			
	}
}
