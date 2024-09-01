package 병합정렬;

// 분할 정복 기법을 활용한 안정적인 정렬 알고리즘
// 배열을 절반으로 분할하고, 각 부분을 재귀적으로 정렬한 뒤
// 정렬된 부분 배열을 다시 병합하는 정렬 방식
// 시간 복잡도 O(NlogN)
// 추가적인 공간이 필요하다

import java.util.Arrays;
public class Solution {
	static int[] arr = {7, 5, 13, 2, 79, 12, 35, 42};
	static int N = arr.length;
	static int[] tmp = new int[N];
	
	public static void main(String[] args) {
		// 배열의 첫번째 인덱스부터 마지막 인덱스까지 병합 정렬
		mergeSort(0, N-1);
		System.out.println(Arrays.toString(arr));
	}
	
	// left : 정렬할 구간의 시작 인덱스
	// right : 정렬할 구간의 끝 인덱스
	static void mergeSort(int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			
			mergeSort(left, mid);
			mergeSort(mid + 1, right);
			
			merge(left, mid, right);
		}
	}
	
	// left : 병합할 구간의 시작 인덱스
	// right : 병합할 구간의 중간 인덱스
	// right : 병합할 구간의 끝 인덱스
	static void merge(int left, int mid, int right) {
		int L = left; // 왼쪽 부분 배열의 시작 인덱스
		int R = mid + 1; // 오른쪽 부분 배열의 시작 인덱스
		
		int idx = left; // 임시 배열의 인덱스
		
		// 왼쪽 구간과 오른쪽 구간의 원소 비교
		while (L <= mid && R <= right) {
			// 왼쪽 구간의 현재 원소가 오른쪽 구간의 현재 원소보다 작거나 같으면
			if(arr[L] <= arr[R]) {
				// 왼쪽 구간의 원소를 저장하고 인덱스 증가
				tmp[idx++] = arr[L++];
			} else {
				// 오른쪽 구간의 원소를 저장하고 인덱스 증가
				tmp[idx++] = arr[R++];
			}
		}
		
		// 왼쪽 구간에 원소가 남은 경우
		if (L <= mid) {
			// 남은 원소를 임시배열에 저장
			for(int i = L; i <= mid; i++) {
				tmp[idx++] = arr[i];
			}
		} else {
			for(int i = R; i <= right; i++) {
				tmp[idx++] = arr[i];
			}
		}
		
		// 임시 배열에 저장된 정렬된 값을 원래 배열에 저장
		for(int i = left; i <= right; i++) {
			arr[i] = tmp[i];
		}
	}
	
}