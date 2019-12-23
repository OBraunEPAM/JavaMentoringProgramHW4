package utils.enums;

public enum SetupEnums {

    WEB_DRIVER ("WEB DRIVER"),
    REMOTE_WEB_DRIVER ("REMOTE_WEB DRIVER");

    public String value;

    SetupEnums(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SetupEnums{" +
                "value='" + value + '\'' +
                '}';
    }
}
