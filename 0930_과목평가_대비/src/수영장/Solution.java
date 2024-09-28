package 수영장;

import java.util.Scanner;

public class Solution {
	
	static int[] cost;
	static int[] plan;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			// 1일, 1달, 3달, 1년 비용
			cost = new int[4];
			for(int i = 0; i < 4; i++) {
				cost[i] = sc.nextInt();
			}
			// 12개월 각 달의 최소 비용
			plan = new int[13];
			for (int i = 1; i <= 12; i++) {
				int days = sc.nextInt(); // 해당 달의 이용일 수
				// 1일 이용권과 1달 이용권 중 최소 비용
				plan[i] = Math.min(plan[i - 1] + days * cost[0], plan[i - 1] + cost[1]);
				// 3달 이용권 사용할 경우 최소 비용
				if(i >= 3) {
					plan[i] = Math.min(plan[i], plan[i - 3] + cost[2]);
				}
			}
			// 1년 이용권과 비교
			System.out.println("#" + tc + " " + Math.min(plan[12], cost[3]));
		}
	}
}
