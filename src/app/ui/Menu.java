package app.ui;

import java.util.Scanner;

import app.data.EmployeeReader;
import java.util.List;
import app.model.Employee;
import app.Option;;

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
            System.out.println("loadinf File!");
            String fileName = scanner.nextLine().trim();
            employees = employeeReader.load(fileName);
        
        } while (employees.isEmpty());
            System.out.println("File loaded!!");
    }

    // method to read an integer from the user input with error handling
    private int readInt() {
        while(true){
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input)
            } catch (NumberFormatException e) {
                // TODO: handle exception
                System.out.println("this is not a number, try again please!");
            }
        }
    }

    // method to display the menu options and read the user's choice
    private void mainLoop(){
        Option choice = null;

        while(choice != Option.EXIT){

        }
    }
}
