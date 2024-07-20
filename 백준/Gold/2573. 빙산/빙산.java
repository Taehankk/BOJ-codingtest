import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int matrix[][];
	static boolean visited[][];
	static Queue<int[]> queue;
	static int N, M, result, size, count, day;
	static StringTokenizer token;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		token = new StringTokenizer(reader.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		matrix = new int[N][M];
		queue = new LinkedList<>();
		visited = new boolean[N][M];
		day = 0;

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(reader.readLine());
			for (int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		// 2등분 될때까지 수행
		while (result < 2) {
			search(); // 주변 물 탐색

			if (queue.isEmpty()) {
				break;
			}

            size = queue.size();
			melt(); // 얼음 녹이기
			
			
			for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++){
                    visited[i][j] = false;
                }
			}
            
            size = queue.size();
			result = 0;
			for (int i = 0; i < size; i++) {
				int[] pos = queue.poll();
				if (!visited[pos[0]][pos[1]]) {
					dfs(pos[0], pos[1]); // 등분 파악
					result++;
				}
			}
			day++;
		}

		if (result < 2) {
			day = 0;
		}

		System.out.println(day);
	}

	// 얼음 주변 탐색
	private static void search() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				count = 0;
				if (matrix[i][j] > 0) {
					for (int k = 0; k < 4; k++) {
						int row = i + dr[k];
						int col = j + dc[k];
						if (row >= 0 && row < N && col >= 0 && col < M && matrix[row][col] == 0) {
							count++;
						}
					}
					queue.offer(new int[] { i, j, count });
				}
			}
		}
	}

	// 얼음 녹이기
	public static void melt() {
		int[] arr;

        for(int i = 0; i < size; i++){
            arr = queue.poll();
		    int x = arr[0];
			int y = arr[1];
			int count = arr[2];

			if (matrix[x][y] <= count) {
			    matrix[x][y] = 0;
			} else {
			    matrix[x][y] -= count;
                queue.offer(arr);
			}
            
            
        }
			
		
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;

		for (int k = 0; k < 4; k++) {
			int row = x + dr[k];
			int col = y + dc[k];
			if (row >= 0 && row < N && col >= 0 && col < M && !visited[row][col] && matrix[row][col] > 0) {
				dfs(row, col);
			}
		}
	}
}