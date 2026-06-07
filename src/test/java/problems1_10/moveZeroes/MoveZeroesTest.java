package problems1_10.moveZeroes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveZeroesTest {
    MoveZeroes solution = new MoveZeroes();
    @Test
    public void baseCaseTest() {
        int[] input = {0,1,0,3,12}; // Arrange
        solution.moveZeroes3(input); // Act
        Assertions.assertArrayEquals(new int[]{1,3,12,0,0}, input); // Assert
    }
    @Test
    public void emptyInputTest() {
        int[] input = {};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{}, input);
    }
    @Test
    public void oneZeroTest() {
        int[] input = {0};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{0}, input);
    }
    @Test
    public void twoZeroesTest() {
        int[] input = {0,0};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{0,0}, input);
    }
    @Test
    public void oneNonZeroTest() {
        int[] input = {1};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{1}, input);
    }
    @Test
    public void nullTest() {
        int[] input = null;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> solution.moveZeroes3(input));
    }
    @Test
    public void manyZeroesOneNonZeroAtTheEndTest() {
        int[] input = {0,0,0,0,0,0,0,0,0,5};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{5,0,0,0,0,0,0,0,0,0}, input);
    }
    @Test
    public void basicNonAscendingElementsTest() {
        int[] input = {58,0,4,0,26};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{58,4,26,0,0}, input);
    }
    @Test
    public void nonZeroesInTheMiddleTest() {
        int[] input = {0,0,0,0,5,1,26,0,0,0,0};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{5,1,26,0,0,0,0,0,0,0,0}, input);
    }
    @Test
    public void zeroesInTheMiddleTest() {
        int[] input = {10,0,0,0,0,0,5};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{10,5,0,0,0,0,0}, input);
    }
    @Test
    public void allNonZeroesTest() {
        int[] input = {1,2,3,4};
        solution.moveZeroes3(input);
        Assertions.assertArrayEquals(new int[]{1,2,3,4}, input);
    }
}
