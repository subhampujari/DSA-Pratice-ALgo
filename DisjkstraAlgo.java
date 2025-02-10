import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DisjkstraAlgo {
    static class Pair{
        int dest;
        int weight;
        Pair(int dest,int weight){
            this.dest=dest;
            this.weight=weight;
        }
    }
    public static int[] dijkstra(int V,ArrayList<ArrayList<Pair>> adj,int src){
         int dist[]=new int[V];
         Arrays.fill(dist, Integer.MAX_VALUE);

         dist[src]=0;

         PriorityQueue <Pair>pq=new PriorityQueue<>(Comparator.comparingInt(a->a.weight));
         pq.offer(new Pair(src, 0));

         while (!pq.isEmpty()) {
            Pair pair=pq.poll();
            int u=pair.dest;
             for(Pair next:adj.get(u)){
                int v=next.dest;
                int weight=next.weight;
                if(dist[u] !=Integer.MAX_VALUE && dist[u]+weight<dist[v]){
                    dist[v]=dist[u]+weight;
                    pq.offer(new Pair(v, dist[v]));
                }
             }
         }
         return dist;
    }
    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        // Initialize adjacency list for the graph
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges (u, v, weight)
        adj.get(0).add(new Pair(1, 5));
        adj.get(0).add(new Pair(2, 3));
        adj.get(1).add(new Pair(3, 6));
        adj.get(1).add(new Pair(2, 2));
        adj.get(2).add(new Pair(4, 4));
        adj.get(2).add(new Pair(5, 2));
        adj.get(3).add(new Pair(5, 1));

        // Call Dijkstra's Algorithm from source node 0
        int[] result = dijkstra(V, adj, 0);

        // Print the shortest path from source node 0
        System.out.println("Shortest path distances from node 0:");
        for (int i = 0; i < V; i++) {
            if (result[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": No path");
            } else {
                System.out.println("Vertex " + i + ": " + result[i]);
            }
        }
    }
}
