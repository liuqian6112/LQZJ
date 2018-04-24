package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class TrigonTest {

    public static void main(String[] args) {
        TrigonTest test = new TrigonTest();
        test.compute(5);
        test.compute(6);
        test.compute(7);
    }

    public void compute(int length) {
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(lineNumber(length, i, j, 0) + "\t");
            }
            System.out.println();
        }
    }

    private int lineNumber(int length, int x, int y, int gap) {
        // 画竖线
        if (y == 1) {
            return x + gap;
        }

        // 画横线
        if (x == length) {
            return x + y - 1 + gap;
        }

        // 画斜线，要找到左下角的值
        if (x == y) {
            return lineNumber(length, x + 1, y + 1, gap) + 1;
        }

        // 画一个新的三角形，新的gap为上层的gap + 新的三角gap
        if (length - 3 > 0) {
            return lineNumber(length - 3, x - 2, y - 1, 3 * (length - 1) + gap);
        }

        return 0;
    }
}
