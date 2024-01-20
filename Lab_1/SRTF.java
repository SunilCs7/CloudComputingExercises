
// SRTF
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int remainingBurstTime;

    Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
    }
}

public class SRTF {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for Process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();

            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();

            processes.add(new Process("P" + (i + 1), arrivalTime, burstTime));
        }

        performSRTF(processes);
    }

    private static void performSRTF(List<Process> processes) {
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.remainingBurstTime));
        int currentTime = 0;

        while (!processes.isEmpty() || !processQueue.isEmpty()) {
            // Add processes to the queue that have arrived by the current time
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime) {
                    processQueue.add(process);
                }
            }

            if (!processQueue.isEmpty()) {
                // Execute the process with the shortest remaining burst time
                Process currentProcess = processQueue.poll();
                int executionTime = Math.min(currentProcess.remainingBurstTime, 1);

                // Execute the process for the calculated time
                System.out.println(currentProcess.name + " is executing for " + executionTime + " unit(s).");

                currentTime += executionTime;
                currentProcess.remainingBurstTime -= executionTime;

                // If the process is not finished, add it back to the queue
                if (currentProcess.remainingBurstTime > 0) {
                    processQueue.add(currentProcess);
                } else {
                    // Remove the finished process from the list
                    processes.remove(currentProcess);
                }
            } else {
                // If there are no processes ready to execute, move to the next time unit
                currentTime++;
            }
        }

        System.out.println("\nAll processes completed!");
    }
}
