import java.util.Date;

public class Item {
    private static int counter = 0;
    private int id;
    private String description;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Item(String description) {
        this.id = ++counter;
        this.description = description;
        this.status = "todo";
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String toJson() {
        return "{"
                + "\"id\":" + id + ","
                + "\"description\":\"" + description + "\","
                + "\"status\":\"" + status + "\","
                + "\"createdAt\":\"" + createdAt + "\","
                + "\"updatedAt\":\"" + updatedAt + "\""
                + "}";
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
