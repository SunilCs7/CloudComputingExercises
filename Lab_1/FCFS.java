import java.util.Scanner;

public class FCFS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.println("Enter arrival time and burst time for each process:");

        for (int i = 0; i < n; i++) {
            System.out.print("Arrival time for Process " + (i + 1) + ": ");
            arrivalTime[i] = scanner.nextInt();

            System.out.print("Burst time for Process " + (i + 1) + ": ");
            burstTime[i] = scanner.nextInt();
        }

        // Calculate completion time for each process
        completionTime[0] = arrivalTime[0] + burstTime[0];
        for (int i = 1; i < n; i++) {
            completionTime[i] = Math.max(completionTime[i - 1], arrivalTime[i]) + burstTime[i];
        }

        // Calculate waiting time for each process
        for (int i = 0; i < n; i++) {
            waitingTime[i] = completionTime[i] - arrivalTime[i] - burstTime[i];
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
        }

        // Display results
        System.out.println("\nProcess\t Arrival Time\t Burst Time\t Waiting Time\t Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t\t " + arrivalTime[i] + "\t\t " + burstTime[i] +
                    "\t\t " + waitingTime[i] + "\t\t " + turnaroundTime[i]);
        }

        // Calculate and display average waiting time and average turnaround time
        double avgWaitingTime = calculateAverage(waitingTime);
        double avgTurnaroundTime = calculateAverage(turnaroundTime);
        System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    private static double calculateAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }
}
