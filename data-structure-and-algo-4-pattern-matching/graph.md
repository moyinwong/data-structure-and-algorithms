# Graph

## Terminology

- Vertex (Vertices)/node (nodes)
- Edges, represent direction (in directed graph) / connection (in undirected graph)
- order of a graph, the number of vertices
- size of a graph, the number of edges

### Types of path

- **simple** path, with no repeated vertices
- **trail**, allow repeated vertices but not edge
- **walk**, allow both repeated vertices and edges
- **cycle**, a path where first vertex is adjacent to the last vertex
- **circuit**, a trail where first vertex is adjacent to the last vertex

### Types of graph

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

