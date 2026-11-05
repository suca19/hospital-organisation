package app.ui;

import java.util.Scanner;

import app.algorithms.Sorter;
import app.data.EmployeeReader;
import java.util.List;
import app.model.Employee;
import app.Option;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeReader employeeReader = new EmployeeReader();
    private final Sorter sorter = new Sorter();
    private List<Employee> employees;

    // run method to start the menu and load the file
    public void run() {
        loadFile();
        mainLoop();
    }

    // method to load the file and read the employees
    private void loadFile() {
        do {
            System.out.println("Please type the name of your file to read");
            String fileName = scanner.nextLine().trim();
            employees = employeeReader.load(fileName);

        } while (employees.isEmpty());
        System.out.println("-----> File read successfully!!");
    }

    // method to read an integer from the user input with error handling
    private int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("this is not a number, try again please!");
            }
        }
    }

    // method to display the menu options and read the user's choice
    private void mainLoop() {
        Option choice = null;

        while (choice != Option.EXIT) {

            spacer();
            System.out.println("Printing Menu, Select an option between 1-5");
            spacer();
            for (Option option : Option.values()) {
                System.out.println(option.getOptionNumber() + ". " + option.getOptionDescription());
            }
            spacer();

            choice = Option.fromOptionNumber(readInt());

            if (choice == null) {
                System.out.println("Invalid option, please select an option between 1-5");
                continue;
            }

            switch (choice) {
                case SORT -> sortEmployees();
                case SEARCH -> System.out.println("Search Records selected!");
                case ADD_RECORDS -> System.out.println("Add Records selected!");
                case CREATE_BINARY_TREE -> System.out.println("Create Binary Tree selected!");
                case EXIT -> System.out.println("Goodbye!");
            }
        }

    }

    // sorts the employees with Merge Sort and displays the first 20
    private void sortEmployees() {
        employees = sorter.mergeSort(employees);

        int limit = Math.min(20, employees.size());
        spacer();
        System.out.println("First " + limit + " employees sorted alphabetically:");
        for (int k = 0; k < limit; k++) {
            System.out.println((k + 1) + ". " + employees.get(k));
        }
        spacer();
    }

    private void spacer() {
        System.out.println("-------------------");
    }
}
