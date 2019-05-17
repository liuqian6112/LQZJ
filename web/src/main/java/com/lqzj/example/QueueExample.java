package com.lqzj.example;

/**
 * @author qianliu86
 */
public class QueueExample {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.enqueue("aba");
        arrayQueue.enqueue("cdc");
        arrayQueue.enqueue("ded");
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        arrayQueue.enqueue("trt");
        System.out.println(arrayQueue.head);
        System.out.println(arrayQueue.tail);

        System.out.println("================");
        LinkQueue linkQueue = new LinkQueue();
        linkQueue.enqueue("bbg");
        linkQueue.enqueue("ggd");
        System.out.println(linkQueue.dequeue());
        System.out.println(linkQueue.dequeue());
        System.out.println(linkQueue.dequeue());
        System.out.println(linkQueue.head);
        System.out.println(linkQueue.tail);

        System.out.println("================");
        ArrayQueue queue = new ArrayQueue(4);
        System.out.println(queue.enqueueB("ad"));
        System.out.println(queue.enqueueB("ac"));
        System.out.println(queue.enqueueB("ae"));
        System.out.println(queue.enqueueB("ae"));
        System.out.println(queue.dequeueB());
        System.out.println(queue.enqueueB("ab"));
        System.out.println(queue.head);
        System.out.println(queue.tail);
        System.out.println(queue.enqueueB("b"));
    }

    static class ArrayQueue {

        private String[] items;

        private int head;

        private int tail;

        private int n;

        public ArrayQueue(int capacity) {
            items = new String[capacity];
            this.n = capacity;
        }

        public boolean enqueue(String item) {
            if (tail == n) {
                if (head == 0) {
                    return false;
                }

                for (int i = head; i < n; i++) {
                    items[i - head] = items[i];
                }

                tail -= head;
                head = 0;
            }
            items[tail++] = item;

            return true;
        }

        // 循环队列
        public boolean enqueueB(String item) {
            // 队列已满
            if ((tail + 1) % n == head) {
                return false;
            }

//            if (tail == n - 1) {
//                tail = 0;
//            }
//
//            items[tail++] = item;

            items[tail] = item;
            tail = (tail + 1) % n;

            return true;
        }

        public String dequeueB() {
            if (head == tail) {
                return null;
            }

            String item = items[head];

            head = (head + 1) % n;

            return item;
        }

        public String dequeue() {
            if (head == tail) {
                return null;
            }

            return items[head++];
        }
    }

    static class LinkQueue {

        private StackExample.ListNode nodes;

        private int head;

        private int tail;

        public LinkQueue() {

        }

        public boolean enqueue(String item) {
            if (null == nodes) {
                nodes = new StackExample.ListNode(item);
            } else {
                StackExample.ListNode node = new StackExample.ListNode(item);

                StackExample.ListNode cur = nodes;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
            }

            tail++;
            return true;
        }

        public String dequeue() {
            if (head == tail) {
                return null;
            }
            String val = nodes.val;
            nodes = nodes.next;
            head++;

            return val;
        }
    }
}
