# Dynamic Programming

Main idea, break a problem into subproblems such that finding the solution to a subproblem help solve the original
problem

## Longest common subsequence (LCS)

Find the longest common subsequence of two strings by utilizing a 2D array to store the results of the subproblem

- `S[i][j]` represents the LCS of substring of string 1 ending at index `i` , and the substring of string 2 ending at
  index `j`
- The algorithm:

1. init 2D array `S` of `n (length of str1) + 1` rows and `m (length of str2) + 1` cols
2. Prefills first row and col with 0. so that we don't need to special handle row and col 0
3. Iterate through each row
    1. if character of str1 at index `i` is matching character of str2 at index `j`
        - set `S[i][j]` to be `S[i - 1][j - 1] + 1` (`S[i - 1][j - 1]` is the top left diagonal, represent the previous
          LCS between substring of str 1 and substring of str 2 before the current character in both str)
    2. if character doesn't match and either top/left is greater than the other:
        - take the bigger val
    3. if character doesn't match and `top == left`
        - take left

```java
public static int lcs(String str1, String str2) {
    int n = str1.length() + 1;
    int m = str2.length() + 1;
    int[][] dp = new int[n][m];

    // prefill with 0s for easier traverse of algorithm
    for (int i = 0; i < n; i++) {
        dp[i][0] = 0;
    }

    for (int j = 0; j < m; j++) {
        dp[0][j] = 0;
    }

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                int top = dp[i - 1][j];
                int left = dp[i][j - 1];
                if (top == left) {
                    dp[i][j] = left;
                } else {
                    dp[i][j] = Math.max(top, left);
                }
            }
        }
    }

    int row = n - 1;
    int col = m - 1;
    int curr = dp[row][col];
    StringBuilder lcs = new StringBuilder();
    System.out.println("curr: " + curr);
    while (curr != 0) {
        int top = dp[row - 1][col];
        int left = dp[row][col - 1];
        int diagonal = dp[row - 1][col - 1];

        if (top == left && left == diagonal && curr != diagonal) {
            // values comes from diagonal calculation
            // we have an extra row & column, so need row - 1/col - 1 to get correct character at either str
            lcs.append(str1.charAt(row - 1));
            row--;
            col--;
        } else if (top == left && left == diagonal || top < left) {
            // top and left the same, comes from left
            col--;
        } else if (top > left) {
            // comes from top
            row--;
        }

        curr = dp[row][col];
    }

    // lcs string
    // lcs.reverse().toString()
    return dp[n - 1][m - 1];
}
```

## Bellman-Ford

Find the shortest distance from a `source` to all other vertices in the graph. Assume the shortest path from source to
some vertex could only take at most `V (no.of vertices) - 1` edge.

- use an array/map to store the shortest distance from source to all other vertexes. Initialize all vertex to
  store `infinity` and source vertex to store `0`, since the shortest distance to source is `0`
- run all edges `V - 1` times. For each time, it will find the shortest path that will take at most `i` edges
- update the cumulative distance from source to particular vertex if shorter path is found
- can detect negative cycle if optimal path can still be found after running all edges `V - 1` times

**Different than Dijkstra, it works with graph with negative weight**

```java
void BellmanFord(Graph graph, int src) {
    int V = graph.V, E = graph.E;
    int dist[] = new int[V];

    // Step 1: Initialize distances from src to all
    // other vertices as INFINITE
    for (int i = 0; i < V; ++i)
        dist[i] = Integer.MAX_VALUE;
    dist[src] = 0;

    // Step 2: Relax all edges |V| - 1 times. A simple
    // shortest path from src to any other vertex can
    // have at-most |V| - 1 edges
    for (int i = 1; i < V; ++i) {
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v])
                dist[v] = dist[u] + weight;
        }
    }

    // Step 3: check for negative-weight cycles. The
    // above step guarantees shortest distances if graph
    // doesn't contain negative weight cycle. If we get
    // a shorter path, then there is a cycle.
    for (int j = 0; j < E; ++j) {
        int u = graph.edge[j].src;
        int v = graph.edge[j].dest;
        int weight = graph.edge[j].weight;
        if (dist[u] != Integer.MAX_VALUE
                && dist[u] + weight < dist[v]) {
            System.out.println(
                    "Graph contains negative weight cycle");
            return;
        }
    }
    printArr(dist, V);
}
```

## Floyd-Warshall

Find shortest distances for all pairs of vertices in the graph by comparing all edges. Given `source` -> `u`
and `destination` -> `v`. If `s -> v` is greater than the sum of path of a intermediate node (`k`) between `u` and `v`.
Then a shorter path is found. Use a 2D matrix to store the result `s[u][v] = min(s[u][v], s[u][k] + s[k][v])`

It utilizes an **adjacency matrix** to compare the distance between edges.

- copy the adjacency matrix
- for each pair and each intermediate vertex, compare the distance between `u`, `v` and the sum of `u` and `k`, and `k`
  and `u`

```java
// 
public static int[][] floydWarshall(Graph graph) {
    int[][] adjMatrix = graph.getAdjMatrix();
    int n = graph.getVertices().size();
    int[][] matrix = copy(adjMatrix);

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] > (matrix[i][k] + matrix[k][j])) {
                    matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
    }

    return matrix;
}
```