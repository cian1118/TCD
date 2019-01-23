package Week3;
/* SELF ASSESSMENT
1. Did I use easy-to-understand meaningful variable names?
    Mark out of 10: 10
2. Did I format the variable names properly (in lowerCamelCase)?
    Mark out of 10: 10
3. Did I indent the code appropriately?
    Mark out of 10: 10
4. Did I read the input correctly from the user using appropriate questions?
    Mark out of 20: 20
5. Did I use an appropriate (i.e. not more than necessary) series of if statements?
    Mark out of 30: 30
6. Did I output the correct answer for each possibility in an easy to read format?
    Mark out of 15: 15
7. How well did I complete this self-assessment?
    Mark out of 5: 5
Total Mark out of 100 (Add all the previous marks): 100
*/

import javax.swing.JOptionPane;

public class umbrella {
    public static void main(String[] args) {

        int userInput = JOptionPane.showConfirmDialog(null, "Is it raining?");
        boolean rain = (userInput == JOptionPane.YES_OPTION);

        if (rain) {
            JOptionPane.showMessageDialog(null, "Bring your umbrella and put it up!");
        }
        else {
            userInput = JOptionPane.showConfirmDialog(null, "Does it look like it might rain?");
            boolean mightRain = (userInput == JOptionPane.YES_OPTION);

            if (mightRain) {
                JOptionPane.showMessageDialog(null, "Bring your umbrella but you don't need to put it up.");
            }
            else {
                JOptionPane.showMessageDialog(null, "You don't need to bring your umbrella.");
            }
        }
    }
}