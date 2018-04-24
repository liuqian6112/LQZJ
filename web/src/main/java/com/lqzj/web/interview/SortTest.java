package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class SortTest {
    public static void main(String[] args) {
        SortTest test = new SortTest();
        int[] array = {5, 69, 12, 3, 56, 789, 2, 5648, 23};
//        test.bubbleSort(array);
//        test.selectionSort(array);
//        test.insertSort(array);
//        test.quickSort(array, 0, array.length - 1);
        test.shellSort(array);
        for (int m = 0; m <= array.length - 1; m++) {
            System.out.print(array[m] + "\t");
        }
    }

    public void bubbleSort(int[] arrays) {
        int temp;

        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = 1; j < arrays.length; j++) {
                if (arrays[j - 1] > arrays[j]) {
                    temp = arrays[j - 1];
                    arrays[j - 1] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
    }

    public void selectionSort(int[] arrays) {
        int temp;

        for (int i = 0; i < arrays.length - 1; i++) {
            for (int j = i + 1; j < arrays.length; j++) {
                if (arrays[i] > arrays[j]) {
                    temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
    }

    public void insertSort(int[] arrays) {
        int temp, j;
        for (int i = 1; i < arrays.length; i++) {
            temp = arrays[i];
            j = i - 1;
            while (j >= 0 && arrays[j] > temp) {
                arrays[j + 1] = arrays[j];
                j--;
            }
            arrays[j + 1] = temp;
        }
    }

    public void quickSort(int[] arrays, int low, int high) {
        // 递归算法出口
        if (low > high) {
            return;
        }

        // 存
        int i = low;
        int j = high;

        // key
        int key = arrays[low];

        // 完成一趟排序
        while (i < j) {
            // 从右往左找到第一个小于key的数
            while (i < j && arrays[j] > key) {
                j--;
            }

            // 从左往右找到第一个大于key的数
            while (i < j && arrays[i] <= key) {
                i++;
            }

            // 交换
            if (i < j) {
                int temp = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] = temp;
            }
        }

        // 调整key的位置
        int temp = arrays[i];
        arrays[i] = key;
        arrays[low] = temp;

        // 对key左边的数排序
        quickSort(arrays, low, i - 1);
        // 对key右边的数排序
        quickSort(arrays, i + 1, high);
    }

    public void shellSort(int[] arrays) {
        int i, j, gap;
        int temp;

        for (gap = arrays.length / 2; gap > 0; gap /= 2) {
            for (i = gap; i < arrays.length; i++) {
                for (j = i - gap; j >= 0 && arrays[j] > arrays[j + gap]; j -= gap) {
                    temp = arrays[j];
                    arrays[j] = arrays[j + gap];
                    arrays[j + gap] = temp;
                }
            }
        }
    }


}
