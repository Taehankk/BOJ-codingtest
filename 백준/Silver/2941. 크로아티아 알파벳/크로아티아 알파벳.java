import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().toString();
		int count = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'c') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == '=') {
					i++;
				}else if(str.charAt(i+1) == '-') {
					i++;
				}
				count++;
			}else if(str.charAt(i) == 'd') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == '-') {
					i++;
				}else if(str.charAt(i+1) == 'z') {
					if(i == str.length()-2) {
						count += 2;
						break;
					}
					if(str.charAt(i+2) == '=') {
						i += 2;
					}else {
						count ++;
						i++;
					}					
				}
				count++;
			}else if(str.charAt(i) == 'l') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == 'j') {					
					i++;
				}
				count++;
			}else if(str.charAt(i) == 'n') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == 'j'){					
					i++;
				}
				count++;
			}else if(str.charAt(i) == 's') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == '=') {
					i++;
				}
				count++;
			}else if(str.charAt(i) == 'z') {
				if(i == str.length()-1) {
					count++;
					break;
				}
				if(str.charAt(i+1) == '=') {
					i++;
				}
				count++;		
			} else {
				count++;
			}
		}
		
		System.out.println(count);
				
	}
}
