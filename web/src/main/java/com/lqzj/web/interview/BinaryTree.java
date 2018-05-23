package com.lqzj.web.interview;

/**
 * @author liuqian1
 */
public class BinaryTree {

    public BinaryTree left;

    public BinaryTree right;

    public String data;

    public BinaryTree() {
    }

    public BinaryTree(BinaryTree left, BinaryTree right, String data) {
        this.left = left;
        this.right = left;
        this.data = data;
    }

    public BinaryTree(String data) {
        this(null, null, data);
    }

    public void insertLeft(BinaryTree node, String value) {
        if (node != null) {
            if (node.left != null) {
                BinaryTree newNode = new BinaryTree(value);
                newNode.left = node.left;
                node.left = newNode;
            } else {
                node.left = new BinaryTree(value);
            }
        } else {
            BinaryTree newNode = new BinaryTree(value);
        }
    }

    public void insertRight(BinaryTree node, String value) {
        if (node != null) {
            if (node.right != null) {
                BinaryTree newNode = new BinaryTree(value);
                newNode.right = node.left;
                node.left = newNode;
            } else {
                node.right = new BinaryTree(value);
            }
        } else {
            BinaryTree newNode = new BinaryTree(value);
        }
    }

    public void preOrder(BinaryTree node) {
        if (node != null) {
            System.out.println(node.data);

            if (node.left != null) {
                node.preOrder(node.left);
            }

            if (node.right != null) {
                node.preOrder(node.right);
            }
        }
    }

    public void postOrder(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                node.postOrder(node.left);
            }

            System.out.println(node.data);

            if (node.right != null) {
                node.postOrder(node.right);
            }
        }
    }

    public void afterOrder(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                node.afterOrder(node.left);
            }

            if (node.right != null) {
                node.afterOrder(node.right);
            }

            System.out.println(node.data);
        }
    }

    public void insertNode(BinaryTree node, int num) {
        if (node != null) {
            Integer data = Integer.valueOf(node.data);
            if (num < data) {
                if (node.left != null) {
                    insertNode(node.left, num);
                } else {
                    node.left = new BinaryTree(String.valueOf(num));
                }
            } else {
                if (node.right != null) {
                    insertNode(node.right, num);
                } else {
                    node.right = new BinaryTree(String.valueOf(num));
                }
            }
        } else {
            BinaryTree newNode = new BinaryTree(String.valueOf(num));
        }
    }

    public boolean findNode(BinaryTree node, int num) {
        if (node != null) {
            Integer data = Integer.valueOf(node.data);
            if (num < data) {
                return findNode(node.left, num);
            } else if (num > data) {
                return findNode(node.right, num);
            }

            return num == data;
        }

        return false;

    }

    public boolean removeNode(BinaryTree node, Integer value, BinaryTree parent) {
        if (node != null) {
            if (value < Integer.valueOf(node.data)) {
                return removeNode(node.left, value, node);
            } else if (value > Integer.valueOf(node.data)) {
                return removeNode(node.right, value, node);
            } else {
                if (node.left == null && node.right == null) {
                    if (parent.left == node) {
                        parent.left = null;
                    } else if (parent.right == node) {
                        parent.right = null;
                    }
                } else if (node.left != null && node.right == null) {
                    parent.left = node.left;
                } else if (node.left == null) {
                    parent.right = node.right;
                } else {
                    Integer minValue = findMinValue(node.right);
                    BinaryTree newNode = new BinaryTree(String.valueOf(minValue));
                    newNode.left = node.left;
                    newNode.right = node.right;
                    if (parent.left == node) {
                        parent.left = newNode;
                    } else if (parent.right == node) {
                        parent.right = newNode;
                    }

                    removeNode(node.right, minValue, node);
                }
                clearNode(node);
                return true;
            }
        }

        return false;
    }

    public void clearNode(BinaryTree node) {
        node.left = null;
        node.right = null;
        node.data = null;
    }

    public Integer findMinValue(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                return findMinValue(node.left);
            } else {
                return Integer.valueOf(node.data);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BinaryTree tree1 = new BinaryTree("1");
        tree1.insertLeft(tree1, "2");
        tree1.insertRight(tree1, "5");

        BinaryTree tree2 = tree1.left;
        BinaryTree tree5 = tree1.right;

        tree2.insertLeft(tree2, "3");
        tree2.insertRight(tree2, "4");

        tree5.insertLeft(tree5, "6");
        tree5.insertRight(tree5, "7");

        tree1.preOrder(tree1);
        System.out.println("================");
        tree1.postOrder(tree1);
        System.out.println("================");
        tree1.afterOrder(tree1);

        System.out.println("################");
        BinaryTree tree = new BinaryTree("50");
        tree.insertNode(tree, 76);
        tree.insertNode(tree, 21);
        tree.insertNode(tree, 4);
        tree.insertNode(tree, 32);
        tree.insertNode(tree, 100);
        tree.insertNode(tree, 64);
        tree.insertNode(tree, 52);

        tree.preOrder(tree);
        System.out.println("################");
        tree.postOrder(tree);
        System.out.println("################");
        tree.afterOrder(tree);

        System.out.println("################");
        System.out.println(tree.findNode(tree, 102));
        System.out.println(tree.findNode(tree, 100));
        System.out.println(tree.findNode(tree, 52));

        System.out.println("################");
        System.out.println(tree.findMinValue(tree));

        System.out.println("################");
        System.out.println(tree.removeNode(tree, 100, null));
        tree.preOrder(tree);
    }
}
