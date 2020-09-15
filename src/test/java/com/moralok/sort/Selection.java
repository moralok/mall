package com.moralok.sort;

/**
 * @author moralok
 * @since 2020/9/15 10:38 上午
 */
public class Selection extends SortTemplate {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i; j < len; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            exch(arr, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
