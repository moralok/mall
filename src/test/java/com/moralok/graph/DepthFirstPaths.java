package com.moralok.graph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 深度优先搜索寻找路径
 *
 * @author moralok
 * @since 2020/9/16
 */
public class DepthFirstPaths {

    private boolean[] marked;

    private int count;

    /**
     * 起点
     */
    private final int s;

    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        this.s = s;
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            // 防御没有触及的顶点
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            // 注意循环的条件
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
