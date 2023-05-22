class KillMonsterQuest extends Quest {
    private String monsterType;
    private int requiredKillCount;

    public KillMonsterQuest(String name, String description, String monsterType, int requiredKillCount) {
        super(name, description);
        this.monsterType = monsterType;
        this.requiredKillCount = requiredKillCount;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getRequiredKillCount() {
        return requiredKillCount;
    }

    @Override
    public boolean isCompleted() {
        return getKillCount() >= requiredKillCount;
    }
}