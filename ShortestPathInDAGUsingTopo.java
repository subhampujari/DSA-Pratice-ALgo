import java.util.*;

public class ShortestPathInDAGUsingTopo {
    
    static class Pair {
        int second;
        int wt;

        Pair(int second, int wt) {
            this.second = second;
            this.wt = wt;
        }
    }

    // Topological Sort using DFS
    private static void topoSort(int node, ArrayList<ArrayList<Pair>> adj, int[] visited, Stack<Integer> stack) {
        visited[node] = 1;  // Mark the node as visited
        for (Pair neighbor : adj.get(node)) {
            if (visited[neighbor.second] == 0) {  // If not visited
                topoSort(neighbor.second, adj, visited, stack);
            }
        }
        stack.push(node);  // Push the node to stack after exploring all its neighbors
    }

    public int[] shortestPath(int V, int E, int[][] edge) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        
        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the adjacency list
        for (int i = 0; i < E; i++) {
            int u = edge[i][0];
            int v = edge[i][1];
            int wt = edge[i][2];
            adj.get(u).add(new Pair(v, wt));
        }

        // Step 1: Perform Topological Sort
        int[] visited = new int[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                topoSort(i, adj, visited, stack);
            }
        }

        // Step 2: Initialize distances
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // Assuming source is node 0

        // Step 3: Relax edges in topologically sorted order
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (dist[node] != Integer.MAX_VALUE) { // If the node is reachable
                for (Pair neighbor : adj.get(node)) {
                    int v = neighbor.second;
                    int wt = neighbor.wt;
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        // Step 4: Replace unreachable vertices with -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }

        return dist;
    }

    // Main method to test the code
    public static void main(String[] args) {
        // Example input: Directed Acyclic Graph (DAG) with 6 vertices and 7 edges
        int V = 6;
        int E = 7;
        int[][] edge = {
            {0, 1, 5},
            {0, 2, 3},
            {1, 3, 6},
            {1, 2, 2},
            {2, 4, 4},
            {2, 5, 2},
            {3, 5, 1}
        };

        ShortestPathInDAGUsingTopo sp = new ShortestPathInDAGUsingTopo();
        int[] result = sp.shortestPath(V, E, edge);

        // Print the shortest path distances
        System.out.println("Shortest path from node 0:");
        for (int i = 0; i < V; i++) {
            if (result[i] == -1) {
                System.out.println("Vertex " + i + ": No path");
            } else {
                System.out.println("Vertex " + i + ": " + result[i]);
            }
        }
    }
}

