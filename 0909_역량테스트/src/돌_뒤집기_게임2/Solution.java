package 돌_뒤집기_게임2;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 돌의 수
			int M = sc.nextInt(); // 뒤집기 횟수
			
			// N개의 돌의 초기 상태
			int[] stone = new int[N];
			for(int i = 0; i < N; i++) {
				stone[i] = sc.nextInt();
			}
			
			int[][] flip = new int[M + 1][2];
			for(int i = 0; i < M; i++) {
				int idx = sc.nextInt() - 1; // i번째 돌을 사이에 두고
				int op = sc.nextInt(); // 마주보는 j개의 돌에 대해
				
				for(int j = 1; j <= op; j++) {
					if(idx - j < 0 || idx + j >= N) {
						break;
					}
					if(stone[idx - j] == stone[idx + j]) {
						if(stone[idx - j] == 0) {
							stone[idx - j] = 1;
							stone[idx + j] = 1;
						} else {
							stone[idx - j] = 0;
							stone[idx + j] = 0;
						}
					}
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < N; i++) {
				System.out.print(stone[i] + " ");
			}
			System.out.println();
		}
	}
}
