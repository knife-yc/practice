package com.yc.practice.algorithm.maxsubarray.leetcode;
/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        int tmpL1 = l1.val;
        int tmpL2 = l2.val;
        int sum = tmpL1 + tmpL2;
        int jinwei = 0;
        ListNode result = null;
        if (sum >= 10) {
            jinwei = 1;
            result = new ListNode(sum - 10);
        } else {
            result = new ListNode(sum);
        }
        if (l1.next == null || l2.next == null) {
            if (sum >= 10) {
                ListNode next = new ListNode(1);
                result.next = next;
                return result;
            } else {
                return result;
            }
        }

        getListNode(result, l1.next, l2.next, jinwei);
        return result;

    }

    private void getListNode(ListNode root, ListNode l1, ListNode l2, int jinwei) {
        if (l1 == null & l2 == null) {
            if(jinwei > 0){
                ListNode next = new ListNode(jinwei);
                root.next = next;
            }
            return;
        } else if (l1 == null && l2 != null) {
            int sum = l2.val+jinwei;
            ListNode next = null;
            if(sum >= 10){
                jinwei = 1;
                next = new ListNode(sum - 10);
            }else{
                jinwei = 0;
                next = new ListNode(sum);
            }
            root.next = next;
            getListNode(next, null, l2.next, jinwei);
        } else if (l1 != null && l2 == null) {
            int sum = l1.val+jinwei;
            ListNode next = null;

            root.next = next;
            getListNode(next, l1.next, null, jinwei);
        } else {
            int sum = l1.val + l2.val + jinwei;
            ListNode next = null;
            int jin = 0;
            if (sum >= 10) {
                jin = 1;
                next = new ListNode(sum - 10);
            } else {
                next = new ListNode(sum);
            }

            root.next = next;
            getListNode(next, l1.next, l2.next, jin);
        }
    }

}
