import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 스위치 개수 저장
		int switch_count = Integer.parseInt(br.readLine());
		// 스위치 상태 저장, index 1번부터 스위치
		int[] switch_oo = new int[switch_count+1];
		
		st = new StringTokenizer(br.readLine());
		
		// 스위치 초기 상태 저장
		for(int i = 1; i < switch_oo.length; i++) {
			switch_oo[i] = Integer.parseInt(st.nextToken());
		}
				
		// 학생 수 저장
		int student_count = Integer.parseInt(br.readLine());
		// 성별 파악
		int gender;
		
		for(int i = 0; i < student_count; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			
			// 남학생일 경우
			if(gender == 1) {
				// 남학생일 때 받은 번호 저장
				int m_num = Integer.parseInt(st.nextToken());
				// 남학생일 때 배수로 더하기 위한 변수
				int multiple = m_num;
				
				// 배수인 번호에서 스위치 상태 변경
				while(m_num < switch_count+1) {
					switch_oo[m_num] = (switch_oo[m_num]+1)%2;
					m_num += multiple;
				}
			} else { // 여학생일 때
				// 왼쪽, 오른쪽으로 갈 변수
				int left = Integer.parseInt(st.nextToken());
				// 오른쪽 변수는 미리 한 칸 이동
				int right = left+1;
				
				// 처음 받은 번호의 스위치 변경
				switch_oo[left] = (switch_oo[left]+1)%2;
				
				// 왼쪽 한 칸 이동
				left -= 1;
				
				// 좌우 대칭이 아닐때까지 돌면서 스위치 상태 변경
				while((left > 0) && (right < (switch_count+1)) && (switch_oo[left] == switch_oo[right])) {
					switch_oo[left] = (switch_oo[left]+1) % 2;
					switch_oo[right] = (switch_oo[right]+1) % 2;
					
					left--;
					right++;
				}
			}
		}
		
		// 20개씩 출력
		for(int i = 1; i < switch_oo.length; i++) {
			System.out.print(switch_oo[i] + " ");
			if(i % 20 == 0) {
				System.out.println();
			}			
		}
		
	}
}
