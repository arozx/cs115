public class Test {
    /**
     * Main method to execute the vending machine tests.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        VendingMachine[] machines = new VendingMachine[4];

        // Create 4 VendingMachine objects
        for (int i = 0; i < machines.length; i++) {
            machines[i] = new VendingMachine();
            // Power them on to set attributes
            machines[i].togglePowerState();
        }

        machines[0].setItem("Chips", 5.0);
        machines[0].addItems(9);

        machines[1].setItem("Drinks", 3.0);
        machines[1].addItems(7);

        machines[2].setItem("Cookies", 1.0);
        machines[2].addItems(2);

        machines[3].setItem("Drinks", 2.0);
        machines[3].addItems(5);

        System.out.println("VM-No   Item-Name  Quantity Cost");
        for (VendingMachine vm : machines) {
            System.out.println(vm);
        }

        System.out.println("\n--- Testing Task 2.4 (Limits) ---");
        VendingMachine largeMachine = new VendingMachine(60); // print error & set to 50
        largeMachine.togglePowerState();
        largeMachine.addItems(51); // print error
        largeMachine.addItems(50); // succeed
        System.out.println(largeMachine);

        System.out.println("\n--- Testing Task 2.5 (Low Stock) ---");
        VendingMachine smallMachine = new VendingMachine(20);
        smallMachine.togglePowerState();
        smallMachine.addItems(2); // 10%
        smallMachine.buyItem(); // Becomes 1 < 10% of 20. Should trigger warning.
    }
}
