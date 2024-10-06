package 탈주범_검거;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    // 파이프 연결 상태
    static int[][] p = {
        {0, 0, 0, 0},  // 0번 파이프 (연결 없음)
        {1, 1, 1, 1},  // 1번 파이프 (상하좌우 모두 연결)
        {1, 1, 0, 0},  // 2번 파이프 (상하 연결)
        {0, 0, 1, 1},  // 3번 파이프 (좌우 연결)
        {1, 0, 0, 1},  // 4번 파이프 (상우 연결)
        {0, 1, 0, 1},  // 5번 파이프 (하우 연결)
        {0, 1, 1, 0},  // 6번 파이프 (하좌 연결)
        {1, 0, 1, 0}   // 7번 파이프 (상좌 연결)
    };

    // 반대 방향을 나타내는 배열
    // 0: 상 -> 1: 하, 1: 하 -> 0: 상, 2: 좌 -> 3: 우, 3: 우 -> 2: 좌
    static int[] opp = {1, 0, 3, 2};
    
    // 방향을 나타내는 배열 (상, 하, 좌, 우)
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    // BFS 함수
    public static int bfs(int si, int sj, int N, int M, int L, int[][] arr) {
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][M];  // int 타입의 배열로 변경하여 시간 기록
        int ans = 0;

        q.add(new int[]{si, sj});
        visited[si][sj] = 1;  // 시작 시간을 1로 설정 (1시간 경과)
        ans += 1;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int ci = current[0];
            int cj = current[1];

            // L시간이 되면 탐색 종료
            if (visited[ci][cj] == L) {
                return ans;
            }

            // 4방향 탐색
            for (int dr = 0; dr < 4; dr++) {
                int ni = ci + di[dr];
                int nj = cj + dj[dr];

                if (ni >= 0 && ni < N && nj >= 0 && nj < M && visited[ni][nj] == 0 
                    && p[arr[ci][cj]][dr] == 1 && p[arr[ni][nj]][opp[dr]] == 1) {
                    q.add(new int[]{ni, nj});
                    visited[ni][nj] = visited[ci][cj] + 1;  // 시간을 1 증가시킴
                    ans += 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();  // 세로 크기
            int M = sc.nextInt();  // 가로 크기
            int R = sc.nextInt();  // 시작 위치의 행
            int C = sc.nextInt();  // 시작 위치의 열
            int L = sc.nextInt();  // 이동 가능한 시간

            int[][] arr = new int[N][M];  // 파이프 배열
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // BFS 수행
            int ans = bfs(R, C, N, M, L, arr);
            System.out.println("#" + test_case + " " + ans);
        }
        sc.close();
    }
}
