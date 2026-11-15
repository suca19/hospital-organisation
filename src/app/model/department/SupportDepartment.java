package app.model.department;

import app.model.Department;

// Departments that support the hospital's operation (Pharmacy, Administration)
public class SupportDepartment extends HospitalDepartment {

    public SupportDepartment(Department type) {
        super(type);
    }

    @Override
    public String getCategory() {
        return "Support";
    }

    @Override
    public boolean isOpen24Hours() {
        return false; // these departments work office hours
    }
}
