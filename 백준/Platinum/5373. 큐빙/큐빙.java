import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[][][] cube = new char[6][3][3];
	static int[] flatinfo = new int[4];
	static int[][] rcinfo = new int[4][2];
	static int[] odinfo = new int[4];
	static char[][] tmp = new char[4][3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			for (int i = 0; i < 3; i++) {
				Arrays.fill(cube[0][i], 'w');
				Arrays.fill(cube[1][i], 'r');
				Arrays.fill(cube[2][i], 'g');
				Arrays.fill(cube[3][i], 'y');
				Arrays.fill(cube[4][i], 'o');
				Arrays.fill(cube[5][i], 'b');
			}

			int N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				String str = st.nextToken();

				testcase(str.charAt(0), str.charAt(1));
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(cube[0][i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void testcase(char flat, char dir) {
		if (flat == 'U') {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 2;
			flatinfo[1] = 4;
			flatinfo[2] = 5;
			flatinfo[3] = 1;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 0;
			rcinfo[0][1] = 0;
			rcinfo[1][0] = 0;
			rcinfo[1][1] = 0;
			rcinfo[2][0] = 0;
			rcinfo[2][1] = 0;
			rcinfo[3][0] = 0;
			rcinfo[3][1] = 0;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			if (dir == '+') {
				odinfo[0] = 1;
				odinfo[1] = 1;
				odinfo[2] = 1;
				odinfo[3] = 1;
			} else {
				odinfo[0] = 1;
				odinfo[1] = 1;
				odinfo[2] = 1;
				odinfo[3] = 1;
			}

			turn(dir);

			// 본인 회전
			flatturn(0, dir);
		} else if (flat == 'F') {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 2;
			flatinfo[1] = 0;
			flatinfo[2] = 5;
			flatinfo[3] = 3;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 2;
			rcinfo[0][1] = 1;
			rcinfo[1][0] = 2;
			rcinfo[1][1] = 0;
			rcinfo[2][0] = 0;
			rcinfo[2][1] = 1;
			rcinfo[3][0] = 0;
			rcinfo[3][1] = 0;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			if (dir == '+') {
				odinfo[0] = 0;
				odinfo[1] = 1;
				odinfo[2] = 0;
				odinfo[3] = 1;
			} else {
				odinfo[0] = 1;
				odinfo[1] = 0;
				odinfo[2] = 1;
				odinfo[3] = 0;
			}

			turn(dir);

			// 본인 회전
			flatturn(1, dir);

		} else if (flat == 'L') {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 4;
			flatinfo[1] = 0;
			flatinfo[2] = 1;
			flatinfo[3] = 3;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 2;
			rcinfo[0][1] = 1;
			rcinfo[1][0] = 0;
			rcinfo[1][1] = 1;
			rcinfo[2][0] = 0;
			rcinfo[2][1] = 1;
			rcinfo[3][0] = 0;
			rcinfo[3][1] = 1;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			if (dir == '+') {
				odinfo[0] = 0;
				odinfo[1] = 1;
				odinfo[2] = 1;
				odinfo[3] = 0;
			} else {
				odinfo[0] = 0;
				odinfo[1] = 0;
				odinfo[2] = 1;
				odinfo[3] = 1;
			}

			turn(dir);

			// 본인 회전
			flatturn(2, dir);
		} else if (flat == 'D') {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 2;
			flatinfo[1] = 1;
			flatinfo[2] = 5;
			flatinfo[3] = 4;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 2;
			rcinfo[0][1] = 0;
			rcinfo[1][0] = 2;
			rcinfo[1][1] = 0;
			rcinfo[2][0] = 2;
			rcinfo[2][1] = 0;
			rcinfo[3][0] = 2;
			rcinfo[3][1] = 0;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			odinfo[0] = 1;
			odinfo[1] = 1;
			odinfo[2] = 1;
			odinfo[3] = 1;

			turn(dir);

			// 본인 회전
			flatturn(3, dir);
		} else if (flat == 'B') {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 5;
			flatinfo[1] = 0;
			flatinfo[2] = 2;
			flatinfo[3] = 3;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 2;
			rcinfo[0][1] = 1;
			rcinfo[1][0] = 0;
			rcinfo[1][1] = 0;
			rcinfo[2][0] = 0;
			rcinfo[2][1] = 1;
			rcinfo[3][0] = 2;
			rcinfo[3][1] = 0;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			if (dir == '+') {
				odinfo[0] = 1;
				odinfo[1] = 0;
				odinfo[2] = 1;
				odinfo[3] = 0;
			} else {
				odinfo[0] = 0;
				odinfo[1] = 1;
				odinfo[2] = 0;
				odinfo[3] = 1;
			}

			turn(dir);

			// 본인 회전
			flatturn(4, dir);
		} else {
			// 행/열이 바뀌는 순서
			// idx0은 idx1한테, idx1은 idx2한테 넘김
			flatinfo[0] = 1;
			flatinfo[1] = 0;
			flatinfo[2] = 4;
			flatinfo[3] = 3;

			// 행/열 전달 정보, 번호와 행(0)인지 열(1)인지 전달
			rcinfo[0][0] = 2;
			rcinfo[0][1] = 1;
			rcinfo[1][0] = 2;
			rcinfo[1][1] = 1;
			rcinfo[2][0] = 0;
			rcinfo[2][1] = 1;
			rcinfo[3][0] = 2;
			rcinfo[3][1] = 1;

			// 전달 방향 정보
			// 역순(0) , 정(1)
			if (dir == '+') {
				odinfo[0] = 1;
				odinfo[1] = 0;
				odinfo[2] = 0;
				odinfo[3] = 1;
			} else {
				odinfo[0] = 1;
				odinfo[1] = 1;
				odinfo[2] = 0;
				odinfo[3] = 0;
			}

			turn(dir);

			// 본인 회전
			flatturn(5, dir);
		}
	}

	public static void turn(char dir) {
		int flatnum, rownum, colnum;
		// tmp는 임시배열
		// 돌아가는 행/열에 대한 정보를 임시 저장
		for (int i = 0; i < 4; i++) {
			rownum = rcinfo[i][1] == 0 ? rcinfo[i][0] : -1;
			colnum = rcinfo[i][1] == 1 ? rcinfo[i][0] : -1;

			if (rownum != -1) {
				for (int j = 0; j < 3; j++) {
					tmp[i][j] = cube[flatinfo[i]][rownum][j];
				}
			} else if (colnum != -1) {
				for (int j = 0; j < 3; j++) {
					tmp[i][j] = cube[flatinfo[i]][j][colnum];
				}
			}
		}

		// 조건에 따라 돌아가면서 행/열 저장(큐브 돌리기)
		for (int i = 0; i < 4; i++) {
			if (dir == '+') {
				flatnum = i - 1 < 0 ? 3 : i - 1;
				rownum = rcinfo[i][1] == 0 ? rcinfo[i][0] : -1;
				colnum = rcinfo[i][1] == 1 ? rcinfo[i][0] : -1;
			} else {
				flatnum = i + 1 > 3 ? 0 : i + 1;
				rownum = rcinfo[i][1] == 0 ? rcinfo[i][0] : -1;
				colnum = rcinfo[i][1] == 1 ? rcinfo[i][0] : -1;
			}

			if (odinfo[flatnum] == 0) { // 역순
				if (rownum != -1) {
					for (int j = 0; j < 3; j++) {
						cube[flatinfo[i]][rownum][j] = tmp[flatnum][2 - j];
					}
				} else if (colnum != -1) {
					for (int j = 0; j < 3; j++) {
						cube[flatinfo[i]][j][colnum] = tmp[flatnum][2 - j];
					}
				}
			} else { // 정
				if (rownum != -1) {
					for (int j = 0; j < 3; j++) {
						cube[flatinfo[i]][rownum][j] = tmp[flatnum][j];
					}
				} else if (colnum != -1) {
					for (int j = 0; j < 3; j++) {
						cube[flatinfo[i]][j][colnum] = tmp[flatnum][j];
					}
				}
			}
		}

	}

	// 기준면 회전
	public static void flatturn(int flat, char dir) {
		// 임시 배열에 0행/열, 2행/열 값 저장
		for (int i = 0; i < 3; i++) {
			tmp[0][i] = cube[flat][i][0];
			tmp[1][i] = cube[flat][0][i];
			tmp[2][i] = cube[flat][i][2];
			tmp[3][i] = cube[flat][2][i];
		}

		if (dir == '+') { // 시계방향
			for (int i = 0; i < 3; i++) {
				cube[flat][0][i] = tmp[0][2 - i];
				cube[flat][i][0] = tmp[3][i];
				cube[flat][i][2] = tmp[1][i];
				cube[flat][2][i] = tmp[2][2 - i];
			}
		} else { // 반시계방향
			for (int i = 0; i < 3; i++) {
				cube[flat][0][i] = tmp[2][i];
				cube[flat][i][0] = tmp[1][2 - i];
				cube[flat][i][2] = tmp[3][2 - i];
				cube[flat][2][i] = tmp[0][i];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(Arrays.toString(cube[i][j]));
			}
			System.out.println();
		}
	}
}
