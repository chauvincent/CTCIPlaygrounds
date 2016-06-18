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
			}
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
	
	// 1.1 isUnique, cannot use addition DS ~ O(N) if length < 128, else pigeon hole effect O(1)
	static Boolean isUnique(String s){
		if(s.length() > 128) return false;
		int []characterBucket = new int[128];
		for(char c : s.toCharArray()){
			if(characterBucket[c] != 0){
				return false;
			}else{
				characterBucket[c]++;
			}	
		}
		return true;
	}
	static Boolean checkPermutation(String s, String s2){
		if(s.length() != s2.length()) return false;
		int []letterBucket1 = new int[128], letterBucket2 = new int[128];
		char[] stringArr = s.toCharArray(), stringArr2 = s2.toCharArray();
		for(int i = 0; i < s.length(); i++){
			letterBucket1[stringArr[i]]++;
			letterBucket2[stringArr2[i]]++;
		}
		if (Arrays.equals(letterBucket1, letterBucket2)) return true;
		return false;
	} // O(S)
	
	static void URLify(String s, int n){
		char []inputString = s.toCharArray();
		int spacesCount = 0;
		for(int i = 0; i < n; i++){
			if(inputString[i] == ' ')
				spacesCount++;
		}
		char []outputArr = new char[n + (spacesCount * 3) + 1];
		for(int j = 0; j < n; j++){
			if(inputString[j]!=' '){
				outputArr[j] = inputString[j];
			}else{
				outputArr[j] = '%';
				j = j + 1;
				outputArr[j] = '2';
				j = j + 2;
				outputArr[j] = '0';
			}
		}
		for(char c : outputArr){
			System.out.print(c);
		}
	}
	
	public static void main(String[] args) {
//													K-Difference of array
/*		int[] arr = {1, 7, 5, 9, 2, 12, 3};
		int k = 2;
//		System.out.println(getPairsWithKDifference(k, arr));
		System.out.println(getPairsWithKDifferenceOptimal(k, arr));
*/	
//										String Permutation (should have 7 permutations of s inside b)
/*		String s = "abbc";
		String b = "cbabadcbbabbcbabaabccbabc";
		System.out.println(findAllPermutationsInString(s, b));
*/

// 											Print All Permutations of a Given String
//		generatePermutations("tag");
		
//												CTCI Chapter 1 Problems :)
//
// 										1.1 isUnique no additional Data Structures
/* 
	String s = "in my mind in my head, this is where we all came from...";
	String s2 = "abcdefg";
	System.out.println(isUnique(s2));
*/
// 										1.2 checkPermutation: Given two strings find out if they are permutation of each other
/*	String s = "ta.g";
	String s2 = "a24..";
	System.out.println(checkPermutation(s,s2));
*/
//										1.3 URLify: write a method to replace all spaces with %20, given "true" length of string
	String s = "Mr John Smith     ";
	int sLength = 13;
	URLify(s, sLength);
		
	}
}
