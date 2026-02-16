/**
 * Represents a vending machine that holds a single type of item.
 */
public class VendingMachine {
    /** maximum allowable quantity limit for any vending machine. */
    public static final int UPPER_BOUND_LIMIT = 50;
    /** default quantity limit if none is specified. */
    public static final int DEFAULT_LIMIT = 10;
    /** starting serial number for vending machines. */
    public static final int START_SERIAL = 1000;

    private static int nextSerialNumber = START_SERIAL;

    private final int serialNumber;
    private final int maxQuantity;
    private String itemName;
    private double itemCost;
    private int quantity;
    private boolean isOn;

    /**
     * set item quantity limit to 10.
     */
    public VendingMachine() {
        this(DEFAULT_LIMIT);
    }

    /**
     * Constructor setting a custom item quantity limit.
     * If limit exceeds 50, it is set to 50 and an error is printed.
     *
     * @param maxQuantity The desired maximum number of items.
     */
    public VendingMachine(int maxQuantity) {
        if (maxQuantity > UPPER_BOUND_LIMIT) {
            System.out.println("Error: Quantity limit cannot exceed " + UPPER_BOUND_LIMIT + ". Setting to "
                    + UPPER_BOUND_LIMIT + ".");
            this.maxQuantity = UPPER_BOUND_LIMIT;
        } else {
            this.maxQuantity = maxQuantity;
        }
        this.serialNumber = nextSerialNumber++;
        this.quantity = 0;
        this.isOn = false;
        this.itemName = "Empty";
        this.itemCost = 0.0;
    }

    /**
     * Sets the item type and cost for this vending machine.
     *
     * @param name The name of the item.
     * @param cost The cost of a single item.
     */
    public void setItem(String name, double cost) {
        if (isOn) {
            this.itemName = name;
            this.itemCost = cost;
        } else {
            System.out.println("Error: Machine is OFF. Cannot set item.");
        }
    }

    /**
     * Toggles the power state of the vending machine.
     */
    public void togglePowerState() {
        isOn = !isOn;
    }

    /**
     * Gets the name of the items held by the machine.
     *
     * @return The item name or null if OFF.
     */
    public String getItems() {
        if (isOn) {
            return itemName;
        }
        System.out.println("Error: Machine is OFF.");
        return null;
    }

    /**
     * Gets the cost of an item.
     *
     * @return The item cost or 0.0 if OFF.
     */
    public double getPrice() {
        if (isOn) {
            return itemCost;
        }
        System.out.println("Error: Machine is OFF.");
        return 0.0;
    }

    /**
     * Gets the current quantity of items in stock.
     *
     * @return The current quantity or 0 if OFF.
     */
    public int getQuantity() {
        if (isOn) {
            return quantity;
        }
        System.out.println("Error: Machine is OFF.");
        return 0;
    }

    /**
     * Gets the unique serial number of the machine.
     *
     * @return The serial number.
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Adds a specified quantity of items to the machine.
     *
     * @param amount The number of items to add.
     */
    public void addItems(int amount) {
        if (isOn) {
            if (quantity + amount > maxQuantity) {
                System.out.println("Error: Cannot add items. Limit of " + maxQuantity + " would be exceeded.");
            } else {
                quantity += amount;
            }
        } else {
            System.out.println("Error: Machine is OFF.");
        }
    }

    /**
     * Buys one item from the machine, reducing stock by 1.
     */
    public void buyItem() {
        if (isOn) {
            if (quantity > 0) {
                quantity -= 1;
                checkStockLow();
            } else {
                System.out.println("Error: Out of stock.");
            }
        } else {
            System.out.println("Error: Machine is OFF.");
        }
    }

    /**
     * Checks if stock is below 10% and prints a warning if so.
     */
    private void checkStockLow() {
        if (quantity < (0.1 * maxQuantity)) {
            System.out.println("Warning: Low stock on machine " + serialNumber + ". Less than 10% remaining.");
        }
    }

    /**
     * Returns a string representation of the vending machine's status.
     *
     * @return Formatted string with machine details.
     */
    @Override
    public String toString() {
        if (isOn) {
            return String.format("%-7d %-10s %-8d £%.2f", serialNumber, itemName, quantity, itemCost);
        }
        return "Machine " + serialNumber + " is OFF.";
    }
}
