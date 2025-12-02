import java.io.*;

public class Main {
	static int[] docking;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		docking = new int[G + 1];
		for(int i = 1; i <= G; i++) {
			docking[i] = i;
		}
		
		int result = 0;
		
		int gate;
		for(int i = 0; i < P; i++) {
			gate = Integer.parseInt(br.readLine());

			if(!checkDocking(gate)) break;
			
			result++;
		}
		
		System.out.println(result);
	}
	
	public static boolean checkDocking(int gate) {
		int dockingNum = findDockingLoc(gate);
		if(dockingNum <= 0) return false;
		
		return true;
	}
	
	public static int findDockingLoc(int gate) {
		if(docking[gate] == gate) {
			docking[gate] = gate - 1;
			return gate;
		}
		
		docking[gate] = findDockingLoc(docking[gate]);
		
		if(docking[gate] <= 0) {
			docking[gate] = 0;
		}
		
		return docking[gate];
	}
}
