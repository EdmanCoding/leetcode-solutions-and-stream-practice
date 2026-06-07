package problems1_10.mergeSortedArray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeSortedArray {
    // initial solution O(n+m) time, O(n+m) space
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list = new LinkedList<>();
        int nums1index = 0;
        int nums2index = 0;
        for( int i = 0; i<nums1.length; i++){
            if (nums2index<nums2.length){
                if (nums1[nums1index]<nums2[nums2index]&&nums1index<m){
                    list.add(nums1[nums1index]);
                    nums1index++;
                    continue;
                }
                else{
                    list.add(nums2[nums2index]);
                    nums2index++;
                    continue;
                }
            }
            list.add(nums1[nums1index]);
            nums1index++;
        }
        int i = 0;
        for(Integer num : list){
            nums1[i++] = num;
        }
    }
    // after the hint, that I should iterate from the end. O(n+m) time, O(1) space
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int a = m-1;
        int b = n-1;
        for(int i = nums1.length-1; i>=0; i--){
            if(b>=0){
                if(a>=0&&nums1[a]>nums2[b]){
                    nums1[i]=nums1[a];
                    a--;
                }
                else{
                    nums1[i]=nums2[b];
                    b--;
                }
            }
        }
    }
    // more elegant from chatGPT but the same as merge2
    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int a = m-1;
        int b = n-1;
        int i = nums1.length-1;
        while (b >= 0) {
            if (a >= 0 && nums1[a] > nums2[b]) {
                nums1[i--] = nums1[a--];
            } else {
                nums1[i--] = nums2[b--];
            }
        }
    }
    // first resolving - failed attempt
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int[] peasant = new int[m];
        for(int i = 0; i<m; i++)
            peasant[i]=nums1[i];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i<m||j<n){
            if(i<m && j<n && peasant[i]<=nums2[j])
                nums1[k++]=peasant[i++];
            else if (j>0)
                nums1[k++]=nums2[j++];
        }
    }
    // first resolving after the only hint, that I should start from nums1 back
    // Time: O(m + n), Space: O(1)
    public static void merge5(int[] nums1, int m, int[] nums2, int n) {
        int n1 = m - 1;
        int n2 = n - 1;
        for (int i = (m + n) - 1; i >= 0; i--) {
            if (n2<0)
                nums1[i] = nums1[n1--];
            else if (n1<0)
                nums1[i] = nums2[n2--];
            else if (nums1[n1] >= nums2[n2])
                nums1[i] = nums1[n1--];
            else {
                nums1[i] = nums2[n2--];
            }
        }
    }

    public static void main(String[] args) {
        /*int[] nums1 = {-1,0,0,3,3,3,0,0,0};
        int[] nums2 = {1,2,2};*/
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge2(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
