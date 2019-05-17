package com.lqzj.example;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qianliu86
 */
public class GraphExample {

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(3, 5);
        graph.addEdge(1, 4);

        graph.bfs(1, 5);
    }

    // 无向图
    static class Graph {

        // 顶点的个数
        private int v;

        // 邻接表
        private LinkedList<Integer>[] adj;

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // 无向图一条边存两次
        public void addEdge(int s, int t) {
            adj[s].add(t);
            adj[t].add(s);
        }

        /**
         * s到t的最短路径
         *
         * @param s 起始顶点
         * @param t 终止顶点
         */
        public void bfs(int s, int t) {
            if (s == t) {
                return;
            }

            // 记录已经被访问的顶点，用来避免顶点被重复访问
            boolean[] visited = new boolean[v];
            visited[s] = true;
            // 存储已经被访问，但相邻接的顶点还没被访问的顶点
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            // 记录搜索路径，反向存储，prev[w]存储的是，顶点w是从哪个前驱顶点遍历过来的
            int[] prev = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }

            while (queue.size() != 0) {
                int w = queue.poll();
                for (int i = 0; i < adj[w].size(); i++) {
                    int q = adj[w].get(i);
                    if (!visited[q]) {
                        prev[q] = w;
                        if (q == t) {
                            print(prev, s, t);
                            return;
                        }
                        visited[q] = true;
                        queue.add(q);
                    }
                }
            }
        }

        // 递归打印s->t的路径
        private void print(int[] prev, int s, int t) {
            if (prev[t] != -1 && t != s) {
                print(prev, s, prev[t]);
            }
            System.out.println(t + " ");
        }

        boolean found = false;

        public void dfs(int s, int t) {
            found = false;
            boolean[] visited = new boolean[v];
            int[] prev = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }

            print(prev, s, t);
        }

        private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
            if (found) {
                return;
            }

            visited[w] = true;
            if (w == t) {
                found = true;
                return;
            }

            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    recurDfs(q, t, visited, prev);
                }
            }
        }
    }
}
