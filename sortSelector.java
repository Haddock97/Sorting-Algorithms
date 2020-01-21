/*
	Sidd Lakshman
	Sorting Algorithm Selector
	Program will contain a menu with all sorting methods available
*/

import	java.util.Scanner;
import	java.util.Random;
import	java.util.*;

public class sortSelector {
	
	
	
	
	/******************** MAIN MENU ********************/
	//method to build menu and returns integer value 1-6 depending on user selection
	public static int menu() {
		System.out.println("\n\nMenu:");
		System.out.println("1. Selection Sort");
		System.out.println("2. Bubble Sort");
		System.out.println("3. Insertion Sort");
		System.out.println("4. Merge Sort");
		System.out.println("5. Quick Sort");
		System.out.println("6. Exit");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPlease enter your selection: ");
		int choice = scanner.nextInt();
		int valid = 0;
		
		while (valid == 0) {
			if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6)
				break;
			System.out.print("\nPlease enter a valid selection (1 through 6): ");
			choice = scanner.nextInt();
		}
		
		return choice;
	}
	
	
	
	
	/******************** SORTING ALGORITHMS ********************/
	//option 1: selection sort
	public static int[] selectionSort(int[] array) {
		
		int length = array.length;
		int minIndex = 0;
		
		for (int i = 0; i < length; i++) {
			minIndex = i;
			for (int j = i+1; j < length; j++) 
				if (array[j] < array[minIndex]) 
					minIndex = j;
			swap(array, i, minIndex);
		}
		return array;
	}
	
	
	//option 2: bubble sort
	public static int[] bubbleSort(int[] array) {
		int length = array.length;
		int swaps = 0;
		
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (array[j] > array[j+1]) {
					swap(array, j, j+1);
					swaps++;
				}
			}
			if (swaps == 0)
				return array;
			else
				swaps = 0;
		}
		return array;
	}
	
	
	//option 3: insertion sort
	public static int[] insertionSort(int[] array) {
		int length = array.length;
		for (int i = 1; i < length; i++)
			for (int j = 0; j < i; j++)
				if (array[i] < array[j])
					swap(array, i, j);
		return array;
	}
	
	
	//option 4: merge sort
	public static int[] mergeSort(int[] array) {
		int length = array.length;
		
		if (length <= 1) 
			return array;
		
		int mid = length/2;
		int[] L = new int[mid];
		int[] R;
		
		if (length%2 == 0)
			R = new int[mid];
		else
			R = new int[mid + 1];
		
		for (int i = 0; i < L.length; i++) 
			L[i] = array[i];
		for (int i = 0; i < R.length; i++)
			R[i] = array[i + L.length];
		
		int[] result = new int[length];
		L = mergeSort(L);
		R = mergeSort(R);
		
		result = merge(L, R);
		return result;
	}
	
	public static int[] merge(int[] L, int[] R) {
		int[] result = new int[L.length + R.length];
		int LP = 0;	//left pointer
		int RP = 0;	//right pointer
		int ResP = 0;	//result pointer
		
		while (LP < L.length || RP < R.length) {
			if (LP < L.length && RP < R.length) {
				if (L[LP] < R[RP]) {
					result[ResP] = L[LP];
					ResP++;
					LP++;
				} else {
					result[ResP] = R[RP];
					ResP++;
					RP++;
				}
			} else if (LP < L.length) {
				result[ResP] = L[LP];
				ResP++;
				LP++;
			} else if (RP < R.length) {
				result[ResP] = R[RP];
				ResP++;
				RP++;
			}
		}
		return result;
	}
	
	
	//option 5: quick sort
	public static int[] quickSort(int[] array, int start, int end) {
		if (start >= end)
			return array;
		
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
	
	
	
	
	/******************** HELPER METHODS ********************/
	//helper method to swap two elements in an array
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	//helper method to print array
	public static void printArray(int[] array) {
		int n = array.length;
		for (int i = 0; i < n; i++) 
			System.out.print(array[i] + " ");
	}
	//return intarray filled with random nonduplicate integers of size between 1 to 100
	public static int[] getArray(int size) {
		
		ArrayList<Integer> m = new ArrayList<Integer>();
		int[] result = new int[size];
		
		for (int i = 0; i < size; i++) 
			m.add(i);
		
		Collections.shuffle(m);
		for (int i = 0; i < size; i++) 
			result[i] = m.get(i);
		
		return result;
	}
	//returns random int from 1 to 100
	private static int getSize(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	//returns boolean if choice is valid
	private static boolean isValid(int choice) {
		for (int i = 1; i < 7; i++) 
			if (choice == i)
				return true;
		return false;
	}
	
	
	
	
	/******************** ACTUAL PROGRAM (UI etc) ********************/
	//main method
	public static void main(String[] args) {
		System.out.println("\nWelcome to the Sorting Algorithm Selector!");
		System.out.println("This program contains 5 sorting algorithms.");
		System.out.println("You will be able to choose which one to test. Enjoy!\n");
		int choice = 0;
		
		do {
			choice = menu();
			
			int size = getSize(1, 100);	//range 1 to 100
			int[] array = getArray(size);

			if (choice == 1) {
				System.out.println("\nThe following randomized array has been generated: ");
				printArray(array);
				array = selectionSort(array);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 2) {
				System.out.println("\nThe following randomized array has been generated: ");
				printArray(array);
				array = bubbleSort(array);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 3) {
				System.out.println("\nThe following randomized array has been generated: ");
				printArray(array);
				array = insertionSort(array);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 4) {
				System.out.println("\nThe following randomized array has been generated: ");
				printArray(array);
				array = mergeSort(array);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 5) {
				System.out.println("\nThe following randomized array has been generated: ");
				printArray(array);
				int start = 0;
				int end = array.length - 1;
				array = quickSort(array, start, end);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 6) 
				System.exit(0);
			
		} while (isValid(choice));
	}
}