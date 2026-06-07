package problems1_10.Binary_Search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problems11_20.binarySearch.BinarySearch;

public class BinarySearchTest {
    BinarySearch solution = new BinarySearch();

    @Test
    public void testBasicTrueCase() {
        int[] nums = {-1,0,3,5,9,12};   // Arrange
        int target = 9;                 // Arrange

        int result = solution.search3(nums, target); // Act

        Assertions.assertEquals(4, result); // Assert
    }
    @Test
    public void testBasicFalseCase() {
        int[] nums = {-1,0,3,5,9,12};   // Arrange
        int target = 2;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(-1, result); // Assert
    }
    @Test
    public void testTargetHigherThanUpperBound() {
        int[] nums = {-1,0,3,5,9,12};   // Arrange
        int target = 22;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(-1, result); // Assert
    }
    @Test
    public void testTargetLowerThanUpperBound() {
        int[] nums = {-1,0,3,5,9,12};   // Arrange
        int target = -5;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(-1, result); // Assert
    }
    @Test
    public void testOneElementEqualTarget() {
        int[] nums = {5};   // Arrange
        int target = 5;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(0, result); // Assert
    }
    @Test
    public void testOneElementUnequalTarget() {
        int[] nums = {5};   // Arrange
        int target = 2;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(-1, result); // Assert
    }
    @Test
    public void testEmptyArray() {
        int[] nums = {};   // Arrange
        int target = 3;                 // Arrange
        int result = solution.search3(nums, target); // Act
        Assertions.assertEquals(-1, result); // Assert
    }
    @Test
    public void testNullArray() {
        int[] nums = null;   // Arrange
        int target = 3;                 // Arrange
        Assertions.assertThrows(IllegalArgumentException.class, () -> { // Act + Assert
            solution.search3(nums, target);
        });
    }
    @Test
    public void testDuplicateElements() {
        int[] nums = {1, 2, 2, 2, 3};
        int target = 2;

        int result = solution.search3(nums, target);

        Assertions.assertTrue(result >= 1 && result <= 3);
    }
}
