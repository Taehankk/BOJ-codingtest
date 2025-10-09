import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = new int[] {0, -1, -1, -1, 0, 1, 1, 1};

    static int result;

    static class Fish {
        int idx;
        int dir;

        Fish(int idx, int dir) {
            this.idx = idx;
            this.dir = dir;
        }

        public String toString() {
            return "Fish" + idx + " " + dir + " ";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Fish[][] stage = new Fish[4][4];
        int[][] loc = new int[17][2];
        loc[0][0] = 0;
        loc[0][1] = 0;

        int idx, dir;
        for(int r = 0; r < 4; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < 4; c++) {
                idx = Integer.parseInt(st.nextToken());
                dir = Integer.parseInt(st.nextToken()) - 1;
                
                stage[r][c] = new Fish(idx, dir);
                loc[idx][0] = r;
                loc[idx][1] = c;
            }
        }

        Fish start = stage[0][0];
        loc[start.idx][0] = -1;
        loc[start.idx][1] = -1;
        result = start.idx;
        start.idx = 100 + start.idx;

        moveFish(stage, loc);

        System.out.println(result);
    }

    public static void moveFish(Fish[][] stageCopy, int[][] locCopy) {
        Fish[][] stage = new Fish[4][4];
        int[][] loc = new int[17][2];

        Fish copy;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                copy = stageCopy[i][j];
                stage[i][j] = new Fish(copy.idx, copy.dir);
            }
        }

        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < 2; j++) {
                loc[i][j] = locCopy[i][j];
            }
        }
        Fish now, change;
        int firstDir, nr, nc;

        next: for(int idx = 1; idx <= 16; idx++) {
            if(loc[idx][0] != -1) {
                now = stage[loc[idx][0]][loc[idx][1]];
                firstDir = now.dir;

                do {
                    nr = loc[idx][0] + dr[now.dir];
                    nc = loc[idx][1] + dc[now.dir];

                    if(0 <= nr && nr < 4 && 0 <= nc && nc < 4 && 0 <= stage[nr][nc].idx && stage[nr][nc].idx <= 16) {
                        change = stage[nr][nc];

                        stage[loc[idx][0]][loc[idx][1]] = change;
                        stage[nr][nc] = now;

                        if(change.idx > 0) {
                            loc[change.idx][0] = loc[idx][0];
                            loc[change.idx][1] = loc[idx][1];
                        }
                        loc[idx][0] = nr;
                        loc[idx][1] = nc;

                        continue next;
                    }
                    
                    now.dir = (now.dir + 1) % 8;
                } while(now.dir != firstDir);
            }
        }

        moveShark(stage, loc);
    }

    public static void moveShark(Fish[][] stageCopy, int[][] locCopy) {
        Fish[][] stage = new Fish[4][4];
        int[][] loc = new int[17][2];

        Fish copy;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                copy = stageCopy[i][j];
                stage[i][j] = new Fish(copy.idx, copy.dir);
            }
        }

        for(int i = 0; i < 17; i++) {
            for(int j = 0; j < 2; j++) {
                loc[i][j] = locCopy[i][j];
            }
        }

        Fish shark = stage[loc[0][0]][loc[0][1]];
        int sharkDir = shark.dir;

        Fish check;
        int nr, nc, befFishIdx, befSharkR, befSharkC;
        for(int step = 1; step <= 3; step++) {
            nr = loc[0][0] + dr[sharkDir] * step;
            nc = loc[0][1] + dc[sharkDir] * step;

            if(0 <= nr && nr < 4 && 0 <= nc && nc < 4) {
                if(stage[nr][nc].idx > 0) {
                    check = stage[nr][nc];

                    befFishIdx = check.idx;
                    befSharkR = loc[0][0];
                    befSharkC = loc[0][1];
                    sharkDir = shark.dir;              

                    shark.idx += check.idx;
                    if(shark.idx - 100 > result) {
                        result = shark.idx - 100;
                    }
                    
                    loc[0][0] = nr;
                    loc[0][1] = nc;
                    loc[check.idx][0] = -1;
                    loc[check.idx][1] = -1;

                    shark.dir = check.dir;
                    stage[nr][nc] = shark;
                    check.idx = 0;
                    stage[befSharkR][befSharkC] = check;

                    moveFish(stage, loc);

                    stage[nr][nc] = check;
                    stage[befSharkR][befSharkC] = shark;
                    check.idx = befFishIdx;
                    shark.idx -= befFishIdx;
                    shark.dir = sharkDir;
                    
                    loc[check.idx][0] = nr;
                    loc[check.idx][1] = nc;
                    loc[0][0] = befSharkR;
                    loc[0][1] = befSharkC;
                }
            } else {
                break;
            }
        }
    }
}