package app.model.department;

import app.model.Department;

// Departments that treat patients directly (Emergency, Cardiology, Paediatrics, Surgery)
public class ClinicalDepartment extends HospitalDepartment {

    public ClinicalDepartment(Department type) {
        super(type);
    }

    @Override
    public String getCategory() {
        return "Clinical";
    }

    @Override
    public boolean isOpen24Hours() {
        return true; // patients can need treatment at any time
    }
}
