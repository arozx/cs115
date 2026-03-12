public class Main {
    public static void main(String[] args) {
        System.out.println("5^5 = " + power(5, 5));
        System.out.println("2^10 = " + power(2, 10));
        System.out.println("3^0 = " + power(3, 0));
        System.out.println("7^1 = " + power(7, 1));
    }

    /**
     * Recursively calculates x to the power of y.
     * Uses the rule: x^y = x * x^(y-1)
     * Base case: x^0 = 1
     * 
     * @param x the base
     * @param y the exponent (must be non-negative)
     * @return x raised to the power of y
     */
    public static int power(int x, int y) {
        // Base case: any number to the power of 0 is 1
        if (y == 0) {
            return 1;
        }
        // Recursive case: x^y = x * x^(y-1)
        return x * power(x, y - 1);
    }
}
