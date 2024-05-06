import assignment.Edge;
import assignment.Graph;
import assignment.Vertex;
import assignment.VertexDistance;

import java.util.*;
import java.util.stream.Collectors;

public class MST {

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

        var map = prim(b, graph);
        map.forEach(ele -> {
            System.out.printf("Vertex: %s, edge weight: %d\n", ele.getVertex().getData().toString(), ele.getDistance());
        });
    }

    public static <T> Set<VertexDistance<T>> prim(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> visited = new HashSet<>();
        Set<VertexDistance<T>> mstEdges = new HashSet<>();
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();

        for (VertexDistance<T> v : adjList.get(start)) {
            priorityQueue.offer(v);
        }
        visited.add(start);

        while (visited.size() < graph.getVertices().size() && !priorityQueue.isEmpty()) {
            VertexDistance<T> edge = priorityQueue.poll();
            Vertex<T> vertex = edge.getVertex();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                mstEdges.add(edge);

                for (VertexDistance<T> v : adjList.get(vertex)) {
                    if (!visited.contains(v.getVertex())) {
                        priorityQueue.offer(new VertexDistance<>(v.getVertex(), v.getDistance()));
                    }
                }
            }
        }

        return mstEdges;
    }
}
