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
                    System.out.println("O arquivo de entrada não segue o padrão");
                    return;
                }

                String[] text1 = line.split("=");

                int vertexId = Integer.parseInt(text1[0].trim());

                Arrays.stream(text1[1].trim().split(" "))
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
            System.out.println("É trivialmente perfeito");
        } else {
            System.out.println("Não é trivialmente perfeito");
        }

    }
}
