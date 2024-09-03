package 준환이의_양팔저울;

import java.util.Scanner;

public class Solution {
    static int count; // 경우의 수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 무게 추의 개수
            int[] weights = new int[N]; // 무게 추의 무게를 저장
            for(int i = 0; i < N; i++) {
                weights[i] = sc.nextInt(); // 각 무게 추의 무게 입력
            }

            count = 0; // 경우의 수 초기화
            // 백트래킹 시작: 초기 인덱스 0, 왼쪽 저울의 초기 무게 0, 오른쪽 저울의 초기 무게 0
            backtrack(weights, N, 0, 0, 0);
            
            System.out.println("#" + tc + " " + count);
        }
    }

    // 백트래킹 함수: 주어진 무게 추들을 왼쪽 또는 오른쪽 저울에 배치하는 모든 경우를 탐색
    public static void backtrack(int[] weights, int N, int index, int leftWeight, int rightWeight) {
        // 모든 무게 추를 사용한 경우
        if(index == N) {
            count++; // 유효한 배치가 발견되었으므로 경우의 수 증가
            return;
        }

        // 현재 무게 추를 왼쪽 저울에 추가
        backtrack(weights, N, index + 1, leftWeight + weights[index], rightWeight);

        // 현재 무게 추를 오른쪽 저울에 추가 (오른쪽 저울이 왼쪽 저울보다 무거워지지 않는 경우에만)
        if(rightWeight + weights[index] <= leftWeight) {
            backtrack(weights, N, index + 1, leftWeight, rightWeight + weights[index]);
        }
    }
}
