package problems11_20.minStack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinStackTest {
    @Test
    void topReturnsLastPushedTest(){
        MinStack2 minStack = new MinStack2(); // Arrange
        minStack.push(-2); // Act
        minStack.push(0);
        minStack.push(5);
        assertEquals(5, minStack.top()); // Assert
    }
    @Test
    void getMinReturnsMinValueTest(){
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(5);
        assertEquals(-2, minStack.getMin());
    }
    @Test
    void popChangesTopAndMinTest(){
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(5);
        minStack.push(-3);
        minStack.pop();
        minStack.pop();
        assertEquals(-2, minStack.getMin());
        assertEquals(0, minStack.top());
    }
    @Test
    void theSameElementsTest(){
        MinStack2 minStack = new MinStack2();
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        assertEquals(5, minStack.getMin());
        assertEquals(5, minStack.top());
    }
    @Test
    void onlyNegativesTest(){
        MinStack2 minStack = new MinStack2();
        minStack.push(-2);
        minStack.push(-7);
        minStack.push(-3);
        minStack.push(-5);
        minStack.pop();
        assertEquals(-7, minStack.getMin());
        assertEquals(-3, minStack.top());
    }
    @Test
    void manyDuplicatesLastDifferentTest(){
        MinStack2 minStack = new MinStack2();
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(5);
        minStack.push(33);
        assertEquals(33, minStack.top());
        assertEquals(5, minStack.getMin());
    }
}
