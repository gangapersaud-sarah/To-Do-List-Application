package ToDoList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoListTests {
	@Test
    void testAddTask1() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDoListApp.addTask(tasks, "Test Task");
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }

    @Test
    void testMarkTaskAsCompleted1() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test Task"));
        ToDoListApp.markTaskAsCompleted(tasks, 0);
        assertEquals(true, tasks.get(0).isCompleted());
    }

    @Test
    void testRemoveTask1() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test Task"));
        ToDoListApp.removeTask(tasks, 0);
        assertEquals(0, tasks.size());
    }
    
    @Test
    void testAddTask2() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDoListApp.addTask(tasks, "Test Task");
        assertEquals(1, tasks.size());
        ToDoListApp.addTask(tasks, "Test Task2");
        assertEquals(2, tasks.size());
        ToDoListApp.addTask(tasks, "Test Task3");
        assertEquals(3, tasks.size());
        ToDoListApp.addTask(tasks, "Test Task4");
        assertEquals(4, tasks.size());
        assertEquals("Test Task3", tasks.get(2).getDescription());
    }

    @Test
    void testMarkTaskAsCompleted2() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test Task"));
        ToDoListApp.addTask(tasks, "Test Task2");
        assertEquals(2, tasks.size());
        ToDoListApp.addTask(tasks, "Test Task3");
        assertEquals(3, tasks.size());
        ToDoListApp.addTask(tasks, "Test Task4");
        ToDoListApp.markTaskAsCompleted(tasks, 1);
        ToDoListApp.markTaskAsCompleted(tasks, 3);
        assertEquals(true, tasks.get(1).isCompleted());
        assertEquals(true, tasks.get(3).isCompleted());
    }

    @Test
    void testRemoveTask2() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test Task"));
        tasks.add(new Task("Test Task2"));
        tasks.add(new Task("Test Task3"));
        tasks.add(new Task("Test Task4"));
        tasks.add(new Task("Test Task5"));
        ToDoListApp.removeTask(tasks, 0);
        ToDoListApp.removeTask(tasks, 3);
        assertEquals(3, tasks.size());
    }
    
    @Test
    void testViewTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test Task 1"));
        tasks.add(new Task("Test Task 2"));

        // Set up input/output redirection for testing
        ByteArrayInputStream inputStream = new ByteArrayInputStream("4\n5\n".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setIn(inputStream);
        System.setOut(printStream);

        // Run the app
        ToDoListApp.viewTasks(tasks);

        // Check the output
        String expectedOutput = "Tasks:\n0. Test Task 1 - Not Completed\n" +
                                "1. Test Task 2 - Not Completed\n";
        assertEquals(expectedOutput, outputStream.toString().replaceAll("\\r\\n", "\n"));

        // Restore standard input/output
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
