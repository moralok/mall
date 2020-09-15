package com.moralok.sort;

/**
 * @author moralok
 * @since 2020/9/15 3:08 下午
 */
public class MergeBu extends SortTemplate {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        Comparable[] aux = new Comparable[len];
        for (int sz = 1; sz < len; sz *= 2) {
            for (int i = 0; i + sz <= len - 1; i += 2 * sz) {
                merge(arr, i, i + sz - 1, Math.min(i + 2 * sz - 1, len - 1), aux);
            }
        }
    }

    private static void merge(Comparable[] arr, int left, int mid, int right, Comparable[] aux) {
        if (!less(arr[mid + 1], arr[mid])) {
            return;
        }
        for (int i = left; i <= right; i++) {
            aux[i] = arr[i];
        }
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > right) {
                arr[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
