package problems11_20.balancedBinaryTree;

import org.junit.jupiter.api.Test;
import problems11_20.balanced_Binary_Tree.balancedBinaryTree;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class balancedBinaryTreeTest {
    @Test
    public void baseBalancedTreeTest() {
        // Arrange
        TreeNode tree = new TreeNode(9, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // Act
        boolean result = balancedBinaryTree.isBalanced3(tree);
        // Assert
        assertTrue(result);
    }
    @Test
    public void baseUnbalancedTreeTest() {
        TreeNode tree = new TreeNode(1, new TreeNode(2,
                new TreeNode(3, new TreeNode(4), new TreeNode(4)), new TreeNode(3)), new TreeNode(2));
        boolean result = balancedBinaryTree.isBalanced3(tree);
        assertFalse(result);
    }
    @Test
    public void emptyTreeTest() {
        TreeNode tree = null;
        boolean result = balancedBinaryTree.isBalanced3(tree);
        assertTrue(result);
    }
    @Test
    public void trickyUnbalancedTreeTest() {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null),
                new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null));
        boolean result = balancedBinaryTree.isBalanced3(root);
        assertFalse(result);
    }
    @Test
    public void singleNodeTreeTest() {
        TreeNode tree = new TreeNode(1);
        assertTrue(balancedBinaryTree.isBalanced3(tree));
    }
}
