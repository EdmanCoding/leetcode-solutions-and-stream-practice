package problems11_20.InvertBinaryTree;

import org.junit.jupiter.api.Test;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InvertBinaryTreeTest {
    invertBinaryTree solution = new invertBinaryTree();
    @Test
    public void normalTreeTest() {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3))
        , new TreeNode(7, new TreeNode(6), new TreeNode(9)));   // Arrange
        TreeNode result = solution.invertTree(root);            // Act
        assertEquals(4, result.val);                            // Assert
        assertEquals(7, result.left.val);                       
        assertEquals(9, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(1, result.right.right.val);
    }
    @Test
    public void singleNodeTest() {
        TreeNode root = new TreeNode(4);
        TreeNode result = solution.invertTree(root);
        assertEquals(4, result.val);
    }
    @Test
    public void emptyTreeTest() {
        TreeNode root = null;
        TreeNode result = solution.invertTree(root);
        assertNull(result);
    }
}
