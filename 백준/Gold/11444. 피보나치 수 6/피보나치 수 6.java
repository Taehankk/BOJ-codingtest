import java.io.*;
import java.util.*;

public class Main {
	static long[][] MATRIX = new long[][] {{1, 1}, {1, 0}};
	static long DIV = 1000000007;
	static Map<Long, long[][]> fiboArrMap;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fiboArrMap = new HashMap<>();
        
        fiboArrMap.put(0l, new long[][] {{1, 0}, {0, 1}});
        fiboArrMap.put(1l, MATRIX);

        long N = Long.parseLong(br.readLine());
        long[][] result = findFiboMatrix(N);
        System.out.println(result[0][1]);
    }
    
    public static long[][] findFiboMatrix(long idx) {
    	if(fiboArrMap.containsKey(idx) ) {
    		return fiboArrMap.get(idx);
    	}

    	long half = idx / 2;
    	long[][] halfMatrix = findFiboMatrix(half);
    	long[][] newFiboMatrix = multiply(halfMatrix, halfMatrix);
    	
    	if(idx % 2 == 1l) {
    		newFiboMatrix = multiply(newFiboMatrix, MATRIX);
    	}
    	
    	fiboArrMap.put(idx, newFiboMatrix);
    	
    	return newFiboMatrix;
    }
    
    public static long[][] multiply(long[][] arr1, long[][] arr2) {
    	long[][] newArr = new long[2][2];
    	
    	newArr[0][0] = ((arr1[0][0] * arr2[0][0]) % DIV + (arr1[0][1] * arr2[1][0]) % DIV) % DIV;
    	newArr[0][1] = ((arr1[0][0] * arr2[0][1]) % DIV + (arr1[0][1] * arr2[1][1]) % DIV) % DIV;
    	newArr[1][0] = ((arr1[1][0] * arr2[0][0]) % DIV + (arr1[1][1] * arr2[1][0]) % DIV) % DIV;
    	newArr[1][1] = ((arr1[1][0] * arr2[0][1]) % DIV + (arr1[1][1] * arr2[1][1]) % DIV) % DIV;
    	
    	return newArr;
    }
}
