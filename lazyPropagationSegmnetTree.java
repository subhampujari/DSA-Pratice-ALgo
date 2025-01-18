class SGT {
    int seg[];
    int lazy[];
    int n;

    public SGT(int n) {
        this.n = n;
        seg = new int[4 * n];
        lazy = new int[4 * n];
    }

    // Building the segment tree
    public void build(int ind, int low, int high, int[] arr) {
        if (low == high) {
            seg[ind] = arr[low];
            return;
        }
        int mid = (low + high) >> 1;
        build(2 * ind + 1, low, mid, arr);      // Left subtree
        build(2 * ind + 2, mid + 1, high, arr); // Right subtree
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2];  // Store the sum of the two children
    }

    // Update the range [l, r] with value val
    public void update(int ind, int low, int high, int l, int r, int val) {
        // Apply lazy propagation if needed
        if (lazy[ind] != 0) {
            seg[ind] += (high - low + 1) * lazy[ind]; // Apply pending updates
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind]; // Propagate laziness to left child
                lazy[2 * ind + 2] += lazy[ind]; // Propagate laziness to right child
            }
            lazy[ind] = 0; // Reset the lazy value at current node
        }

        // No overlap
        if (r < low || high < l) {
            return;
        }

        // Complete overlap
        if (low >= l && high <= r) {
            seg[ind] += (high - low + 1) * val;
            if (low != high) {
                lazy[2 * ind + 1] += val;  // Propagate laziness to left child
                lazy[2 * ind + 2] += val;  // Propagate laziness to right child
            }
            return;
        }

        // Partial overlap
        int mid = (low + high) >> 1;
        update(2 * ind + 1, low, mid, l, r, val);     // Left child
        update(2 * ind + 2, mid + 1, high, l, r, val); // Right child
        seg[ind] = seg[2 * ind + 1] + seg[2 * ind + 2]; // Update the current node after updating children
    }

    // Query the range [l, r]
    public int query(int ind, int low, int high, int l, int r) {
        // Apply lazy propagation if needed
        if (lazy[ind] != 0) {
            seg[ind] += (high - low + 1) * lazy[ind]; // Apply pending updates
            if (low != high) {
                lazy[2 * ind + 1] += lazy[ind]; // Propagate laziness to left child
                lazy[2 * ind + 2] += lazy[ind]; // Propagate laziness to right child
            }
            lazy[ind] = 0; // Reset the lazy value at current node
        }

        // No overlap
        if (r < low || high < l) {
            return 0;  // Return 0 for sum queries (appropriate identity element)
        }

        // Complete overlap
        if (low >= l && high <= r) {
            return seg[ind];
        }

        // Partial overlap
        int mid = (low + high) >> 1;
        int left = query(2 * ind + 1, low, mid, l, r);   // Left subtree
        int right = query(2 * ind + 2, mid + 1, high, l, r); // Right subtree
        return left + right;  // Return the sum of both subtrees
    }
}

public class lazyPropagationSegmnetTree {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,9,7,8};
        int n=arr.length;

        SGT sgt=new SGT(n);
        System.out.println("the Segment tree is build :-");
        sgt.build(0, 0, n-1, arr);
         
        System.out.println("the query range sum is : "+sgt.query(0, 0, n-1, 2, 4));

        System.out.println("the queery update is : ");
        sgt.update(0, 0, n-1, 3, 6, 38);
        System.out.println("the query range sum is : "+sgt.query(0, 0, n-1, 1, n-1));

    }
}
