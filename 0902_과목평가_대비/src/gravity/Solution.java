package gravity;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {			
			int N = sc.nextInt(); // 방의 가로 길이
			int[] height = new int[N]; // 각 칸의 박스 높이
			for(int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
			}
			// 최대 낙차
			int maxDrop = 0;
			// 각 칸에서 떨어질 수 있는 최대 낙차 계산
			for(int i = 0; i < N; i++) {
				// 현재 칸에서 떨어질 수 있는 낙차
				int drop = 0;
				// 현재 칸(i)보다 오른쪽에 있는 모든 칸(j) 비교
				for(int j = i + 1; j < N; j++) {
					// 현재 칸의 높이가 오른쪽 칸의 높이보다 크면
					if(height[i] > height[j]) {
						drop++;
					}
				}
				if(drop > maxDrop) {
					maxDrop = drop;
				}
			}
			System.out.println("#" + tc + " " + maxDrop);
		}
	}
}
