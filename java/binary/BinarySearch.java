package com.github.distribute.binary;
/**
 * 最全面的二分查找算法
 * 看似简单的二分查找，完全写对，非常困难
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,23,77,77,77,77,77,89,90,98};
        int index = binarySearchLastLtKey(arr, 77);
        System.out.println(index);
    }

    /**
     * 正常二分查找，找到即返回
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (arr[middle] < key) {
                low = middle + 1;
            }else if (arr[middle] > key) {
                high = middle -1;
            }else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 存在重复元素的情况下，返回第一个该元素的索引值
     * 比如int[] arr = {1,23,45,77,77,77,77,89,90,98}; key=77返回3；
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearchFirstKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (arr[middle] < key) {
                low = middle + 1;
            }else if (arr[middle] > key) {
                high = middle -1;
            }else {
                if(middle == 0 || arr[middle-1]<key) {
                    return middle;
                }else {
                    high = middle-1;
                }
            }
        }
        return -1;
    }

    /**
     * 存在重复元素的情况下，返回最后一个该元素的索引值
     * 比如int[] arr = {1,23,45,77,77,77,77,89,90,98}; key=77返回6；
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearchLastKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (arr[middle] < key) {
                low = middle + 1;
            }else if (arr[middle] > key) {
                high = middle -1;
            }else {
                if(middle == arr.length-1 || arr[middle+1] > key) {
                    return middle;
                }else {
                    low = middle + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 返回一组数据中第一个大于等于该元素的索引值
     * 比如int[] arr = {1,23,45,77,89,90,98}; key=77返回3；
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearchGtKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (arr[middle] >= key) {
                if (middle == 0 || arr[middle-1] < key) {
                    return middle;
                }else {
                    high = middle - 1;
                }

            }else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 返回一组数据中最后一个小于等于该元素的索引值
     * 比如int[] arr = {1,23,45,77,89,90,98}; key=77返回3；
     * @param arr
     * @param key
     * @return
     */
    private static int binarySearchLastLtKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high)/2;
            if (arr[middle] <= key) {
                if (middle == arr.length-1 || arr[middle+1] > key) {
                    return middle;
                }else {
                    low = middle + 1;
                }
            }else {
                high = middle - 1;
            }
        }
        return -1;
    }
}
