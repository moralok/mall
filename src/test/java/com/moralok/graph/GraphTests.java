package com.moralok.graph;

import com.moralok.alg4.In;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author moralok
 * @since 2020/9/16
 */
public class GraphTests {

    private final String tinyCG = "src/test/java/com/moralok/alg4/data/tinyCG.txt";
    private final String tinyG = "src/test/java/com/moralok/alg4/data/tinyG.txt";

    @Before
    public void setUp() {
    }

    @Test
    public void testGraph() {
        Graph G = new Graph(new In(tinyG));
        System.out.println(G);
    }

    @Test
    public void testDepthFirstSearch() {
        System.out.println("深度优先搜索");
        Graph G = new Graph(new In(tinyG));
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        System.out.println("与0连通的顶点数量 " + search.count());
        for (int i = 0; i < G.V(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        if (search.count() != G.V()) {
            System.out.print("Not ");
        }
        System.out.println("connected");
    }

    @Test
    public void testDepthFirstPaths() {
        System.out.println("深度优先搜索寻找路径");
        Graph G = new Graph(new In(tinyCG));
        int s = 0;
        DepthFirstPaths paths = new DepthFirstPaths(G, s);
        for (int i = 0; i < G.V(); i++) {
            System.out.print(s + " to " + i + " : ");
            if (paths.hasPathTo(i)) {
                for (int x : paths.pathTo(i)) {
                    if (x == s) {
                        System.out.print(x);
                    } else {
                        System.out.print("-" + x);
                    }
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testBreadthFirstPaths() {
        System.out.println("广度优先搜索寻找路径");
        Graph G = new Graph(new In(tinyCG));
        int s = 0;
        BreadthFirstPaths paths = new BreadthFirstPaths(G, s);
        for (int i = 0; i < G.V(); i++) {
            System.out.print(s + " to " + i + " : ");
            if (paths.hasPathTo(i)) {
                for (int x : paths.pathTo(i)) {
                    if (x == s) {
                        System.out.print(x);
                    } else {
                        System.out.print("-" + x);
                    }
                }
                System.out.println();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testConnectedComponent() {
        System.out.println("深度优先搜索寻找连通分量");
        Graph G = new Graph(new In(tinyG));
        ConnectedComponent cc = new ConnectedComponent(G);
        int count = cc.count();
        System.out.println(count + " 连通分量");
        Set<Integer>[] component = (Set<Integer>[]) new Set[count];
        for (int i = 0; i < count; i++) {
            component[i] = new HashSet<>();
        }
        for (int i = 0; i < G.V(); i++) {
            component[cc.id(i)].add(i);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("id " + i + " 连通分量中的顶点 " + component[i]);
        }
    }

    @Test
    public void testCycle() {
        System.out.println("深度优先搜索检查是否为无环图");
        Graph G = new Graph(new In(tinyG));
        Cycle cycle = new Cycle(G);
        System.out.println("是否有环 " + cycle.hasCycle());
    }

    @Test
    public void testTwoColor() {
        System.out.println("深度优先搜索检查是否为二分图");
        Graph G = new Graph(new In(tinyG));
        TwoColor twoColor = new TwoColor(G);
        System.out.println("是否为二分图 " + twoColor.isBipartite());
    }
}
