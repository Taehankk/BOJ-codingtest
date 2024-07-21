import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] result = new int[11];
		result[0] = result[1] = 1;
		result[2] = 2;
		
		for(int i = 3; i < result.length; i++) {
			result[i] = result[i-1] + result[i-2] + result[i-3];
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int num = Integer.parseInt(br.readLine());
			
			System.out.println(result[num]);
		}
	}
}
