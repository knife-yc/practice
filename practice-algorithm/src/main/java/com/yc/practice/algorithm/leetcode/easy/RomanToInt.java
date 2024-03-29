package com.yc.practice.algorithm.leetcode.easy;
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: "III"
Output: 3

Example 2:

Input: "IV"
Output: 4

Example 3:

Input: "IX"
Output: 9

Example 4:

Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 5:

Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInt {
    public int romanToInt(String s) {
        if(s==null||s.length()==0)
            return 0;
        int sum=0;
        char[] charArray=s.toCharArray();
        char tmp=charArray[0];
        if(charArray.length==1){
            if(tmp=='I'){
                return 1;
            }else if(tmp=='V'){
                return 5;
            }else if(tmp=='X'){
                return 10;
            }else if(tmp=='L'){
                return 50;
            }else if(tmp=='C'){
                return 100;
            }else if(tmp=='D'){
                return 500;
            }else if(tmp=='M'){
                return 1000;
            }
        }
        for(int i= 1;i< charArray.length;i++){
            char current = charArray[i];
            switch(tmp){
                case 'I':
                    if(current=='V'){
                        sum += 4;
                        if(i<charArray.length-1){
                            tmp=charArray[++i];}else {
                            tmp='0';
                        }
                    }else if(current=='X'){
                        sum += 9;
                        if(i==charArray.length-1){
                            tmp='0';
                        }else{
                            tmp=charArray[++i];
                        }

                    }else{
                        sum+=1;
                    }
                    break;
                case 'X':
                    if(current=='L'){
                        sum+=40;
                        if(i<charArray.length-1){
                            tmp=charArray[++i];}else{
                            tmp='0';
                        }

                    }else if(current=='C'){
                        sum+=90;
                        if(i<charArray.length-1){
                            tmp=charArray[++i];}else{
                            tmp='0';
                        }
                    }else{
                        sum+=10;
                        tmp=current;
                    }
                    break;
                case 'C':
                    if(current=='D'){
                        sum+=400;
                        if(i<charArray.length-1){
                            tmp=charArray[++i];}else{
                            tmp='0';
                        }
                    }else if(current=='M'){
                        sum+=900;
                        if(i<charArray.length-1){
                            tmp=charArray[++i];}else{
                            tmp='0';
                        }
                    }else{
                        sum+=100;
                        tmp=current;
                    }
                    break;
                case 'V':
                    sum+=5;
                    tmp=current;
                    break;
                case 'D':
                    sum+=500;
                    tmp=current;
                    break;
                case 'M':
                    sum+=1000;
                    tmp=current;
                    break;
                case 'L':
                    sum+=50;
                    tmp=current;
                    break;
            }
        }
        if(tmp=='I'){
            sum+=1;
        }else if(tmp=='V'){
            sum+=5;
        }else if(tmp=='X'){
            sum+=10;
        }else if(tmp=='L'){
            sum+=50;
        }else if(tmp=='C'){
            sum+=100;
        }else if(tmp=='D'){
            sum+=500;
        }else if(tmp=='M'){
            sum+=1000;
        }

        return  sum;
    }
}
