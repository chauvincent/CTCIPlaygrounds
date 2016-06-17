public class CrackingTheCodingInterview {

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
	// Given an array of distinct integer values, find the number of pairs that have the difference k.
	// ex: Input: {1, 7, 5, 9, 2, 12, 3}  k = 1 -> there are three pairs (1,3), (3,5), (7,9)
	static int[] getPairsWithKDifference(int k, int[] arr){
		
		
		return null;
	}
	public static void main(String[] args) {
		// K-Difference of array
		int[] arr = {1, 7, 5, 9, 2, 12, 3};
		int k = 2;
		System.out.println(getPairsWithKDifference(k, arr));
		
	}

}
