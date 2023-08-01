import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        File testfile = new File("src/testFile.txt");
        File file = new File("src/file.txt");
        System.out.println(one(file));
        System.out.println(two(testfile));
    }

    public static String one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Stack<String[]> stack = new Stack<String[]>();
        ArrayList<Stack<String>> list = new ArrayList<Stack<String>>();
        for (int i = 0; i < 9; i++) {
            list.add(new Stack<String>());
        }
        Boolean trigger = false;
        while (scanner.hasNextLine()) {
            if (!trigger) {
                String input = scanner.nextLine();
                String[] inputArr = input.split("(?<=\\G.{" + 4 + "})");
                if (inputArr[0].equals(" 1  ")) {
                    scanner.nextLine();
                    trigger = true;
                    while (!stack.empty()) {
                        String[] stackItem = stack.pop();
                        int index = 0;
                        for (String item : stackItem) {
                            String sliced = Character.toString(item.charAt(1));
                            if (!sliced.equals(" ")) {
                                list.get(index).push(sliced);
                            }
                            index++;
                        }
                    }
                } else {
                    stack.push(inputArr);
                }
            } else {
                String[] input = scanner.nextLine().split(" ");
                int howMany = Integer.parseInt(input[1]);
                int startPos = Integer.parseInt(input[3]) - 1;
                int endPos = Integer.parseInt(input[5]) - 1;
                int counter = 0;
                while (counter < howMany) {
                    list.get(endPos).push(list.get(startPos).pop());
                    counter++;
                }
            }
        }
        String result = "";
        for (Stack<String> item : list) {
            result = result + item.pop();
        }
        scanner.close();
        return result;
    }

    public static String two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        final int CRATES = 3;
        Stack<String[]> stack = new Stack<String[]>();
        ArrayList<Stack<String>> list = new ArrayList<Stack<String>>();
        for (int i = 0; i < CRATES; i++) {
            list.add(new Stack<String>());
        }
        Boolean trigger = false;
        while (scanner.hasNextLine()) {
            if (!trigger) {
                String input = scanner.nextLine();
                String[] inputArr = input.split("(?<=\\G.{" + 4 + "})");
                if (inputArr[0].equals(" 1  ")) {
                    scanner.nextLine();
                    trigger = true;
                    while (!stack.empty()) {
                        String[] stackItem = stack.pop();
                        int index = 0;
                        for (String item : stackItem) {
                            String sliced = Character.toString(item.charAt(1));
                            if (!sliced.equals(" ")) {
                                list.get(index).push(sliced);
                            }
                            index++;
                        }
                    }
                    System.out.println(list);
                } else {
                    stack.push(inputArr);
                }
            } else {
                String inputStr = scanner.nextLine();
                String[] input = inputStr.split(" ");
                System.out.println(inputStr);
                int howMany = Integer.parseInt(input[1]);
                int startPos = Integer.parseInt(input[3]) - 1;
                int endPos = Integer.parseInt(input[5]) - 1;
                int counter = 0;
                Stack<String> tempStack = new Stack<String>();
                while (counter < howMany) {
                    tempStack.push(list.get(startPos).pop());
                    counter++;
                }
                while (!tempStack.isEmpty()) {
                    list.get(endPos).push(tempStack.pop());
                }
                System.out.println(list);
            }
        }
        String result = "";
        for (Stack<String> item : list) {
            result = result + item.pop();
        }
        scanner.close();
        return result;
    }
}
