package problems1_10.mergeSortedArray;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortedArrayTest {
    MergeSortedArray solution = new MergeSortedArray();
    @Test
    public void mergeBaseCaseTest(){
        // arrange
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        // act
        solution.merge6(nums1, 3, nums2, 3);
        // assert
        Assertions.assertArrayEquals(new int[] {1,2,2,3,5,6}, nums1);
    }
    @Test
    public void mergeWithNegativeRepeatableAllHigherValuesTest(){
        int[] nums1 = {-1,2,2,3,3,3,4,0,0,0};
        int[] nums2 = {-5,-2,-2};
        solution.merge6(nums1, 7, nums2, 3);
        Assertions.assertArrayEquals(new int[] {-5,-2,-2,-1,2,2,3,3,3,4}, nums1);
    }
    @Test
    public void mergeWithEmptyNums2Test(){
        int[] nums1 = {3, 88, 622, 0};
        int[] nums2 = {};
        solution.merge6(nums1, 3, nums2, 0);
        Assertions.assertArrayEquals(new int[] {3, 88, 622,0}, nums1);
    }
    @Test void mergeWithTheSameElementsTest(){
        int[] nums1 = {3,3,3,0,0,0};
        int[] nums2 = {3,3,3};
        solution.merge6(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(new int[] {3,3,3,3,3,3}, nums1);
    }
    @Test
    public void mergeWithEmptyNums1Test(){
        int[] nums1 = {0};
        int[] nums2 = {2};
        solution.merge6(nums1, 0, nums2, 1);
        Assertions.assertArrayEquals(new int[] {2}, nums1);
    }
    @Test
    public void mergeWithNums1AllLowerValuesTest(){
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {61,62,63};
        solution.merge6(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(new int[] {1,2,3,61,62,63}, nums1);
    }
    @Test
    public void mergeWithNums1AllHigherValuesTest(){
        int[] nums1 = {10,20,30,0,0,0};
        int[] nums2 = {1,2,3};
        solution.merge6(nums1, 3, nums2, 3);
        Assertions.assertArrayEquals(new int[]{1,2,3,10,20,30}, nums1);
    }
    @Test
    public void bothSingleElementTest(){
        int[] nums1 = {5,0};
        int[] nums2 = {3};
        solution.merge6(nums1, 1, nums2, 1);
        Assertions.assertArrayEquals(new int[]{3,5}, nums1);
    }
}
