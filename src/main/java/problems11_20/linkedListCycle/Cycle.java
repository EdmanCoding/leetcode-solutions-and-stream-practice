package problems11_20.linkedListCycle;


import java.util.HashSet;

//Definition for singly-linked list.
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
         val = x;
          next = null;
      }
  }
public class Cycle {
      // my first own silly solution
    public static boolean hasCycle(ListNode head) {
        int count = 0;
        while(head!=null){
            head=head.next;
            ++count;
            if(count>10000)
                return true;
        }
        return false;
    }
    // my not working solution
    public static boolean hasCycle2(ListNode head) {
        ListNode start = head;
        while(head!=null){
            head=head.next;
            if(head==null)
                return false;
            ListNode traverse = start;
            if(traverse == head)
                return true;
            while(traverse!=head){
                traverse=traverse.next;
                if(head==traverse)
                    return true;
            }
        }
        return false;
    }
    // Floyd’s slow–fast pointers
    // If there is a cycle, a fast pointer will eventually meet a slow pointer
    // Time O(n), space O(1)
    public boolean hasCycle3(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // 1 step
            fast = fast.next.next;      // 2 steps

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    // my solution with HashSet. 
    public boolean hasCycle4(ListNode head) {
        if (head == null) return false;
        HashSet<ListNode> check = new HashSet<>();
        while(head!=null){
            if(check.add(head))
                head=head.next;
            else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(hasCycle2(head));
    }
}
