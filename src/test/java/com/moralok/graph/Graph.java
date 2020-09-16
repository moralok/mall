package com.moralok.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 邻接表法
 *
 * @author moralok
 * @since 2020/9/16
 */
public class Graph {

    /**
     * 顶点数
     */
    private final int V;

    /**
     * 边数
     */
    private int E;

    /**
     * 邻接表
     */
    private Set<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.V = V;
        adj = (Set<Integer>[]) new Set[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public Graph(int V, int E, int[] data) {
        this(V);
        for (int i = 0; i < E; i++) {
            int v = data[2 * i];
            int w = data[2 * i + 1];
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}