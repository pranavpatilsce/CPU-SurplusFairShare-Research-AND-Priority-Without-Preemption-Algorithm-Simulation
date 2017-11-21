
import java.util.Random;

/* Priority Scheduler without Pre-emption */



public class Main {

    static void printTableHeader() {
        System.out.print("|\tN\t|");
//        System.out.print("N");
//        System.out.print("\t|");
        System.out.print("\tRun Number\t");
        System.out.print("|");
        System.out.print("\tWaiting Time \t|");
        System.out.println("\tTurnaround Time\t|");
    }

    public static void main(String[] args)
    {
        // output the table header
        printTableHeader();

        Scheduler test_scheduler = new Scheduler(50);
        Scheduler test2 = new Scheduler( 100);
        Scheduler test3 = new Scheduler( 150);

        if (args.length > 0) {
            int testCaseNumber = 1;
            for (String s : args) {
                int numberOfProcesses = 0;
                try {
                    numberOfProcesses = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    System.out.println("Wrong argument!");
                }

                System.out.println("\nTest Case " + testCaseNumber++);
//                System.out.println(numberOfProcesses + " is your number. Running...");
                System.out.println(" ");
                System.out.print("|\tN");
                System.out.print("\t|");
                System.out.print("\tRun Number\t");
                System.out.print("|");
                System.out.print("\tWaiting Time \t|");
                System.out.println("\tTurnaround Time\t|");
                Scheduler test4 = new Scheduler(numberOfProcesses);
            }
        }
    } // end main method
}
