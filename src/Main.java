import java.util.Random;

public class Main {
    static final int sizeOfArray = 10_000;
    public static void main(String[] args) {
        //bubble sort
        var startTime = System.nanoTime();
        var nums = bubbleSort(getUnsortedArray());
        var totalTime = System.nanoTime() - startTime;
        System.out.println(String.format("Num swaps for %s: %s, num comparisons %s, total time in nanoseconds %s","bubbleSort",nums.swaps,nums.comparisons,totalTime));

        //bubble sort
        startTime = System.nanoTime();
        nums = selectionSort(getUnsortedArray());
        totalTime = System.nanoTime() - startTime;
        System.out.println(String.format("Num swaps for %s: %s, num comparisons %s, total time in nanoseconds %s","Selection Sort",nums.swaps,nums.comparisons,totalTime));

        startTime = System.nanoTime();
        MergeSort ms = new MergeSort();
        ms.sort(getUnsortedArray(),0,sizeOfArray-1);
        totalTime = System.nanoTime() - startTime;
        nums = ms.nums;
        System.out.println(String.format("Num comparisons for %s: %s, num copies %s, total time in nanoseconds %s","Merge Sort",nums.comparisons,nums.copyOps,totalTime));

    }
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    static EfficiencyNumbers selectionSort(int arr[]) {
        var nums = new EfficiencyNumbers();
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                nums.comparisons++;
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            }

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
            nums.swaps++;
        }
        return nums;
    }
        static EfficiencyNumbers bubbleSort(int arr[])
    {
        var nums = new EfficiencyNumbers();
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j + 1]) {

                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    nums.swaps++;
                }
                nums.comparisons++;
            }
            return nums;
    }
    private static int[] getUnsortedArray() {
        Random random = new Random();
        int[] randArray = new int[sizeOfArray];
        for(int i =0; i < sizeOfArray; i++)
        {
            randArray[i] = random.nextInt(10_000_000);
        }
        return randArray;
    }
}
class EfficiencyNumbers
{
    int comparisons;
    int swaps;
    int copyOps;
}

/* Java program for Merge Sort */
class MergeSort {
    EfficiencyNumbers nums = new EfficiencyNumbers();
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
            nums.copyOps++;
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
            nums.copyOps++;
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            nums.comparisons+=2;

            nums.comparisons++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            nums.copyOps++;
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            nums.comparisons++;
            nums.copyOps++;
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            nums.comparisons++;
            nums.copyOps++;
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r) {
        if (l < r) {
            nums.comparisons++;
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
}