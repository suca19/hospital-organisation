package app.model;

import java.util.Comparator;

public abstract class Employee {

    // sorting rules: alphabetical by name (ignoring case), and by seniority (rank 1 = most senior)
    public static final Comparator<Employee> BY_NAME =
            (a, b) -> a.getName().compareToIgnoreCase(b.getName());
    public static final Comparator<Employee> BY_RANK =
            (a, b) -> a.getManagerType().getRank() - b.getManagerType().getRank();

    private String name;
    private Department department;
    private ManagerType managerType;

    public Employee(String name, Department department, ManagerType managerType) {
        this.name = name;
        this.department = department;
        this.managerType = managerType;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public ManagerType getManagerType() {
        return managerType;
    }

    public abstract String getProfession();

    @Override
    public String toString() {
        return name + ", " + getProfession() + ", " + department.getDepartmentName() + ", " + managerType.getManagerTypeName();
    }
}
