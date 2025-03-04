import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int[] lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        answer = 1;
        int[] arr = new int[N];
        lis = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = arr[0];

        for (int i = 1; i < N; i++) {
            if (arr[i] > lis[answer - 1]) {
                lis[answer++] = arr[i];
            } else if (arr[i] < lis[answer - 1]) {
                binarySearch(arr[i], 0, answer - 1);
            }
        }

        System.out.println(answer);
    }

    public static void binarySearch(int num, int left, int right) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (lis[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }


        lis[left] = num;
    }
}