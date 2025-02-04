import java.util.ArrayList;

public class DetectCycleInDirect {

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int visited[] = new int[V]; // This will keep track of visited nodes
        int recursionStack[] = new int[V]; // This keeps track of nodes in the current DFS stack

        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) { // If the node is not yet visited
                if (dfs(i, visited, recursionStack, adj)) {
                    return true; // If a cycle is found, return true
                }
            }
        }
        return false; // No cycle found in the graph
    }

    public boolean dfs(int src, int[] visited, int[] recursionStack, ArrayList<ArrayList<Integer>> adj) {
        visited[src] = 1; // Mark the node as visited
        recursionStack[src] = 1; // Add the node to the current DFS stack

        for (int next : adj.get(src)) {
            if (visited[next] == 0) { // If the adjacent node is not visited
                if (dfs(next, visited, recursionStack, adj)) {
                    return true; // If a cycle is found during the DFS of the adjacent node
                }
            } else if (recursionStack[next] == 1) { // If the adjacent node is already in the recursion stack, a cycle is detected
                return true;
            }
        }

        recursionStack[src] = 0; // Remove the node from the recursion stack after DFS is done
        return false; // No cycle found in this DFS path
    }

    public static void main(String[] args) {
        DetectCycleInDirect detector = new DetectCycleInDirect();

        // Create a graph represented by an adjacency list
        int V = 4;  // Number of vertices in the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        // Initialize the adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph (directed edges)
        adj.get(0).add(1);  // 0 -> 1
        adj.get(1).add(2);  // 1 -> 2
        adj.get(2).add(0);  // 2 -> 0 (creates a cycle)
        adj.get(3).add(2);  // 3 -> 2 (no cycle)

        // Check if the graph contains a cycle
        if (detector.isCycle(V, adj)) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }

        // Another example without a cycle
        adj.clear();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph (directed edges)
        adj.get(0).add(1);  // 0 -> 1
        adj.get(1).add(2);  // 1 -> 2
        adj.get(2).add(3);  // 2 -> 3
        adj.get(3).add(0);  // 3 -> 0 (creates no cycle in this case)

        // Check if the graph contains a cycle
        if (detector.isCycle(V, adj)) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain a cycle.");
        }
    }
}
