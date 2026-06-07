package problems11_20.InvertBinaryTree;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


public class invertBinaryTree {
    // my initial solution first without any hint during 22 minutes
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return root;
        if(root.left!=null && root.right!=null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        else if (root.left!=null){
            root.right = root.left;
            root.left = null;
            invertTree(root.right);
        }
        else if (root.right!=null){
            root.left = root.right;
            root.right = null;
            invertTree(root.left);
        }
        return root;
    }
    // ChatGPT optimized solution - DFS recursive
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    // BFS non-recursive solution from LeetCode
    public TreeNode invertTree3(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            final TreeNode node = queue.poll();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
    // DFS non-recursive solution from LeetCode
    public TreeNode invertTree4(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            final TreeNode node = stack.pop();
            final TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if(node.left != null) {
                stack.push(node.left);
            }
            if(node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }
}
