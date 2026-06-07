package problems11_20.binarySearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {
    BinarySearch solution = new BinarySearch();

    @Test
    public void baseHappyCaseTest() {
        // Arrange
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(4, result);
    }
    @Test
    public void baseFailureCaseTest() {
        // Arrange
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 2;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(-1, result);
    }
    @Test
    public void happyCaseLengthOneTest() {
        // Arrange
        int[] nums = new int[]{5};
        int target = 5;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(0, result);
    }
    @Test
    public void failureCaseLengthOneTest() {
        // Arrange
        int[] nums = new int[]{5};
        int target = 4;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(-1, result);
    }
    @Test
    public void happyCaseLengthTwoHugeRangeTest() {
        // Arrange
        int[] nums = new int[]{-9999,9999};
        int target = -9999;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(0, result);
    }
    @Test
    public void failureCaseLengthTwoHugeRangeTest() {
        // Arrange
        int[] nums = new int[]{-9999,9999};
        int target = 1;
        // Act
        int result = solution.search(nums, target);
        // Assert
        assertEquals(-1, result);
    }
}
