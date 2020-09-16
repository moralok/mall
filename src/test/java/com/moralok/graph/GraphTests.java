package com.moralok.graph;

import org.junit.Before;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/16
 */
public class GraphTests {

    private Graph graph;

    @Before
    public void setUp() {
        int V = 5;
        int E = 3;
        int[] data = new int[] {0, 1, 2, 3, 1, 3};
        graph = new Graph(V, E, data);
    }

    @Test
    public void testGraph() {
        System.out.println("顶点的数量 " + graph.V());
        System.out.println("边的数量 " + graph.E());
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点 " + i + " 的邻接表 " + graph.adj(i));
        }
    }

    @Test
    public void testDepthFirstSearch() {
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);
        System.out.println("与0连通的顶点数量 " + search.count());
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点 " + i + " 是否连通 "+ search.marked(i));
        }
    }

    @Test
    public void testDepthFirstPaths() {
        DepthFirstPaths paths = new DepthFirstPaths(graph, 0);
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点到达 " + i + " 的路径 "+ paths.pathTo(i));
        }
    }
}
