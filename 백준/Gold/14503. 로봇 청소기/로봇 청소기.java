import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dc = {0, 1, 0, -1};

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] loc = new int[]{r, c, d};
        int nr = 0, nc = 0;
        int idx = -1;
        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = -1;
                result++;
            }

            loc = findDirty(r, c, d);

            if (loc[2] == -1) {
                break;
            }

            r = loc[0];
            c = loc[1];
            d = loc[2];
        }

        System.out.println(result);
    }

    static int[] findDirty(int r, int c, int d) {
        int nr = 0, nc = 0, nd = d == 0 ? 3 : d - 1;

        for (int i = 0; i < 4; i++) {
            nr = r + dr[nd];
            nc = c + dc[nd];

            if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                if (map[nr][nc] == 0) {
                    return new int[]{nr, nc, nd};
                }
            }

            nd = nd == 0 ? 3 : nd - 1;
        }

        nr = r + dr[(d + 2) % 4];
        nc = c + dc[(d + 2) % 4];
        if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] <= 0) {
            return new int[]{nr, nc, d};
        } else {
            return new int[]{nr, nc, -1};
        }
    }
}