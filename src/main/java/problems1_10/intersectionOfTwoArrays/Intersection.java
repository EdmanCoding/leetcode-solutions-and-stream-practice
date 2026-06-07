package problems1_10.intersectionOfTwoArrays;

import java.util.*;
import java.util.stream.Collectors;

public class Intersection {
    //my initial slow solution, O(n^2), 10 ms
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i<nums1.length;i++){
            for(int j = 0; j<nums2.length;j++){
                if(nums1[i]==nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }
        int[] result = set.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }
    // second solution
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for(int el:nums1){
            set1.add(el);
        }
        for(int el:nums2){
            set2.add(el);
        }
        set1.retainAll(set2);
        return set1.stream().mapToInt(Integer::intValue).toArray();
    }
    // O(n+m) solution with hints from chatGTP
    public static int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        if(nums1.length<=nums2.length){
            for(int el:nums1) {set.add(el);}
            for(int i =0; i<nums2.length; i++){
                if(set.contains(nums2[i]))
                    result.add(nums2[i]);
            }
        }
        else {
            for(int el:nums2) {set.add(el);}
            for(int i =0; i<nums1.length; i++){
                if(set.contains(nums1[i]))
                    result.add(nums1[i]);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Professional solution. Time: O(n + m). Space: O(min(n, m))
    public static int[] intersection4(int[] nums1, int[] nums2) {
        if(nums1==null||nums2==null)
            throw new IllegalArgumentException();
        Set<Integer> set = new HashSet<>();
        if(nums1.length<=nums2.length) {
            for (int el : nums1) {
                set.add(el);
            }
            int[] result = new int[nums1.length];
            int index = 0;
            for (int el : nums2) {
                if(set.remove(el))
                    result[index++] = el;
            }
            return Arrays.copyOf(result, index);
        }
        else {
            for (int el : nums2) {
                set.add(el);
            }
            int[] result = new int[nums2.length];
            int index = 0;
            for (int el : nums1) {
                if(set.remove(el))
                    result[index++] = el;
            }
            return Arrays.copyOf(result, index);
        }
    }

    // Sort both arrays, use two pointers
    // Time complexity: O(nlogn)
    public static int[] intersection5(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    // Binary search. Time complexity: O(nlogn)
    public int[] intersection6(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int i = 0;
        int[] result = new int[set.size()];
        for (Integer num : set) {
            result[i++] = num;
        }
        return result;
    }
    public boolean binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
    // the second attempt by myself. Almost right solution :)
    public int[] intersection7(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums1)
            set.add(num);
        List<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(set.contains(num)){
                if(!list.contains(num))
                    list.add(num);
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
    // stream style
    public static int[] intersection8(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2)
                .filter(set1::contains)
                .distinct()
                .toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {85484,485983,283958,283934,99334,83849,20664,29583,5818,38594,2228,28599};
        int[] nums2 = {95830,446123,56433,53466,99334,25426,21676,135361,52352167,236436,23526,44322};
        System.out.println(Arrays.toString(intersection4(nums1, nums2)));
    }
}
