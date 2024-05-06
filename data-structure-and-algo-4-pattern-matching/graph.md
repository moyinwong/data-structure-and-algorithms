# Graph

## Terminology

- Vertex (Vertices)/node (nodes)
- Edges, represent direction (in directed graph) / connection (in undirected graph)
- order of a graph, the number of vertices
- size of a graph, the number of edges

## Types of path

- **simple** path, with no repeated vertices
- **trail**, allow repeated vertices but not edge
- **walk**, allow both repeated vertices and edges
- **cycle**, a path where first vertex is adjacent to the last vertex
- **circuit**, a trail where first vertex is adjacent to the last vertex
- **tree**, acyclic, undirected and connected graph
- **spanning tree**, a tree that connects every vertex in the graph
- **Minimum Spanning Tree**, a spanning tree of the graph that has the minimum total edge weight

## Types of graph

- **Simple graph**, graph without parallel edges and self-loop
- **multigraph**, graph with parallel edges/self-loop
- **Directed graph**
    - each edge can only traverse from origin to destination, not vice versa
    - connected graph
        - weakly connected graph, is a graph is connected if removed the direction of each edges and making it a
          undirected graph
        - strongly connected graph, is a graph where every pair of vertices has a directed path between them
- **Undirected graph**
    - each edge is bi-directional
- **disconnected graph**
    - if two nodes exists in the graph with no path
- **dense graph**, a graph which there are more edges than vertices, `E = V^2` (more precisely`(n(n-1)/2)`)
- **sparse graph**, a graph where the number of edges <= number of vertices, `E = V`

## Dijkstra's algorithm

Search for shortest path/distance from a source vertex to a destination. The algorithms makes use of 3 auxiliary data
structures:

1. Priority Queue (for finding the shortest cumulative distance to a vertex)
2. Visited set (not to repeatedly visit an edge)
3. Hash Map (storing the shortest distance from source to a vertex)

```java
// assume the graph is in form of adjacency list
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

```

### Time complexity

- `E log E`
    - the priority queue could add entries `E` times. Each add is `log E` for binary heap
    - and it could also remove entries `E` times. Each removal is `log E` for binary heap
- `(V + E) log V` if a update function is implemented to update the priority of entries in the PQ to minimize the number
  of entries added to the queue
    - Max entries in queue is `V`. Removal is now at most `V` times and cost `log V`
    - Add/update to queue is still `E` times. Add cost `log V` each times

## Prim's Algorithm

It's a **greedy algorithm** for finding **Minimum Spanning Tree (MST)** by considering the shortest edges the connect
all the vertices. The algorithm makes use of 2 auxiliary data structures:

1. Priority Queue
2. visited set
3. MST edge set

```java
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
```

### Time complexity

- `E log E`
    - the priority queue could add entries `E` times. Each add is `log E` for binary heap
    - and it could also remove entries `E` times. Each removal is `log E` for binary heap

## Read further

- UCS (uniform cost search) algorithm
- A* algorithm