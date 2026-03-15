import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {
    private Path FILE_PATH = Path.of("tasks.json");
    private List<Item> tasks;

    public TaskManager() {
        this.tasks = loadTasks();
    }

    // This code part reads through the json and get the data loaded.
    private List<Item> loadTasks() {
        List<Item> stored_items = new ArrayList<>();

        if(!Files.exists(FILE_PATH)) {
            return stored_items;
        }

        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] taskList = jsonContent
                    .replace("[", "")
                    .replace("]", "")
                    .split("},");
            for(String taskJson: taskList) {
                if(!taskJson.endsWith("}")) {
                    taskJson = taskJson + "}";
                    stored_items.add(Item.fromJson(taskJson));
                }else {
                    stored_items.add(Item.fromJson(taskJson));
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return stored_items;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());
            if(i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonContent = sb.toString();
        try {
            Files.writeString(FILE_PATH, jsonContent);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String description) {
        Item newItem = new Item(description);
        tasks.add(newItem);
        System.out.println("Task added successfully");
    }

    public void updateTask(String id, String newDescription) {
        Item item = findItem(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
        item.updateDescription(newDescription);
    }

    public void deleteTask(String id) {
        Item item = findItem(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
        tasks.remove(item);
    }

    public void markInProgress(String id) {
        Item item = findItem(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
        item.markInProgress();
    }

    public void markDone(String id) {
        Item item = findItem(id).orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found"));
        item.markDone();
    }

    public void listTasks(String type) {
        for(Item item: tasks) {
            String status = item.getStatus().toString();
            if(type.equals("All") || status.equals(type)) {
                System.out.println(item.toString());
            }
        }
    }

    public Optional<Item> findItem(String id) {
        return tasks.stream().filter((item) -> item.getId() == Integer.parseInt(id)).findFirst();
    }
}
