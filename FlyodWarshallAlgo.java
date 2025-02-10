import java.util.Arrays;

public class FlyodWarshallAlgo {

    // Floyd-Warshall Algorithm to find shortest paths between all pairs of vertices
    public static void floydWarshall(int V, int[][] edges) {
        // Initialize the distance matrix with infinity
        int[][] dist = new int[V][V];
        
        // Step 1: Initialize distances based on the edges
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0;  // Distance from a vertex to itself is 0
                } else {
                    dist[i][j] = Integer.MAX_VALUE;  // No direct path, set to infinity
                }
            }
        }
        
        // Step 2: Set distances based on the edges
        for (int[] edge : edges) {
            int u = edge[0];  // source
            int v = edge[1];  // destination
            int weight = edge[2];  // weight
            
            dist[u][v] = weight;
        }

        // Step 3: Apply the Floyd-Warshall algorithm to update the distance matrix
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Check if the path from i -> k -> j is shorter than i -> j
                   dist[i][j]=Math.max(dist[i][k]+dist[k][j], dist[i][j]);
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if(dist[i][j]==Integer.MAX_VALUE){
                    dist[i][j]=-1;
                }
            }
           
        }
        // Step 4: Print the distance matrix
        printSolution(dist);
    }

    // Function to print the solution matrix
    public static void printSolution(int[][] dist) {
        int V = dist.length;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }

    // Main method to test the Floyd-Warshall algorithm
    public static void main(String[] args) {
        int V = 4;  // Number of vertices

        // Define edges in the form of int[][]: [source, destination, weight]
        int[][] edges = {
            {0, 1, 3},
            {0, 2, 5},
            {1, 2, 1},
            {2, 3, 2},
            {3, 0, 4}
        };

        // Run Floyd-Warshall algorithm to find shortest paths between all pairs of vertices
        floydWarshall(V, edges);
    }
}

