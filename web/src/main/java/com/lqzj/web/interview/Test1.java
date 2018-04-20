package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class Test1 {

    public static void main(String[] args) {
        //1-n阶乘之和
        System.out.println(new Test1().factorial(4));
        //获取二维数组每列最小的值
        for (int i : new Test1().minArray()) {
            System.out.print(i + "  ");
        }
        System.out.println();
        //求"1！+4！(2的平方)+9！(3的平方)的值
        System.out.println(new Test1().calculate(2));
        //数组对角线元素之和[只要行和列相等，即是对角线的元素]
        System.out.println(new Test1().arraySum());
        //打印杨辉三角形
        new Test1().pascalTriangle();
        //猴子摘下了n个桃子，当天吃掉一半多一个，第二天也是吃掉剩下桃子的一半多一个，到了第十天，桃子只剩下了1个。问：猴子第一天摘了多少个桃子
        new Test1().monkeyQue();
        System.out.println(new Test1().monkeyQueAnother(10));
        //计算单词的个数
        new Test1().countWord();
        //判断字母是否完全一样
        new Test1().isAnagram();
        //判断一个数是不是2的某次方
        new Test1().isPowerOfTwo();
        //判断一个数字是不是ugly number
        new Test1().isUgly(25);

    }

    private int factorial(int n) {
        int sum = 0;
        int multi = 1;
        for (int i = 1; i <= n; i++) {
            multi = multi * i;
            sum += multi;
        }
        return sum;
    }

    private int[] minArray() {
        int[][] array = {
                {4, 9, 2, 6, 10},
                {3, 6, 5, 8, 4}
        };
        int[] minArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int min = array[i][0];
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
            minArray[i] = min;
        }

        return minArray;
    }

    private int calculate(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            int factorial = 1;
            for (int j = 1; j <= square; j++) {
                factorial = factorial * j;
            }
            sum += factorial;
        }

        return sum;
    }

    private int arraySum() {
        int[][] array = {
                {23, 106, 8, 234},
                {25, 9, 73, 19},
                {56, 25, 67, 137},
                {33, 22, 11, 44},
        };

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    sum += array[i][j];
                }
            }
        }

        return sum;
    }

    private void pascalTriangle() {
        int[][] array = new int[10][];
        for (int i = 0; i < array.length; i++) {
            array[i] = new int[i + 1];
            for (int j = 0; j < array[i].length; j++) {
                if (i == 0 || j == 0 || i == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
                }
            }
        }
        for (int[] out : array) {
            for (int inner : out) {
                System.out.print(inner + " ");
            }
            System.out.println();
        }
    }

    private void monkeyQue() {
        // 循环
        int sum = 1;
        for (int i = 0; i < 9; i++) {
            sum = (sum + 1) * 2;
        }
        System.out.println(sum);
    }

    private int monkeyQueAnother(int n) {
        //递归
        //假设当天有n个桃子，它是前一天桃子的一半少1个，f(n - 1) = f(n)/2 - 1,
        //我们就可以推出当天桃子的个数：根据递推公式：f(n) = 2 * f(n - 1) + 2
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return 2 * monkeyQueAnother(n - 1) + 2;
        }
    }

    private void countWord() {
        String word = "hello world my mom i love you";
        int num = 1;
        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).equals(" ")) {
                num++;
            }
        }
        System.out.println(num);
    }

    private void isAnagram() {
        //分别存储字符串的字符
        char[] array1 = new char[26];
        char[] array2 = new char[26];


        String s1 = "pleasefollowthewechatpublicnumber";
        String s2 = "pleowcnumberthewechatpubliasefoll";

        for (int i = 0; i < s1.length(); i++) {
            int index = s1.charAt(i) - 'a';
            array1[index]++;
        }

        for (int j = 0; j < s2.length(); j++) {
            int index = s2.charAt(j) - 'a';
            array2[index]++;
        }

        for (int i = 0; i < 26; i++) {
            if (array1[i] != array2[i]) {
                System.out.println("不相同");
            }
        }
        System.out.println("相同");
    }

    private void isPowerOfTwo() {
        int num = 256;

        while (num % 2 == 0) {
            num = num / 2;
            if (num == 0) {
                break;
            }
        }

        if (num == 1) {
            System.out.println("是");
        } else {
            System.out.println("不是");
        }
    }

    private void isUgly(int num) {
        if (num < 0) {
            System.out.println("不是");
        } else {
            while (num % 2 == 0) {
                num = num / 2;
            }
            while (num % 3 == 0) {
                num = num / 3;
            }
            while (num % 5 == 0) {
                num = num / 5;
            }
            if (num == 1) {
                System.out.println("是");
            } else {
                System.out.println("不是");
            }
        }
    }

}
