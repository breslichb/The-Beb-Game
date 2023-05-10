public class Quest {
    private questDes description;

    public Quest(questDes description) {
        this.description = description;
    }

    public questDes getDescription() {
        return description;
    }
    public enum questDes {
        KILL_ENEMIES,
        COLLECT_ITEMS,
    }
}