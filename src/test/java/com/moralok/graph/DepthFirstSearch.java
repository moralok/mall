package com.moralok.graph;

/**
 * 深度优先搜索
 *
 * @author moralok
 * @since 2020/9/16
 */
public class DepthFirstSearch {

    private boolean[] marked;

    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
