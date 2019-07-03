package com.yc.practice.algorithm.leetcode.easy;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDuplicatesFromSortedArray {

    //不需要改变数组大小

    public int solution(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        nums = getUniqueArr(0, nums);
        return nums.length;
    }

    private int[] getUniqueArr(int index, int[] array) {
        boolean containRerpeatNum = false;
        for(;index < array.length;index++){
            int tmp = array[index];
            for (int tmpIndex = 1; tmpIndex < array.length && tmpIndex != index; tmpIndex++) {
                if (tmp == array[tmpIndex]) {
                    containRerpeatNum = true;
                    break;
                }
            }
            if(containRerpeatNum){
                break;
            }
        }

        if (containRerpeatNum) {
            int[] tmpArr = new int[array.length - 1];
            for (int i = 0, j = 0; i < array.length; i++) {
                if (i != index) {
                    tmpArr[j++] = array[i];
                }
            }
            return getUniqueArr(0, tmpArr);
        } else {
            return array;
        }

    }


    //使用动态规划
    public int answeer(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == val){
                length--;
            }
        }
        return length;
    }

}
