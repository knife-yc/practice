package com.yc.practice.algorithm.leetcode.medium;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LengthOfLongestSubstring {

    public int solution(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] set = new int[128];
        for (int i = 0; i < set.length; i++)
            set[i] = -1;

        char[] cs = s.toCharArray();
        int start = 0, max = 0;
        for (int i = 0; i < cs.length; i++) {
            if (set[cs[i]] >= start)
                start = set[cs[i]] + 1;
            max = Math.max(max, i - start + 1);
            set[cs[i]] = i;
        }
        return max;
    }


    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int max = 0;
        String tmp = "";
        for (int i = 0; i < s.length(); i++) {
            if (tmp.contains(s.charAt(i) + "")) {
                int length = tmp.length();
                if (max < length) {
                    max = length;
                }
                i = i - length + 1;
                tmp = s.charAt(i) + "";
            } else {
                tmp += s.charAt(i);
            }
        }
        if (max < tmp.length())
            max = tmp.length();
        return max;
    }

}
