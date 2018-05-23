package com.lqzj.web.interview;

import java.util.Stack;

/**
 * @author liuqian1
 */
public class Test3 {
    public static void main(String[] args) {

        int len = computeLen(new char[]{'-', '-', '-', '-', 'i', 't', '-', '-', '-', 'i', 's', '-', '-', '-', 'j', 'd', '-', '-'});
        System.out.println(len);
        int i = computeLen1("  it   is   jd  ".toCharArray());
        System.out.println(i);
        System.out.println(longestValidParentheses1("()(())"));
        System.out.println(longestValidParentheses1("()((()))"));
        System.out.println(longestValidParentheses1("()(()"));
        System.out.println(longestValidParentheses1("(()"));
        System.out.println(longestValidParentheses1("(()()"));
        System.out.println("++++++++++++++++++");
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1.0.1", "1"));
        System.out.println(compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(compareVersion("1.0", "1"));
        System.out.println(compareVersion("10.6.5", "10.6"));
        System.out.println("++++++++++++++++++");

    }

    public static int computeLen(char[] arr) {
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                count = count + 2;
                flag = false;
                continue;
            }
            if (arr[i] == '-' && arr[i + 1] == '-') {
                flag = true;
            }
        }

        return arr.length - count;
    }

    public static int computeLen1(char[] arr) {
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            if (' ' != arr[i]) {
                if (length != 0 && ' ' == arr[i - 1]) {
                    length++;
                }
                length++;
            }
        }

        return length;
    }

    public static int longestValidParentheses(String s) {
        int max = 0;
        int length = 0;
        char left = '(';
        char right = ')';
        boolean flag = false;
        boolean sec = false;

        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (sec) {
                length += 2;
                if (length > max) {
                    max = length;
                }
                sec = false;
                continue;
            }

            if (!flag && left == charArray[i] && i < charArray.length - 1 && right == charArray[i + 1]) {
                sec = true;
                flag = true;
                continue;
            }

            if (flag && left == charArray[i] && i < charArray.length - 1 && right == charArray[i + 1]) {
                sec = true;
                continue;
            }

            flag = false;
            length = 0;

        }

        return max;
    }

    public static int longestValidParentheses1(String s) {
        Stack<Character> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        int max = 0;
        int maxStackLength = 0;
        int length = 0;

        for (int i = 0; i < charArray.length; i++) {


            if ('(' == charArray[i]) {
                stack.push(charArray[i]);
                maxStackLength++;
                continue;
            }

            if (')' == charArray[i] && !stack.isEmpty()) {
                stack.pop();
                length += 2;
                if (i == charArray.length - 1 && length > 2) {
                    if (!stack.isEmpty()) {
                        length = length - 2 * maxStackLength;
                    }
                }
                max = length > max ? length : max;
                continue;
            }

            if (!stack.isEmpty()) {
                maxStackLength = 0;
                length = 0;
            }

        }

        return max;
    }

    public static int longestValidParentheses2(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 2; j < chars.length + 1; j += 2) {
                boolean flag = validate(s.substring(i, j));
                max = (flag && (max < j - i)) ? j - i : max;
            }
        }
        return max;
    }

    public static boolean validate(String s) {
        boolean flag = true;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : chars) {
            if ('(' == c) {
                stack.push(c);
            } else if (')' == c && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                flag = false;
                break;
            }
        }

        if (!stack.isEmpty()) {
            flag = false;
        }

        return flag;
    }

    public static void compute(String s) {
        int length = 0;
        char[] chars = s.toCharArray();
        Stack<String> stack = new Stack<>();
        for (char ch : chars) {
            if ('(' == ch) {
                stack.push(String.valueOf(ch));
            } else if (')' == ch) {
                if (!stack.isEmpty()) {
                    String pop = stack.pop();
                    if (pop != null) {
                        length += 2;
                    }
                }
            } else {
                stack.clear();
            }
        }
        System.out.println("length: " + length);
    }

    public static int compareVersion(String version1, String version2) {
        String[] version1Arr = version1.split("\\.");
        String[] version2Arr = version2.split("\\.");
        int minLength = version1Arr.length > version2Arr.length ? version2Arr.length : version1Arr.length;

        int result = 0;
        for (int i = 0; i < minLength; i++) {
            if (Integer.valueOf(version1Arr[i]) > Integer.valueOf(version2Arr[i])) {
                result = 1;
                break;
            } else if (Integer.valueOf(version1Arr[i]) < Integer.valueOf(version2Arr[i])) {
                result = -1;
                break;
            }
        }

        if (result == 0 && version1Arr.length != version2Arr.length) {
            if (version1Arr.length > version2Arr.length) {
                for (int i = minLength; i < version1Arr.length; i++) {
                    if (Integer.valueOf(version1Arr[i]) != 0) {
                        result = 1;
                        break;
                    }
                }
            } else {
                for (int i = minLength; i < version2Arr.length; i++) {
                    if (Integer.valueOf(version2Arr[i]) != 0) {
                        result = -1;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
