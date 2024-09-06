package SWEA_1949_등산로조성;

import java.util.Scanner;

public class Solution {
	// 상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, K, maxH, ans; // K : 공사가능깊이
	static int[][] mountain;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			maxH = 0; // 최대 높이
			ans = 0; // 등산로 길이
			mountain = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					mountain[i][j] = sc.nextInt();
					if(maxH < mountain[i][j]) {
						maxH = mountain[i][j];
					}
				}
			} // 입력 받으면서 최대 높이만 찾는다. 최대 높이는 여러개 있을 수 있다.
			
			// 전체를 순회하면서 가장 높은 봉우리에서 등산로 조성 시작
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(mountain[i][j] == maxH) {
						// 공사시작
						work(i, j, 1, true);
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	// r,c 현재좌표, dist 지금까지 공사 길이, skill 공사 할 수 있는지 유무
	private static void work(int r, int c, int dist, boolean skill) {
		if(dist > ans) {
			ans = dist;
		}
		visited[r][c] = true;
		
		// 상하좌우 탐색
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			// 범위 벗어났는지, 이미 방문했는지 확인
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
			// 1. 다음 좌표의 높이가 내 높이 보다 낮을 때
			if(mountain[r][c] > mountain[nr][nc])
				work(nr, nc, dist + 1, skill);
			// 2. 다음 좌표의 높이가 내 높이와 같거나 높을 때
			else if(skill && mountain[r][c] > mountain[nr][nc] - K) {
				int tmp = mountain[nr][nc]; // 원상복구 기록
				mountain[nr][nc] = mountain[r][c] - 1;
				work(nr, nc, dist + 1, false);
				mountain[nr][nc] = tmp;
			}
		}
		
		visited[r][c] = false;
		
	}
}
