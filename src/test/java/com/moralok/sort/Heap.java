package com.moralok.sort;

/**
 * @author moralok
 * @since 2020/9/15 5:26 下午
 */
public class Heap extends SortTemplate {

    public static void sort(Comparable[] arr) {
        // 访问数组时下标-1
        int len = arr.length;
        for (int i = len / 2; i >= 1; i--) {
            sink(arr, i, len);
        }
        while (len > 1) {
            exch(arr, 0, len - 1);
            len--;
            sink(arr, 1, len);
        }
    }

    private static void sink(Comparable[] arr, int i, int len) {
        while (i <= len / 2) {
            int j = 2 * i;
            if (j + 1 <= len && less(arr[j-1], arr[j])) {
                j++;
            }
            if (!less(arr[i - 1], arr[j - 1])) {
                break;
            }
            exch(arr, i - 1, j - 1);
            i = j;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
