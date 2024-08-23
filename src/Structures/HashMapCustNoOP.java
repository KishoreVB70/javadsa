package Structures;

public class HashMapCustNoOP {
    Pair[] pairs = new Pair[5];
    public HashMapCustNoOP() {
    }

    void add(String key, int value) {
        Pair pair = new Pair(key, value);
        int hashIndex =  Math.abs(key.hashCode() % pairs.length);
        pairs[hashIndex] = pair;
    }

    int get(String key) {
        int hashIndex =  Math.abs(key.hashCode() % pairs.length);
        if (pairs[hashIndex] != null) {
            return pairs[hashIndex].value;
        }
        return -1;
    }

    private class Pair {
        String key;
        int value;

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
