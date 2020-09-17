package com.moralok.graph;

/**
 * 连通分量
 *
 * @author moralok
 * @since 2020/9/17 10:05 上午
 */
public class ConnectedComponent {

    private boolean[] marked;

    /**
     * 顶点所属连通分量ID
     */
    private int[] id;

    /**
     * 连通分量数
     */
    private int count;

    public ConnectedComponent(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
