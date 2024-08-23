package Structures;

public class HashProblems {
    public static void main(String[] args) {
        String a = "Renga";
        String b = "VRengaVindz";
        System.out.println(karpRabin(a, b));
    }

    // Karp Rabin substring matching algorithm -> ez pz
    static boolean karpRabin(String a, String b) {
        int hashA = a.hashCode();
        int aLength = a.length();
        String sub = b.substring(0,a.length());
        for (int i = 1; i < b.length() - aLength; i++) {
            int hashB = sub.hashCode();

            // Hash is same
            if (hashB == hashA) {
                boolean breaker = false;

                // Check string is same, character by character
                for (int j = 0; j < aLength; j++) {
                    if (a.charAt(j) != sub.charAt(j)) {
                        breaker = true;
                        break;
                    }
                }
                if (!breaker) {
                    return true;
                }
            }

            sub = b.substring(1,aLength + 1);
        }
        return false;
    }

    static boolean karpRabin1(String a, String b) {
        int hashA = a.hashCode();
        StringBuilder sub = new StringBuilder(b.substring(0,a.length()));
        for (int i = a.length(); i < b.length(); i++) {
            int hashB = sub.hashCode();

            // Hash is same
            if (hashB == hashA) {
                boolean breaker = false;

                // Check string is same, character by character
                for (int j = 0; j < a.length(); j++) {
                    if (a.charAt(j) != sub.charAt(j)) {
                        breaker = true;
                        break;
                    }
                }
                if (!breaker) {
                    return true;
                }
            }

            sub.deleteCharAt(0);
            sub.append(b.charAt(i));
        }
        return false;
    }
}
