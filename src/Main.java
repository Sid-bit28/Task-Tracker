import java.io.*;

public class Main {
    public static void main(String[] args) {
        readJSON();
        if(args.length < 1) {
            System.out.println("Usage: task-cli <task-name> [arguments]");
            return;
        }

        String taskName = args[0];
        switch(taskName) {
            case "add":
                if(args.length < 2) {
                    System.out.println("Usage: task-cli add [arguments]");
                    return;
                }
                String description = args[1];
                addTask(description);
                break;
            case "update":
                if(args.length < 3) {
                    System.out.println("Usage: task-cli update <id> [arguments]");
                    return;
                }
                updateTask(args[1], args[2]);
                break;
            case "delete":
                if(args.length < 2) {
                    System.out.println("Usage: task-cli delete <id>");
                    return;
                }
                deleteTask(args[1]);
                break;
            default:
                System.out.println("Unknown command: " + taskName);
        }
    }

    private static void addTask(String description) {
        Item todoTask = new Item(description);
        int id = todoTask.getId();
        System.out.println("Id: " + id);
        try {
            File file = new File("task.json");
            if(!file.exists()) {
                FileWriter writer = new FileWriter(file);
                writer.write("[]");
                writer.close();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String content = "";
            String line;
            while((line = reader.readLine()) != null) {
                content += line;
            }
            reader.close();
            content = content.trim();
            if(content.equals("[]")) {
                content = "[" + todoTask.toJson() + "]";
            }else {
                content = content.substring(0, content.length() - 1) +
                        "," + todoTask.toJson() + "]";
            }
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
            System.out.println("Successfully added task: " + todoTask.toJson());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateTask(String id, String description) {

    }

    private static void deleteTask(String id) {

    }

    private static void readJSON() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("task.json"));
            String content = "";
            String line;
            while((line = reader.readLine()) != null) {
                content += line;
            }
            reader.close();
            System.out.println(content + "\n");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}