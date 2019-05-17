package com.lqzj.example;

import java.util.Stack;

/**
 * @author qianliu86
 */
public class LinkExample {

    public static void main(String[] args) {
        ListNode res = new ListNode("a");
        ListNode res1 = new ListNode("b");
        ListNode res2 = new ListNode("c");
        ListNode res3 = new ListNode("b");
        ListNode res4 = new ListNode("a");
        res3.next = res4;
        res2.next = res3;
        res1.next = res2;
        res.next = res1;

        System.out.println(isPalindrome(res));

        String a = " 8 s";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < a.length(); i++) {
            Character c = a.charAt(i);

            if (c != ' ' && c != 's') {
                stack.push(c);
            }
        }


        System.out.println(Integer.valueOf(stack.pop().toString()));

        Integer i = 8;


    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }

    static class ListNode {
        String val;

        ListNode next;

        ListNode(String val) {
            this.val = val;
        }
    }
}
