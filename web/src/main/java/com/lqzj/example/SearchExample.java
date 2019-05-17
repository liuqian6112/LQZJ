package com.lqzj.example;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author qianliu86
 */
public class SearchExample {

    public static void main(String[] args) throws Exception {
        int[] arr = {3, 5, 8, 9, 9, 9, 14, 19, 24};
        System.out.println(binarySearch(arr, 9));
        System.out.println(binarySearchVersion1(arr, 9));
        System.out.println(binarySearchVersion2(arr, 9));
        System.out.println(binarySearchVersion3(arr, 0));
        System.out.println(binarySearchVersion4(arr, 67));

        String startDate = "2019-05-05";
        String endDate = "2019-05-08";

        Date date = DateUtils.parseDate(startDate, "yyyy-MM-dd");
        Date date1 = DateUtils.parseDate(endDate, "yyyy-MM-dd");

        while (date.compareTo(date1) <= 0) {
            System.out.println(date);
            date = DateUtils.addDays(date, 1);
        }

    }

    public static int binarySearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // 查找第一个值等于给定值的元素
    public static int binarySearchVersion1(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                }
                high = mid - 1;
            }
        }

        return -1;
    }

    // 查找最后一个值等于给定值的元素
    public static int binarySearchVersion2(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                }
                low = mid + 1;
            }
        }

        return -1;
    }

    // 查找第一个大于等于给定值的元素
    public static int binarySearchVersion3(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }

            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    // 查找最后一个小于等于给定值的元素
    public static int binarySearchVersion4(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= value) {
                if (mid == arr.length - 1 || arr[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

}
