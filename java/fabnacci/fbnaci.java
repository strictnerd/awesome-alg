package com.github.distribute.fb;

public class fbnaci {
    public static void main(String[] args) {
        int i = fb1(3);
        int j = fb2(3);
        int k = fb0(3);
        System.out.println(i + "====" + j+"===" +k);
    }

    public static int fb0(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return fb0(n-1) + fb0(n-2);
    }

    public static int fb1(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;

        int [] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        } // arr[]
        return arr[n-1];
    }
    /**
     * 通过这种解法，复杂度从O（n）降低到O（1）
     * @param n
     * @return
     */
    public static int fb2(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;

        int ret = 0;
        int pre = 1;
        int prepare = 2;
        for (int i = 2; i < n; i++) {
            ret = pre + prepare;
            pre = prepare;
            prepare = ret;
        }
        return ret;
    }
}
