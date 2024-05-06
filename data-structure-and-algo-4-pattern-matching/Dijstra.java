import assignment.Edge;
import assignment.Vertex;
import assignment.Graph;
import assignment.VertexDistance;

import java.util.*;
import java.util.stream.Collectors;

public class Dijstra {
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

        var map = dijstra(b, graph);
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        System.out.println(mapAsString);
    }

    public static <T> Map<Vertex<T>, Integer> dijstra(Vertex<T> start, Graph<T> graph) {
        Map<Vertex<T>, Integer> shortestDistanceMap = new HashMap<>();
        Set<Vertex<T>> visited = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        int n = graph.getVertices().size();

        for (Vertex<T> vertex : graph.getVertices()) {
            shortestDistanceMap.put(vertex, -1);
        }
        pq.add(new VertexDistance<>(start, 0));

        while (!pq.isEmpty() && visited.size() < n) {
            VertexDistance<T> vertexDistance = pq.poll();
            Vertex<T> vertex = vertexDistance.getVertex();

            if (!visited.contains(vertex)) {
                visited.add(vertex);
                shortestDistanceMap.put(vertex, vertexDistance.getDistance());
                // store path here if want to find shortest path

                for (VertexDistance<T> v : adjList.get(vertex)) {
                    if (!visited.contains(v.getVertex())) {
                        // update cumulative path if want to return shortest path
                        pq.offer(new VertexDistance<>(v.getVertex(), v.getDistance() + vertexDistance.getDistance()));
                    }
                }
            }
        }

        return shortestDistanceMap;
    }

}
