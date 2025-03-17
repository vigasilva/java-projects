import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter your age:");
        int age = scanner.nextInt(); // Gets user age

        if (age <18) { // verifies if user is over 18 for voting
            System.out.print("You are not eligible to vote");
        } else { // if user isn't over 18 he cant vote
            System.out.print("You are eligible to vote");
            scanner.close();
        }
    }
}
