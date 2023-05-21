class CollectAmuletQuest extends Quest {
    private String amuletType;
    private int requiredAmuletCount;

    public CollectAmuletQuest(String name, String description, String amuletType, int requiredAmuletCount) {
        super(name, description);
        this.amuletType = amuletType;
        this.requiredAmuletCount = requiredAmuletCount;
    }

    public String getAmuletType() {
        return amuletType;
    }

    public int getRequiredAmuletCount() {
        return requiredAmuletCount;
    }
}