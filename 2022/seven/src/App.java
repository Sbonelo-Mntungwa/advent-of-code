import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("src/file.txt");
        // File testfile = new File("src/testfile.txt");
        System.out.println(one(file));
        System.out.println(two(file));
    }

    public static int one(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        TreeNode root = new TreeNode(scanner.nextLine().split(" ")[2]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("$")) {
                if (input[1].equals("cd")) {
                    if (input[2].equals("..")) {
                        stack.pop();
                    }
                    // open directory
                    else {
                        TreeNode node = stack.pop();
                        String data = input[2];
                        for (TreeNode item : node.children) {
                            if (item.val.equals(data)) {
                                stack.push(node);
                                stack.push(item);
                                node = item;
                            }
                        }
                    }
                }
            } else if (input[0].equals("dir")) {
                TreeNode node = stack.pop();
                node.children.add(new TreeNode(input[1]));
                stack.push(node);
            }
            // adding file
            else {
                TreeNode node = stack.pop();
                node.files.add(new HashMap<String, Integer>());
                node.files.get(node.files.size() - 1).put(input[1], Integer.parseInt(input[0]));
                node.fileSize += Integer.parseInt(input[0]);
                stack = updateParentSize(stack, Integer.parseInt(input[0]));
                stack.push(node);
            }
        }
        scanner.close();
        LinkedList<Integer> list = getFileSizeList(root);
        Collections.sort(list);
        int result = 0;
        for (int item : list) {
            if (item <= 100000) {
                result += item;
            }
        }
        return result;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        TreeNode root = new TreeNode(scanner.nextLine().split(" ")[2]);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            if (input[0].equals("$")) {
                if (input[1].equals("cd")) {
                    if (input[2].equals("..")) {
                        stack.pop();
                    }
                    // open directory
                    else {
                        TreeNode node = stack.pop();
                        String data = input[2];
                        for (TreeNode item : node.children) {
                            if (item.val.equals(data)) {
                                stack.push(node);
                                stack.push(item);
                                node = item;
                            }
                        }
                    }
                }
            } else if (input[0].equals("dir")) {
                TreeNode node = stack.pop();
                node.children.add(new TreeNode(input[1]));
                stack.push(node);
            }
            // adding file
            else {
                TreeNode node = stack.pop();
                node.files.add(new HashMap<String, Integer>());
                node.files.get(node.files.size() - 1).put(input[1], Integer.parseInt(input[0]));
                node.fileSize += Integer.parseInt(input[0]);
                stack = updateParentSize(stack, Integer.parseInt(input[0]));
                stack.push(node);
            }
        }
        scanner.close();
        LinkedList<Integer> list = getFileSizeList(root);
        Collections.sort(list, Collections.reverseOrder());
        final int AVAILABLE_SPACE = 70000000;
        final int LIMIT = 30000000;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (AVAILABLE_SPACE - list.get(0) + list.get(i) < LIMIT) {
                break;
            }
            index++;
        }
        return list.get(index - 1);
    }

    public static Stack<TreeNode> updateParentSize(Stack<TreeNode> stack, int size) {
        Stack<TreeNode> tempStack = new Stack<TreeNode>();
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            node.fileSize += size;
            tempStack.push(node);
        }
        while (!tempStack.empty()) {
            TreeNode tempNode = tempStack.pop();
            stack.push(tempNode);
        }
        return stack;
    }

    public static LinkedList<Integer> getFileSizeList(TreeNode root) {
        if (root == null)
            return new LinkedList<Integer>();
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> result = new LinkedList<Integer>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                result.add(node.fileSize);
                for (TreeNode item : node.children) {
                    queue.offer(item);
                }
            }
        }
        return result;

    }
}
