import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        // File testfile = new File("src/testfile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Set<int[]> set = new HashSet<>();
        int[] tail = { 0, 0 };
        int[] head = { 0, 0 };
        set.add(new int[] { 0, 0 });
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            int move = Integer.parseInt(input[1]);
            while (move > 0) {
                move--;
                head = getHead(head, input[0]);
                tail = getTail(head, tail);
                final int[] result = tail;
                if (!set.stream().anyMatch(c -> Arrays.equals(c, result))) {
                    set.add(tail);
                }
            }
        }
        scanner.close();
        return set.size();
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        final int TAILS = 9;
        ArrayList<int[]> rope = new ArrayList<>();
        for (int i = 0; i < TAILS + 1; i++) {
            rope.add(i, new int[] { 0, 0 });
        }
        Set<int[]> set = new HashSet<>();
        set.add(new int[] { 0, 0 });

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            int move = Integer.parseInt(input[1]);
            while (move > 0) {
                move--;
                rope.set(0, getHead(rope.get(0), input[0]));
                for (int i = 0; i < TAILS; i++) {
                    rope.set(i + 1, getTail(rope.get(i), rope.get(i + 1)));
                }
                if (!set.stream().anyMatch(c -> Arrays.equals(c, rope.get(TAILS)))) {
                    set.add(rope.get(TAILS));
                }
            }
        }
        scanner.close();
        return set.size();
    }

    public static int[] getHead(int[] head, String direction) {
        int[] result = { head[0], head[1] };

        if (direction.equals("L")) {
            result[0] -= 1;
            return result;
        }
        if (direction.equals("R")) {
            result[0] += 1;
            return result;
        }
        if (direction.equals("D")) {
            result[1] -= 1;
            return result;
        }
        if (direction.equals("U")) {
            result[1] += 1;
            return result;
        }
        return result;
    }

    public static int[] getTail(int[] head, int[] tail) {
        int[] result = { tail[0], tail[1] };

        boolean left = tail[0] - head[0] == 2;
        boolean right = tail[0] - head[0] == -2;
        boolean up = tail[1] - head[1] == -2;
        boolean down = tail[1] - head[1] == 2;

        if (left && down) {
            result[0] -= 1;
            result[1] -= 1;
            return result;
        }
        if (left && up) {
            result[0] -= 1;
            result[1] += 1;
            return result;
        }
        if (right && down) {
            result[0] += 1;
            result[1] -= 1;
            return result;
        }
        if (right && up) {
            result[0] += 1;
            result[1] += 1;
            return result;
        }
        if (left) {
            result[0] -= 1;
            result[1] = head[1];
            return result;
        }
        if (right) {
            result[0] += 1;
            result[1] = head[1];
            return result;
        }
        if (down) {
            result[0] = head[0];
            result[1] -= 1;
            return result;
        }
        if (up) {
            result[0] = head[0];
            result[1] += 1;
            return result;
        }
        return result;
    }
}
