package problems11_20.binaryTreePreorderTraversal;

import org.junit.jupiter.api.Test;
import problems11_20.binary_Tree_Preorder_Traversal.PreorderTraversal;
import problems11_20.maximumDepthOfBinaryTree.TreeNode;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PreorderTraversalTest {
    PreorderTraversal solution = new PreorderTraversal();
    @Test
    public void onlySingleBranchedTest(){
        // Arrange
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, null)));
        // Act
        List<Integer> result = solution.preorderTraversal(root);
        // Assert
        assertThat(result).containsExactly(1, 2, 3);
    }
    @Test
    public void complexTreeTest(){
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(6), new TreeNode(7))),
                new TreeNode(3, null, new TreeNode(8, new TreeNode(9), null)));
        List<Integer> result = solution.preorderTraversal(root);
        assertThat(result).containsExactly(1, 2, 4, 5, 6, 7, 3, 8, 9);
    }
    @Test
    public void singeNodeTest(){
        TreeNode root = new TreeNode(1);
        List<Integer> result = solution.preorderTraversal(root);
        assertThat(result).containsExactly(1);
    }
    @Test
    public void nullNodeTest(){
        TreeNode root = null;
        List<Integer> result = solution.preorderTraversal(root);
        assertThat(result).isEmpty();
    }
    @Test
    public void leftAndRightOrderTest(){
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        List<Integer> result = solution.preorderTraversal(root);
        assertThat(result).containsExactly(1, 2, 3);
    }
}
