package com.yc.practice.algorithm.maxsubarray.leetcode;
/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                int sum = first + second;
                if (sum - target == 0) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public int[] answer(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        int mask = 2048;
        int[] map = new int[mask--];
        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int diff = target - nums[i];
            if (diff == first) {
                return new int[]{0, i};
            } else {
                if (map[diff & mask] != 0) {
                    return new int[]{i, map[diff & mask]};
                }
                map[nums[i] & mask] = i;
            }
        }
        return new int[0];
    }
}
