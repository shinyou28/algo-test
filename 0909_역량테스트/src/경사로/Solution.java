package 경사로;

import java.util.Scanner;

public class Solution {
	static int N;
	static int L; // 경사로 길이
	static int[][] map; // 지도
	static boolean[][] visited; // 경사로 놓았는지 확인
	static int pathCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();

		map = new int[N][N];
		visited = new boolean[N][N];

		// 지도 정보 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 가능한 길인지 확인
		for (int i = 0; i < N; i++) {
			if (checkPath(i, 0, true)) // 행 검사
				pathCnt++;
			if (checkPath(0, i, false)) // 열 검사
				pathCnt++;
		}

		System.out.println(pathCnt);
	}

	// flag : true -> 행 검사
	private static boolean checkPath(int x, int y, boolean flag) {
		int[] height = new int[N];
		boolean[] visited = new boolean[N];

		// 행 또는 열을 height 배열에 복사
		for (int i = 0; i < N; i++) {
			if (flag)
				height[i] = map[x][i];
			else
				height[i] = map[i][y];
		}

		// 경사로 배치 확인 로직
		for (int i = 0; i < N - 1; i++) {
			// 높이가 같을 때
			if (height[i] == height[i + 1]) {
				continue;
			}
			// 내려가는 경사로
			else if (height[i] - height[i + 1] == 1) {
				for (int j = i + 1; j <= i + L; j++) {
					// 범위 넘어가거나 높이가 다르거나 이미 경사로가 있는 경우
					if (j >= N || height[i + 1] != height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
				i += (L - 1); // 경사로를 놓으면 그만큼 건너뛰기
			}
			// 올라가는 경사로
			else if (height[i] - height[i + 1] == -1) {
				for (int j = i; j > i - L; j--) {
					// 범위 넘어가거나 높이가 다르거나 이미 경사로가 있는 경우
					if (j < 0 || height[i] != height[j] || visited[j]) {
						return false;
					}
					visited[j] = true;
				}
			}
			// 높이 차이가 2 이상일 때
			else {
				return false;
			}
		}
		return true;
	}
}
