package com.yc.practice.algorithm.sort;

import com.yc.practice.algorithm.maxsubarray.leetcode.ListNode;
import com.yc.practice.algorithm.maxsubarray.leetcode.MergeTwoSortedLists;
import org.junit.Test;

public class SortTest {

    @Test
    public void testInsertSort(){
        int[] testArray = {5,2,4,6,1,3};
        Sort.insertSort(testArray);
    }

    @Test
    public void  test(){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        ListNode result = mergeTwoSortedLists.mergeTwoLists(l1,l2);
        System.out.println(result);
    }
}
