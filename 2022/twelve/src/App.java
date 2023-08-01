import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Edge[] edges = {
                new Edge(0, 2, 1),
                new Edge(0, 3, 4),
                new Edge(0, 4, 2),
                new Edge(0, 1, 3),
                new Edge(1, 3, 2),
                new Edge(1, 4, 3),
                new Edge(1, 5, 1),
                new Edge(2, 4, 1),
                new Edge(3, 5, 4),
                new Edge(4, 5, 2),
                new Edge(4, 6, 7),
                new Edge(4, 7, 2),
                new Edge(5, 6, 4),
                new Edge(6, 7, 5)
        };

        Graph graph = new Graph(edges);
        graph.calculateShortestDistance();
        graph.printResult();

    }

    public static int one(File file) throws FileNotFoundException {
        // final int ASCII_LOWER = 97;
        final int ROW = 5;
        final int COL = 8;
        Scanner scanner = new Scanner(file);
        String[][] input = new String[ROW][COL];
        int counter = 0;
        while (scanner.hasNextLine()) {
            input[counter] = scanner.nextLine().split("");
        }
        scanner.close();
        return 1;
    }

    public static int two(File file) throws FileNotFoundException {
        return 2;
    }
}
