package problems1_10.ContainsDuplicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContainsDuplicateTest {
    ContainsDuplicate solution = new ContainsDuplicate();

    @Test
    public void baseTrueCaseTest(){
        int[] nums = {1, 2, 3, 1};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertTrue(result);
    }
    @Test
    public void baseFalseCaseTest(){
        int[] nums = {1, 2, 3, 4};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertFalse(result);
    }
    @Test
    public void oneElementTest(){
        int[] nums = {1};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertFalse(result);
    }
    @Test
    public void bigNumbersTrueTest(){
        int[] nums = {9048593, 9395839, 949583, 2284893, 9443938, 438284, 9395839, 2038502};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertTrue(result);
    }
    @Test
    public void bigNumbersFalseTest(){
        int[] nums = {9048593, 48594895, 38885,3859385,9989893,222223};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertFalse(result);
    }
    @Test
    public void nullArrayTest(){
        int[] nums = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            solution.containsDuplicate3(nums);
        });
    }
    @Test
    public void emptyArrayTest(){
        int[] nums = {};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertFalse(result);
    }
    @Test
    public void negativeElementsTest(){
        int[] nums = {-1, 4, 29, 3, -1, 5, 9};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertTrue(result);
    }
    @Test
    public void duplicateAtEndTest(){
        int[] nums = {1,2,3,4,5,5};
        boolean result = solution.containsDuplicate3(nums);
        Assertions.assertTrue(result);
    }
}
