package com.yc.practice.algorithm.maxsubarray.leetcode;

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



}
