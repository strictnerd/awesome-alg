package com.github.distribute.binary;
/**
 * ��ȫ��Ķ��ֲ����㷨
 * ���Ƽ򵥵Ķ��ֲ��ң���ȫд�ԣ��ǳ�����
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,23,77,77,77,77,77,89,90,98};
        int index = binarySearchLastLtKey(arr, 77);
        System.out.println(index);
    }

    /**
     * �������ֲ��ң��ҵ�������
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
     * �����ظ�Ԫ�ص�����£����ص�һ����Ԫ�ص�����ֵ
     * ����int[] arr = {1,23,45,77,77,77,77,89,90,98}; key=77����3��
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
     * �����ظ�Ԫ�ص�����£��������һ����Ԫ�ص�����ֵ
     * ����int[] arr = {1,23,45,77,77,77,77,89,90,98}; key=77����6��
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
     * ����һ�������е�һ�����ڵ��ڸ�Ԫ�ص�����ֵ
     * ����int[] arr = {1,23,45,77,89,90,98}; key=77����3��
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
     * ����һ�����������һ��С�ڵ��ڸ�Ԫ�ص�����ֵ
     * ����int[] arr = {1,23,45,77,89,90,98}; key=77����3��
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
