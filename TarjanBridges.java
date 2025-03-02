import java.util.*;

public class TarjanBridges {
    static int time = 0;
    
    // Function to perform DFS and find bridges
    public static void dfs(int u, int parent, int[] disc, int[] low, 
                            List<List<Integer>> graph, boolean[] visited, List<String> bridges) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        // Explore all the neighbors of u
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                // Recur for the adjacent vertex v
                dfs(v, u, disc, low, graph, visited, bridges);

                // Check if the subtree rooted at v has a connection back to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);

                // If the lowest vertex reachable from v is lower than u, then u-v is a bridge
                if (low[v] > disc[u]) {
                    bridges.add(u + "-" + v);
                }
            }
            // Update low[u] for the back edge from u to v
            else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Function to find bridges in the graph
    public static List<String> findBridges(int vertices, List<List<Integer>> graph) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        List<String> bridges = new ArrayList<>();

        // Initialize arrays
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(visited, false);

        // Call DFS for every unvisited node in the graph
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, graph, visited, bridges);
            }
        }

        return bridges;
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges to the graph (Undirected graph)
        graph.get(0).add(1);
        graph.get(1).add(0);

        graph.get(1).add(2);
        graph.get(2).add(1);

        graph.get(0).add(2);
        graph.get(2).add(0);

        graph.get(2).add(3);
        graph.get(3).add(2);

        graph.get(3).add(4);
        graph.get(4).add(3);

        // Find bridges
        List<String> bridges = findBridges(vertices, graph);

        // Print bridges
        System.out.println("Bridges in the graph:");
        for (String bridge : bridges) {
            System.out.println(bridge);
        }
    }
}

