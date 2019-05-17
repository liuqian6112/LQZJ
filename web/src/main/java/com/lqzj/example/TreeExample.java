package com.lqzj.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qianliu86
 */
public class TreeExample {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        root.leftNode = b;
        root.rightNode = c;
        Node d = new Node(4);
        Node e = new Node(5);
        b.leftNode = d;
        b.rightNode = e;
        Node f = new Node(6);
        Node g = new Node(7);
        c.leftNode = f;
        c.rightNode = g;

        preOrder(root);
        System.out.println("===");
        inOrder(root);
        System.out.println("==");
        postOrder(root);
        System.out.println("==");
        List<List<Integer>> lists = levelOrder(root);
        lists.forEach(l -> l.forEach(System.out::print));


    }

    public static void preOrder(Node node) {
        if (null == node) {
            return;
        }

        System.out.print(node.data + " -> ");
        preOrder(node.leftNode);
        preOrder(node.rightNode);
    }

    public static void inOrder(Node node) {
        if (null == node) {
            return;
        }

        inOrder(node.leftNode);
        System.out.print(node.data + " -> ");
        inOrder(node.rightNode);
    }

    public static void postOrder(Node node) {
        if (null == node) {
            return;
        }

        postOrder(node.leftNode);
        postOrder(node.rightNode);
        System.out.print(node.data + " -> ");
    }

    public static List<List<Integer>> levelOrder(Node node) {
        if (null == node) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        Queue<Node> curLevQueue = new LinkedList<>();

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            curLevQueue.offer(poll);

            if (queue.isEmpty()) {
                List<Integer> list = new ArrayList<>(curLevQueue.size());
                while (!curLevQueue.isEmpty()) {
                    Node curNode = curLevQueue.poll();
                    list.add(curNode.data);

                    if (null != curNode.leftNode) {
                        queue.offer(curNode.leftNode);
                    }

                    if (null != curNode.rightNode) {
                        queue.offer(curNode.rightNode);
                    }
                }

                result.add(list);
            }
        }

        return result;
    }

    private Node tree;

    public Node find(int data) {
        Node p = tree;

        while (null != p) {
            if (data < p.data) {
                p = p.leftNode;
            } else if (data > p.data) {
                p = p.rightNode;
            } else {
                return p;
            }
        }

        return null;
    }

    public void insert(int data) {
        if (null == tree) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (null == p.rightNode) {
                    p.rightNode = new Node(data);
                    return;
                }
                p = p.rightNode;
            } else {
                if (null == p.leftNode) {
                    p.leftNode = new Node(data);
                    return;
                }
                p = p.leftNode;
            }
        }
    }

    public void delete(int data) {
        // p指向要删除节点,初始化指向根节点
        Node p = tree;
        // pp指向p的父节点
        Node pp = null;

        while (null != p && data != p.data) {
            pp = p;
            if (data > p.data) {
                p = p.rightNode;
            } else {
                p = p.leftNode;
            }
        }

        if (p == null) {
            return;
        }

        // 要删除的节点有两个子节点
        if (null != p.leftNode && null != p.rightNode) {
            Node minP = p.rightNode;
            // minPP表示minP的父节点
            Node minPP = p;
            while (null != minP.leftNode) {
                minPP = minP;
                minP = minP.leftNode;
            }
            // 将minP的数据替换到p中
            p.data = minP.data;
            // 变成删除minP
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或仅有一个节点
        // p的子节点
        Node child;
        if (null != p.leftNode) {
            child = p.leftNode;
        } else if (null != p.rightNode) {
            child = p.rightNode;
        } else {
            child = null;
        }

        if (null == pp) {
            // 删除的是根节点
            tree = child;
        } else if (pp.leftNode == p) {
            pp.leftNode = child;
        } else {
            pp.rightNode = child;
        }
    }

    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }

        int leftHeight = maxDepth(root.leftNode);
        int rightHeight = maxDepth(root.rightNode);

        return java.lang.Math.max(leftHeight, rightHeight) + 1;
    }

        static class Node {
        int data;

        Node leftNode;

        Node rightNode;

        public Node(int data) {
            this.data = data;
        }
    }
}
