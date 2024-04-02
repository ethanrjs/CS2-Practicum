import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class PersonGenerator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> persons = new ArrayList<>();
            boolean done = false;

            while (!done) {
                System.out.println("Enter the person's details:");
                String id = SafeInput.getRegExString(scanner, "Enter ID (a String)", "\\d+");
                String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
                String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
                String title = SafeInput.getNonZeroLenString(scanner, "Enter Title (Mr., Mrs., Ms., Dr., etc.)");
                int yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year Of Birth", 0, 2024);

                persons.add(String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth));
                done = !SafeInput.getYNConfirm(scanner, "Do you want to add another person?");
            }

            String filename = SafeInput.getNonZeroLenString(scanner, "Enter the name of the file to save (e.g., Persons.txt)");
            savePersonsToFile(filename, persons);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void savePersonsToFile(String filename, ArrayList<String> persons) {
        try {
            Files.write(Paths.get(filename), persons, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }
}
