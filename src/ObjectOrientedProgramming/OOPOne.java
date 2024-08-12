package ObjectOrientedProgramming;

import java.util.Objects;

public class OOPOne {
    public static void main(String[] args) {
        // Only declaring, not initialized(creating memory in heap)
        Student kishore;

        // Initializing -> new keyword is used to dynamically allocate memory in runtime
        kishore = new Student("Kishore", 21);

        // kishore is the reference variable pointing to the memory address of that object

        System.out.println(kishore.rollNum);

        // dot operator
        kishore.name = "kishore";
        kishore.rollNum = 7;
    }

    static class Student {
        String name;
        int rollNum;

        Student(String _name, int _rollNum) {
            name = _name;
            rollNum = _rollNum;
        }
    }
}
