package com.yc.practice.algorithm.leetcode;

import com.yc.practice.algorithm.leetcode.easy.RemoveDuplicatesFromSortedArray;
import com.yc.practice.algorithm.leetcode.easy.ReverseInteger;
import org.junit.jupiter.api.Test;

public class TestClass {

   
    public void test() {
        Building b = new Building();
        House h = new House();
        Building hb = new House();
        Building p = (Building) h;
        House q = (House) b;//
        Building r = (Building) hb;
        House s = (House) hb;

    }

   
    public void testReverse() {

    }

   
    public void testReverse1(){
        ReverseInteger.reverseInteger(-12345);
    }

   
    public void isPalindrome() {
        int x = 121;
        StringBuilder builder = new StringBuilder();
        builder.append(x);
        String reverse = builder.reverse().toString();
        System.out.println(reverse.equals(x + ""));
    }

    @Test
    public void testRemoveDuplicatesFromSortedArray(){
        RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray = new RemoveDuplicatesFromSortedArray();
//        int[] array = {0,0,1,1,1,2,2,3,3,4};
        int[] array = {3,2,2,3};
//        int result = removeDuplicatesFromSortedArray.solution(array);
//        System.out.println(result);
//        result = removeDuplicatesFromSortedArray.answeer(array);
//        System.out.println(result);

        int r = removeDuplicatesFromSortedArray.removeElement(array,3);
        System.out.println(r);
    }

}
