package app.ui;

import java.util.Scanner;

import app.algorithms.Sorter;
import app.data.EmployeeReader;
import java.util.List;
import app.model.Employee;
import app.Option;
import app.algorithms.Searcher;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeReader employeeReader = new EmployeeReader();
    private final Sorter sorter = new Sorter();
    private final Searcher searcher = new Searcher();
    private List<Employee> employees;
    private final AddRecordForm addRecordForm = new AddRecordForm(scanner);

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
                case SEARCH -> searchRecords();
                case ADD_RECORDS -> addRecordForm.run(employees);
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

    // sorts the list (binary search needs it sorted), asks for a name and searches
    // it recursively
    private void searchRecords() {
        employees = sorter.mergeSort(employees);

        String targetName;
        do {
            System.out.println("Please type the full name of the employee you want to search for:");
            targetName = scanner.nextLine().trim();
            if (targetName.isEmpty()) {
                System.out.println("The name cannot be empty, try again please!");
            }
        } while (targetName.isEmpty());

        Employee found = searcher.binarySearch(employees, targetName, 0, employees.size() - 1);

        spacer();
        if (found == null) {
            System.out.println("Employee \"" + targetName + "\" not found.");
        } else {
            System.out.println("Employee found: " + found.getName());
            System.out.println("Manager Type:   " + found.getManagerType().getManagerTypeName());
            System.out.println("Department:     " + found.getDepartment().getDepartmentName());
        }
        spacer();
    }

    private void spacer() {
        System.out.println("-------------------");
    }
}
