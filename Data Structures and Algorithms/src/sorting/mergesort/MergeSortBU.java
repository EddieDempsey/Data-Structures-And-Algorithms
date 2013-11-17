package sorting.mergesort;

import sorting.common.SortHelper;

// TO do - which insertion sort to use
@SuppressWarnings("rawtypes")
public class MergeSortBU
{

	// Merge a[lo..mid] with a[mid+1..hi].
	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid,
			int hi)
	{
		// i and j is used to point to the currently processing element in the
		// first and 2nd half respectively
		int i = lo, j = mid + 1;

		// Copy a[lo..hi] to aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		// Merge back to a[lo..hi].
		for (int k = lo; k <= hi; k++)
			if (i > mid) // 1st half is exhausted.
				a[k] = aux[j++];
			else if (j > hi) // 2st half is exhausted.
				a[k] = aux[i++];
			else if (SortHelper.less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	// bottom-up mergesort
	public static void sortBU(Comparable[] a)
	{
		int N = a.length;
		Comparable[] aux = new Comparable[N];
        
        // Do lg N passes
		for (int n = 1; n < N; n = n + n)
		{// n is the subarray size

            // i < N - n. The number of elements from position lo must be
            //  greater than the subarray size so that a split can occur and 
            //  merge can be done across the split.
            // The split always results in a two sorted arrays as the subarray
            //  size is an exponential multiple of the subarray size of the
            // previous iteration
			for (int lo = 0; lo < N - n; lo += n + n)
			{
				int hi = Math.min(lo + (n + n - 1), N - 1);
				merge(a, aux, lo, lo + n - 1, hi);
			}
		}
	}
}
