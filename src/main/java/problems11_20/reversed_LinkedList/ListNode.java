package problems11_20.reversed_LinkedList;

import java.util.Objects;

//Definition for singly-linked list.
public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ListNode that)) return false;
          return val == that.val && Objects.equals(next, that.next);
      }
      @Override
      public int hashCode() {
            return Objects.hash(val, next);
      }
 }
