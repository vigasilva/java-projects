import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter a number:");
        int result = 0; // Creates the Result variable

        int number = scanner.nextInt(); // gets the number

        for (int repeats = 0; number >= repeats; repeats++) {
            result = result + repeats; // adds to Result every sum of number
        }
        System.out.println("The sum of 1 to " + number + " is " + result); // outputs the result
        scanner.close();
    }
}
