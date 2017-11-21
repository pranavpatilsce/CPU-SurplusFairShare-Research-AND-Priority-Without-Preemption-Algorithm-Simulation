import java.util.Random;

 class Scheduler {

    static int NUMBER_RUN_TIMES = 5;

    private int N = 0;
    int time = 0;
    int waiting = 0; //Todo: Find average waiting time
    int turnaround = 0; //Todo: Find average turnaround time
    int head = 0;
    int tail = 0;

    Scheduler(int aNumberOfProcesses) {
        // Todo: Loop run number with different seeds
        // Todo: Iterate for N = 100 and N = 500

        N = aNumberOfProcesses;
        tail = aNumberOfProcesses - 1;

        Process[] processList;
        processList = new Process[N];


        float waiting = 0;
        float turnaround = 0;

        //Instantiate all processes
        for(int k = 0; k < NUMBER_RUN_TIMES; k++) {
            Random rng = new Random(k); // initialize a random generator

            for (int i = 0; i < N; i++) {
                int CPUBurst = rng.nextInt(101);
                int arrivalTime = rng.nextInt(1001);
                int priority = rng.nextInt(11);

                processList[i] = new Process(i, CPUBurst, arrivalTime, priority);
            }


            //Sort by Arrival
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (processList[i].arrival < processList[j].arrival) {
                        Process temp = new Process(processList[i]);
                        processList[i] = processList[j];
                        processList[j] = temp;
                    }
                }
            }
            //Initialize time to first arrived Process time
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
                            Process temp = new Process(processList[i]);
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
