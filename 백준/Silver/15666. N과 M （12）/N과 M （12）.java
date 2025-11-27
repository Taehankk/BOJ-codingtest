import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] nums, selectNums;
	static List<String> resultList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		selectNums = new int[M];
		resultList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		setSequence(0, 0);
		
		for(String answer : resultList) {
			System.out.println(answer);
		}
	}
	
	public static void setSequence(int idx, int numIdx) {
		if(idx >= M) {
			Arrays.sort(selectNums);
			String result = Arrays.toString(selectNums)
					.replace("[", "")
					.replace("]", "")
					.replace(",", "");
			
			if(!resultList.contains(result)) {
				resultList.add(result);
			}
			
			return;
		}
		
		for(int i = numIdx; i < N; i++) {
			selectNums[idx] = nums[i];
			setSequence(idx + 1, i);
		}
	}
}
