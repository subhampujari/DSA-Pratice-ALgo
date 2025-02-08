public class Trie {

    // TreeNode class represents each node in the Trie
    static class TreeNode {
        TreeNode[] children;
        boolean eow; // End of Word flag
        
        TreeNode() {
            children = new TreeNode[26]; // 26 letters in the English alphabet
            eow = false; // Initially, no word ends at this node
        }
    }

    public TreeNode root;

    // Constructor to initialize the root node
    Trie() {
        root = new TreeNode();
    }

    // Method to insert a word into the Trie
    public void insert(String word) {
        TreeNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a'; // Calculate index (0 for 'a', 1 for 'b', etc.)
            if (node.children[index] == null) {
                node.children[index] = new TreeNode();
            }
            node = node.children[index]; // Move to the next node
        }
        node.eow = true; // Mark the end of the word
    }

    // Method to search for a word in the Trie
    public boolean search(String word) {
        TreeNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false; // Word not found
            }
            node = node.children[index]; // Move to the next node
        }
        return node.eow; // Return true if it's a complete word, false if it's a prefix
    }

    // Method to check if there is any word that starts with the given prefix
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false; // Prefix not found
            }
            node = node.children[index]; // Move to the next node
        }
        return true; // Prefix found
    }

    // Suggestion: A method to delete a word from the Trie (if needed)
    // You can delete a word by unmarking its end-of-word flag and cleaning up nodes if necessary.
    public boolean delete(String word) {
        return deleteHelper(root, word, 0);
    }

    // Helper method for delete function
    private boolean deleteHelper(TreeNode node, String word, int index) {
        if (index == word.length()) {
            if (!node.eow) {
                return false; // Word doesn't exist
            }
            node.eow = false; // Unmark the end of the word
            return nodeHasNoChildren(node); // If the node has no children, return true
        }

        int childIndex = word.charAt(index) - 'a';
        TreeNode child = node.children[childIndex];
        if (child == null) {
            return false; // Word doesn't exist
        }

        boolean canDeleteChild = deleteHelper(child, word, index + 1);

        if (canDeleteChild) {
            node.children[childIndex] = null; // Delete child node
            return !node.eow && nodeHasNoChildren(node); // If node has no children and is not an end of word
        }
        return false;
    }

    // Helper method to check if a node has no children
    private boolean nodeHasNoChildren(TreeNode node) {
        for (TreeNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    // Main method to test the Trie implementation
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words into the Trie
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        // Test search functionality
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app")); // true
        System.out.println("Search 'ban': " + trie.search("ban")); // false

        // Test startsWith functionality
        System.out.println("Starts with 'ban': " + trie.startsWith("ban")); // true
        System.out.println("Starts with 'bat': " + trie.startsWith("bat")); // false

        // Test delete functionality
        System.out.println("Delete 'apple': " + trie.delete("apple")); // true
        System.out.println("Search 'apple' after deletion: " + trie.search("apple")); // false
    }
}
