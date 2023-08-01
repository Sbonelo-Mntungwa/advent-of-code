import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // File testfile = new File("src/testfile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String input = scanner.nextLine();
        scanner.close();
        final int PACKET_SIZE = 4;
        int result = PACKET_SIZE;
        for (int i = 0; i < input.length() - PACKET_SIZE; i++) {
            HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
            for (int j = i; j < i + PACKET_SIZE; j++) {
                if (hash.containsKey(input.charAt(j))) {
                    hash.put(input.charAt(j), hash.get(input.charAt(j)) + 1);
                } else {
                    hash.put(input.charAt(j), 1);
                }
            }
            if (hash.size() == PACKET_SIZE) {
                return result;
            }
            result++;
        }
        return result;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String input = scanner.nextLine();
        scanner.close();
        final int PACKET_SIZE = 14;
        int result = PACKET_SIZE;
        for (int i = 0; i < input.length() - PACKET_SIZE; i++) {
            HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
            for (int j = i; j < i + PACKET_SIZE; j++) {
                if (hash.containsKey(input.charAt(j))) {
                    hash.put(input.charAt(j), hash.get(input.charAt(j)) + 1);
                } else {
                    hash.put(input.charAt(j), 1);
                }
            }
            if (hash.size() == PACKET_SIZE) {
                return result;
            }
            result++;
        }
        return result;
    }
}
