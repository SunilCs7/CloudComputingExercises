import java.util.Arrays;
import java.util.Scanner;

class Process implements Comparable<Process> {
    int processId;
    int arrivalTime;
    int burstTime;

    public Process(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.burstTime, other.burstTime);
    }
}

public class SJFScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();

            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Sort processes based on burst time (SJF)
        Arrays.sort(processes);

        // Calculate completion time and turnaround time
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int totalTurnaroundTime = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                completionTime[i] = processes[i].arrivalTime + processes[i].burstTime;
            } else {
                completionTime[i] = Math.max(processes[i].arrivalTime, completionTime[i - 1]) + processes[i].burstTime;
            }

            turnaroundTime[i] = completionTime[i] - processes[i].arrivalTime;
            totalTurnaroundTime += turnaroundTime[i];
        }

        // Calculate average turnaround time
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        // Display results
        System.out.println("\nProcess\t Arrival Time\t Burst Time\t Completion Time\t Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processId + "\t\t" + processes[i].arrivalTime + "\t\t" +
                    processes[i].burstTime + "\t\t" + completionTime[i] + "\t\t\t" + turnaroundTime[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + avgTurnaroundTime);

        scanner.close();
    }
}
