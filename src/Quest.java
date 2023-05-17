public class Quest {
    private QuestType type;
    private String description;
    private int requiredEnemyCount;
    private int currentEnemyCount;

    public Quest(QuestType type, String description) {
        this.type = type;
        this.description = description;
        this.requiredEnemyCount = 0;
        this.currentEnemyCount = 0;
    }

    public QuestType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getRequiredEnemyCount() {
        return requiredEnemyCount;
    }

    public void setRequiredEnemyCount(int requiredEnemyCount) {
        this.requiredEnemyCount = requiredEnemyCount;
    }

    public int getCurrentEnemyCount() {
        return currentEnemyCount;
    }

    public void incrementEnemyCount() {
        currentEnemyCount++;
    }

    public boolean isQuestCompleted() {
        return currentEnemyCount >= requiredEnemyCount;
    }

    public enum QuestType {
        KILL_ENEMIES,
        COLLECT_ITEMS,
    }
}
