package com.yc.practice.algorithm.maxsubarray.leetcode;

public class LongestCommonPrefix {
    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     * Example 1:
     *
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     *
     * Input: ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * Note:
     *
     * All given inputs are in lowercase letters a-z.
     */

    public String answer(String[] strs) {
        if(strs.length == 0)
            return "";
        // find the shortest word
        String min = strs[0];
        for(int i = 1; i < strs.length; i++)
        {
            if(strs[i].length() < min.length())
                min = strs[i];
        }
        boolean maxPrefix = false;
        // keep attempting to see if the 'min' word is a prefix of all
        // if not, take away the last character of 'min', to test a smaller and
        // smaller version each time
        while(!maxPrefix)
        {
            maxPrefix = true;
            for(int i = 0; i < strs.length; i++)
            {
                if(!isPrefix(min, strs[i]))
                    maxPrefix = false;
            }
            if(maxPrefix)
                return min;
            min = min.substring(0, min.length() - 1);
        }
        return "";
    }

    public boolean isPrefix(String prefix, String word)
    {
        if(word.indexOf(prefix) == 0)
            return true;
        else
            return false;
    }

    public void myCase() {
        String[] strs1 = {"dog", "racecar", "car"};
        String[] strs = {"c", "ac", "accc"};
        String prefix = getCommonPrefix(strs, 1);
        System.out.println(prefix);
    }

    private String getCommonPrefix(String[] strs, int index) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String tmpStr = strs[0];
        boolean outOfRange = isIndexOutOfRange(tmpStr, index);
        if (outOfRange) {
            return tmpStr.substring(0, --index);
        }

        String prefix = tmpStr.substring(0, index);
        for (int i = 1; i < strs.length; i++) {
            boolean isOutOfRange = isIndexOutOfRange(strs[i],index);
            if(isOutOfRange){
                return tmpStr.substring(0, --index);
            }
            String tmp = strs[i].substring(0,index);
            if (!tmp.equals(prefix)) {
                return tmpStr.substring(0, --index);
            }
        }
        return getCommonPrefix(strs, ++index);
    }

    private boolean isIndexOutOfRange(String str, int index) {
        if (str.length() < index) {
            return true;
        }
        return false;
    }


}
