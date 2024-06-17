package frontend;

public enum Color {

    RESET("\033[0m"),
    YELLOW("\033[0;33m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
