package ObjectOrientedProgramming;

import javax.print.attribute.standard.MediaSize;
import java.util.Objects;

public class OOPOne {
    public static void main(String[] args) {
        // Only declaring, not initialized(creating memory in heap)
        final Student kishore;

        // Initializing -> new keyword is used to dynamically allocate memory in runtime
        kishore = new Student("Kishore", 21);

        // kishore is the reference variable pointing to the memory address of that object


        Student kathir = new Student(kishore);

        // dot operator
        kishore.name = "kishore";
        kishore.rollNum = 7;

        System.out.println(kishore.name);
    }

    final static class Student {
        String name;
        int rollNum;

        Student(String _name, int _rollNum) {
            name = _name;
            rollNum = _rollNum;
        }

        Student (Student other) {
            this.name = other.name;
            this.rollNum = other.rollNum;
        }
    }
}
