package app;

public enum Option {
    SORT(1, "Sort Records"),
    SEARCH(2, "Search Records"),
    ADD_RECORDS(3, "Add Records"),
    CREATE_BINARY_TREE(4, "Create Binary Tree"),
    EXIT(5, "Exit");

    // private final options to store the option number and description
    private final int optionNumber;
    private final String optionDescription;

    // Constructor to initialize the option number and description
    Option(int optionNumber, String optionDescription) {
        this.optionNumber = optionNumber;
        this.optionDescription = optionDescription;
    }

    // Getters for option number and description
    public int getOptionNumber() {
        return optionNumber;
    }

    // Getters for option description
    public String getOptionDescription() {
        return optionDescription;
    }

    // Method to get the option based on the option number
    public static Option fromOptionNumber(int optionNumber) {
        for (Option option : Option.values()) {
            if (option.getOptionNumber() == optionNumber) {
                return option;
            }
        }
        // Return null if the option number is not found
        return null;
    }
}