import java.util.Arrays;

public class BellAndFordAlgorithm {

    // Bellman-Ford Algorithm to find shortest paths from source to all vertices
    public static int[] bellAndFordAlgorithm(int V, int[][] edges, int src) {
        // Initialize distances to all vertices as infinity (Integer.MAX_VALUE)
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;  // Distance to the source node is 0

        // Step 1: Relax all edges (V-1) times
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                // Relax the edge if a shorter path is found
                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Step 2: Check for negative weight cycles
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            // If we can relax the edge after V-1 iterations, there's a negative cycle
            if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                return new int[]{-1};  // Return an array indicating a negative cycle
            }
        }

        return dist;  // Return the array of shortest distances
    }

    // Main method to test the Bellman-Ford algorithm
    public static void main(String[] args) {
        int V = 5;  // Number of vertices

        // Define edges in the form of int[][]: [source, destination, weight]
        int[][] edges = {
            {0, 1, -1},
            {0, 2, 4},
            {1, 2, 3},
            {1, 3, 2},
            {1, 4, 2},
            {3, 2, 5},
            {3, 1, 1},
            {4, 3, -3}
        };

        // Run Bellman-Ford algorithm from source node 0
        int[] res = bellAndFordAlgorithm(V, edges, 0);

        // Print the result (shortest distances)
        if (res[0] != -1) {  // If no negative cycle is found
            System.out.println("Shortest distances from source 0:");
            for (int i = 0; i < res.length; i++) {
                if (res[i] == Integer.MAX_VALUE) {
                    System.out.println("Vertex " + i + ": No path");
                } else {
                    System.out.println("Vertex " + i + ": " + res[i]);
                }
            }
        }
    }
}
