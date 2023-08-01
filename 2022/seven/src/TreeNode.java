import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    String val;
    List<TreeNode> children = new LinkedList<>();
    List<HashMap<String, Integer>> files = new ArrayList<>();
    int fileSize;

    TreeNode(String data) {
        val = data;
    }

    TreeNode(String data, List<TreeNode> child, List<HashMap<String, Integer>> files, int size) {
        val = data;
        children = child;
        this.files = files;
        fileSize = size;
    }

}
