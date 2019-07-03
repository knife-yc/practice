package com.yc.practice.algorithm.leetcode.easy;

public class ReverseInteger {

    public static int reverseInteger(int num){
        int x = 1534236469;

        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            System.out.println(0);
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(x);
        String returnVal = builder.reverse().toString();
        if (x < 0) {
            returnVal = "-" + returnVal.substring(0, returnVal.length() - 1);
            String min = Integer.MIN_VALUE + "";
            if (min.length() < returnVal.length()) {
                System.out.println(0);
                return 0;
            }
        } else {
            String max = Integer.MAX_VALUE + "";
            if (max.length() < returnVal.length()) {
                System.out.println(0);
                return 0;
            }
        }
        int result = 0;
        try {
            result = Integer.parseInt(returnVal);
        } catch (NumberFormatException e) {

        }
        System.out.println(result);
        return result;
    }

    public int anwser(int x){
        int result = 0;
        int INT_MAX = Integer.MAX_VALUE, INT_MIN = Integer.MIN_VALUE;

        while (x != 0) {
            int curDigit = x % 10;
            // positive overflow
            if (result > INT_MAX / 10 || (result == INT_MAX / 10 && curDigit > 7)) {
                return 0;
            }

            // negative overflow
            if (result < INT_MIN / 10 || (result == INT_MIN / 10 && curDigit < -8)) {
                return 0;
            }

            // push operation
            result = result * 10 + curDigit;
            // pop operation
            x = x / 10;
        }

        return result;
        // TC : logn -> n is no of digits in x
    }

}
