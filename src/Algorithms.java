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
		//String answer = "", pairFound = "";
		StringBuilder answer = new StringBuilder();
		String pairFound = "", pairFound2 = "";
		int index = -1;
		for(int i = 0; i < arr.length; i++){
			ht.put(arr[i], i);
		}
		for(int i = 0; i < arr.length; i++){
			if(ht.containsKey(  arr[i]+k )){
				index = ht.get( (arr[i]+ k) );
				pairFound = ("(" + arr[i] + "," + arr[index] + "), ");
				answer.append(pairFound);
				//answer += pairFound;
			}else if(ht.containsKey(arr[i] - k)){
				index = ht.get( (arr[i] - k) );
				pairFound2 = ("(" + arr[i] + "," + arr[index] + "), ");
				answer.append(pairFound2);
			}
		}
		return answer.toString();
	}
	//	String Permutation in a longer string
	//	Given a smaller string s and a bigger string b, design an algorithm to find all permutations of the shorter
	//	string within the longer one. Print all location of each permutation
	//  example: find s:abbc occurences in cbabadcbababbc
	static String findAllPermutationsInString(String s, String b){
		int charBucket[] = new int[25];
		//String answer = "";
		StringBuilder answer = new StringBuilder();
		s = s.toLowerCase(); b = b.toLowerCase();
		for(char c : s.toCharArray()){
			int index = c - 'a';
			charBucket[index] += 1;
		}  // Constant of size O(S)
		char bArray[] = b.toCharArray();
		int windowSize = s.length();
		String temp = "";
		for (int i = 0; i < b.length()-windowSize+1; i++){
			int charBucket2[] = new int[25];
			for(int j = 0; j < windowSize; j++){
				charBucket2[bArray[i+j] - 'a'] += 1;
				temp += bArray[i+j];
			} // Window Size == S
			if(Arrays.equals(charBucket, charBucket2)){
				answer.append( " " + temp + " ");
			} else {
				temp = "";
			}
		} // O(S + (B * S) + S) ~ Big Oh of O(S)
		return answer.toString();
	}
	static void generatePermutations(String s){
		generatePermutation(s, "");
	}
	static void generatePermutation(String str, String prefix){
		// base case 
		if (str.length() == 0){
			System.out.println(prefix);
		} else {
			for (int i = 0; i < str.length(); i++){
				String remainder = str.substring(0,i) + str.substring(i+1);
				generatePermutation(remainder, prefix + str.charAt(i));
			}
		}
	} // O(n!) Brute Force
	public static void main(String[] args) {
//	K-Difference of array
/*		int[] arr = {1, 7, 5, 9, 2, 12, 3};
		int k = 2;
//		System.out.println(getPairsWithKDifference(k, arr));
		System.out.println(getPairsWithKDifferenceOptimal(k, arr));
*/	
		
//	String Permutation (should have 7 permutations of s inside b)
/*		String s = "abbc";
		String b = "cbabadcbbabbcbabaabccbabc";
		System.out.println(findAllPermutationsInString(s, b));
*/

// Print All Permutations of a Given String
//		generatePermutations("tag");
		


	}
}
