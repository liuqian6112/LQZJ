package com.lqzj.web.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuqian1
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            System.out.println(root.val);
            list.add(root.val);
            if (root.left != null) {
                preorderTraversal(root.left);
            }

            if (root.right != null) {
                preorderTraversal(root.right);
            }
        }
        return list;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
