public class Main {
    public static void main(String[] args) {
        int[] array = {10, 20, 30, 40, 50}; // get the starting array
        int[] result = new int[array.length]; // define the result array

        for (int i = 1; i <= array.length; i++) { // invert the arrays

            result[i-1] = array[array.length-i];
        }

        printarray("Original Array: ", array); // prints the arrays in the correct format
        printarray("Reversed Array: ", result);

    }

    public static void printarray(String text, int[] array) { // method for printing th arrays
        System.out.print(text);

        System.out.print("[");

        for (int i = 0; i < array.length; i++) { // loop for printing in the right way
            if (i+1 == array.length) { // if for correct formating
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + ", ");
            }
        }
        System.out.print("]");
        System.out.println(); // garantees that new line is created at the end
    }
}

