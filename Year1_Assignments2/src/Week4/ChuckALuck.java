/* SELF ASSESSMENT
1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7:7].
Comment: resolveBet takes the bet type string and the wallet, return type is void.
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8:8].
Comment: the user's current balance is printed and the they are asked how much they want to bet
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5:5].
Comment: an error message is shown if the bet amount is more than the wallet balance
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15:15]..
Comment: three dice objects are created and rolled, the top faces are added to the total (in the method rollDice)
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20:20].
Comment: winnings are determined by comparing the bet type conditions with the dice total. A method called isTriple is used for the triple, high and low bets.
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10:10].
Comment: bet amount is deducted first. The top faces of the dice are output and the result, if the user won the winnings are added to the wallet.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15:15]
Comment: user is asked how much cash they have and a Wallet object is created, the cash is added to the wallet
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5:5]
Comment: while loop is used and the user is repeatedly asked to place bets until cash is 0 or quit/exit is typed.
I ask the user to enter any of the four bet types or quit [Mark out of 5:5].
Comment: the user is asked to enter one of the four bet types OR they can enter quit/exit
My program calls resolveBet for each bet type entered [Mark out of 5:5].
Comment: after the determineBetType method returns a bet type, the resolveBet method is used.
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5:5]
Comment: a summary is presented at the which shows the starting and ending balances.

 Total Mark out of 100 (Add all the previous marks): 100
*/
package Week4;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChuckALuck {
    private static final int THIRTY_TO_ONE  = 31;
    private static final int ONE_TO_ONE = 2;

    public static void main(String[] args) {
        double userMoney = 0.0;
        System.out.println("How much money do you have?");
        while (userMoney == 0.0) {
            try {
                Scanner inputScanner = new Scanner(System.in);
                userMoney = inputScanner.nextDouble();
                if (userMoney < 0) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input, please enter a positive number.");
                userMoney = 0.0;
            }
        }
        NumberFormat twoDecPlaces = new DecimalFormat("€0.00");
        Wallet wallet = new Wallet();
        wallet.put(userMoney);
        System.out.println("Starting balance: " + twoDecPlaces.format(wallet.check()));
        boolean finished = false;
        while (!finished) {
            String betType = "";
            if (wallet.check() == 0.0) {
                finished = true;
            } else {
                betType = determineBetType();
                if (betType.equals("finished")) {
                    finished = true;
                } else {
                    resolveBet(betType, wallet);
                }
            }
        }
        System.out.println("\nPlay summary: \nStarting balance: " + twoDecPlaces.format(userMoney) +
                "\nFinishing balance: " + twoDecPlaces.format(wallet.check()));
    }

    public static void resolveBet(String betType, Wallet wallet) {
        NumberFormat twoDecPlaces = new DecimalFormat("€0.00");
        System.out.println("Balance: "+ twoDecPlaces.format(wallet.check()) + "\nHow much would you like to bet?");
        double bet = 0.0;
        while (bet == 0.0) {
            Scanner betScan = new Scanner(System.in);
            try {
                bet = betScan.nextDouble();
                if (wallet.get(bet)) {
                    System.out.println("Bet to the value of " + twoDecPlaces.format(bet) + " has been placed!");
                    ArrayList<Dice> diceArray = new ArrayList<>();
                    int diceTotal = rollDice(diceArray);
                    double payout = 0.0;
                    boolean win = false;
                    switch (betType) {
                        case "Triple":
                            if (isTriple(diceArray)) {
                                payout = (bet * THIRTY_TO_ONE);
                                win = true;
                            }
                            break;
                        case "Field":
                            if (diceTotal < 12 && diceTotal > 8) {
                                payout = bet * ONE_TO_ONE;
                                win = true;
                            }
                            break;
                        case "High":
                            if (diceTotal > 10 && !isTriple(diceArray)) {
                                payout = bet * ONE_TO_ONE;
                                win = true;
                            }
                            break;
                        case "Low":
                            if (diceTotal < 11 && !isTriple(diceArray)) {
                                payout = bet * ONE_TO_ONE;
                                win = true;
                            }
                            break;
                        default:
                            break;
                    }

                    if (win) {
                        wallet.put(payout);
                        System.out.println("\nYou won " + twoDecPlaces.format(payout) + "!\nNew wallet balance: "
                                + twoDecPlaces.format(wallet.check()));
                    } else {
                        System.out.println("\nYou lost the bet.\nNew wallet balance: " + twoDecPlaces.format(wallet.check()));
                    }

                } else {
                    System.out.println("You do not have enough money to place that bet!");
                    bet = 0.0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input, please enter a number.");
            }
        }
    }

    public static String determineBetType() {
        String betType = "";
        System.out.println("Enter which type of bet you would like to place (or just the first letter):\nType 'quit' " +
                "or 'exit' now to finish the game.\n\n Bet     Odds\n-Triple  30:1\n-Field   1:1\n-High    1:1\n-Low     1:1");
        while (betType.equals("")) {
            Scanner betScanner = new Scanner(System.in);
            String betInput = betScanner.nextLine();
            switch (betInput.toLowerCase()) {
                case "t":
                case "triple":
                    betType = "Triple";
                    break;
                case "f":
                case "field":
                    betType = "Field";
                    break;
                case "h":
                case "high":
                    betType = "High";
                    break;
                case "l":
                case "low":
                    betType = "Low";
                    break;
                case "quit":
                case "exit":
                    betType = "finished";
                    break;
                default:
                    System.out.println("Error: invalid bet type, enter the bet type or the first letter (t, f, h or l)");
                    break;
            }
        }
        System.out.println(betType.equals("finished") ? "Finishing..." : ("Selected bet type: " + betType));
        return betType;
    }

    public static boolean isTriple(ArrayList<Dice> diceArray) {
        boolean isTriple = true;
        int firstDieTopFace = diceArray.get(0).topFace();
        if (firstDieTopFace == 1 || firstDieTopFace == 6) {
            isTriple = false;
        } else {
            for (int index = 0; index < diceArray.size() - 1 && isTriple; index++) {
                Dice currDie1 = diceArray.get(index);
                Dice currDie2 = diceArray.get(index + 1);
                if (currDie1.topFace() != currDie2.topFace()) {
                    isTriple = false;
                }
            }
        }
        return isTriple;
    }

    public static int rollDice(ArrayList<Dice> diceArray) {
        int diceTotal = 0;
        System.out.print("Result: ");
        for (int count = 0; count < 3; count++) {
            Dice die = new Dice();
            diceTotal += die.roll();
            diceArray.add(die);
            System.out.print(die.topFace() + "  ");
        }
        return diceTotal;
    }
}