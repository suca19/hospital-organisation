package app.model;

import java.util.Comparator;

import app.model.department.HospitalDepartment;
import app.model.position.Position;

// Base class for all staff; abstract because every employee must be a Doctor, Nurse or Technician
public abstract class Employee {

    // sorting rules: alphabetical by name (ignoring case), and by seniority (rank 1 = most senior)
    public static final Comparator<Employee> BY_NAME =
            (a, b) -> a.getName().compareToIgnoreCase(b.getName());
    public static final Comparator<Employee> BY_RANK =
            (a, b) -> a.getManagerType().getRank() - b.getManagerType().getRank();

    private final String name;
    private final HospitalDepartment department; // where the employee works
    private final Position managerType;          // the employee's level in the hierarchy

    public Employee(String name, HospitalDepartment department, Position managerType) {
        this.name = name;
        this.department = department;
        this.managerType = managerType;
    }

    public String getName() {
        return name;
    }

    public HospitalDepartment getDepartment() {
        return department;
    }

    public Position getManagerType() {
        return managerType;
    }

    // each subclass returns its own profession name
    public abstract String getProfession();

    // one-line description used when printing an employee
    @Override
    public String toString() {
        return name + ", " + getProfession() + ", " + department.getDepartmentName() + ", " + managerType.getManagerTypeName();
    }
}
