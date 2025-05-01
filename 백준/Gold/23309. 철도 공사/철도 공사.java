import java.io.*;
import java.util.*;

public class Main {
    static int[] prevStation, nextStation;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        prevStation = new int[1000001];
        nextStation = new int[1000001];

        int prev = 0, now;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            now = Integer.parseInt(st.nextToken());

            nextStation[prev] = now;
            prevStation[now] = prev;

            prev = now;
        }
        now = nextStation[0];
        nextStation[prev] = now;
        prevStation[now] = prev;

        String info;
        int i, j;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            info = st.nextToken();

            i = Integer.parseInt(st.nextToken());

            switch (info) {
                case "BN":
                    j = Integer.parseInt(st.nextToken());
                    caseBN(i, j);
                    break;
                case "BP":
                    j = Integer.parseInt(st.nextToken());
                    caseBP(i, j);
                    break;
                case "CN":
                    caseCN(i);
                    break;
                case "CP":
                    caseCP(i);
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    public static void caseBN(int station, int newStation) {
        int next = nextStation[station];

        sb.append(next).append("\n");

        prevStation[next] = newStation;
        prevStation[newStation] = station;
        nextStation[station] = newStation;
        nextStation[newStation] = next;
    }

    public static void caseBP(int station, int newStation) {
        int prev = prevStation[station];

        sb.append(prev).append("\n");

        prevStation[station] = newStation;
        prevStation[newStation] = prev;
        nextStation[prev] = newStation;
        nextStation[newStation] = station;
    }

    public static void caseCN(int station) {
        int next = nextStation[station];

        sb.append(next).append("\n");

        prevStation[nextStation[next]] = station;
        nextStation[station] = nextStation[next];
    }

    public static void caseCP(int station) {
        int prev = prevStation[station];

        sb.append(prev).append("\n");

        prevStation[station] = prevStation[prev];
        nextStation[prevStation[prev]] = station;
    }
}
