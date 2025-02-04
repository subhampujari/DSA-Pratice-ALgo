import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {

    // Function to check whether the graph is bipartite or not
    public boolean isBipartiteOrNot(int V, ArrayList<ArrayList<Integer>> adj) {
        int color[] = new int[V];
        Arrays.fill(color, -1);  // Fill the color array with -1 indicating uncolored vertices

      /*   for (int i = 0; i < V; i++) {
            if (color[i] == -1) {  // If vertex is not colored
                if (dfs(i, 0, adj, color) == false) {  // Start DFS from this vertex with color 0
                    return false;  // If the graph is not bipartite, return false
                }
            }
        }*/

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {  // If vertex is not colored
                if (bfs(i, adj, color) == false) {  // Start DFS from this vertex with color 0
                    return false;  // If the graph is not bipartite, return false
                }
            }
        }
        return true;  // If no issues were found, return true
    }

    // DFS function to perform depth-first search and color the graph
    public boolean dfs(int src, int col, ArrayList<ArrayList<Integer>> adj, int color[]) {
        color[src] = col;  // Assign color to the current node
        for (int node : adj.get(src)) {  // Traverse through all adjacent nodes
            if (color[node] == -1) {  // If the adjacent node is not colored
                if (dfs(node, 1 - col, adj, color) == false) {  // Alternate color for the adjacent node
                    return false;  // If any node cannot be colored, return false
                }
            } else if (color[node] == col) {  // If the adjacent node has the same color, return false
                return false;
            }
        }
        return true;  // If no issues were found, return true
    }
    
    // BFS function to check if the graph is bipartite starting from the source node
    public boolean bfs(int src, ArrayList<ArrayList<Integer>> adj, int color[]) {
        color[src] = 0;  // Start coloring the source node with color 0
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);  // Add the source node to the queue
        
        while (!q.isEmpty()) {
            int next = q.poll();  // Get the next node from the queue
            
            // Explore all adjacent nodes
            for (int r : adj.get(next)) {
                if (color[r] == -1) {  // If the node has not been colored
                    color[r] = 1 - color[next];  // Assign an alternate color
                    q.add(r);  // Add the node to the queue for further exploration
                } else if (color[r] == color[next]) {  // If the adjacent node has the same color
                    return false;  // The graph is not bipartite
                }
            }
        }
        return true;  // If no issues were found, the graph is bipartite
    }
    // Main method to test the functionality
    public static void main(String[] args) {
        // Create an instance of BipartiteGraph
        BipartiteGraph bg = new BipartiteGraph();
        
        // Sample Graph
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Add edges to the graph
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        
        // Check if the graph is bipartite
        if (bg.isBipartiteOrNot(V, adj)) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }
    }
}