import java.util.List;

class Quest {
    private String name;
    private String description;
    private boolean isCompleted;
    private int killCount;

    public Quest(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getKillCount() {
        return killCount;
    }

    public void incrementKillCount() {
        killCount++;
    }

}

