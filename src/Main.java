

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
        System.out.println("\tMade by Matthew Chuw, Andrew Hu, Pranav Patil, & Dmitry Sokolov.");
        System.out.println("\tCMPE 142-03 Fall 2017.\n");
    }
}
