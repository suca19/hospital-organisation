package app.model.department;

import app.model.Department;

// Parent class for all departments: wraps the Department enum and adds behaviour per category
public abstract class HospitalDepartment {

    private final Department type; // which department this is (e.g. EMERGENCY)

    public HospitalDepartment(Department type) {
        this.type = type;
    }

    public Department getType() {
        return type;
    }

    public String getDepartmentName() {
        return type.getDepartmentName();
    }

    // each category returns its own name: "Clinical", "Diagnostic" or "Support"
    public abstract String getCategory();

    // whether the department has to stay open day and night
    public abstract boolean isOpen24Hours();
}
