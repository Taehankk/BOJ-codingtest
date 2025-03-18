import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        int k = 0;
        TreeMap<Integer, Integer> pq;
        String cal;
        String type;
        int num, max;
        for (int t = 0; t < T; t++) {
            pq = new TreeMap<>();
            k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken();
                num = Integer.parseInt(st.nextToken());

                if (type.equals("I")) {
                    if (pq.containsKey(num)) {
                        pq.put(num, pq.get(num) + 1);
                    } else {
                        pq.put(num, 1);
                    }
                } else if (type.equals("D")) {
                    if (!pq.isEmpty()) {
                        if (num == 1) {
                            if (pq.lastEntry().getValue() == 1) {
                                pq.pollLastEntry();
                            } else {
                                pq.put(pq.lastEntry().getKey(), pq.lastEntry().getValue() - 1);
                            }
                        } else {
                            if (pq.firstEntry().getValue() == 1) {
                                pq.pollFirstEntry();
                            } else {
                                pq.put(pq.firstEntry().getKey(), pq.firstEntry().getValue() - 1);
                            }
                        }
                    }
                }
            }

            if (pq.isEmpty()) {
                bw.append("EMPTY");
            } else {
                max = pq.pollLastEntry().getKey();
                bw.append(max + " ");
                if (pq.isEmpty()) {
                    bw.append(max + "");
                } else {
                    bw.append(pq.pollFirstEntry().getKey() + "");
                }
            }
            bw.append("\n");

        }

        bw.flush();
        bw.close();
    }
}