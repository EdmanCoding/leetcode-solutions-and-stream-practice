package problems1_10.IsAnagram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class isAnagramTest {
    private IsAnagram solution = new IsAnagram();
    @Test
    public void baseTrueCaseTest() {
        String s1 = "anagram";  // arrange
        String s2 = "nagaram";  // arrange
        boolean result = solution.isAnagram5(s1, s2); // act
        Assertions.assertTrue(result); //assert
    }
    @Test
    public void baseFalseCaseTest() {
        String s1 = "rat";
        String s2 = "cat";
        boolean result = solution.isAnagram5(s1, s2);
        Assertions.assertFalse(result);
    }
    @Test
    public void nullCaseTest() {
        String s1 = null;
        String s2 = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> solution.isAnagram5(s1, s2));
    }
    @Test
    public void differentLengthAndRepeatedTest() {
        String s1 = "rat";
        String s2 = "ratrat";
        boolean result = solution.isAnagram5(s1, s2);
        Assertions.assertFalse(result);
    }
    @Test
    public void sameLettersButDifferentQuantityTest() {
        String s1 = "rrtttt";
        String s2 = "rrrrtt";
        boolean result = solution.isAnagram5(s1, s2);
        Assertions.assertFalse(result);
    }
    @Test
    public void oneLetterTest() {
        String s1 = "r";
        String s2 = "r";
        boolean result = solution.isAnagram5(s1, s2);
        Assertions.assertTrue(result);
    }
    @Test
    public void emptyStringsTest() {
        String s1 = "";
        String s2 = "";
        Assertions.assertThrows(IllegalArgumentException.class, () -> solution.isAnagram5(s1, s2));
    }
}
