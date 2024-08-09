package ObjectOrientedProgramming;

import java.util.Objects;

public class OOPOne {
    public static void main(String[] args) {
        // Only declaring, not initialized(creating memory in heap)
        Student kishore;

        // Initializing -> new keyword is used to dynamically allocate memory in runtime
        kishore = new Student();

        // kishore is the reference variable pointing to the memory address of that object

        // dot operator
        kishore.name = "kishore";
        kishore.rollNum = 7;
        System.out.println(kishore);
    }

    static class Student {
        String name;
        int rollNum;




    }
}
