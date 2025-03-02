import java.util.*;

public class TarjanArticulationPoints {
    static int time = 0;

    // Function to perform DFS and find articulation points
    public static void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited, 
                            List<List<Integer>> graph, boolean[] articulationPoints) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        // Explore all the neighbors of u
        for (int v : graph.get(u)) {
            if (!visited[v]) {
                children++;

                // Recur for the adjacent vertex v
                dfs(v, u, disc, low, visited, graph, articulationPoints);

                // Check if the subtree rooted at v has a connection back to one of the ancestors of u
                low[u] = Math.min(low[u], low[v]);

                // If u is the root and has more than one child, it is an articulation point
                if (parent == -1 && children > 1) {
                    articulationPoints[u] = true;
                }

                // If u is not the root and low[v] >= disc[u], it is an articulation point
                if (parent != -1 && low[v] >= disc[u]) {
                    articulationPoints[u] = true;
                }
            }
            // Update low[u] for the back edge from u to v
            else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Function to find articulation points in the graph
    public static List<Integer> findArticulationPoints(int vertices, List<List<Integer>> graph) {
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        boolean[] articulationPoints = new boolean[vertices];
        List<Integer> articulationPointList = new ArrayList<>();

        // Initialize arrays
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(visited, false);

        // Call DFS for every unvisited node in the graph
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, graph, articulationPoints);
            }
        }

        // Collect the articulation points
        for (int i = 0; i < vertices; i++) {
            if (articulationPoints[i]) {
                articulationPointList.add(i);
            }
        }

        return articulationPointList;
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

        // Find articulation points
        List<Integer> articulationPoints = findArticulationPoints(vertices, graph);

        // Print articulation points
        System.out.println("Articulation points in the graph:");
        for (int point : articulationPoints) {
            System.out.println(point);
        }
    }
}

