package 게리맨더링;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    static int N; // 구역의 수
    static int[] people; // 구역 별 인구 수
    static int min; // 인구 차이 최소값
    static ArrayList<ArrayList<Integer>> list; // 각 구역의 연결 정보를 저장하는 인접 리스트

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();      
        // 인구 수 배열 people 초기화 (1번 구역부터 사용하기 위해 N+1 크기로 만듦)
        people = new int[N + 1];
        // 각 구역의 인구 수 입력 받기
        for (int i = 1; i <= N; i++) {
            people[i] = scanner.nextInt();
        }
        // 구역 간 연결 정보를 저장하기 위한 인접 리스트 초기화
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>()); // 각 구역에 대한 리스트 생성
        }

        // 구역의 연결 정보를 입력 받기
        for (int i = 1; i <= N; i++) {
            int cnt = scanner.nextInt(); // 현재 구역과 연결된 다른 구역의 개수 입력 받기
            for (int j = 0; j < cnt; j++) {
                int node = scanner.nextInt(); // 연결된 구역 번호 입력 받기
                list.get(i).add(node); // 현재 구역에 연결된 구역 추가
                list.get(node).add(i); // 양방향 연결이므로 반대 방향도 추가
            }
        }

        // 모든 구역의 인구 수의 총합 계산
        int totalPeople = 0;
        for (int i = 1; i <= N; i++) {
            totalPeople += people[i]; // 각 구역의 인구 수를 더함
        }
        min = totalPeople; // 최소값을 처음엔 전체 인구 수로 초기화 (나눌 수 없는 경우 대비)

        // 부분집합을 통해 구역을 나누는 모든 경우의 수를 확인
        subset(1, new boolean[N + 1]);

        // 만약 최소값이 여전히 전체 인구 수라면, 구역을 제대로 나누지 못한 경우이므로 -1 출력
        if (min == totalPeople) {
            System.out.println(-1);
        } else {
            System.out.println(min); // 구역을 나누었다면 최소 인구 차이를 출력
        }

    }

    // 부분집합을 구하는 재귀 함수
    static void subset(int cnt, boolean[] isSelected) {
        // 모든 구역에 대해 선택 여부를 결정한 경우
        if (cnt == N + 1) {
            // 선택된 구역들로 두 구역을 나눌 수 있는지 확인
            boolean[] visited = new boolean[cnt]; // 방문 여부 체크 배열
            int check = 0; // 몇 개의 그룹으로 나뉘었는지 확인할 변수

            for (int i = 1; i < cnt; i++) {
                if (!visited[i]) {
                    // 아직 방문하지 않은 구역에 대해 DFS로 연결 확인
                    dfs(i, visited, isSelected);
                    check++; // 구역이 하나의 그룹으로 연결되었음을 의미
                }
            }

            // 두 그룹으로 나뉜 경우에만 인구 차이 계산
            if (check == 2) {
                int sum1 = 0, sum2 = 0;
                for (int i = 1; i < cnt; i++) {
                    if (isSelected[i]) {
                        sum1 += people[i]; // 첫 번째 그룹의 인구 수 합계
                    } else {
                        sum2 += people[i]; // 두 번째 그룹의 인구 수 합계
                    }
                }
                // 두 그룹의 인구 수 차이 계산
                int diff = Math.abs(sum1 - sum2);
                // 현재 구역 나누기에서의 최소 인구 차이를 저장
                min = Math.min(min, diff);
            }
            return;
        }

        // 현재 구역을 첫 번째 그룹에 포함시키는 경우
        isSelected[cnt] = true;
        subset(cnt + 1, isSelected);

        // 현재 구역을 두 번째 그룹에 포함시키는 경우
        isSelected[cnt] = false;
        subset(cnt + 1, isSelected);
    }

    // 구역 내 모든 점들이 연결되어 있는지 확인하는 DFS 함수
    static void dfs(int idx, boolean[] visited, boolean[] isSelected) {
        // 구역 범위를 벗어나면 return
        if (idx > N) return;

        visited[idx] = true; // 현재 구역 방문 표시

        // 현재 구역과 연결된 다른 구역들에 대해 확인
        for (int i = 1; i <= N; i++) {
            // 아직 방문하지 않았고, 같은 그룹에 속하며, 연결되어 있는 구역이라면 재귀적으로 탐색
            if (!visited[i] && isSelected[i] == isSelected[idx] && list.get(idx).contains(i)) {
                dfs(i, visited, isSelected);
            }
        }
    }
}
