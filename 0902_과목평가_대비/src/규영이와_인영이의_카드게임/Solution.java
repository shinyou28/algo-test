package 규영이와_인영이의_카드게임;

import java.util.Scanner;
import java.util.ArrayList;

public class Solution {

    static int[] gyuCard;  // 규영이의 카드 배열
    static boolean[] isUsed;  // 인영이의 카드 사용 여부 체크
    static ArrayList<Integer> inCards;  // 인영이의 카드 저장
    static int gyuWinCount;  // 규영이가 이긴 횟수
    static int inWinCount;  // 인영이가 이긴 횟수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            gyuCard = new int[9];  // 규영이의 카드를 저장할 배열
            boolean[] cardUsed = new boolean[19];  // 1부터 18까지의 카드 사용 여부를 체크
            inCards = new ArrayList<>();  // 인영이의 카드를 저장할 리스트
            gyuWinCount = 0;
            inWinCount = 0;

            for(int i = 0; i < 9; i++) {
                gyuCard[i] = sc.nextInt();  // 규영이의 카드 입력
                cardUsed[gyuCard[i]] = true;  // 해당 카드를 사용했다고 표시
            }

            // 인영이의 카드를 결정
            for(int i = 1; i <= 18; i++) {
                if(!cardUsed[i]) {  // 규영이의 카드에 포함되지 않은 카드를 인영이의 카드로 사용
                    inCards.add(i);  // 인영이의 카드 리스트에 추가
                }
            }

            // 인영이의 카드 순열을 생성하고 게임 결과 계산
            isUsed = new boolean[9];  // 인영이의 카드 사용 여부를 체크
            calculateGame(0, new int[9]);  // 백트래킹을 통해 인영이의 카드 순열 생성 및 게임 결과 계산

            System.out.println("#" + tc + " " + gyuWinCount + " " + inWinCount);
        }
    }

    static void calculateGame(int depth, int[] inCardPerm) {
        if(depth == 9) {  // 모든 순열을 생성한 경우
            int gyuScore = 0;  // 규영이의 점수
            int inScore = 0;  // 인영이의 점수
            for(int i = 0; i < 9; i++) {
                if(gyuCard[i] > inCardPerm[i]) {  // 규영이의 카드가 더 크다면
                    gyuScore += gyuCard[i] + inCardPerm[i];  // 규영이가 점수를 얻음
                } else {
                    inScore += gyuCard[i] + inCardPerm[i];  // 인영이가 점수를 얻음
                }
            }
            // 점수 비교 후 승패 결정
            if(gyuScore > inScore) {
                gyuWinCount++;  // 규영이 승리 횟수 증가
            } else if(gyuScore < inScore) {
                inWinCount++;
            }
            return;
        }

        // 인영이의 카드를 순열 형태로 생성
        for(int i = 0; i < 9; i++) {
            if(!isUsed[i]) {  // 아직 사용되지 않은 카드를 선택
                isUsed[i] = true;  // 해당 카드를 사용했다고 표시
                inCardPerm[depth] = inCards.get(i);  // 현재 순열의 해당 위치에 카드 배치
                calculateGame(depth + 1, inCardPerm);  // 다음 카드 선택을 위해 재귀 호출
                isUsed[i] = false;  // 카드 사용 상태 복구 (백트래킹)
            }
        }
    }
}