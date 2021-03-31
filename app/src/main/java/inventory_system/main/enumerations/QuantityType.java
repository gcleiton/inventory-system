package inventory_system.main.enumerations;

public enum QuantityType {
    ADDITION(1),
    SUBTRACTION(2);

    private int value;

    private QuantityType(int value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
