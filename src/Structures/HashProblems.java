package Structures;

public class HashProblems {
    public static void main(String[] args) {
        String s = "Renga";
        String t = "VRengsRenga";
        System.out.println(karpRabin(s, t));
    }

    // Karp Rabin substring matching algorithm

    // Custom hashing function
    static int prime = 101;
    static double customHashFunction(String str) {
        double hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash +=  str.charAt(i) * Math.pow(prime,i);
        }
        return hash;
    }
    // Rolling hash updating
    static double updateHash(double hash, char oldChar, char newChar, int length) {
        double newHash = (hash - oldChar) / prime;
        newHash += newChar * Math.pow(prime, length - 1);
        return newHash;
    }
    static boolean karpRabin(String s, String t) {
        double hashA = customHashFunction(s);
        int aLength = s.length();

        double hashB = customHashFunction(t.substring(0, aLength));
        int subStart = 0;

        for (int i = aLength; i < t.length(); i++) {
            if (hashB == hashA) {
                return true;
            }
            hashB = updateHash(hashB, t.charAt(subStart), t.charAt(i), aLength );
            subStart++;
        }
        // Checking for the last index which is left out
        return hashA == hashB;
    }


}
