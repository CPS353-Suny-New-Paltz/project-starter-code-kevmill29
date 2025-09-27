package processAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;





public class convertData {
    public static HashMap<Integer, String> convertData() {
        File file = new File("src/Output/output.txt");
        Scanner scanner = null;
        String delimiter = ","; // You can change this if needed

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>(); // Return empty map if file not found
        }

        HashMap<Integer, String> map = new HashMap<>();

        while (scanner.hasNext()) {
            int jobID = (int) (Math.random() * 2000) + 1; // Generate unique job ID
            String line = scanner.nextLine(); // Read the line
            String[] values = line.split(delimiter); // Split by delimiter
            // Optionally format the values into a single string
            String formattedValue = String.join(", ", values);

            map.put(jobID, formattedValue);
        }

        scanner.close();
        return map;
    }

}
