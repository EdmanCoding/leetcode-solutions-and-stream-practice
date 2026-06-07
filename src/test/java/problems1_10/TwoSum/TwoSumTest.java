package problems1_10.TwoSum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TwoSumTest {
    private TwoSum solution = new TwoSum();

    @Test
    @DisplayName("Should return [0,1] for nums=[2,7,11,15] and target=9")
    public void testBasicCase() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum3(nums, target);
        assertArrayEquals(new int[]{0, 1}, result); // indices 0 and 1
    }
    @Test
    public void testNegativeNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] result = solution.twoSum3(nums, target);
        assertArrayEquals(new int[]{0, 2}, result); // -3 + 3 = 0
    }
    @Test
    public void testSameElementTwice() {
        int[] nums = {3, 3};
        int target = 6;
        int[] result = solution.twoSum3(nums, target);
        assertArrayEquals(new int[]{0, 1}, result);
    }
    @Test
    public void testLargeNumbers() {
        int[] nums = {1000000, 2000000, 3000000};
        int target = 5000000;
        int[] result = solution.twoSum3(nums, target);
        assertArrayEquals(new int[]{1, 2}, result);
    }
    @Test
    public void testNoSolutionThrowsException() {
        int[] nums = {1, 2, 3};
        int target = 100;
        assertThrows(IllegalArgumentException.class, () -> {
            solution.twoSum3(nums, target);
        });
    }

}
