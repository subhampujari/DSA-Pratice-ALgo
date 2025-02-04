import java.util.ArrayList;
import java.util.Stack;

public class TopoSort {

    // Method for topological sorting
    public ArrayList<Integer> topoLogicalSorting(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<>();
        int[] isVist = new int[V];  // Array to track visited nodes
        // Apply DFS to all unvisited nodes
        for (int i = 0; i < V; i++) {
            if (isVist[i] == 0) {
                dfs(i, isVist, st, adj);
            }
        }
        
        // Extract the topological sort from the stack
        ArrayList<Integer> res = new ArrayList<>();
        while (!st.isEmpty()) {
            res.add(st.pop());
        }
        return res;
    }

    // Depth-First Search (DFS) method
    public void dfs(int src, int[] isVist, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj) {
        isVist[src] = 1;  // Mark the node as visited
        // Traverse all adjacent nodes (neighbors)
        for (int next : adj.get(src)) {
            if (isVist[next] == 0) {
                dfs(next, isVist, st, adj);  // Recursively visit unvisited neighbors
            }
        }
        // Push the current node to the stack after all its neighbors are processed
        st.push(src);
    }

    // Main method to create a DAG and perform topological sorting
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

        // Create an instance of the TopoSort class and perform topological sorting
        TopoSort topoSort = new TopoSort();
        ArrayList<Integer> result = topoSort.topoLogicalSorting(V, adj);
        
        // Print the result of topological sorting
        System.out.println("Topologically sorted order: " + result);
    }
}
