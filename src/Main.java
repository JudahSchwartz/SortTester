import java.util.Arrays;

import java.util.Random;



public class Main {

    static final int NUM_ITERATIONS = 10;

    static EfficiencyNumbers[] bubbleNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] selectionNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] mergeNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] quickNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] insertionNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] heapNumbers = new EfficiencyNumbers[NUM_ITERATIONS];
    static EfficiencyNumbers[] binaryInsertionNumbers = new EfficiencyNumbers[NUM_ITERATIONS];




//    public static void main(String[] args) {
//
//        for (int len = 10; len < 10_001;len*=10) {
//
//            System.out.println("Length of " + len);
//            for (int i = 0; i < NUM_ITERATIONS; i++) {
//
//                System.out.println("____ iteration " + (i+1));
//                bubbleNumbers[i] = new EfficiencyNumbers();
//                bubbleSort(getUnsortedArray(len), bubbleNumbers[i]);
//                printOut("bubblesort ",bubbleNumbers[i]);
//
//                // System.out.println(String.format("Num swaps for %s: %s, num comparisons %s, total time in nanoseconds %s", "bubbleSort", nums.swaps, nums.comparisons, totalTime));
//
//                selectionNumbers[i] = new EfficiencyNumbers();
//                selectionSort(getUnsortedArray(len), selectionNumbers[i]);
//                printOut("selectionSort ",bubbleNumbers[i]);
//
//                binaryInsertionNumbers[i] = new EfficiencyNumbers();
//                binaryInsertionSort(getUnsortedArray(len), binaryInsertionNumbers[i]);
//                printOut("",binaryInsertionNumbers[i]);
//               // cocktailSort(getUnsortedArray(len), cocktailNumbers);
//
//                //System.out.println(String.format("Num swaps for %s: %s, num comparisons %s, total time in nanoseconds %s", "Selection Sort", nums.swaps, nums.comparisons, totalTime));
//
//
//
//
//
//               // shellSort(getUnsortedArray(len), shellNumbers);
//                mergeNumbers[i] = new EfficiencyNumbers();
//                MergeSort ms = new MergeSort(mergeNumbers[i]);
//                ms.sort(getUnsortedArray(len), 0, len - 1);
//                printOut("merge sort",mergeNumbers[i]);
//
//
//
//                // System.out.println(String.format("Num comparisons for %s: %s, num copies %s, total time in nanoseconds %s", "Merge Sort", nums.comparisons, nums.copyOps, totalTime));
//
//
//
//
//                quickNumbers[i] = new EfficiencyNumbers();
//                QuickSort qs = new QuickSort(quickNumbers[i]);
//                qs.sort(getUnsortedArray(len), 0, len - 1);
//                printOut("Quicksort ",quickNumbers[i]);
//
//                //  System.out.println(String.format("Num comparisons for %s: %s, num swaps %s, total time in nanoseconds %s", "Quick Sort", nums.comparisons, nums.swaps, totalTime));
//
//                insertionNumbers[i] = new EfficiencyNumbers();
//                insertionSort(getUnsortedArray(len), insertionNumbers[i]);
//                printOut("insertion sort",insertionNumbers[i]);
//
//
//
//                //   System.out.println(String.format("Num copyOperations for %s: %s, num comparisons %s, total time in nanoseconds %s", "insertion Sort", nums.copyOps, nums.comparisons, totalTime));
//
//
//
//                heapNumbers[i] = new EfficiencyNumbers();
//                HeapSort hs = new HeapSort(heapNumbers[i]);
//                hs.sort(getUnsortedArray(len));
//                printOut("heap sort", heapNumbers[i]);
//
//
//               // BST bst = new BST(bstNumbers);
//
//               // bst.treeins(getUnsortedArray(len));
//
//              //  bst.inorderRec(bst.root);
//
//
//
//                // System.out.println(String.format("Num comparisons for %s: %s, num copies %s, num swaps : %s total time in nanoseconds %s", "heap Sort", nums.comparisons, nums.copyOps, nums.swaps, totalTime));
//
//
//
//            }
//
//          //  printResults(len);
//
//        }
//
//    }



