package app.ui;

import java.util.Scanner;

import app.data.EmployeeReader;
import java.util.List;
import app.model.Employee;
import app.Option;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final EmployeeReader employeeReader = new EmployeeReader();
    private List<Employee> employees;

    // run method to start the menu and load the file
    public void run() {
        loadFile();
        mainLoop();
    }

    // method to load the file and read the employees
    private void loadFile() {
        do{
            System.out.println("Please type the name of your file to read");
            String fileName = scanner.nextLine().trim();
            employees = employeeReader.load(fileName);
        
        } while (employees.isEmpty());
            System.out.println("File read successfully!!");
    }

    // method to read an integer from the user input with error handling
    private int readInt() {
        while(true){
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("this is not a number, try again please!");
            }
        }
    }

    // method to display the menu options and read the user's choice
    private void mainLoop(){
        Option choice = null;

        while(choice != Option.EXIT){
                
                spacer();
                System.out.println("Printing Menu!!!");
                spacer();
                for(Option option : Option.values()){
                    System.out.println(option.getOptionNumber() + ". " + option.getOptionDescription());
                }
                spacer();

                choice = Option.fromOptionNumber(readInt());

                if(choice == null){
                    System.out.println("Invalid option, please select an option between 1-5");
                    continue;
                }

                switch (choice.getOptionDescription()) {
                    case "Sort Employees" -> System.out.println("Sort Employees selected!");
                    case "Search Records" -> System.out.println("Search Records selected!");
                    case "Add Records" -> System.out.println("Add Records selected!");
                    case "Create Binary Tree" -> System.out.println("Create Binary Tree selected!");
                    case "Exit" -> System.out.println("Exit selected!");
                }
        }

    }

    private void spacer(){
        System.out.println("-------------------");
    }
}
