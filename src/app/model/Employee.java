package app.model;

public abstract class Employee {
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
