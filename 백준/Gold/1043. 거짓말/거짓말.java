import java.io.*;
import java.util.*;

public class Main {
    static int[] myLeader, truth;
    static List<int[]> parties;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        myLeader = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            myLeader[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        truth = new int[K];
        if (K > 0) {
            int truthLeader = Integer.parseInt(st.nextToken());
            truth[0] = truthLeader;
            for (int i = 1; i < K; i++) {
                truth[i] = Integer.parseInt(st.nextToken());
                myLeader[truth[i]] = truthLeader;

            }
        }

        parties = new ArrayList<>();
        int size, leader, mate;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());

            parties.add(new int[size]);
            leader = Integer.parseInt(st.nextToken());
            parties.get(i)[0] = leader;

            for (int j = 1; j < size; j++) {
                mate = Integer.parseInt(st.nextToken());
                parties.get(i)[j] = mate;
                union(leader, mate);
            }
        }

        int partyLeader;
        next:
        for (int i = 0; i < M; i++) {
            partyLeader = parties.get(i)[0];

            for (int j = 0; j < truth.length; j++) {
                if (findLeader(partyLeader) == findLeader(truth[j])) {
                    continue next;
                }
            }

            result++;
        }

        System.out.println(result);
    }

    public static void union(int a, int b) {
        int aLeader = findLeader(a);
        int bLeader = findLeader(b);

        if (aLeader != bLeader) {
            myLeader[bLeader] = aLeader;
        }
    }

    public static int findLeader(int mate) {
        if (mate != myLeader[mate]) {
            myLeader[mate] = findLeader(myLeader[mate]);
        }

        return myLeader[mate];
    }
}
