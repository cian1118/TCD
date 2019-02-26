// -------------------------------------------------------------------------
/*
============
|| RESULTS ||
============
+---------------------------------------+
| FIRST RUN                             |
+---------------------------------------+
numbers10.txt
insertion : 5256 ns 		| 0.005256 ms
quick     : 14893 ns 		| 0.014893 ms
merge iter: 72873 ns 		| 0.072873 ms
merge recu: 14639 ns 		| 0.014639 ms
selection : 10141 ns 		| 0.010141 ms

numbers100.txt
insertion : 128262 ns 		| 0.128262 ms
quick     : 152146 ns 		| 0.152146 ms
merge iter: 70664 ns 		| 0.070664 ms
merge recu: 100564 ns 		| 0.100564 ms
selection : 141941 ns 		| 0.141941 ms

numbers1000.txt
insertion : 9047960 ns 		| 9.04796 ms
quick     : 7181612 ns 		| 7.181612 ms
merge iter: 242009 ns 		| 0.242009 ms
merge recu: 368244 ns 		| 0.368244 ms
selection : 4874777 ns 		| 4.874777 ms

numbers1000Duplicates.txt
insertion : 1780770 ns 		| 1.78077 ms
quick     : 1378148 ns 		| 1.378148 ms
merge iter: 228588 ns 		| 0.228588 ms
merge recu: 192376 ns 		| 0.192376 ms
selection : 5221799 ns 		| 5.221799 ms

numbersNearlyOrdered1000.txt
insertion : 297872 ns 		| 0.297872 ms
quick     : 4442460 ns 		| 4.44246 ms
merge iter: 118732 ns 		| 0.118732 ms
merge recu: 78426 ns 		| 0.078426 ms
selection : 5134963 ns 		| 5.134963 ms

numbersReverse1000.txt
insertion : 508239 ns 		| 0.508239 ms
quick     : 4361259 ns 		| 4.361259 ms
merge iter: 114338 ns 		| 0.114338 ms
merge recu: 67667 ns 		| 0.067667 ms
selection : 8203375 ns 		| 8.203375 ms

numbersSorted1000.txt
insertion : 10403 ns 		| 0.010403 ms
quick     : 452109 ns 		| 0.452109 ms
merge iter: 101361 ns 		| 0.101361 ms
merge recu: 71799 ns 		| 0.071799 ms
selection : 3366292 ns 		| 3.366292 ms
+---------------------------------------+
| SECOND RUN                            |
+---------------------------------------+
numbers10.txt
insertion : 5645 ns 		| 0.005645 ms
quick     : 15758 ns 		| 0.015758 ms
merge iter: 79411 ns 		| 0.079411 ms
merge recu: 15017 ns 		| 0.015017 ms
selection : 9299 ns 		| 0.009299 ms

numbers100.txt
insertion : 104325 ns 		| 0.104325 ms
quick     : 144539 ns 		| 0.144539 ms
merge iter: 65111 ns 		| 0.065111 ms
merge recu: 112097 ns 		| 0.112097 ms
selection : 146352 ns 		| 0.146352 ms

numbers1000.txt
insertion : 10309986 ns 	| 10.309986 ms
quick     : 6383814 ns 		| 6.383814 ms
merge iter: 3320653 ns 		| 3.320653 ms
merge recu: 3075800 ns 		| 3.0758 ms
selection : 4765213 ns 		| 4.765213 ms

numbers1000Duplicates.txt
insertion : 2396643 ns 		| 2.396643 ms
quick     : 1539684 ns 		| 1.539684 ms
merge iter: 244119 ns 		| 0.244119 ms
merge recu: 186146 ns 		| 0.186146 ms
selection : 445511 ns 		| 0.445511 ms

numbersNearlyOrdered1000.txt
insertion : 311068 ns 		| 0.311068 ms
quick     : 24128946 ns 	| 24.128946 ms
merge iter: 107289 ns 		| 0.107289 ms
merge recu: 99374 ns 		| 0.099374 ms
selection : 289998 ns 		| 0.289998 ms

numbersReverse1000.txt
insertion : 607801 ns 		| 0.607801 ms
quick     : 456544 ns 		| 0.456544 ms
merge iter: 146933 ns 		| 0.146933 ms
merge recu: 71608 ns 		| 0.071608 ms
selection : 325584 ns 		| 0.325584 ms

numbersSorted1000.txt
insertion : 10014 ns 		| 0.010014 ms
quick     : 493943 ns 		| 0.493943 ms
merge iter: 116269 ns 		| 0.116269 ms
merge recu: 68858 ns 		| 0.068858 ms
selection : 280935 ns 		| 0.280935 ms

QUESTIONS:
a.
b.
c.
d.
e.


 */
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Cian Higgins
 *  @version HT 2019
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]) {
        int n = a.length;
        for (int i=1; i<n; ++i) {
            double key = a[i];
            int j = i-1;

            while (j>=0 && a[j] > key) {
                a[j+1] = a[j];
                j = j-1;
            }
            a[j+1] = key;
        }
        return a;

    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]) {
        recursiveQuickSort(a, 0, a.length-1);
        return a;
    }

    private static void recursiveQuickSort(double a[], int low, int high) {
        if (high <= low) {
            return;
        }
        int pivotIndex = partition(a, low, high);
        recursiveQuickSort(a, low, pivotIndex-1);
        recursiveQuickSort(a, pivotIndex+1, high);
    }

    private static int partition(double a[], int low, int high) {
        int i = low;
        int j = high+1;
        double pivot = a[low];
        while (true) {
            while (a[++i] < pivot) {
                if (i == high) break;
            }
            while (a[--j] > pivot) {
                if (j == low) break;
            }
            if (i >= j) break;
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        a[low] = a[j];
        a[j] = pivot;
        return j;
    }
    //end quickSort


    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
        int length = a.length;
        double[] aux = new double[length];
        for (int i = 1; i < length; i = i + i) {
            for (int low = 0; low < length - i; low += i + i) {
                merge(a, aux, low, low+i-1, Math.min(low+i+i-1,length-1));
            }
        }
        return a;

    }//end mergeSortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length-1);
        return a;

    }

    private static void sort(double[] a, double[] aux, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(a, aux, low, mid);
        sort(a, aux, mid+1, high);
        merge(a, aux, low, mid, high);
    }

    private static void merge(double[] a, double[] aux, int low, int mid, int high) {
        //copy
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        //merge
        int i = low, j = mid+1;
        for (int k = low; k <= high; k++) {
            if      (i > mid)           a[k] = aux[j++];
            else if (j > high)          a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }
    //end mergeSortRecursive


    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
        int length = a.length;
        for (int i = 0; i < length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            double temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
        return a;

    }//end selectionsort


    public static void main(String[] args) {
        FileReader fReader;

        //saving file paths to file array
        File files[] = new File[7];
        files[0] = new File("./src/numbers10.txt");
        files[1] = new File("./src/numbers100.txt");
        files[2] = new File("./src/numbers1000.txt");
        files[3] = new File("./src/numbers1000Duplicates.txt");
        files[4] = new File("./src/numbersNearlyOrdered1000.txt");
        files[5] = new File("./src/numbersReverse1000.txt");
        files[6] = new File("./src/numbersSorted1000.txt");

        int arraySize = 1;
        for (int count = 0; count < 7; count++) {
            try {
                fReader = new FileReader(files[count]);
                BufferedReader bReader = new BufferedReader(fReader);

                if (count < 3) arraySize *= 10;
                double a[] = new double[arraySize];

                String currLine;
                int j = 0;
                while ((currLine = bReader.readLine()) != null) {
                    double currDouble = Double.parseDouble(currLine);
                    a[j] = currDouble;
                    j++;
                }
                System.out.println(files[count].getName());
                timeSortsInMs(a);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void timeSortsInMs(double[] a) {
        long startTime, endTime, duration;

        startTime = System.nanoTime();
        insertionSort(a);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("insertion : " +duration + " ns \t\t| " + duration/1e6 + " ms" );

        startTime = System.nanoTime();
        quickSort(a);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("quick     : " + duration + " ns \t\t| " + duration/1e6 + " ms" );

        startTime = System.nanoTime();
        mergeSortIterative(a);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("merge iter: " + duration + " ns \t\t| " + duration/1e6 + " ms" );

        startTime = System.nanoTime();
        mergeSortRecursive(a);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("merge recu: " + duration + " ns \t\t| " + duration/1e6 + " ms" );

        startTime = System.nanoTime();
        selectionSort(a);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("selection : " + duration + " ns \t\t| " + duration/1e6 + " ms" + "\n");

    }

}//end class
