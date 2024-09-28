package 미로1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int[][] maze;
    static boolean[][] visited;
    static int answer;
    
    // 방향 배열: 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int tc = 1; tc <= 10; tc++) {
            maze = new int[16][16];
            visited = new boolean[16][16];
            answer = 0; // 도달 가능한지 여부
            
            sc.nextInt(); // 테스트 케이스 번호

            // 미로 입력
            for (int i = 0; i < 16; i++) {
                String line = sc.next(); // 한 줄씩 입력 받기
                for (int j = 0; j < 16; j++) {
                    maze[i][j] = line.charAt(j) - '0'; // 문자를 숫자로 변환
                }
            }

            // 출발점 (1,1)에서 BFS
            bfs(1, 1);

            System.out.println("#" + tc + " " + answer);
        }
        
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cx = current[0];
            int cy = current[1];

            // 도착점(3)에 도달하면 탐색 종료
            if (maze[cx][cy] == 3) {
                answer = 1; // 도달 가능 1
                return;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 미로 범위를 벗어나지 않고, 아직 방문하지 않았으며, 벽(1)이 아닌 경우 이동
                if (nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && !visited[nx][ny] && maze[nx][ny] != 1) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
