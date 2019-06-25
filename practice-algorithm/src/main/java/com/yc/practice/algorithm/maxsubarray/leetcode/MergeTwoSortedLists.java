package com.yc.practice.algorithm.maxsubarray.leetcode;

public class MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
     * <p>
     * Example:
     * <p>
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */

    //我的答案就是最佳答案
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = null;
        if (l1 == null) {
            if (l2 == null) {
                return null;
            } else {
                return l2;
            }
        } else {
            if (l2 == null) {
                return l1;
            } else {
                boolean useL1 = false;
                if (l1.val > l2.val) {
                    root = new ListNode(l2.val);
                } else {
                    root = new ListNode(l1.val);
                    useL1 = true;
                }
                addNode(root,l1,l2,useL1);
            }
        }
        return root;
    }

    private void addNode(ListNode root, ListNode l1, ListNode l2, boolean useL1) {
        ListNode tmp = null;
        if (useL1) {
            tmp = l1.next;
            if(tmp == null){
                root.next = l2;
                return;
            }
            if (tmp.val > l2.val) {
                root.next = new ListNode(l2.val);
                addNode(root.next,tmp,l2,false);
            }else{
                root.next = new ListNode(tmp.val);
                addNode(root.next,tmp,l2,true);
            }
        } else {
            tmp = l2.next;
            if(tmp == null){
                root.next = l1;
                return;
            }
            if (tmp.val > l1.val) {
                root.next = new ListNode(l1.val);
                addNode(root.next,l1,tmp,true);
            }else{
                root.next = new ListNode(tmp.val);
                addNode(root.next,l1,tmp,false);
            }
        }

    }
}
