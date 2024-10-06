package 차윤이의_RC카;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            char[][] field = new char[N][N];
            // 현재위치, 목적지
            int startX = 0, startY = 0, targetX = 0, targetY = 0;
            
            for (int i = 0; i < N; i++) {
                String row = sc.next();
                for (int j = 0; j < N; j++) {
                    field[i][j] = row.charAt(j);
                    // 현재 위치
                    if (field[i][j] == 'X') {
                        startX = i;
                        startY = j;
                    }
                    // 목적지
                    if (field[i][j] == 'Y') {
                        targetX = i;
                        targetY = j;
                    }
                }
            }

            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};
            int dir = 0; // 초기 방향은 북쪽 (0: 북, 1: 동, 2: 남, 3: 서)

            int Q = sc.nextInt(); // 조종 횟수
            System.out.print("#" + tc + " ");
            
            for (int i = 0; i < Q; i++) {
                int C = sc.nextInt(); // 커맨드 길이
                String commands = sc.next(); // 커맨드 입력
                int currX = startX;
                int currY = startY;
                dir = 0; // 초기 방향은 북쪽으로 설정
                
                for (int j = 0; j < C; j++) {
                    char command = commands.charAt(j);
                    if (command == 'L') {
                        dir = (dir + 3) % 4; // 왼쪽으로 90도 회전
                    } else if (command == 'R') {
                        dir = (dir + 1) % 4; // 오른쪽으로 90도 회전
                    } else if (command == 'A') {
                        int newX = currX + dx[dir];
                        int newY = currY + dy[dir];
                        // 맵 경계 및 나무 검사
                        if (newX >= 0 && newX < N && newY >= 0 && newY < N && field[newX][newY] != 'T') {
                            currX = newX;
                            currY = newY;
                        }
                    }
                }
                
                // 명령어를 모두 실행한 후 목적지 도달 여부 확인
                if (currX == targetX && currY == targetY) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
