/*
	Sidd Lakshman
	Quick Sort Algorithm in Java 
	Partition will be rightmost element 
*/

public class quickSort {
	
	public static int[] quickSort(int[] array, int start, int end) {
		
		//base case
		if (start >= end) 
			return array;
		
		//subproblems
		int pIndex = partition(array, start, end);
		array = quickSort(array, start, pIndex - 1);
		array = quickSort(array, pIndex + 1, end);
		return array;
	}
	
	public static int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int pIndex = start;
		for (int i = start; i < end; i++) {
			if (array[i] <= pivot) {
				swap(array, i, pIndex);
				pIndex++;
			}
		}
		swap(array, pIndex, end);
		return pIndex;
	}
	
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static void printArray(int[] array) {
		int n = array.length;
		System.out.println(""); //newline
		for (int i = 0; i < n; i++)
			System.out.print(array[i] + " ");
	}
	
	public static void main(String[] args) {
		System.out.println("Sorting Algorithm 5: Quick Sort");
		System.out.println("Array containing: 5, 2, 3, 6, 1, 7, 9, 8");
		int[] mixedArr = {5, 2, 3, 6, 1, 7, 9, 8};
		int end = mixedArr.length - 1;
		int[] sortedArr = quickSort(mixedArr, 0, end);
		printArray(sortedArr);
	}
}