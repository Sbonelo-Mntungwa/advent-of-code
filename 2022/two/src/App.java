import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // File("src/testFile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        HashMap<String, Integer> rules = new HashMap<String, Integer>();
        rules.put("X", 1); // rock
        rules.put("Y", 2); // paper
        rules.put("Z", 3); // scissors

        Scanner scanner = new Scanner(file);
        int totalScore = 0;
        while (scanner.hasNextLine()) {
            String[] game = scanner.nextLine().split(" ");
            totalScore += rules.get(game[1]);
            if (game[0].equals("A") && game[1].equals("X")
                    || game[0].equals("B") && game[1].equals("Y")
                    || game[0].equals("C") && game[1].equals("Z")) {
                totalScore += 3;
            } else if (game[0].equals("A") && game[1].equals("Y")
                    || game[0].equals("B") && game[1].equals("Z")
                    || game[0].equals("C") && game[1].equals("X")) {
                totalScore += 6;
            }
        }
        scanner.close();
        return totalScore;
    }

    public static int two(File file) throws FileNotFoundException {
        HashMap<String, Integer> rules = new HashMap<String, Integer>();
        rules.put("X", 0); // lose
        rules.put("Y", 3); // draw
        rules.put("Z", 6); // win

        int totalScore = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] game = scanner.nextLine().split(" ");
            totalScore += rules.get(game[1]);
            // rock
            if (game[0].equals("B") && game[1].equals("X") // lose to paper
                    || game[0].equals("A") && game[1].equals("Y") // draw to rock
                    || game[0].equals("C") && game[1].equals("Z")) { // win to scissors
                totalScore += 1;
                // paper
            } else if (game[0].equals("C") && game[1].equals("X") // lose to scissors
                    || game[0].equals("B") && game[1].equals("Y") // draw to paper
                    || game[0].equals("A") && game[1].equals("Z")) { // win to rock
                totalScore += 2;
                // scissors
            } else {
                totalScore += 3;
            }
        }
        scanner.close();
        return totalScore;
    }
}