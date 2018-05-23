package com.lqzj.web.interview;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author liuqian1
 */
public class SumTest {
    public static void main(String[] args) {
        ArrayList<Integer> nums = Lists.newArrayList(-25, 44, -54, 82, -92, 12, 43, -34, -54, 88, 34, -77);

        int max = 0;
        int temp = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (j == i + 1) {
                    temp = nums.get(i);
                }
                if (temp + nums.get(j) > max) {
                    max = temp + nums.get(j);
                    start = i + 1;
                    end = j + 1;
                }
                temp += nums.get(j);
            }
        }

        System.out.println("max: " + max + ", start: " + start + ", end: " + end);
    }

}
