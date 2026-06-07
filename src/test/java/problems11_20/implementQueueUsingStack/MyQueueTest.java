package problems11_20.implementQueueUsingStack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyQueueTest {
    @Test
    public void popTest() {
        // Arrange
        MyQueue5 queue = new MyQueue5();
        // Act
        queue.push(5);
        queue.push(20);
        queue.push(14);
        // Assert
        assertEquals(5, queue.pop());
    }
    @Test
    public void peekTest() {
        MyQueue5 queue = new MyQueue5();
        queue.push(5);
        queue.push(20);
        queue.push(14);
        assertEquals(5, queue.peek());
    }
    @Test
    public void emptyTrueTest() {
        MyQueue5 queue = new MyQueue5();
        assertTrue(queue.empty());
    }
    @Test
    public void emptyTrueAfterPushPopTest() {
        MyQueue5 queue = new MyQueue5();
        queue.push(5);
        queue.push(20);
        queue.push(14);
        queue.pop();
        queue.pop();
        queue.pop();
        assertTrue(queue.empty());
    }
    @Test
    public void peekDoesntPopTest() {
        MyQueue5 queue = new MyQueue5();
        queue.push(5);
        queue.push(20);
        queue.push(14);
        queue.peek();
        assertEquals(5, queue.peek());
    }
    @Test
    public void correctPopOrderTest() {
        MyQueue5 queue = new MyQueue5();
        queue.push(5);
        queue.push(20);
        queue.push(14);
        queue.pop();
        assertEquals(20, queue.pop());
    }
    @Test
    public void severalPopsAndPushesTest() {
        MyQueue5 queue = new MyQueue5();
        queue.push(5);
        queue.push(20);
        queue.pop();
        queue.push(14);
        queue.pop();
        queue.push(15);
        queue.push(20);
        assertEquals(14, queue.pop());
    }
}
