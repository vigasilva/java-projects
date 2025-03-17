import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in); //opens the scanner for input listening

    public static void main(String[] args) {
        System.out.print("Enter your name:");
        String name = scanner.nextLine(); // asking for user name

        System.out.print("Enter your age:");
        int age = scanner.nextInt(); // asking for user age

        System.out.println("Hello, " + name + "! You are " + age + " years old."); // greets the user based on gathered information
    }
}
