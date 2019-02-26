package com.yc.practice.algorithm.sort;

public class Sort {

    //插入排序升序排，需要改成降序时src[j] > key写成src[j] < key就行
    public static void insertSort(int src[]) {
        for (int i = 1; i < src.length; i++) {
            int key = src[i];
            int j = i - 1;
            while (j >= 0 && src[j] > key) {
                src[j + 1] = src[j];
                j = j - 1;
            }
            src[j + 1] = key;
        }

        for (int i = 0; i < src.length; i++) {
            System.out.print("\t" + src[i]);
        }
    }

}
