package problems11_20.reverseLinkedList;

import org.junit.jupiter.api.Test;
import problems11_20.reversed_LinkedList.ListNode;
import problems11_20.reversed_LinkedList.ReversedList;

import static org.assertj.core.api.Assertions.assertThat;

public class ReverseListTest {
    ReversedList solution = new ReversedList();
    @Test
    public void baseListTest() {
        // Arrange
        ListNode head = new ListNode(1, new ListNode(2));
        // Act
        head = solution.reverseList(head);
        // Assert
        assertThat(head).isEqualTo(new ListNode(2, new ListNode(1)));
    }
    @Test
    public void longListTest() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = solution.reverseList(head);
        assertThat(head).isEqualTo(new ListNode(5,new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))))));
    }
    @Test
    public void oneNodeTest() {
        ListNode head = new ListNode(1);
        head = solution.reverseList(head);
        assertThat(head).isEqualTo(new ListNode(1));
    }
    @Test
    public void emptyListTest() {
        ListNode head = null;
        head = solution.reverseList(head);
        assertThat(head).isNull();
    }
}
