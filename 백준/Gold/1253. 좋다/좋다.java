import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int now = 0;
        int i, j;
        while (now < N) {
            i = 0;
            j = N - 1;

            while (i < j) {
                if (numbers[i] + numbers[j] == numbers[now]) {
                    if (i != now && j != now) {
                        result++;
                        break;
                    }else if(i == now){
                        i++;
                    }else if(j == now){
                        j--;
                    }
                } else if (numbers[i] + numbers[j] > numbers[now]) {
                    j--;
                } else {
                    i++;
                }
            }

            now++;
        }

        System.out.println(result);
    }
}