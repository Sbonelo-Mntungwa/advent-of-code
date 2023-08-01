import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // File testFile = new
        // File("/Users/sbonelomntungwa/Documents/adv/2022/one/src/testFile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int max = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            if (item.isEmpty()) {
                int testMax = arr.stream().mapToInt(Integer::intValue).sum();
                max = Math.max(max, testMax);
                arr = new ArrayList<>();
            } else {
                arr.add(Integer.parseInt(item));

            }
        }
        int testMax = arr.stream().mapToInt(Integer::intValue).sum();
        max = Math.max(max, testMax);
        scanner.close();
        return max;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Integer[] maxArr = { 0, 0, 0 };
        ArrayList<Integer> arr = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String item = scanner.nextLine();
            if (item.isEmpty()) {
                Arrays.sort(maxArr);
                int testMax = arr.stream().mapToInt(Integer::intValue).sum();
                for (int i = 0; i < maxArr.length; i++) {
                    if (maxArr[i] < testMax) {
                        maxArr[i] = testMax;
                        break;
                    }
                }
                arr = new ArrayList<>();
            } else {
                arr.add(Integer.parseInt(item));
            }
        }
        Arrays.sort(maxArr);
        int testMax = arr.stream().mapToInt(Integer::intValue).sum();
        for (int i = 0; i < maxArr.length; i++) {
            if (maxArr[i] < testMax) {
                maxArr[i] = testMax;
                break;
            }
        }
        scanner.close();
        return Arrays.stream(maxArr).mapToInt(Integer::intValue).sum();
    }
}
