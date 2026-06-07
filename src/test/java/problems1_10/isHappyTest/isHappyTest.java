package problems1_10.isHappyTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problems1_10.isHappy.IsHappy;

public class isHappyTest {
    IsHappy solution = new IsHappy();
    @Test
    public void baseHappyTest() {
        int input = 19; // arrange
        boolean result = solution.isHappy2(input); // act
        Assertions.assertTrue(result); // assert
    }
    @Test
    public void baseUnhappyTest() {
        int n = 2;
        boolean result = solution.isHappy2(n);
        Assertions.assertFalse(result);
    }
    @Test
    public void numberOneTest() {
        int n = 1;
        boolean result = solution.isHappy2(n);
        Assertions.assertTrue(result);
    }
    @Test
    public void bigNumberTest() {
        int n = 2147483647;
        boolean result = solution.isHappy2(n);
        Assertions.assertFalse(result);
    }
    @Test
    public void largeHappyNumberTest() {
        int n = 22_222_222;
        boolean result = solution.isHappy2(n);
        Assertions.assertTrue(result);
    }
    @Test
    public void cycleEntryTest() {
        int n = 4;
        Assertions.assertFalse(solution.isHappy2(n));
    }
}
