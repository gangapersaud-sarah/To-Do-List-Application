package ToDoList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListApp {
    public static void main(String[] args) {
        ArrayList<Task> tasks = loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Remove Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter task description:");
                    String description = scanner.nextLine();
                    Task newTask = new Task(description);
                    tasks.add(newTask);
                    System.out.println("Task added.");
                    saveTasksToFile(tasks); // Save tasks to file
                    break;

                case 2:
                	System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + ". " + task.getDescription() + " - " +
                                           (task.isCompleted() ? "Completed" : "Not Completed"));
                    }
                	System.out.println("Enter the index of the task to mark as completed:");
                    int indexToComplete = scanner.nextInt();
                    if (indexToComplete >= 0 && indexToComplete < tasks.size()) {
                        Task taskToComplete = tasks.get(indexToComplete);
                        taskToComplete.markAsCompleted();
                        System.out.println("Task marked as completed.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    saveTasksToFile(tasks); // Save tasks to file
                    break;

                case 3:
                    System.out.println("Enter the index of the task to remove:");
                    System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + ". " + task.getDescription() + " - " +
                                           (task.isCompleted() ? "Completed" : "Not Completed"));
                    }
                    int indexToRemove = scanner.nextInt();
                    if (indexToRemove >= 0 && indexToRemove < tasks.size()) {
                        tasks.remove(indexToRemove);
                        System.out.println("Task removed.");
                    } else {
                        System.out.println("Invalid index.");
                    }
                    saveTasksToFile(tasks); // Save tasks to file
                    break;

                case 4:
                    System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(i + ". " + task.getDescription() + " - " +
                                           (task.isCompleted() ? "Completed" : "Not Completed"));
                    }
                    break;

                case 5:
                    saveTasksToFile(tasks); // Save tasks to file
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
            }
        }
	}

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tasks.ser"))) {
            tasks = (ArrayList<Task>) inputStream.readObject();
            System.out.println("Tasks loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved tasks found. Starting with an empty task list.");
        }
        return tasks;
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tasks.ser"))) {
            outputStream.writeObject(tasks);
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save tasks to file.");
        }
    }
    
    public static void addTask(ArrayList<Task> tasks, String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
    }

    public static void markTaskAsCompleted(ArrayList<Task> tasks, int index) {
        if (index >= 0 && index < tasks.size()) {
            Task taskToComplete = tasks.get(index);
            taskToComplete.markAsCompleted();
        }
    }

    public static void removeTask(ArrayList<Task> tasks, int index) {
    	if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public static void viewTasks(ArrayList<Task> tasks) {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + ". " + task.getDescription() + " - " +
                               (task.isCompleted() ? "Completed" : "Not Completed"));
        }
    }
    
}
