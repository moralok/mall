package com.moralok.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
        System.out.println("构造一个图");
        System.out.println("顶点的数量 " + graph.V());
        System.out.println("边的数量 " + graph.E());
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点 " + i + " 的邻接表 " + graph.adj(i));
        }
    }

    @Test
    public void testDepthFirstSearch() {
        System.out.println("深度优先搜索");
        DepthFirstSearch search = new DepthFirstSearch(graph, 0);
        System.out.println("与0连通的顶点数量 " + search.count());
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点 " + i + " 是否连通 "+ search.marked(i));
        }
    }

    @Test
    public void testDepthFirstPaths() {
        System.out.println("深度优先搜索寻找路径");
        DepthFirstPaths paths = new DepthFirstPaths(graph, 0);
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点到达 " + i + " 的路径 "+ paths.pathTo(i));
        }
    }

    @Test
    public void testBreadthFirstPaths() {
        System.out.println("广度优先搜索寻找路径");
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, 0);
        for (int i = 0; i < graph.V(); i++) {
            System.out.println("顶点到达 " + i + " 的路径 "+ paths.pathTo(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testConnectedComponent() {
        System.out.println("深度优先搜索寻找连通分量");
        ConnectedComponent cc = new ConnectedComponent(graph);
        int count = cc.count();
        System.out.println(count + " 连通分量");
        Set<Integer>[] component = (Set<Integer>[]) new Set[count];
        for (int i = 0; i < count; i++) {
            component[i] = new HashSet<>();
        }
        for (int i = 0; i < graph.V(); i++) {
            component[cc.id(i)].add(i);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("id " + i + " 连通分量中的顶点 " + component[i]);
        }
    }

    @Test
    public void testCycle() {
        System.out.println("深度优先搜索检查是否为无环图");
        Cycle cycle = new Cycle(graph);
        System.out.println("是否有环 " + cycle.hasCycle());
    }
}
