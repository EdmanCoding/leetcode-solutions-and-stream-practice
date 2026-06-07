Invariant must describe state during execution, not only final result.
Invariant is what remains true during iteration.
A loop invariant is:
    A property that remains true before and after every iteration of a loop.
Key point: before and after every iteration, not just at the end.

What Dynamic Programming really is:
1) Problem can be broken into overlapping subproblems;
2) You must consider multiple choices;
3) You store results to avoid recomputation.


    TwoSum
Data Structure: HashMap
Pattern: subtract the current element from the target and check for the match in HashMap
Trick: Put each element after the match check
Edge case: return empty array or throw exception when a match not found
Complexity(optimal): O(n) time and space (due to HashMap)
Invariant: map contains elements seen so far

    ValidParentheses
Data Structure: Stack
Pattern: push each opening parenthesis onto the stack.
When we encounter a closing bracket, the stack must be non-empty and its top must match the 
corresponding opening bracket.
Caveat: After traversing the string, the stack must be empty.
Complexity(optimal): O(n) time and space (due to Stack for each open parentheses)
Trick: reverse the parentheses when push to the Stack

    BinarySearch
Pattern: in each iteration, find the middle index and compare array[mid] with the target.
if target is higher -> left = mid+1, if target is lower -> right = mid-1, then evaluate 
new middle with updated right or left. If target == array[middle] -> return true.
Loop condition: while (left <= right)
Invariant: if execution leaves the loop, the target is not present in the array, so return false.
Caveats: 
• Using while (left < right) fails for arrays with a single element equal to the target, 
    therefore <= is mandatory. 
• If you assign right = mid or left = mid, you will end up in an infinite loop,
    so right = mid-1 and left=mid+1 are mandatory!
• Algorithm works only with sorted arrays
• Mid should be computed as left + (right - left) / 2 to avoid integer overflow.
    (int left  = 2_000_000_000;  int right = 2_100_000_000;  int mid = (left + right) / 2;
    left + right = 4_100_000_000)

    Maximum depth of binary tree
Pattern: recursively compute the depth of left and right subtrees and return
Invariant: depth = 1 + max(leftDepth, rightDepth).
Base case: if node == null, return 0.
Complexity:
• Time: O(n) — each node is visited once
• Space: O(h) — recursion stack, where h is the height of the tree

        Maximum Depth of Binary Tree (iterative)
Pattern: level-order traversal (BFS) using a queue.
Each level processed increases depth by 1.
Queue initially contains root.
Loop while queue not empty, process all nodes of current level.
Depth equals number of completed levels.
Complexity: O(n) time, O(w) space (max width of tree)

        Best time to buy and sell stock
Pattern: prefix minimum tracking
        At each index i:
    - track the minimum price seen in range [0, i]
    - compute potential profit = prices[i] − minPriceSoFar
    - update maxProfit if this profit is larger
Invariant:
    minPriceSoFar = minimum of prices[0..i]
    maxProfit = maximum of prices[j] − prices[i] for all valid pairs i < j seen so far
Complexity:
    Time: O(n)
    Space: O(1)

        Contains duplicate
Pattern: HashSet membership tracking
Invariant:
The set contains all unique elements seen so far.
At each element nums[i]:
    - if element already exists in set → duplicate found
    - otherwise add element to set
Complexity: O(n) time and space

        Valid anagram
Pattern: frequency difference tracking using fixed-size array
    Each index represents a letter from 'a' to 'z'.
    For each position i:
    - increment count for s.charAt(i)
    - decrement count for t.charAt(i)
Invariant:
    Array stores the difference in frequency between characters seen so far in s and t.
    If s and t are anagrams, all counts must be zero at the end.
True invariant:
    counts[c] = frequency of c in s[0..i] − frequency of c in t[0..i]
Complexity: Time O(n), Space O(1) (array size is constant: 26)

        First Unique Character in a String
Pattern: Frequency counting using fixed-size array.
First pass: Count frequency of each character.
Invariant (first loop):
    counts[c] = number of occurrences of character c in s[0..i−1]
Second pass: Scan string in original order to find first unique character.
Invariant (second loop):
    No character in s[0..i−1] has frequency 1.
Complexity: Time O(n) (O(n+n) due to two loops), Space O(1) (array size is constant: 26)

Invariant for Queue solution:
    The queue always contains only indices of characters with frequency 1,
    so we remove from the front until that condition is restored.

        LRUCache
Data Structures:
- HashMap<Integer, Node> for O(1) key lookup
- Doubly linked list to maintain usage order
Node: contains key, value, prev, next
Structure:
- head and tail are dummy nodes
- head.next = most recently used (MRU)
- tail.prev = least recently used (LRU)
Invariant:
1. Map contains exactly all nodes currently in the linked list.
2. head.next always points to most recently used node.
3. tail.prev always points to least recently used node.
4. All operations maintain O(1) complexity. 
5. the accessed/inserted node becomes head.next (MRU)

         Move Zeroes
Pattern: 
- loop through each element of the array using fast pointer
- write to the slow pointer 
- write only when slow pointer points to 0 and fast != slow
- fast >= slow
- if fast != 0 and fast != slow => slow = 0
Invariant:
- All elements in range [0 .. slow-1] are non-zero and in original relative order.
Complexity: O(n) time, O(1) space.

      Intersection of Two Arrays
