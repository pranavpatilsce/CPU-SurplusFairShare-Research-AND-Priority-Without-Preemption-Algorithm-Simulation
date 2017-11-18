package com.company;
import java.util.Random;
//Random rndx = new Random(1);
//rndx.nextInt()

//priority scheduler w/o premption (queue)
class process{
    int id = 0;
    int burst  = 0;
    int arrival = 0;
    int priority = 0;

    process(int i, int b, int a, int p ){
        id = i;
        burst  = b;
        arrival = a;
        priority = p;
    }
    void print(){
        System.out.println("Id: " + id);
        System.out.println("\tBurst time: " + burst);
        System.out.println("\tArrival Time: " + arrival);
        System.out.println("\tPriority level: " + priority);
        System.out.println("\n");
    }
}

class scheduler{
    int N = 0;
    int time = 0;
    int waiting = 0; //average waiting time
    int turnaround = 0; //average turnaround time

    scheduler(int number) {
        N = number;
        process[] processList;
        processList = new process[N]; //makes 'p' process objects
        //loop to instantiate all processes
        Random rng = new Random(1);
        for (int i = 0; i < N ; i++){
            int id=i;
            int b=rng.nextInt(101);
            int a=rng.nextInt(1001);
            int p=rng.nextInt(11);

            processList[i] = new process(id,b,a,p);
            processList[i].print();
        }
    }
}


public class Main {

    public static void main(String[] args) {
        scheduler test_scheduler = new scheduler(50);
        
        //report waiting time
        //report turnaround time
    }
}
