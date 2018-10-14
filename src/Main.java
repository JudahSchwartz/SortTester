import java.util.Random;

public class Main {
    static final int sizeOfArray = 20_000;
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

        startTime = System.nanoTime();
        QuickSort qs = new QuickSort();
        qs.sort(getUnsortedArray(),0,sizeOfArray-1);
        totalTime = System.nanoTime() - startTime;
        nums = qs.nums;
        System.out.println(String.format("Num comparisons for %s: %s, num swaps %s, total time in nanoseconds %s","Quick Sort",nums.comparisons,nums.swaps,totalTime));


        startTime = System.nanoTime();
        nums = insertionSort(getUnsortedArray());
        totalTime = System.nanoTime() - startTime;
        System.out.println(String.format("Num copyOperations for %s: %s, num comparisons %s, total time in nanoseconds %s","insertion Sort",nums.copyOps,nums.comparisons,totalTime));

        startTime = System.nanoTime();
        HeapSort hs = new HeapSort();
        hs.sort(getUnsortedArray());
        totalTime = System.nanoTime() - startTime;
        nums = hs.nums;
        System.out.println(String.format("Num comparisons for %s: %s, num copies %s, num swaps : %s total time in nanoseconds %s","heap Sort",nums.comparisons,nums.copyOps,nums.swaps,totalTime));

    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]


    static EfficiencyNumbers insertionSort(int arr[])
    {
        var nums = new EfficiencyNumbers();
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            nums.comparisons++;
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                nums.comparisons+=2;
                arr[j+1] = arr[j];
                j = j-1;
                nums.copyOps+=2;
            }
            arr[j+1] = key;
        }
        return nums;
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

// Java program for implementation of QuickSort
class QuickSort {

    public EfficiencyNumbers nums = new EfficiencyNumbers();

    /* This function takes last element as pivot,
           places the pivot element at its correct
           position in sorted array, and places all
           smaller (smaller than pivot) to left of
           pivot and all greater elements to right
           of pivot */
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            nums.comparisons++;
            // If current element is smaller than or
            // equal to pivot
            nums.comparisons++;
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                nums.swaps++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        nums.swaps++;

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(int arr[], int low, int high) {
        if (low < high) {
            nums.comparisons++;
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
}

// Java program for implementation of Heap Sort
class HeapSort {
    EfficiencyNumbers nums = new EfficiencyNumbers();
    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            nums.comparisons++;
            heapify(arr, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            nums.comparisons++;
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            nums.swaps++;
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
        nums.copyOps += 3;
        // If left child is larger than root
        nums.comparisons+=2;
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        nums.comparisons+=2;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        nums.comparisons++;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            nums.swaps++;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
}