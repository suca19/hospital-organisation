package app.data;

import java.util.List;

import app.model.Department;
import app.model.Employee;
import app.model.EmployeeFactory;
import app.model.ManagerType;
import app.model.Profession;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;

// Reads the staff file and turns each valid line into an Employee
public class EmployeeReader {

    // number of columns every line must have; shorter lines are skipped
    private static final int expectedColumns = 9;

    // loads all valid employees from the file; returns an empty list if the file can't be read
    public List<Employee> load(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (int i = 1; i < lines.size(); i++) { // start at 1 to skip the header line
                String line = lines.get(i);

                if (line.isBlank()) { // ignore empty lines
                    continue;
                }
                Employee employee = parseEmployee(line);
                if (employee == null) {
                    System.out.println("Skipping line " + (i + 1) + ": " + line);
                    continue;
                }
                employees.add(employee);
            }

            System.out.println("-----> Loaded " + employees.size() + " Employees");
            return employees;
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }

        return employees;
    }

    // turns one CSV line into an Employee, or returns null if any value is missing or unknown
    private Employee parseEmployee(String line) {

        String[] parts = line.split(",");

        if (parts.length < expectedColumns) {
            return null;
        }

        // columns: 0 first name, 1 last name, 5 department, 6 profession, 7 job title
        String name = parts[0].trim() + " " + parts[1].trim();
        Department department = Department.fromDepartmentName(parts[5].trim());
        ManagerType managerType = ManagerType.fromManagerTypeName(parts[7].trim());

        if (department == null || managerType == null) {
            return null;
        }

        Profession profession = Profession.fromProfessionName(parts[6].trim());
        if(profession == null) {
            return null;
        }

        return EmployeeFactory.createEmployee(profession, name, department, managerType); // builds Doctor / Nurse / Technician

    }
}
