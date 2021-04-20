import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {

    //Define the default hashtable size. Size should be power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    //Defime maximum hash-table size. 2^30 is the same as 1 << 30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    //Current hashtable size. Should be power of two.
    private int capacity;

    //Default load-factor to ensure capacity
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    //leoad factor used in hashtable
    private float loadFatctorThreshold;

    private int size = 0;

    private LinkedList<Entry<K, V>>[] table;

    public MyHashMap() {
        this(MAXIMUM_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int capacity) {
        this(capacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFatctorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        else {
            this.capacity = initialCapacity;
        }
        this.loadFatctorThreshold = loadFatctorThreshold;
        table = new LinkedList[capacity];

    }

    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    @Override
    public V put(K key, V value) {

        if (get(key) != null) {
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];

            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
            }
        }

        if (size >= capacity * loadFatctorThreshold) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }

        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<>();
        }

        table[bucketIndex].add(new Entry<>(key, value));

        size++;

        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];

            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    bucket.remove();
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];

            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];

                for (Entry<K, V> entry : bucket) {
                    set.add(entry);
                }
            }
        }
        return set;
    }

    private void rehash() {
        capacity <<= 1; // Same as *=2
        table = new LinkedList[capacity];

        size = 0;

        for (Entry<K ,V> entry : entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    private int supplementalHash(int hashCode) {
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }
}
