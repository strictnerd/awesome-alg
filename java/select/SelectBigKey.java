package com.github.distribute.select;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Body1_1Impl;

/**
 * O(n)时间复杂度内，找到第K大的元素
 */
public class SelectBigKey {

    public static void main(String[] args) {
        int[] arr = {1,5,3,2,6};
        //int[] arr = {4,2,5,12,3};
        int k = sort(arr, 0, arr.length-1, 3);
        System.out.println(k);
    }

    public static int sort(int[] arr, int l, int r, int k) {
        if (l > r) {
            return 0;
        }
        int p = partition(arr, l, r);
        if ((p + 1) == k) {
            return arr[p];
        }else if ((p + 1) < k) {
            return sort(arr, p+1, r, k);
        }else {
            return sort(arr, l, p-1, k);
        }
    }

    /**
     * 利用快排以i为中心分割元素
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right){
        int partition = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= partition) {
                swab(arr, i, j);
                i++;
            }
        }
        swab(arr, i, right);
        return i;
    }

    private static void swab(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] =tmp;
    }
}
