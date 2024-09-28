package 거스름돈;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 거슬러 줘야하는 금액
			int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
			int[] dp = new int[money.length];
			
			for(int i = 0; i < money.length; i++) {
				if(money[i] > N) {
					dp[i] = 0;
					continue;
				}
				int rest = N / money[i];
				for(int j = 1; j <= rest; j++) {
					N -= money[i];
					dp[i]++;
				}
			}
			System.out.println("#" + tc + " ");
			for(int i = 0; i < money.length; i++) {
				System.out.print(dp[i] + " ");
			}
			System.out.println();
		}
	}
}
