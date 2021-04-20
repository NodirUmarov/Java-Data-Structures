import java.util.Set;

public interface MyMap<K, V> {

    void clear();

    V put(K key, V value);

    void remove(K key);

    int size();

    V get(K key);

    Set<Entry<K, V>> entrySet();

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
