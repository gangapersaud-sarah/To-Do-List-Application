# To-Do-List-Application
This code defines a simple command-line To-Do List application. It allows users to manage tasks by adding, marking as completed, removing, and viewing tasks. The application uses Java and JUnit for testing.

**Classes:**

1. Task Class:

- This class defines a Task object with properties like description (the task's description) and completed (a boolean flag indicating whether the task is completed). It provides methods to access and modify these properties.

 
2. ToDoListApp Class:

- This class contains the main logic of the To-Do List application. It provides a command-line interface for users to interact with tasks.
- main: This method is the entry point of the application. It starts by loading tasks from a file using the loadTasksFromFile method. It then enters a loop to display a menu and accept user input. The user can choose options to perform various tasks, such as adding, marking as completed, removing, and viewing tasks.
- loadTasksFromFile: This method attempts to load tasks from a file named "tasks.ser" using object serialization. If no tasks are found, it starts with an empty task list.
- saveTasksToFile: This method saves the list of tasks to the "tasks.ser" file using object serialization.
- addTask, markTaskAsCompleted, removeTask, and viewTasks: These methods provide functionalities for adding, marking tasks as completed, removing tasks, and viewing tasks, respectively. They take the task list and necessary parameters as input.

**How to Use it:**

- Compile the code and run the ToDoListApp class.
- The application will display a menu with options: adding tasks, marking tasks as completed, removing tasks, viewing tasks, and exiting.
- Choose an option by entering the corresponding number and following the prompts.