Data Structures: two HashSets. Using sets, we provide uniqueness of the elements in the result.
Pattern:    First loop - add all elements from the first array to the set.
            Second loop - add all elements that set.contains()==true to the result set.
Invariant:  The result set contains only unique elements
            that appear in both arrays and have been processed so far.
Complexity: O(n+m) time, O(n) space.

        Merge sorted array
Invariant:  At every iteration Positions (i+1) .. (m+n-1) are already correctly filled
            with the largest elements from nums1 and nums2.
Pattern:    fill nums1 from the back by biggest of the nums1 and nums2 seen so far.
            Each iteration decrement (n+m)-1 index and of n-1 or m-1.
            If (n-1) index < 0, stop iteration.
Complexity: Time: O(m + n), Space: O(1)

        Happy number
Invariant:
    Next number = sum of squares of digits of current number
Pattern:
    Cycle detection (Floyd's algorithm)
    Slow pointer → next(n)
    Fast pointer → next(next(n))
    If number is happy → sequence reaches 1
    If not → sequence enters a cycle and slow == fast
Complexity:
    Time: O(log n), Space: O(1)

        Min stack
Data Structures: double linked list to implement stack
Invariant: top Node always contains value of minimum element in the stack
Pattern: each node should contain additional int variable to track minimum value seen so far in the stack.
         implemented by comparing pushed value with the minimum variable of the previous element.
Complexity: Time O(1) for each method, Space O(n)
Each push allocates a node, so total memory grows with stack size.
This trick appears in many harder problems:
    store extra information per node to avoid recomputation.

        Implementing Queue using Stacks
Data Structures:
Two stacks: input stack and output stack

Pattern:
Push → always push to input stack
Pop/Peek → if output stack empty, move all elements from input to output
This reverses LIFO order into FIFO order.

Invariant:
output stack top is next queue element.

Complexity:
push  O(1)
pop   amortized O(1)
peek  amortized O(1)
space O(n)

        Binary search
Pattern:    Maintain a search range [start, end] and repeatedly cut it in half
            using mid.
Invariant:  If the target exists, it is always inside the range [start, end].
            target ∈ [start, end].
Complexity: time O(logn), space O(1).
    Bad version:
        int mid = (start + end) / 2;
    Why this can fail:
        If start and end are very large (close to Integer.MAX_VALUE), their sum can overflow.
    Correct version used in production code:
        int mid = start + (end - start) / 2;
    This bug actually lived for 9 years in Java’s standard library before it was discovered.

        Maximum Subarray (Kadane’s Algorithm)
Pattern:
    Dynamic programming with running prefix decision.
Invariant:
    currentSum = maximum subarray sum ending at index i (the best subarray that MUST end at i).
    bestSoFar = best subarray seen ANYWHERE so far.
Transition:
    Either extend the previous subarray or start a new one.
    currentSum = max(nums[i], currentSum + nums[i])
    bestSoFar = max(bestSoFar, currentSum)
Complexity: Time O(n), Space O(1)

        Invert binary tree (recursive DFS)
Pattern:    swap each node that is leaf with its opposite (left->right, right->left)
Invariant:  After invertTree(node) finishes, the subtree rooted at node is fully inverted.
Base case:  if node == null, return null.
Complexity: • Time: O(n) — each node is visited once
            • Space: O(h) — recursion stack, where h is the height of the tree

        Balanced binary tree
Base case:  Math.abs(root.left-root.right)>1 -> return false
Pattern:    Use the method with recursive DFS to track the difference in heights of nodes.
            After first encounter of difference more than 1, return false.
            Use if statement to recursively return the false int flag.
Invariant:  When processing a node, heights of both subtrees are already known;
            The function returns the height of the subtree if it is balanced, 
            otherwise it returns -1.
Complexity: • Time: O(n) — each node is visited once
            • Space: O(h) — recursion stack, where h is the height of the tree

        Binary Tree Preorder Traversal
Pattern:    Preorder iterative = stack stores nodes that must be visited later
            Visit node → push right child → move to left child
Invariant:  Stack always contains nodes whose right subtree is not processed yet
Complexity: O(n) time, O(h) space (h = tree height)

        Reverse Linked List (iterative)
Pattern:    3 pointers -> previous – current – next. Initially previous = null;
Invariant:  each iteration current node reverses its next
Complexity: O(n) time, O(1) space

        Reverse Linked List (recursive)
Base case: head.next == null;
Pattern:    descend recursively the last node. return it to the previous level of stack
            and save to result node. From there onward reverse the pointer by 
            current.next.next = current and assign null to current.next to break infinite cycle.
Invariant:  each recursive level pointer of current.next reversed
Complexity: O(n) time, O(n) space (where n - height of stack, which equal to List.size())

        Linked List Cycle
Pattern:    use slow and fast pointers. Each iteration slow moves one node forward, fast - two nodes.
            If a cycle exists, fast eventually catches slow inside the cycle.
            This way O(1) space provided.
Invariant:  fast pointer two times ahead of slow pointer.
Complexity: O(n) time, O(1) space.

