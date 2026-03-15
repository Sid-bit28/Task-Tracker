import java.io.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if(args.length < 1) {
            System.out.println("Usage: Main <task-name> [arguments]");
            return;
        }

        String taskName = args[0];
        switch(taskName) {
            case "add":
                if(args.length < 2) {
                    System.out.println("Usage: Main add [arguments]");
                    return;
                }
                taskManager.addTask(args[1]);
                System.out.println(args[1] + " task has been added.");
                break;
            case "update":
                if(args.length < 3) {
                    System.out.println("Usage: Main update <id> [arguments]");
                    return;
                }
                taskManager.updateTask(args[1], args[2]);
                System.out.println("Task updated successfully (ID: " + args[1] + ")");
                break;
            case "delete":
                if(args.length < 2) {
                    System.out.println("Usage: Main delete <id>");
                    return;
                }
                taskManager.deleteTask(args[1]);
                System.out.println("Task deleted successfully (ID: " + args[1] + ")");
                break;
            case "mark-in-progress":
                if(args.length < 2) {
                    System.out.println("Usage: Main mark-in-progress <id>");
                    return;
                }
                taskManager.markInProgress(args[1]);
                System.out.println("Task marked in progress successfully (ID: " + args[1] + ")");
                break;
            case "mark-done":
                if(args.length < 2) {
                    System.out.println("Usage: Main mark-done <id>");
                    return;
                }
                taskManager.markDone(args[1]);
                System.out.println("Task marked done successfully (ID: " + args[1] + ")");
                break;
            case "list":
                if(args.length < 2) {
                    taskManager.listTasks("All");
                }else {
                    Status filterStatus;
                    try {
                        filterStatus = Status.valueOf(args[1].toUpperCase().replace("-", "_"));
                    }catch(IllegalArgumentException e) {
                        System.out.println("Invalid status: " + args[1]);
                        return;
                    }
                    taskManager.listTasks(filterStatus.toString());
                }
                break;
            default:
                System.out.println("Unknown command: " + taskName);
        }
        taskManager.saveTasks();
    }
}