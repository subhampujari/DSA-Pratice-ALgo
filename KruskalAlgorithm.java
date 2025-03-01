import java.util.*;

// Class to represent an edge in the graph
class Edge {
    int src, dest, weight;

    // Constructor to create an edge
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

// Class to represent the disjoint-set (Union-Find)
class DisjointSet {
    int[] parent, rank;

    // Constructor to initialize disjoint-set data structure
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // Find the root of the node with path compression
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // Path compression
        }
        return parent[node];
    }

    // Union of two subsets by rank
    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        // If they are in the same set, do nothing
        if (rootU != rootV) {
            // Union by rank
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}

// Class to implement Kruskal's Algorithm
public class KruskalAlgorithm {
    
    // Method to find the Minimum Spanning Tree (MST) using Kruskal's algorithm
    public static int kruskalMST(int V, List<Edge> edges) {
        // Sort edges based on their weight
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        DisjointSet ds = new DisjointSet(V);
        int mstWeight = 0; // Variable to store the weight of MST
        List<Edge> mstEdges = new ArrayList<>(); // List to store edges in the MST

        // Iterate through all edges
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int weight = edge.weight;

            // Check if including this edge creates a cycle
            if (ds.find(u) != ds.find(v)) {
                ds.union(u, v); // Union the sets
                mstEdges.add(edge); // Add edge to the MST
                mstWeight += weight; // Add weight to the MST weight
            }
        }

        // Print the edges in the MST (optional)
        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mstEdges) {
            System.out.println("Source: " + edge.src + " Destination: " + edge.dest + " Weight: " + edge.weight);
        }

        // Return the total weight of the MST
        return mstWeight;
    }

    public static void main(String[] args) {
        // Create a graph with 6 vertices (0-5)
        int V = 6;
        
        // List of edges (src, dest, weight)
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 3));
        edges.add(new Edge(2, 4, 6));
        edges.add(new Edge(3, 4, 7));
        edges.add(new Edge(3, 5, 8));
        edges.add(new Edge(4, 5, 9));

        // Find the MST weight using Kruskal's algorithm
        int mstWeight = kruskalMST(V, edges);
        System.out.println("Weight of the Minimum Spanning Tree: " + mstWeight);
    }
}
