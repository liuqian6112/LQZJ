package com.lqzj.example;

/**
 * @author qianliu86
 */
public class HeapExample {

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        for (int i = 1; i < 7; i++) {
            heap.insert(i);
        }

        for (int i = 0; i < heap.items.length; i++) {
            System.out.println("索引: " + i + " => 数据：" + heap.items[i]);
        }

        heap.removeMax();
        for (int i = 0; i < heap.items.length; i++) {
            System.out.println("索引: " + i + " => 数据：" + heap.items[i]);
        }

        Heap heap1 = new Heap(10);
        int[] arr = {0, 5, 9, 3, 6, 7, 1};
        heap1.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static class Heap {
        // 数组，从下标为1开始存储数据
        private int[] items;

        // 堆中可以存储的最大个数
        private int n;

        // 堆中已经存储的数据个数
        private int count;

        public Heap(int capacity) {
            items = new int[capacity + 1];
            n = capacity;
            count = 0;
        }

        public void insert(int value) {
            if (count >= n) {
                // 堆满了
                return;
            }
            count++;
            items[count] = value;

            // 自下往上堆化
            int i = count;
            while (i / 2 > 0 && items[i] > items[i / 2]) {
                // 交换位置i和i/2的数据
                int tmp = items[i];
                items[i] = items[i / 2];
                items[i / 2] = tmp;

                i /= 2;
            }
        }

        public void removeMax() {
            if (count == 0) {
                return;
            }
            items[1] = items[count];
            count--;
            heapify(items, count, 1);
        }

        // 堆化
        private void heapify(int[] items, int n, int i) {
            while (true) {
                int maxPos = i;
                if (i * 2 <= n && items[i * 2] > items[i]) {
                    maxPos = i * 2;
                }
                if (i * 2 + 1 <= n && items[i * 2 + 1] > items[maxPos]) {
                    maxPos = i * 2 + 1;
                }
                if (maxPos == i) {
                    break;
                }
                // swap
                int tmp = items[i];
                items[i] = items[maxPos];
                items[maxPos] = tmp;

                i = maxPos;
            }
        }

        private void buildHeap(int[] arr) {
            for (int i = (arr.length - 1) / 2; i >= 1; i--) {
                heapify(arr, arr.length - 1, i);
            }
        }

        public void sort(int[] arr) {
            buildHeap(arr);
            int k = arr.length - 1;
            while (k > 1) {
                int tmp = arr[1];
                arr[1] = arr[k];
                arr[k--] = tmp;
                heapify(arr, k, 1);
            }
        }
    }
}
