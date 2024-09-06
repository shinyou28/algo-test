package SWEA_2112_보호필름;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int D; // 행
	static int W; // 열
	static int K; // 통과 기준
	static int[][] film;
	static int[] A;
	static int[] B;
	static int ans; // 최소 투약 횟수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			film = new int[D][W];
			A = new int[W];
			B = new int[W];
			// B는 1로 초기화
			Arrays.fill(B, 1);
			
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					film[i][j] = sc.nextInt();
				}
			}
			
			ans = K; // 최소 투약 횟수가 K를 넘을 수 없다.
			makefilm(0, 0);
			System.out.println("#" + tc + " " + ans);
						
		}
	}
	// idx : 현재 약품을 주입하려고 하는 행
	// cnt : 약품을 주입한 횟수
	private static void makefilm(int idx, int cnt) {
		if(isOk()) {
			ans = Math.min(ans, cnt);
			return;
		}
		if(ans < cnt)
			return;
		if(idx == D)
			return;
		
		// 주입X
		makefilm(idx + 1, cnt);
		int[] tmp = film[idx];
		// 주입A
		film[idx] = A;
		makefilm(idx + 1, cnt + 1);
		// 주입B
		film[idx] = B;
		makefilm(idx + 1, cnt + 1);
		// 원상복구
		film[idx] = tmp;
		
	}
	
	// 통과기준을 만족하는지 유무
	private static boolean isOk() {
		for(int c = 0; c < W; c++) {
			boolean flag = false;
			int cnt = 1;
			
			for(int r = 1; r < D; r++) {
				if(film[r][c] == film[r - 1][c])
					cnt++;
				else
					cnt = 1;
				
				if(cnt >= K) {
					flag = true;
					break;
				}
			}
			if(!flag)
				return false;
		}
		return true;
	}

}
