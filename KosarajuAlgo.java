import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KosarajuAlgo {

    // Function to find the number of strongly connected components (SCCs)
    public int stronglyConnectedComponent(int V, List<List<Integer>> adj) {
        // Array to mark the visited vertices
        int[] vist = new int[V];
        Stack<Integer> stack = new Stack<>();
        
        // Step 1: Perform DFS on the original graph to get the finishing order of vertices
        for (int i = 0; i < V; i++) {
            if (vist[i] == 0) {
                dfs(i, vist, adj, stack);
            }
        }

        // Step 2: Reverse the graph
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj1.add(new ArrayList<>());
        }
        
        // Reverse the edges of the graph
        for (int i = 0; i < V; i++) {
            vist[i] = 0;  // Reset visited array for the next DFS
            for (int edge : adj.get(i)) {
                adj1.get(edge).add(i);  // Reverse the edge direction
            }
        }

        // Step 3: Perform DFS in the reverse graph in the order of stack (from Step 1)
        int count = 0;
        while (!stack.isEmpty()) {
            int node = stack.pop();  // Get the top of the stack (finishing time order)
            if (vist[node] == 0) {   // If the node is not visited in the reversed graph
                count++;  // New SCC found
                dfsReverse(node, vist, adj1);  // Perform DFS on the reversed graph
            }
        }

        return count;  // Return the number of strongly connected components
    }

    // DFS function for the original graph to fill the stack with finishing order
    public void dfs(int node, int[] vist, List<List<Integer>> adj, Stack<Integer> stack) {
        vist[node] = 1;  // Mark the node as visited
        for (int edge : adj.get(node)) {
            if (vist[edge] == 0) {  // If the adjacent node is not visited
                dfs(edge, vist, adj, stack);
            }
        }
        stack.push(node);  // Push the node onto the stack after exploring all its neighbors
    }

    // DFS function for the reversed graph to mark all reachable nodes from a component
    public void dfsReverse(int node, int[] vist, List<List<Integer>> adj1) {
        vist[node] = 1;  // Mark the node as visited
        for (int edge : adj1.get(node)) {
            if (vist[edge] == 0) {  // If the adjacent node is not visited
                dfsReverse(edge, vist, adj1);
            }
        }
    }

    // Main method to test the Kosaraju algorithm
    public static void main(String[] args) {
        // Create a graph with 5 vertices (0 to 4)
        KosarajuAlgo kosaraju = new KosarajuAlgo();
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph
        kosaraju.addEdge(adj, 0, 2);
        kosaraju.addEdge(adj, 2, 1);
        kosaraju.addEdge(adj, 1, 0);
        kosaraju.addEdge(adj, 2, 3);
        kosaraju.addEdge(adj, 3, 4);

        // Find the number of SCCs using Kosaraju's algorithm
        int sccCount = kosaraju.stronglyConnectedComponent(V, adj);
        
        // Print the number of strongly connected components
        System.out.println("Number of Strongly Connected Components: " + sccCount);
    }

    // Helper function to add edges to the graph
    public void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);  // Add directed edge u -> v
    }
}

