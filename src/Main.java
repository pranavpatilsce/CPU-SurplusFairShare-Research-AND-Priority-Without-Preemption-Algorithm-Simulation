
import java.util.Random;

/* Priority Scheduler without Pre-emption */

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
    process( process p) {
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
//        System.out.println("\n");
    }
}

class scheduler{
    int N = 0;
    int time = 0;
    int waiting = 0; //Todo: Find average waiting time
    int turnaround = 0; //Todo: Find average turnaround time
    int head = 0;
    int tail = 0;

    scheduler(int number) {
        // Todo: Loop run number with different seeds
        // Todo: Iterate for N = 100 and N = 500

        N = number;
        tail = number - 1;
        process[] processList;
        processList = new process[N];
        float waiting = 0;
        float turnaround = 0;

        //Instantiate all processes
        for(int k = 0; k < 5; k++) {
            Random rng = new Random(k);
            for (int i = 0; i < N; i++) {
                int id = i;
                int b = rng.nextInt(101);
                int a = rng.nextInt(1001);
                int p = rng.nextInt(11);

                processList[i] = new process(id, b, a, p);
            }
            //Sort by Arrival
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (processList[i].arrival < processList[j].arrival) {
                        process temp = new process(processList[i]);
                        processList[i] = processList[j];
                        processList[j] = temp;
                    }
                }
            }
            //Initialize time to first arrived process time
            time = processList[head].arrival;
            /* Sorting Algorithm
            * After sorting by arrival time, head and tail are used to
            * indicate the "subarray" that the CPU is sorting by priority
            * with respect to the time that has elapsed. */

            while (head != N-1) {
                //Find tail
                for (int i = 0; i < N; i++) {
                    if (processList[i].arrival <= time) {
                        tail = i;
                    } else {
                        break;
                    }
                }
                //Sort by Priority
                for (int i = head; i <= tail; i++) {
                    for (int j = head; j <= tail; j++) {
                        if (processList[i].priority < processList[j].priority) {
                            process temp = new process(processList[i]);
                            processList[i] = processList[j];
                            processList[j] = temp;
                        }
                    }
                }
                time = time + processList[head].burst; // Timer after burst
                head = head + 1; //Increment head
            }

            time = processList[0].arrival;

            //calculate wait time and turnaround time
            for (int i = 0; i < N; i++)
            {
                waiting = waiting + time - processList[i].arrival;
                time = time + processList[i].burst;
                turnaround = turnaround + time - processList[i].arrival;
            }

            turnaround = turnaround / N;
            waiting = waiting / N;

            //Print Sorted
            // Todo: Format output as table
//            for (int i = 0; i < N; i++) {
//                processList[i].print();
//            }
            //Table Output
            System.out.print("|\t");
            System.out.print(N + "\t");
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

    public static void main(String[] args)
    {

        System.out.print("|\t");
        System.out.print("N");
        System.out.print("\t|");
        System.out.print("\tRun Number\t");
        System.out.print("|");
        System.out.print("\tWaiting Time \t|");
        System.out.println("\tTurnaround Time\t|");
        scheduler test_scheduler = new scheduler(50);
        scheduler test2 = new scheduler( 100);
        scheduler test3 = new scheduler( 150);

        int testCaseNumber = 1;
        for (String s: args) {
            int numberOfProcesses = 0;
            try {
                numberOfProcesses = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Wrong argument!");
            }
            System.out.println(numberOfProcesses + " is your number. Running...");
            System.out.println(" ");
            System.out.print("|");
            System.out.print("   Test Case " + testCaseNumber++);
            System.out.print("\t|");
            System.out.print("\tRun Number\t");
            System.out.print("|");
            System.out.print("\tWaiting Time \t|");
            System.out.println("\tTurnaround Time\t|");
            scheduler test4 = new scheduler(numberOfProcesses);

        }

    }
}