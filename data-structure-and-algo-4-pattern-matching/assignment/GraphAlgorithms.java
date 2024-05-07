package assignment;

import java.sql.SQLOutput;
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
        edges.add(new Edge<>(b, a, 1));
        edges.add(new Edge<>(a, c, 5));
        edges.add(new Edge<>(c, a, 5));
        edges.add(new Edge<>(b, c, 7));
        edges.add(new Edge<>(c, b, 7));
        edges.add(new Edge<>(b, g, 1));
        edges.add(new Edge<>(g, b, 1));
        edges.add(new Edge<>(b, d, 9));
        edges.add(new Edge<>(d, b, 9));
        edges.add(new Edge<>(c, g, 13));
        edges.add(new Edge<>(g, c, 13));
        edges.add(new Edge<>(d, f, 4));
        edges.add(new Edge<>(f, d, 4));
        edges.add(new Edge<>(f, g, 2));
        edges.add(new Edge<>(g, f, 2));
        edges.add(new Edge<>(f, h, 0));
        edges.add(new Edge<>(h, f, 0));
        edges.add(new Edge<>(h, g, 3));
        edges.add(new Edge<>(g, h, 3));
        edges.add(new Edge<>(g, e, 10));
        edges.add(new Edge<>(e, g, 10));

        Graph<Character> graph = new Graph<>(vertices, edges);

        var set = prims(b, graph);
        if (set == null) {
            return;
        }
        set.forEach(ele -> {
            System.out.printf("From: %s, to: %s, edge weight: %d\n", ele.getU().getData().toString(), ele.getV().getData().toString(), ele.getWeight());
        });
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops or parallel edges in the MST.
     * <p>
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     * <p>
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     * <p>
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        Queue<Edge<T>> queue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        int n = graph.getVertices().size();

        addNeighbourEdgesToQueue(start, adjList, queue, visited);
        visited.add(start);

        while (visited.size() < n && mst.size() < (2 * (n - 1)) && !queue.isEmpty()) {
            Edge<T> edge = queue.poll();
            Vertex<T> from = edge.getU();
            Vertex<T> to = edge.getV();

            if (!visited.contains(to)) {
                visited.add(to);
                mst.add(edge);
                mst.add(new Edge<>(to, from, edge.getWeight()));

                addNeighbourEdgesToQueue(to, adjList, queue, visited);
            }
        }


        if (mst.size() != 2 * (n - 1) || visited.size() < n) {
            return null;
        }

        return mst;
    }

    private static <T> void addNeighbourEdgesToQueue(Vertex<T> source, Map<Vertex<T>, List<VertexDistance<T>>> adjList, Queue<Edge<T>> queue, Set<Vertex<T>> visited) {
        for (VertexDistance<T> v : adjList.get(source)) {
            if (!visited.contains(v.getVertex())) {
                queue.offer(new Edge<>(source, v.getVertex(), v.getDistance()));
            }
        }
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