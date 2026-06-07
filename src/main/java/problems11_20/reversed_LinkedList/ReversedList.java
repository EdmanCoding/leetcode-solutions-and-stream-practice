package problems11_20.reversed_LinkedList;

public class ReversedList {
    // chatGPT solution O(n) time, O(n) stack space
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    // my iterative solution without hints
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode result = new ListNode(head.val);
        ListNode helper = new ListNode(head.next.val);
        helper.next = result;
        head = head.next;
        while (head.next != null){
            ListNode helper2 = new ListNode(head.next.val);
            helper2.next = helper;
            helper = helper2;
            head = head.next;
        }
        return helper;
    }
    // GPT iterative solution time O(n), space O(1)
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next; // save
            head.next = prev;          // reverse pointer
            prev = head;               // advance prev
            head = next;               // advance head
        }
        return prev;
    }
    // Second try my recursive solution with hint from GPT
    public ListNode reverseList4(ListNode head) {
        if(head == null)
            return null;
        if(head.next == null)
            return head;
        ListNode temp = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }
    // My own iterative solution
    public ListNode reverseList5(ListNode head) {
        ListNode current = head;
        ListNode previous = null;
        while(current != null){
            ListNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
}
