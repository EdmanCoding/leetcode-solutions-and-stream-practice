package problems11_20.maximum_Depth_Of_Binary_Tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problems11_20.maximumDepthOfBinaryTree.Solution;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void testBaseTrueCase(){
        TreeNode root = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7))); // Arrange
        int result = solution.maxDepth(root); // Act
        Assertions.assertEquals(3,result); // Assert
    }
    @Test
    public void testTreeWithAllLeftAndAllRightBranches(){
        TreeNode root = new TreeNode(3,new TreeNode(9, new TreeNode(22, new TreeNode(54),null), null),
                new TreeNode(20,null,new TreeNode(15,null, new TreeNode (52)))); // Arrange
        int result = solution.maxDepth(root); // Act
        Assertions.assertEquals(4,result); // Assert
    }
    @Test
    public void testRootWithoutLeaves(){
        TreeNode root = new TreeNode(3); // Arrange
        int result = solution.maxDepth(root); // Act
        Assertions.assertEquals(1,result); // Assert
    }
    @Test
    public void testNullRoot(){
        TreeNode root = null; // Arrange
        int result = solution.maxDepth(root); // Act
        Assertions.assertEquals(0,result); // Assert
    }
    @Test
    public void testOnlyRightBranches(){
        TreeNode root = new TreeNode(20,null,new TreeNode(15,null, new TreeNode (52))); // Arrange
        int result = solution.maxDepth(root); // Act
        Assertions.assertEquals(3,result); // Assert
    }
    @Test
    public void testDeepLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 0; i < 10; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        int result = solution.maxDepth(root);
        Assertions.assertEquals(11, result);
    }
}
