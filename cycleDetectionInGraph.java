import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class cycleDetectionInGraph {

    static class Pair {
        int src, parent;
        
        // Constructor for the Pair class
        public Pair(int src, int parent) {
            this.src = src;
            this.parent = parent;
        }
    }


// Helper function for DFS to detect a cycle
    public static boolean dfsCycle(int src, int parent, List<List<Integer>> adj, int[] isVist) {
        // Mark the current node as visited
        isVist[src] = 1;

        // Explore all neighbors of the current node
        for (int nextNode : adj.get(src)) {
            // If the neighbor is not visited, do DFS on that node
            if (isVist[nextNode] == 0) {
                if (dfsCycle(nextNode, src, adj, isVist)) {
                    return true;  // If a cycle is detected, return true
                }
            }
            // If the neighbor is visited and it's not the parent, we've found a cycle
            else if (nextNode != parent) {
                return true;  // Cycle found
            }
        }

        return false;  // No cycle found from this node
    }

    // Main function to check if the graph contains a cycle
    public static boolean isCycle(int v, List<List<Integer>> adj) {
        // Array to track visited nodes (0 = unvisited, 1 = visited)
        int[] isVist = new int[v];
        
        // Perform DFS from every unvisited node to check for cycles
        for (int i = 0; i < v; i++) {
            if (isVist[i] == 0) {  // If the node is unvisited
                if (dfsCycle(i, -1, adj, isVist)) {
                    return true;  // Cycle detected, return true
                }
            }
        }
        
        return false;  // No cycle detected
    }


    // Function to check if the graph contains a cycle using BFS
    public boolean isCycleBFS(int v, List<List<Integer>> adj) {
        // Array to track visited nodes (0 = unvisited, 1 = visited)
        int[] isVisit = new int[v];

        // Iterate over all nodes to ensure that even disconnected components are checked
        for (int i = 0; i < v; i++) {
            // If the node is unvisited, start BFS from that node
            if (isVisit[i] == 0) {
                if (bfsCycle(i, adj, isVisit)) {
                    return true;  // If BFS detects a cycle, return true
                }
            }
        }
        return false;  // If no cycle is detected in any component, return false
    }

    // Helper function to perform BFS and detect a cycle
    public boolean bfsCycle(int src, List<List<Integer>> adj, int[] isVist) {
        // Mark the starting node as visited
        isVist[src] = 1;

        // Queue for BFS, storing pairs of (node, parent)
        Queue<Pair> q = new LinkedList<>();
        
        // Enqueue the start node with no parent (-1 indicates no parent)
        q.offer(new Pair(src, -1));

        // Perform BFS traversal
        while (!q.isEmpty()) {
            // Dequeue a pair (current node, parent)
            Pair p = q.poll();
            int curr = p.src;   // Current node
            int parent = p.parent;   // Parent of the current node

            // Explore all neighbors of the current node
            for (int next : adj.get(curr)) {
                // If the neighbor has not been visited yet, mark it visited and enqueue it
                if (isVist[next] == 0) {
                    isVist[next] = 1;
                    q.offer(new Pair(next, curr));  // Add (neighbor, current) pair to queue
                } 
                // If the neighbor is already visited and is not the parent, we've found a cycle
                else if (parent != next) {
                    return true;  // Cycle detected
                }
            }
        }
        return false;  // No cycle detected in this BFS run
    }

    public static void main(String[] args) {
        
        cycleDetectionInGraph solution = new cycleDetectionInGraph();

        // Example usage:
        int v = 5;  // Number of vertices
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list for the graph
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph (undirected graph)
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(4).add(3);
        adj.get(1).add(3);
        adj.get(3).add(1);  // This introduces a cycle: 1 -> 3 -> 2 -> 1

        // Check if the graph contains a cycle
        boolean result = solution.isCycle(v, adj);
        System.out.println("Does the graph contain a cycle? " + result); 

             // Check if the graph contains a cycle
             boolean result1 = solution.isCycleBFS(v, adj);
             System.out.println("Does the graph contain a cycle? " + result1); 
     
   }
}