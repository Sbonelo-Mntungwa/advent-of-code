import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("src/file.txt");
        // File testfile = new File("src/testfile.txt");
        System.out.println(one(file));
        two(file);
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Queue<Integer> queue = new LinkedList<>();
        int register = 1;
        int cycle = 0;
        int sum = 0;

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            queue.offer(0);
            if (input[0].equals("addx")) {
                queue.offer(Integer.parseInt(input[1]));
            }
            cycle++;
            sum += updateSum(register, cycle);
            register += queue.poll();
        }
        scanner.close();
        while (!queue.isEmpty()) {
            cycle++;
            sum += updateSum(register, cycle);
            register += queue.poll();
        }
        return sum;
    }

    public static void two(File file) throws FileNotFoundException {

        // constants
        final String LIT_PIXEL = "@";
        final String DIM_PIXEL = " ";
        final int CRT_HEIGHT = 6;
        final int CRT_WIDTH = 40;

        String[] register = new String[CRT_WIDTH];
        for (int i = 0; i < 3; i++) {
            register[i] = LIT_PIXEL;
        }
        for (int i = 3; i < CRT_WIDTH; i++) {
            register[i] = DIM_PIXEL;
        }

        ArrayList<String> crt = new ArrayList<String>();
        for (int i = 0; i < CRT_HEIGHT; i++) {
            crt.add("");
        }

        Queue<Integer> queue = new LinkedList<>();
        int cycle = 0;
        int index = 1;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            queue.offer(0);
            if (input[0].equals("addx")) {
                queue.offer(Integer.parseInt(input[1]));
            }

            int reg_index = cycle / CRT_WIDTH;

            boolean isDrawn = register[cycle % CRT_WIDTH].equals(LIT_PIXEL);
            if (isDrawn) {
                crt.set(reg_index, crt.get(reg_index) + LIT_PIXEL);
            } else {
                crt.set(reg_index, crt.get(reg_index) + DIM_PIXEL);
            }

            boolean registerStart = index >= 0 && index < CRT_WIDTH - 1;
            boolean registerMiddle = index >= 0 && index <= CRT_WIDTH - 1;
            boolean registerEnd = index >= 0 && index < CRT_WIDTH - 1;

            if (registerMiddle) {
                register[index] = DIM_PIXEL;
            }
            if (registerStart) {
                register[index + 1] = DIM_PIXEL;
            }
            if (registerEnd) {
                register[index - 1] = DIM_PIXEL;
            }
            index += queue.poll();
            if (registerMiddle) {
                register[index] = LIT_PIXEL;
            }
            if (registerStart) {
                register[index + 1] = LIT_PIXEL;
            }
            if (registerEnd) {
                register[index - 1] = LIT_PIXEL;
            }
            cycle++;
        }

        scanner.close();
        while (!queue.isEmpty()) {
            int reg_index = cycle / CRT_WIDTH;

            boolean isDrawn = register[cycle % CRT_WIDTH].equals(LIT_PIXEL);
            if (isDrawn) {
                crt.set(reg_index, crt.get(reg_index) + LIT_PIXEL);
            } else {
                crt.set(reg_index, crt.get(reg_index) + DIM_PIXEL);
            }

            boolean registerStart = index >= 0 && index < CRT_WIDTH - 1;
            boolean registerMiddle = index >= 0 && index <= CRT_WIDTH - 1;
            boolean registerEnd = index >= 0 && index < CRT_WIDTH - 1;

            if (registerMiddle) {
                register[index] = DIM_PIXEL;
            }
            if (registerStart) {
                register[index + 1] = DIM_PIXEL;
            }
            if (registerEnd) {
                register[index - 1] = DIM_PIXEL;
            }
            index += queue.poll();
            if (registerMiddle) {
                register[index] = LIT_PIXEL;
            }
            if (registerStart) {
                register[index + 1] = LIT_PIXEL;
            }
            if (registerEnd) {
                register[index - 1] = LIT_PIXEL;
            }
            cycle++;
        }
        for (int i = 0; i < CRT_HEIGHT; i++) {
            System.out.println(crt.get(i));

        }
        // ZFBFHGUP
    }

    public static int updateSum(int register, int cycle) {
        final int[] CYCLE_INDEX = new int[] { 20, 60, 100, 140, 180, 220 };
        if (Arrays.stream(CYCLE_INDEX).anyMatch(xh -> xh == cycle)) {
            return cycle * register;
        }
        return 0;
    }

}
