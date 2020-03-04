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
		System.out.println("6. Counting Sort");
		System.out.println("7. Test");
		System.out.println("8. Exit");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nPlease enter your selection: ");
		int choice = scanner.nextInt();
		int valid = 0;
		
		while (valid == 0) {
			if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6 || choice == 7 || choice == 8)
				break;
			System.out.print("\nPlease enter a valid selection (1 through 8): ");
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
	
	
	//option 6: counting sort (geeksforgeeks implementation which is less efficient)
	/*public static int[] countingSort(int[] ar) {
		int maxIndex = 0;
		for (int i = 0; i < ar.length; i++) 
			if (ar[i] > ar[maxIndex])
				maxIndex = i;
		int[] frequency = new int[ar[maxIndex]+1];
		for (int i : ar)
			frequency[i]++;
		int ndx = 0;
		for (int i = 0; i < frequency.length; i++) {
			while (0 < frequency[i]) {
				ar[ndx++] = i;
				frequency[i]--;
			}
		}
		return ar;
	}*/
	//option 6: counting sort (self written), bucket space complexity reduced
	public static int[] countingSort(int[] ar) {
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] < ar[minIndex])
                minIndex = i;
            if (ar[i] > ar[maxIndex])
                maxIndex = i;
        }
        int[] buckets = new int[ar[maxIndex] - ar[minIndex] + 1];
        int[] sorted = new int[ar.length];
        for (int i = 0; i < ar.length; i++)
            buckets[ar[i] - ar[minIndex]]++;
		for (int i = 1; i < buckets.length; i++)
			buckets[i] += buckets[i-1];
        for (int i = 0; i < ar.length; i++) {
            sorted[buckets[ar[i] - ar[minIndex]] - 1] = ar[i];
            buckets[ar[i] - ar[minIndex]]--;
        }
        return sorted;
    }
	
	//option 7: testing stuff
	public static String test(String s) {
		//frequency hashmap for each character in s
        HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++)
            if (freq.containsKey(s.charAt(i)))
                freq.put(s.charAt(i), freq.get(s.charAt(i)) + 1);
            else
                freq.put(s.charAt(i), 1);

        //convert freq hashmap into a value-array (probably a better way to do this?)
        int[] arr = new int[freq.size()];
        int idx = 0;
        for (Map.Entry<Character, Integer> entry : freq.entrySet())
            arr[idx++] = entry.getValue();

        //frequency of frequencies hashmap
        HashMap<Integer, Integer> freqOfFreq = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) 
            if (freqOfFreq.containsKey(arr[i]))
                freqOfFreq.put(arr[i], freqOfFreq.get(arr[i]) + 1);
            else
                freqOfFreq.put(arr[i], 1);
        
        //check for validity
        int[] keyvals = new int[4];
        int k = 0;
        if (freqOfFreq.size() > 2)
            return "NO";
        if (freqOfFreq.size() == 2) {
            for (Map.Entry<Integer, Integer> entry : freqOfFreq.entrySet()) {
                keyvals[k++] = entry.getKey();
                keyvals[k++] = entry.getValue();
            }
			System.out.println("keyvals: ");
			printArray(keyvals);
            if (keyvals[3] > 1 && keyvals[2] < keyvals[0])
                return "NO";
            else if (Math.abs(keyvals[2] - keyvals[0]) > 1)
                return "NO";
        }
        return "YES";
	}
	/*TEST FOR HACKERRANK HASHMAP PROBLEM
	public static ArrayList<Integer> test(ArrayList<ArrayList<Integer>> queries) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        int op = 0;
        int key = 0;

        for (int i = 0; i < queries.size(); i++) {
            op = queries.get(i).get(0);
            key = queries.get(i).get(1);
			System.out.println("i = " + i);
			System.out.println("This is the op: " + op);
			System.out.println("This is the key: " + key);
			
            HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>(); 
            if (op == 1) {
                if (freq.containsKey(key))
                    freq.put(key, freq.get(key) + 1);
                else 
                    freq.put(key, 1);
            } else if (op == 2 && freq.containsKey(key)) {
                freq.put(key, freq.get(key) - 1);
                freq.remove(key, 0);
            } else if (op == 3) {
                if(freq.containsValue(key))
                    output.add(1);
                else 
                    output.add(0);
            }
        }
        return output;
    }
	*/
	/*TEST FOR COUNTING SORT
	public static int test(int[] expenditure, int d) {
		int[] medianArr = new int[d];
		int k = 0;
		int maxSpend = 0;
		int alerts = 0;
		for (int i = 0; i < expenditure.length - d; i++) {
			for (int j = i; j < i + d; j++)
				medianArr[k++] = expenditure[j];
			k = 0;
			maxSpend = getMaxSpend(medianArr);
			if (expenditure[i+d] >= maxSpend)
				alerts++;
		}
		return alerts;
	}
	*/
    private static int getMaxSpend(int[] ar) {
		/*
        int n = ar.length%2;
        ar = countingSort(ar);
        if (n == 1)
            return (2*ar[ar.length/2]);
        return ar[ar.length/2 - 1] + ar[ar.length/2];
		*/
		int n = ar.length%2;
		ar = countingSort(ar);
		//System.out.print("Median arr after sort: ");
		//printArray(ar);
		if (n == 1) 
			return 2*ar[ar.length/2];
		return ar[ar.length/2 - 1] + ar[ar.length/2];
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
		for (int i = 1; i < 9; i++) 
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
			
			if (choice == 6) { 
				System.out.println("\nThe following randomized array has been generated: ");
				int[] temp = {2, 1, 4, 3};
				printArray(temp);
				array = countingSort(temp);
				System.out.println("\n\nThe array is now sorted: ");
				printArray(array);
			}
			
			if (choice == 7) {
				String s = "aabbc";
				System.out.println("Test for sherlock and valid string problem on hackerrank: ");
				System.out.println("This case should output YES: " + test(s));
			}
			/*TEST CASE FOR HACKERRANK HASHMAP PROBLEM
			if (choice == 7) {
				System.out.println("\nTest for hashmap problem on hackerrank: ");
				ArrayList<Integer> sample1 = new ArrayList<Integer>();
				ArrayList<Integer> sample2 = new ArrayList<Integer>();
				ArrayList<Integer> sample3 = new ArrayList<Integer>();
				ArrayList<Integer> sample4 = new ArrayList<Integer>();
				ArrayList<Integer> sample5 = new ArrayList<Integer>();
				ArrayList<Integer> sample6 = new ArrayList<Integer>();
				ArrayList<Integer> sample7 = new ArrayList<Integer>();
				ArrayList<Integer> sample8 = new ArrayList<Integer>();
				
				sample1.add(1);
				sample1.add(5);
				sample2.add(1);
				sample2.add(6);
				sample3.add(3);
				sample3.add(2);
				sample4.add(1);
				sample4.add(10);
				sample5.add(1);
				sample5.add(10);
				sample6.add(1);
				sample6.add(6);
				sample7.add(2);
				sample7.add(5);
				sample8.add(3);
				sample8.add(2);	
				
				ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
				input.add(sample1);
				input.add(sample2);
				input.add(sample3);
				input.add(sample4);
				input.add(sample5);
				input.add(sample6);
				input.add(sample7);
				input.add(sample8);
				
				ArrayList<Integer> output= new ArrayList<Integer>();
				output = test(input);
				
				System.out.print("Array Values: ");
				for(int i =0; i < output.size(); i++)
					System.out.print(output.get(i) + " ");
			}
			*/
			/*TEST CASE FOR COUNTING SORT
			if (choice == 7) {
				int[] expenditure = {2, 1, 4, 3, 4};
				System.out.print("Expenditure array: ");
				printArray(expenditure);
				int res = test(expenditure, 4);
				System.out.println("\nalerts: " + res);
			}
			*/
			
			if (choice == 8) 
				System.exit(0);
		} while (isValid(choice));
	}
}