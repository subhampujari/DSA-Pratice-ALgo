import java.util.*;

class Disjoint {
    // List to store the rank of each node
    List<Integer> rank = new ArrayList<>();
    // List to store the parent of each node
    List<Integer> parent = new ArrayList<>();
    // List to store the size of each node's tree for union by size
    List<Integer> size = new ArrayList<>();

    // Constructor to initialize the disjoint set
    Disjoint(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0); // Initialize rank to 0 for all nodes
            parent.add(i); // Initially, each node is its own parent
            size.add(1); // Initially, the size of each node's tree is 1
        }
    }

    // Find the parent of a node with path compression
    public int findParent(int node) {
        // Base case: if the node is the root, return it
        if (node == parent.get(node)) {
            return node;
        }
        // Recursively find the parent, with path compression
        int ulp = findParent(parent.get(node));
        parent.set(node, ulp); // Path compression: update the parent to the root
        return parent.get(node);
    }

    // Union by Rank (Attach the smaller tree under the larger one based on rank)
    public void unionByRank(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) return; // If they are already in the same set, do nothing

        // Union by rank
        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU + 1); // Increase the rank of the root
        }
    }

    // Union by Size (Attach the smaller tree under the larger one based on size)
    public void unionBySize(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        if (ulp_u == ulp_v) return; // If they are already in the same set, do nothing

        // Union by size: Attach the smaller tree under the larger one
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v); // Make ulp_v the parent of ulp_u
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v)); // Update the size of the new root
        } else {
            parent.set(ulp_v, ulp_u); // Make ulp_u the parent of ulp_v
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v)); // Update the size of the new root
        }
    }
}

public class disjoinset {
    public static void main(String[] args) {
        // Initialize disjoint set with 7 nodes
        Disjoint d = new Disjoint(7);
        
        // Perform union operations using union by rank
        d.unionByRank(1, 2);
        d.unionByRank(2, 3);
        d.unionByRank(4, 5);
        d.unionByRank(6, 7);
        d.unionByRank(5, 6);
        
        // Check if nodes 3 and 7 have the same parent (i.e., are in the same set)
        if (d.findParent(3) == d.findParent(7)) {
            System.out.println("Same parent");
        } else {
            System.out.println("Not same parent");
        }

        // Perform union operation between nodes 3 and 7 using union by rank
        d.unionByRank(3, 7);
        // Check again if nodes 3 and 7 have the same parent after union
        if (d.findParent(3) == d.findParent(7)) {
            System.out.println("Same parent");
        } else {
            System.out.println("Not same parent");
        }

        // Now perform union by size
        d.unionBySize(1, 4); // Union of nodes 1 and 4
        d.unionBySize(2, 5); // Union of nodes 2 and 5

        // Check if nodes 1 and 5 have the same parent
        if (d.findParent(1) == d.findParent(5)) {
            System.out.println("Same parent (Union by size)");
        } else {
            System.out.println("Not same parent (Union by size)");
        }
    }
}
