import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("src/file.txt");
        File testfile = new File("src/testfile.txt");
        one(file);
        two(file);
    }

    public static void one(File file) throws FileNotFoundException {
        ArrayList<String[]> monkeys = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            scanner.nextLine(); // monkeys
            String[] monkeyInput = new String[6];
            monkeyInput[0] = scanner.nextLine().split(": ")[1]; // starting item
            monkeyInput[1] = scanner.nextLine().split(": ")[1]; // operand
            monkeyInput[2] = scanner.nextLine().split(": ")[1]; // test
            monkeyInput[3] = scanner.nextLine().split(": ")[1]; // true
            monkeyInput[4] = scanner.nextLine().split(": ")[1]; // false
            monkeyInput[5] = "" + 0;
            monkeys.add(monkeyInput);
            scanner.nextLine(); // space
        }
        scanner.close();
        int round = 0;
        while (round < 20) {
            monkeys = getWorryLevel(monkeys, false);
            round++;
        }
        ArrayList<Integer> inspection = new ArrayList<>();
        for (String[] monkey : monkeys) {
            inspection.add(Integer.parseInt(monkey[5]));
        }
        Collections.sort(inspection, Collections.reverseOrder());
        System.out.println(inspection.get(0) * inspection.get(1));

    }

    public static void two(File file) throws FileNotFoundException {
        ArrayList<String[]> monkeys = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            scanner.nextLine(); // monkeys
            String[] monkeyInput = new String[6];
            monkeyInput[0] = scanner.nextLine().split(": ")[1]; // starting item
            monkeyInput[1] = scanner.nextLine().split(": ")[1]; // operand
            monkeyInput[2] = scanner.nextLine().split(": ")[1]; // test
            monkeyInput[3] = scanner.nextLine().split(": ")[1]; // true
            monkeyInput[4] = scanner.nextLine().split(": ")[1]; // false
            monkeyInput[5] = "" + 0;
            monkeys.add(monkeyInput);
            scanner.nextLine(); // space
        }
        scanner.close();

        for (int i = 0; i < 10000; i++) {
            monkeys = getWorryLevel(monkeys, true);
        }

        ArrayList<Integer> inspection = new ArrayList<>();
        for (String[] monkey : monkeys) {
            inspection.add(Integer.parseInt(monkey[5]));
        }

        Collections.sort(inspection, Collections.reverseOrder());
        System.out.println(inspection.get(0) * inspection.get(1));

    }

    public static ArrayList<String[]> getWorryLevel(ArrayList<String[]> monkeys, boolean part2) {
        int modulo = 1;
        for (String[] monkey : monkeys) {
            modulo *= Integer.parseInt(monkey[2].split(" ")[2]);
        }
        for (String[] monkey : monkeys) {
            if (monkey[0].length() > 0) {
                long[] startingItems = Arrays.stream(monkey[0].split(", ")).mapToLong(Long::parseLong).toArray();
                monkey[0] = "";
                String[] operand = monkey[1].split(" ");
                int quotient = Integer.parseInt(monkey[2].split(" ")[2]);
                int trueMonkey = Integer.parseInt(monkey[3].split(" ")[3]);
                int falseMonkey = Integer.parseInt(monkey[4].split(" ")[3]);
                for (long startingItem : startingItems) {
                    monkey[5] = "" + (Integer.parseInt(monkey[5]) + 1);
                    long answer = startingItem;
                    long old = operand[4].equals("old") ? startingItem : Long.parseLong(operand[4]);
                    switch (operand[3]) {
                        case "*":
                            answer *= old;
                            break;
                        case "+":
                            answer += old;
                            break;
                    }

                    answer = part2 ? answer % modulo : answer / 3;

                    if (answer % quotient == 0) {
                        monkeys.get(trueMonkey)[0] = updateMonkeySeries(monkeys.get(trueMonkey)[0], answer);
                    } else {
                        monkeys.get(falseMonkey)[0] = updateMonkeySeries(monkeys.get(falseMonkey)[0], answer);
                    }
                }
            }

        }
        return monkeys;
    }

    public static String updateMonkeySeries(String monkeySeries, long newItem) {
        if (monkeySeries.length() == 0) {
            return "" + newItem;
        }
        return monkeySeries + ", " + newItem;
    }
}
