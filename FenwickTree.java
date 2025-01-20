class Fenwick {
    int n;
    int[] fen;

    // Constructor to initialize Fenwick Tree with size n
    Fenwick(int n) {
        this.n = n;
        fen = new int[n + 1];  // Fenwick tree is 1-based, so we allocate size n + 1
    }

    // Update the Fenwick Tree at index id (1-based)
    void update(int id, int val) {
        while (id <= n) {
            fen[id] += val;
            id += (id & -id);  // Move to the next index
        }
    }

    // Query the sum from index 1 to id (1-based)
    int query(int id) {
        int ans = 0;
        while (id > 0) {
            ans += fen[id];
            id -= (id & -id);  // Move to the parent index
        }
        return ans;
    }

    // Query the sum from index left to right (1-based)
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    // Reupdate the value at index `id` to `val` (this updates the tree)
    public void reupdate(int id, int val) {
        // Remove the old value by subtracting the current value
        update(id, -fen[id]);
        // Set the new value at the index
        update(id, val);
    }
}

public class FenwickTree {
    public static void main(String[] args) {
        int n = 5;
        Fenwick fen = new Fenwick(n);  // Fenwick tree of size n
        int[] arr = {1, 2, 3, 4, 5};  // Array of values (index 1 to 5)

        // Update Fenwick Tree with values from arr
        for (int i = 1; i <= n; i++) {
            fen.update(i, arr[i - 1]);  // Update using 1-based index for Fenwick Tree
        }

        // Query the sum from index 2 to 4
        System.out.println("The range sum query is: " + fen.rangeQuery(2, 4));  // Expected output: 9 (2 + 3 + 4)

        // Reupdate the value at index 4 to 5 (from 4 to 5)
        fen.reupdate(4, 5);
        
        // Query the sum from index 1 to 5 after the update
        System.out.println("After reupdate, the sum from index 1 to 5 is: " + fen.query(5));  // Expected output: 15 (1 + 2 + 3 + 5 + 4)
    }
}
