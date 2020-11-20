/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful variable names formatted properly (in lowerCamelCase)?
        Mark out of 5: 5        Comment: All variable names easy to understand and formatted correctly.
 2. Did I indent the code appropriately?
        Mark out of 5: 5        Comment: Code indented automatically with IDE
 3. Did I write the determineStarNumber or determineTriangleNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20       Comment: determineTriangleNumber function written with int return type, parameter int
                                    index and function body correctly finds the triangle number.
 4. Did I write the isStarNumber function correctly (parameters, return type and function body) and invoke it correctly?
       Mark out of 20: 20       Comment: isStarNumber function: return type boolean, parameter int testNumber. Body
                                    correctly determines if the passed int is a star number or not.
 5. Did I calculate and/or check triangle numbers correctly?
       Mark out of 15: 15       Comment: Triangle numbers are calculated correctly then passed through the isStarNumber
                                    function to see if it is also a star number.
 6. Did I loop through all possibilities in the program using system defined constants to determine when to stop?
       Mark out of 10: 10       Comment: Loops to find all possible 'triangle stars' that are of type int. Program
                                    terminates if the triangle number gets larger than int MAX_VALUE.
 7. Does my program compute and print all the correct triangular star numbers?
       Mark out of 20: 20       Comment: Yes, the program correctly finds all numbers of type int that are simultaneously
                                    star numbers and triangle numbers
 8. How well did I complete this self-assessment?
        Mark out of 5: 5        Comment: Marks and detailed comments given for each question of the self-assessment.
 Total Mark out of 100 (Add all the previous marks): 100
*/
package Week10;

public class TriangularStars {
    public static void main(String[] args) {
        int index = 1;
        int triangleNumber = 0;
        System.out.println("The following numbers are simultaneously star numbers and triangle numbers:");
        while ((triangleNumber <= Integer.MAX_VALUE) && (triangleNumber >= 0)) {
            triangleNumber = determineTriangleNumber(index);
            if (isStarNumber(triangleNumber)) {
                System.out.println(triangleNumber);
                index++;
            } else {
                index++;
            }
        }
    }

    public static boolean isStarNumber(int testNumber) {
        int starNumbIndex = 1;
        int starNumber = 0;
        while (starNumber != testNumber && starNumber < testNumber) {
            starNumber = (6 * (starNumbIndex) * (starNumbIndex - 1)) + 1;
            starNumbIndex++;
        }
        return (starNumber == testNumber);
    }

    public static int determineTriangleNumber(int triangleNumbIndex) {
        int triangleNumber = 0;
        int indexForCalc = 1;
        while (indexForCalc <= triangleNumbIndex) {
            triangleNumber += indexForCalc;
            indexForCalc++;
        }
        return triangleNumber;
    }
}