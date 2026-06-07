package problems11_20.linkedListCycle;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CycleTest {
    Cycle solution = new Cycle();
    @Test
    public void shouldReturnFalse_WhenListIsOneNode() {
        // Arrange
        ListNode head = new ListNode(1);
        // Act
        boolean result = solution.hasCycle3(head);
        // Assert
        assertThat(result).isFalse();
    }
    @Test
    public void shouldReturnTrue_WhenListIsTwoNodesCycle() {
        // Arrange
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        head.next = node;
        node.next = head;
        // Act
        boolean result = solution.hasCycle3(head);
        // Assert
        assertThat(result).isTrue();
    }
    @Test
    public void shouldReturnTrue_WhenListBigAndCycled() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(-3);
        ListNode node2 = new ListNode(99);
        ListNode node3 = new ListNode(-17);
        ListNode node4 = new ListNode(99);
        ListNode node5 = new ListNode(14);
        ListNode node6 = new ListNode(2);
        head.next = node;
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = head;

        boolean result = solution.hasCycle3(head);

        assertThat(result).isTrue();
    }
    @Test
    public void shouldReturnFalse_WhenListBigUncycled() {
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(-3);
        ListNode node2 = new ListNode(99);
        ListNode node3 = new ListNode(-17);
        ListNode node4 = new ListNode(99);
        ListNode node5 = new ListNode(14);
        ListNode node6 = new ListNode(2);
        head.next = node;
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        boolean result = solution.hasCycle3(head);

        assertThat(result).isFalse();
    }
    @Test
    public void shouldReturnFalse_WhenListIsNull() {
        ListNode head = null;

        boolean result = solution.hasCycle3(head);

        assertThat(result).isFalse();
    }
 }
