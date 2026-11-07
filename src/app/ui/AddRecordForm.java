package app.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.model.Department;
import app.model.Employee;
import app.model.EmployeeFactory;
import app.model.ManagerType;
import app.model.Profession;

// Asks the user for a new employee's details, validates them and adds the employee to the list
public class AddRecordForm {

    private final Scanner scanner;                                  // shared with Menu (only one Scanner on System.in)
    private final List<Employee> newEmployees = new ArrayList<>(); // remembers every record added in this session

    public AddRecordForm(Scanner scanner) {
        this.scanner = scanner;
    }

    // employees = the CURRENT main list from Menu (it changes after every sort)
    public void run(List<Employee> employees) {
        String name = readName();
        Profession profession = chooseProfession();
        ManagerType managerType = chooseManagerType();
        Department department = chooseDepartment();

        Employee newEmployee = EmployeeFactory.createEmployee(profession, name, department, managerType);
        employees.add(newEmployee);     // stored in the program's memory (main list)
        newEmployees.add(newEmployee);  // remembered as "newly added"

        System.out.println("-------------------");
        System.out.println("\"" + name + "\" has been added as \"" + managerType.getManagerTypeName()
                + "\" to \"" + department.getDepartmentName() + "\" successfully!");
        showNewEmployees();
    }

    // asks until the name is not empty
    private String readName() {
        String name;
        do {
            System.out.println("Please input the Employee Name:");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("The name cannot be empty, try again please!");
            }
        } while (name.isEmpty());
        return name;
    }

    // each choose method prints the enum menu by looping values() and repeats until the number exists
    private Profession chooseProfession() {
        Profession profession = null;
        while (profession == null) {
            System.out.println("Please select the Profession:");
            for (Profession p : Profession.values()) {
                System.out.println(p.getProfessionNumber() + ". " + p.getProfessionName());
            }
            profession = Profession.fromProfessionNumber(readInt());
            if (profession == null) {
                System.out.println("Invalid profession, try again please!");
            }
        }
        return profession;
    }

    private ManagerType chooseManagerType() {
        ManagerType managerType = null;
        while (managerType == null) {
            System.out.println("Please select from the following Management Staff:");
            for (ManagerType m : ManagerType.values()) {
                System.out.println(m.getManagerTypeNumber() + ". " + m.getManagerTypeName());
            }
            managerType = ManagerType.fromManagerTypeNumber(readInt());
            if (managerType == null) {
                System.out.println("Invalid manager type, try again please!");
            }
        }
        return managerType;
    }

    private Department chooseDepartment() {
        Department department = null;
        while (department == null) {
            System.out.println("Please select the Department:");
            for (Department d : Department.values()) {
                System.out.println(d.getDepartmentNumber() + ". " + d.getDepartmentName());
            }
            department = Department.fromDepartmentNumber(readInt());
            if (department == null) {
                System.out.println("Invalid department, try again please!");
            }
        }
        return department;
    }

    // prints every record added so far in this session
    private void showNewEmployees() {
        System.out.println("Newly added records (" + newEmployees.size() + "):");
        for (int k = 0; k < newEmployees.size(); k++) {
            System.out.println((k + 1) + ". " + newEmployees.get(k));
        }
    }

    // reads a whole number, asking again if the input is not a number
    private int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("This is not a number, try again please!");
            }
        }
    }
}
