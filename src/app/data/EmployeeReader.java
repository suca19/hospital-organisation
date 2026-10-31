package app.data;

import java.util.List;

import app.model.Department;
import app.model.Employee;
import app.model.ManagerType;
import app.model.types.Doctor;
import app.model.types.Nurse;
import app.model.types.Technician;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeReader {

    // local variable to check if there are fewer than 9 parts, otherwise we return
    // null
    private static final int expectedColumns = 9;

    public List<Employee> load(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.isBlank()) {
                    continue;
                }
                Employee employee = parseEmployee(line);
                if (employee == null) {
                    System.out.println("Skipping line " + (i + 1) + ": " + line);
                    continue;
                }
                employees.add(employee);
            }

            System.out.println("Loaded " + employees.size() + " Employees");
            return employees;
        } catch (IOException e) {
            System.out.println("Could not read the file: " + e.getMessage());
        }

        return employees;
    }

    private Employee parseEmployee(String line) {

        String[] parts = line.split(",");

        if (parts.length < expectedColumns) {
            return null;
        }

        String name = parts[0].trim() + " " + parts[1].trim();
        Department department = Department.fromDepartmentName(parts[5].trim());
        ManagerType managerType = ManagerType.fromManagerTypeName(parts[7].trim());

        if (department == null || managerType == null) {
            return null;
        }

        String profession = parts[6].trim();

        switch (profession.toLowerCase()) {
            case "doctor" -> {
                return new Doctor(name, department, managerType);
            }
            case "nurse" -> {
                return new Nurse(name, department, managerType);
            }
            case "technician" -> {
                return new Technician(name, department, managerType);
            }
            default -> {
                return null;
            } // unknown profession
        }

    }
}
