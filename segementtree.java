class SGTtree {
    int seg[];
    int n;

    public SGTtree(int n) {
        this.n = n;
        seg = new int[4 * n]; // Allocate enough space for segment tree
    }

    // Building the segment tree
    void build(int ind, int low, int high, int arr[]) {
        if (low == high) {
            seg[ind] = arr[low];  // If we reached a leaf node
            return;
        }
        int mid = low + (high - low) / 2;
        build(2 * ind + 1, low, mid, arr);      // Left subtree
        build(2 * ind + 2, mid + 1, high, arr); // Right subtree
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);  // Store the minimum of the two children
    }

    // Querying the range [l, r]
    int query(int ind, int low, int high, int l, int r) {
        // No overlap
        if (r < low || high < l) {
            return Integer.MAX_VALUE;
        }
        // Complete overlap
        if (low >= l && high <= r) {
            return seg[ind];
        }
        // Partial overlap
        int mid = low + (high - low) / 2;
        int left = query(2 * ind + 1, low, mid, l, r);   // Left subtree
        int right = query(2 * ind + 2, mid + 1, high, l, r); // Right subtree
        return Math.min(left, right);  // Return the minimum of both halves
    }

    // Update the value at index `i` with `val`
    void update(int ind, int low, int high, int i, int val) {
        if (low == high) {
            seg[ind] = val;  // Leaf node
            return;
        }
        int mid = low + (high - low) / 2;
        if (i <= mid) {
            update(2 * ind + 1, low, mid, i, val);  // Left subtree
        } else {
            update(2 * ind + 2, mid + 1, high, i, val);  // Right subtree
        }
        seg[ind] = Math.min(seg[2 * ind + 1], seg[2 * ind + 2]);  // Update current node
    }
}

public class SegmentTree {
    public static void main(String[] args) {
        int arr[] = {1, 3, 2, 7, 9, 11};
        int n = arr.length;

        // Create the Segment Tree
        SGTtree tree = new SGTtree(n);
        
        // Build the tree
        tree.build(0, 0, n - 1, arr);

        // Query range [1, 4]
        System.out.println("Minimum in range [1, 4]: " + tree.query(0, 0, n - 1, 1, 4));

        // Update index 3 with value 5
        tree.update(0, 0, n - 1, 3, 5);

        // Query again after the update
        System.out.println("Minimum in range [1, 4] after update: " + tree.query(0, 0, n - 1, 1, 4));
    }
}
