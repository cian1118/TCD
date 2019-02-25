// -------------------------------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

        //todo: implement the sort
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
            else if (j > high)          a[k] = aux[j++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[j++];
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

        //todo: implement the sort
        return a;

    }//end selectionsort




    public static void main(String[] args) {
        File data = new File("numbers10.txt");
        try {
            FileReader fileIn = new FileReader(data);
            BufferedReader bf = new BufferedReader(fileIn);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //todo: do experiments as per assignment instructions
    }

}//end class
