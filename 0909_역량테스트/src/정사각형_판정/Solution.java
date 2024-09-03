package 정사각형_판정;

import java.util.Scanner;

public class Solution {
    static char[][] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 격자판의 크기
            arr = new char[N][N]; // 격자판
            for(int i = 0; i < N; i++) {
                String str = sc.next();
                for(int j = 0; j < N; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            int maxR = -1, maxC = -1, minR = N, minC = N;

            // 최대, 최소 좌표 계산
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(arr[i][j] == '#') {
                        if (i > maxR) maxR = i;
                        if (j > maxC) maxC = j;
                        if (i < minR) minR = i;
                        if (j < minC) minC = j;
                    }
                }
            }

            if (maxR == -1 || maxC == -1) {
                // '#'이 없을 경우
                System.out.println("#" + tc + " " + "no");
            } else if (square(maxR, maxC, minR, minC)) {
                System.out.println("#" + tc + " " + "yes");
            } else {
                System.out.println("#" + tc + " " + "no");
            }
        }
    }

    public static boolean square(int maxR, int maxC, int minR, int minC) {
        // 정사각형 조건 확인: 행과 열의 차이가 같아야 한다.
        if (maxR - minR != maxC - minC) {
            return false;
        }

        for(int i = minR; i <= maxR; i++) {
            for(int j = minC; j <= maxC; j++) {
                if(arr[i][j] != '#') {
                    return false;
                }
            }
        }
        return true;
    }
}
