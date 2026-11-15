package app.model.department;

import app.model.Department;

// Departments that run tests and scans for the clinical teams (Radiology, Laboratory)
public class DiagnosticDepartment extends HospitalDepartment {

    public DiagnosticDepartment(Department type) {
        super(type);
    }

    @Override
    public String getCategory() {
        return "Diagnostic";
    }

    @Override
    public boolean isOpen24Hours() {
        return true; // urgent tests and scans are needed day and night
    }
}
