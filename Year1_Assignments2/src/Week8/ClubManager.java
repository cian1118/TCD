/* SELF ASSESSMENT

Harness Class: Member variables (8/8 marks)
All my data members are declared, private and the ones that don't change are marked as private. I also have a constant for the maximum number of uses of a harness.
Comment: data members that don't change are marked as final, data members are private, I created a constant equal to 25 for the max numb of uses.

Harness Class: Harness constructor 1 & constructor 2 (6/6 marks)
I initialise all the variables using the parameters given and set the other members to reasonable default values.
Comment: the variables are all initialised. In constructor 2 defaults: timesUsed = 0, onLoan = false, nameOfMemberOnLoanTo = null.

Harness Class: checkHarness method (5/5 marks)
My method takes an instructor's name as a parameter, and if the harness is not on loan sets the instructor member variable to the given parameter value (assuming the instructor's name is not null/empty). It also resets the number of times the harness was used.
Comment: checks harness not on loan, sets times used to 0 and updates the instructor's name.

Harness Class: isHarnessOnLoan method (2/2 marks)
My method has no parameters and returns the value of the loan status variable.
Comment: method returns whether the harness is on loan or not.

Harness Class: canHarnessBeLoaned method (4/4 marks)
My method has no parameters and returns true if the harness is not on loan and if the number of times it was used is less than the maximum allowed number of times.
Comment: method returns true only if times used < 25 and harness is not currently on loan

Harness Class: loanHarness method (6/6 marks)
My method has a member name as a parameter and it checks if harness can be loaned by using the canHarnessBeLoaned method. If true, it sets the club member value to the parameter value, sets the on loan status to true and increments the number of times used variable.
Comment: if the harness can be loaned, the harness is set to onLoan and the name of the club member is set, times used is incremented

Harness Class: returnHarness method (5/5 marks)
My method has no parameters, checks if the harness is on loan, and if so, changes its on-loan status to false, and resets the club member value.
Comment: checks if harness is on loan, if it is the harness is returned - onLoan set to false and club member value set to null.

Harness Class: toString method (3/3 marks)
My method returns a String representation of all the member variables.
Comment: default toString used which shows all member variables.

HarnessRecords Class: member variables (3/3 marks)
I declare the member variable as a private collection of Harnesses
Comment: private ArrayList of harnesses declared

HarnessRecords Class: HarnessRecords constructor 1 & 2 (9/9 marks)
In the first constructor, I set the member variable to null [1 mark]. In the second constructor, I use the Java I/O to read data from a text file I created containing sets of Harness characteristics. I use these set of characteristics to create Harness objects and add them to the collection.
Comment: ArrayList set to null in constructor 1. In constructor 2 I use Java IO to read data from a text file, each harness in the text is added to the array list.

HarnessRecords Class: isEmpty method (1/1 marks)
I return true if the collection is null/empty and, false otherwise.
Comment: if the ArrayList equals null or .size() = 0, true is returned, otherwise false.

HarnessRecords Class: addHarness method (5/5 marks)
My method takes a Harness object as a parameter and adds the harness to the collection.
Comment: The harness take as a parameter is added to the ArrayList.

HarnessRecords Class: findHarness method (6/6 marks)
My method takes a make and model number as parameters. It checks if a harness with such properties exists and if it does it returns harness object, otherwise returns null.
Comment: the method loops through the ArrayList and attempts to find the harness with that make and model number. if found the harness is returned, otherwise null.

HarnessRecords Class: checkHarness method (6/6 marks)
must take instructor name, make and model number as parameters and return a Harness. If a harness with the make and model number exists by using the findHarness method and is not on loan, the Harness method checkHarness is called with the instructor name as a parameter and the updated Harness object is returned. If the harness is not available returns null.
Comment: make model and instructor taken as parameters, return type = Harness. findHarness is used and if the Harness is found & not on loan it will be "checked" by the passed instructor name.

HarnessRecords Class: loanHarness method (7/7 marks)
My method takes a club member name as a parameter and looks for an available harness by calling the method canHarnessBeLoaned be loaned. If an available harness is found it is loaned by using the Harness method loanHarness with the club member as a parameter, returning the harness. If there's no available harness null is returned.
Comment: club member name taken as parameter. while loop used to look for an available harness - i.e. one that is not on loan. If one is available the loanHarness method is used w/ member name, the harness is returned, if no harness available returns null.

HarnessRecords Class: returnHarness method (7/7 marks)
My method takes a make and model number as parameters. It checks if a harness with those properties exists by using the findHarness method. If the found harness is not null, it returns the harness object by using Harness method returnHarness, otherwise returns null.
Comment: make and model taken as parameters. findHarness used to get that harness if it exists. If it's found it is returned using the returnHarness method.

HarnessRecords Class: removeHarness method (8/8 marks)
My method takes a make and model number as parameters and check the collection for a harness with those properties and removes it. It returns the harness object if it is found, otherwise returns null.
Comment: make and model taken as parameters. finds a harness with those properties if one exists and removes it. The harness is returned if found & removed, otherwise null.

GUI (Java main line) (5/5 marks)
My test class has a menu which implements at least the five points specified using the HarnessRecords class methods.
Comment: The test class implements 5 of the harness records methods with a simple text/console based GUI. The second constructor from the HarnessRecords class is also used, it takes a number of harnesses from a text file & adds them to a collection.
*/
package Week8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class ClubManager {
    private static final int MAKE_MODEL_AND_INSTRUCTOR = 0;
    private static final int MAKE_AND_MODEL = 1;
    private static final int MAKE_INDEX = 0;
    private static final int MODEL_INDEX = 1;
    private static final int NAME_OF_INSTRUCTOR_INDEX = 2;

    public static void main(String[] args) {
        HarnessRecords harnessRecords = null;

        boolean finished = false;
        String userMessage = "1 Add a record for a new harness\n2 Remove a harness from the club\n3 Record that a " +
                "harness has been checked by an instructor\n4 Loan a harness to a member (if available) " +
                "\n5 Return a harness that is no longer in use by a member\n\nType the number of the action you " +
                "wish to perform, type 0 to exit:";

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("./src/Week8/harnesses.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            harnessRecords = new HarnessRecords(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!finished) {
            try {
                System.out.println(userMessage);
                Scanner inputScanner = new Scanner(System.in);
                int inputNumber = inputScanner.nextInt();
                if (inputNumber > 5 || inputNumber < 0) {
                    throw new InputMismatchException();
                }

                switch (inputNumber) {
                    case 0: //exit
                        finished = true;
                        break;

                    case 1: //add
                        String[] harnessInfo = getHarnessInfo(MAKE_MODEL_AND_INSTRUCTOR);
                        Harness harnessToAdd = new Harness(harnessInfo[MAKE_INDEX],
                                Integer.parseInt(harnessInfo[MODEL_INDEX]),
                                harnessInfo[NAME_OF_INSTRUCTOR_INDEX]);
                        System.out.println("The following harness will be added:\n" + harnessToAdd.toString());
                        harnessRecords.addHarness(harnessToAdd);
                        break;

                    case 2: //remove
                        harnessInfo = getHarnessInfo(MAKE_AND_MODEL);
                        String make = harnessInfo[MAKE_INDEX];
                        int modelNumber = Integer.parseInt(harnessInfo[MODEL_INDEX]);
                        Harness harnessToRemove = harnessRecords.removeHarness(make, modelNumber);
                        if (harnessToRemove != null) {
                            System.out.println("This harness has been removed:\n" + harnessToRemove.toString());
                        } else {
                            System.out.println("Error: Harness could not be found.");
                        }
                        break;

                    case 3: //check
                        harnessInfo = getHarnessInfo(MAKE_MODEL_AND_INSTRUCTOR);
                        Harness harnessToCheck = harnessRecords.checkHarness(harnessInfo[MAKE_INDEX],
                                Integer.parseInt(harnessInfo[MODEL_INDEX]),
                                harnessInfo[NAME_OF_INSTRUCTOR_INDEX]);
                        if (harnessToCheck != null) {
                            System.out.println("Harness " + harnessToCheck.toString() + " has been checked by "
                                    + harnessInfo[NAME_OF_INSTRUCTOR_INDEX]);
                        } else {
                            System.out.println("Error: Harness could not be found.");
                        }
                        break;

                    case 4: //loan
                        System.out.println("Enter name of club member that is borrowing the harness");
                        Scanner nameScanner = new Scanner(System.in);
                        String nameOfClubMember = nameScanner.nextLine();
                        Harness harnessToLoan = harnessRecords.loanHarness(nameOfClubMember);
                        if (harnessToLoan != null) {
                            System.out.println("The harness " + harnessToLoan.toString() + " has been loaned to " +
                                    nameOfClubMember + ".\n");
                        } else {
                            System.out.println("Error: There is no more harnesses available to loan.");
                        }
                        break;

                    case 5: //return
                        harnessInfo = getHarnessInfo(MAKE_AND_MODEL);
                        Harness harnessToReturn = harnessRecords.returnHarness(harnessInfo[MAKE_INDEX],
                                Integer.parseInt(harnessInfo[MODEL_INDEX]));
                        if (harnessToReturn != null) {
                            System.out.println("Harness " + harnessToReturn.toString() + " has been returned.\n");
                        } else {
                            System.out.println("Error: Harness could not be found.");
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input.\n");
            } catch (NumberFormatException e) {
                System.out.println("Error: Harness could not be found in the records.\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: Invalid input, please try again.\n");
            }
        }
    }

    public static String[] getHarnessInfo(int option) {
        Harness harness = null;
        if (option == MAKE_AND_MODEL) {
            System.out.println("Enter harness make and model number in that order and separated by commas.");
        } else if (option == MAKE_MODEL_AND_INSTRUCTOR) {
            System.out.println("Enter harness make, model number and instructor who checked the harness in that " +
                    "order and separated by commas.");
        }
        Scanner inputScanner = new Scanner(System.in);
        String harnessString = inputScanner.nextLine();
        return harnessString.split(", ");
    }
}