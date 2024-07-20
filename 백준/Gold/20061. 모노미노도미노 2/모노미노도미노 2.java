import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int t, score, tile;
	static boolean[][] green, blue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 점수 저장 변수 score, 타일 수 저장 변수 tile
		score = 0;
		tile = 0;
		// green, blue 타일 visited 배열
		green = new boolean[6][4];
		blue = new boolean[4][6];
		
		int x, y;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			// 타일 삽입
			insert(0, x, y);
			insert(1, x, y);
			
			// 0~1 행에 타일이 있는지 체크
			bonus(0);
			bonus(1);
			
		}
		
		sb.append(score).append("\n");
		sb.append(tile).append("\n");
		
		System.out.println(sb);
	}
	
	// color == 0 이 green
	// color == 1 이 blue
	// 타일 삽입 메서드
	public static void insert(int color, int x, int y) {
		// 삽입
		boolean check = false;
		int num = 5;
		
		in: while(true) {
			// 가장 위쪽(green)(또는 가장 왼쪽(blue))에 차 있는 위치 확인
			for(int i = 0; i < 6; i++) {
				if(color == 0) { // green
					if(green[i][y]) {
						if(num > i-1) num = i-1;
						break;
					}
				} else { // blue
					if(blue[x][i]) {
						if(num > i-1) num = i-1;
						break;
					}
				}
			}
			
			if(color == 0) { // green
				// 만약 1 * 2 사이즈면, y의 위치를 2번 확인해야 함
				if(!check && t == 2) {
					y++;
					check = true;
					continue in;
				}
				
				// 타일 위치 저장(방문처리)
				tile++;
				green[num][y] = true;
				if(t == 2) { // 1*2 사이즈는, 열 이동해서 true 처리
					tile++;
					green[num][y-1] = true;
				} else if(t == 3) { // 2*1 사이즈는, 행 이동해서 true 처리
					tile++;
					green[num-1][y] = true;
				}
			} else { // blue
				// 만약 2 * 1 사이즈면, y의 위치를 2번 확인해야 함
				if(!check && t == 3) {
					x++;
					check = true;
					continue in;
				}
				
				tile++;
				blue[x][num] = true;
				if(t == 2) { // 1*2 사이즈는, 행 이동해서 true 처리
					tile++;
					blue[x][num-1] = true;
				} else if(t == 3) { // 2*1 사이즈는, 열 이동해서 true 처리
					tile++;
					blue[x-1][num] = true;
				}
			}
			break in;
		}
		
		// 삭제할 행/열 있는지 확인
		delete(color);
		
	}
	
	public static void delete(int color) {
		next: for(int i = 5; i >= 0; i--) {
			for(int j = 0; j < 4; j++) {
				if(color == 0) {
					if(!green[i][j]) { // 중간에 하나라도 안 차있으면 다음 행/열 확인
						continue next;
					}
				}else if(color == 1) {
					if(!blue[j][i]) { // 중간에 하나라도 안 차있으면 다음 행/열 확인
						continue next;
					}
				}
			}
			
			// 행 삭제 처리
			// 여기로 넘어오면 한 줄(행/열)이 true인 것
			if(color == 0) {
				for(int j = 0; j < 4; j++) {
					tile--;
					green[i][j] = false;
				}
			} else if(color == 1) {
				for(int j = 0; j < 4; j++) {
					tile--;
					blue[j][i] = false;
				}
			}
			
			// 점수 추가 및 타일 이동 작업 실행
			score++;
			move(color, i, 1);
			i++;
		}
		
	}
	
	// 0~1 행에 타일 있는지 확인
	public static void bonus(int color) {
		int count = 0;
		
		next: for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(color == 0) {
					if(green[i][j]) {
						count++;
						for(int k = 0; k < 4; k++) {
							if(green[6-count][k]) tile--;
							green[6-count][k] = false;
						}
						continue next;
					}
				}else if(color == 1) {
					if(blue[j][i]) {
						count++;
						for(int k = 0; k < 4; k++) {
							if(blue[k][6-count]) tile--;
							blue[k][6-count] = false;
						}
						continue next;
					}
				}
			}
		}
		
		if(count != 0) {
			move(color, 6-count, count);
		}
	}
	
	// 타일 이동 메서드
	// 파라미터 : color - 색깔 구분 / last - 행이 비어있는 위치 / count - 삭제된 행/열 수(bonus 시 사용하기 위함)
	public static void move(int color, int last, int count) {
		// 삭제된 행/열 위/왼쪽 부터 옮기기 진행
		for(int i = last-1; i >= 0; i--) {
			for(int j = 0; j < 4; j++) {
				if(color == 0) {
					if(green[i][j]) {
						// 행을 아래로 한 칸씩 아래로 이동
						green[i][j] = false;
						green[i+count][j] = true;
					}
				}else if(color == 1) {
					if(blue[j][i]) {
						// 열을 오른쪽으로 한 칸씩 이동
						blue[j][i] = false;
						blue[j][i+count] = true;
					}
				}
			}
		}
	}
	
}
