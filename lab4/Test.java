import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class to test participant data processing.
 * Reads data from files, computes heart rates, and outputs the results.
 */
public class Test {
    private static final String DATA_DIR = "participant_data";
    private static final String OUTPUT_FILE = "OUTPUT.txt";
    private static final int NUM_PARTICIPANTS = 5;

    /**
     * The main entry point of the program.
     * Displays a menu and handles user interaction.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Participant> participants = null;

        while (true) {
            System.out.println("*** Welcome to my Program to Compute Heart Rate ***");
            System.out.println("Enter '1' to compute Heart Rate for all the Participants.");
            System.out.println("Enter '2' to write the data of all Participants to a file.");
            System.out.println("Enter '3' to exit the program.");

            try {
                String inputStr = scanner.nextLine();
                int choice = Integer.parseInt(inputStr);

                if (choice == 1) {
                    participants = computeHeartRates();
                    System.out.println("Successfully computed heart rates.");
                } else if (choice == 2) {
                    if (participants == null) {
                        System.out.println("Error: You must compute heart rates (Option 1) before writing to file.");
                    } else {
                        writeParticipantsToFile(participants);
                        System.out.println("Successfully wrote data to " + OUTPUT_FILE);
                    }
                } else if (choice == 3) {
                    System.out.println("Exiting program.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number (1, 2, or 3).");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Reads files from the data directory and computes the average heart rate.
     * Randomly generates dates and times for each participant.
     *
     * @return An ArrayList containing Participant objects.
     */
    private static ArrayList<Participant> computeHeartRates() {
        ArrayList<Participant> participants = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= NUM_PARTICIPANTS; i++) {
            String participantId = "p" + i;
            String filename = DATA_DIR + File.separator + participantId + ".txt";

            try {
                File file = new File(filename);
                Scanner fileScanner = new Scanner(file);

                double sum = 0;
                int count = 0;

                while (fileScanner.hasNextDouble()) {
                    sum = sum + fileScanner.nextDouble();
                    count = count + 1;
                }
                fileScanner.close();

                if (count > 0) {
                    double rrMean = sum / count;
                    int hr = (int) Math.round((60.0 * 1000.0) / rrMean);

                    // Generate random date between 2019 and 2023
                    int year = 2019 + random.nextInt(5);
                    int month = 1 + random.nextInt(12);
                    int day = 1 + random.nextInt(28); // simplified to avoid invalid dates
                    String date = String.format("%04d-%02d-%02d", year, month, day);

                    // Generate random time
                    int hour = random.nextInt(24);
                    int minute = random.nextInt(60);
                    int second = random.nextInt(60);
                    String time = String.format("%02d-%02d-%02d", hour, minute, second);

                    participants.add(new Participant("P" + i, date, time, hr));
                } else {
                    System.out.println("Warning: File " + filename + " is empty or contains no valid data.");
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found - " + filename);
            } catch (Exception e) {
                System.out.println("Error processing file " + filename + ": " + e.getMessage());
            }
        }

        // 4.5: Sort the list in order of increasing HR (asc)
        Collections.sort(participants);

        return participants;
    }

    /**
     * Writes the participant details to an output file.
     *
     * @param participants The list of participants to write.
     */
    private static void writeParticipantsToFile(ArrayList<Participant> participants) {
        try {
            PrintWriter writer = new PrintWriter(OUTPUT_FILE);
            writer.printf("%-15s%-15s%-15s%s%n", "ParticipantID", "Date", "Time", "HR");

            for (Participant p : participants) {
                writer.println(p.toString());
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not create output file " + OUTPUT_FILE);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
