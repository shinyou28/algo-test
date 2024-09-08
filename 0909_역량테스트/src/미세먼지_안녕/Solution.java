package 미세먼지_안녕;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt(); // 행
        int C = sc.nextInt(); // 열
        int T = sc.nextInt(); // 시간
        
        int[][] arr = new int[R][C];
        int airTop = -1; // 공기청정기 윗부분 위치
        
        // 배열 입력 및 공기청정기 위치 확인
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == -1) {
                    airTop = i;
                }
            }
        }
        
        int airBottom = airTop + 1; // 공기청정기 아랫부분 위치
        
        // 확산 방향
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        for (int time = 0; time < T; time++) {
        	// 미세먼지 양 임시 저장
            int[][] tmp = new int[R][C];
            
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                	// 미세먼지가 있으면
                    if (arr[i][j] > 0) {
                        int amount = arr[i][j] / 5; // 확산될 미세먼지 양
                        int cnt = 0; // 확산될 방향 갯수
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if (nr >= 0 && nc >= 0 && nr < R && nc < C && arr[nr][nc] != -1) {
                                tmp[nr][nc] += amount;
                                cnt++;
                            }
                        }
                        // 확산되고 남아있는 미세먼지 양
                        tmp[i][j] += arr[i][j] - amount * cnt;
                    }
                }
            }
            
            // 공기청정기 위치 초기화
            tmp[airTop][0] = -1;
            tmp[airBottom][0] = -1;
            
            // 위쪽 공기청정기 - 반시계 방향
            for (int i = airTop - 1; i > 0; i--) {            	
            	tmp[i][0] = tmp[i - 1][0];
            }
            for (int j = 0; j < C - 1; j++) {            	
            	tmp[0][j] = tmp[0][j + 1];
            }
            for (int i = 0; i < airTop; i++) {            	
            	tmp[i][C - 1] = tmp[i + 1][C - 1];
            }
            for (int j = C - 1; j > 1; j--) {            	
            	tmp[airTop][j] = tmp[airTop][j - 1];
            }
            tmp[airTop][1] = 0;

            // 아래쪽 공기청정기 - 시계 방향
            for (int i = airBottom + 1; i < R - 1; i++) {            	
            	tmp[i][0] = tmp[i + 1][0];
            }
            for (int j = 0; j < C - 1; j++) {            	
            	tmp[R - 1][j] = tmp[R - 1][j + 1];
            }
            for (int i = R - 1; i > airBottom; i--) {            	
            	tmp[i][C - 1] = tmp[i - 1][C - 1];
            }
            for (int j = C - 1; j > 1; j--) {            	
            	tmp[airBottom][j] = tmp[airBottom][j - 1];
            }
            tmp[airBottom][1] = 0;
            
            // 최종 결과 저장
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    arr[i][j] = tmp[i][j];
                }
            }
        }
        
        // 남은 미세먼지 양
        int totalAmount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) totalAmount += arr[i][j];
            }
        }
        
        System.out.println(totalAmount);
    }
}