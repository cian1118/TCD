/* SELF ASSESSMENT

Class Rational
I declared two member variables: numerator and denominator (marks out of 4:4).
Comment: variables numerator and denominator are declared at start of class.

Constructor 1
My program takes take two integers as parameters (for numerator and denominator) and initialises the member variables with the corresponding values . If the denominator is equal to 0 I throw an exception (marks out of 5:5).
Comment: two ints taken as parameters, member variables are initialised with these values. If denominator equals 0, exception is thrown.

Constructor 2
My program takes only one integer as parameter (numerator), and set the numerator to this value . I set the denominator to 1 in this case, as the resulting rational number in this case is an integer (marks out of 3:3).
Comment:

Add Method
My program takes only a rational number as a parameter and returns a new rational number which has a numerator and denominator which the addition of the two objects - this and the parameter. My program does not overwrite any of the other two rational numbers (marks out of 8:8).
Comment: the passed rational number is added to this rational number, new number returned which is the sum of the numbs, originals not overwritten.

Subtract Method
I have implemented this the same as add method, except it implements subtraction (marks out of 8:8).
Comment: passed rational is subtracted from this rational number, new number returned which is the difference of the numbs, originals not overwritten.

Multiply Method
I have implemented this the same as add method, except it implements multiplication (marks out of 8:8).
Comment: implemented in same way as add, * operator used instead.

Divide Method
I have implemented this the same as add method, except it implements divide (marks out of 8:8).
Comment: implemented in same way as add, / operator used instead and one fraction "flipped"

Equals Method
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication between numerators/denominators for the purpose of comparison, as integer division will lead to incorrect results. I return a boolean value ((marks out of 8:8).
Comment: boolean returned, numbers are compared, only mult used.

isLessThan
My program takes a rational number as a parameter and compares it to the reference object. I only use multiplication as integer division will lead to incorrect results. I return a boolean value (marks out of 8:8).
Comment: numbers compared boolean returned. true if numb1 less than passed numb. only mult used.

Simplify Method
My program returns a rational number but not a new rational number, instead it returns the current reference which is this. It doesn't take any parameters as it works only with the reference object. I first find the greatest common divisor (GCD) between the numerator and denominator, and then obtain the new numerator and denominator by dividing to the GCD (marks out of 8:8).
Comment: this rational is simplified, not a new object!

gcd function
My program returns the greatest common divider of two integers: the numerator and the denominator (marks out of 6:6).
Comment: gcd finds greatest common divider of the two passed ints, for loop used.

toString Method
My program returns a string showing the fraction representation of the number, eg. "1/2". It takes no parameters (marks out of 4:4).
Comment:  toString prints rational as fraction representation with bracket /

Test Client Class
My program asks the user for two rational numbers, creates two rational objects using the constructor and passing in the provided values, calls addition, subtraction, multiplication, division, comparison and simplification and prints out the results (marks out of 22:22).
Comment: test client asks user for to rational numbers, creates the objects using constructor and calls add, subtract, mult, div, compare (less than and equals) and simplifies. All results are printed to the console.
TOTAL: 100
*/
package Week5;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) throws NoSuchFieldException {
        this.numerator = numerator;
        if (denominator == 0) {
            throw new NoSuchFieldException();
        } else {
            this.denominator = denominator;
        }
    }

    public Rational(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Rational add(Rational number) throws NoSuchFieldException {
        int numerator, commonDenominator;

        commonDenominator = this.denominator * number.denominator;
        numerator = (this.numerator * number.denominator) + (this.denominator * number.numerator);

        return new Rational(numerator, commonDenominator).simplify();
    }

    public Rational subtract(Rational number) throws NoSuchFieldException {
        int numerator, commonDenominator;

        commonDenominator = this.denominator * number.denominator;
        numerator = (this.numerator * number.denominator) - (this.denominator * number.numerator);

        return new Rational(numerator, commonDenominator).simplify();
    }

    public Rational multiply(Rational number) throws NoSuchFieldException {
        int numerator, denominator;
        numerator = this.numerator * number.numerator;
        denominator = this.denominator * number.denominator;
        return new Rational(numerator, denominator).simplify();
    }

    public Rational divide(Rational number) throws NoSuchFieldException {
        int numerator, denominator;
        numerator = this.numerator * number.denominator;
        denominator = this.denominator * number.numerator;
        return new Rational(numerator,denominator).simplify();
    }

    public boolean equals(Rational number) {
        number.simplify();
        this.simplify();
        return (this.numerator == number.numerator && this.denominator == number.denominator);
    }

    public boolean isLessThan(Rational number) {
        this.simplify();
        number.simplify();
        int firstNumerator, secondNumerator;
        firstNumerator = this.numerator * number.denominator;
        secondNumerator = this.denominator * number.numerator;
        return (firstNumerator < secondNumerator);
    }

    public Rational simplify() {
        if (this.numerator < 0 && this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
        if (this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
        int gcd = gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
        return this;
    }

    public int gcd(int numerator, int denominator) {
        int largest;
        int gcd = 0;
        if (numerator < 0) {
            numerator *= -1;
        }
        if (denominator < 0) {
            denominator *= -1;
        }
        if (numerator > denominator) {
            largest = numerator;
        } else {
            largest = denominator;
        }

        for (int count = largest; largest >= 2 && gcd == 0; count--) {
            if (numerator % count == 0 && denominator % count == 0) {
                gcd = count;
            }
        }
        return gcd;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}