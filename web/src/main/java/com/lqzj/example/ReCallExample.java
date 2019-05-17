package com.lqzj.example;

/**
 * @author qianliu86
 */
public class ReCallExample {
    //下标表示行，值表示queen存储在哪一列
    int[] result = new int[8];

    public static void main(String[] args) {
        ReCallExample example = new ReCallExample();
        example.cal8queens(0);

        example.f(0, 0);
        System.out.println(example.maxW);
    }

    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    public void f(int i, int cw) { // 调用 f(0, 0)
        if (cw == w || i == n) { // cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 选择不装第 i 个物品
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); // 选择装第 i 个物品
        }
    }

    //weight: 物品重量，n: 物品个数，w: 背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w+1]; // 默认值 false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
                if (states[i-1][j] == true) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) {// 把第 i 个物品放入背包
                if (states[i-1][j]==true) states[i][j+weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n-1][i] == true) return i;
        }
        return 0;
    }

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {// 把第 i 个物品放入背包
                if (states[j]==true) states[j+items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }



    // 调用方式：cal8queens(0)
    public void cal8queens(int row) {
        if (row == 8) {
            printQueens(result);
            return;
        }

        // 每一行有8种放法
        for (int column = 0; column < 8; column++) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第row行棋子放在column列
                result[row] = column;
                cal8queens(row + 1);
            }
        }
    }

    // 判断row行column列放置是否合适
    private boolean isOk(int row, int column) {
        int leftup = column - 1;
        int rightup = column + 1;

        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; i--) {
            // 第i行column列有棋子吗?
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列是否有棋子
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列是否有棋子
            if (rightup >= 0) {
                if (result[i] == rightup) {
                    return false;
                }
            }

            leftup--;
            rightup++;
        }

        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column) {
                    System.out.println("Q ");
                } else {
                    System.out.println("* ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }


}
