package com.moralok.graph;

/**
 * 深度优先搜索检查是否为无环图
 *
 * @author moralok
 * @since 2020/9/17 10:36 上午
 */
public class TwoColor {

    private boolean[] marked;

    private boolean[] color;

    private boolean twoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                // 注意未触及的才需要搜索
                dfs(G, i);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                twoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return twoColorable;
    }
}