//    private static void printResults(int len) {
//
//        averageOut(bubbleNumbers);
//
//        averageOut(selectionNumbers);
//
//        averageOut(mergeNumbers);
//
//        averageOut(quickNumbers);
//
//        averageOut(insertionNumbers);
//
//        averageOut(heapNumbers);
//
//       // averageOut(bstNumbers);
//
//      //  averageOut(cocktailNumbers);
//
//     //   averageOut(shellNumbers);
//
//        averageOut(binaryInsertionNumbers);
//
//
//
//        System.out.printf("\n\n\nResults for length %d:",len);
//
//        printOut("bubbleSort",bubbleNumbers);
//
//        printOut("Selection sort", selectionNumbers);
//
//        printOut("Merge Sort", mergeNumbers);
//
//        printOut("QuickSort", quickNumbers);
//
//        printOut("insertion sort",insertionNumbers);
//
//        printOut("Heap sort",heapNumbers);
//
//       // printOut("BST sort",bstNumbers);
//
//        //printOut("cocktail sort",cocktailNumbers);
//
//        //printOut("Shell sort",shellNumbers);
//
//        printOut("Binary Insertion",binaryInsertionNumbers);
//
//
//
//        reset(bubbleNumbers);
//
//        reset(selectionNumbers);
//
//        reset(mergeNumbers);
//
//        reset(quickNumbers);
//
//        reset(insertionNumbers);
//
//        reset(heapNumbers);
//
//      //  reset(bstNumbers);
//
//        //reset(cocktailNumbers);
//
//        //reset(shellNumbers);
//
//        reset(binaryInsertionNumbers);
//
//    }



    private static void reset(EfficiencyNumbers nums) {

        nums = new EfficiencyNumbers();



    }



    private static void averageOut(EfficiencyNumbers nums) {

        nums.copyOps/=NUM_ITERATIONS;

        nums.comparisons/=NUM_ITERATIONS;

        nums.swaps/=NUM_ITERATIONS;

    }



    private static void printOut(String name, EfficiencyNumbers nums)

    {

        System.out.printf("%d\n",nums.comparisons);

    }



    // Merges two subarrays of arr[].

    // First subarray is arr[l..m]

    // Second subarray is arr[m+1..r]



    static void shellSort(int arr[],EfficiencyNumbers nums) {

        int n = arr.length;

        nums.copyOps++;



        // Start with a big gap, then reduce the gap

        for (int gap = n / 2; gap > 0; gap /= 2) {

            nums.comparisons++;

            nums.copyOps++;

            // Do a gapped insertion sort for this gap size.

            // The first gap elements a[0..gap-1] are already

            // in gapped order keep adding one more element

            // until the entire array is gap sorted

            for (int i = gap; i < n; i += 1) {

                nums.comparisons++;

                nums.copyOps++;

                // add a[i] to the elements that have been gap

                // sorted save a[i] in temp and make a hole at

                // position i

                int temp = arr[i];

                nums.copyOps++;



                // shift earlier gap-sorted elements up until

                // the correct location for a[i] is found

                int j;



                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {

                    nums.comparisons += 2;

                    nums.copyOps++;

                    arr[j] = arr[j - gap];

                }

                // put temp (the original a[i]) in its correct

                // location

                arr[j] = temp;

                nums.copyOps++;

            }

        }

    }

    static void binaryInsertionSort(int array[],EfficiencyNumbers nums)

    {

        for (int i = 1; i < array.length; i++)

        {

            nums.copyOps+=2;

            nums.comparisons++;

            int x = array[i];



            // Find location to insert using binary search

            int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1);

            nums.copyOps+=2;

            nums.comparisons += Math.log(i)/Math.log(2);



            //Shifting array to one location right

            System.arraycopy(array, j, array, j+1, i-j);

            nums.copyOps+=(i-j);



            //Placing element at its correct location

            array[j] = x;

            nums.copyOps++;

        }

    }

    static void insertionSort(int arr[], EfficiencyNumbers nums)

    {

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

    }

    static void cocktailSort(int a[], EfficiencyNumbers nums)

    {

        boolean swapped = true;

        int start = 0;

        int end = a.length;

        nums.copyOps+=3;





        while (swapped == true) {

            nums.comparisons++;

            // reset the swapped flag on entering the

            // loop, because it might be true from a

            // previous iteration.

            swapped = false;

            nums.copyOps++;

            // loop from bottom to top same as

            // the bubble sort

            for (int i = start; i < end - 1; ++i) {

                nums.comparisons++;

                nums.copyOps++;



                nums.comparisons++;

                if (a[i] > a[i + 1]) {

                    int temp = a[i];

                    a[i] = a[i + 1];

                    a[i + 1] = temp;

                    swapped = true;

                    nums.swaps++;

                    nums.copyOps++;

                }

            }



            // if nothing moved, then array is sorted.

            nums.comparisons++;

            if (swapped == false)

                break;



            // otherwise, reset the swapped flag so that it

            // can be used in the next stage

            swapped = false;

            nums.copyOps++;

            // move the end point back by one, because

            // item at the end is in its rightful spot

            end = end - 1;

            nums.copyOps++;

            // from top to bottom, doing the

            // same comparison as in the previous stage

            for (int i = end - 1; i >= start; i--) {

                nums.comparisons++;

                nums.copyOps++;



                nums.comparisons++;

                if (a[i] > a[i + 1]) {

                    int temp = a[i];

                    a[i] = a[i + 1];

                    a[i + 1] = temp;

                    swapped = true;

                    nums.swaps++;

                    nums.copyOps++;

                }

            }



            // increase the starting point, because

            // the last stage would have moved the next

            // smallest number to its rightful spot.

            start = start + 1;

            nums.copyOps++;

        }

    }

    static void selectionSort(int arr[], EfficiencyNumbers nums) {

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

    }

    static void bubbleSort(int arr[], EfficiencyNumbers nums)

    {



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

    }

    public static int[] getUnsortedArray(int lengthOfArray) {

        Random random = new Random();

        int[] randArray = new int[lengthOfArray];

        for(int i =0; i < lengthOfArray; i++)

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

    EfficiencyNumbers nums;

    public MergeSort(EfficiencyNumbers nums){

        this.nums = nums;

    }



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



    public EfficiencyNumbers nums;

    public QuickSort(EfficiencyNumbers nums)

    {

        this.nums = nums;

    }



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

    EfficiencyNumbers nums;

    public HeapSort(EfficiencyNumbers nums){this.nums = nums;}

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



// Java program to

// implement Tree Sort

class BST {



    // Class containing left and

    // right child of current

    // node and key value

    EfficiencyNumbers nums;



    class Node {

        int key;

        Node left, right;



        public Node(int item) {

            key = item;

            left = right = null;

            nums.copyOps += 3;

        }

    }



    // Root of BST

    Node root;



    // Constructor

    BST(EfficiencyNumbers nums) {

        this.nums = nums;

        root = null;

        nums.copyOps++;

    }



    // This method mainly

    // calls insertRec()

    void insert(int key) {

        root = insertRec(root, key);

        nums.copyOps++;

    }



    /* A recursive function to

    insert a new key in BST */

    Node insertRec(Node root, int key) {



        nums.comparisons++;

        /* If the tree is empty,

        return a new node */

        if (root == null) {

            root = new Node(key);

            nums.copyOps++;

            return root;

        }



        /* Otherwise, recur

        down the tree */

        nums.comparisons++;

        if (key < root.key) {

            root.left = insertRec(root.left, key);

            nums.copyOps++;

        } else {

            nums.comparisons++;

        }

        if (key > root.key) {

            root.right = insertRec(root.right, key);

            nums.copyOps++;

        }



        /* return the root */

        return root;

    }



    // A function to do

    // inorder traversal of BST

    void inorderRec(Node root) {

        nums.comparisons++;

        if (root != null) {

            inorderRec(root.left);

            inorderRec(root.right);

        }

    }



    void treeins(int arr[]) {

        for (int i = 0; i < arr.length; i++) {

            nums.comparisons++;

            nums.copyOps++;

            insert(arr[i]);

        }



    }

}