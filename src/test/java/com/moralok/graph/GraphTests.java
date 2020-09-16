package com.moralok.graph;

import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/16
 */
public class GraphTests {

    @Test
    public void testCreateGraph() {
        int V = 4;
        int E = 3;
        int[] data = new int[] {0, 1, 2, 3, 1, 3};
        Graph graph = new Graph(V, E, data);
        System.out.println("顶点的数量 " + graph.V());
        System.out.println("边的数量 " + graph.E());
        for (int i = 0; i < V; i++) {
            System.out.println("顶点 " + i + " 的邻接表 " + graph.adj(i));
        }
    }
}
