import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import javax.swing.JFileChooser;

public class PersonReader {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        System.out.println("Please select the Person file.");
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            readAndPrintPersonDetails(selectedFile);
        } else {
            System.out.println("File selection was canceled.");
        }
    }

    private static void readAndPrintPersonDetails(File file) {
        String header = String.format("%-15s%-15s%-15s%-10s%-10s", "ID#", "Firstname", "Lastname", "Title", "YOB");
        System.out.println(header);
        System.out.println("===============================================================");
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            reader.lines().forEach(line -> {
                String[] parts = line.split(",\\s*");
                System.out.printf("%-15s%-15s%-15s%-10s%-10s%n", parts[0], parts[1], parts[2], parts[3], parts[4]);
            });
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
