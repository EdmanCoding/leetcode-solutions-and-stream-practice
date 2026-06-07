package problems1_10.intersectionOfTwoArrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionTest {
    Intersection solution = new Intersection();
    @Test
    public void baseCaseTest(){
        // ARRANGE
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Set<Integer> expected = Set.of(4,9);

        // ACT
        int[] result = solution.intersection4(nums1, nums2);

        // ASSERT
        Set<Integer> actual = Arrays.stream(result)
                .boxed()
                .collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void nums2WithMultipleNonIntersectedElementTest(){
        int[] nums1 = {6,4,3};
        int[] nums2 = {3,5,6,1,1,1,1};
        Set<Integer> expected = Set.of(3,6);
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void multipleButSameIntersectedElementsTest(){
        int[] nums1 = {6,4,4,4,3};
        int[] nums2 = {1,7,4,4,4,4};
        Set<Integer> expected = Set.of(4);
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void oneByOneNonIntersectedTest(){
        int[] nums1 = {3};
        int[] nums2 = {9};
        Set<Integer> expected = Set.of();
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void nullNumsTest(){
        int[] nums1 = null;
        int[] nums2 = {4,10,22};
        Assertions.assertThrows(IllegalArgumentException.class, () -> solution.intersection4(nums1, nums2));
    }
    @Test
    public void oneByOneIntersectedTest(){
        int[] nums1 = {5};
        int[] nums2 = {5};
        Set<Integer> expected = Set.of(5);
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void emptyNumsTest(){
        int[] nums1 = {};
        int[] nums2 = {4,1,29};
        Set<Integer> expected = Set.of();
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void bigValuesManyElementsOneIntersectedTest(){
        int[] nums1 = {85484,485983,283958,283934,99334,83849,20664,29583,5818,38594,2228,28599};
        int[] nums2 = {95830,446123,56433,53466,99334,25426,21676,135361,52352167,236436,23526,44322};
        Set<Integer> expected = Set.of(99334);
        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void identicalArraysTest(){
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {1,2,3,4};
        Set<Integer> expected = Set.of(1,2,3,4);

        int[] result = solution.intersection4(nums1, nums2);
        Set<Integer> actual = Arrays.stream(result).boxed().collect(Collectors.toSet());

        Assertions.assertEquals(expected, actual);
    }
}
