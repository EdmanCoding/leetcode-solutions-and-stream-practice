package problems11_20.maximumDepthOfBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // DFS solution by qwen
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }

    // BFS solution from LC solutions
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes in this level
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            depth++; // finished one level
        }
        return depth;
    }

    // the first resolving, by myself. O(n) time, O(h) space
    public int maxDepth3(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth3(root.left), maxDepth3(root.right));
    }

}
