package Structures;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapCust {

    ArrayList<LinkedList<Pair>> pairs;

    public HashMapCust() {
        pairs = new ArrayList<LinkedList<Pair>>(10);
    }

    void add(String key, int value) {
        Pair pair = new Pair(key, value);

        int hashIndex =  Math.abs(key.hashCode() % pairs.size());

        LinkedList<Pair> list = pairs.get(hashIndex);
        // Create linked list, if doesn't exist
        if (list == null) {
            list = new LinkedList<>();
        }

        // If it is already occupied, add it into the linked list
        list.add(pair);
    }

    int get(String key) {
        int hashIndex =  Math.abs(key.hashCode() % pairs.size());
        LinkedList<Pair> list = pairs.get(hashIndex);
        // Create linked list, if doesn't exist
        if (list == null) {
            return -1;
        }

        for (int i = 0; i < list.size(); i++) {
            if ( (list.get(i).key).equals(key) ) {
                return list.get(i).value;
            }
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
