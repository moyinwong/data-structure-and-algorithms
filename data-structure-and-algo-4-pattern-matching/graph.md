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
all the vertices. The algorithm makes use of 3 auxiliary data structures:

1. Priority Queue
2. visited set
3. MST edge set

Graph cut, takes a subset of vertices and all edges connecting them. Any MST must include the minimum cost edge over any
graph cut

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

## Kruskal's Algorithm

Find the MST or Minimum Spanning Forest (MSF) for an undirected, connected and weighted graph. It utilise three
auxiliary data structures:

1. Priority Queue
2. Disjoint set
3. MST edge set

The algorithm:

- adding all edges into the priority queue
- While the priority queue is not empty and MST edge set size is not `n - 1` (no. of vertices in graph)
    - dequeue an edge from the queue
    - if the `from` and `to` vertex doesn't form a cycle, add the edge to the MST

Disjoint set is used to detect if cycle would be formed by:

- detecting if two vertices are connected (if they are in the same component)
- merge/union two connected components efficiently so that they are in the same component

It has two operations:

- `find(s)`, find the root/representative of a given vertex. Follow the outgoing edge to ancestors until reaching the
  root. Will need efficient access to where is located in the disjoint set forest. This is accomplished by using a
  HashMap
  from vertex objects in the graph to node objects in the disjoint set forest.
- `union(u, v)`, merge the sets that `u` and `v` belong to. First need to find the roots of `find(u)` and
  then `find(v)`. Make either `root(u)` a child of `root(v)` or vice versa. This ensure the representative of the
  component is unique

Two optimizations can be made for the disjoint set operations

1. **Path compression** for `find(s)`, making all nodes in a subtree point to same root on the recursive call to find
   root for a particular node
2. **Union by rank** for `union(u, v)`, serves as upper bound for height with path compression. Rank can only be higher
   than the actual height of a vertex with path compression:
    - Each node's initial rank is set to 0. Once we compute and `root(u)` and `root(v)`, we check
      if `rank(root(u))` > `rank(root(v))`. If so, `root(v)` becomes a child of `root(u)`; the rank doesn't change.
    - If `rank(root(v))` > `rank(root(u))`, then `root(u)` becomes the child of `root(v)`, and the rank doesn't change.
      Otherwise, the ranks are equal,
      so we can make either the child. For example, if we make `root(v)` the child of `root(u)`, then we update the rank
      like so: .`rank(root(u)) = rank(root(u)) + 1`

### Time complexity

- `E log E`
    - the priority queue could add entries `E` times. Each add is `log E` for binary heap
    - and it could also remove entries `E` times. Each removal is `log E` for binary heap

## Read further

- UCS (uniform cost search) algorithm
- A* algorithm