package Structures;

public class MainClassStruct {
    public static void main(String[] args) {
        HashMapCustNoOP map = new HashMapCustNoOP();
        map.add("Puffy", 21);
        map.add("Peach", 26);
        System.out.println(map.get("Puffy"));


    }
}
