import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a graph
        Graph g1 = new Graph();

        try {
            // Open file
            Scanner sc = new Scanner(new File("src/input.txt"));

            // iterates all the lines of the graph
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // Verify if file matches the graph
                if (!line.matches("\\d+(\\s=\\s)(\\d*\\s)*(\\d+)")) {
                    System.out.println("A linha ("+ line + ") do arquivo de entrada não segue o padrão");
                    return;
                }

                // Divide the line
                String[] bruteFormat = line.split("=");

                // Get the first number as a vertex id
                int vertexId = Integer.parseInt(bruteFormat[0].trim());

                // Divide the string in the spaces, convert the number to an Integer and add in the graph
                Arrays.stream(bruteFormat[1].trim().split(" "))
                        .map(value -> Integer.parseInt(value.trim()))
                        .forEach((Integer i) -> g1.add_edge(vertexId, i));
            }
            sc.close();
        }  catch (FileNotFoundException e) {
            System.out.println("Arquivo de entrada não encontrado");
        }

        // Prints the graph
        g1.print();

        // Call the function that verify the graph
        boolean result = g1.quasiThresholdRecognition();

        if (result) {
            System.out.println("Is trivially perfect / Quasi Threshold");
        } else {
            System.out.println("Is not trivially perfect / Quasi Threshold");
        }

    }
}
