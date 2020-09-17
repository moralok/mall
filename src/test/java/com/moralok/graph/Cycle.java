package com.moralok.graph;

/**
 * 深度优先搜索检查是否为无环图
 *
 * @author moralok
 * @since 2020/9/17 10:36 上午
 */
public class Cycle {

    private boolean[] marked;

    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                // 注意未触及的才需要搜索
                dfs(G, i, i);
            }
        }
    }

    private void dfs(Graph G, int v, int s) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != s) {
                // 注意判断是否回到来源顶点
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
