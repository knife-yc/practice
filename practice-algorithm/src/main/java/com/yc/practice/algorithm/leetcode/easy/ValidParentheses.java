package com.yc.practice.algorithm.leetcode.easy;

public class ValidParentheses {

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * <p>
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     * <p>
     * Example 1:
     * <p>
     * Input: "()"
     * Output: true
     * Example 2:
     * <p>
     * Input: "()[]{}"
     * Output: true
     * Example 3:
     * <p>
     * Input: "(]"
     * Output: false
     * Example 4:
     * <p>
     * Input: "([)]"
     * Output: false
     * Example 5:
     * <p>
     * Input: "{[]}"
     * Output: true
     */
    public void testValidParentheses() {
        String str = "{{[()]}}";
        str = "(([]){})";
        if (str.length() == 0) {
            System.out.println(true);
            return;
        }
        if (str.length() % 2 > 0) {
            System.out.println(false);
            return;
        }
        boolean flag = true;
        int index = 0;
        while (index < str.length()) {
            char start = str.charAt(index);
            int is = judge(start, index, str);
            if (is == -1) {
                flag = false;
                break;
            } else if (is == 0) {
                flag = true;
                break;
            } else {
                index = ++is;
            }
        }
        System.out.println(flag);
    }

    private int judge(char start, int index, String str) {
        if (index + 1 >= str.length()) {
            return -1;
        }
        char next = str.charAt(index + 1);
        Pair pair = new Pair(start, next);
        if (!pair.isEligal()) {
            return -1;
        }
        if (pair.isPair()) {
            index++;
            return index;
        } else {
            ++index;
            int returnIndex = judge(next, index, str);
            if (returnIndex == str.length() - 1) {
                return 0;
            }
            if (returnIndex == -1) {
                return -1;
            }
            return judge(start, returnIndex, str);
        }
    }

    class Pair {
        private char start;
        private char end;

        public Pair(char start, char end) {
            this.start = start;
            this.end = end;
        }

        public boolean isPair() {
            if (start == '{') {
                return end == '}';
            } else if (start == '[') {
                return end == ']';
            } else {
                return end == ')';
            }
        }

        public boolean isEligal() {
            if (start == '{') {
                return end != ')' && end != ']';
            } else if (start == '[') {
                return end != ')' && end != '}';
            } else {
                return end != '}' && end != ']';
            }
        }
    }

    //最佳答案，使用了栈的思维方式
    class Solution {
        char getMatchingChar(char c){
            if(c == '}') return '{';
            if(c == ')') return '(';
            if(c == ']') return '[';
            return '\0';
        }
        public boolean isValid(String s) {
            char[] chars = new char[s.length()];
            int pos = 0;

            for(int i=0; i<chars.length; i++){
                char c = s.charAt(i);

                if(c == '{' || c == '(' || c == '['){
                    chars[pos++] = c;
                }else if(pos != 0 && chars[pos-1] == getMatchingChar(c))
                    pos--;
                else
                    return false;
            }
            return pos == 0;
        }
    }
}
