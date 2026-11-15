package app.model.department;

import app.model.Department;

// The only class that decides which category each department belongs to
public class DepartmentFactory {

    public static HospitalDepartment create(Department type) {
        switch (type) {
            case EMERGENCY, CARDIOLOGY, PAEDIATRICS, SURGERY -> {
                return new ClinicalDepartment(type);
            }
            case RADIOLOGY, LABORATORY -> {
                return new DiagnosticDepartment(type);
            }
            case PHARMACY, ADMINISTRATION -> {
                return new SupportDepartment(type);
            }
            default -> // only reached if a new department is added to the enum but not here
                throw new IllegalArgumentException("Unknown department: " + type);
        }
    }
}
