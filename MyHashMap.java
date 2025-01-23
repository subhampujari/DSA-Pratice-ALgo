import java.util.LinkedList;

public class MyHashMap<K, V> {
    
    // The array size for the hash map
    private static final int SIZE = 16;
    
    // Array of LinkedLists to handle collisions
    private LinkedList<Entry<K, V>>[] table;

    // Entry class to store key-value pairs
    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor to initialize the table
    public MyHashMap() {
        table = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to get the index for a given key
    private int getIndex(K key) {
        return key.hashCode() % SIZE;
    }

    // Put method to insert a key-value pair
    public void put(K key, V value) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        // Check if the key already exists in the bucket
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;  // Update value if key is found
                return;
            }
        }
        
        // If key doesn't exist, add a new entry
        bucket.add(new Entry<>(key, value));
    }

    // Get method to retrieve a value for a given key
    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        
        return null; // Return null if the key is not found
    }

    // Remove method to delete a key-value pair
    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        bucket.removeIf(entry -> entry.key.equals(key));
    }

    // ContainsKey method to check if a key exists in the map
    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;  // Key found
            }
        }
        
        return false; // Key not found
    }
    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            if (!table[i].isEmpty()) {
                System.out.println("Index " + i + ": " + table[i]);
            }
        }
    }

    // Main method to test the MyHashMap
    public static void main(String[] args) {
        MyHashMap<String, Integer> myMap= new MyHashMap<>();

        System.out.println("This class is an implementation of the Map Data Structure!.....");


        myMap.put("Subham", 102020222);
        myMap.put("Nigam", 292993);
        myMap.put("Rinku", 2020202);
        myMap.put("Banita", 393993333);
        myMap.put("Murali", 220202393);
        myMap.put("Sweet", 2333322);

        System.out.println("Value for 'Subham': " + myMap.get("Subham"));
        System.out.println("Contains 'Subham': " + myMap.containsKey("Subham"));
        System.out.println("Contains 'bala': " + myMap.containsKey("bala"));

        // Before removing
        System.out.println("Map contents before removal:");
        myMap.printMap();

        myMap.remove("Rinku"); // 'Rinku' (case-sensitive) needs to be used

        // After removing
        System.out.println("Map contents after removal:");
        myMap.printMap();
    }
}
