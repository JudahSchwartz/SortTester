import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class OtherSorts {
    public static void main(String[] args) {
        for(int j = 0; j < 10; j++) {
            System.out.println("Iteration " + (j+ 1));
            for (int i = 10; i < 10001; i *= 10) {
                System.out.println("Array Length " + i);
                int arr[] = Main.getUnsortedArray(i);
                int n = arr.length;
                int accesses = Radix.radixsort(arr, n);
                System.out.println(accesses);
                CountingSort on = new CountingSort();
                accesses = on.sort(Main.getUnsortedArray(i));
                System.out.println(accesses);
                BucketSort.sort(toIntegerArray(Main.getUnsortedArray(i)));
                System.out.println(BucketSort.total);
                BucketSort.total = 0;
            }
        }
    }

    private static Integer[] toIntegerArray(int[] unsortedArray) {
        Integer[] arr = new Integer[unsortedArray.length];
       for(int i = 0; i < unsortedArray.length; i++)
       {
           arr[i] = unsortedArray[i];
       }
        return arr;
    }
}
class BucketSort {
    static int total = 0;
    private static final int DEFAULT_BUCKET_SIZE = 5;

    public static void sort(Integer[] array) {
        sort(array, DEFAULT_BUCKET_SIZE);
    }

    public static void sort(Integer[] array, int bucketSize) {
        if (array.length == 0) {
            return;
        }

        // Determine minimum and maximum values
        Integer minValue = array[0];
        total++;
        Integer maxValue = array[0];
        total++;
        for (int i = 1; i < array.length; i++) {
            total++;
            if (array[i] < minValue) {
                {
                    minValue = array[i];
                    total++;
                }
            } else
            {
                total++;
                if (array[i] > maxValue) {
                    total++;
                    maxValue = array[i];
                }
            }
        }

        // Initialise buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        // Distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
            total+=3;
        }

        // Sort buckets and place back into input array
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            insertionSort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                total+=2;
                array[currentIndex++] = bucketArray[j];
            }
        }
    }
    static void insertionSort(Integer arr[])

    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            total+=2;
            int j = i-1;
            /* Move elements of arr[0..i-1], that are

               greater than key, to one position ahead

               of their current position */
            while (j>=0 && arr[j] > key)

            {
                total+=2;

                arr[j+1] = arr[j];
                total +=2;

                j = j-1;



            }

            arr[j+1] = key;
            total++;

        }

    }
}
class Radix {

    static int totalAccesses = 0;
    // A utility function to get maximum value in arr[]
    static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        totalAccesses++;
        for (int i = 1; i < n; i++) {
            totalAccesses += 2;
            if (arr[i] > mx) {
                mx = arr[i];
                totalAccesses+=2;
            }
        }
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);
        totalAccesses+=10;

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
            totalAccesses++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            totalAccesses+=2;
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
            totalAccesses+=4;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
            totalAccesses+=2;
        }
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static int radixsort(int arr[], int n)
    {
        totalAccesses = 0;
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
        return totalAccesses;
    }

    // A utility function to print an array
    static void print(int arr[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }

}
class CountingSort
{

    int sort(int arr[])
    {
       int total = 0;
        int n = arr.length;

        // The output character array that will have sorted arr
        int output[] = new int[n];

        // Create a count array to store count of inidividul
        // characters and initialize count array as 0
        int count[] = new int[10_000_000];
        for (int i=0; i<10_000_000; ++i) {
            count[i] = 0;
            total++;
        }

        // store count of each character
        for (int i=0; i<n; ++i) {
            ++count[arr[i]];
            total++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i=1; i<=10_000_000-1; ++i) {
            total+=2;
            count[i] += count[i - 1];
        }

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n-1; i>=0; i--)
        {
            total+=3;
            output[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i<n; ++i) {
            arr[i] = output[i];
            total+=2;
        }
        return total;
    }

    // Driver method

}