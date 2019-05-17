package com.lqzj.example;

import javafx.scene.input.Mnemonic;
import sun.awt.image.GifImageDecoder;

import java.math.BigDecimal;

/**
 * @author qianliu86
 */
public class SortExample {

    public static void main(String[] args) {
        int[] arr = {5, 6, 1, 4, 2, 3};
        bubbleSort(arr);
        System.out.println("=====");
        int[] arr1 = {5, 6, 1, 4, 2, 3};
        insertSort(arr1);
        System.out.println("=====");
        int[] arr11 = {5, 6, 1, 4, 2, 3};
        insertSort(arr11);
        System.out.println("=====");
        int[] arr2 = {5, 6, 1, 4, 2, 3};
        selectSort(arr2);
        System.out.println("=====");
        int[] arr3 = {5, 6, 1, 4, 2, 3};
        mergeSort(arr3);
        System.out.println("=====");
        int[] arr4 = {5, 6, 1, 4, 2, 3};
        quickSort(arr4);
        kthSmall(arr4, 4);
        System.out.println("=====");
        int[] arr5 = {5, 6, 1, 4, 2, 3};
        countingSort(arr5);

        double a = 1.21423269495833001245906684528929311842E04;
        System.out.println(new BigDecimal(a));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > v) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }

            arr[j + 1] = v;
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        int tmp;
        int gap = len / 2;

        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                tmp = arr[i];
                int pre = i - gap;
                while (pre >= 0 && arr[pre] > tmp) {
                    arr[pre + gap] = arr[pre];
                    pre -= gap;
                }
                arr[pre + gap] = tmp;
            }

            gap /= 2;
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }

            if (min > i) {
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSortExt(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    private static void mergeSortExt(int[] arr, int p, int r) {
        // 递归终止条件
        if (p >= r) {
            return;
        }

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p) / 2;
        // 分治递归
        mergeSortExt(arr, p, q);
        mergeSortExt(arr, q + 1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(arr, p, q, r);
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int k = 0;
        int[] tmp = new int[r - p + 1];

        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;

        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = arr[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r - p; i++) {
            arr[p + i] = tmp[i];
        }
    }

    public static void quickSort(int[] arr) {
        quickSortExt(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

    private static void quickSortExt(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(arr, p, r);
        quickSortExt(arr, p, q - 1);
        quickSortExt(arr, q + 1, r);
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;

        for (int j = p; j <= r; j++) {
            if (arr[j] < pivot) {
                if (i == j) {
                    ++i;
                } else {
                    int tmp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        int tmp = arr[r];
        arr[r] = arr[i];
        arr[i] = tmp;

        return i;
    }

    public static void kthSmall(int[] arr, int n) {

        int p = partition(arr, 0, arr.length - 1);

        while (p + 1 != n) {
            if (p + 1 > n) {
                p = partition(arr, 0, p - 1);
            } else {
                p = partition(arr, p + 1, arr.length - 1);
            }
        }

        System.out.println(arr[p]);
    }

    public static void countingSort(int[] arr) {
        // 查找数组中的数据范围
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];
        for (int i = 0; i < max + 1; i++) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }

        // 依次累加
        for (int i = 1; i < max + 1; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数据r，存储排序后的结果
        int[] r = new int[arr.length];
        // 计算排序
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = c[arr[i]] - 1;
            r[index] = arr[i];
            c[arr[i]]--;
        }

        // 将结果拷贝到arr数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }

        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }

}
