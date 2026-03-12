
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class which shows recursive and iterative searching over an ArrayList.
 */
public class PhoneMain {
    public static void main(String[] args) {
        ArrayList<PhoneEntry> phoneBook = new ArrayList<PhoneEntry>();
        PhoneEntry casey = new PhoneEntry("Casey", "07123123121");
        PhoneEntry faron = new PhoneEntry("Faron", "07123123125");
        PhoneEntry liam = new PhoneEntry("Liam", "07123123123");
        PhoneEntry neal = new PhoneEntry("Neal", "07123123124");
        PhoneEntry troy = new PhoneEntry("Troy", "07123123122");
        phoneBook.add(casey);
        phoneBook.add(faron);
        phoneBook.add(liam);
        phoneBook.add(neal);
        phoneBook.add(troy);

        // Sort the phone book alphabetically by name (required for binary search)
        Collections.sort(phoneBook, (a, b) -> a.getName().compareTo(b.getName()));

        Scanner in = new Scanner(System.in);
        System.out.println("Phone book size: " + phoneBook.size() + " entries");
        System.out.println("Who are you looking for?");
        String search = in.nextLine();

        // Run iterative search with timing
        long startTimeIterative = System.nanoTime();
        String resultIterative = iterativeSearch(phoneBook, search);
        long endTimeIterative = System.nanoTime();
        long elapsedTimeIterative = endTimeIterative - startTimeIterative;

        // Run recursive (binary) search with timing
        long startTimeRecursive = System.nanoTime();
        String resultRecursive = recursiveSearch(phoneBook, search, 0, phoneBook.size() - 1);
        long endTimeRecursive = System.nanoTime();
        long elapsedTimeRecursive = endTimeRecursive - startTimeRecursive;

        // Display results
        System.out.println("Iterative search result: " + resultIterative);
        System.out.println("Iterative search took: " + elapsedTimeIterative + " nanoseconds");
        System.out.println();
        System.out.println("Recursive (binary) search result: " + resultRecursive);
        System.out.println("Recursive search took: " + elapsedTimeRecursive + " nanoseconds");
        if (elapsedTimeIterative < elapsedTimeRecursive) {
            System.out.println(
                    "Iterative search was faster by " + (elapsedTimeRecursive - elapsedTimeIterative) + " nanoseconds");
        } else if (elapsedTimeRecursive < elapsedTimeIterative) {
            System.out.println("Recursive (binary) search was faster by "
                    + (elapsedTimeIterative - elapsedTimeRecursive) + " nanoseconds");
        } else {
            System.out.println("Both searches took the same time");
        }
    }

    public static String iterativeSearch(ArrayList<PhoneEntry> phoneBook, String name) {
        for (int i = 0; i < phoneBook.size(); i++) {
            if (phoneBook.get(i).getName().equals(name)) {
                return phoneBook.get(i).getPhoneNumber();
            }
        }
        return "Not found";
    }

    // The phone book must be in alphabetical order
    public static String recursiveSearch(ArrayList<PhoneEntry> phoneBook, String name, int start, int end) {
        int middle = (start + end) / 2;
        // base case: check middle element
        if (name.equals(phoneBook.get(middle).getName())) {
            return phoneBook.get(middle).getPhoneNumber();
        }
        // base case: check if we've run out of elements
        if (end < start) {
            return "Not Found";
        }
        // recursive call: search start to middle
        if (name.compareTo(phoneBook.get(middle).getName()) < 0) {
            return recursiveSearch(phoneBook, name, start, middle - 1);
        }
        // recursive call: search middle to end
        if (name.compareTo(phoneBook.get(middle).getName()) > 0) {
            return recursiveSearch(phoneBook, name, middle + 1, end);
        }
        return "Not found";
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

}
