import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // File testFile = new File("src/testfile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int result = 0;
        while (scanner.hasNextLine()) {
            String[] spaceId = scanner.nextLine().split(",");
            int[] one = Arrays.stream(spaceId[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] two = Arrays.stream(spaceId[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (two[0] >= one[0] && two[1] <= one[1]) {
                result++;
            } else if (two[0] <= one[0] && two[1] >= one[1]) {
                result++;
            }
            ;
        }
        scanner.close();
        return result;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int result = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] spaceId = input.split(",");

            int[] one = Arrays.stream(spaceId[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] two = Arrays.stream(spaceId[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (one[0] == Math.min(one[0], two[0]) && (one[1] >= two[0])) {
                result++;
            } else if (two[0] == Math.min(one[0], two[0]) && (two[1] >= one[0])) {
                result++;
            }
            // System.out.println(input + " " + result);
        }
        scanner.close();
        return result;
    }
}
