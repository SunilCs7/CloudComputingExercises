import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class User {
    String userId;
    int resourceRequirement;

    public User(String userId, int resourceRequirement) {
        this.userId = userId;
        this.resourceRequirement = resourceRequirement;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", resourceRequirement=" + resourceRequirement +
                '}';
    }
}

class Resource {
    String resourceId;
    int availability;

    public Resource(String resourceId, int availability) {
        this.resourceId = resourceId;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId='" + resourceId + '\'' +
                ", availability=" + availability +
                '}';
    }
}

public class MaxMinAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of users: ");
        int numUsers = scanner.nextInt();

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= numUsers; i++) {
            System.out.print("Enter resource requirement for User " + i + ": ");
            int resourceRequirement = scanner.nextInt();
            users.add(new User("User" + i, resourceRequirement));
        }

        System.out.print("Enter the number of resources: ");
        int numResources = scanner.nextInt();

        List<Resource> resources = new ArrayList<>();
        for (int i = 1; i <= numResources; i++) {
            System.out.print("Enter availability for Resource " + i + ": ");
            int availability = scanner.nextInt();
            resources.add(new Resource("Resource" + i, availability));
        }

        // Sort users based on resource requirements (descending order)
        Collections.sort(users, (u1, u2) -> Integer.compare(u2.resourceRequirement, u1.resourceRequirement));

        // Sort resources based on availability (ascending order)
        Collections.sort(resources, (r1, r2) -> Integer.compare(r1.availability, r2.availability));

        // Allocate resources to users
        allocateResources(users, resources);

        // Display allocation results
        for (User user : users) {
            System.out.println(user.userId + " is allocated resources.");
        }

        scanner.close();
    }

    private static void allocateResources(List<User> users, List<Resource> resources) {
        for (User user : users) {
            for (Resource resource : resources) {
                if (resource.availability >= user.resourceRequirement) {
                    // Allocate resource to the user
                    System.out.println("Allocating " + resource.resourceId + " to " + user.userId);
                    resource.availability -= user.resourceRequirement;
                    break;
                }
            }
        }
    }
}
