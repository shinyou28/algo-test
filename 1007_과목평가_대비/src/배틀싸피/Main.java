package 배틀싸피;

import java.util.*;

public class Main {
    private static String NICKNAME = "기본코드";
    private static String[][] mapData;  // 맵 정보
    private static Map<String, String[]> allies = new HashMap<>();  // 아군 정보
    private static Map<String, String[]> enemies = new HashMap<>();  // 적군 정보
    private static String[] codes;  // 암호문 정보

    public static void main(String[] args) {
        Bridge bridge = new Bridge();
        String gameData = bridge.init(NICKNAME);

        while (gameData.length() > 0) {
            System.out.printf("----입력데이터----\n%s\n----------------\n", gameData);
            parseData(gameData);

            int[] myPosition = {-1, -1};  // 내 탱크 위치 찾기
            for (int i = 0; i < mapData.length; i++) {
                for (int j = 0; j < mapData[i].length; j++) {
                    if (mapData[i][j].equals("A")) {
                        myPosition[0] = i;
                        myPosition[1] = j;
                        break;
                    }
                }
                if (myPosition[0] != -1) break;
            }

            // BFS로 최단 경로를 찾고, 경로를 따라 이동
            String output = bfsFindShortestPath(myPosition[0], myPosition[1]);

            // 다음 상태 전송
            gameData = bridge.submit(output);
        }

        bridge.close();
    }

    // BFS 알고리즘으로 최단 경로 탐색 후 명령어 반환
    static String bfsFindShortestPath(int startX, int startY) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // 상, 하, 좌, 우
        String[] directionSymbols = {"U", "D", "L", "R"};  // 이동 방향 기호
        boolean[][] visited = new boolean[mapData.length][mapData[0].length];  // 방문 여부
        int[][] previous = new int[mapData.length][mapData[0].length];  // 이전 좌표 저장 (경로 추적용)

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY, -1});  // 시작점 추가, 마지막 값은 방향 추적용
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int lastDirection = current[2];  // 이전 방향

            if (mapData[x][y].equals("X")) {  // 목적지에 도달하면
                return reconstructPath(startX, startY, x, y, previous);
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int newX = x + directions[i][0];
                int newY = y + directions[i][1];

                if (newX >= 0 && newY >= 0 && newX < mapData.length && newY < mapData[0].length 
                        && !visited[newX][newY] && (mapData[newX][newY].equals("G") || mapData[newX][newY].equals("X"))) {
                    queue.add(new int[]{newX, newY, i});  // 큐에 새 좌표 추가
                    visited[newX][newY] = true;  // 방문 처리
                    previous[newX][newY] = i;  // 이동한 방향 저장
                }
            }
        }

        return "S";  // 경로가 없을 경우 대기
    }

    // 경로를 역추적하여 명령어 생성
    static String reconstructPath(int startX, int startY, int endX, int endY, int[][] previous) {
        int x = endX;
        int y = endY;
        StringBuilder path = new StringBuilder();

        while (x != startX || y != startY) {
            int direction = previous[x][y];
            path.insert(0, direction);  // 경로 역추적, 마지막 방향부터 기록
            // 이전 좌표로 이동
            switch (direction) {
                case 0: x += 1; break;  // U(상) -> 아래쪽 좌표로 돌아감
                case 1: x -= 1; break;  // D(하) -> 위쪽 좌표로 돌아감
                case 2: y += 1; break;  // L(좌) -> 오른쪽 좌표로 돌아감
                case 3: y -= 1; break;  // R(우) -> 왼쪽 좌표로 돌아감
            }
        }

        char firstMove = path.charAt(0);  // 첫 번째 움직임만 필요
        switch (firstMove) {
            case '0': return "U A";  // 위로 이동
            case '1': return "D A";  // 아래로 이동
            case '2': return "L A";  // 왼쪽으로 이동
            case '3': return "R A";  // 오른쪽으로 이동
        }

        return "S";  // 기본값: 대기
    }


    // 입력 데이터를 파싱하여 변수에 저장
    static void parseData(String gameData) {
    	// 입력 데이터를 행으로 나누기
        String[] rows = gameData.split("\n");
        int rowIndex = 0;

        // 첫 번째 행 데이터 읽기
        String[] header = rows[rowIndex].split(" ");
        int mapHeight = Integer.parseInt(header[0]);  // 맵의 세로 크기
        int mapWidth = Integer.parseInt(header[1]);  // 맵의 가로 크기
        int numOfAllies = Integer.parseInt(header[2]);  // 아군의 수
        int numOfEnemies = Integer.parseInt(header[3]);  // 적군의 수
        int numOfCodes = Integer.parseInt(header[4]);  // 암호문의 수
        rowIndex++;

        // 기존의 맵 정보를 초기화하고 다시 읽어오기
        mapData = new String[mapHeight][mapWidth];
        for (int i = 0; i < mapHeight; i++) {
            mapData[i] = rows[rowIndex + i].split(" ");
        }
        rowIndex += mapHeight;

        // 기존의 아군 정보를 초기화하고 다시 읽어오기
        allies.clear();
        for (int i = 0; i < numOfAllies; i++) {
            String[] allyData = rows[rowIndex + i].split(" ");
            String allyName = allyData[0];
            String[] allyInfo = Arrays.copyOfRange(allyData, 1, allyData.length);
            allies.put(allyName, allyInfo);
        }
        rowIndex += numOfAllies;

        // 기존의 적군 정보를 초기화하고 다시 읽어오기
        enemies.clear();
        for (int i = 0; i < numOfEnemies; i++) {
            String[] enemyData = rows[rowIndex + i].split(" ");
            String enemyName = enemyData[0];
            String[] enemyInfo = Arrays.copyOfRange(enemyData, 1, enemyData.length);
            enemies.put(enemyName, enemyInfo);
        }
        rowIndex += numOfEnemies;

        // 기존의 암호문 정보를 초기화하고 다시 읽어오기
        codes = new String[numOfCodes];
        for (int i = 0; i < numOfCodes; i++) {
            codes[i] = rows[rowIndex + i];
        }
    }
}