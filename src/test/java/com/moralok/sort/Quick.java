package com.moralok.sort;

/**
 * @author moralok
 * @since 2020/9/15 4:25 下午
 */
public class Quick extends SortTemplate {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        sort(arr, 0, len - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        Comparable v = arr[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (less(arr[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, arr[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
