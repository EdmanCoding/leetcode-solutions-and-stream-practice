package problems11_20.balanced_Binary_Tree;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

public class balancedBinaryTree {
    // my solution without any help during 23 minutes. O(n^2)
    public boolean isBalanced(TreeNode root) {
        if (root==null || (root.left == null && root.right == null))
            return true;
        boolean boolLeft = isBalanced(root.left);
        boolean boolRight = isBalanced(root.right);
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if(boolLeft == false || boolRight == false)
            return false;
        return Math.abs(left - right)>1?false:true;
    }
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }
    // from LeetCode solutions. O(n)
    public boolean isBalanced2(TreeNode root) {
        return isBalancedAndHeight(root) >= 0;
    }

    public int isBalancedAndHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = isBalancedAndHeight(root.left);
        if (l < 0) {
            return l;
        }
        int r = isBalancedAndHeight(root.right);
        if (r < 0) {
            return r;
        }
        if (Math.abs(l-r) > 1) {
            return -Math.abs(l-r);
        }
        return Math.max(l, r) + 1;
    }
    // second try, solved with hints from chatGPT.
    // initially solved O(n^2) by my own without hints.
    public static boolean isBalanced3(TreeNode root) {
        if(root == null)
            return true;
        return treeDepth(root) != -1;
    }
    public static int treeDepth(TreeNode root){
        if(root == null)
            return 0;
        int left = treeDepth(root.left);
        if(left == -1)
            return -1;
        int right = treeDepth(root.right);
        if(right == -1)
            return -1;
        if(Math.abs(left - right)>1)
            return -1;
        return 1+Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null),
                new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null));
        System.out.println(isBalanced3(root));
    }
}
