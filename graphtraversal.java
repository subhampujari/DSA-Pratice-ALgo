import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class graphtraversal {
   // DFS Helper Function (recursive)
   private static  void dfsHelper(int node, List<List<Integer>> adj, boolean[] isVisited, List<Integer> res) {
    // Mark the current node as visited
    isVisited[node] = true;
    // Add the current node to the result list
    res.add(node);
    
    // Explore all neighbors of the current node
    for (int nextNode : adj.get(node)) {
        if (!isVisited[nextNode]) {
            // Recursively visit the next node
            dfsHelper(nextNode, adj, isVisited, res);
        }
    }
}
// Main DFS method
public static List<Integer> dfs(int v, List<List<Integer>> adj, int startNode) {
    List<Integer> res = new ArrayList<>();
    // Boolean array to track visited nodes
    boolean[] isVisited = new boolean[v];
    
    // Start DFS from the startNode
    dfsHelper(startNode, adj, isVisited, res);
    
    return res;
}
  // BFS Method: Perform Breadth-First Search starting from the given node
  public List<Integer> bfs(int v, List<List<Integer>> adj, int node) {
    List<Integer> res = new ArrayList<>();  // List to store the BFS traversal result
    Queue<Integer> q = new LinkedList<>();  // Queue for BFS, stores nodes to visit
    boolean[] isVist = new boolean[v];      // Array to track visited nodes
    
    // Mark the start node as visited and add it to the queue
    q.offer(node);
    isVist[node] = true;

    // Start processing nodes in the queue
    while (!q.isEmpty()) {
        int currNode = q.poll();  // Dequeue a node from the front of the queue
        res.add(currNode);        // Add the current node to the result list

        // Explore all the neighbors of the current node
        for (int nextNode : adj.get(currNode)) {
            // If the neighbor hasn't been visited, mark it and add it to the queue
            if (!isVist[nextNode]) {
                q.add(nextNode);     // Enqueue the neighbor for future processing
                isVist[nextNode] = true; // Mark the neighbor as visited
            }
        }
    }
    
    return res; 
 } // Return the final BFS traversal result
    public static void main(String[] args) {
        graphtraversal gf=new graphtraversal();
        int v=5;
        List<List<Integer>> adj=new ArrayList<>();

        for(int  i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }

        // For an undirected graph example:
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(1).add(3);
        adj.get(3).add(1);
        adj.get(1).add(4);
        adj.get(4).add(1);
       
        List<Integer> res=gf.dfs(v, adj, 0);
        System.out.println(" This is for the undirected graph"+res);

        int v1 = 4; // Number of vertices in the directed graph
        List<List<Integer>> adj1 = new ArrayList<>();
        
        // Initialize adjacency list for a directed graph (example)
        for (int i = 0; i < v1; i++) {
            adj1.add(new ArrayList<>());
        }
        
        // Directed graph example:
        adj1.get(0).add(1);
        adj1.get(1).add(2);
        adj1.get(1).add(3);
        adj1.get(3).add(2);
        adj1.get(3).add(0);

        // Start DFS from node 0
        List<Integer> res1 = gf.dfs(v1, adj1, 0);
        System.out.println("This is for the directed graph: " + res1);

        // BFS metho calll

        System.out.println("This is for the BFS traversal method of unDirected Graph!.."+gf.bfs(v, adj, 0));
        
        System.out.println("This is for the BFS traversal method of Directed Graph!.."+gf.bfs(v1, adj1, 0));
}
}