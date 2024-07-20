import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, init_fish, shark_size, time;
	static int[][] map;
	static boolean[][] visited;
	static int[] fish;
	static Queue<Fish> queue, queue2;
	static int[] baby;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

    // 물고기 위치, 이동 수 저장 클래스
	static class Fish implements Comparable<Fish> {
		int row;
		int col;
		int move;

		Fish(int row, int col, int move) {
			this.row = row;
			this.col = col;
			this.move = move;
		}

		@Override
		public String toString() {
			return "Fish [row=" + row + ", col=" + col + ", move=" + move + "]";
		}

        // 문제 조건에 맞게 우선순위 정렬
		@Override
		public int compareTo(Fish o) {
			if (this.move == o.move) {
				if (this.row == o.row) {
					return this.col - o.col;
				}
				return this.row - o.row;
			}
			return this.move - o.move;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        // map 크기 N
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
        // 방문배열
		visited = new boolean[N][N];
        // 아기상어 위치 저장 배열
		baby = new int[2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

                // 초기 아기상어 위치 저장
				if (map[i][j] == 9) {
					baby[0] = i;
					baby[1] = j;
					map[i][j] = 0;
				}
			}
		}

        // 아기상어의 크기 저장
		shark_size = 2;
        // 먹은 물고기 수
		int eat = 0;
        // 흐른 시간
		time = 0;
		
        // 물고기를 먹을 수 있는 경우에만 while문 진행
        // 못 먹으면 바로 빠져나옴
		while (findRoute()) {
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			eat++;
			if (eat == shark_size) {
				shark_size++;
				eat = 0;
			}
		}

        // 최종 결과 출력
		System.out.println(time);
	}

    // 물고기를 먹을 수 있는지 확인하는 메서드
	public static boolean findRoute() {
        // 아기 상어의 초기 위치는 0으로 바꿈
		map[baby[0]][baby[1]] = 0;
		visited[baby[0]][baby[1]] = true;

        // 아기상어의 위치를 확인하는 'check' queue
		Queue<Fish> check = new PriorityQueue<>();
		check.offer(new Fish(baby[0], baby[1], 0));

		int nr, nc, nm = 0;
		Fish move;
		while (!check.isEmpty()) {
			move = check.poll();
			
            // 도달한 위치에 물고기가 먹을 수 있는 물고기면 먹음
			if(map[move.row][move.col] != 0 && map[move.row][move.col] < shark_size) {
				time += move.move;
				map[move.row][move.col] = 0;
				baby[0] = move.row;
				baby[1] = move.col;
				return true;
			}

            // 사방탐색 하면서, 아기상어가 이동할 수 있으면 이동
            // ('check' queue에 offer)
			for (int i = 0; i < 4; i++) {
				nr = move.row + dr[i];
				nc = move.col + dc[i];
				nm = move.move;

				if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
					visited[nr][nc] = true;

					if (map[nr][nc] > shark_size) {
						continue;
					}
					
					check.offer(new Fish(nr, nc, nm + 1));
				}

			}
		}

		return false;

	}
}
