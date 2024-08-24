package Structures;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class HuffmanCoder {

    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    public HuffmanCoder(String feeder) throws Exception{
        // 1 -> create hash map
        HashMap<Character, Integer> costMap = new HashMap<>();
        for (int i = 0; i < feeder.length(); i++) {
            costMap.put(feeder.charAt(i), costMap.getOrDefault(feeder.charAt(i), 0)+1);
        }

        // 2 -> create min heap with nodes
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        Set<Map.Entry<Character, Integer>> entries =  costMap.entrySet();

        for(Map.Entry<Character, Integer> entry: entries) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.add(node);
        }

        // 3 -> remove 2 and create a new node
        while (minHeap.size() > 1) {
            // Get the two nodes
            Node remove1 =  minHeap.remove();
            Node remove2 =  minHeap.remove();

            // Merge the new nodes
            Node newNode = new Node('\0', remove1.cost + remove2.cost);

            // Lowest frequency in the right
            newNode.right = remove1;
            // Highest frequency in the left
            newNode.left = remove2;
            minHeap.add(newNode);
        }

        Node head = minHeap.remove();

        // 4 -> Populate the encoder and decoder
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
        initEncoderDecoder(head, "");
    }

    public String decode(String inp) {
        StringBuilder op = new StringBuilder();

        int inpIndex = 0;
        while (inpIndex < inp.length()) {
            String key = inp.substring(0,1);
            if (decoder.containsKey(key)) {
                op.append(decoder.get(key));
                inpIndex++;
                key = inp.substring(inpIndex, inpIndex+1);
            } else {
                key += inp.charAt(++inpIndex);
            }
        }

        return op.toString();
    }

    public String encode(String inp) {
        StringBuilder op = new StringBuilder();

        for (int i = 0; i < inp.length(); i++) {
            op.append(encoder.get(inp.charAt(0)));
        }

        return op.toString();
    }

    private void initEncoderDecoder(Node node, String crypt) {
        if (node == null) {
            return;
        }

        if (node.data != '\0') {
            this.encoder.put(node.left.data, crypt);
            this.decoder.put(crypt, node.left.data);
        }

        initEncoderDecoder(node.left, crypt + "0");
        initEncoderDecoder(node.right, crypt + "1");

    }

    private class Node implements Comparable<Node> {
        Character data;
        int cost;
        Node left;
        Node right;

        Node(Character data, int cost) {
            this.cost = cost;
            this.data = data;
        }

        @Override
        public int compareTo(Node o) {
            return  this.cost - o.cost;
        }
    }
}
