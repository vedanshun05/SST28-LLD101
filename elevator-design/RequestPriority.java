public enum RequestPriority {
    LOW(1),
    NORMAL(2),
    HIGH(3),
    EMERGENCY(4);

    private final int value;

    RequestPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
