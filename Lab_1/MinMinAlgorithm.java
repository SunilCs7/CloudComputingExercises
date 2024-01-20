import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Task implements Comparable<Task> {
    String taskId;
    int taskLength;
    int taskLoad;

    public Task(String taskId, int taskLength) {
        this.taskId = taskId;
        this.taskLength = taskLength;
        this.taskLoad = 0;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.taskLength, other.taskLength);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskLength=" + taskLength +
                ", taskLoad=" + taskLoad +
                '}';
    }
}

class Machine {
    String machineId;
    int machineLoad;

    public Machine(String machineId) {
        this.machineId = machineId;
        this.machineLoad = 0;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineId='" + machineId + '\'' +
                ", machineLoad=" + machineLoad +
                '}';
    }
}

public class MinMinAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of tasks: ");
        int numTasks = scanner.nextInt();

        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= numTasks; i++) {
            System.out.print("Enter length for Task " + i + ": ");
            int taskLength = scanner.nextInt();
            tasks.add(new Task("Task" + i, taskLength));
        }

        System.out.print("Enter the number of machines: ");
        int numMachines = scanner.nextInt();

        List<Machine> machines = new ArrayList<>();
        for (int i = 1; i <= numMachines; i++) {
            machines.add(new Machine("Machine" + i));
        }

        // Apply Min-Min scheduling algorithm
        minMinScheduling(tasks, machines);

        // Display allocation results
        System.out.println("\nFinal Machine Loads:");
        for (Machine machine : machines) {
            System.out.println(machine);
        }

        scanner.close();
    }

    private static void minMinScheduling(List<Task> tasks, List<Machine> machines) {
        Collections.sort(tasks);
        Collections.reverse(machines);

        for (Task task : tasks) {
            Machine selectedMachine = null;
            int minLoad = Integer.MAX_VALUE;

            for (Machine machine : machines) {
                int newLoad = machine.machineLoad + task.taskLength;
                if (newLoad < minLoad) {
                    minLoad = newLoad;
                    selectedMachine = machine;
                }
            }

            // Allocate task to the machine with the minimum load
            if (selectedMachine != null) {
                selectedMachine.machineLoad += task.taskLength;
                task.taskLoad = selectedMachine.machineLoad;
                System.out.println("Allocating " + task.taskId + " to " + selectedMachine.machineId);
            }
        }
    }
}
