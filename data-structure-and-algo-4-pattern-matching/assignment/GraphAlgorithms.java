package assignment;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    public static void main(String[] args) {
        Set<Vertex<Character>> vertices = new HashSet<>();
        Vertex<Character> a = new Vertex<>('A');
        vertices.add(a);
        Vertex<Character> b = new Vertex<>('B');
        vertices.add(b);
        Vertex<Character> c = new Vertex<>('C');
        vertices.add(c);
        Vertex<Character> d = new Vertex<>('D');
        vertices.add(d);
        Vertex<Character> e = new Vertex<>('E');
        vertices.add(e);
        Vertex<Character> f = new Vertex<>('F');
        vertices.add(f);
        Vertex<Character> g = new Vertex<>('G');
        vertices.add(g);
        Vertex<Character> h = new Vertex<>('H');
        vertices.add(h);

        Set<Edge<Character>> edges = new HashSet<>();
        edges.add(new Edge<>(a, b, 1));
        edges.add(new Edge<>(a, e, 2));
        edges.add(new Edge<>(a, d, 7));
        edges.add(new Edge<>(b, c, 8));
        edges.add(new Edge<>(b, f, 7));
        edges.add(new Edge<>(c, g, 5));
        edges.add(new Edge<>(c, a, 2));
        edges.add(new Edge<>(g, f, 4));
        edges.add(new Edge<>(g, h, 2));
        edges.add(new Edge<>(g, e, 1));
        edges.add(new Edge<>(f, g, 9));
        edges.add(new Edge<>(f, h, 9));
        edges.add(new Edge<>(d, f, 6));
        edges.add(new Edge<>(d, b, 7));
        edges.add(new Edge<>(d, h, 8));
        edges.add(new Edge<>(h, g, 2));
        Graph<Character> graph = new Graph<>(vertices, edges);

        System.out.println(bfs(a, graph));
    }

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     * <p>
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Set<Vertex<T>> visited = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        List<Vertex<T>> vertices = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();
            vertices.add(vertex);

            for (VertexDistance<T> v : adjList.get(vertex)) {
                if (!visited.contains(v.getVertex())) {
                    visited.add(v.getVertex());
                    queue.add(v.getVertex());
                }
            }
        }
        return vertices;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     * <p>
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     * <p>
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     * <p>
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        List<Vertex<T>> vertices = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        visited.add(start);
        vertices.add(start);
        dfsR(start, adjList, visited, vertices);

        return vertices;
    }

    private static <T> void dfsR(Vertex<T> vertex, Map<Vertex<T>, List<VertexDistance<T>>> adjList, Set<Vertex<T>> visited, List<Vertex<T>> vertices) {
        for (VertexDistance<T> v : adjList.get(vertex)) {
            Vertex<T> neighbour = v.getVertex();
            if (!visited.contains(neighbour)) {
                visited.add(neighbour);
                vertices.add(neighbour);
                dfsR(neighbour, adjList, visited, vertices);
            }
        }
    }
}