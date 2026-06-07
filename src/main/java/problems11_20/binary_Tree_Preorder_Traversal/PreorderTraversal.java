package problems11_20.binary_Tree_Preorder_Traversal;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

import java.util.*;

public class PreorderTraversal {
    // idiomatic solution
    public List<Integer> preorderTraversal5(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }
    //PREORDER  → root → left → right       PRE   → root is FIRST
    //INORDER   → left → root → right       IN    → root is in the MIDDLE
    //POSTORDER → left → right → root       POST  → root is LAST

    //One more useful insight (worth remembering)
    //INORDER traversal of BST → gives sorted order
    //That fact appears in multiple problems.


    // my first solution without any hints
    // O(n) time, O(h) space (h = tree height)
    List<Integer> result = new LinkedList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root==null) return result;
        else result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }
    // second iterative solution with 2 hints by GPT
    // O(n) time
    // O(h) space (h = tree height)
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root==null)
            return null;
        List<Integer> result = new LinkedList<>();

        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right!=null)
                stack.push(node.right);
            if(node.left!=null)
                stack.push(node.left);
        }
        return result;
    }













    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return list;
        list.add(root.val);
        do {
            if (root.left != null){
                stack.push(root);
                root = root.left;
                list.add(root.val);
            }
            else if (root.right != null){
                stack.push(root);
                root = root.right;
                list.add(root.val);
            }
            else {
                root = null;
                root = stack.pop();
            }
        } while (!stack.isEmpty());
        return list;
    }
    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return list;
        list.add(root.val);
        if(root.left == null && root.right == null)
            return list;
        do {
            if (root.left != null){
                stack.push(root);
                root = root.left;
                list.add(root.val);
            }
            else if (root.right != null){
                stack.push(root);
                root = root.right;
                list.add(root.val);
            }
            else {
                TreeNode temp = root;
                root = stack.pop();
                if(temp == root.left)
                    root.left = null;
                else
                    root.right = null;
            }
        } while (!stack.isEmpty() || (root.left != null || root.right != null));
        return list;
    }

    public static void main(String[] args) {
        PreorderTraversal pt = new PreorderTraversal();
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))),
                new TreeNode(3, null, new TreeNode(8, new TreeNode(9), null)));
        System.out.println(pt.preorderTraversal4(root));
    }
}
