
/**
 * Process class
 * Emulate the process as a object with specific data variables
 */

public class Process {
    int id = 0;
    int burst  = 0;
    int arrival = 0;
    int priority = 0;

    public Process(int identifier, int aBurst, int anArrival, int aPriority ){
        id = identifier;
        burst  = aBurst;
        arrival = anArrival;
        priority = aPriority;
    }

    public Process(Process p) {
        id = p.id;
        burst = p.burst;
        arrival = p.arrival;
        priority = p.priority;
    }

    void print(){
        System.out.print("Id: " + id);
        System.out.print("\tBurst time: " + burst);
        System.out.print("\tArrival Time: " + arrival);
        System.out.println("\tPriority level: " + priority);
    }
}
