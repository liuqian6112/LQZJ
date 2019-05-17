package com.lqzj.example;

import java.util.Stack;

/**
 * @author qianliu86
 */
public class StackExample {

    private Stack<String> stackA;

    private Stack<String> stackB;

    public StackExample() {
        this.stackA = new Stack<>();
        this.stackB = new Stack<>();
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(6);
        arrayStack.push("a");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());

        LinkStack linkStack = new LinkStack();
        linkStack.push("a");
        linkStack.push("b");
        System.out.println(linkStack.pop());
        System.out.println(linkStack.pop());
        linkStack.push("b");
        System.out.println(linkStack.pop());

//        System.out.println(new StackExample().calculate("5+3-4-(1+2-7+(10-1+3+5+(3-0+(8-(3+(8-(10-(6-10-8-7+(0+0+7)-10+5-3-2+(9+0+(7+(2-(2-(9)-2+5+4+2+(2+9+1+5+5-8-9-2-9+1+0)-(5-(9)-(0-(7+9)+(10+(6-4+6))+0-2+(10+7+(8+(7-(8-(3)+(2)+(10-6+10-(2)-7-(2)+(3+(8))+(1-3-8)+6-(4+1)+(6))+6-(1)-(10+(4)+(8)+(5+(0))+(3-(6))-(9)-(4)+(2))))))-1)))+(9+6)+(0))))+3-(1))+(7))))))))"));
    }

    public int calculate(String s) {
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if (c != ' ' && c != '(' && c != ')') {
                if (c == '+' || c == '-') {
                    if (stackB.isEmpty()) {
                        stackB.push(c.toString());
                    } else {
                        while (stackA.size() > 2) {
                            String a = stackA.pop();
                            String b = stackA.pop();
                            stackA.push(b + a);
                        }
                        String a = stackA.pop();
                        String b = stackA.pop();
                        String f = stackB.pop();
                        Integer r = 0;
                        if (f.equals("+")) {
                            r = Integer.valueOf(b) + Integer.valueOf(a);
                        } else {
                            r = Integer.valueOf(b) - Integer.valueOf(a);
                        }

                        stackA.push(r.toString());

                        stackB.push(c.toString());
                    }
                } else {
                    if (stackB.isEmpty() && !stackA.isEmpty()) {
                        String a = stackA.pop();
                        String r = a + c.toString();
                        stackA.push(r);
                    } else {
                        stackA.push(c.toString());
                    }
                }

            }


        }

        if (stackB.isEmpty()) {
            return Integer.valueOf(stackA.pop());
        }

        while (stackA.size() > 2) {
            String a = stackA.pop();
            String b = stackA.pop();
            stackA.push(b + a);
        }
        String a = stackA.pop();
        String b = stackA.pop();
        String f = stackB.pop();
        Integer r = 0;
        if (f.equals("+")) {

            r = Integer.valueOf(a) + Integer.valueOf(b);
        } else {
            r = Integer.valueOf(b) - Integer.valueOf(a);
        }

        return r;
    }

    static class ArrayStack {

        private String[] items;

        private int count;

        private int n;

        ArrayStack(int n) {
            items = new String[n];
            this.n = n;
            this.count = 0;
        }

        public boolean push(String item) {
            if (count == n) {
                return false;
            }
            items[count] = item;
            count++;
            return true;
        }

        public String pop() {
            if (count == 0) {
                return null;
            }

            String tmp = items[count - 1];
            count--;
            return tmp;
        }
    }

    static class LinkStack {

        private ListNode header;

        public void push(String item) {
            ListNode node = new ListNode(item);
            if (null == header) {
                header = node;
            } else {
                node.next = header;
                header = node;
            }
        }

        public String pop() {
            if (null == header) {
                return null;
            }

            String val = header.val;
            header = header.next;
            return val;
        }
    }

    static class ListNode {
        String val;

        ListNode next;

        ListNode(String val) {
            this.val = val;
        }
    }
}
