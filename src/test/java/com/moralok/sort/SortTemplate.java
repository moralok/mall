package com.moralok.sort;

import java.util.Arrays;

/**
 * @author moralok
 * @since 2020/9/15 10:49 上午
 */
public abstract class SortTemplate {

    public static void sort(Comparable[] arr) {

    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    protected static void show(Comparable[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    protected static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
