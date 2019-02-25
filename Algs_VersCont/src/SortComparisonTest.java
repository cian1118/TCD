import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Cian Higgins
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    private double array10[] =  {2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31, 4849.83, 1518.63};
    private double sorted10[] = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    @Test
    public void insertionSortTest() {
        assertArrayEquals(sorted10, SortComparison.insertionSort(array10),0);
    }

    @Test
    public void quickSortTest() {
        assertArrayEquals(sorted10, SortComparison.quickSort(array10), 0);
    }

    @Test
    public void mergeIterativeTest() {
        assertArrayEquals(sorted10, SortComparison.mergeSortIterative(array10), 0);
    }

    @Test
    public void mergeRecursiveTest() {
        assertArrayEquals(sorted10, SortComparison.mergeSortRecursive(array10), 0);
    }

    @Test
    public void selectionSortTest() {

    }

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
        //TODO: implement this method
        /* runs all the experiments on SortComparison
        described below and prints the time in milliseconds
        that each method execution took
        */
    }

}
