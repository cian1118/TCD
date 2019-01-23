package Week6;

import java.util.Random;

public class HiLoGameTest {
    public static void main(String[] args) {

        for (int count = 0; count < 500; count++) {
            Random generator = new Random();
            int randomNumb = generator.nextInt(13) + 2;
            System.out.println(randomNumb);
        }
    }
}