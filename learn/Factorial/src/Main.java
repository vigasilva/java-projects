import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.print("Enter a number: ");
        int number = scanner.nextInt(); // gets the number to factor

        System.out.println("The factorial of " + number + " is: " + factorial(number)); // calls the factorial method and outputs the code

        scanner.close(); // closes scanner
    }

    public static int factorial(int number) {

        int result = 1;

        for (int i = 1; number >= i; i++) {
            result = i * result; // calculates the factorial
        }
        return result; // returns the factorial
    }

}
