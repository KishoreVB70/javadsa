package Structures;

public class StackAndQueueProblems {

    public static void main(String[] args) {
        QueueCircularCust qu = new QueueCircularCust(3);
        qu.add(1);
        qu.display();
        qu.remove();
        qu.display();
        qu.add(2);
        qu.display();
        qu.add(3);
        qu.display();
        qu.add(4);
        qu.display();
        qu.add(4);
        qu.display();
        qu.remove();
        qu.display();
        qu.add(5);
        qu.display();

    }




}

