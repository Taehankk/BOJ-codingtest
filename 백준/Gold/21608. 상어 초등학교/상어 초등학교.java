import java.io.*;
import java.util.*;

public class Main {
    static class Shark {
        int[] friends;
        int[] seat = new int[] {-1, -1};

        Shark(int[] friends) {
            this.friends = friends;
        }

        public String toString() {
            return "Friend : " + Arrays.toString(friends) + "\n Seat : " + Arrays.toString(seat);
        }
    }

    static int N;
    static int[][] seat;
    static Map<Integer, Shark> sharks;
    static int[] dr = new int[] {-1, 0, 0, 1};
    static int[] dc = new int[] {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        seat = new int[N][N];
        Queue<Integer> seatOrder = new LinkedList<>();
        sharks = new HashMap<>();

        int idx, friend;
        int[] friends;
        for(int i = 0; i < N* N; i++) {
            friends = new int[4];
            st = new StringTokenizer(br.readLine());

            idx = Integer.parseInt(st.nextToken());

            for(int j = 0; j < 4; j++) {
                friend = Integer.parseInt(st.nextToken());
                friends[j] = friend;
            }

            seatOrder.offer(idx);
            sharks.put(idx, new Shark(friends));
        }

        Shark now;
        while(!seatOrder.isEmpty()) {
            idx = seatOrder.poll();
            now = sharks.get(idx);

            findBlankSeat(idx, now);
        }

        int result = calScore();
        
        System.out.println(result);
    }

    public static void findBlankSeat(int idx, Shark now) {
        int maxFriendCnt = -1;
        int maxBlankCnt = -1;

        int[] result;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(seat[i][j] == 0) {
                    result = findNearSeat(i, j, now.friends);

                    if(result[0] > maxFriendCnt) {
                        maxFriendCnt = result[0];
                        maxBlankCnt = result[1];

                        now.seat[0] = i;
                        now.seat[1] = j;
                    } else if(result[0] == maxFriendCnt && result[1] > maxBlankCnt) {
                        maxBlankCnt = result[1];

                        now.seat[0] = i;
                        now.seat[1] = j;
                    }
                }
            }
        }

        seat[now.seat[0]][now.seat[1]] = idx;
    }

    public static int[] findNearSeat(int r, int c, int[] friends) {
        int friendCnt = 0;
        int blankCnt = 0;

        int nr, nc;
        for(int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];

            if(0 <= nr && nr < N && 0 <= nc && nc < N ) {
                if(seat[nr][nc] != 0) {
                    for(int j = 0; j < 4; j++) {
                        if(seat[nr][nc] == friends[j]) {
                            friendCnt++;
                            break;
                        }
                    }
                } else {
                    blankCnt++;
                }
            }
        }

        return new int[] {friendCnt, blankCnt};
    }

    public static int calScore() {
        int result = 0;

        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt = findNearSeat(i, j, sharks.get(seat[i][j]).friends)[0];

                result += Math.pow(10, cnt-1);
            }
        }

        return result;
    }
}
