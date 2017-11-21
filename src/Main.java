import java.util.Random;

/**
 * Process class
 * Emulate the process as a object with specific data variables
 */

class Process {
    int id = 0;
    int burst  = 0;
    int arrival = 0;
    int priority = 0;

    // Parametrized Constructor
    Process(int identifier, int aBurst, int anArrival, int aPriority ){
        id = identifier;
        burst  = aBurst;
        arrival = anArrival;
        priority = aPriority;
    }

    // Copy Constructor
    Process(Process p) {
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



/* Priority Scheduler without Pre-emption */

class Scheduler {
    final static int NUMBER_RUN_TIMES = 5;

    final static int CPU_BURST_TIME_MIN = 1;
    final static int CPU_BURST_TIME_MAX = 100;

    final static int ARRIVAL_TIME_MIN = 1;
    final static int ARRIVAL_TIME_MAX = 1000;

    final static int PRIORITY_MIN = 1;
    final static int PRIORITY_MAX = 10;


    /**
     * Static simulateWith method
     * @param numberOfProcesses - the number of processes to simulate
     *
     * Simulate the scheduler with given number of processes and output results
     * to the system output stream
     */
    static void simulateWith(int numberOfProcesses) {
        // array of processes to hold
        Process[] processList = new Process[numberOfProcesses];

        float waiting = 0;
        float turnaround = 0;

        // instantiate all processes
        for(int k = 0; k < NUMBER_RUN_TIMES; k++) {
            Random rng = new Random(k); // initialize a random generator with different seed

            // create N number of processes
            for (int i = 0; i < numberOfProcesses; i++) {
                int CPUBurst = rng.nextInt(CPU_BURST_TIME_MAX - CPU_BURST_TIME_MIN + 1) + CPU_BURST_TIME_MIN;
                int arrivalTime = rng.nextInt(ARRIVAL_TIME_MAX - ARRIVAL_TIME_MIN + 1) + ARRIVAL_TIME_MIN;
                int priority = rng.nextInt(PRIORITY_MAX - PRIORITY_MIN + 1) + PRIORITY_MIN;

                processList[i] = new Process(i, CPUBurst, arrivalTime, priority);
            }

            // sort by arrival time
            for (int i = 0; i < numberOfProcesses; i++) {
                for (int j = 0; j < numberOfProcesses; j++) {
                    if (processList[i].arrival < processList[j].arrival) {
                        Process temp = new Process(processList[i]);
                        processList[i] = processList[j];
                        processList[j] = temp;
                    }
                }
            }


            // initialize time to first arrived Process time
            int headIndex = 0;
            int tailIndex = numberOfProcesses - 1;
            int time = processList[headIndex].arrival;

            /* Sorting Algorithm
            * After sorting by arrival time, headIndex and tailIndex are used to
            * indicate the "sub-array" that the CPU is sorting by priority
            * with respect to the time that has elapsed. */

            while (headIndex != numberOfProcesses -1) {

                // find tail
                for (int i = 0; i < numberOfProcesses; i++) {
                    if (processList[i].arrival <= time) {
                        tailIndex = i;
                    } else {
                        break;
                    }
                }
                // sort by Priority (lower number means higher priority)
                for (int i = headIndex; i <= tailIndex; i++) {
                    for (int j = headIndex; j <= tailIndex; j++) {
                        if (processList[i].priority < processList[j].priority) {
                            Process temp = new Process(processList[i]);
                            processList[i] = processList[j];
                            processList[j] = temp;
                        }
                    }
                }
                time = time + processList[headIndex].burst; // timer after burst
                headIndex = headIndex + 1; // increment head
            }

            time = processList[0].arrival;

            // calculate wait time and turnaround time
            for (int i = 0; i < numberOfProcesses; i++)
            {
                waiting = waiting + time - processList[i].arrival;
                time = time + processList[i].burst;
                turnaround = turnaround + time - processList[i].arrival;
            }

            turnaround = turnaround / numberOfProcesses;
            waiting = waiting / numberOfProcesses;

            // output the results
            System.out.print("|\t");
            System.out.print(numberOfProcesses + "\t");
            System.out.print("|");
            System.out.print("\t" + (k+1) + "\t");
            System.out.print("\t|");
            System.out.print("\t" + waiting + " ns \t");
            System.out.print("|");
            System.out.print("\t" + turnaround + " ns\t");
            System.out.println("|");
        }
    }

}



public class Main {

    static void printTableHeader() {
        System.out.print("|\tN\t|");
        System.out.print("\tRun Number\t");
        System.out.print("|");
        System.out.print("\tWaiting Time \t|");
        System.out.println("\tTurnaround Time\t|");
    }

    public static void main(String[] args)
    {
        printWelcomeMessage();


        // output the table header
        printTableHeader();

        // simulate scheduler with 50 processes
        Scheduler.simulateWith(50);

        // simulate scheduler with 100 processes
        Scheduler.simulateWith(100);

        // simulate scheduler  with 150 processes
        Scheduler.simulateWith(150);

        System.out.println(); // create a new line

        // parse arguments
        if (args.length == 0) {
            System.out.println("\tYou can pass arguments to this program to run tests with custom number of processes.\n\tUsage: java -jar this_program_name.jar N where N is the number\n");
        } else {

            int testCaseNumber = 1; // counter of test cases

            for (String argument : args) {

                int numberOfProcesses = 0;
                try { // parse the argument and convert it to integer
                    numberOfProcesses = Integer.parseInt(argument);
                } catch (NumberFormatException e) {
                    System.out.println("\tWrong argument! Just type a number");
                    continue;
                }

                // validate the argument
                if (numberOfProcesses < 0) {
                    System.out.println("\tWrong argument! You cannot have negative number of processes!\n");
                    continue;
                }

                // print
                System.out.println("\nTest Case " + testCaseNumber++);

                printTableHeader();
                Scheduler.simulateWith(numberOfProcesses);
            }
        }
    } // end main method

    private static void printWelcomeMessage() {
        System.out.println("\nProject: Simulation of Priority Scheduler without Preemption.");
        System.out.println("\tMade by Matthew Chew, Andrew Hu, Pranav Patil, & Dmitry Sokolov.");
        System.out.println("\tCMPE 142-03 Fall 2017.\n");
    }
}

