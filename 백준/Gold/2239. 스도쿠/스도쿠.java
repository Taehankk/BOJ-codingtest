import java.io.*;

public class Main {
    static int[][] sudoku;
    static boolean[][] visited;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sudoku = new int[9][9];

        // 0~8 : row, 9~17 : col, 18~26 : square;
        visited = new boolean[27][9];

        String line;
        for(int r = 0; r < 9; r++) {
            line = br.readLine();
            for(int c = 0; c < 9; c++) {
                sudoku[r][c] = line.charAt(c) - '0';
                if(sudoku[r][c] > 0) {
                    visited[r][sudoku[r][c] - 1] = true;
                    visited[c + 9][sudoku[r][c] - 1] = true;
                    visited[r / 3 * 3 + c / 3 + 18][sudoku[r][c] - 1] = true;
                }
            }
        }
        
        findBlank(0, 0);
    }

    static boolean findBlank(int nr, int nc) {
        for(int r = 0; r < 9; r++) {
            if(r < nr) continue;
            for(int c = 0; c < 9; c++) {
                if(r == nr && c < nc) continue;
                if(sudoku[r][c] == 0) {
                    return fillSudoku(r, c);
                }
            }
        }

        printResult();
        return true;
    }

    static boolean fillSudoku(int r, int c) {
        // System.out.println(r + " / " + c);
        for(int i = 1; i <= 9; i++) {
            if(!visited[r][i-1] && !visited[c + 9][i-1] && !visited[r / 3 * 3 + c / 3 + 18][i-1]) {
                sudoku[r][c] = i;
                visited[r][i-1] = true;
                visited[c + 9][i-1] = true;
                visited[r / 3 * 3 + c / 3 + 18][i-1] = true;

                if(findBlank(r, c)) return true;

                sudoku[r][c] = 0;
                visited[r][i-1] = false;
                visited[c + 9][i-1] = false;
                visited[r / 3 * 3 + c / 3 + 18][i-1] = false;
            }
        }

        return false;
    }

    static void printResult() {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String result;
        for(int r = 0; r < 9; r++) {
            result = "";
            for(int c = 0; c < 9; c++) {
                result += sudoku[r][c];
            }

            System.out.println(result);
        }

        System.exit(0);
    }
}