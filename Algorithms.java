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
	// ex: Input: {1, 7, 5, 9, 2, 12, 3}  k = 2 -> there are three pairs (1,3), (3,5), (7,9)
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
	// 1.2
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
	//1.3
	static void URLify(String s, int n){
		char []inputString = s.toCharArray();
		int spacesCount = 0;
		for(int i = 0; i < n; i++){
			if(inputString[i] == ' ')
				spacesCount++;
		}
		int newSize = n + (spacesCount * 3) + 1;
		int spacesProcessed = 0;
		int index = 0, index2 = 0;		
		char []outputArr = new char[newSize];

		while(spacesProcessed != spacesCount){
			while(inputString[index] != ' '){
				outputArr[index2] = inputString[index];
				index2++; index++;
			}
			outputArr[index2+1] = '%';
			outputArr[index2+2] = '2';
			outputArr[index2+3] = '0';
			index2 += 4; index++;
			spacesProcessed++;
		}
		while(index2 != newSize){
			outputArr[index2] = inputString[index];
			index++; index2++;
		}
		for(int i = 0; i < outputArr.length; i++)
			System.out.print(outputArr[i]);
	}
	// 1.4
	static Boolean permutationPalindrome(String s){
		s= s.toLowerCase();
		int letterBucket[] = new int[25];
		for(char c : s.toCharArray()){
			letterBucket[c - 'a']++;
		}
		Boolean flag = false;
		for(int i : letterBucket){
			System.out.println(i);
		}
		return flag;
	}
	// 1.5 
	static Boolean checkSameLength(String s1, String s2){
		int difference = 0;
		char[] strArr1 = s1.toCharArray();
		char[] strArr2 = s2.toCharArray();
		for(int i = 0; i < strArr1.length; i++){
			if (strArr1[i] != strArr2[i]){
				difference++;
			}
		}
		if (difference == 1) return true;
		return false;
	}
	static Boolean isOneAway(String s1, String s2){
		String sameLength = "";
		String sameLength2 = "";
		if (s1.length() + 1 != s2.length() || s2.length() + 1 != s1.length()) 
			return false;
		if (s1.length() == s2.length()) 
			return checkSameLength(s1,s2);
	
		Boolean insertReplace = (s1.length() + 1 == s2.length()); 
		sameLength = insertReplace ? s1 + 'X' : s2 + 'X';
		sameLength2 = insertReplace ? s2 : s1;
		return checkSameLength(sameLength, sameLength2);
	}
	//	1.6
	static String compressString(String s){
		char[] stringArr = s.toCharArray();
		StringBuilder compressed = new StringBuilder();
		int index = 0, index2 = 1;
		int count = 1;
		int offset = 0;
		Boolean done = false;
		while(!done){
			if(stringArr[index] == stringArr[index2]){
				count++; offset++;
			}else{
				compressed.append(stringArr[index]);
				compressed.append(count);
				count = 1;
			}
			index++; index2++;
			if(index2 == s.length()){
				compressed.append(stringArr[offset]);
				int lenOfLast = s.length() - offset;
				compressed.append(lenOfLast);
				return compressed.toString();
			}
		}
		return compressed.toString();
	}
	
	static int[][] rotateMatrix(int[][] M){
		if (M.length != M[0].length) 
			return M;
		int N = M.length;
		for (int pivot = 0; pivot < N / 2; pivot++) {
			int low = pivot;
			int high = N - pivot - 1;
			for(int i = low; i < high; i++) {
				int c = i - low;
				// below diagonal
				M[low][i] = M[high-c][low];   			
				M[high-c][low] = M[high][high-c]; 
				// above diagonal
				M[high][high-c] = M[i][high]; 
				M[i][high] = M[low][i];
			}
		}
		return M;
	}
	static void zeroMatrix(int[][] M){
		/*  1 2 0    0 0 0
		 *  2 0 1 -> 0 0 0
		 *  0 3 5    0 0 0
		 */

		boolean[] rowHasZero = new boolean[M.length];
		boolean[] colHasZero = new boolean[M[0].length];
		
		for (int i = 0; i < M.length; i++){
			for(int j = 0; j < M[0].length;j++){
				if(M[i][j] == 0){
					rowHasZero[i] = true;
					colHasZero[j] = true;
				}
			}
		}
		
		for(int i = 0; i < rowHasZero.length; i++){
			if (rowHasZero[i]){
				for(int j = 0; j < M[0].length; j++){
					M[i][j] = 0;
				}
			}
		}
		for(int j = 0; j < colHasZero.length; j++){
			if(colHasZero[j])
				for(int k = 0; k < M.length; k++){
					M[k][j] = 0;
				}
		}
		
		for(int i = 0; i < rowHasZero.length; i++){
			for(int j = 0; j < colHasZero.length; j++){
				System.out.print(M[i][j] + " ");
			}
			System.out.println("");
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
/*
	String s = "Mr John Smith     ";
	int sLength = 13;
	URLify(s, sLength);
*/		

//										1.4 Palindrome Permutation: Given a string check if it is a permutation of a palindrome
//	Example: Input: Tact Coa , tactcoapapa   Output: True since "taco cat", "atco cta"    ~~~ tacOcat  atcOcta
/*
		String s = "tactcoapapa";
		System.out.println(permutationPalindrome(s)); // should return true
*/	
//										1.5 One Away: There are three types of edits that can be performed on strings; 
//	Insert a characater, remove. replace. Given two strings, write a function to check if they are one edit away;
/*
	String s1 = "waterbottle";
	String s2 = "waterbottl";
	String s3 = "waterbottles";
	String s4 = "waterbottlq";
	System.out.println(isOneAway(s3,s2));
*/
//										1.6 String Compression: implement a method to compres a string with repeated characters
//	Example: aabcccccaaa -> a2b1c5a3   but return original string if compressed version is longer. assume lowercase a-z)
/*
		String s = "aabcccccaaa";
		System.out.println(compressString(s));
*/
//										1.7 Rotate Matrix: NxN matrix, each pixel is 4 bytes, rotate by 90 degrees.
/*
		int N = 3;
		int counter = 9;
		int [][]M = new int[N][N];
		for(int i = 0; i < M.length; i++)
			for(int j = 0; j < M.length; j++)
				M[i][j] = ++counter + 10;
		for(int i = 0; i < M.length; i++){
			for(int j = 0; j < M.length; j++){
				System.out.print(M[i][j] + " ");
			}
		System.out.println("");
		}
		int [][]M2 = rotateMatrix(M);
		for(int i = 0; i < M.length; i++){
			for(int j = 0; j < M.length; j++){
				System.out.print(M2[i][j] + " ");
			}
		System.out.println("");
		}
	
*/	
//										1.8 Zero Matrix: Write an algorithm st if an element in a MxN matrix is 0, its entire row and column are set to 0
//		int[][]M = new int[5][3];
//		for(int i = 0; i < 5; i++){
//			for(int j = 0; j < 3; j++){
//				if(i % 2 == 0 && j%3 == 1){
//					M[i][j] = 0;
//				}else{
//					M[i][j] = i + j;
//				}
//					
//			}
//		}
//		zeroMatrix(M);

		
	}
}
