package 요리사;

import java.util.Scanner;

class Solution {

    static int[][] synergy;  // 시너지 배열 (Sij 값 저장)
    static boolean[] selects;  // 재료 선택 여부를 저장하는 배열
    static int N;  // 식재료의 개수
    static int answer;  // A음식과 B음식 간의 맛 차이의 최소값
    
    // A음식과 B음식의 맛 차이를 계산하는 함수
    static int cal() {
        int tasteA = 0;  // A음식의 맛
        int tasteB = 0;  // B음식의 맛
        // 각 재료들의 시너지를 계산하여 맛을 구함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;  // 자기 자신과는 시너지 계산 안 함
                if (selects[i] && selects[j]) {
                    tasteA += synergy[i][j];  // A음식에 포함된 재료들의 시너지 합산
                } else if (!selects[i] && !selects[j]) {
                    tasteB += synergy[i][j];  // B음식에 포함된 재료들의 시너지 합산
                }
            }
        }
        return Math.abs(tasteA - tasteB);  // A음식과 B음식의 맛 차이 반환
    }
    
    // 재료를 선택하여 두 음식으로 나누는 조합
    static void combination(int idx, int cnt) {
        if (cnt == N / 2) {  // 재료를 절반씩 나누었다면
            answer = Math.min(answer, cal());  // 맛 차이의 최소값 갱신
            return;
        }
        
        // 각 재료를 A음식에 넣거나 B음식에 넣는 선택을 재귀적으로 진행
        for (int i = idx; i < N; i++) {
            selects[i] = true;  // i번째 재료를 A음식에 선택
            combination(i + 1, cnt + 1);  // 다음 재료 선택
            selects[i] = false;  // i번째 재료를 B음식에 선택
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();  // 식재료의 개수 입력 (항상 짝수)
            synergy = new int[N][N];  // 시너지 배열 초기화
            selects = new boolean[N];  // 재료 선택 배열 초기화
            answer = Integer.MAX_VALUE;  // 최소 차이 값 초기화
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    synergy[i][j] = sc.nextInt();  // 시너지 입력
                }
            }
            combination(0, 0);  // 첫 번째 재료부터 조합 생성 시작
            
            System.out.println("#" + t + " " + answer);
        }
    }
}
