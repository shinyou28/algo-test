package 거스름돈;

import java.util.Scanner;

public class Solution2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 거스름돈 액수
		int count = 0;
		int temp = 0;
		while(true) {
			if(N % 5 == 0) {
				count += N/5;
				System.out.println(count);
				break;
			}else {
				N -= 2;
				count++;
			}
			if(N < 0) {
				System.out.println(-1);
				break;
			}
		}
	}
}
