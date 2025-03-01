import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class PrimsAlgorithm {
    // This is the function to compute the Minimum Spanning Tree (MST) using Prim's Algorithm
    public int spanningTree(int V, List<List<int[]>> adj) {
        // PriorityQueue to store edges, sorted by their weight (min-heap)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        int mstWeight = 0; // Variable to store the total weight of the MST
        boolean[] isVisited = new boolean[V]; // Array to track visited nodes
        pq.offer(new int[]{0, 0}); // Start from node 0 with weight 0
        
        while (!pq.isEmpty()) {
            // Poll the edge with the minimum weight from the priority queue
            int[] curr = pq.poll();
            int weight = curr[0];
            int v = curr[1];
            
            // If the node is already visited, skip it
            if (isVisited[v]) continue;
            
            // Add the weight of this edge to the MST weight
            mstWeight += weight;
            // Mark the current node as visited
            isVisited[v] = true;
            
            // Check all adjacent edges to the current node
            for (int[] edge : adj.get(v)) {
                int newWeight = edge[0]; // Weight of the edge
                int nextNode = edge[1]; // Node at the other end of the edge
                
                // If the adjacent node is not visited, add the edge to the priority queue
                if (!isVisited[nextNode]) {
                    pq.offer(new int[]{newWeight, nextNode});
                }
            }
        }
        
        // Return the total weight of the MST
        return mstWeight;
    }

    public static void main(String[] args) {
        // Number of vertices in the graph
        int V = 5;
        
        // Creating an adjacency list representation of the graph
        List<List<int[]>> adj = new ArrayList<>();
        
        // Initialize the adjacency list for each vertex
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Add edges to the graph (weight, destination vertex)
        adj.get(0).add(new int[]{2, 1});
        adj.get(0).add(new int[]{3, 3});
        adj.get(1).add(new int[]{2, 0});
        adj.get(1).add(new int[]{5, 2});
        adj.get(2).add(new int[]{5, 1});
        adj.get(2).add(new int[]{1, 3});
        adj.get(3).add(new int[]{3, 0});
        adj.get(3).add(new int[]{1, 2});
        
        // Create an instance of the PrimsAlgorithm class and call spanningTree method
        PrimsAlgorithm prim = new PrimsAlgorithm();
        int mstWeight = prim.spanningTree(V, adj);
        
        // Output the result (minimum spanning tree weight)
        System.out.println("Weight of the Minimum Spanning Tree: " + mstWeight);
    }
}
