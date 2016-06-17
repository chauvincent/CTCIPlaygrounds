import java.util.*;

public class Algorithms {
	
	// Quicksort
	static void myQuickSort(int[] a, int lo, int hi){
		if (hi <= lo) 
			return;
		int i = lo-1, j = hi;
		int temp, pivot = a[hi];  

		while(true){
			// start from left to find first smaller than pivot on right
			while(a[++i] < pivot);
			// start from right, look for greater than pivot
			while(pivot < a[--j]){
				if (j == lo)
					break;
			}
			// left and right index touch
			if( i >= j) 
				break;
			// swap values
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;			
			// recurse the two chunks
			myQuickSort(a, lo, i-1);
			myQuickSort(a, i+1, hi);
		}
	}
	// Binary Search Algorithm
	static int myBinarySearch(int[] a, int query){
		int lo = 0, hi = a.length-1;
		int mid = (lo + hi) / 2;	
		while(lo <= hi && a[mid] != query){
			if(a[mid] < query){
				lo = mid + 1;
			}else{
				hi = mid -1;
			}
			mid = (lo + hi) / 2;
		}
		if(lo > hi){
			mid = -1;
		}
		return mid;
	}
	// Given an array of distinct integer values, find the number of pairs that have the difference k.
	// ex: Input: {1, 7, 5, 9, 2, 12, 3}  k = 1 -> there are three pairs (1,3), (3,5), (7,9)
	static String getPairsWithKDifference(int k, int[] arr){
		int index, index2;
		String pairFound;
		String answer = "";
		//ArrayList<String>pairs = new ArrayList<>();
		for(int i = 0; i < arr.length; i++){
			index = -1;
			index2 = -1;
			index = myBinarySearch(arr, arr[i] + k);
			index2 = myBinarySearch(arr, arr[i] - k);
			if (index != -1){
				pairFound = "(" + arr[i] + "," + arr[index] + "), ";
				answer += pairFound;
			}
			if (index2 != -1){
				pairFound = "(" + arr[i] + "," + arr[index2] + "), ";
				answer += pairFound;
			}
		}
		return answer;
	}
	static String getPairsWithKDifferenceOptimal(int k, int[] arr){
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		String answer = "", pairFound = "";
		int index = -1;
		for(int i = 0; i < arr.length; i++){
			ht.put(arr[i], i);
		}
		for(int i = 0; i < arr.length; i++){
			if(ht.containsKey(  arr[i]+k )){
				index = ht.get( (arr[i]+ k) );
				pairFound = "(" + arr[i] + "," + arr[index] + "), ";
				answer += pairFound;
			}else if(ht.containsKey(arr[i] - k)){
				index = ht.get( (arr[i] - k) );
				pairFound = "(" + arr[i] + "," + arr[index] + "), ";
				answer += pairFound;
			}
		}
		return answer;
	}
	//	String Permutation in a longer string
	//	Given a smaller string s and a bigger string b, design an algorithm to find all permutations of the shorter
	//	string within the longer one. Print all location of each permutation
	//  example: find s:abbc occurences in cbabadcbababbc
	static void findAllPermutationsInString(String s, String b){

	}
	
	
	public static void main(String[] args) {
//		K-Difference of array
/*		int[] arr = {1, 7, 5, 9, 2, 12, 3};
		int k = 2;
		System.out.println(getPairsWithKDifference(k, arr));
		System.out.println(getPairsWithKDifferenceOptimal(k, arr));
*/	
//		String Permutation 
		


	}
}
