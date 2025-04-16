import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dc = {0, 0, -1, 1};

    static int R, C, result;
    static char[][] board;
    static boolean[][] visited;
    static boolean[] alphabet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];
        alphabet = new boolean[26];

        String line = "";
        for (int i = 0; i < R; i++) {
            line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        result = 1;
        alphabet[board[0][0] - 'A'] = true;
        visited[0][0] = true;
        dfs(0, 0, 1);

        System.out.println(result);
    }

    public static void dfs(int r, int c, int cnt) {
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];

            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (!alphabet[board[nr][nc] - 'A']) {
                    alphabet[board[nr][nc] - 'A'] = true;
                    visited[nr][nc] = true;
                    dfs(nr, nc, cnt + 1);
                    alphabet[board[nr][nc] - 'A'] = false;
                    visited[nr][nc] = false;
                } else {
                    if (cnt > result) {
                        result = cnt;
                    }
                }
            }
        }
    }
}