import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KhansAlgo {

    // Method to perform Kahn's Algorithm for topological sorting
    public ArrayList<Integer> KhansAlgo(int V, ArrayList<ArrayList<Integer>> adj) {
        int inDegree[] = new int[V];  // Array to track the in-degree of each node
        // Calculate in-degrees of all vertices
        for (int i = 0; i < V; i++) {
            for (int e : adj.get(i)) {
                inDegree[e]++;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();  // List to store the result of topological sort
        Queue<Integer> q = new LinkedList<>();  // Queue for nodes with in-degree 0
        // Add all nodes with in-degree 0 to the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // Process the nodes in the queue
        while (!q.isEmpty()) {
            int curr = q.poll();
            res.add(curr);  // Add the current node to the result
            // Reduce in-degree of adjacent nodes
            for (int next : adj.get(curr)) {
                inDegree[next]--;
                // If in-degree of adjacent node becomes 0, add it to the queue
                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        return res;
    }

    // Main method to create a graph and run Kahn's Algorithm for topological sorting
    public static void main(String[] args) {
        // Number of vertices in the graph (DAG)
        int V = 6;

        // Create the adjacency list for the DAG
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize adjacency lists for each vertex
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add directed edges (DAG structure)
        adj.get(0).add(1);  // 0 -> 1
        adj.get(0).add(2);  // 0 -> 2
        adj.get(1).add(3);  // 1 -> 3
        adj.get(1).add(4);  // 1 -> 4
        adj.get(2).add(5);  // 2 -> 5
        adj.get(4).add(5);  // 4 -> 5

        // Create an instance of the KhansAlgo class and perform topological sorting
        KhansAlgo khansAlgo = new KhansAlgo();
        ArrayList<Integer> result = khansAlgo.KhansAlgo(V, adj);

        // Print the result of topological sorting
        System.out.println("Topologically sorted order: " + result);
    }
}

