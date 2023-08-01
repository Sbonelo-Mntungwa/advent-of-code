package two.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        File file = new File("/Users/sbonelomntungwa/Documents/adv/2015/two/src/file.txt");
        System.out.println(wrappingPaper(file) + " square feet of wrapping paper");
        System.out.println(ribbon(file) + " feet of ribbon");
    }

    public static int wrappingPaper(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int total = 0;
        while (scanner.hasNextLine()) {
            int[] arr = Arrays.stream(scanner.nextLine().split("x")).mapToInt(Integer::parseInt).toArray();
            total = total + 2 * arr[0] * arr[1]
                    + 2 * arr[1] * arr[2]
                    + 2 * arr[2] * arr[0]
                    + Math.min(Math.min(arr[0] * arr[1], arr[1] * arr[2]), arr[2] * arr[0]);
        }
        scanner.close();
        return total;
    }

    public static int ribbon(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int total = 0;
        while (scanner.hasNextLine()) {
            int[] arr = Arrays.stream(scanner.nextLine().split("x")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(arr);
            total = total + 2 * arr[0] + 2 * arr[1] + arr[0] * arr[1] * arr[2];
        }
        scanner.close();
        return total;
    }
}
