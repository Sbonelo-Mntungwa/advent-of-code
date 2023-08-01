import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        File file = new File("src/file.txt");
        // File testFile = new File("src/testfile.txt");

        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        final int ASCII_UPPER = 65;
        final int ASCII_LOWER = 97;
        int result = 0;
        while (scanner.hasNextLine()) {
            HashMap<String, Integer> firstHash = new HashMap<String, Integer>();
            HashMap<String, Integer> secondHash = new HashMap<String, Integer>();
            String[] arr = scanner.nextLine().split("");
            for (int i = 0; i < arr.length / 2; i++) {
                firstHash.put(arr[i], i);
            }
            for (int i = arr.length / 2; i < arr.length; i++) {
                secondHash.put(arr[i], i);
            }
            HashMap<String, Integer> hash = new HashMap<String, Integer>();

            for (String item : firstHash.keySet()) {
                hash.put(item, 1);
            }
            for (String item : secondHash.keySet()) {
                if (hash.containsKey(item)) {
                    int charAscii = (int) item.charAt(0);
                    if (charAscii < ASCII_LOWER) {
                        result += charAscii - ASCII_UPPER + 1 + 26;
                    } else {
                        result += charAscii - ASCII_LOWER + 1;
                    }
                    break;
                }
            }
        }
        scanner.close();
        return result;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        final int ASCII_UPPER = 65;
        final int ASCII_LOWER = 97;
        int result = 0;
        ArrayList<String[]> group = new ArrayList<String[]>();
        while (scanner.hasNextLine()) {
            group.add(scanner.nextLine().split(""));
            if (group.size() == 3) {
                HashMap<String, Integer> hash = new HashMap<String, Integer>();
                for (String[] arr : group) {
                    HashMap<String, Integer> internalHash = new HashMap<String, Integer>();
                    for (String item : arr) {
                        internalHash.put(item, 1);
                    }
                    for (String item : internalHash.keySet()) {
                        if (hash.containsKey(item)) {
                            if (hash.get(item) == 2) {
                                int charAscii = (int) item.charAt(0);
                                if (charAscii < ASCII_LOWER) {
                                    result += charAscii - ASCII_UPPER + 1 + 26;
                                } else {
                                    result += charAscii - ASCII_LOWER + 1;
                                }
                                break;
                            }
                            hash.put(item, hash.get(item) + 1);
                        } else {
                            hash.put(item, 1);
                        }
                    }
                }
                group = new ArrayList<String[]>();
            }
        }
        scanner.close();
        return result;
    }
}
