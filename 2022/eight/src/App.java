import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
        final int ROW = 99;
        int[][] grid = new int[ROW][ROW];
        int counter = 0;
        while (scanner.hasNextLine()) {
            grid[counter] = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            counter++;
        }
        scanner.close();
        int result = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < ROW; j++) {
                boolean visible = false;
                if (i == 0 || j == 0 || i == ROW - 1 || j == ROW - 1) {
                    visible = true;
                } else {
                    // left
                    int leftMax = 0;
                    for (int k = 0; k < j; k++) {
                        leftMax = Math.max(leftMax, grid[i][k]);
                    }
                    if (grid[i][j] == Math.max(grid[i][j], leftMax) && grid[i][j] != leftMax) {
                        visible = true;
                    }
                    // right
                    int rightMax = 0;
                    for (int k = j + 1; k < ROW; k++) {
                        rightMax = Math.max(rightMax, grid[i][k]);
                    }
                    if (grid[i][j] == Math.max(grid[i][j], rightMax) && grid[i][j] != rightMax) {
                        visible = true;
                    }
                    // top
                    int topMax = 0;
                    for (int k = 0; k < i; k++) {
                        topMax = Math.max(topMax, grid[k][j]);
                    }
                    if (grid[i][j] == Math.max(grid[i][j], topMax) && grid[i][j] != topMax) {
                        visible = true;
                    }
                    // bottom
                    int bottomMax = 0;
                    for (int k = i + 1; k < ROW; k++) {
                        bottomMax = Math.max(bottomMax, grid[k][j]);
                    }
                    if (grid[i][j] == Math.max(grid[i][j], bottomMax) && grid[i][j] != bottomMax) {
                        visible = true;
                    }

                }
                if (visible) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int two(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        final int ROW = 99;
        int[][] grid = new int[ROW][ROW];
        int counter = 0;
        while (scanner.hasNextLine()) {
            grid[counter] = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
            counter++;
        }
        scanner.close();
        int result = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < ROW; j++) {
                int tempResult = 1;
                if (i == 0 || j == 0 || i == ROW - 1 || j == ROW - 1) {
                    tempResult = 0;
                } else {
                    // left
                    int[] leftMax = { Integer.MIN_VALUE, 0 };
                    for (int k = 0; k < j; k++) {
                        // find biggest tree closest to point of interest
                        if (grid[i][k] >= grid[i][j] && k > leftMax[0]) {
                            leftMax[0] = k;
                            leftMax[1] = grid[i][k];
                        }
                    }
                    // if closest biggest tree is bigger than the point of interest
                    if (grid[i][j] == Math.max(grid[i][j], leftMax[1]) && grid[i][j] != leftMax[1]) {
                        tempResult = tempResult * j;
                    } else {
                        tempResult = tempResult * (j - leftMax[0]);
                    }
                    // right
                    int[] rightMax = { Integer.MAX_VALUE, 0 };
                    for (int k = j + 1; k < ROW; k++) {
                        if (grid[i][k] >= grid[i][j] && k < rightMax[0]) {
                            rightMax[0] = k;
                            rightMax[1] = grid[i][k];
                        }
                    }
                    if (grid[i][j] == Math.max(grid[i][j], rightMax[1]) && grid[i][j] != rightMax[1]) {
                        tempResult = tempResult * (ROW - 1 - j);
                    } else {
                        tempResult = tempResult * (rightMax[0] - j);
                    }
                    // top
                    int[] topMax = { Integer.MIN_VALUE, 0 };
                    for (int k = 0; k < i; k++) {
                        if (grid[k][j] >= grid[i][j] && k > topMax[0]) {
                            topMax[0] = k;
                            topMax[1] = grid[k][j];
                        }
                    }
                    if (grid[i][j] == Math.max(grid[i][j], topMax[1]) && grid[i][j] != topMax[1]) {
                        tempResult = tempResult * i;
                    } else {
                        tempResult = tempResult * (i - topMax[0]);
                    }
                    // bottom
                    int[] bottomMax = { Integer.MAX_VALUE, 0 };
                    for (int k = i + 1; k < ROW; k++) {
                        if (grid[k][j] >= grid[i][j] && k < bottomMax[0]) {
                            bottomMax[0] = k;
                            bottomMax[1] = grid[k][j];
                        }
                    }
                    if (grid[i][j] == Math.max(grid[i][j], bottomMax[1]) && grid[i][j] != bottomMax[1]) {
                        tempResult = tempResult * (ROW - 1 - i);
                    } else {
                        tempResult = tempResult * (bottomMax[0] - i);
                    }
                    ;
                }
                result = Math.max(result, tempResult);
            }
        }
        return result;
    }
}
